package com.example.ticket_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private  String error;
    private Optional<String> errorMessage;
}
