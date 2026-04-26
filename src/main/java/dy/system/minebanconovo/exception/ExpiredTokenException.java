package dy.system.minebanconovo.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("Token de autenticação expirado");
    }
}
