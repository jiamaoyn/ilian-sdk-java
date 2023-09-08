package top.ilianpay.model;

/***
* 分账响应结果
*/
public class PayOrderDivisionExecResModel extends IlianPayObject {

    /**
     * 分账状态 1-分账成功, 2-分账失败
     */
    private Byte state;

    /**
     * 上游分账批次号
     */
    private String channelBatchOrderId;

    /**
     * 支付渠道错误码
     */
    private String errCode;

    /**
     * 支付渠道错误信息
     */
    private String errMsg;


    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getChannelBatchOrderId() {
        return channelBatchOrderId;
    }

    public void setChannelBatchOrderId(String channelBatchOrderId) {
        this.channelBatchOrderId = channelBatchOrderId;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
