package com.example.resale.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.resale.audit.AuditwithBaseEntity;
import com.example.resale.enums.Usertype;
import com.example.resale.enums.GenderType;
import com.example.resale.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resale")
public class User extends AuditwithBaseEntity implements UserDetails, Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
		
	}

	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
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
		
		return true;
	}
	
	@Column(name="age")
	private String age;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private GenderType gender;
	
	
	@Column(name="date_Birth")
	private String dateOfBirth;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_No")
	private String mobileNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Usertype role;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;

	@Column(name="user_Name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="conform_Password")
	private String conformPassword;
	
	


	



}
