package dy.system.minebanconovo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dy.system.minebanconovo.domain.enuns.StatusUser;
import dy.system.minebanconovo.domain.enuns.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
        String id,
        String fullName,
        String cpf,
        String login,
        StatusUser statusUser,
        UserRole role,
        String email,
        LocalDate birthDate,
        LocalDateTime createdAt,
        AddressResponseDTO address
) {
}
