package Modele;

public class Camera {

    public enum TipCamera {
        SINGLE, DOUBLE, SUITE, APARTAMENT, DELUXE
    }

    private int id;
    private int numar;
    private double pret;         // pret pe noapte (RON)
    private TipCamera tip;
    private String status;       // "disponibila" / "ocupata" / "in_mentenanta"

    public Camera(int id, int numar, double pret, TipCamera tip) {
        this.id = id;
        this.numar = numar;
        this.pret = pret;
        this.tip = tip;
        this.status = "disponibila";
    }

    /**
     * Verifica daca camera este disponibila pentru rezervare.
     */
    public boolean verificaDisponibilitatea() {
        boolean disponibila = status.equals("disponibila");
        System.out.println("Camera " + numar + " (" + tip + ") este "
                + (disponibila ? "DISPONIBILA" : "INDISPONIBILA - status: " + status));
        return disponibila;
    }

    /**
     * Marcheaza camera ca ocupata (la creare rezervare).
     */
    public void ocupa() {
        if (status.equals("disponibila")) {
            status = "ocupata";
            System.out.println("Camera " + numar + " a fost marcata ca ocupata.");
        } else {
            System.out.println("Camera " + numar + " nu poate fi ocupata - status curent: " + status);
        }
    }

    /**
     * Elibereaza camera (la finalizarea rezervarii).
     */
    public void elibereaza() {
        status = "disponibila";
        System.out.println("Camera " + numar + " a fost eliberata si este din nou disponibila.");
    }

    /**
     * Pune camera in mentenanta.
     */
    public void intraMentenanta() {
        status = "in_mentenanta";
        System.out.println("Camera " + numar + " a intrat in mentenanta.");
    }

    // Getteri si setteri

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public TipCamera getTip() {
        return tip;
    }

    public void setTip(TipCamera tip) {
        this.tip = tip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void afisare() {
        System.out.println("Camera #" + numar + " | Tip: " + tip
                + " | Pret/noapte: " + pret + " RON | Status: " + status);
    }
}