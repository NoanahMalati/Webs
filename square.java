import java.util.*;
public class square {
    public void square(int e1, int e2, int e3, int e4) {
        System.out.println("[1,1,1,1]");
        //System.out.println(Arrays.toString(findNum(e1,e4,e4,e2,e4,e3,0)));
        System.out.println("[0,"+e1+","+findNum(e1,e4,e4,e2,e4,e3,0)[0]+","+findNum(e1,e4,e4,e2,e4,e3,0)[1]+"]");
        System.out.println("[0,0,"+findNum(e2,e4,e4,e2,e4,e3,0)[0]+","+findNum(e1,e4,e4,e2,e4,e3,0)[1]+"]");
        System.out.println("[0,0,0," +lcm(e3,e4)+ "]");

    }
    public int[] findNum(int num1,int num2,int num2copy,int e2,int e4,int e3,int count){
        //base case
        if((num2-num1)%e3==0){
            return new int[]{num1,num2};
        }
        if(count<15){
            //System.out.println(num2);
            num2+=e4;
            count++;
        }
        else{
            num1+=e2;
            num2=num2copy;
            count=0;
        }
        //System.out.println("num1="+num1+", num2="+num2);
        return findNum(num1, num2,num2copy, e2, e4, e3, count);
    }
    public int lcm(int a,int b){
        int result=a*b;
        for(int i=a*b;i>0;i--){
            if(i%a==0&&i%b==0){
                result=i;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        square ex1 = new square();
        Scanner sc = new Scanner(System.in);
        System.out.print("edge1: ");
        int e1 = sc.nextInt();
        System.out.print("edge2: ");
        int e2 = sc.nextInt();
        System.out.print("edge3: ");
        int e3 = sc.nextInt();
        System.out.print("edge4: ");
        int e4 = sc.nextInt();
        ex1.square(e1,e2,e3,e4);
    }
}
