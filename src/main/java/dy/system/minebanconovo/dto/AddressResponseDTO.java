package dy.system.minebanconovo.dto;

public record AddressResponseDTO(
        String street,
        String number,
        String city,
        String state,
        String zipcode
) {}
