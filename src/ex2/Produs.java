package ex2;
import java.time.LocalDate;

public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirare;

    // Variabila statica pentru incasari
    private static double incasari = 0;

    public Produs(String denumire, double pret, int cantitate, LocalDate dataExpirare) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirare = dataExpirare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    // Metoda pentru vânzare
    public void vinde(int cantitateVanduta) {
        if (cantitateVanduta <= cantitate) {
            incasari += cantitateVanduta * pret;
            cantitate -= cantitateVanduta;
            if (cantitate == 0) {
                System.out.println("Produsul " + denumire + " a fost vândut complet și a fost eliminat din stoc.");
            }
        } else {
            System.out.println("Nu există suficientă cantitate pe stoc pentru a vinde.");
        }
    }

    // Redefinirea metodei toString()
    @Override
    public String toString() {
        return "Produs: " + denumire + ", Preț: " + pret + ", Cantitate: " + cantitate + ", Data expirare: " + dataExpirare;
    }

    // Getter pentru variabila statica incasari
    public static double getIncasari() {
        return incasari;
    }
}

