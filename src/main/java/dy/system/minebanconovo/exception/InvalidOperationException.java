package dy.system.minebanconovo.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String msg){
        super(String.format("Operaçao não finalizada: %s", msg));
    }
}
