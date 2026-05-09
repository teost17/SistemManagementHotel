package Teste;

import Modele.Rezervare;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class RezervareTest {

    // Test 1 - rezervare normala, 3 nopti
    @Test
    public void testCalculeazaPret_3Nopti() {
        Rezervare r = new Rezervare(1,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 4));
        double pret = r.calculeazaPret(200);
        assertEquals(600.0, pret, 0.01);
    }

    // Test 2 - o singura noapte
    @Test
    public void testCalculeazaPret_1Noapte() {
        Rezervare r = new Rezervare(2,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 2));
        double pret = r.calculeazaPret(150);
        assertEquals(150.0, pret, 0.01);
    }

    // Test 3 - status initial este "activa"
    @Test
    public void testStatusInitial() {
        Rezervare r = new Rezervare(3,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 3));
        assertEquals("activa", r.getStatus());
    }

    // Test 4 - dupa plata statusul devine "platita"
    @Test
    public void testStatusDupaPlata() {
        Rezervare r = new Rezervare(4,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 3));
        r.efectueazaPlata(400, "card");
        assertEquals("platita", r.getStatus());
    }

    // Test 5 - plata nu e null dupa efectuare
    @Test
    public void testPlataExistaDupaEfectuare() {
        Rezervare r = new Rezervare(5,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 3));
        r.efectueazaPlata(400, "cash");
        assertNotNull(r.getPlata());
    }

    // Test 6 - pret zero nopti (caz limita)
    @Test
    public void testCalculeazaPret_ZeroNopti() {
        Rezervare r = new Rezervare(6,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 1));
        double pret = r.calculeazaPret(200);
        assertEquals(0.0, pret, 0.01);
    }
}