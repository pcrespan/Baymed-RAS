package com.ras.baymax.controllers;

import com.ras.baymax.entities.DoctorRegister;
import com.ras.baymax.entities.LoginParameters;
import com.ras.baymax.entities.NurseRegister;
import com.ras.baymax.entities.User;
import com.ras.baymax.services.DoctorService;
import com.ras.baymax.services.NurseService;
import com.ras.baymax.services.PhoneService;
import com.ras.baymax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin()
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private NurseService nurseService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> save(@RequestBody User user) {
        User savedUser = userService.save(user);
        savedUser.getPhone().setUser(savedUser);
        phoneService.save(savedUser.getPhone());
        return ResponseEntity.status(201).body("User created");
    }

    @PostMapping(value = "/register/doctor")
    public ResponseEntity<String> saveDoctor(@RequestBody DoctorRegister doctorRegister) {
        doctorService.saveDoctorRegister(doctorRegister);
        return ResponseEntity.status(201).body("User created");
    }

    @PostMapping(value = "/register/nurse")
    public ResponseEntity<String> saveNurse(@RequestBody NurseRegister nurseRegister) {
        nurseService.saveNurseRegister(nurseRegister);
        return ResponseEntity.status(201).body("User created");
    }

    @GetMapping(value = "/login")
    public void login(Model model, LoginParameters loginParameters) {
        model.addAttribute("loginParameters", loginParameters);
    }

    @GetMapping(value = "login-success")
    public ResponseEntity<List<GrantedAuthority>> loginSuccessful() {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return ResponseEntity.ok().body(authorities);
    }
}
