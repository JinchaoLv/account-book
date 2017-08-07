package com.lvjc.po;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/6/27.
 */
@Setter
@Getter
public class User extends BaseDomain {

    public enum Sex{
        MALE("男"), FEMALE("女"), ELSE("其它");

        private String description;

        private Sex(String desc){
            this.description = desc;
        }

        public String getDescription(){
            return this.description;
        }
    }

    public enum State{
        ONLINE("在线"), OFFLINE("离线");

        private String description;

        State(String desc){
            this.description = desc;
        }

        public String getDescription(){
            return this.description;
        }
    }

    protected String id;

    protected String username;

    protected String sex;

    protected int age;

    protected String phone;

    protected String email;

    protected String state;

    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;
        if(object instanceof User){
            User user = (User)object;
            return this.getId().equals(user.getId());
        }
        return false;
    }
}
