package aqua.ideamart.uni.rest;

import aqua.ideamart.api.sms.MoSmsReq;
import aqua.ideamart.api.sms.MoSmsResp;

import static aqua.ideamart.uni.processor.RequestProcessor.*;
import aqua.ideamart.uni.processor.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/uni-detail")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    private static final Logger logger = LoggerFactory.getLogger(RestService.class);

    @POST
    @Path("/mo")
    public MoSmsResp receiveMo(MoSmsReq req) {
        logger.debug("Sms Mo req message received = [{}].", req);
        try {
            BLOCKING_QUEUE.put(req);
        } catch (InterruptedException e) {
            return ResponseFactory.error();
        }
        return ResponseFactory.success();
    }
}
