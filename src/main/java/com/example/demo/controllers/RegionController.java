package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.RegionDao;
import com.example.demo.models.Region;
import com.example.demo.tools.DBConnection;

import io.micrometer.core.lang.Nullable;

@Controller
@RequestMapping("region")
public class RegionController {
    RegionDao reg = new RegionDao(DBConnection.getConnection());

    @GetMapping
    public String index(Model model) {
        // Object region = reg.getAllData();
        model.addAttribute("regions", reg.getAllData());
        return "region/region-index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(@PathVariable(required = false) Integer Id, Model model) {
        if (Id != null) {
            // get data by id for update
            model.addAttribute("region", reg.getById(Id));
        } else {
            // insert data for new data
            model.addAttribute("region", new Region());
        }
        return "region/form";

    }

    // Insert and Update Data
    @PostMapping("save")
    public String save(@Nullable Region region) {
        Boolean result;
        if (region.getId() != null) {
            result = reg.updateData(region);
        } else {
            result = reg.insertData(region);
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
        Boolean result = reg.deleteData(id);
        if (result) {
            return "redirect:/region";
        } else {
            return "";
        }
    }
}
