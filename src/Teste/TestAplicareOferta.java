package Teste;

import Modele.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste JUnit 5 pentru scenariul:
 * "Client face o rezervare cu oferta" (Diagrama de activitati)
 *
 * Fluxul testat conform diagramei:
 * 1. Sistemul verifica ofertele clientului — getOferteAplicate()
 * 2. [Decizie] Are oferte active?
 *    - DA → aplicaReducere(pretInitial)
 *    - NU → Calculeaza pret fara reducere
 * 3. Calculeaza pret final — calculeazaPret(pretPeNoapte)
 * 4. Clientul confirma rezervarea — adaugaRezervare()
 * 5. Sistemul marcheaza camera ocupata — ocupa()
 * 6. Sistemul efectueaza plata — efectueazaPlata(suma, metoda)
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAplicareOferta {

    private Camera camera;
    private Client client;
    private Oferta oferta;
    private Rezervare rezervare;

    @BeforeEach
    void setup() {
        camera = new Camera(1, 101, 200.0, Camera.TipCamera.DOUBLE);
        client = new Client(1, "Elena Popescu", "elena@gmail.com", "0722000001");
        oferta = new Oferta(1, "Weekend Relax", Oferta.TipOferta.WEEKEND, 10.0);
        rezervare = new Rezervare(1,
                LocalDate.of(2025, 7, 10),
                LocalDate.of(2025, 7, 14));
    }

    // ================================================================
    // 1. TESTE - getOferteAplicate() (Pasul 1 din diagrama)
    // ================================================================

    @Test
    @Order(1)
    @DisplayName("Client nou — lista ofertelor este goala")
    void testClientNouListaOferteGoala() {
        assertTrue(client.getOferteAplicate().isEmpty(),
                "Un client nou nu trebuie sa aiba oferte");
    }

    @Test
    @Order(2)
    @DisplayName("Client cu oferta adaugata — lista nu este goala")
    void testClientCuOfertaListaNuEGoala() {
        client.adaugaOferta(oferta);
        assertFalse(client.getOferteAplicate().isEmpty(),
                "Dupa adaugare, clientul trebuie sa aiba oferte");
    }

    // ================================================================
    // 2. TESTE - aplicaReducere() — ramura DA (Pasul 2 din diagrama)
    // ================================================================

    @Test
    @Order(3)
    @DisplayName("Reducere 10% aplicata corect")
    void testAplicaReducere10Procente() {
        double pretRedus = oferta.aplicaReducere(800.0);
        assertEquals(720.0, pretRedus, 0.001,
                "800 RON cu reducere 10% = 720 RON");
    }

    @Test
    @Order(4)
    @DisplayName("Reducere 25% aplicata corect")
    void testAplicaReducere25Procente() {
        Oferta o = new Oferta(2, "Last Minute", Oferta.TipOferta.LAST_MINUTE, 25.0);
        double pretRedus = o.aplicaReducere(1400.0);
        assertEquals(1050.0, pretRedus, 0.001,
                "1400 RON cu reducere 25% = 1050 RON");
    }

    @ParameterizedTest
    @Order(5)
    @DisplayName("Reduceri diverse aplicate corect")
    @CsvSource({
            "800.0,  10.0, 720.0",
            "1000.0, 15.0, 850.0",
            "500.0,  50.0, 250.0",
            "200.0,   0.0, 200.0",
            "200.0, 100.0,   0.0"
    })
    void testReduceriParametrizate(double pret, double reducere, double asteptat) {
        Oferta o = new Oferta(99, "Test", Oferta.TipOferta.SEJUR, reducere);
        assertEquals(asteptat, o.aplicaReducere(pret), 0.001);
    }

    // ================================================================
    // 3. TESTE - calculeazaPret() — ramura NU + pret final (Pasul 3)
    // ================================================================

    @Test
    @Order(6)
    @DisplayName("Pret fara reducere — client fara oferte")
    void testClientFaraOfertePretNeschimbat() {
        double pretInitial = 800.0;
        double pretFinal = client.aplicaOfertaLaRezervare(pretInitial);
        assertEquals(pretInitial, pretFinal, 0.001,
                "Fara oferte, pretul ramane nemodificat");
    }

    @Test
    @Order(7)
    @DisplayName("Calculul pretului pentru 4 nopti la 200 RON/noapte")
    void testCalculPretNormal() {
        double pret = rezervare.calculeazaPret(200.0);
        assertEquals(800.0, pret, 0.001,
                "4 nopti * 200 RON = 800 RON");
    }

    @Test
    @Order(8)
    @DisplayName("Calculul pretului pentru 1 noapte")
    void testCalculPretONoapte() {
        Rezervare r = new Rezervare(2,
                LocalDate.of(2025, 8, 1),
                LocalDate.of(2025, 8, 2));
        assertEquals(200.0, r.calculeazaPret(200.0), 0.001,
                "1 noapte * 200 RON = 200 RON");
    }

    @ParameterizedTest
    @Order(9)
    @DisplayName("Calculul pretului pentru diverse durate si preturi")
    @CsvSource({
            "2025-06-01, 2025-06-03, 150.0, 300.0",
            "2025-06-01, 2025-06-08, 200.0, 1400.0",
            "2025-06-01, 2025-06-02, 350.0, 350.0",
            "2025-06-01, 2025-06-11, 100.0, 1000.0"
    })
    void testCalculPretParametrizat(String start, String final_, double pret, double asteptat) {
        Rezervare r = new Rezervare(99,
                LocalDate.parse(start),
                LocalDate.parse(final_));
        assertEquals(asteptat, r.calculeazaPret(pret), 0.001);
    }

    // ================================================================
    // 4. TESTE - adaugaRezervare() (Pasul 4 din diagrama)
    // ================================================================

    @Test
    @Order(10)
    @DisplayName("Rezervarea este adaugata la client")
    void testAdaugaRezervareClient() {
        client.adaugaRezervare(rezervare);
        assertEquals(1, client.getRezervari().size(),
                "Clientul trebuie sa aiba 1 rezervare dupa confirmare");
    }

    @Test
    @Order(11)
    @DisplayName("Rezervare noua are statusul activa")
    void testRezervareNouaEsteActiva() {
        assertEquals("activa", rezervare.getStatus(),
                "O rezervare noua trebuie sa aiba statusul 'activa'");
        assertNull(rezervare.getPlata(),
                "O rezervare noua nu trebuie sa aiba plata");
    }

    // ================================================================
    // 5. TESTE - ocupa() (Pasul 5 din diagrama)
    // ================================================================

    @Test
    @Order(12)
    @DisplayName("Camera devine ocupata dupa confirmare rezervare")
    void testCameraOcupataDupaRezervare() {
        camera.ocupa();
        assertFalse(camera.verificaDisponibilitatea(),
                "Camera trebuie sa fie ocupata dupa rezervare");
        assertEquals("ocupata", camera.getStatus());
    }

    @Test
    @Order(13)
    @DisplayName("Nu se poate ocupa o camera deja ocupata")
    void testNuSePoateOcupaOCameraOcupata() {
        camera.ocupa();
        camera.ocupa();
        assertEquals("ocupata", camera.getStatus(),
                "Statusul ramane 'ocupata', nu se dubleaza");
    }

    // ================================================================
    // 6. TESTE - efectueazaPlata() (Pasul 6 din diagrama)
    // ================================================================

    @Test
    @Order(14)
    @DisplayName("Plata se efectueaza cu succes")
    void testEfectueazaPlataSucces() {
        rezervare.efectueazaPlata(720.0, "card");
        assertNotNull(rezervare.getPlata(),
                "Plata trebuie sa fie creata dupa efectuare");
        assertEquals("platita", rezervare.getStatus());
    }

    @Test
    @Order(15)
    @DisplayName("Suma platii este corecta")
    void testSumaPlatiCorecta() {
        rezervare.efectueazaPlata(720.0, "card");
        assertEquals(720.0, rezervare.getPlata().getSuma(), 0.001);
    }

    @Test
    @Order(16)
    @DisplayName("Metoda platii este corecta")
    void testMetodaPlataCorecta() {
        rezervare.efectueazaPlata(720.0, "numerar");
        assertEquals("numerar", rezervare.getPlata().getMetoda());
    }

    // ================================================================
    // 7. TESTE INTEGRATE — fluxul complet din diagrama
    // ================================================================

    @Test
    @Order(17)
    @DisplayName("Flux complet — ramura DA: client cu oferta activa")
    void testFluxCompletCuOferta() {
        // Pasul 1: verifica ofertele
        client.adaugaOferta(oferta);
        assertFalse(client.getOferteAplicate().isEmpty());

        // Pasul 2 (DA): aplica reducerea
        double pretInitial = 800.0;
        double pretRedus = oferta.aplicaReducere(pretInitial);
        assertEquals(720.0, pretRedus, 0.001);

        // Pasul 3: calculeaza pretul final
        double pretFinal = rezervare.calculeazaPret(camera.getPret()); // 4 * 200 = 800
        double pretFinalCuOferta = client.aplicaOfertaLaRezervare(pretFinal);
        assertEquals(720.0, pretFinalCuOferta, 0.001);

        // Pasul 4: clientul confirma rezervarea
        client.adaugaRezervare(rezervare);
        assertEquals(1, client.getRezervari().size());

        // Pasul 5: camera marcata ocupata
        camera.ocupa();
        assertFalse(camera.verificaDisponibilitatea());
        assertEquals("ocupata", camera.getStatus());

        // Pasul 6: efectueaza plata
        rezervare.efectueazaPlata(pretFinalCuOferta, "card");
        assertEquals("platita", rezervare.getStatus());
        assertNotNull(rezervare.getPlata());
        assertEquals(720.0, rezervare.getPlata().getSuma(), 0.001);
    }

    @Test
    @Order(18)
    @DisplayName("Flux complet — ramura NU: client fara oferte")
    void testFluxCompletFaraOferta() {
        // Pasul 1: verifica ofertele — lista goala
        assertTrue(client.getOferteAplicate().isEmpty());

        // Pasul 2 (NU): calculeaza pret fara reducere
        double pretFinal = rezervare.calculeazaPret(camera.getPret()); // 4 * 200 = 800
        double pretFinalFaraOferta = client.aplicaOfertaLaRezervare(pretFinal);
        assertEquals(pretFinal, pretFinalFaraOferta, 0.001);

        // Pasul 4: clientul confirma rezervarea
        client.adaugaRezervare(rezervare);
        assertEquals(1, client.getRezervari().size());

        // Pasul 5: camera marcata ocupata
        camera.ocupa();
        assertFalse(camera.verificaDisponibilitatea());

        // Pasul 6: efectueaza plata
        rezervare.efectueazaPlata(pretFinalFaraOferta, "card");
        assertEquals("platita", rezervare.getStatus());
        assertEquals(800.0, rezervare.getPlata().getSuma(), 0.001);
    }
}