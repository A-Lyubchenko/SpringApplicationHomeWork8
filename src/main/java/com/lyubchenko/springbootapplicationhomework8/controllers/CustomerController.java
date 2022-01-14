package com.lyubchenko.springbootapplicationhomework8.controllers;

import com.lyubchenko.springbootapplicationhomework8.domain.Customer;
import com.lyubchenko.springbootapplicationhomework8.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
@Slf4j
@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("customers", customerService.read());
        return "customer/read";
    }

    @GetMapping("{id}")
    public String get(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("customer", customerService.getEntityById(id));
        return "customer/get";

    }

    @GetMapping("new")
    public String getCreatePage(@ModelAttribute("customer") Customer customer) {
        return "customer/create";
    }

    @PostMapping()
    public String create(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "customer/create";

        customerService.create(customer);
        model.addAttribute("customer", customerService.getEntityById(customer.getId()));
        return "redirect:/customers";

    }

    @GetMapping("{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("customer", customerService.getEntityById(id));
        return "customer/update";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") UUID id, @Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "customer/update";

        Customer byId = customerService.getEntityById(id);
        byId.setName(customer.getName());
        customerService.update(byId);
        model.addAttribute("customer", customerService.getEntityById(byId.getId()));
        return "customer/get";

    }

    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        Customer customer = customerService.getEntityById(id);
        customerService.deleteEntity(customer);
        return "redirect:/customers";
    }
}
