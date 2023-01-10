package com.example.demo.controllers;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDao;
import com.example.demo.daos.RegionDao;
import com.example.demo.models.Division;
import com.example.demo.tools.DBConnection;

@Controller
@RequestMapping("division")
public class DivisionController {
    DivisionDao div = new DivisionDao(DBConnection.getConnection());
    RegionDao reg = new RegionDao(DBConnection.getConnection());

    // @ModelAttribute("divisionList")
    // public String getRegion(Model model) {
    // model.addAttribute("regName", div.getRegion());
    // return "division/form";
    // }

    @GetMapping()
    public String index(Model model) {
        // Object division = div.getAll();
        model.addAttribute("divisions", div.getAll());
        return "division/division-index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            // Object division = div.getById(id);
            model.addAttribute("regions", reg.getAllData());
            model.addAttribute("division", div.getById(id));
        } else {
            model.addAttribute("regions", reg.getAllData());
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
            return "redirect:/division";
        } else {
            return "division/form";
        }
    }

    // DELETE
    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // Division division = new Division();

        // id = division.setId(id);
        Boolean result = div.deleteData(id);
        if (result) {
            return "redirect:/division";
        } else {
            return "";
        }
    }
}
