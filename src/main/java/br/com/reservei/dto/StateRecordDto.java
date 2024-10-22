package br.com.reservei.dto;

import br.com.reservei.entity.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public record StateRecordDto(@NotNull @NotBlank String name) {
}
