package Teste;

import Modele.Rezervare;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class RezervareTest {

    @Test
    public void testCalculeazaPret_normal() {
        Rezervare r = new Rezervare(1,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5));
        assertEquals(800.0, r.calculeazaPret(200.0), 0.01);
    }

    @Test
    public void testCalculeazaPret_oNoapte() {
        Rezervare r = new Rezervare(2,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 2));
        assertEquals(150.0, r.calculeazaPret(150.0), 0.01);
    }

    @Test
    public void testCalculeazaPret_pretZero() {
        Rezervare r = new Rezervare(3,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5));
        assertEquals(0.0, r.calculeazaPret(0.0), 0.01);
    }

    @Test
    public void testEfectueazaPlata_statusSeSchimba() {
        Rezervare r = new Rezervare(4,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5));
        assertEquals("activa", r.getStatus());
        r.efectueazaPlata(800.0, "card");
        assertEquals("platita", r.getStatus());
    }

    @Test
    public void testEfectueazaPlata_plataNuENull() {
        Rezervare r = new Rezervare(5,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 3));
        assertNull(r.getPlata());
        r.efectueazaPlata(400.0, "cash");
        assertNotNull(r.getPlata());
    }

    @Test
    public void testEfectueazaPlata_sumaCorecta() {
        Rezervare r = new Rezervare(6,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 4));
        r.efectueazaPlata(600.0, "card");
        assertEquals(600.0, r.getPlata().getSuma(), 0.01);
    }

    @Test
    public void testEfectueazaPlata_metodaCorecta() {
        Rezervare r = new Rezervare(7,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 4));
        r.efectueazaPlata(600.0, "cash");
        assertEquals("cash", r.getPlata().getMetoda());
    }

    @Test
    public void testAnuleaza_statusAnulat() {
        Rezervare r = new Rezervare(8,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5));
        r.anuleaza();
        assertEquals("anulata", r.getStatus());
    }

    @Test
    public void testAnuleaza_plataEsteNull() {
        Rezervare r = new Rezervare(9,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5));
        r.efectueazaPlata(800.0, "card");
        assertNotNull(r.getPlata());
        r.anuleaza();
        assertNull(r.getPlata());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEfectueazaPlata_sumaNegativa() {
        Rezervare r = new Rezervare(10,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5));
        r.efectueazaPlata(-100.0, "card");
    }
}