package proj.web_api.handler;

public class ObligatoryFieldException extends BusinessException{
    
    public ObligatoryFieldException(String campo) {
        super("O campo de %s precisa ser preenchido!", campo);
    }
    
}
