package Exeptions;
public class IncorrectPassword extends  RuntimeException{
    public IncorrectPassword(String message){
        super(message);
    }
}
