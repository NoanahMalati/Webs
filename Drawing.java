//package Experiments;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.io.*;
import java.util.*;


import java.awt.Container;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
//import javax.swing.JFrame;


public class Drawing extends Canvas {
    public static void main(String[] args) {
        //set up the canvas
        JFrame frame = new JFrame("Spline");
        Canvas canvas = new Drawing();
        canvas.setSize(600, 600);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }


    //read the user input and call the draw polygon method based on the input
    public void paint(Graphics g) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int edge =0; 
        //not stop until the user inputs an integer
        while (true) {
            System.out.println("Enter the number of edge.");
            String input = myObj.next();
            try {
                edge = Integer.parseInt(input);
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Input is not a number, continue");
            }
        }
        
        int[] array = new int[edge];
        //read the input from e_1 to e_n based on user input
        for(int i =1; i<= array.length; i++){
            while (true) {
                System.out.println("Enter e"+i);
                String input = myObj.next();
                try {
                    array[i-1] = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException ne) {
                    System.out.println("Input is not a number, continue");
                }
            }
        }
        for(int i =0; i< edge; i++){
            System.out.print(array[i]);
        }
        //decide whehter include zero or not
        boolean choice = true;
        //call the draw polygon method
        drawPolygons(g, array, choice);
    }

    // draws a regular polygon with n sides, centered at (cx,cy), with radius r
    // draw the edge values and vertice value based on the basic of spline 
    public static void regularPolygon(Graphics graphics, int[] array, int cx, int cy, int r, int[] vertice) {
        int n =array.length;
        double theta=(2*Math.PI)/n;
        for(int i=1; i< n+1; i++){
          graphics.drawLine((int)(cx+r*Math.cos(i*theta)), (int)(cy+r*Math.sin(i*theta)), (int)(cx+r*Math.cos((i+1)*theta)), (int)(cy+r*Math.sin((i+1)*theta)));
      
          if(graphics instanceof Graphics2D){
            Graphics2D g2 = (Graphics2D)graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            //g2.setColor(Color.Black);
            g2.drawString(String.valueOf(vertice[i-1]),(int)(cx+r*Math.cos(i*theta)), (int)(cy+r*Math.sin(i*theta))); 
            //g2.setColor(Color.Blue);
            g2.drawString(String.valueOf(array[i-1]),(int)(cx+r*Math.cos((i+0.5)*theta)), (int)(cy+r*Math.sin((i+0.5)*theta))); 
            }
        }
      }
  
  
    public static void drawPolygons(Graphics graphics, int[] array, boolean choice){
      int n= array.length;
      int x= 0;
      int y= 0; 
      int[][] vertices =findBasic(array, choice);
      for(var i = 0; i< n; i++){
        if (75+x*125>400){
          y+=1;
          x=0;
        }
        regularPolygon(graphics, array, 75+x*150, 75+y*180, 50, vertices[i]);
        x++;
      }
    }


    public static int[][] findBasic(int[] array, boolean choice){
        int n= array.length;
        //System.out.println(array);

        //ArrayList<ArrayList<Integer>> basic = new ArrayList<ArrayList<Integer>>();
        int[][] basic =new int[n][n];
        // for (var i = 0; i < array.length; i++) {
        //   basic[i]=new int[array.length];
        // }
        for (int i = 1; i <= array.length; i++) {
          basic[0][i-1]=1;
          basic[n-1][i-1]=0;
        }
        basic[n-1][n-1]= lcm(array[n-2], array[n-1]);
    
        for(int i =1; i< array.length-1;  i++){
          for(int j =0; j<i; j++){
            basic[i][j]=0;
          }
          int num =0;
          //var times = 0;
          for(int j =i; j<n-2; j++){
    
            if(num == 0){
              num+= array[j-1];
            }
            else if(num > 0){
              num = minus(num, array[j-1], choice);
            }
            else{
              num = addition(num, array[j-1], choice);
            }
            // if(times%2==0){
            //   num += array[j-1];
            // }
            // else{
            //   num -= array[j-1];
            // }
            // times++
            // if (num ==0){
            //   num += array[j-1]
            // }
            basic[i][j]=num;
          }
          int num1;
          boolean br= false;
          boolean be= false;
          int num2;
          int plus_minus_num1=99; 
          int plus_minus_num2=99;
          int plus_plus_num1=99;
          int plus_plus_num2=99;
          int minus_plus_num1=99;
          int minus_plus_num2=99;
          int minus_minus_num1=99;
          int minus_minus_num2=99;
        //   int plus_possible_num1;
        //   int minus_possible_num1;
          int[] num1_plus = possible_plus_num1(num, array[n-2], array[n-3]);
        //   for (int z=0; z<num1_plus.length; z++){
        //     System.out.println(num1_plus[z]);
        //   }

          for(int x=1; x<=array[n-2]; x++){
            num1= num1_plus[x-1];
            //console.log(num1)
            for(int y=1; y<=array[n-1]; y++){
              num2= num1-array[n-2]*y;
            //   System.out.print(1);
            //   System.out.print(" "+num2);
            //   System.out.print(" "+array[n-2]+" ");
              if(num2%array[n-1]==0 && !((!choice) && (num1==0 || num2==0) )){
                plus_minus_num1=num1;
                plus_minus_num2=num2;
                System.out.print("a");
                br= true;
                break;
              }
            }
            for(var y=1; y<=array[n-1]; y++){
              num2= num1+array[n-2]*y;
              if(num2%array[n-1]==0 && !((!choice) && (num1==0 || num2==0) )){
                plus_plus_num1=num1;
                plus_plus_num2=num2;
                be= true;
                break;
              }
            }
            if(br&&be){
                System.out.print(plus_minus_num1);
              break;
            }
          }
          System.out.print("   "+plus_minus_num1);
          boolean bt=false;
          boolean bw=false;
          int[] num1_minus= possible_minus_num1(num, array[n-2], array[n-3]);
          for(var x=1; x<=array[n-2]; x++){
            num1= num1_minus[x-1];
            for(var y=1; y<=array[n-1]; y++){
              num2= num1+array[n-2]*y;
              if(num2%array[n-1]==0 && !((!choice) && (num1==0 || num2==0) )){
                //num1!=0 && num2!=0
                minus_plus_num1=num1;
                minus_plus_num2=num2;
                bt= true;
                break;
              }
            }
            for(var y=1; y<=array[n-1]; y++){
              num2= num1-array[n-2]*y;
              //console.log(num2)
              //console.log("   "+array[n-1])
              if(num2%array[n-1]==0 && !((!choice) && (num1==0 || num2==0) )){
                minus_minus_num1=num1;
                minus_minus_num2=num2;
                bw= true;
                break;
              }
            }
            
            if(bt&&bw){
              break;
            }
          }

          int plus_plus = Math.abs(plus_plus_num1)+Math.abs(plus_plus_num2);
          int plus_minus = Math.abs(plus_minus_num1)+Math.abs(plus_minus_num2);
          int minus_minus = Math.abs(minus_minus_num1)+Math.abs(minus_minus_num2);
          int minus_plus = Math.abs(minus_plus_num1)+Math.abs(minus_plus_num2);
          int plus_min = Math.min(plus_plus, plus_minus);
          int minus_min = Math.min(minus_minus,minus_plus);
        //   System.out.println(plus_plus);
        //   System.out.println(minus_minus);
        //   System.out.println(plus_minus);
        //   System.out.println(minus_plus);
          int min = Math.min(plus_min,minus_min);
          //console.log(min)
    
           int[] cho = {Math.abs(plus_plus_num1)+Math.abs(plus_plus_num2), Math.abs(plus_minus_num1)+Math.abs(plus_minus_num2),Math.abs(minus_minus_num1)+Math.abs(minus_minus_num2),Math.abs(minus_plus_num1)+Math.abs(minus_plus_num2)};
    
    
          //  console.log(minus_minus_num1)
          //  console.log(minus_minus_num2)
      
          // console.log(minus_plus_num1)
          // console.log(minus_plus_num2)
          
          //console.log(cho)
    
          
          if(min==Math.abs(plus_plus_num1)+Math.abs(plus_plus_num2)){
            basic[i][n-2]=plus_plus_num1;
            basic[i][n-1]=plus_plus_num2;
            
          }
          else if(min==Math.abs(plus_minus_num1)+Math.abs(plus_minus_num2)){
            basic[i][n-2]=plus_minus_num1;
            basic[i][n-1]=plus_minus_num2;
          }
          else if(min==Math.abs(minus_minus_num1)+Math.abs(minus_minus_num2)){
            basic[i][n-2]=minus_minus_num1;
            basic[i][n-1]=minus_minus_num2;
          }
          else{
            basic[i][n-2]=minus_plus_num1;
            basic[i][n-1]=minus_plus_num2;
          }
          
    
          
        }
        for (int i = 0; i< n; i++){
            //console.log(basic[i])
          }
        return basic;
      }


      public static int addition(int num, int array_num, boolean choice){
        int new_num= num+array_num;
        if(Math.abs(new_num)> Math.abs(num)){
          if((!choice) && num==0){
            return new_num;
          }
          return num;
        }
        return addition(new_num, array_num, choice);
      }
    
    
      public static int minus(int num, int array_num, boolean choice){
        //console.log(choice)
        int new_num= num-array_num;
        //console.log(new_num)
        if(Math.abs(new_num)> Math.abs(num)){
          if((!choice) && num ==0){
            //console.log(true)
            return new_num;
          }
          return num;
        }
        return minus(new_num, array_num, choice);
      }


      public static int[] possible_plus_num1(int num, int n_2, int n_3){
        ArrayList<Integer> array = new ArrayList<Integer> ();
        for (int x=1; x<=n_2; x++){
          array.add(num+n_3*x);
        }
        array= rearrange(array, array.size(), 0);
        int[] arr = new int[array.size()];
       for (int i =0; i< array.size(); i++){
            arr[i]= array.get(i);
       }
     //console.log(array)
        return arr;
      }

    public static int[] possible_minus_num1(int num, int n_2, int n_3){
        ArrayList<Integer> array = new ArrayList<Integer> ();
        for (int x=1; x<=n_2; x++){
          array.add(num-n_3*x);
        }
        array= rearrange(array, array.size(), 0);
       //console.log(array)
       int[] arr = new int[array.size()];
       for (int i =0; i< array.size(); i++){
            arr[i]= array.get(i);
       }
        return arr;
      }

    public static ArrayList<Integer> rearrange(ArrayList<Integer> arr, int n, int x)
    {
        TreeMap<Integer, ArrayList<Integer> > m
            = new TreeMap<>();
 
        // Store values in a map with the difference
        // with X as key
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(x - arr.get(i));
            if (m.containsKey(diff)) {
                ArrayList<Integer> al = m.get(diff);
                al.add(arr.get(i));
                m.put(diff, al);
            }
            else {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(arr.get(i));
                m.put(diff, al);
            }
        }
 
        // Update the values of array
        int index = 0;
        for (Map.Entry entry : m.entrySet()) {
            ArrayList<Integer> al = m.get(entry.getKey());
            for (int i = 0; i < al.size(); i++)
                //arr[index++] = al.get(i);
                arr.set(index++, al.get(i));
        }
        return arr;
    }


    public static int gcd(int x,int y) { 
        x = Math.abs(x);
        y = Math.abs(y);
        int gcd =1;
        for(int i = 1; i <= x && i <= y; i++)  {  
            //returns true if both conditions are satisfied   
            if(x%i==0 && y%i==0){
                gcd = i;  
            }
            //storing the variable i in the variable gcd  
            
        }  
        return gcd;
      }
    
      
    public static int lcm(int x,int y) { 
        return x*y/gcd(x,y); 
      }







}
