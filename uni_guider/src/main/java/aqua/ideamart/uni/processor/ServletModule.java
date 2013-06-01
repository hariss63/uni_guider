package aqua.ideamart.uni.processor;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

public class ServletModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        Map<String, String> params = new HashMap<String, String>();
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "aqua.ideamart.uni.rest");
        serve("/*").with(GuiceContainer.class, params);
    }
}
