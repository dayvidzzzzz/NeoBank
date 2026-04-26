package dy.system.minebanconovo.exception;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String field) {
        super(String.format("Endereço inválido: %s não preenchido", field));
    }
}