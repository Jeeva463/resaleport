package com.example.resale.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.usertype.UserType;
import org.springframework.security.core.GrantedAuthority;
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
	
	@Column(name="Name")
	private String name;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
//	@Column(name="Age")
//	private String Age;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="Gender")
//	private GenderType Gender;
//	
//	@Column(name="Address")
//	private String Address;
//	
//	@Column(name="DOB")
//	private String DOB;
//	
//	@Column(name="Email")
//	private String Email;
//	
//	@Column(name="mobile_No")
//	private String mobileNo;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="Role")
//	private Usertype Role;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="Status")
//	private Status Status;
//
//	@Column(name="user_Name")
//	private String userName;
//	
//	@Column(name="Password")
//	private String Password;
//	
//	@Column(name="conform_Password")
//	private String conformPassword;
//	
//	


	



}
