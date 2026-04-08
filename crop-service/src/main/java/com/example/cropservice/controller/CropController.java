package com.example.cropservice.controller;

import com.example.cropservice.dto.CropRequestDTO;
import com.example.cropservice.dto.CropStatusUpdateDTO;
import com.example.cropservice.entity.Crop;
import com.example.cropservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping
    public Crop create(@RequestBody CropRequestDTO dto) {
        return cropService.createCrop(dto);
    }

    @PutMapping("/{id}/status")
    public Crop updateStatus(@PathVariable Long id,
                             @RequestBody CropStatusUpdateDTO dto) {
        return cropService.updateStatus(id, dto.getStatus().name());
    }

    @GetMapping
    public List<Crop> getAll() {
        return cropService.getAllCrops();
    }
}