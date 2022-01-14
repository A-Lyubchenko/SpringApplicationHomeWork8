package com.lyubchenko.springbootapplicationhomework8.controllers;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.EncodePassword;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.User;

import com.lyubchenko.springbootapplicationhomework8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("registration")
public class RegistrationController {
    private final String userClassName = User.class.getSimpleName().toLowerCase();
    @Autowired
    private UserService userService;
    @Autowired
    private EncodePassword encodePassword;

    @GetMapping("register")
    public String registerForm(@ModelAttribute("user") User user) {
        return "main/registration";
    }

    @PostMapping
    public String processRegistration(@Valid User user, BindingResult bindingResult) {
        if (!userService.isUniqueUserName(user)) {
            bindingResult.addError(new FieldError(userClassName, User.Fields.username,
                    "Username already in use, choose other."));
        }
        if (!userService.isUniqueEmail(user)) {
            bindingResult.addError(new FieldError(userClassName, User.Fields.email,
                    "Email already in use, choose other."));
        }
        if (bindingResult.hasErrors())
            return "main/registration";


        String enPassword = encodePassword.encodePassword(user.getPassword());
        user.setPassword(enPassword);
        userService.create(user);
        return "redirect:/login";
    }

    @GetMapping("index")
    public String mainPage() {
        return "main/index";
    }

}


