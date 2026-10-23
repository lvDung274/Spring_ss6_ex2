package com.example.bdx_ss6.services;

import com.example.bdx_ss6.dto.VehicleCreateRequest;
import com.example.bdx_ss6.dto.VehicleResponse;
import com.example.bdx_ss6.entities.Vehicle;
import com.example.bdx_ss6.repositories.VehicleRepository;
import com.example.bdx_ss6.responses.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword){
        if (page<0){
            page = 0;
        }
        Sort dataSort = Sort.unsorted();
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            if ("ASC".equalsIgnoreCase(direction)) {
                dataSort = Sort.by(sortBy).ascending();
            } else if ("DESC".equalsIgnoreCase(direction)) {
                dataSort = Sort.by(sortBy).descending();
            } else {
                dataSort = Sort.by(sortBy);
            }
        }
        Pageable pageable = PageRequest.of(page, size, dataSort);
        Page<VehicleResponse> vehiclePage = vehicleRepository.findAllByKeyword(keyword, pageable);
        return PageResponse.<VehicleResponse>builder()
                .items(vehiclePage.getContent())
                .page(vehiclePage.getNumber())
                .size(vehiclePage.getSize())
                .totalItems(vehiclePage.getTotalElements())
                .totalPages(vehiclePage.getTotalPages())
                .isLast(vehiclePage.isLast())
                .build();
    }
    public Vehicle createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(request.licensePlate())
                .color(request.color())
                .type(request.vehicleType())
                .build();

        return vehicleRepository.save(vehicle);
    }
}
