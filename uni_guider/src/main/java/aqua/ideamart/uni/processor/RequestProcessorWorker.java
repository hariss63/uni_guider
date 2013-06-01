package aqua.ideamart.uni.processor;

import aqua.ideamart.api.sms.MoSmsReq;
import aqua.ideamart.api.sms.MtSmsReq;
import aqua.ideamart.api.sms.MtSmsResp;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.Arrays;

public class RequestProcessorWorker implements Runnable {

    private final MoSmsReq req;
    private static final ClientConfig clientConfig = new DefaultClientConfig();
    private static final Client client = Client.create(clientConfig);
    private static final WebResource IN_SERVER_WEB_RESOURCE = client.resource(UriBuilder.fromUri("http://api.dialog.lk:8080/sms/send").build());
    private static final String APP_ID = "APP_002365";
    private static final String APP_PWD = "c02f9cc674053f635022846602b60f3e";

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessorWorker.class);

    public RequestProcessorWorker(MoSmsReq req) {
        this.req = req;
    }

    @Override
    public void run() {
        String response = "";
        try {
            response = RequestHandler.handle(req.getMessage());
        } catch (Exception e) {
            response = "Error occurred while processing the uni number.";
        } finally {
            final MtSmsReq mtSmsReq = new MtSmsReq();
            mtSmsReq.setApplicationId(APP_ID);
            mtSmsReq.setMessage(response);
            mtSmsReq.setPassword(APP_PWD);
            mtSmsReq.setDestinationAddresses(Arrays.asList(req.getSourceAddress()));

            sendMt(mtSmsReq);
        }
    }

    private void sendMt(MtSmsReq req) {
        MtSmsResp resp = IN_SERVER_WEB_RESOURCE.accept(MediaType.APPLICATION_JSON_TYPE).
                type(MediaType.APPLICATION_JSON_TYPE).
                post(MtSmsResp.class, req);

        logger.info("Sent Mt req = [{}] for NBL, received Mt resp = [{}]", req, resp);
    }
}
