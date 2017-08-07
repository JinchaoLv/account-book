package com.lvjc.po;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/6/19.
 */
@Setter
@Getter
public class DetailUser extends User {

    private String password;

    private String signature;

    private byte[] icon;

    private String tableInfo;

/*    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;
        if(object instanceof DetailUser){
            DetailUser user = (DetailUser)object;
            return this.getId().equals(user.getId());
        }
        return false;
    }*/
}
