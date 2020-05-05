import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = {"com.vyibc.user"})//扫描接口
@EnableAutoConfiguration
public class UserEdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserEdgeServiceApplication.class, args);
	}

}
