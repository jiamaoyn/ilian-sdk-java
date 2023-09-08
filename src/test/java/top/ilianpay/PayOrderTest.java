package top.ilianpay;

import top.ilianpay.exception.IlianPayException;
import top.ilianpay.model.PayOrderCloseReqModel;
import top.ilianpay.model.PayOrderCreateReqModel;
import top.ilianpay.model.PayOrderQueryReqModel;
import top.ilianpay.request.PayOrderCloseRequest;
import top.ilianpay.request.PayOrderCreateRequest;
import top.ilianpay.request.PayOrderQueryRequest;
import top.ilianpay.response.PayOrderCloseResponse;
import top.ilianpay.response.PayOrderCreateResponse;
import top.ilianpay.response.PayOrderQueryResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

class PayOrderTest {

    final static Logger _log = LoggerFactory.getLogger(PayOrderTest.class);

    @BeforeAll
    public static void initApiKey() {
        IlianPay.setApiBase(IlianPayTestData.getApiBase());
        IlianPay.apiKey = IlianPayTestData.getApiKey();
        IlianPay.mchNo = IlianPayTestData.getMchNo();
        IlianPay.appId = IlianPayTestData.getAppId();
    }

    @Test
    public void testPayOrderCreate() {
        IlianPayClient ilianPayClient = IlianPayClient.getInstance(IlianPay.appId, IlianPay.apiKey, IlianPay.getApiBase());
        String wayCode = "ALI_PC";                           // 支付方式
        PayOrderCreateRequest request = new PayOrderCreateRequest();
        PayOrderCreateReqModel model = new PayOrderCreateReqModel();
        model.setMchNo(IlianPay.mchNo);                       // 商户号
        model.setAppId(ilianPayClient.getAppId());            // 应用ID
        String orderNo = "mho" + new Date().getTime();
        model.setMchOrderNo(orderNo);                       // 商户订单号
        model.setWayCode(wayCode);                          // 支付方式
        model.setAmount(3000l);                                // 金额，单位分
        model.setCurrency("CNY");                           // 币种，目前只支持cny
        model.setClientIp("192.166.1.132");                 // 发起支付请求客户端的IP地址
        model.setSubject("商品标题");                         // 商品标题
        model.setBody("商品描述");                            // 商品描述
        model.setNotifyUrl("http://www.xxxxxx.com");      // 异步通知地址
        model.setReturnUrl("");                             // 前端跳转地址
        model.setExtParam("");                              // 商户扩展参数,回调时原样返回

        request.setBizModel(model);
        try {
            PayOrderCreateResponse response = ilianPayClient.execute(request);
            _log.info("验签结果：{}", response.checkSign(IlianPay.apiKey));
            // 下单成功
            if(response.isSuccess(IlianPay.apiKey)) {
                String payOrderId = response.get().getPayOrderId();
                _log.info("payOrderId：{}", payOrderId);
                _log.info("mchOrderNo：{}", response.get().getMchOrderNo());
                _log.info("payData：{}", response.get().getPayData());
            }else {
                _log.info("下单失败：{}", orderNo);
                _log.info("通道错误码：{}", response.get().getErrCode());
                _log.info("通道错误信息：{}", response.get().getErrMsg());
            }
        } catch (IlianPayException e) {
            _log.error(e.getMessage());
        }

    }

    @Test
    public void testPayOrderQuery() {
        IlianPayClient ilianPayClient = IlianPayClient.getInstance(IlianPay.appId, IlianPay.apiKey, IlianPay.getApiBase());
        PayOrderQueryRequest request = new PayOrderQueryRequest();
        PayOrderQueryReqModel model = new PayOrderQueryReqModel();
        model.setMchNo(IlianPay.mchNo);                                           // 商户号
        model.setAppId(ilianPayClient.getAppId());                                // 应用ID
        model.setPayOrderId("P1699745854051237890");                          // 支付订单号
        request.setBizModel(model);

        try {
            PayOrderQueryResponse response = ilianPayClient.execute(request);
            _log.info("验签结果：{}", response.checkSign(IlianPay.apiKey));

            if(response.isSuccess(IlianPay.apiKey)) {
                _log.info("订单信息：{}", response);
                _log.info("金额：{}", response.get().getAmount());
                _log.info("订单状态：{}", response.get().getState());
            }
        } catch (IlianPayException e) {

            e.printStackTrace();
        }

    }

    @Test
    public void testPayOrderClose() {
        IlianPayClient ilianPayClient = new IlianPayClient();
        PayOrderCloseRequest request = new PayOrderCloseRequest();
        PayOrderCloseReqModel model = new PayOrderCloseReqModel();
        model.setMchNo(IlianPay.mchNo);                                           // 商户号
        model.setAppId(IlianPay.appId);
        model.setPayOrderId("P1485879219030011906");                            // 支付订单号
        request.setBizModel(model);

        try {
            PayOrderCloseResponse response = ilianPayClient.execute(request);
            _log.info("验签结果：{}", response.checkSign(IlianPay.apiKey));

            if(response.isSuccess(IlianPay.apiKey)) {
                _log.info("返回信息：{}", response);
            }
        } catch (IlianPayException e) {

            e.printStackTrace();
        }

    }
}