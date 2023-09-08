package top.ilianpay.request;

import top.ilianpay.IlianPay;
import top.ilianpay.model.IlianPayObject;
import top.ilianpay.net.RequestOptions;
import top.ilianpay.response.PayOrderQueryResponse;

/**
 * Pay支付查单请求实现
 */
public class PayOrderQueryRequest implements IlianPayRequest<PayOrderQueryResponse> {

    private String apiVersion = IlianPay.VERSION;
    private String apiUri = "api/pay/query";
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
    public Class<PayOrderQueryResponse> getResponseClass() {
        return PayOrderQueryResponse.class;
    }

}
