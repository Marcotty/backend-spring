package marcotty.softwares.technical_portfolio.diagnostic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final RequestLogService logService;

    public RequestLoggingInterceptor(RequestLogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String origine = request.getHeader("Origin"); // présent si la requête vient d'Angular (autre port)
        if (origine == null) {
            origine = request.getRemoteAddr(); // sinon, on affiche juste l'IP appelante (ex: toi via curl)
        }

        logService.enregistrer(request.getMethod(), request.getRequestURI(), origine);
        return true; // ne bloque jamais la requête, on se contente de l'observer
    }
}