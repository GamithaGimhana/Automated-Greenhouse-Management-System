package com.example.cropservice.service.impl;

import com.example.cropservice.dto.CropRequestDTO;
import com.example.cropservice.entity.Crop;
import com.example.cropservice.entity.CropStatus;
import com.example.cropservice.repository.CropRepository;
import com.example.cropservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository repository;

    @Override
    public Crop createCrop(CropRequestDTO dto) {

        Crop crop = new Crop();
        crop.setName(dto.getName());
        crop.setQuantity(dto.getQuantity());
        crop.setStatus(CropStatus.SEEDLING); // default

        return repository.save(crop);
    }

    @Override
    public Crop updateStatus(Long id, String status) {

        Crop crop = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        crop.setStatus(CropStatus.valueOf(status));

        return repository.save(crop);
    }

    @Override
    public List<Crop> getAllCrops() {
        return repository.findAll();
    }
}