package aqua.ideamart.uni.processor;

import aqua.ideamart.api.sms.MoSmsReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestProcessor implements Runnable {

    private static final int BLOCKING_QUEUE_SIZE = 1000;
    public static final ArrayBlockingQueue<MoSmsReq> BLOCKING_QUEUE = new ArrayBlockingQueue<MoSmsReq>(BLOCKING_QUEUE_SIZE, true);
    private static final int WORKER_THREADS = 10;
    ExecutorService service = Executors.newFixedThreadPool(WORKER_THREADS);
    private static final Logger logger = LoggerFactory.getLogger(RequestProcessor.class);

    public RequestProcessor() {
    }

    @Override
    public void run() {
        while (true) {
            if(BLOCKING_QUEUE.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } else {
                service.execute(new RequestProcessorWorker(BLOCKING_QUEUE.poll()));
            }
        }
    }
}