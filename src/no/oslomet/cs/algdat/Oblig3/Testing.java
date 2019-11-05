package no.oslomet.cs.algdat.Oblig3;

import java.util.Comparator;

public class Testing {

    // OPPGAVE 0 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave0() {
            ObligSBinTre<String> tre = new ObligSBinTre<>(Comparator.naturalOrder());
            System.out.println(tre.antall()); // Utskrift: 0
    }
}