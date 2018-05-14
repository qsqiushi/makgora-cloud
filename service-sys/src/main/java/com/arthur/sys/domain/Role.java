package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class Role extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private Integer sequence;

    private Long pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}