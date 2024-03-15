package no5;
/*CIS 481: Parallel and Distributed Software Systems
 * Problem Set 3
 * Group Members:
 * Ameh Ojonukpemi Felix
 * Isaac Edward Pefaur
 * 
 * Question P3-5:
 * Implement the pseudo code solution to The One Lane Bridge
 * This is the SouthBoundCar class, this process will compete along with other SouthBoundCars to control the bridge
 * and writes data each time it crosses the bridge
 */
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SouthBoundCar extends Thread{
	
	private int id = 0;
	private int sCrossed = 0;
	
	public SouthBoundCar(int i) { 
		
		this.id = i; 
	
	}
	
	private void waka() {
			
		Random random = new Random();
		int num = random.nextInt(3) + 1;
		
		
		for(int i =0; i <num; i++) {
			System.out.print("PS" + id + " : ");
			try{ sleep(500); } catch (InterruptedException e) { } ;
		}
		//System.out.println("\nProcess " + bound + id + " exiting the bridge; Safe travels");
			
	}
	
	public void run(){
		while(true) {
			P(Bridge.mutexR);
			
				if (Bridge.NBound > 0 || Bridge.nInc == 1) {
					Bridge.dSBound++;
					
					V(Bridge.mutexR);
					P(Bridge.delayS);
				}
				Bridge.SBound++;
			
			Signal();
				
	        
	        waka();
	        
	        P(Bridge.mutexR); 
	        
	        	Bridge.SBound--;
	        	sCrossed++;
	        	
	        Signal();
	        try{ sleep(1000) ; } catch (InterruptedException e) { } ;
		}
	}
	
	
	void Signal() {
			if(sCrossed > 9) {
				Bridge.nInc = 1;
			}
	    	if(Bridge.dNBound > 0 && Bridge.SBound == 0 && Bridge.sInc == 0) {
	    		Bridge.dNBound--;
	    		System.out.println("\n<<Bridge traffic now going North>> ");
	    		Bridge.nInc = 0;
	    		sCrossed = 0;
	    		V(Bridge.delayN);
	    	}
	    	else if(Bridge.dSBound > 0 && Bridge.NBound == 0 && Bridge.nInc == 0) {
	    		Bridge.dSBound--;
	    		System.out.println("\n<<Bridge traffic now going South>> ");
	    		V(Bridge.delayS);
	    	}else {
	    		V(Bridge.mutexR);
	    	}
	    		
	    		
	    	

    }
	
	void P(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void V(Semaphore s) {

        s.release();
    }
}


