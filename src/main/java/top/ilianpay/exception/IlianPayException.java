package top.ilianpay.exception;

/**
 * IlianPay异常抽象类
 */
public abstract class IlianPayException extends Exception {

    private static final long serialVersionUID = 2566087783987900120L;

    private int statusCode;

    public IlianPayException(String message) {
        super(message, null);
    }

    public IlianPayException(String message, Throwable e) {
        super(message, e);
    }

    public IlianPayException(String message, int statusCode, Throwable e) {
        super(message, e);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
                return sb.toString();
    }
}
