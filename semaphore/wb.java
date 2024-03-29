package semaphore;

import java.util.concurrent.Semaphore;

public class wb {
    static int N = 3 ;
    static int nr = 0;
    static Semaphore mutexR = new Semaphore(1) ;
    static Semaphore rw = new Semaphore(1) ;

    public static void main(String [] args) {
        //Reader p[] = new Reader[N] ;
        //Writer q[] = new Writer[N] ;
        
        System.out.println("Creating 3 readers: Reader[0], Reader[1], and Reader[2] ...");
        for (int i=0; i<N; i++) {
           Reader x = new Reader(i) ;
            x.start() ;
        }
        
        System.out.println("Creating 3 writers: Writer[0], Writer[1], and Writer[2] ...");
        for (int i=0; i<N; i++) {
            Writer y = new Writer(i) ;
            y.start() ;
        }
    }
}
