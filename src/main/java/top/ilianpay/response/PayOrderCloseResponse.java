package top.ilianpay.response;

import top.ilianpay.model.PayOrderCloseResModel;

/**
 * 支付 关闭订单响应实现
 */
public class PayOrderCloseResponse extends IlianPayResponse {

    private static final long serialVersionUID = 7654172640802954221L;

    public PayOrderCloseResModel get() {
        if(getData() == null) return new PayOrderCloseResModel();
        return getData().toJavaObject(PayOrderCloseResModel.class);
    }

}
