package com.example.bdx_ss6.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse <T>{
    private Boolean success;
    private String message;
    private T data;

}
