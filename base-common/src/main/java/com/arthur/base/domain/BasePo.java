package com.arthur.base.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author qiushiqiushi
 *
 */
public class BasePo implements Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private Long id;
  // 创建人
  private Long createdBy;
  // 创建时间
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date createdDate;
  // 修改人
  private Long modifiedBy;
  // 修改时间
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date modifiedDate;
  // 逻辑删除状态
  private String isDeleted;
  // 备注
  private String remark;
  // 状态
  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(String isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Long getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(Long modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  

}
