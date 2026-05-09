package Modele;

public class Client extends Persoana {
    private int id;
    private String email;
    private String telefon;

    public Client(int id, String nume, String email, String telefon) {
        super(nume);
        this.id = id;
        this.email = email;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public void afisare() {
        System.out.println("Client: " + nume + ", Email: " + email + ", Telefon: " + telefon);
    }
}