package marcotty.softwares.technical_portfolio.repository;

import marcotty.softwares.technical_portfolio.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
}