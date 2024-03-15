package no5;
/*CIS 481: Parallel and Distributed Software Systems
 * Problem Set 3
 * Group Members:
 * Ameh Ojonukpemi Felix
 * Isaac Edward Pefaur
 * 
 * Question P3-5:
 * Implement the pseudo code solution to The One Lane Bridge
 * This is the NorthBoundCar class, this process will compete along with other NorthBoundCars to control the bridge
 * and writes data each time it crosses the bridge
 */
import java.util.Random;
import java.util.concurrent.Semaphore;

public class NorthBoundCar extends Thread{
	
	private int id = 0;
	private int nCrossed = 0;
	
	public NorthBoundCar(int i) { 
		
		this.id = i; 
	
	}
	
	private void waka() {
			
		Random random = new Random();
		int num = random.nextInt(3) + 1;
		
		
		for(int i =0; i <num; i++) {
			System.out.print("PN" + id + " : ");
			try{ sleep(500); } catch (InterruptedException e) { } ;
		}
		//System.out.println("\nProcess " + bound + id + " exiting the bridge; Safe travels");
			
	}
	
	public void run(){
		while(true) {
			P(Bridge.mutexR);
			
				if (Bridge.SBound > 0 || Bridge.sInc == 1) {
					
					Bridge.dNBound++;
					V(Bridge.mutexR);
					P(Bridge.delayN);
				}
				Bridge.NBound++;
			
			Signal();
				
	        

	        waka();

	        P(Bridge.mutexR); 
	        
	        	Bridge.NBound--;
	        	nCrossed++;

	        Signal();
	        try{ sleep(1000) ; } catch (InterruptedException e) { } ;
		}
	}
	
	
	void Signal() {
		

		if(nCrossed > 10) {
			Bridge.sInc = 1;
		}
    	if(Bridge.dNBound > 0 && Bridge.SBound == 0 && Bridge.sInc == 0 ) {
    		Bridge.dNBound--;
    		System.out.println("\n<<Bridge traffic now going North>> ");
    		V(Bridge.delayN);
    	}
    	else if(Bridge.dSBound > 0 && Bridge.NBound == 0 && Bridge.nInc == 0) {
    		Bridge.dSBound--;
    		System.out.println("\n<<Bridge traffic now going South>> ");
    		Bridge.sInc = 0;
    		nCrossed = 0;
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
