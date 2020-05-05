import com.vyibc.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
@ComponentScan(basePackages = {"com.vyibc.course"})//扫描接口
@EnableAutoConfiguration
public class CourseEdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseEdgeServiceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean(CourseFilter courseFilter ) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(courseFilter);
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/*");
		filterRegistrationBean.setUrlPatterns(urlPatterns);
		return filterRegistrationBean;
	}
}
