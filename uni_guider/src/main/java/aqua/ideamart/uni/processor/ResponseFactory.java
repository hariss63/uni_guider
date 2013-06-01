package aqua.ideamart.uni.processor;

import aqua.ideamart.api.common.StatusCodes;
import aqua.ideamart.api.sms.MoSmsResp;

public class ResponseFactory {

    public static MoSmsResp error() {
        MoSmsResp moSmsResp = new MoSmsResp();
        moSmsResp.setStatusCode(StatusCodes.SystemErrorK);
        moSmsResp.setStatusDetail("System error occured");
        return  moSmsResp;
    }

    public static MoSmsResp success() {
        MoSmsResp moSmsResp = new MoSmsResp();
        moSmsResp.setStatusCode(StatusCodes.SuccessK);
        moSmsResp.setStatusDetail("Successfully processed by application.");
        return  moSmsResp;
    }
}
