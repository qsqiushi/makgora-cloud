package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;
import java.util.Date;

public class LoginLog extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long userId;

    private Long userIp;

    private Date loginOnDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserIp() {
        return userIp;
    }

    public void setUserIp(Long userIp) {
        this.userIp = userIp;
    }

    public Date getLoginOnDate() {
        return loginOnDate;
    }

    public void setLoginOnDate(Date loginOnDate) {
        this.loginOnDate = loginOnDate;
    }
}