package ssamba.ept.sn.BankerApp.auth.exception;

public final class InvalidOldPasswordException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public InvalidOldPasswordException() {
        super("Ancien mot de passe incorrecte");
    }

    public InvalidOldPasswordException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidOldPasswordException(final String message) {
        super(message);
    }

    public InvalidOldPasswordException(final Throwable cause) {
        super(cause);
    }

}