import java.util.Arrays;
import org.apache.commons.lang.StringUtils;

/**
 * Fibonacci(150)result with cache = 6792540214324356296 	Executed in 0 millisecs 
 * 
 * 
 * Fibonacci(15) result with cache =   		610             Executed in 0 millisecs
 * Fibonacci(15) result without cache=     610             Executed in 0 millisecs
 * 
 * Fibonacci(20) result with cache =       6765						Executed in 0 millisecs
 * Fibonacci(20) result without cache=     6765            Executed in 2 millisecs
 * 
 * Fibonacci(25) result with cache =       75 025          Executed in 0 millisecs
 * Fibonacci(25) result without cache=     75 025          Executed in 4 millisecs
 * 
 * Fibonacci(30) result with cache =       832 040         Executed in 0 millisecs
 * Fibonacci(30) result without cache=     832 040         Executed in 7 millisecs
 * 
 * Fibonacci(35) result with cache =       9 227 465       Executed in 0 millisecs
 * Fibonacci(35) result without cache= 		9 227 465       Executed in 48 millisecs
 * 
 * Fibonacci(40) result with cache =       102 334 155     Executed in 0 millisecs
 * Fibonacci(40) result without cache=     102 334 155     Executed in 502 millisecs
 * 
 * Fibonacci(55) result with cache =       139 583 862 445 Executed in 0 millisecs
 * Fibonacci(55) result without cache=     139 583 862 445 Executed in 687667 millisecs (11 minutes....)
*/

public class FibonacciCalculator {
	
	
		/**
     * Class is expecting a positive integer argument
     * @param args
     */
    public static void main(String [] args){
        
        for (String s: args) {
            if (StringUtils.isNumeric(s))
            {
                Long n = Long.parseLong(s);
                if (n >=0) 
                  FibonacciCalculator fCalculator = new FibonacciCalculator();
                  //long result = fCalculator.getFibonacci(n.intValue());
                  long result = new Long(2L);
                  System.out.println(result);
                  return;
            }
                
        }
    }
    
    public long getFibonacci(int n)
    {      
        //Cache init
        long[] fiboCache = new long[(n<10000)?n:10000];
        Arrays.fill(fiboCache, -1);
        
        
        long startTime = System.currentTimeMillis();
        long fiboResult = fibonacci(n, fiboCache);
        long stopTime = System.currentTimeMillis();
        
        System.out.println("Fibonacci(" + n + ") result with cache = " + fiboResult + " Executed in " + (stopTime - startTime) + " millisecs");
        
        //Remove commented code bloc below if you want to compare trivial recursive  
        //implementation with the one that uses dynamic programming technique.
        //any call beyond 
        
        /*
        startTime = System.currentTimeMillis();
        fiboResult = fibonacci(n);
        stopTime = System.currentTimeMillis();
        System.out.println("Fibonacci(" + n + ") result without cache= " + fiboResult + " Executed in " + (stopTime - startTime) + " millisecs");
        */
        
        return fiboResult;
    }
    
    //Dynamic programming                                                
    private long fibonacci(int n, long[] pCache)  {
        
        if(n <= 1) {
            pCache[n]=n;
            return n;
        }      
        else {
            
           long n_1Result;
           long n_2Result;
            
           if ((n-2 < pCache.length)  && (pCache[(n-2)] != -1L))                                                    
              n_2Result = pCache[(n-2)];
           else 
              n_2Result = fibonacci(n-2, pCache);  
           
           if ((n-1 < pCache.length) && (pCache[(n-1)] != -1L))                                                   
                n_1Result = pCache[(n-1)];
           else           
               n_1Result = fibonacci(n-1, pCache);          
                    
           long result = n_1Result + n_2Result;
            
           //put result in cache to potentially speed up next method call execution
           if (n < pCache.length)
               pCache[n] = result;
                     
           return result;
       }
    }

		//Trivial (Brute force)
    private long fibonacci(int n)  {
        
        if(n <= 1) {
            return n;
        }
        else {
            
           long n_1Result;
           long n_2Result;
                        
           n_2Result = fibonacci(n-2);  
           n_1Result = fibonacci(n-1);  
                               
           long result = n_1Result + n_2Result;
                    
           return result;
       }
    }
}