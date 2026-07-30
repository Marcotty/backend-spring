package marcotty.softwares.technical_portfolio.diagnostic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestLogEntry {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String methode;
    private final String chemin;
    private final String origine;
    private final LocalDateTime horodatage;

    public RequestLogEntry(String methode, String chemin, String origine) {
        this.methode = methode;
        this.chemin = chemin;
        this.origine = origine;
        this.horodatage = LocalDateTime.now();
    }

    public String getMethode() { return methode; }
    public String getChemin() { return chemin; }
    public String getOrigine() { return origine; }
    public String getHeureFormatee() { return horodatage.format(FORMAT); }
}