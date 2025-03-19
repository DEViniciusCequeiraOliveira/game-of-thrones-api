package com.vinicius.gameofthrones.Models;


import com.vinicius.gameofthrones.dto.LoginRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "UserData")
public class UserModel implements UserDetails {
     @Id
     private String _id;
     private String email;
     private String passWord;
     private List<UserRole> role;

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of();
     }

     @Override
     public String getPassword() {
          return "";
     }

     @Override
     public String getUsername() {
          return "";
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

     public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
          return passwordEncoder.matches(loginRequest.passWord(),this.passWord);
     }
}
