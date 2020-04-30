package com.zhang.openApi.api.model;

import java.io.Serializable;
import java.util.Date;

public class PlatClientToken implements Serializable {
    private String clientId;

    /**
     * 刷新的token
     *
     * @mbggenerated
     */
    private String refreshToken;

    private Date createDts;

    private Date updateDts;

    /**
     * 当前有效的token
     *
     * @mbggenerated
     */
    private String token;

    private static final long serialVersionUID = 1L;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clientId=").append(clientId);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", createDts=").append(createDts);
        sb.append(", updateDts=").append(updateDts);
        sb.append(", token=").append(token);
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
        PlatClientToken other = (PlatClientToken) that;
        return (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getRefreshToken() == null ? other.getRefreshToken() == null : this.getRefreshToken().equals(other.getRefreshToken()))
            && (this.getCreateDts() == null ? other.getCreateDts() == null : this.getCreateDts().equals(other.getCreateDts()))
            && (this.getUpdateDts() == null ? other.getUpdateDts() == null : this.getUpdateDts().equals(other.getUpdateDts()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getRefreshToken() == null) ? 0 : getRefreshToken().hashCode());
        result = prime * result + ((getCreateDts() == null) ? 0 : getCreateDts().hashCode());
        result = prime * result + ((getUpdateDts() == null) ? 0 : getUpdateDts().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        return result;
    }
}