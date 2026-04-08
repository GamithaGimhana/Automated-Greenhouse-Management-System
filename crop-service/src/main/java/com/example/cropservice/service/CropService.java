package com.example.cropservice.service;

import com.example.cropservice.dto.CropRequestDTO;
import com.example.cropservice.entity.Crop;

import java.util.List;

public interface CropService {

    Crop createCrop(CropRequestDTO dto);

    Crop updateStatus(Long id, String status);

    List<Crop> getAllCrops();
}