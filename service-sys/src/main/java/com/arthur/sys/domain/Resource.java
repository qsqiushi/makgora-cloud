package com.arthur.sys.domain;

import com.arthur.base.domain.BasePo;

public class Resource extends BasePo {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String name;

    private String uri;

    private Long pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}