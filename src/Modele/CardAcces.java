package Modele;

public class CardAcces {
    private int idAngajat;
    private String numar;

    public CardAcces(int idAngajat, String numar) {
        this.idAngajat = idAngajat;
        this.numar = numar;
    }

    public String oferaAcces() {
        return "Card " + numar + " ofera acces angajatului cu id " + idAngajat;
    }

    public int getIdAngajat() { return idAngajat; }
    public String getNumar() { return numar; }

    public void afisare() {
        System.out.println("CardAcces: " + numar + ", AngajatID: " + idAngajat);
    }
}