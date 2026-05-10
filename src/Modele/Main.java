package Modele;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("========== SISTEM MANAGEMENT HOTEL ==========\n");

        // --- Creare Administrator ---
        Administrator admin = new Administrator("Andrei", 5000, "Manager");

        // --- Creare Camere si adaugare la Administrator ---
        Camera c1 = new Camera(1, 101, 200.0, Camera.TipCamera.DOUBLE);
        Camera c2 = new Camera(2, 102, 350.0, Camera.TipCamera.SUITE);
        Camera c3 = new Camera(3, 103, 150.0, Camera.TipCamera.SINGLE);

        admin.adaugaCamera(c1);
        admin.adaugaCamera(c2);
        admin.adaugaCamera(c3);
        admin.afisareCamere();

        System.out.println();

        // --- Modificare date camera ---
        System.out.println("========== MODIFICARE DATE CAMERA ==========");
        admin.modificaDateCamera(101, 250.0, "in_mentenanta");
        admin.afisareCamere();

        System.out.println();

        // --- Creare Oferte si adaugare la Administrator ---
        Oferta o1 = new Oferta(1, "Oferta Weekend Relax", Oferta.TipOferta.WEEKEND, 10.0);
        Oferta o2 = new Oferta(2, "Last Minute Summer", Oferta.TipOferta.LAST_MINUTE, 25.0);
        Oferta o3 = new Oferta(3, "Luna de Miere Deluxe", Oferta.TipOferta.LUNA_DE_MIERE, 15.0);

        admin.adaugaOferta(o1);
        admin.adaugaOferta(o2);
        admin.adaugaOferta(o3);
        admin.afisareOferte();

        System.out.println();

        // --- Creare Clienti si inregistrare la Administrator ---
        Client client1 = new Client(1, "Elena Popescu", "elena@gmail.com", "0722000001");
        Client client2 = new Client(2, "Mihai Ionescu", "mihai@yahoo.com", "0733000002");

        admin.inregistreazaClient(client1);
        admin.inregistreazaClient(client2);
        admin.afisareClienti();

        System.out.println();

        // --- Asociere Oferte -> Clienti ---
        client1.adaugaOferta(o1);
        client1.adaugaOferta(o3);
        client2.adaugaOferta(o2);

        System.out.println();
        client1.afisareOferte();
        client2.afisareOferte();

        System.out.println();

        // --- Verificare disponibilitate camere ---
        c1.verificaDisponibilitatea();
        c2.verificaDisponibilitatea();

        System.out.println();

        // --- Rezervare pentru client1 cu oferta aplicata ---
        Rezervare r1 = new Rezervare(1, LocalDate.of(2025, 7, 10), LocalDate.of(2025, 7, 14));
        double pretBaza = r1.calculeazaPret(c1.getPret());
        System.out.println("Pret baza rezervare: " + pretBaza + " RON");

        double pretFinal = client1.aplicaOfertaLaRezervare(pretBaza);
        System.out.println("Pret final dupa oferta: " + pretFinal + " RON");

        r1.efectueazaPlata(pretFinal, "card");
        client1.adaugaRezervare(r1);
        c1.ocupa();

        System.out.println();

        // --- Rezervare pentru client2 cu oferta specifica ---
        Rezervare r2 = new Rezervare(2, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 5));
        double pretBaza2 = r2.calculeazaPret(c2.getPret());
        System.out.println("Pret baza rezervare: " + pretBaza2 + " RON");

        double pretFinal2 = client2.aplicaOfertaSpecifica(2, pretBaza2);
        System.out.println("Pret final dupa oferta: " + pretFinal2 + " RON");

        r2.efectueazaPlata(pretFinal2, "numerar");
        client2.adaugaRezervare(r2);
        c2.ocupa();

        System.out.println();

        // --- Afisare finala ---
        System.out.println("========== SUMAR FINAL ==========");
        admin.afisare();
        System.out.println();
        client1.afisare();
        client1.afisareRezervari();
        System.out.println();
        client2.afisare();
        client2.afisareRezervari();

        System.out.println();

        // --- Eliberare camera dupa checkout ---
        c1.elibereaza();
        c2.elibereaza();

        // --- Stergere oferta si client demo ---
        admin.stergeOferta(3);
        admin.eliminaClient(2);
        System.out.println();
        admin.afisare();
    }
}