package github.luisfeliperochamartins.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.addAllowedMethod(CorsConfiguration.ALL);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.PUT);

		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/api/person", config);
		config.addAllowedOrigin("http://localhost:8080");
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}