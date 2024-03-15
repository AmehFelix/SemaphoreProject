package semaphore;

import java.util.concurrent.Semaphore;


public class Reader extends Thread {
    private int i ;

    public Reader(int i) { this.i = i; }

    private void read(int i, String s) {
        System.out.println(" **Reader[" + i + "] is " + s) ;
        for (int j = 0; j < 10; j++) {
            System.out.print("R"+i+".");
            try{ sleep(500); } catch (InterruptedException e) { } ;
        }
        System.out.println(" **Reader[" + i + "] " + s + " done") ;
    }

    public void run() {
        do {
            P(wb.mutexR);                                 // Reading pre protocol
            wb.nr++ ;
            if (wb.nr == 1) P(wb.rw);
            V(wb.mutexR);

            read(i, "reading") ;                          // Reading

            P(wb.mutexR);                                 // Reading post protocol
            wb.nr-- ;
            if (wb.nr == 0) V(wb.rw);
            V(wb.mutexR);

            try{ sleep(1000) ; } catch (InterruptedException e) { } ;

         } while (true) ;
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
