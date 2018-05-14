package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class Constant extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}