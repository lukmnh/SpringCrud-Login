package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;

import com.example.demo.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model) {
        // Object user = div.getAll();
        model.addAttribute("user", userService.getAll());
        return "user/user-index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            // Object user = div.getById(id);
            model.addAttribute("user", userService.getById(id));
        } else {
            model.addAttribute("user", new User());
        }
        return "user/form";
    }

    // @PostMapping("save")
    // public String save(@Nullable User user) {
    // Boolean result;
    // if (user.getId() != null) {
    // result = userService.save(user);
    // } else {
    // result = userService.save(user);
    // }

    // if (result) {
    // return "redirect:/user";
    // } else {
    // return "user/form";
    // }
    // }

    // DELETE
    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // user user = new user();

        // id = user.setId(id);
        Boolean result = userService.delete(id);
        if (result) {
            return "redirect:/user";
        } else {
            return "";
        }
    }
}
