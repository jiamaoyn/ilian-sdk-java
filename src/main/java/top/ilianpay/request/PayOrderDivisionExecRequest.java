package top.ilianpay.request;

import top.ilianpay.IlianPay;
import top.ilianpay.response.PayOrderDivisionExecResponse;
import top.ilianpay.model.IlianPayObject;
import top.ilianpay.net.RequestOptions;

/***
* 分账发起
*/
public class PayOrderDivisionExecRequest implements IlianPayRequest<PayOrderDivisionExecResponse> {

    private String apiVersion = IlianPay.VERSION;
    private String apiUri = "api/division/exec";
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
    public Class<PayOrderDivisionExecResponse> getResponseClass() {
        return PayOrderDivisionExecResponse.class;
    }

}
