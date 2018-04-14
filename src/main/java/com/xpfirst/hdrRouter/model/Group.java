package com.xpfirst.hdrRouter.model;

import java.io.Serializable;

/**
 * Created by mac on 2018/4/9.
 */
public class Group implements Serializable {
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
