package cs.sample.employeedetails.dto;

import java.util.List;

public class ResponseMessage {

    private String status;
    private List<MessageInfo> messageInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MessageInfo> getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(List<MessageInfo> messageInfo) {
        this.messageInfo = messageInfo;
    }

    private static final ResponseMessage RESPONSE_MESSAGE = new ResponseMessage();

    private ResponseMessage() {}

    public static ResponseMessage getInstance() {
        return RESPONSE_MESSAGE;
    }

}
