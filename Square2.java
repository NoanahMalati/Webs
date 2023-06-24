import java.util.Scanner;

public class Square2 {
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
        int V1 = 0;
        int V2 = e1;
        int[] result = findV3V4(e1, e2, e3, e4);
        System.out.print("[ " + V1 + " " + V2 + " ");
        printIntArray(result);
        System.out.print("]\n");
        
        // Remove v1, v4
        int v1 = 0;
        int v2 = e1;
        int v3 = findV3(e1, e2, e3);
        int v4 = 0;
        System.out.println("[ " + v1 + " " + v2 + " " + v3 + " " + v4 + " ]");

        //Remove v1, v2, v3
        int lcm = lcm(e3, e4);
        System.out.println("[ 0 0 0 " + lcm + " ]");

    }

    public int findV3(int e1, int e2, int e3) {
        int v2 = e1;
        int v3 = v2;
        
        int count = 0;
        for(int i = 2; i < 52; i++) {
            while(count < 15) {
                if(v3 % e3 == 0) {
                    return v3;
                }
                v3 += e2;
                count++;
            }
            v3 = i * v2;
            count = 0;
        }

        return -1;

    }

    public int[] findV3V4(int e1, int e2, int e3, int e4) {
        int v2 = e1;
        int v3 = v2;
        int v4 = e4;
        int temp = v4;
        int[] result = new int[2];

        int count = 0;
        for(int i = 2; i < 52; i++) {
            while(count < 15) {
                if((v4 - v3) % e3 == 0) {
                    result[0] = v3;
                    result[1] = v4;
                    return result;
                }
                v4 += e4;
                count++;
            }
            v3 += e2;
            v4 = temp;
            count = 0;
        }

        return result; //If the numbers are both zero it's false
        
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

    public static void printIntArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        Square2 square2 = new Square2();
        Scanner sc = new Scanner(System.in);
        System.out.print("e1: ");
        int e1 = sc.nextInt();
        System.out.print("e2: ");
        int e2 = sc.nextInt();
        System.out.print("e3: ");
        int e3 = sc.nextInt();
        System.out.print("e4: ");
        int e4 = sc.nextInt();

        square2.squareSplines(e1, e2, e3, e4);

    }
}




