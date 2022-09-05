package nc.apps.smartadder.config;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:beans.xml")
public class SpringBootConfig {
}
