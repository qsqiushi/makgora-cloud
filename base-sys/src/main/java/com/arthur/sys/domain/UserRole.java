package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;
import java.util.Date;

public class UserRole extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;

    private String relateType;

    private Date startDate;

    private Date endDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRelateType() {
        return relateType;
    }

    public void setRelateType(String relateType) {
        this.relateType = relateType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}