package dy.system.minebanconovo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record UserRegisterDTO(
        @NotEmpty
        String fullName,
        @NotEmpty
        String cpf,
        @NotEmpty
        String login,
        @NotEmpty
        String email,
        @NotEmpty
        String password,
        LocalDate birthDate,
        @NotBlank
        AddressRequestDTO addressDTO
) {
}
