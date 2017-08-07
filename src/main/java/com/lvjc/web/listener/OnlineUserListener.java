package com.lvjc.web.listener;

import com.lvjc.cons.SessionConstants;
import com.lvjc.po.DetailUser;
import com.lvjc.service.AdminService;
import com.lvjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.*;

/**
 * Created by lvjc on 2017/7/14.
 */
public class OnlineUserListener extends BaseListener
        implements HttpSessionListener, HttpSessionAttributeListener {

    private HttpSession session;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        this.session = httpSessionEvent.getSession();
        System.out.println("session created.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        DetailUser user = (DetailUser) session.getAttribute(SessionConstants.USER);
        getUserService().logout(user);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        /*//获取被替换的属性的属性值
        Object object = httpSessionBindingEvent.getValue();
        //如果同一次会话中如果再次登录，替换Session中原User属性
        if(object instanceof DetailUser){
            //检测与已登录的用户是否相同，不同则使原用户下线（触发一系列下线相关设置）
            DetailUser newUser = (DetailUser) this.session.getAttribute(SessionConstants.USER);
            if(!newUser.equals(object)){
                getUserService().logout((DetailUser) object);
            }
        }*/
        //如果原属性值与新的属性值判断equals相等，不会进入此方法
        Object object = httpSessionBindingEvent.getValue();
        getUserService().logout((DetailUser) object);
    }
}
