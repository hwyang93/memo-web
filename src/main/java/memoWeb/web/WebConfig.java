package memoWeb.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig {

	@Bean
	MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
}
