package dy.system.minebanconovo.exception;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException() {
        super("Transação não autorizada");
    }
}