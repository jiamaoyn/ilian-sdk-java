package top.ilianpay.exception;

/**
 * API连接异常
 */
public class APIConnectionException extends IlianPayException {

    private static final long serialVersionUID = -8764189839522042543L;

    public APIConnectionException(String message) {
        super(message);
    }

    public APIConnectionException(String message, Throwable e) {
        super(message, e);
    }

}
