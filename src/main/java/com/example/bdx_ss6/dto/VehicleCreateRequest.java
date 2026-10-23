package com.example.bdx_ss6.dto;

import com.example.bdx_ss6.entities.VehicleType;

public record VehicleCreateRequest (String licensePlate,
                                    String color,
                                    VehicleType vehicleType){
}
