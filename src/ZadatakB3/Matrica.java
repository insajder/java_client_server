package ZadatakB3;

import java.text.DecimalFormat;

public class Matrica {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static String format(double d)
    {
        DecimalFormat test = new DecimalFormat("##0.000");
        String str = test.format(d);
        String str2 = str.substring(str.length() - 1);
        str = str.substring(0, str.length() - 1);

        return str + '(' + str2 + ')';
    }
    private static double determinanta(double mat[][])
    {
        double resenje;
        resenje = mat[0][0] * (mat[1][1] * mat[2][2] - mat[2][1] * mat[1][2])
                - mat[0][1] * (mat[1][0] * mat[2][2] - mat[1][2] * mat[2][0])
                + mat[0][2] * (mat[1][0] * mat[2][1] - mat[1][1] * mat[2][0]);
        return resenje;
    }
    public  static String  getString;


    static void pronadjiResenjeJednacine(double[][] Matrica) {

        double d[][] = {
                {Matrica[0][0], Matrica[0][1], Matrica[0][2]},
                {Matrica[1][0], Matrica[1][1], Matrica[1][2]},
                {Matrica[2][0], Matrica[2][1], Matrica[2][2]},
        };


        double d1[][] = {
                {Matrica[0][3], Matrica[0][1], Matrica[0][2]},
                {Matrica[1][3], Matrica[1][1], Matrica[1][2]},
                {Matrica[2][3], Matrica[2][1], Matrica[2][2]},
        };


        double d2[][] = {
                {Matrica[0][0], Matrica[0][3], Matrica[0][2]},
                {Matrica[1][0], Matrica[1][3], Matrica[1][2]},
                {Matrica[2][0], Matrica[2][3], Matrica[2][2]},
        };


        double d3[][] = {
                {Matrica[0][0], Matrica[0][1], Matrica[0][3]},
                {Matrica[1][0], Matrica[1][1], Matrica[1][3]},
                {Matrica[2][0], Matrica[2][1], Matrica[2][3]},
        };

        // Calculating Determinant of Matrices d, d1, d2, d3
        double D = determinanta(d);
        double D1 = determinanta(d1);
        double D2 = determinanta(d2);
        double D3 = determinanta(d3);

        ;


        if (D != 0) {

            double x = D1 / D;
            double y = D2 / D;
            double z = D3 / D;

            getString = "x: " +  format(x) + " y: " + format(y) + " z : " +format(z);

        }

        // Case 2
        else {
            if (D1 == 0 && D2 == 0 && D3 == 0) {
                getString= "Sistem ima beskonacno mnogo resenja";

            } else if (D1 != 0 || D2 != 0 || D3 != 0) {
                getString="Sistem jednacina neema resenja";
            }
        }
    }
}


