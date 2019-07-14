package indi.rui.common.web.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@Slf4j
public class PriKeyInitializer implements ApplicationRunner , EnvironmentAware {
    private Environment env;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String keyfileDir = env.getProperty("docm.auth.token.priKey");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
