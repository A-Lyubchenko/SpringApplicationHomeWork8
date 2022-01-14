package com.lyubchenko.springbootapplicationhomework8.controllers;

import com.lyubchenko.springbootapplicationhomework8.domain.Product;
import com.lyubchenko.springbootapplicationhomework8.service.CustomerService;
import com.lyubchenko.springbootapplicationhomework8.service.ProductService;
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
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("products", productService.read());
        return "product/read";
    }

    @GetMapping("{id}")
    public String get(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("product", productService.getEntityById(id));
        return "product/get";

    }

    @GetMapping("new")
    public String getCreatePage(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("customers", customerService.read());
        return "product/create";
    }

    @PostMapping()
    public String create(@Valid Product product, BindingResult bindingResult, Model model,
                         @ModelAttribute("customer") UUID id, BindingResult bindingResultForId) {
        if (bindingResult.hasErrors() || bindingResultForId.hasErrors()) {
            model.addAttribute("customers", customerService.read());
            return "product/create";
        }

        product.setCustomer(customerService.getEntityById(id));


        productService.create(product);
        model.addAttribute("product", productService.getEntityById(product.getId()));
        return "redirect:/products";

    }

    @GetMapping("{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("product", productService.getEntityById(id));
        model.addAttribute("customers", customerService.read());
        return "product/update";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") UUID id, @Valid Product product, BindingResult bindingResult,
                         Model model, @ModelAttribute("customer") UUID uuid, BindingResult bindingResultForId) {
        if (bindingResult.hasErrors() || bindingResultForId.hasErrors()) {
            model.addAttribute("customers", customerService.read());
            return "product/update";
        }

        Product byId = productService.getEntityById(id);
        byId.setName(product.getName());
        byId.setPrice(product.getPrice());
        byId.setCustomer(customerService.getEntityById(uuid));
        productService.update(byId);
        model.addAttribute("product", productService.getEntityById(byId.getId()));
        return "product/get";

    }

    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        Product product = productService.getEntityById(id);
        productService.deleteEntity(product);
        return "redirect:/products";
    }
}
