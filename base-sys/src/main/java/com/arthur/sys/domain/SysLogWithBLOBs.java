package com.arthur.sys.domain;

public class SysLogWithBLOBs extends SysLog {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private String content;

    private String uri;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}