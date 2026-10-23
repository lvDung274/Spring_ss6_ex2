package com.example.bdx_ss6.controllers;

import com.example.bdx_ss6.dto.VehicleCreateRequest;
import com.example.bdx_ss6.dto.VehicleResponse;
import com.example.bdx_ss6.entities.Vehicle;
import com.example.bdx_ss6.responses.ApiResponse;
import com.example.bdx_ss6.responses.PageResponse;
import com.example.bdx_ss6.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Vehicle>> createVehicle(@RequestBody VehicleCreateRequest request) {
        Vehicle savedVehicle = vehicleService.createVehicle(request);

        ApiResponse<Vehicle> apiResponse = ApiResponse.<Vehicle>builder()
                .success(true)
                .message("Tạo phương tiện thành công")
                .data(savedVehicle)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword) {

        PageResponse<VehicleResponse> pagedResult = vehicleService.getPagedVehicles(
                page, size, sortBy, direction, keyword);

        ApiResponse<PageResponse<VehicleResponse>> apiResponse = ApiResponse.<PageResponse<VehicleResponse>>builder()
                .success(true)
                .message("Lấy danh sách phương tiện thành công")
                .data(pagedResult)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
