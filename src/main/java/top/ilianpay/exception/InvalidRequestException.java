package top.ilianpay.exception;

/**
 * 无效请求异常
 */
public class InvalidRequestException extends IlianPayException {

    private static final long serialVersionUID = 3163726141488238321L;

    public InvalidRequestException(String message, int statusCode, Throwable e) {
        super(message, statusCode, e);
    }

}
