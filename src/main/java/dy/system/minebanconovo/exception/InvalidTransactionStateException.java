package dy.system.minebanconovo.exception;

public class InvalidTransactionStateException extends RuntimeException {
    public InvalidTransactionStateException(String currentState, String expectedState) {
        super(String.format("Estado inválido: %s. Esperado: %s", currentState, expectedState));
    }
}
