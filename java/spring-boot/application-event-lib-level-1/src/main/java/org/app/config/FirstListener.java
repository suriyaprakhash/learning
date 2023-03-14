package org.app.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.*;

import java.util.Properties;

public class FirstListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
//        , Ordered {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        ConfigurableEnvironment configurableEnvironment = event.getEnvironment();
        Properties properties = new Properties();
        properties.put("myprop", "first");
        configurableEnvironment.getPropertySources()
                .addFirst(new PropertiesPropertySource("first-listner", properties));
        System.out.println("ran first listner");
    }

//    @Override
//    public int getOrder() {
//        return 20;
//    }
}
