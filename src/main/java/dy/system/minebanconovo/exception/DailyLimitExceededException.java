package dy.system.minebanconovo.exception;

public class DailyLimitExceededException extends RuntimeException {
    public DailyLimitExceededException(double limit) {
        super(String.format("Limite diário de R$ %.2f excedido", limit));
    }
}
