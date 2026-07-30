package marcotty.softwares.technical_portfolio.diagnostic;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

// Simple "interrupteur" en mémoire : quelle base de données est active en ce moment.
// AtomicReference plutôt qu'une simple variable, pour rester thread-safe si jamais
// plusieurs requêtes arrivent en même temps pendant une bascule.
@Component
public class DatabaseContext {

    public static final String H2 = "h2";
    public static final String POSTGRES = "postgres";

    private final AtomicReference<String> baseActive = new AtomicReference<>(H2);

    public String getBaseActive() {
        return baseActive.get();
    }

    public void setBaseActive(String base) {
        baseActive.set(base);
    }
}