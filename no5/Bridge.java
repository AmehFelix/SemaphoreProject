package no5;
/*CIS 481: Parallel and Distributed Software Systems
 * Problem Set 3
 * Group Members:
 * Ameh Ojonukpemi Felix
 * Isaac Edward Pefaur
 * 
 * Question P3-5:
 * Implement the pseudo code solution to The One Lane Bridge
 * This is the main 'Bridge' that creates and runs the cars of either NorthBoundCar/SouthBoundCar class, (also included)
 * 
 */
import java.util.concurrent.Semaphore;
import java.util.Random;


public class Bridge {
	static int NBound = 0;
	static int dNBound = 0;
	static int SBound = 0;
	static int dSBound = 0;
	static int nInc = 0;
	static int sInc = 0;
    static Semaphore mutexR = new Semaphore(1);
    static Semaphore delayN = new Semaphore(0);
    static Semaphore delayS = new Semaphore(0);
    
    
    //Bridge creates the N amount of NorthCar processes and S amount of SouthCar processes
    public static void main(String [] args) {
    	
    	Random random = new Random();
		//int countS = random.nextInt(5) + 1;
		//int countN = random.nextInt(5) + 1;
    	int countS = 3;
    	int countN = 3;
		
		for(int i = 0; i< countN; i++) {
	    	NorthBoundCar vroom = new NorthBoundCar(i);
	    	System.out.println("\nNorthern bound car " + i +" initialized");
	    	vroom.start();
	    }
		
		for(int j = 0; j< countS; j++) {
	    	
			SouthBoundCar vroom = new SouthBoundCar(j);
			System.out.println("\nSouthern bound car " + j +"  initialized");
	    	vroom.start();   
	    }
		
		
    }
}
