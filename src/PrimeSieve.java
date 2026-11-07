import java.util.ArrayList;
import java.util.List;

public class PrimeSieve {
    // Sieve of Eratosthenes implementation
    public static List<Integer> generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true; // Assume all are prime initially
        }

        // Cross out multiples
        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= limit; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Collect remaining primes
        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p <= limit; p++) {
            if (isPrime[p]) {
                primes.add(p);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        int limit = 100;
        System.out.println("Prime numbers up to " + limit + ":");
        List<Integer> primes = generatePrimes(limit);
        
        for (int i = 0; i < primes.size(); i++) {
            System.out.print(primes.get(i) + " ");
            if ((i + 1) % 10 == 0) System.out.println(); // Print 10 per line
        }
        System.out.println();
    }
}
