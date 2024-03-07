package ex1;
import java.util.List;

public class Parabola {
    private int a;
    private int b;
    private int c;

    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    public double[] calculVarf() {//inlocuim cu return new Punc(...)
        double xVarf = -b / (2.0 * a);
        double yVarf = (-b * b + 4 * a * c) / (4.0 * a);
        return new double[]{xVarf, yVarf};
    }

    public static double[] mijlocSegment(Parabola p1, Parabola p2) {
        double[] varfP1 = p1.calculVarf();
        double[] varfP2 = p2.calculVarf();
        double xMijloc = (varfP1[0] + varfP2[0]) / 2;
        double yMijloc = (varfP1[1] + varfP2[1]) / 2;
        return new double[]{xMijloc, yMijloc};
    }

    public static double lungimeSegment(Parabola p1, Parabola p2) {
        double[] varfP1 = p1.calculVarf();
        double[] varfP2 = p2.calculVarf();
        return Math.hypot(varfP2[0] - varfP1[0], varfP2[1] - varfP1[1]);
    }

    //se mai cereau inca 2 metode
}
