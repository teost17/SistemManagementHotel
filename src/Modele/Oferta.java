package Modele;

public class Oferta {

    public enum TipOferta {
        SEJUR, WEEKEND, LAST_MINUTE, LUNA_DE_MIERE, CORPORATIV
    }

    private int id;
    private String descriere;
    private TipOferta tip;
    private double reducereProcentuala; // ex: 15.0 = 15%

    public Oferta(int id, String descriere, TipOferta tip, double reducereProcentuala) {
        this.id = id;
        this.descriere = descriere;
        this.tip = tip;
        this.reducereProcentuala = reducereProcentuala;
    }

    /**
     * Aplica reducerea ofertei asupra unui pret initial si returneaza pretul redus.
     */
    public double aplicaReducere(double pretInitial) {
        double reducere = pretInitial * (reducereProcentuala / 100.0);
        double pretFinal = pretInitial - reducere;
        System.out.println("Oferta [" + descriere + "] aplicata: -" + reducereProcentuala
                + "% => pret redus de la " + pretInitial + " RON la " + pretFinal + " RON");
        return pretFinal;
    }

    // Getteri si setteri

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public TipOferta getTip() {
        return tip;
    }

    public void setTip(TipOferta tip) {
        this.tip = tip;
    }

    public double getReducereProcentuala() {
        return reducereProcentuala;
    }

    public void setReducereProcentuala(double reducereProcentuala) {
        this.reducereProcentuala = reducereProcentuala;
    }

    public void afisare() {
        System.out.println("Oferta #" + id + " | Tip: " + tip
                + " | Reducere: " + reducereProcentuala + "% | " + descriere);
    }
}