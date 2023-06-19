import java.util.Scanner;
import java.lang.Math;

public class Square {

    /*
     *   v4 ---- e3 ---- v3
     *   |                |
     *   |                |
     *   e4              e2
     *   |                |
     *   |                |
     *   v1 ---- e1 ---- v2
     */


    public void squareSplines(int e1, int e2, int e3, int e4) {
        
        // 1 on all vertices
        System.out.println("[ 1 1 1 1 ]");

        // Remove v1
        int temp = e1;
        while (temp % e4 != 0) {
            temp += e3; 
        }
        System.out.println("[ 0 " + e1 + " " + e1 + " " + temp + " ]");
        
        // Remove v1, v2
        int temp2 = e2;
        while (temp2 % e4 != 0) {
            temp2 += e3;
        }
        System.out.println("[ 0 0 " + e2 + " " + temp2 + " ]");
        
        //Remove v1, v2, v3
        int lcm = lcm(e3, e4);
        System.out.println("[ 0 0 0 " + lcm + " ]");

    }

    public static int lcm(int a, int b) {
        int ab = a * b;
        int absoluteVal = Math.abs(ab);
        int lcm = absoluteVal / gcd(a, b);
        return lcm;
    }

    public static int gcd(int a, int b) {
        // Making sure that a is larger than b by switching the numbers.
        if (b > a) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static void main(String[] args) {
        Square square = new Square();
        Scanner sc = new Scanner(System.in);
        System.out.print("e1: ");
        int e1 = sc.nextInt();
        System.out.print("e2: ");
        int e2 = sc.nextInt();
        System.out.print("e3: ");
        int e3 = sc.nextInt();
        System.out.print("e4: ");
        int e4 = sc.nextInt();

        square.squareSplines(e1, e2, e3, e4);

    }
}
