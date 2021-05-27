package memoWeb.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:globals.properties")
public class ConfigUtil {
	private final Environment environment;

	public String getProperty(String key) {
		return environment.getProperty(key);
	}
}
