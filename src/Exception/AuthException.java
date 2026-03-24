package Exception;

public class AuthException extends RuntimeException{

    public AuthException(String mensagem){
        super(mensagem);
    }

    public AuthException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }


}
