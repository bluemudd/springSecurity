package com.cos.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.model.User;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행.
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어줍니다.
//Security ContextHolder에 세션정보 저장
//세션에 들어갈수 있는 오브젝트가 정해져있음 ->Authentication 타입객체
//Authentication 안에 User정보가 있어야 됨.
//User오브젝트타입 => UserDetails 타입 객체

//Security Session => Authentication => UserDetails타입어야함.
public class PrincipalDetails implements UserDetails{
	
	private User user;// 콤포지션
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//해당 User의 권한을 리턴하는곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	//너의 계정이 잠겼니?
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	//너의 비밀번호가 너무 오래되지는 않았니?
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		// 우리 사이트에서 1년동안 회원이 로그인을 안하면? 휴면계정으로 하기로함.
		//현재시간-로그인 시간 => 1년을 초과하면 return false;
		return true;
	}

}
