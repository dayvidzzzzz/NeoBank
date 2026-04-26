package dy.system.minebanconovo.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException(String cpf) {
        super(String.format("CPF inválido: %s", cpf));
    }
}