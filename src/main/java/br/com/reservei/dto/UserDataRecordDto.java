package br.com.reservei.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserDataRecordDto(@NotNull @NotBlank Date birthday, @NotNull Integer gender, @NotNull Integer race) {
}
