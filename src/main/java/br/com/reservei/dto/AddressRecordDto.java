package br.com.reservei.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRecordDto(
        @NotBlank @NotNull String cep,
        @NotNull Integer city_id,
        @NotBlank @NotNull String neighborhood,
        @NotBlank @NotNull String address_line,
        @NotBlank @NotNull String complements
) {
}
