import java.util.*;
public class pentagon {
    public void pentagon(int a, int b, int c, int d, int e) {
        System.out.println("[1,1,1,1,1]");
        int v1=0;
        int v2=0;
        int v3=0;
        int v4=0;
        int v5=0;
        //one zero
        for(int i=-2*a;i<5*a;i+=a){
            for(int j=i-5*b;j<5*b;j+=b){
                for(int k=j-5*c;k<5*c;k+=c){
                    for(int m=k-5*d;m<5*d;m+=d){
                        if(v2*v3*v4*v5==0){
                            if(i%a==0&&(j-i)%b==0&&(k-j)%c==0&&(k-m)%d==0&&m%e==0){
                                v2=i;
                                v3=j;
                                v4=k;
                                v5=m;
                            }
                        }
                        else{
                            if((i%a==0&&(j-i)%b==0&&(k-j)%c==0&&(k-m)%d==0&&m%e==0)&&(Math.abs(v2)+Math.abs(v3)+Math.abs(v4)+Math.abs(v5))>(Math.abs(i)+Math.abs(j)+Math.abs(k)+Math.abs(m))){
                                v2=i;
                                v3=j;
                                v4=k;
                                v5=m;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("["+v1+","+v2+","+v3+","+v4+","+v5+"]");
        //two zeros
        v2=0;
        v3=0;
        v4=0;
        v5=0;
        for(int i=-2*b;i<5*b;i+=b){
            for(int j=i-5*c;j<5*c;j+=c){
                for(int k=j-5*d;k<5*d;k+=d){
                    if(v3*v4*v5==0){
                        if(i%b==0&&(j-i)%c==0&&(k-j)%d==0&&k%e==0){
                            v3=i;
                            v4=j;
                            v5=k;
                        }
                    }
                    else{
                        if((i%b==0&&(j-i)%c==0&&(k-j)%d==0&&k%e==0)&&(Math.abs(v3)+Math.abs(v4)+Math.abs(v5))>(Math.abs(i)+Math.abs(j)+Math.abs(k))){
                            v3=i;
                            v4=j;
                            v5=k;
                        }
                    }
                }
            }
        }
        System.out.println("["+v1+","+v2+","+v3+","+v4+","+v5+"]");
        //three zeros
        v3=0;
        v4=0;
        v5=0;
        for(int i=-2*c;i<5*c;i+=c){
            for(int j=i-5*d;j<5*d;j+=d){
                if(v4*v5==0){
                    if(i%c==0&&(j-i)%d==0&&j%e==0){
                        v4=i;
                        v5=j;
                    }
                }
                else{
                    if((i%c==0&&(j-i)%d==0&&j%e==0)&&(Math.abs(v4)+Math.abs(v5))>(Math.abs(i)+Math.abs(j))){
                        v4=i;
                        v5=j;
                    }
                }
            }
        }
        System.out.println("["+v1+","+v2+","+v3+","+v4+","+v5+"]");
        //four zeros
        System.out.println("[0,0,0,0,"+lcm(d,e)+"]");

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
        pentagon ex1 = new pentagon();
        Scanner sc = new Scanner(System.in);
        System.out.print("edge1: ");
        int e1 = sc.nextInt();
        System.out.print("edge2: ");
        int e2 = sc.nextInt();
        System.out.print("edge3: ");
        int e3 = sc.nextInt();
        System.out.print("edge4: ");
        int e4 = sc.nextInt();
        System.out.print("edge5: ");
        int e5 = sc.nextInt();
        ex1.pentagon(e1,e2,e3,e4,e5);

    }
}
