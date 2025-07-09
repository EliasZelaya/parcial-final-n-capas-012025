package com.uca.parcialfinalncapas.security;

import com.uca.parcialfinalncapas.entities.UserE;
import com.uca.parcialfinalncapas.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserE user = userRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority(user.getNombreRol());

        return new User(user.getNombre(), user.getPassword(), List.of(grantedAuthorities));
    }
}
