package nc.apps.smartadder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin
@SpringBootApplication
@ComponentScan("nc.apps")
@EnableCaching
public class BookInfoServiceApplication {
    public static void main(String[] args) throws IOException {
        String[] argsExtended = Arrays.copyOf(args,args.length+1);
        argsExtended[argsExtended.length-1]="--spring.profiles.active="+System.getenv("profile");
        SpringApplication.run(BookInfoServiceApplication.class, argsExtended);
    }
}
