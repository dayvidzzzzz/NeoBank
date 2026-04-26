package dy.system.minebanconovo.dto;

import jakarta.validation.constraints.NotEmpty;

public record AddressRequestDTO(
        @NotEmpty
        String number,
        @NotEmpty
        String state,
        @NotEmpty
        String city,
        @NotEmpty
        String zipcode,
        @NotEmpty
        String street
) {
}
