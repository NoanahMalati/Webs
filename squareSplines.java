import java.util.*;
public class squareSplines{
    public void squareSplines(int a, int b, int c, int d) {
        System.out.println("[1,1,1,1]");
        int v1=0;
        int v2=0;
        int v3=0;
        int v4=0;
        //one zero
        for(int k=-5*c;k<10*c;k++){
            for(int j=-5*b;j<10*b;j++){
                for(int i=a;i<10*a;i+=a){
                    //System.out.println("i="+i+"\n j="+j+"\n k="+k);
                    if(v2*v3*v4==0){
                        if(i%a==0&&(j-i)%b==0&&(k-j)%c==0&&k%d==0){
                            v2=i;
                            v3=j;
                            v4=k;
                        }
                    }
                    else{
                        if((i%a==0&&(j-i)%b==0&&(k-j)%c==0&&k%d==0)&&(Math.abs(v2)+Math.abs(v3)+Math.abs(v4))>(Math.abs(i)+Math.abs(j)+Math.abs(k))){
                            v2=i;
                            v3=j;
                            v4=k;
                        }
                    }
                }
            }
        }
        System.out.println("["+v1+","+v2+","+v3+","+v4+"]");
        //two zeros
        v2=0;
        v3=0;
        v4=0;
        for(int j=-5*c;j<10*c;j++){
            for(int i=-5*b;i<10*b;i+=b){
                if(v3*v4==0){
                    if(i%b==0&&(j-i)%c==0&&j%d==0){
                        v3=i;
                        v4=j;
                    }
                }
                else{
                    if((i%b==0&&(j-i)%c==0&&j%d==0)&&(Math.abs(v3)+Math.abs(v4))>(Math.abs(i)+Math.abs(j))){
                        v3=i;
                        v4=j;
                    }
                }
            }
        }
        System.out.println("["+v1+","+v2+","+v3+","+v4+"]");
        //three zeros
        System.out.println("[0,0,0," +lcm(c,d)+ "]");

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
        squareSplines ex1 = new squareSplines();
        Scanner sc = new Scanner(System.in);
        System.out.print("edge1: ");
        int e1 = sc.nextInt();
        System.out.print("edge2: ");
        int e2 = sc.nextInt();
        System.out.print("edge3: ");
        int e3 = sc.nextInt();
        System.out.print("edge4: ");
        int e4 = sc.nextInt();
        ex1.squareSplines(e1,e2,e3,e4);

    }
}