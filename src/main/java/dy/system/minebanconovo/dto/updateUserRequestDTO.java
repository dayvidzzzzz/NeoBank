package dy.system.minebanconovo.dto;

import java.time.LocalDate;

public record updateUserRequestDTO(
        String fullName,
        String login,
        String email,
        LocalDate birthDate
) {
}
