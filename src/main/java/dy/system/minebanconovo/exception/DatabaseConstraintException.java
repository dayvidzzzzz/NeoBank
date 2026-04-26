package dy.system.minebanconovo.exception;

public class DatabaseConstraintException extends RuntimeException {
    public DatabaseConstraintException(String msg) {
        super(String.format("Erro de integridade: %s", msg));
    }
}