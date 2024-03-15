/*package no5;

import java.util.concurrent.Semaphore;
import java.util.Random;


public class Cars extends Thread{
	private int id = 0;
	private int nCrossed = 0;
	private int sCrossed = 0;
	private char bound = 'X';
	private String start = " : Destination: Knowhere, ETA: Who:Cares PM, Objective: Burn it alll@!%^...";
	
	public Cars(int i, char x) { 
		
		this.id = i; 
		this.bound = x;
		if(x == Bridge.n)
			start =  ": Northern bound";
		else if(x == Bridge.s)
			start = ": heading South";
	}
	
	private void waka() {
		
		Random random = new Random();
		int randNum = random.nextInt(5) + 1;
		//System.out.println("\nThis is Process "+ bound + id + start + " ETA: " + randNum + "min(s)");
		for(int i =0; i <randNum; i++) {
			System.out.print("P" + bound + id + " : ");
			try{ sleep(500); } catch (InterruptedException e) { } ;
		}
		//System.out.println("\nProcess " + bound + id + " exiting the bridge; Safe travels");
		
	}

	private void addNorthernCar() {
		while(true) {
			P(Bridge.mutexR);
			
				//if(TrafficLight.NorthernBound == 0 && TrafficLight.SuthernBound == 0) {
				//	System.out.println("<<Bridge traffic now going North>> ");
				//}
				if (Bridge.SuthernBound > 0) {
					Bridge.delayedNBound++;
					
					V(Bridge.mutexR);
					P(Bridge.delayN);
				}
				Bridge.NorthernBound++;
			
			Signal(bound);
				
	        
	        //V(TrafficLight.mutexR);
	        waka();
	        //System.out.print("mutexR=" + TrafficLight.mutexR);
	        P(Bridge.mutexR); 
	        
	        	Bridge.NorthernBound--;
	        	nCrossed++;
	       
	        Signal(bound);
	        try{ sleep(1000) ; } catch (InterruptedException e) { } ;
		}
	}
	
	private void addSuthernCar() {
		while(true) {
			P(Bridge.mutexR);
			
				//if(TrafficLight.NorthernBound == 0 && TrafficLight.SuthernBound == 0) {
				//	System.out.println("<<Bridge traffic now going South>> ");
				//}
				if (Bridge.NorthernBound > 0) {
					Bridge.delayedSBound++;
				
					V(Bridge.mutexR);
					P(Bridge.delayS);
					
				
				}
				Bridge.SuthernBound++;
		
			Signal(bound);
			
			//System.out.print("Hello");
	    	waka();
	    
	    	P(Bridge.mutexR); 
	        
	    		Bridge.SuthernBound--;
	    		sCrossed++;
	   
	    	Signal(bound);
	
	    	try{ sleep(1000) ; } catch (InterruptedException e) { } ;
		}
	}

	public void run(){
		
		switch (bound) {
			case 'N' -> {
				addNorthernCar();
			}
			case 'S' -> {
				
				addSuthernCar();
			}
			default -> {
				System.out.println("Process "+ bound + id + start);
				System.err.println("%$$$...<<Control Regained>> Looks like Process " + bound + id +
									 " ran amok a bit. Remember to assign all processes a destination");
			}
		}

	}
	
	
	void P(Semaphore s) {
		//System.out.print("Hello, this is P ");
        try {
            s.acquire(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void V(Semaphore s) {
    	//System.out.print("Hello, this is V ");
        s.release(1);
        //System.out.print(s);
    }
    
    void Signal(char b) {
    	//P(TrafficLight.mutexR);
	    	if(Bridge.delayedNBound > 0 && Bridge.SuthernBound == 0 ) {
	    		Bridge.delayedNBound--;
	    		System.out.println("\n<<Bridge traffic now going North>> ");
	    		V(Bridge.delayN);
	    	}else if(Bridge.delayedNBound > 0 && sCrossed > 9) {
	    		sCrossed = 0;
	    		System.out.println("\n<<Bridge traffic now going North>> ");
	    		V(Bridge.delayN);
	    	}
	    	if(Bridge.delayedSBound > 0 && Bridge.NorthernBound == 0 ) {
	    		Bridge.delayedSBound--;
	    		System.out.println("\n<<Bridge traffic now going South>> ");
	    		V(Bridge.delayS);
	    	}else if(Bridge.delayedSBound > 0 && nCrossed > 9) {
	    		nCrossed = 0;
	    		System.out.println("\n<<Bridge traffic now going South>> ");
	    		V(Bridge.delayS);
	    	}else {
	    		V(Bridge.mutexR);
	    		
	    	}
	    //V(TrafficLight.mutexR);
    }
	
}*/
