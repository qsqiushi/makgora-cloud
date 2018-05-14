package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class SysLog extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long userId;

    private String type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}