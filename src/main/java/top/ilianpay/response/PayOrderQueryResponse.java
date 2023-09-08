package top.ilianpay.response;

import top.ilianpay.model.PayOrderQueryResModel;

/**
 * IlianPay支付查单响应实现
 */
public class PayOrderQueryResponse extends IlianPayResponse {

    private static final long serialVersionUID = 7654172640802954221L;

    public PayOrderQueryResModel get() {
        if(getData() == null) return new PayOrderQueryResModel();
        return getData().toJavaObject(PayOrderQueryResModel.class);
    }

}
