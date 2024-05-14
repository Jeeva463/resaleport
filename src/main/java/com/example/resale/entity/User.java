package com.example.resale.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.usertype.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.resale.audit.AuditwithBaseEntity;
import com.example.resale.enums.GenderType;
import com.example.resale.enums.Usertype;
import com.example.resale.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
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
		return null;
		
	}

	@Override
	public String getPassword() {
	
		return Password;
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
	
	@Column(name="Age")
	private String Age;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Gender")
	private GenderType Gender;
	
	@Column(name="Address")
	private String Address;
	
	@Column(name="DateOfBirth")
	private String DateOfBirth;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="mobile_No")
	private String mobileNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Role")
	private Usertype Role;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Status")
	private Status Status;

	@Column(name="user_Name")
	private String userName;
	
	@Column(name="Password")
	private String Password;
	
	@Column(name="conform_Password")
	private String conformPassword;
	
	


	



}
