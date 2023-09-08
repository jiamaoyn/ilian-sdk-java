package top.ilianpay.request;

import top.ilianpay.response.RefundOrderQueryResponse;
import top.ilianpay.IlianPay;
import top.ilianpay.model.IlianPayObject;
import top.ilianpay.net.RequestOptions;

/**
 * 退款查单请求实现
 */
public class RefundOrderQueryRequest implements IlianPayRequest<RefundOrderQueryResponse> {

    private String apiVersion = IlianPay.VERSION;
    private String apiUri = "api/refund/query";
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
    public Class<RefundOrderQueryResponse> getResponseClass() {
        return RefundOrderQueryResponse.class;
    }

}
