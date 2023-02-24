

/*
 * Name: Olivia Parker
 * EID: osp257
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program3 extends AbstractProgram3 {

    /**
     * Determines the solution of the optimal response time for the given input TownPlan. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return Updated TownPlan town with the "response" field set to the optimal response time
     */
    @Override
    public TownPlan OptimalResponse(TownPlan town) {
    	int numHouses = town.getHouseCount();
    	int numPartitions = town.getStationCount();//may be wrong
    	Float[] array = town.getPositionHouses().toArray(new Float[0]);
    	Float end = findMax(array, numHouses, numPartitions);
    	town.setResponse(end);
    	
        /* TODO implement this function */
         return town; /* TODO remove this line */
    }

    /**
     * Determines the solution of the set of food station positions that optimize response time for the given input TownPlan. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return Updated TownPlan town with the "position_food_stations" field set to the optimal food station positions
     */
    @Override
    public TownPlan OptimalPosFoodStations(TownPlan town) {
    	
    	int numHouses = town.getHouseCount();
    	int numPartitions = town.getStationCount();//may be wrong
    	Float[] array = town.getPositionHouses().toArray(new Float[0]);
    	ArrayList<Float> end = findMaxLoc(array, numHouses, numPartitions);
    	town.setPositionFoodStations(end);
    	
        /* TODO implement this function */
        return town; /* TODO remove this line */
    }
    
    static Float response(Float arr[], int from, int to)
    {
    	Float total = ((arr[to]-arr[from])/2); 
        return total;
    }
      
    static Float response2(Float arr[], int from, int to)
    {
    	Float total = ((arr[to]-arr[from])/2) + arr[from]; 
        return total;
    }
    static float findMax(Float arr[], int n, int k)
    {
        // initialize table
        Float dp[][] = new Float[k+1][n+1];
      
        // base cases
        // k=1
        for (int i = 1; i <= n; i++)
            dp[1][i] = response(arr, 0, i-1);
      
        // n=1
        for (int i = 1; i <= k; i++)
            dp[i][1] = 0.0f;
      
        // 2 to k partitions
        for (int i = 2; i <= k; i++) { // 2 to n boards
            for (int j = 2; j <= n; j++) {
      
                // track minimum
                Float best = Float.MAX_VALUE;
      
                // i-1 th separator before position arr[p=1..j]
                for (int p = 1; p < j; p++)
                    best = Math.min(best, Math.max(dp[i-1][p],//definitely sus
                                  response(arr, p, j-1)));      
      
                dp[i][j] = best;
            }
        }
      
        // required
        return dp[k][n];
    }
    
    static ArrayList<Float> findMaxLoc(Float arr[], int n, int k)
    {
        // initialize table
        Float dp[][] = new Float[k+1][n+1];
        Integer c[][] = new Integer[k+1][n+1];
       
      
        // base cases
        // k=1
        for (int i = 1; i <= n; i++) {
            dp[1][i] = response(arr, 0, i-1);
        	c[1][i] = 0;}//c[1,0....1,n]
        	
      
        // n=1
        for (int i = 1; i <= k; i++) {
            dp[i][1] = 0.0f;
            c[i][1] = 0;}
      
        
        
                
                for (int i = 2; i <= k; i++) { // 2 to n boards
                    for (int j = 2; j <= n; j++) {  	
            	
                    	 Float best = Float.MAX_VALUE;
                         
                         float old;
                         
                         
                // i-1 th separator before position arr[p=1..j]
                for (int p = 1; p < j; p++) {
                	old=best;
                	
                		best = Math.min(best, Math.max(dp[i-1][p],//dp[previous partition][current checking location]
                                response(arr, p, j-1)));
                		
                		if(old!=best) {
                		c[i][j]=p;}
                			
                		
                		
                		
                		
                		
                	}
                       
      
                dp[i][j] = best;
                
 
            }

            }
        
     ArrayList<Float> use = new ArrayList<Float>();//correct is at 
     int z =n;
      for(int g=k; g>0; g--) {//putting k pieces into list
    	  
    	 use.add(response2(arr, c[g][z], z-1));
    	 z=c[g][z];
      }
        // required
      ArrayList<Float> list = new ArrayList<Float>();//reversing list
      for(int i =use.size()-1; i>=0; i--) {
    	  list.add(use.get(i));
      }
        return list;
    }
     
     
    // Driver code
    
    
}
