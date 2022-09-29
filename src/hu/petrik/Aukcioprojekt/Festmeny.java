package hu.petrik.Aukcioprojekt;

import java.nio.BufferOverflowException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Festmeny {
    public static final int KEZDETI_LICIT = 100;
    private String cim;
    private String festo;
    private String stilus;

    private int licitekszama;
    private int legmagasabbLicit;
    private LocalDateTime legutolsoLicitIdeje;
    private boolean elkelt;

    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
        this.licitekszama = 0;
        this.legmagasabbLicit = 0;
        this.legutolsoLicitIdeje = null;
        this.elkelt = false;

        // TODO: többi adattagot bekell állítanom!
    }

    public String getCim() {
        return cim;
    }

    public String getFesto() {
        return festo;
    }

    public String getFtilus() {
        return stilus;
    }

    public int getLicitekszama() {
        return licitekszama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getLegutolsoLicitIdeje() {
        return legutolsoLicitIdeje;
    }

    public boolean isElkelt() {
        return elkelt;
    }


    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }

    public void licit() {
        this.licit(10);

    }

    public void licit(int mertek) {
        if (mertek < 10 || mertek > 100) {
            throw new IllegalArgumentException("A lciti mértéke 10 és 100 % közé kell hogy essen");
        }
        if (this.elkelt) {
            throw new RuntimeException("A festmeny már elkelt");
        }
        if (this.licitekszama == 0) {
            this.legmagasabbLicit = KEZDETI_LICIT;
        } else {
            int ujLicit = (int) (this.legmagasabbLicit * (1 + mertek / 100));
            this.legmagasabbLicit = getVeglegesLicitStringreAlakitassal(ujLicit);
        }
        this.licitekszama++;
        this.legutolsoLicitIdeje = LocalDateTime.now();
        //TODO: Majd megcsináljuk.
    }

    private int getVeglegesLicitMatematikaimuveletekkel(int ujLicit) {
        int ossztasokszama = 0;
        while (ujLicit > 100) {
            ujLicit /= 10;
            ossztasokszama++;
        }
        return (int) (ujLicit * Math.pow(10, ossztasokszama));
    }


    private int getVeglegesLicitStringreAlakitassal(int ujLicit) {
        String szovegesLicit = String.valueOf(ujLicit);
        int hossz = szovegesLicit.length();
        StringBuilder builder = new StringBuilder(szovegesLicit.substring(0, 2));
        for (int i = 0; i < hossz - 2; i++) {
            builder.append(0);
        }
        return Integer.parseInt(builder.toString());
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String festmenyAdatai = String.format("%s: %s (%s)",
                this.festo, this.cim, this.stilus);
        if (this.licitekszama > 0) {
            String licitAdatai = String.format("%s: %s (%s)\n" +
                            "%s\n" +
                            "%d $ - %s (összesen: %d db)\n",
                    this.festo, this.cim, this.stilus,
                    (this.elkelt) ? "elkelt" : "nem kelt",
                    this.legmagasabbLicit, this.legutolsoLicitIdeje, this.licitekszama);

            festmenyAdatai += "\n" + licitAdatai;
        }else {
            festmenyAdatai += "\nMég nem érkezett licit";
        }

        return festmenyAdatai;

    }
}