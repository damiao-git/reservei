package br.com.reservei.dto;

import jakarta.validation.constraints.*;

public record GenderRecordDto(@NotBlank @NotNull String name) {
}
