package br.com.reservei.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CityRecordDto(@NotBlank @NotNull String name, @NotNull Integer state_id) {
}
