package dy.system.minebanconovo.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String msg) {
        super(String.format("Saldo insuficiente para realizar a operação: %s", msg));
    }
}