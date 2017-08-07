package com.lvjc.service;

import com.lvjc.config.IdGeneratorConfig;
import com.lvjc.cons.JdbcConstants;
import com.lvjc.cons.SessionConstants;
import com.lvjc.po.Admin;
import com.lvjc.po.DetailUser;
import com.lvjc.po.User;
import com.lvjc.support.incrementer.IdGenerator;
import com.lvjc.support.incrementer.PersistentObjectInfo;
import com.lvjc.support.incrementer.impl.ClassIdGenerator;
import com.lvjc.support.incrementer.impl.SequenceInfo;
import com.lvjc.support.util.TableNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvjc on 2017/6/27.
 */
@Service
public class AdminService extends BaseService{

    private ClassIdGenerator adminIdGenerator;
    private Map<String, DetailUser> onlineUsers = new HashMap<>();

    @Autowired
    private IdGeneratorConfig idGeneratorConfig;


    public boolean adminLogin(HttpServletRequest request, String username, String password){
        Admin admin = adminDao.getAdmin(username);
        if(admin != null && password.equals(admin.getPassword())){
            request.getSession().setAttribute(SessionConstants.ADMIN, admin);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers(){
        List<User> users = userDao.getAllUsers();
        return users;
    }

    public void deleteUserById(String id){
       deleteAllTablesOfUser(id);
       userDao.deleteEntityById(null, id);
    }

    public String nextStringUserId() throws Exception {
        return adminIdGenerator.nextStringId(new PersistentObjectInfo<>(DetailUser.class, null));
    }

    public void addOnlineUser(DetailUser user){
        this.onlineUsers.put(user.getUsername(), user);
    }

    public void removeOfflineUser(DetailUser user){
        this.onlineUsers.remove(user.getUsername());
    }

    public DetailUser getOnlineUserByUserName(String username){
        return this.onlineUsers.get(username);
    }

    public List<User> getOnlineUserList(){
        List<User> list = new ArrayList<>();
        for(String username : this.onlineUsers.keySet()){
            list.add(this.onlineUsers.get(username));
        }
        return list;
    }

    @PostConstruct
    public void initAdminIdGenerator(){
        this.adminIdGenerator = idGeneratorConfig.adminIdGenerator();

        adminIdGenerator.init();
    }

    public boolean updataDatabase(){
        try{
            for(DetailUser user : this.onlineUsers.values()){
                //更新在线用户序列表序列值
                this.updateSequenceInfoToDatabase(getIdGenerator(user));
                //用户下线
                userDao.updateStateById(user.getId(), User.State.OFFLINE.getDescription());
            }
            //更新t_user表的id产生器到数据库
            this.updateSequenceInfoToDatabase(adminIdGenerator);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private void deleteAllTablesOfUser(String id){
        DetailUser user = userDao.getEntityById(null, id);
        String tableInfo = user.getTableInfo();
        List<String> tableNames = TableNameUtil.getAllTableNames(user, tableInfoDao);
        for(String tableName : tableNames){
            dropTableDao.dropTable(tableName);
        }
        dropTableDao.dropTable(tableInfo);
    }

    //更新序列表
    public void updateSequenceInfoToDatabase(ClassIdGenerator idGenerator){
        String sequenceTableName = idGenerator.getSequenceTableName();
        List<SequenceInfo> sequenceInfos = idGenerator.getSequenceInfoList();
        for(SequenceInfo info : sequenceInfos){
            sequenceInfoDao.updateEntity(sequenceTableName, info);
        }
    }
}
