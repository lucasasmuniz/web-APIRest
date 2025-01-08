package proj.web_api.handler;

public class NoUserFoundException extends BusinessException{
    
    public NoUserFoundException(String mensagem) {
        super(mensagem);
    }
    
}
