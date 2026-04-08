package com.example.cropservice.dto;

import com.example.cropservice.entity.CropStatus;
import lombok.Data;

@Data
public class CropStatusUpdateDTO {
    private CropStatus status;
}