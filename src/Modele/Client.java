package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Client – extinde Persoana.
 *
 * Relatii de asociatie (conform diagramei UML):
 *   - Client detine o lista de Oferte aplicate  (Client -> * Oferte)
 *   - Client detine o lista de Rezervari        (Client -> * Rezervari)
 */
public class Client extends Persoana {

    private int id;
    private String email;
    private String telefon;

    // Asociatie: Client - Oferta (* la *)
    private List<Oferta> oferteAplicate;

    // Asociatie: Client - Rezervare (1 la *)
    private List<Rezervare> rezervari;

    public Client(int id, String nume, String email, String telefon) {
        super(nume);
        this.id = id;
        this.email = email;
        this.telefon = telefon;
        this.oferteAplicate = new ArrayList<>();
        this.rezervari = new ArrayList<>();
    }

    // ---------------------------------------------------------------
    // Operatii cu Oferte
    // ---------------------------------------------------------------

    /**
     * Adauga o oferta la lista clientului (asociere).
     */
    public void adaugaOferta(Oferta oferta) {
        oferteAplicate.add(oferta);
        System.out.println("Oferta \"" + oferta.getDescriere()
                + "\" a fost adaugata clientului " + nume + ".");
    }

    /**
     * Aplica prima oferta disponibila din lista asupra unui pret dat.
     * Returneaza pretul dupa reducere (sau pretul original daca nu exista oferte).
     */
    public double aplicaOfertaLaRezervare(double pretInitial) {
        if (oferteAplicate.isEmpty()) {
            System.out.println("Clientul " + nume + " nu are oferte disponibile.");
            return pretInitial;
        }
        Oferta oferta = oferteAplicate.get(0);
        double pretFinal = oferta.aplicaReducere(pretInitial);
        oferteAplicate.remove(0); // oferta consumata
        return pretFinal;
    }

    /**
     * Aplica o oferta specifica (dupa id) daca clientul o detine.
     */
    public double aplicaOfertaSpecifica(int idOferta, double pretInitial) {
        for (int i = 0; i < oferteAplicate.size(); i++) {
            if (oferteAplicate.get(i).getId() == idOferta) {
                Oferta oferta = oferteAplicate.get(i);
                double pretFinal = oferta.aplicaReducere(pretInitial);
                oferteAplicate.remove(i);
                return pretFinal;
            }
        }
        System.out.println("Oferta #" + idOferta + " nu a fost gasita pentru clientul " + nume + ".");
        return pretInitial;
    }

    public void afisareOferte() {
        if (oferteAplicate.isEmpty()) {
            System.out.println("Clientul " + nume + " nu are oferte active.");
            return;
        }
        System.out.println("=== Oferte active pentru " + nume + " ===");
        oferteAplicate.forEach(Oferta::afisare);
    }

    public List<Oferta> getOferteAplicate() {
        return oferteAplicate;
    }

    // ---------------------------------------------------------------
    // Operatii cu Rezervari
    // ---------------------------------------------------------------

    /**
     * Adauga o rezervare la istoricul clientului.
     */
    public void adaugaRezervare(Rezervare rezervare) {
        rezervari.add(rezervare);
        System.out.println("Rezervarea #" + rezervare.getId()
                + " a fost adaugata pentru clientul " + nume + ".");
    }

    /**
     * Anuleaza o rezervare dupa id (o scoate din lista si schimba statusul).
     */
    public void anuleazaRezervare(int idRezervare) {
        for (Rezervare r : rezervari) {
            if (r.getId() == idRezervare) {
                System.out.println("Rezervarea #" + idRezervare
                        + " a clientului " + nume + " a fost anulata.");
                rezervari.remove(r);
                return;
            }
        }
        System.out.println("Rezervarea #" + idRezervare + " nu a fost gasita.");
    }

    public void afisareRezervari() {
        if (rezervari.isEmpty()) {
            System.out.println("Clientul " + nume + " nu are rezervari.");
            return;
        }
        System.out.println("=== Rezervarile clientului " + nume + " ===");
        rezervari.forEach(Rezervare::afisare);
    }

    public List<Rezervare> getRezervari() {
        return rezervari;
    }

    // ---------------------------------------------------------------
    // Getteri si setteri
    // ---------------------------------------------------------------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    @Override
    public void afisare() {
        System.out.println("Client #" + id + ": " + nume
                + " | Email: " + email
                + " | Telefon: " + telefon
                + " | Rezervari: " + rezervari.size()
                + " | Oferte active: " + oferteAplicate.size());
    }
}