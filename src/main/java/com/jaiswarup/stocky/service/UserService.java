package com.jaiswarup.stocky.service;

import com.jaiswarup.stocky.model.MyUser;
import com.jaiswarup.stocky.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public MyUser registerUser(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
//        myUser.setRole("USER");
        if  (userRepository.findByUsername(myUser.getUsername()) != null)
            throw new RuntimeException("User already exists");
//        try {
        return userRepository.save(myUser);
//        } catch (Exception e) {
//            throw new RuntimeException("User already exists");
//        }
    }

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    public MyUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String loginUser(MyUser myUser) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myUser.getUsername(), myUser.getPassword()));
        return jwtService.generateToken(myUser.getUsername());
    }
}
