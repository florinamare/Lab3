package ex1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        List<Parabola> parabole = new ArrayList<>();

        try {
            File file = new File("src/ex1/in.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) { //citim direct intregi din fisier
                String line = scanner.nextLine();
                String[] coefficients = line.split(" ");
                int a = Integer.parseInt(coefficients[0]);
                int b = Integer.parseInt(coefficients[1]);
                int c = Integer.parseInt(coefficients[2]);
                Parabola p = new Parabola(a, b, c);
                parabole.add(p);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul nu a fost găsit.");
            e.printStackTrace();
        }
//apelam si cele 2 metode noi

        for (Parabola p : parabole) {
            System.out.println(p);
            double[] varf = p.calculVarf();
            System.out.println("Vârful parabolei: (" + varf[0] + ", " + varf[1] + ")");
        }

        if (parabole.size() >= 2) {
            Parabola p1 = parabole.get(0);
            Parabola p2 = parabole.get(1);
            double[] mijloc = Parabola.mijlocSegment(p1, p2);
            System.out.println("Mijlocul segmentului care unește vârfurile a două parabole: (" + mijloc[0] + ", " + mijloc[1] + ")");
            double lungime = Parabola.lungimeSegment(p1, p2);
            System.out.println("Lungimea segmentului care unește vârfurile a două parabole: " + lungime);
        }
    }
}
