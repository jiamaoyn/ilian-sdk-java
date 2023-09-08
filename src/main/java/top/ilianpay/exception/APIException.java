package top.ilianpay.exception;

/**
 * API异常
 */
public class APIException extends IlianPayException {

    private static final long serialVersionUID = -2753719317464278319L;

    public APIException(String message, String type, String code, int statusCode, Throwable e) {
        super(message, statusCode, e);
    }
}
