package aqua.ideamart.uni.processor;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.ServletContextEvent;

public class ContextListener extends GuiceServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        Thread thread = new Thread(new RequestProcessor());
        thread.start();
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule());
    }
}
