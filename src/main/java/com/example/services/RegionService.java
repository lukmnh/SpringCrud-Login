package com.example.services;

import com.example.demo.models.Region;

import java.util.*;

public interface RegionService {
    public List<Region> getAll();

    public Region getById(Integer id);

    public Boolean save(Region region);

    public Boolean delete(Integer id);

}
