package com.lvjc.web;

import com.lvjc.cons.JspConstants;
import com.lvjc.cons.SessionConstants;
import com.lvjc.exception.analysis.AnalyseFailedException;
import com.lvjc.exception.analysis.AnalysisNotFoundException;
import com.lvjc.exception.transaction.*;
import com.lvjc.exception.user.PasswordWrongException;
import com.lvjc.po.DetailUser;
import com.lvjc.po.TransactionField;
import com.lvjc.po.TransactionMode;
import com.lvjc.service.AnalysisService;
import com.lvjc.service.TransactionFieldService;
import com.lvjc.service.TransactionModeService;
import com.lvjc.service.TransactionService;
import com.lvjc.service.analysis.Analysis;
import com.lvjc.service.analysis.AnalysisParameter;
import com.lvjc.service.analysis.AnalysisResult;
import com.lvjc.support.util.DateUtil;
import com.lvjc.vo.TransactionVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by lvjc on 2017/7/6.
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController extends BaseController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionModeService transactionModeService;

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private TransactionFieldService transactionFieldService;

    private static final String TRANSACTIONS = "transactions";

    private static final String TRANSACTION = "transaction";

    private static final String USERNAME = "username";

    private static final String TRANSACTION_MODES = "transaction_modes";

    private static final String TRANSACTION_FIELDS = "transaction_fields";

    private static final String DETAIL = "detail";

    private static final String ANALYSIS_MONTH = "analysis_month";

    private static final String ANALYSIS_DATA = "analysis_data";

    private static final String LIST_ANALYSIS = "analysisList";

    private static final String ANALYSIS_PARAM = "analysis_param";

    @RequestMapping("/record")
    public ModelAndView transactionRecord(HttpServletRequest request, String id, String date){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactionRecord");
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        List<TransactionVo> transactions = null;
        if(!StringUtils.isBlank(id)){
            transactions = new ArrayList<>(1);
            TransactionVo transaction = null;
            try {
                transaction = transactionService.getTransactionById(user, id);
            } catch (QueryFailedException queryFailedExcep) {
                modelAndView.addObject(JspConstants.ERROR_MESSAGE, queryFailedExcep.getMessage());
            }
            transactions.add(transaction);
        } else if(!StringUtils.isBlank(date)){
            try {
                transactions = transactionService.buildTransactionVoList(user, transactionService.getTransactionsOfDate(user, date));
            } catch (IncorrectDatePatternException e) {
                modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
            }
        } else{
            //默认读取本月交易记录
            try {
                transactions = transactionService.getTransactionsOfCurrentMonth(user);
            } catch (QueryFailedException e) {
                modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
            }
        }
        modelAndView.addObject(USERNAME, user.getUsername());
        modelAndView.addObject(TRANSACTIONS, transactions);
        List<TransactionField> fields = transactionFieldService.getAllTransactionFields(user);
        List<TransactionMode> modes = transactionModeService.getAllTransactionModes(user);
        modelAndView.addObject(TRANSACTION_MODES, modes);
        modelAndView.addObject(TRANSACTION_FIELDS, fields);
        return modelAndView;
    }

    @RequestMapping("/record/add")
    public ModelAndView addTransaction(HttpServletRequest request, TransactionVo transactionVo, String detail){
        ModelAndView modelAndView = new ModelAndView();
        String month;
        try{
            try {
                month = DateUtil.getMonthFromString(transactionVo.getDate());
            } catch (IncorrectDatePatternException e) {
                throw new AddTransactionFailedException();
            }
            modelAndView.setViewName("redirect:/transaction/record?date=" + month);
            try {
                DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
                transactionService.addTransaction(user, transactionService.buildTransaction(user, transactionVo, detail));
            } catch (Exception e) {
                throw new AddTransactionFailedException();
            }
        } catch (AddTransactionFailedException e){
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/record/update")
    public ModelAndView updateTransaction(HttpServletRequest request, TransactionVo transactionVo, String detail, String password){
        ModelAndView modelAndView = new ModelAndView();
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        String month;
        try{
            //私密状态从Yes修改为No需要密码验证
            try {
                transactionService.checkUpdatePassword(user, transactionVo, password);
            } catch (IncorrectDatePatternException e) {
                throw new UpdateTransactionFailedException();
            } catch (PasswordWrongException e) {
                modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
                modelAndView.setViewName("forward:/transaction/record/detail/" + transactionVo.getId());
                return modelAndView;
            }
            //验证无误再修改
            try {
                month = DateUtil.getMonthFromString(transactionVo.getDate());
            } catch (IncorrectDatePatternException e) {
                throw new UpdateTransactionFailedException();
            }
            modelAndView.setViewName("redirect:/transaction/record?date=" + month);
            try {

                transactionService.updateTransaction(user, transactionService.buildTransaction(user, transactionVo, detail));
            } catch (Exception e) {
                throw new UpdateTransactionFailedException();
            }
        } catch (UpdateTransactionFailedException e){
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/record/delete/{id}")
    public ModelAndView deleteTransaction(HttpServletRequest request, @PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        String month;
        try {
            try {
                Date date = transactionService.getDateById(user, id);
                month = DateUtil.getMonthFromString(DateUtil.dateToString(date));
            } catch (Exception e) {
                throw new DeleteTransactionFailedException();
            }
            modelAndView.setViewName("redirect:/transaction/record?date=" + month);
            transactionService.deleteTransaction(user, id);
        } catch (DeleteTransactionFailedException e) {
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/record/detail/{id}")
    public ModelAndView getDetailInfo(HttpServletRequest request, @PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactionDetail");
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        try {
            modelAndView.addObject(TRANSACTION, transactionService.getTransactionById(user, id));
            modelAndView.addObject(DETAIL, transactionService.getDetailById(user, id));
            List<TransactionField> fields = transactionFieldService.getAllTransactionFields(user);
            List<TransactionMode> modes = transactionModeService.getAllTransactionModes(user);
            modelAndView.addObject(TRANSACTION_MODES, modes);
            modelAndView.addObject(TRANSACTION_FIELDS, fields);
        } catch (QueryFailedException e) {
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/mode")
    public ModelAndView transactionMode(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactionMode");
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        List<TransactionMode> modes = transactionModeService.getAllTransactionModes(user);
        modelAndView.addObject(TRANSACTION_MODES, modes);
        modelAndView.addObject(USERNAME, user.getUsername());
        return modelAndView;
    }

    @RequestMapping("/mode/add")
    public ModelAndView addTransactionMode(HttpServletRequest request, String mode){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transaction/mode");
        try {
            transactionModeService.addTransactionMode((DetailUser) request.getSession().getAttribute(SessionConstants.USER), new TransactionMode(null, mode));
        } catch (Exception e) {
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/mode/delete/{id}")
    public ModelAndView deleteTransactionMode(HttpServletRequest request, @PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transaction/mode");
        transactionModeService.deleteTransactionMode((DetailUser) request.getSession().getAttribute(SessionConstants.USER), id);
        return modelAndView;
    }

    @RequestMapping("/field")
    public ModelAndView transactionField(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transactionField");
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        List<TransactionField> fields = transactionFieldService.getAllTransactionFields(user);
        modelAndView.addObject(TRANSACTION_FIELDS, fields);
        modelAndView.addObject(USERNAME, user.getUsername());
        return modelAndView;
    }

    @RequestMapping("/field/add")
    public ModelAndView addTransactionField(HttpServletRequest request, String field){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transaction/field");
        try {
            transactionFieldService.addTransactionField((DetailUser) request.getSession().getAttribute(SessionConstants.USER), new TransactionField(null, field));
        } catch (Exception e) {
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/field/delete/{id}")
    public ModelAndView deleteTransactionField(HttpServletRequest request, @PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transaction/field");
        transactionFieldService.deleteTransactionField((DetailUser) request.getSession().getAttribute(SessionConstants.USER), id);
        return modelAndView;
    }

    @RequestMapping("/analysis")
    public ModelAndView analysis(HttpServletRequest request, AnalysisParameter analysisParameter){
        ModelAndView modelAndView = new ModelAndView();
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        try{
            String analysisName = analysisParameter.getAnalysisName() == null ? "field" : analysisParameter.getAnalysisName();
            String year = analysisParameter.getYear() == null ? DateUtil.getYearFromString(DateUtil.currentDate()) : analysisParameter.getYear();
            String month = analysisParameter.getMonth() == null ? "01" : analysisParameter.getMonth();
            String viewName = analysisService.viewName(analysisName);
            AnalysisResult result = analysisService.getAnalysisResult(user, analysisName, year, month);
            Object data = result.getData();
            modelAndView.setViewName(viewName);
            modelAndView.addObject(USERNAME, user.getUsername());
            modelAndView.addObject(ANALYSIS_DATA, data);
            modelAndView.addObject(ANALYSIS_PARAM, new AnalysisParameter(analysisName, year, month));
            modelAndView.addObject(LIST_ANALYSIS, Analysis.getAllAnalysis());
            modelAndView.addObject(ANALYSIS_MONTH, analysisService.getAnalysisMonth());
        } catch (AnalysisNotFoundException e){
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, e.getMessage());
        } catch (Exception e){
            modelAndView.addObject(JspConstants.ERROR_MESSAGE, new AnalyseFailedException().getMessage());
        }
        return modelAndView;
    }
}
