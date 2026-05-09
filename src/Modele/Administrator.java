package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator – extinde Angajat.
 *
 * Relatii de asociatie (conform diagramei UML):
 *   - Administrator gestioneaza o lista de Clienti  (1 Admin -> * Clienti)
 *   - Administrator gestioneaza o lista de Oferte   (1 Admin -> * Oferte)
 *   - Administrator gestioneaza o lista de Camere   (1 Admin -> * Camere)
 */
public class Administrator extends Angajat {

    // Asociatie: Administrator - Client (1 la *)
    private List<Client> clienti;

    // Asociatie: Administrator - Oferta (1 la *)
    private List<Oferta> oferte;

    // Asociatie: Administrator - Camera (1 la *)
    private List<Camera> camere;

    public Administrator(String nume, double salariu, String functie) {
        super(nume, salariu, functie);
        this.clienti = new ArrayList<>();
        this.oferte  = new ArrayList<>();
        this.camere  = new ArrayList<>();
    }

    // ---------------------------------------------------------------
    // Operatii cu Camere
    // ---------------------------------------------------------------

    public void adaugaCamera(Camera camera) {
        camere.add(camera);
        System.out.println("Administratorul " + nume + " a adaugat camera #"
                + camera.getNumar() + " (" + camera.getTip() + ").");
    }

    public void stergeCamera(int numarCamera) {
        boolean eliminata = camere.removeIf(c -> c.getNumar() == numarCamera);
        if (eliminata) {
            System.out.println("Administratorul " + nume
                    + " a sters camera #" + numarCamera + ".");
        } else {
            System.out.println("Camera #" + numarCamera + " nu a fost gasita in lista.");
        }
    }

    public List<Camera> getCamere() {
        return camere;
    }

    public void afisareCamere() {
        if (camere.isEmpty()) {
            System.out.println("Nu exista camere inregistrate.");
            return;
        }
        System.out.println("=== Camere gestionate de " + nume + " ===");
        camere.forEach(Camera::afisare);
    }

    // ---------------------------------------------------------------
    // Operatii cu Oferte
    // ---------------------------------------------------------------

    public void adaugaOferta(Oferta oferta) {
        oferte.add(oferta);
        System.out.println("Administratorul " + nume + " a adaugat oferta: "
                + oferta.getDescriere() + " (" + oferta.getTip() + ").");
    }

    public void stergeOferta(int idOferta) {
        boolean eliminata = oferte.removeIf(o -> o.getId() == idOferta);
        if (eliminata) {
            System.out.println("Oferta #" + idOferta + " a fost stearsa.");
        } else {
            System.out.println("Oferta #" + idOferta + " nu a fost gasita.");
        }
    }

    public List<Oferta> getOferte() {
        return oferte;
    }

    public void afisareOferte() {
        if (oferte.isEmpty()) {
            System.out.println("Nu exista oferte inregistrate.");
            return;
        }
        System.out.println("=== Oferte gestionate de " + nume + " ===");
        oferte.forEach(Oferta::afisare);
    }

    // ---------------------------------------------------------------
    // Operatii cu Clienti
    // ---------------------------------------------------------------

    public void inregistreazaClient(Client client) {
        clienti.add(client);
        System.out.println("Administratorul " + nume + " a inregistrat clientul: "
                + client.getNume() + ".");
    }

    public void eliminaClient(int idClient) {
        boolean eliminat = clienti.removeIf(c -> c.getId() == idClient);
        if (eliminat) {
            System.out.println("Clientul #" + idClient + " a fost eliminat.");
        } else {
            System.out.println("Clientul #" + idClient + " nu a fost gasit.");
        }
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public void afisareClienti() {
        if (clienti.isEmpty()) {
            System.out.println("Nu exista clienti inregistrati.");
            return;
        }
        System.out.println("=== Clienti gestionati de " + nume + " ===");
        clienti.forEach(Client::afisare);
    }

    // ---------------------------------------------------------------

    @Override
    public void afisare() {
        System.out.println("Administrator: " + nume
                + " | Clienti: " + clienti.size()
                + " | Oferte: " + oferte.size()
                + " | Camere: " + camere.size());
    }
}