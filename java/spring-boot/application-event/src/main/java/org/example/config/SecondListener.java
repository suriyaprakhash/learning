package org.example.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

public class SecondListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
//        , Ordered {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        ConfigurableEnvironment configurableEnvironment = event.getEnvironment();
        Properties properties = new Properties();
        properties.put("myprop", "second");
//        configurableEnvironment.getPropertySources().addBefore("first-listner",
//                new PropertiesPropertySource("second-listner", properties));
        configurableEnvironment.getPropertySources()
                .addFirst(new PropertiesPropertySource("second-listner", properties));
        System.out.println("ran second listner");
    }

//    @Override
//    public int getOrder() {
//        return 10;
//    }
}
