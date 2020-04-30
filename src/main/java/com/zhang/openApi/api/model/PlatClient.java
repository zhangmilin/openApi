package com.zhang.openApi.api.model;

import java.io.Serializable;
import java.util.Date;

public class PlatClient implements Serializable {
    private String clientId;

    private String clientSecret;

    /**
     * 客户端名
     *
     * @mbggenerated
     */
    private String clientName;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 状态 0有效/1失效
     *
     * @mbggenerated
     */
    private Integer status;

    private Date createDts;

    private Date updateDts;

    private static final long serialVersionUID = 1L;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDts() {
        return createDts;
    }

    public void setCreateDts(Date createDts) {
        this.createDts = createDts;
    }

    public Date getUpdateDts() {
        return updateDts;
    }

    public void setUpdateDts(Date updateDts) {
        this.updateDts = updateDts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clientId=").append(clientId);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", clientName=").append(clientName);
        sb.append(", remarks=").append(remarks);
        sb.append(", status=").append(status);
        sb.append(", createDts=").append(createDts);
        sb.append(", updateDts=").append(updateDts);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PlatClient other = (PlatClient) that;
        return (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getClientSecret() == null ? other.getClientSecret() == null : this.getClientSecret().equals(other.getClientSecret()))
            && (this.getClientName() == null ? other.getClientName() == null : this.getClientName().equals(other.getClientName()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateDts() == null ? other.getCreateDts() == null : this.getCreateDts().equals(other.getCreateDts()))
            && (this.getUpdateDts() == null ? other.getUpdateDts() == null : this.getUpdateDts().equals(other.getUpdateDts()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getClientSecret() == null) ? 0 : getClientSecret().hashCode());
        result = prime * result + ((getClientName() == null) ? 0 : getClientName().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateDts() == null) ? 0 : getCreateDts().hashCode());
        result = prime * result + ((getUpdateDts() == null) ? 0 : getUpdateDts().hashCode());
        return result;
    }
}