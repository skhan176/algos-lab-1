// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.
//this is usual
// You are encouraged to make helper functions!
import java.util.Arrays;
public class Robbery {

	// Using DP: Get the maximum value with capacity C and n items
	public int maximizeRobWorthRecur(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		// This is the base case at the time
        //there is no more items to compare
        if (capacity <= 0 ) {
        return 0;
        }
        else {
        	  // size of  item of the arrays
        	 int number = sizes.length;
        
        	if( number== 0) 
            return 0;
		//when the items are more than the capacity
		//no more items can not be added in the sack
		if (sizes[number - 1] > capacity) {
            return maximizeRobWorthRecur(capacity, Arrays.copyOf(sizes, number - 1), Arrays.copyOf(worths, number - 1));
        } 
		//two cases:
		//include the n-th item in the sack
		//do not include it in the sack
		//such that the sack has items whose worth is "maximized"
		
        else {
            return Math.max(worths[number - 1] + maximizeRobWorthRecur(capacity - sizes[number - 1], Arrays.copyOf(sizes, number - 1), Arrays.copyOf(worths, number - 1)),
                    maximizeRobWorthRecur(capacity, Arrays.copyOf(sizes, number - 1), Arrays.copyOf(worths, number - 1)));
        }
	}
	}
	// we use bottom up approach 
    // solving the whole sub-problem we will look this up in the table

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		int p, q, number = sizes.length,m1,m2 ;
        int[][] maxWorth = new int[number+1][capacity+1];
       
        for(p=0;p<=number;p++) {
            for(q=0;q<=capacity;q++) {
                maxWorth[p][q] = 0;
            }
        }
       
        for(p=1;p<=number;p++){
            for(q=1;q<=capacity;q++) {
                m1 = 0;
                if( q - sizes[p-1] >= 0) {
                    m1 = maxWorth[p-1][q - sizes[p-1]] + worths[p-1];
                }
                m2 = maxWorth[p-1][q];
                maxWorth[p][q] = Math.max(m1, m2);
            }
           
        }
       // return maxWorth
        return maxWorth[number][capacity];
	}

/**
* Bonus: figure out which items exactly
* Takes in a DP DPTable
* Returns an int array of the individual worths of the items you took
*/
	public int[] takeRobInventory(int[][] DPTable) {
		// fill in here, change the return
		return new int[DPTable.length];
	}
	//main method

	public static void main(String[] args) {
		//created object
		Robbery r = new Robbery();
		//initialize the bagCapacity
		int bagCapacity = 40;
		 //the itemSizes in array
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
		 //the itemWorths in array
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};
		// initialize number
		int number=itemSizes.length;
		  //Print the max worth of the bag using recursion.

		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		System.out.println("");
		     // Print the max worth of the using dp table.
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);

		// Bonus: Fill in the helper method takeRobInventory that could help you
		//figure out which items go into the bag that make it max worth. Feel free
		//to change up the parameters and returned types + values of the helper
		// methods above.
		// int[] itemsPicked = r.takeRobInventory();
		// System.out.println("The items we take are worth:")
		// for (int i = 0; i < results.length; i++) {
		// 	System.out.print(results[i] + " ");
	}
}
