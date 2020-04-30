package com.zhang.openApi.common.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *  ① 定义 user 对象 这里等价于 client 对象
 */
public class JwtUserDetails implements UserDetails, Serializable {
    private String clientId;
    private String clientName;
    private String clientSecret;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;
    private Date lastPasswordResetDate;

    public JwtUserDetails() { }

    public JwtUserDetails(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public JwtUserDetails(String clientId, String clientName, Collection<? extends GrantedAuthority> authorities, List<String> roles) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.authorities = authorities;
        this.roles = roles;
    }

    public JwtUserDetails(String clientId, String clientName, Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clientSecret;
    }

    @Override
    public String getUsername() {
        return clientName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
