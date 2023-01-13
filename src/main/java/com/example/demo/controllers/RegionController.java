package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Region;
import com.example.demo.services.DivisionService;
import com.example.demo.services.RegionService;

@Controller
@RequestMapping("region")
public class RegionController {
    // RegionDao reg = new RegionDao(DBConnection.getConnection());
    @Autowired
    private RegionService regionService;

    @GetMapping
    public String index(Model model) {
        // Object region = reg.getAllData();
        model.addAttribute("regions", regionService.getAll());
        return "region/region-index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            // get data by id for update
            model.addAttribute("region", regionService.getById(id));
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
            result = regionService.save(region);
        } else {
            result = regionService.save(region);
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
        Boolean result = regionService.delete(id);
        if (result) {
            return "redirect:/region";
        } else {
            return "";
        }
    }
}
