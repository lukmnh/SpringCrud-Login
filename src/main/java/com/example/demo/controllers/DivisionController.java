package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDao;
import com.example.demo.models.Division;
import com.example.demo.tools.DBConnection;

@Controller
@RequestMapping("division")
public class DivisionController {
    DivisionDao div = new DivisionDao(DBConnection.getConnection());

    @GetMapping()
    public String index(Model model) {
        // Object division = div.getAll();
        model.addAttribute("divisions", div.getAll());
        return "division/division-index";
    }

    @GetMapping(value = { "form", "form/{divisionId}" })
    public String form(@PathVariable(required = false) Integer divisionId, Model model) {
        if (divisionId != null) {
            // get data by id for update
            model.addAttribute("division", div.getById(divisionId));
        } else {
            // insert data for new data
            model.addAttribute("division", new Division());
        }
        return "division/form";

    }
}
