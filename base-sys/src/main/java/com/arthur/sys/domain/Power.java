package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class Power extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String name;

    private String depict;

    private Long pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}