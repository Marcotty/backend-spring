package marcotty.softwares.technical_portfolio.controller.web;

import marcotty.softwares.technical_portfolio.diagnostic.RequestLogService;
import marcotty.softwares.technical_portfolio.diagnostic.TechnologieWatchlistService;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.management.ManagementFactory;
import java.time.Duration;

@Controller
public class DiagnosticController {

    private final ProjetRepository projetRepository;
    private final RequestLogService requestLogService;
    private final TechnologieWatchlistService technologieWatchlistService;

    public DiagnosticController(ProjetRepository projetRepository,
                                 RequestLogService requestLogService,
                                 TechnologieWatchlistService technologieWatchlistService) {
        this.projetRepository = projetRepository;
        this.requestLogService = requestLogService;
        this.technologieWatchlistService = technologieWatchlistService;
    }

    @GetMapping("/")
    public String diagnostic(Model model) {
        long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
        Duration uptime = Duration.ofMillis(uptimeMs);

        model.addAttribute("uptimeFormate", String.format("%dh %02dm %02ds",
                uptime.toHours(), uptime.toMinutesPart(), uptime.toSecondsPart()));
        model.addAttribute("nombreProjets", projetRepository.count());
        model.addAttribute("requetes", requestLogService.getEntreesRecentes());
        model.addAttribute("technologies", technologieWatchlistService.getTechnologies());

        return "diagnostic";
    }
}