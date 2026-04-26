package dy.system.minebanconovo.exception;

public class EmptyRequestException extends RuntimeException{
    public EmptyRequestException(String msg){
        super(String.format("Resquest vazio: %s", msg));
    }
}
