package top.ilianpay.net;

import com.alibaba.fastjson.JSON;
import top.ilianpay.exception.APIConnectionException;
import top.ilianpay.exception.IlianPayException;

import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Http请求客户端
 */
public abstract class HttpClient {

    /**
     * 网络故障后尝试发送HTTP请求之间的最大延迟时间
     */
    public static final long maxNetworkRetriesDelay = 5000;

    /**
     * 网络故障后尝试发送HTTP请求之间的最小延迟时间
     */
    public static final long minNetworkRetriesDelay = 500;

    /**
     * 是否网络重试休眠
     */
    boolean networkRetriesSleep = true;

    public HttpClient() {}

    /**
     * 发送http请求
     * @param request
     * @return
     * @throws IlianPayException
     */
    public abstract APIIlianPayResponse request(APIIlianPayRequest request) throws IlianPayException;

    /**
     * 发送请求到IlianPay的API(支持重试)
     * @param request
     * @return
     * @throws IlianPayException
     */
    public APIIlianPayResponse requestWithRetries(APIIlianPayRequest request) throws IlianPayException {
        APIConnectionException requestException = null;
        APIIlianPayResponse response = null;
        int retry = 0;

        while (true) {
            requestException = null;

            try {
                response = this.request(request);
            } catch (APIConnectionException e) {
                requestException = e;
            }

            if (!this.shouldRetry(retry, requestException, request, response)) {
                break;
            }

            retry += 1;

            try {
                Thread.sleep(this.sleepTime(retry));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (requestException != null) {
            throw requestException;
        }

        response.setNumRetries(retry);

        return response;
    }

    protected static String buildUserAgentString(String version) {
        return String.format("IlianPay/v1 JavaBindings/%s", version);
    }

    protected static String buildXIlianPayClientUserAgentString(String version) {
        String[] propertyNames = {
                "os.name",
                "os.version",
                "os.arch",
                "java.version",
                "java.vendor",
                "java.vm.version",
                "java.vm.vendor"
        };

        Map<String, String> propertyMap = new HashMap<>();
        for (String propertyName : propertyNames) {
            propertyMap.put(propertyName, System.getProperty(propertyName));
        }
        propertyMap.put("bindings.version", version);
        propertyMap.put("lang", "Java");
        propertyMap.put("publisher", "IlianPay");
        return JSON.toJSONString(propertyMap);
    }

    private boolean shouldRetry(
            int numRetries, IlianPayException exception, APIIlianPayRequest request, APIIlianPayResponse response) {
        // Do not retry if we are out of retries.
        if (numRetries >= request.options.getMaxNetworkRetries()) {
            return false;
        }

        // Retry on connection error.
        if ((exception != null)
                && (exception.getCause() != null)
                && (exception.getCause() instanceof ConnectException)) {
            return true;
        }

        // Retry on 500, 503, and other internal errors.
        if ((response != null) && (response.getResponseCode() >= 500)) {
            return true;
        }

        return false;
    }

    private long sleepTime(int numRetries) {
        if (!networkRetriesSleep) {
            return 0;
        }

        long delay = (long) (minNetworkRetriesDelay * Math.pow(2, numRetries - 1));

        if (delay > maxNetworkRetriesDelay) {
            delay = maxNetworkRetriesDelay;
        }

        double jitter = ThreadLocalRandom.current().nextDouble(0.75, 1.0);
        delay = (long) (delay * jitter);

        if (delay < minNetworkRetriesDelay) {
            delay = minNetworkRetriesDelay;
        }

        return delay;
    }

    private static String getRequestURIFromURL(URL url) {
        String path = url.getPath();
        String query = url.getQuery();
        if (query == null) {
            return path;
        }
        return path + "?" + query;
    }

}
