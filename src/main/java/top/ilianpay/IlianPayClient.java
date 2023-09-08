package top.ilianpay;

import com.alibaba.fastjson.JSONObject;
import top.ilianpay.exception.IlianPayException;
import top.ilianpay.net.APIIlianPayRequest;
import top.ilianpay.net.APIResource;
import top.ilianpay.net.RequestOptions;
import top.ilianpay.request.IlianPayRequest;
import top.ilianpay.response.IlianPayResponse;
import top.ilianpay.util.JSONWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * sdk客户端
 */
public class IlianPayClient extends APIResource {

    private static final Map<String, IlianPayClient> clientMap = new HashMap<String, IlianPayClient>();

    private String appId;
    private String signType = IlianPay.DEFAULT_SIGN_TYPE;
    private String apiKey = IlianPay.apiKey;
    private String apiBase = IlianPay.getApiBase();

    public IlianPayClient(String apiBase, String signType, String apiKey) {
        this.apiBase = apiBase;
        this.signType = signType;
        this.apiKey = apiKey;
    }

    public IlianPayClient(String apiBase, String apiKey) {
        this.apiBase = apiBase;
        this.apiKey = apiKey;
    }

    public IlianPayClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public IlianPayClient() {
    }

    public static synchronized IlianPayClient getInstance(String appId, String apiKey, String apiBase) {
        IlianPayClient client = clientMap.get(appId);
        if (client != null) {
            return client;
        }
        client = new IlianPayClient();
        clientMap.put(appId, client);
        client.appId = appId;
        client.apiKey = apiKey;
        client.apiBase = apiBase;
        return client;
    }

    public static synchronized IlianPayClient getInstance(String appId, String apiKey) {
        IlianPayClient client = clientMap.get(appId);
        if (client != null) {
            return client;
        }
        client = new IlianPayClient();
        clientMap.put(appId, client);
        client.appId = appId;
        client.apiKey = apiKey;
        return client;
    }

    public static synchronized IlianPayClient getInstance(String appId) {
        IlianPayClient client = clientMap.get(appId);
        if (client != null) {
            return client;
        }
        client = new IlianPayClient();
        clientMap.put(appId, client);
        client.appId = appId;
        return client;
    }

    public String getAppId() {
        return appId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiBase() {
        return apiBase;
    }

    public void setApiBase(String apiBase) {
        this.apiBase = apiBase;
    }

    public <T extends IlianPayResponse> T execute(IlianPayRequest<T> request) throws IlianPayException {

        // 支持用户自己设置RequestOptions
        if (request.getRequestOptions() == null) {
            RequestOptions options = RequestOptions.builder()
                .setVersion(request.getApiVersion())
                .setUri(request.getApiUri())
                .setAppId(this.appId)
                .setApiKey(this.apiKey)
                .build();
            request.setRequestOptions(options);
        }

        return execute(request, RequestMethod.POST, this.apiBase);
    }


    public String getRequestUrl(IlianPayRequest request) throws IlianPayException {
        // 支持用户自己设置RequestOptions
        if (request.getRequestOptions() == null) {
            RequestOptions options = RequestOptions.builder()
                .setVersion(request.getApiVersion())
                .setUri(request.getApiUri())
                .setAppId(this.appId)
                .setApiKey(this.apiKey)
                .build();
            request.setRequestOptions(options);
        }
        String jsonParam = new JSONWriter().write(request.getBizModel(), true);

        JSONObject params = JSONObject.parseObject(jsonParam);
        request.getRequestOptions();

        return APIIlianPayRequest.buildURLWithSign(this.apiBase, params, request.getRequestOptions()).toString();
    }
}
