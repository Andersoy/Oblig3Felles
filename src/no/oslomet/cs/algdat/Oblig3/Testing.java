package no.oslomet.cs.algdat.Oblig3;

import java.util.Comparator;

public class Testing {

    // OPPGAVE 0 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave0() {
            ObligSBinTre<String> tre = new ObligSBinTre<>(Comparator.naturalOrder());
            System.out.println(tre.antall()); // Utskrift: 0
    }

    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall()); // Utskrift: 10
    }

    // OPPGAVE 2 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave2() {

        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall());
        System.out.println(tre.antall(5));
        System.out.println(tre.antall(4));
        System.out.println(tre.antall(7));
        System.out.println(tre.antall(10));
        // Utskrift: 10 ​// Utskrift: 0 ​// Utskrift: 3 ​// Utskrift: 2 ​// Utskrift: 1

    }
}