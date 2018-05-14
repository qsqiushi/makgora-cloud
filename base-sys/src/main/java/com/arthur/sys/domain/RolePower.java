package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class RolePower extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long powerId;

    private Long roleId;

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}