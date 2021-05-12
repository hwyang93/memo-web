package memoWeb.web.config;

import memoWeb.web.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/*/*/login.do")
				.excludePathPatterns("/*/*/logout")
				.excludePathPatterns("/*/*/signIn.do");
	}
}
