package com.lyubchenko.springbootapplicationhomework8.controllers;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.Authority;
import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.EncodePassword;
import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.User;
import com.lyubchenko.springbootapplicationhomework8.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("users")
public class UserController {

    private final String userClassName = User.class.getSimpleName().toLowerCase();
    @Autowired
    private UserService userService;
    @Autowired
    private EncodePassword encodePassword;

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("users", userService.read());
        return "user/read";
    }

    @GetMapping("{id}")
    public String get(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.getEntityById(id));
        return "user/get";

    }

    @GetMapping("new")
    public String getCreatePage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("authoritiesGetCreatePage", Authority.Roles.values());
        return "user/create";
    }

    @PostMapping()
    public String create(@Valid User user, BindingResult bindingResult, Model model,
                         @ModelAttribute("userRole") Authority.Roles name) {
        if (userService.userExistByName(user.getUsername())) {
            bindingResult.addError(new FieldError(userClassName, User.Fields.username,
                    "Username already in use, choose other."));
            model.addAttribute("authoritiesGetCreatePage", Authority.Roles.values());
            return "user/create";
        }
        if (userService.isUniqueEmail(user)) {
            bindingResult.addError(new FieldError(userClassName, User.Fields.email,
                    "Email already in use, choose other."));
            model.addAttribute("authoritiesGetCreatePage", Authority.Roles.values());
            return "user/create";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("authoritiesGetCreatePage", Authority.Roles.values());
            return "user/create";
        }

        user.setAuthorities(List.of(new Authority(name)));
        user.setPassword(encodePassword.encodePassword(user.getPassword()));
        userService.create(user);
        return "redirect:/users";

    }

    @GetMapping("{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.getEntityById(id));
        model.addAttribute("authoritiesPages", Authority.Roles.values());
        return "user/update";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") UUID id, @Valid User user, BindingResult bindingResult,
                         @ModelAttribute("userRole") Authority.Roles name, Model model) {

        if (!userService.isUniqueUserName(user)) {
            bindingResult.addError(new FieldError(userClassName, User.Fields.username,
                    "Username already in use, choose other."));
            model.addAttribute("authoritiesPages", Authority.Roles.values());

            return "user/update";
        }
        if (!userService.isUniqueEmail(user)) {
            bindingResult.addError(new FieldError(userClassName, User.Fields.email,
                    "Email already in use, choose other."));

            model.addAttribute("authoritiesPages", Authority.Roles.values());
            return "user/update";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("authoritiesPages", Authority.Roles.values());
            return "user/update";
        }

        user.setAuthorities(List.of(new Authority(name)));
        userService.update(user);
        return "user/get";

    }

    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        User user = userService.getEntityById(id);
        userService.deleteEntity(user);
        return "redirect:/users";
    }

}
