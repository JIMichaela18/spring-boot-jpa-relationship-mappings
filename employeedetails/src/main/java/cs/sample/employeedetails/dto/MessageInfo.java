package cs.sample.employeedetails.dto;

public class MessageInfo {
    private String msgCode;
    private String msg;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private static final MessageInfo MESSAGE_INFO = new MessageInfo();

    private MessageInfo() {}

    public static MessageInfo getInstance() {
        return MESSAGE_INFO;
    }

    public static MessageInfo setMessage(String msgCode, String msg){
        MessageInfo msgInfo = new MessageInfo();
        msgInfo.setMsgCode(msgCode);
        msgInfo.setMsg(msg);
        return msgInfo;
    }
}
