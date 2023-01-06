package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDao;
import com.example.demo.models.Division;
import com.example.demo.tools.DBConnection;

import io.micrometer.core.lang.Nullable;

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

    @GetMapping(value = { "form", "form/{id}" })
    public String form(@PathVariable(required = false) Integer Id, Model model) {
        if (Id != null) {
            // get data by id for update
            div.getById(Id);
            Division division = new Division();
            division.setId(1);
            division.setName("Helper");
            model.addAttribute("division", div.getById(Id));
        } else {
            // insert data for new data
            Division division = new Division();
            division.setId(5);
            division.setName("Chef");
            model.addAttribute("division", new Division());
        }
        return "division/form";

    }

    @PostMapping("save")
    public String save(@Nullable Division division) {
        Boolean result;
        if (division.getId() != null) {
            result = div.updateData(division);
        } else {
            result = div.insertData(division);
        }

        if (result) {
            return "redirect:/region";
        } else {
            return "region/form";
        }
    }

    // DELETE
    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Boolean result = div.deleteData(1);
        if (result) {
            return "redirect:/region";
        } else {
            return "";
        }
    }
}
