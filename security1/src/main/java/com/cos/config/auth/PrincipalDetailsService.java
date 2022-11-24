package com.cos.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.model.User;
import com.cos.repository.UserRepository;

//시큐리티설정에서 loginProcessingUrl("/login");
//login 요청이 오면 자동으로 UserDetailsService타입으로 IOC 되어 있는 loadUserByUsername 함수가 실행.
@Service
public class PrincipalDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	//시큐리티 session(내부Authentication(내부UserDetails))
	@Override// 이름을 html에서 username으로 잘 받아올것! 틀리면 매칭안됨.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username:"+username);
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
