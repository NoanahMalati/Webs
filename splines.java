import java.util.*;
public class splines {
    public ArrayList<int[]> polygon(int[] e){
        ArrayList<int[]> result=new ArrayList<int[]>();
        int[] arr=new int[e.length];
        int n=e.length;
        arr[0]=e[0];
        for(int i=1;i<n-1;i++){
            arr[i]=e[n-1];
        }
        arr[n-1]=0;
        return polygon(e,n,result,arr,0,0,0);
    }
    public ArrayList<int[]> polygon(int[] e,int n,ArrayList<int[]> result,int[] arr,int count,int indicator,int count2){
        // System.out.println("breaking point");
        // System.out.println("n="+n);
        //base case
        if(n==2){
            // System.out.println("goes here");
            int[] temp=new int[e.length];
            for(int i=0;i<e.length-n+1;i++){
                temp[i]=0;
            }
            temp[e.length-n+1]=lcm(e[0],e[1]);
            // System.out.println(Arrays.toString(temp));
            result.add(temp);
            int[] temp2=new int[e.length];
            for(int i=0;i<temp2.length;i++){
                temp2[i]=1;
            }
            // System.out.println(Arrays.toString(temp2));
            result.add(temp2);
            return result;
        }
        if(arr[0]*arr[1]!=0&&(arr[0]-arr[1])%e[1]==0){
            // System.out.println(Arrays.toString(arr));
            count=0;
            result.add(arr);
            arr=new int[arr.length];
            n--;
            //initial condition for each trial
            arr[0]=e[0];
            for(int i=1;i<n-1;i++){
                arr[i]=e[n-1];
            }
            return polygon(e,n,result,arr,0,0,0);
        }
        System.out.println(n);
        if(count==15){
            if(indicator==0){
                indicator++;
                arr[indicator]+=e[indicator+1];
                arr[0]=e[0];
                for(int i=1;i<indicator;i++){
                    arr[i]=e[n-1];
                }
            }
            else{
                if(count2==15){
                    count2=0;
                    indicator++;
                }
                arr[indicator]+=e[indicator+1];
                arr[0]=e[0];
                for(int i=1;i<indicator;i++){
                    arr[i]=e[n-1];
                }
            }
            count=0;
        }
        arr[0]+=e[0];
        count++;
        return polygon(e,n,result,arr,count,indicator,count2);
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
    public String toString(ArrayList<int[]> spline){
        String str="";
        for(int i=0;i<spline.size();i++){
            str=str+Arrays.toString(spline.get(i))+"\n";
        }
        return str;
    }
    public static void main(String[] args) {
        splines ex1 = new splines();
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack=new Stack<Integer>();
        //take in input edges
        System.out.print("Please type in your edges, type 0 to stop!"+"\n");
        int e=0;
        System.out.print("Your edge: ");
        e = sc.nextInt();
        stack.add(e);
        while(e!=0){
            System.out.print("Your next edge is: ");
            e = sc.nextInt();
            stack.add(e);
        }
        stack.pop();
        //convert the input edges into an array in backward order
        int[] edges=new int[stack.size()];
        for(int i=0;i<edges.length;i++){
            edges[i]=stack.pop();
        }
        // System.out.println(Arrays.toString(edges));
        System.out.println(ex1.toString(ex1.polygon(edges)));
    }
}
