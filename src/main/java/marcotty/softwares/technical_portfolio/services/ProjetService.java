package marcotty.softwares.technical_portfolio.services;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService {

    private final ProjetRepository repository;

    public ProjetService(ProjetRepository repository) {
        this.repository = repository;
    }

    public List<Projet> findAll() {
        return repository.findAll();
    }
}