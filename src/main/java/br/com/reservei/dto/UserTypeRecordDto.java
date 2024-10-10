package br.com.reservei.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserTypeRecordDto(@NotNull @NotBlank String name) {
}
