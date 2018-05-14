package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class LoginUser extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String loginName;

    private String password;

    private Long userId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}