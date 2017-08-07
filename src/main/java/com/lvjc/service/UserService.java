package com.lvjc.service;

import com.lvjc.config.IdGeneratorConfig;
import com.lvjc.cons.JdbcConstants;
import com.lvjc.cons.SessionConstants;
import com.lvjc.exception.transaction.IncorrectDatePatternException;
import com.lvjc.exception.user.*;
import com.lvjc.po.TableInfo;
import com.lvjc.po.DetailUser;
import com.lvjc.po.Transaction;
import com.lvjc.po.User;
import com.lvjc.support.incrementer.IdGenerator;
import com.lvjc.support.incrementer.impl.ClassIdGenerator;
import com.lvjc.support.incrementer.impl.SequenceInfo;
import com.lvjc.support.util.DateUtil;
import com.lvjc.support.util.IdGenerationStrategyFactory;
import com.lvjc.support.util.TableNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * Created by lvjc on 2017/6/19.
 */
@Service
public class UserService extends BaseService{

    @Autowired
    private AdminService adminService;

    @Autowired
    private IdGeneratorConfig idGeneratorConfig;

    public void register(DetailUser user) throws UserExistException, RegisterFailedException {
        DetailUser u = userDao.getDetailUserByName(user.getUsername());
        if(u != null){
            throw new UserExistException();
        } else{
            try{
                user.setId(adminService.nextStringUserId());
                user.setTableInfo(TableNameUtil.getUserTableInfoName(user));
                user.setState(User.State.OFFLINE.getDescription());
                userDao.addEntity(null, user);
                this.afterRegister(user);
            } catch (Exception e){
                throw new RegisterFailedException();
            }
        }
    }

    public DetailUser login(HttpServletRequest request, String username, String password) throws UserNotExistException, PasswordWrongException {
        boolean userAlreadyLogan = false;
        DetailUser onlineUser = adminService.getOnlineUserByUserName(username);
        if(onlineUser != null)
                userAlreadyLogan = true;
        DetailUser user = userDao.getDetailUserByName(username);
        if(user == null)
            throw new UserNotExistException();
        if(!user.getPassword().equals(password))
            throw new PasswordWrongException();
        if(!userAlreadyLogan){
            afterLogin(user);
        }
        request.getSession().setAttribute(SessionConstants.USER, user);
        return user;
    }

    public void logout(DetailUser user){
        if(user != null){
            //数据更新到数据库
            adminService.updateSequenceInfoToDatabase(getIdGenerator(user));
            //更改服务器状态
            user.setState(User.State.OFFLINE.getDescription());
            userDao.updateStateById(user.getId(), User.State.OFFLINE.getDescription());
            adminService.removeOfflineUser(user);
            this.removeUserIdGenerator(user);
        }
    }

    private void afterLogin(DetailUser user){
        //更改用户在线状态
        user.setState(User.State.ONLINE.getDescription());
        userDao.updateStateById(user.getId(), User.State.ONLINE.getDescription());
        //添加到在线用户列表
        adminService.addOnlineUser(user);
        //用户独立的表的id产生器
        ClassIdGenerator idGenerator = createUserIdGenerator(user);
        addUserIdGenerator(user, idGenerator);
    }

    private ClassIdGenerator createUserIdGenerator(DetailUser user){
        ClassIdGenerator idGenerator = idGeneratorConfig.userIdGenerator();
        idGenerator.setSequenceTableName(TableNameUtil.getIdGeneratorTableNameOfUser(user));
        idGenerator.init();
        return idGenerator;
    }

    //新用户注册成功的后续处理
    private void afterRegister(DetailUser user) throws IncorrectDatePatternException {
        String transactionTableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromString(DateUtil.currentDate()));
        String transactionModeTableName = TableNameUtil.getUserTransactionModeTableName(user);
        String transactionFieldTableName = TableNameUtil.getUserTransactionFieldTableName(user);
        String idGeneratorTableName = TableNameUtil.getIdGeneratorTableNameOfUser(user);
        this.initIdGeneratorTable(idGeneratorTableName)
                .initTransactionFieldTable(transactionFieldTableName, idGeneratorTableName)
                .initTransactionModeTable(transactionModeTableName, idGeneratorTableName)
                .initTransactionTable(transactionTableName, idGeneratorTableName)
                .initTableInfoTable(user.getTableInfo(), transactionTableName, transactionModeTableName, transactionFieldTableName, idGeneratorTableName);
    }

    private void initTableInfoTable(String tableInfoTableName, String transactionTableName, String transactionModeTableName,
                                    String transactionFieldTableName, String idGeneratorTableName){
        tableInfoDao.createTable(tableInfoTableName);
        tableInfoDao.addEntity(tableInfoTableName, new TableInfo(transactionTableName));
        tableInfoDao.addEntity(tableInfoTableName, new TableInfo(transactionModeTableName));
        tableInfoDao.addEntity(tableInfoTableName, new TableInfo(transactionFieldTableName));
        tableInfoDao.addEntity(tableInfoTableName, new TableInfo(idGeneratorTableName));
    }

    private UserService initTransactionTable(String tableName, String idGeneratorTableName){
        transactionDao.createTable(tableName);
        sequenceInfoDao.addEntity(idGeneratorTableName, new SequenceInfo(JdbcConstants.KEY_TRANSACTION, 0));
        return this;
    }

    private UserService initTransactionModeTable(String tableName, String idGeneratorTableName){
        transactionModeDao.createTable(tableName);
        sequenceInfoDao.addEntity(idGeneratorTableName, new SequenceInfo(JdbcConstants.KEY_TRANSACTION_MODE, 0));
        return this;
    }

    private UserService initTransactionFieldTable(String tableName, String idGeneratorTableName){
        transactionFieldDao.createTable(tableName);
        sequenceInfoDao.addEntity(idGeneratorTableName, new SequenceInfo(JdbcConstants.KEY_TRANSACTION_FIELD, 0));
        return this;
    }

    private UserService initIdGeneratorTable(String tableName){
        sequenceInfoDao.createTable(tableName);
        return this;
    }

}
