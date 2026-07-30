package marcotty.softwares.technical_portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import marcotty.softwares.technical_portfolio.diagnostic.RequestLogService;
import marcotty.softwares.technical_portfolio.diagnostic.RequestLoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestLogService logService;

    public WebConfig(RequestLogService logService) {
        this.logService = logService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLoggingInterceptor(logService))
                .excludePathPatterns("/", "/css/**", "/js/**", "/favicon.ico", "/h2-console/**",
                        "/diagnostic/basculer-db"); // déjà loggué manuellement, en détail, dans DatabaseSwitchController
    }
}