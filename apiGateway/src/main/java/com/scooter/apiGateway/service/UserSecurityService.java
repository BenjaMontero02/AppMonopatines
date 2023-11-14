package com.scooter.apiGateway.service;

import com.scooter.apiGateway.DTO.UserDTO;
import com.scooter.apiGateway.DTO.UserRequestDTO;
import com.scooter.apiGateway.DTO.UserResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    private WebClient webClient;

    public UserSecurityService() {
        this.webClient = WebClient.create("http://192.168.121.66:8081");;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserResponseDTO userDTO = this.webClient
                .get()
                .uri("/api/users/login/" + username)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();

        String [] rolesUser = new String[userDTO.getRoles().size()-1];

        for(int i = 0; i < userDTO.getRoles().size()-1; i++){
            rolesUser[i] = userDTO.getRoles().get(i);
        }

        return User.builder()
                .username(userDTO.getMail())
                .password(userDTO.getPassword())
                .authorities(this.grantedAuthorities(rolesUser))
                .accountLocked(false)
                .disabled(false)
                .build();
    }


    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return authorities;
    }
}
