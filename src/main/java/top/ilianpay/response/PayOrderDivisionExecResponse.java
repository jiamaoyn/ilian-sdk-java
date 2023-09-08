package top.ilianpay.response;

import top.ilianpay.model.PayOrderDivisionExecResModel;


/***
* 发起分账响应实现
*/
public class PayOrderDivisionExecResponse extends IlianPayResponse {

    private static final long serialVersionUID = 7419683269497002904L;

    public PayOrderDivisionExecResModel get() {
        if(getData() == null) return new PayOrderDivisionExecResModel();
        return getData().toJavaObject(PayOrderDivisionExecResModel.class);
    }

    @Override
    public boolean isSuccess(String apiKey) {
        if(super.isSuccess(apiKey)) {
            int state = get().getState();
            return state == 1;
        }
        return false;
    }

}
