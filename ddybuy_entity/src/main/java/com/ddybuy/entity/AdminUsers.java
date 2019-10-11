package com.ddybuy.entity;

import java.io.Serializable;
import java.util.Date;

public class AdminUsers implements Serializable {
    private Long id;

    private String username;

    private String pwd;

    private String isroot;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getIsroot() {
        return isroot;
    }

    public void setIsroot(String isroot) {
        this.isroot = isroot == null ? null : isroot.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}