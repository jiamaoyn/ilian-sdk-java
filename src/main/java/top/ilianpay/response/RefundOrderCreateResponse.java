package top.ilianpay.response;

import top.ilianpay.model.RefundOrderCreateResModel;

/**
 * 退款响应实现
 */
public class RefundOrderCreateResponse extends IlianPayResponse {

    private static final long serialVersionUID = 7419683269497002904L;

    public RefundOrderCreateResModel get() {
        if(getData() == null) return new RefundOrderCreateResModel();
        return getData().toJavaObject(RefundOrderCreateResModel.class);
    }

    @Override
    public boolean isSuccess(String apiKey) {
        if(super.isSuccess(apiKey)) {
            int state = get().getState();
            return state == 0 || state == 1 || state == 2;
        }
        return false;
    }

}
