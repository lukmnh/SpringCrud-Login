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

    // GET DATA
    // @GetMapping("form")
    // public String create(Model model) {
    // model.addAttribute("region", new Region());
    // return "region/form";
    // }

    @GetMapping(value = { "form", "form/{regionId}" })
    public String form(@PathVariable(required = false) Integer regionId, Model model) {
        if (regionId != null) {
            // get data by id for update
            model.addAttribute("region", reg.getById(regionId));
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
        if (region.getRegionId() != null) {
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

    // Edit Data
    // get by id
    // @GetMapping(value = "edit/{regionId}")
    // public String edit(@PathVariable(required = true) int regionId, Model model)
    // {
    // model.addAttribute("region", reg.getById(regionId));
    // return "region/edit";
    // }

    // @PostMapping("update")
    // public String update(Region region) {
    // Boolean result = reg.updateData(region);
    // if (result) {
    // return "redirect:/region";
    // } else {
    // return "region/edit";
    // }
    // }

    // DELETE
    @PostMapping(value = "/delete/{regionId}")
    public String delete(@PathVariable Integer regionId) {
        Boolean result = reg.deleteData(regionId);
        if (result) {
            return "redirect:/region";
        } else {
            return "";
        }
    }
}
