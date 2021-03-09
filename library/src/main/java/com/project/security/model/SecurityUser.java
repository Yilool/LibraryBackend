package com.project.security.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.model.entity.LibraryUser;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class SecurityUser implements UserDetails{
	private static final long serialVersionUID = 2046866248113544418L;
	
	private static final int MAX_AUTH_ATTEMPTS = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<SecurityUserRole> roles;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "libraryuser_id", referencedColumnName = "id", nullable = false)
	private LibraryUser libraryUser;
	
	@CreatedDate
	private LocalDateTime createTime;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	private LocalDateTime deleteTime;
	
	private LocalDateTime lastPasswordChange;
	
	private boolean locked;
	
	private boolean enabled;
	
	private Integer authenticationAttempts;
	
	private LocalDateTime passwordPolicyExpDate;
	
	
	public SecurityUser() {
		super();
		this.roles = new HashSet<>();
		this.roles.add(SecurityUserRole.USER);
		this.libraryUser = new LibraryUser();
		this.createTime = LocalDateTime.now();
		this.updateTime = null;
		this.deleteTime = null;
		this.lastPasswordChange = LocalDateTime.now();;
		this.locked = Boolean.FALSE;
		this.enabled = Boolean.TRUE;
		this.authenticationAttempts = MAX_AUTH_ATTEMPTS;
		this.passwordPolicyExpDate = LocalDateTime.now().plusYears(1);
	}

	public SecurityUser(String username, String password, String name, String surname, String dni, String birthdate, String number) {
		super();
		this.username = username;
		this.password = password;
		this.roles = new HashSet<>();
		this.roles.add(SecurityUserRole.USER);
		this.libraryUser = new LibraryUser(name, surname, dni, birthdate, number, this);
		this.createTime = LocalDateTime.now();
		this.updateTime = null;
		this.deleteTime = null;
		this.lastPasswordChange = LocalDateTime.now();
		this.locked = Boolean.FALSE;
		this.enabled = Boolean.TRUE;
		this.authenticationAttempts = MAX_AUTH_ATTEMPTS;
		this.passwordPolicyExpDate = LocalDateTime.now().plusYears(1);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_"+ur.name())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<SecurityUserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SecurityUserRole> roles) {
		this.roles = roles;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}

	public LocalDateTime getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(LocalDateTime lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Integer getAuthenticationAttempts() {
		return authenticationAttempts;
	}

	public void setAuthenticationAttempts(Integer authenticationAttempts) {
		this.authenticationAttempts = authenticationAttempts;
	}

	public LocalDateTime getPasswordPolicyExpDate() {
		return passwordPolicyExpDate;
	}

	public void setPasswordPolicyExpDate(LocalDateTime passwordPolicyExpDate) {
		this.passwordPolicyExpDate = passwordPolicyExpDate;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LibraryUser getLibraryUser() {
		return libraryUser;
	}

	public void setLibraryUser(LibraryUser libraryUser) {
		this.libraryUser = libraryUser;
	}

	
}
