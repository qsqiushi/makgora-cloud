package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class PowerResource extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long resourceId;

    private Long powerId;

    private String depict;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}