package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class EnumValue extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long enumId;

    private String code;

    private String name;

    private Integer sequence;

    private Long pid;

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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