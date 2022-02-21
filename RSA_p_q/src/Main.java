import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BigInteger n = new BigInteger("238373560316942850390995533425775013783");
        BigInteger e = new BigInteger("65537");
        BigInteger c = new BigInteger("224443774599960357148718901487584596812");
        // find p, q from factordb.com
        BigInteger p = new BigInteger("13542555658222801393");
        BigInteger q = new BigInteger("17601815073376244231");
        BigInteger f = new BigInteger("238373560316942850359851162694175968160");
        BigInteger d = Find_d(e,f);
        BigInteger m = Decrypt(c,d,n);
        System.out.println(m);
    }

    static BigInteger Find_f(BigInteger p, BigInteger q){
        return p.add(new BigInteger("-1")).multiply(q.add(new BigInteger("-1")));
    }

    static BigInteger Find_d(BigInteger e, BigInteger f){
         return e.modInverse(f);
    }

    static BigInteger Decrypt(BigInteger c, BigInteger d, BigInteger n){
        return c.multiply(d).mod(n);
    }

}
