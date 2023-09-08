package top.ilianpay.response;

import top.ilianpay.model.RefundOrderQueryResModel;

/**
 * 退款查单响应实现
 */
public class RefundOrderQueryResponse extends IlianPayResponse {

    private static final long serialVersionUID = 7654172640802954221L;

    public RefundOrderQueryResModel get() {
        if(getData() == null) return new RefundOrderQueryResModel();
        return getData().toJavaObject(RefundOrderQueryResModel.class);
    }

}
