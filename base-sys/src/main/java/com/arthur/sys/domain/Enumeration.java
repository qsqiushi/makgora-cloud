package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class Enumeration extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String code;

    private String name;

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
}