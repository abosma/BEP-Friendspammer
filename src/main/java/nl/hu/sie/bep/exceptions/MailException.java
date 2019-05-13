package nl.hu.sie.bep.exceptions;

public class MailException extends RuntimeException {

    public MailException()
    {
        super();
    }

    public MailException(String s)
    {
        super(s);
    }

    public MailException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public MailException(Throwable throwable)
    {
        super(throwable);
    }
}
