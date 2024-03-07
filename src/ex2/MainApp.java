package ex2;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        List<Produs> listaProduse = new ArrayList<>();

        // Citirea produselor din fișier
        citesteProduse("src/ex2/produse.csv", listaProduse);

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("Meniu:");
            System.out.println("1. Afișare produse");
            System.out.println("2. Afișare produse expirate");
            System.out.println("3. Vânzare produs");
            System.out.println("4. Afișare produse cu preț minim");
            System.out.println("5. Salvare produse cu cantitate mai mică decât o valoare");
            System.out.println("6. Ieșire");

            System.out.print("Alegeți opțiunea: ");
            int optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    afiseazaProduse(listaProduse);
                    break;
                case 2:
                    afiseazaProduseExpirate(listaProduse);
                    break;
                case 3:
                    vindeProdus(scanner, listaProduse);
                    break;
                case 4:
                    afiseazaProduseCuPretMinim(listaProduse);
                    break;
                case 5:
                    salvareProduseCuCantitateMica(scanner, listaProduse);
                    break;
                case 6:
                    continua = false;
                    break;
                default:
                    System.out.println("Opțiune invalidă.");
            }
        }

        // La final, salvăm starea actuală a listei de produse înapoi în fișier (dacă este necesar).
        //salveazaProduse("produse.csv", listaProduse);
    }

    // Metoda pentru citirea produselor din fișier
    private static void citesteProduse(String numeFisier, List<Produs> listaProduse) {
        try (Scanner scanner = new Scanner(new File(numeFisier))) {
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] elemente = linie.split(",");
                String denumire = elemente[0];
                double pret = Double.parseDouble(elemente[1]);
                int cantitate = Integer.parseInt(elemente[2]);
                LocalDate dataExpirare = LocalDate.parse(elemente[3]);//schimbam formatul de data in fisier sa fie zi.luna.an
                listaProduse.add(new Produs(denumire, pret, cantitate, dataExpirare));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul nu a fost găsit.");
        }
    }

    // Metoda pentru afișarea tuturor produselor
    private static void afiseazaProduse(List<Produs> listaProduse) {
        for (Produs produs : listaProduse) {
            System.out.println(produs);
        }
    }

    // Metoda pentru afișarea produselor expirate
    private static void afiseazaProduseExpirate(List<Produs> listaProduse) {
        LocalDate dataCurenta = LocalDate.now();
        for (Produs produs : listaProduse) {
            if (produs.getDataExpirare().isBefore(dataCurenta)) {
                System.out.println(produs);
            }
        }
    }

    // Metoda pentru vânzarea unui produs
    private static void vindeProdus(Scanner scanner, List<Produs> listaProduse) {
        System.out.print("Introduceți denumirea produsului: ");
        String denumire = scanner.next();
        System.out.print("Introduceți cantitatea vândută: ");
        int cantitateVanduta = scanner.nextInt();

        for (Produs produs : listaProduse) {
            if (produs.getDenumire().equalsIgnoreCase(denumire)) {
                produs.vinde(cantitateVanduta);
                break;
            }
        }
    }

    // Metoda pentru afișarea produselor cu preț minim
    private static void afiseazaProduseCuPretMinim(List<Produs> listaProduse) {
        double pretMinim = Double.MAX_VALUE;
        for (Produs produs : listaProduse) {
            if (produs.getPret() < pretMinim) {
                pretMinim = produs.getPret();
            }
        }

        for (Produs produs : listaProduse) {
            if (produs.getPret() == pretMinim) {
                System.out.println(produs);
            }
        }
    }

    // Metoda pentru salvarea produselor cu cantitate mai mică decât o valoare citită de la tastatură
    private static void salvareProduseCuCantitateMica(Scanner scanner, List<Produs> listaProduse) {
        System.out.print("Introduceți cantitatea minimă: ");
        int cantitateMinima = scanner.nextInt();
        try (PrintWriter writer = new PrintWriter(new FileWriter("produse_mici.csv"))) {
            for (Produs produs : listaProduse) {
                if (produs.getCantitate() < cantitateMinima) {
                    writer.println(produs.getDenumire() + "," + produs.getPret() + "," + produs.getCantitate() + "," + produs.getDataExpirare());
                }
            }
            System.out.println("Produsele cu cantitate mai mică de " + cantitateMinima + " au fost salvate în fișierul produse_mici.csv");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea produselor.");
        }
    }

    // Metoda pentru salvarea produselor înapoi în fișier
    private static void salveazaProduse(String numeFisier, List<Produs> listaProduse) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            for (Produs produs : listaProduse) {
                writer.println(produs.getDenumire() + "," + produs.getPret() + "," + produs.getCantitate() + "," + produs.getDataExpirare());
            }
        } catch (IOException e) {
            System.out.println("Eroare la salvarea produselor.");
        }
    }
}

