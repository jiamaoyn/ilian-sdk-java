package top.ilianpay.request;

import top.ilianpay.IlianPay;
import top.ilianpay.response.PayOrderCloseResponse;
import top.ilianpay.model.IlianPayObject;
import top.ilianpay.net.RequestOptions;

/**
 * Pay支付 订单关闭请求实现
 */
public class PayOrderCloseRequest implements IlianPayRequest<PayOrderCloseResponse> {

    private String apiVersion = IlianPay.VERSION;
    private String apiUri = "api/pay/close";
    private RequestOptions options;
    private IlianPayObject bizModel = null;

    @Override
    public String getApiUri() {
        return this.apiUri;
    }

    @Override
    public String getApiVersion() {
        return this.apiVersion;
    }

    @Override
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public RequestOptions getRequestOptions() {
        return this.options;
    }

    @Override
    public void setRequestOptions(RequestOptions options) {
        this.options = options;
    }

    @Override
    public IlianPayObject getBizModel() {
        return this.bizModel;
    }

    @Override
    public void setBizModel(IlianPayObject bizModel) {
        this.bizModel = bizModel;
    }

    @Override
    public Class<PayOrderCloseResponse> getResponseClass() {
        return PayOrderCloseResponse.class;
    }

}
