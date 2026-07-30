package marcotty.softwares.technical_portfolio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;

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