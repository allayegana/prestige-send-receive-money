package us.com.prestigemoney.sendreceive.exception;

public class EmailExistesException extends Exception {
    public EmailExistesException( String s) {

        super(s);
    }

    private   static final long SerialVersionUID = 1L;
}
