package top.ilianpay.request;

import top.ilianpay.IlianPay;
import top.ilianpay.response.DivisionReceiverBindResponse;
import top.ilianpay.model.IlianPayObject;
import top.ilianpay.net.RequestOptions;

/***
* 分账绑定接口
*/
public class DivisionReceiverBindRequest implements IlianPayRequest<DivisionReceiverBindResponse> {

    private String apiVersion = IlianPay.VERSION;
    private String apiUri = "api/division/receiver/bind";
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
    public Class<DivisionReceiverBindResponse> getResponseClass() {
        return DivisionReceiverBindResponse.class;
    }

}
