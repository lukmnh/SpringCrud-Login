package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.ComponentScans;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.example.demo.models.Region;
import com.example.demo.repositories.RegionRepository;

// @EnableRepository("com.example.services.RegionService")
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region getById(Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    @Override
    public Boolean save(Region region) {
        regionRepository.save(region);
        return regionRepository.findById(region.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        regionRepository.deleteById(id);
        return !regionRepository.findById(id).isPresent();
    }

}
