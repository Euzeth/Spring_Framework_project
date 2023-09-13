package com.test.app.Config.auth;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.app.Domain.Dto.MemberDto;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("CustomLoginSuccessHandler's onAuthenticationSuccess! ");
		Collection<? extends GrantedAuthority> collection =   authentication.getAuthorities();
		
		
		collection.forEach((role)->{	
			String role_str = role.getAuthority();
			request.getSession().setAttribute("role", role_str);
			String MemberId = authentication.getName();
			request.getSession().setAttribute("name", MemberId);
			try {	
				System.out.println("role : " + role.getAuthority());
				if(role_str.equals("ROLE_USER")) {
					
					System.out.println("USER 페이지로 이동!");    
					response.sendRedirect(request.getContextPath()+"/indexlog");
					return ;
				}else if(role_str.equals("ROLE_MEMBER")){
					System.out.println("MEMBER 페이지로 이동!");
					response.sendRedirect(request.getContextPath()+"/indexlog");
					return ;
				}
				else if(role_str.equals("ROLE_ADMIN")) {
					System.out.println("ADMIN 페이지로 이동!");	
					response.sendRedirect(request.getContextPath()+"/admin");
					return ;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		} );

	}

}
