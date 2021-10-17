package com.PI.ProjetoIntegrador.service;

import com.PI.ProjetoIntegrador.model.User;
import com.PI.ProjetoIntegrador.model.UserLogin;
import com.PI.ProjetoIntegrador.repository.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserService {

        @Autowired
        private UserRepository repository;

        public ResponseEntity<User> userRegistration(User user){
            if (repository.findByEmail(user.getEmail()).isBlank()){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String  passwordEncoder = encoder.encode(user.getPassword());
                user.setPassword(passwordEncoder);
                return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        public Optional<UserLogin> signIn(Optional<UserLogin> userSignIn){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Optional<User> user = repository.findByUsername(userSignIn.get().getUsername());

            if (user.isPresent()){
                if (encoder.matches(userSignIn.get().getPassword(), user.get().getPassword())){
                    String auth = userSignIn.get().getUsername() + ":" + userSignIn.get().getPassword();
                    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

                    String authHeader = "Basic " + new String(encodedAuth);
                    userSignIn.get().setToken(authHeader);
                    userSignIn.get().setUsername(user.get().getUsername());
                    userSignIn.get().setEmail(user.get().getEmail());

                    return userSignIn;
                }
            }
            return null;
        }
}
