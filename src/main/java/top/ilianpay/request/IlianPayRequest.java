package top.ilianpay.request;

import top.ilianpay.response.IlianPayResponse;
import top.ilianpay.model.IlianPayObject;
import top.ilianpay.net.RequestOptions;

/**
 * IlianPay请求接口
 */
public interface IlianPayRequest<T extends IlianPayResponse> {

    /**
     * 获取当前接口的路径
     * @return
     */
    String getApiUri();

    /**
     * 获取当前接口的版本
     * @return
     */
    String getApiVersion();

    /**
     * 设置当前接口的版本
     * @return
     */
    void setApiVersion(String apiVersion);

    RequestOptions getRequestOptions();

    void setRequestOptions(RequestOptions options);

    IlianPayObject getBizModel();

    void setBizModel(IlianPayObject bizModel);

    Class<T> getResponseClass();

}
