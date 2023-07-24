import java.util.ArrayList;
import java.util.List;

//final edge label = num
//prev edge label = increaseBy
public class Remainder {
    public void getRemainder(int finalEdgeLabel, int divisor) {
        int numInitial = finalEdgeLabel;
        for(int prevEdgeLabel = 2; prevEdgeLabel <= 9; prevEdgeLabel++) { 
            List<Integer> remainderList = new ArrayList<>();
            int numOfReps = 0; 
            while(!remainderList.contains(finalEdgeLabel % divisor)) {
                numOfReps++;
                remainderList.add(finalEdgeLabel % divisor);
                finalEdgeLabel += prevEdgeLabel;
            }
            System.out.print("Final Edge Label: " + numInitial + " | Previous Edge Label: " + prevEdgeLabel + " | Divisor: " + divisor + " | Remainder: ");
            for(int element : remainderList) {
                System.out.print(element + " ");
            }
            System.out.print("| # of reps: " + numOfReps + "\n");
        }
    }
        

    public static void main(String[] args) {
        Remainder remainder = new Remainder();
        for(int i = 2; i <= 8; i++) {
            System.out.println("================================================================================================");
            remainder.getRemainder(i, 5);
        }
    }   
}