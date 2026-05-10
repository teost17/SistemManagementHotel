package Teste;

import Modele.Administrator;
import Modele.Camera;
import Modele.Camera.TipCamera;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModificaCameraTest {

    // Test 1 - verifica ca pretul si statusul se modifica corect
    // pentru o camera existenta in lista administratorului
    @Test
    public void testModificaDateCamera_pretSiStatus() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c = new Camera(1, 101, 200.0, TipCamera.SINGLE);
        admin.adaugaCamera(c);
        admin.modificaDateCamera(101, 350.0, "ocupata");
        assertEquals(350.0, c.getPret(), 0.01);
        assertEquals("ocupata", c.getStatus());
    }

    // Test 2 - verifica ca o camera cu numar gresit nu e modificata
    // camera cu numarul 999 nu exista, deci pretul ramane 200
    @Test
    public void testModificaDateCamera_cameraNegasita() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c = new Camera(1, 101, 200.0, TipCamera.SINGLE);
        admin.adaugaCamera(c);
        admin.modificaDateCamera(999, 350.0, "ocupata");
        assertEquals(200.0, c.getPret(), 0.01);
    }

    // Test 3 - verifica ca pretul poate fi setat la 0
    // caz limita - pretul zero e permis
    @Test
    public void testModificaDateCamera_pretZero() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c = new Camera(1, 101, 200.0, TipCamera.DOUBLE);
        admin.adaugaCamera(c);
        admin.modificaDateCamera(101, 0.0, "disponibila");
        assertEquals(0.0, c.getPret(), 0.01);
    }

    // Test 4 - verifica ca statusul poate fi setat la "in_mentenanta"
    // statusuri personalizate sunt permise
    @Test
    public void testModificaDateCamera_statusMentenanta() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c = new Camera(1, 101, 200.0, TipCamera.SUITE);
        admin.adaugaCamera(c);
        admin.modificaDateCamera(101, 200.0, "in_mentenanta");
        assertEquals("in_mentenanta", c.getStatus());
    }

    // Test 5 - verifica ca doar camera corecta e modificata
    // din doua camere, doar camera 102 trebuie modificata
    @Test
    public void testModificaDateCamera_maiMulteCamere() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c1 = new Camera(1, 101, 200.0, TipCamera.SINGLE);
        Camera c2 = new Camera(2, 102, 300.0, TipCamera.DOUBLE);
        admin.adaugaCamera(c1);
        admin.adaugaCamera(c2);
        admin.modificaDateCamera(102, 450.0, "ocupata");
        assertEquals(200.0, c1.getPret(), 0.01);
        assertEquals(450.0, c2.getPret(), 0.01);
    }

    // Test 6 - verifica ca pretul mare e acceptat
    // caz limita superioara - pret foarte mare
    @Test
    public void testModificaDateCamera_pretMare() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c = new Camera(1, 101, 200.0, TipCamera.SUITE);
        admin.adaugaCamera(c);
        admin.modificaDateCamera(101, 9999.99, "disponibila");
        assertEquals(9999.99, c.getPret(), 0.01);
    }

    // Test 7 - verifica ca statusul revine la "disponibila"
    // camera era ocupata si administratorul o elibereaza
    @Test
    public void testModificaDateCamera_statusDisponibil() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        Camera c = new Camera(1, 101, 200.0, TipCamera.SINGLE);
        admin.adaugaCamera(c);
        c.setStatus("ocupata");
        admin.modificaDateCamera(101, 200.0, "disponibila");
        assertEquals("disponibila", c.getStatus());
    }

    // Test 8 - verifica comportamentul cand lista de camere e goala
    // nu exista nicio camera, deci lista ramane vida
    @Test
    public void testModificaDateCamera_faraCamera() {
        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        admin.modificaDateCamera(101, 350.0, "ocupata");
        assertEquals(0, admin.getCamere().size());
    }
}