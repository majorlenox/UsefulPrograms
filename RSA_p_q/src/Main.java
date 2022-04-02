import java.math.BigInteger;

public class Main {

    private static boolean option = true;  // false -> n = p*q, true -> n = p^2*q^2

    public static void main(String[] args){
        BigInteger n = new BigInteger("184906977989893071784194758558769080224895044140931441118833206249383803179734573611321");
        BigInteger e = new BigInteger("65537");
        BigInteger c = new BigInteger("175698528051807837145146484599295985521262017741071537613661229632145002128053611973544");
        // find p, q from factordb.com
        BigInteger p = new BigInteger("3661161386942196028943");
        BigInteger q = new BigInteger("3714135784936599300827");
        BigInteger f = Find_f(p, q);
        BigInteger d = Find_d(e,f);
        BigInteger m = Decrypt(c,d,n);
        System.out.println(m);
    }

    static BigInteger Find_f(BigInteger p, BigInteger q){
        BigInteger f = p.add(new BigInteger("-1")).multiply(q.add(new BigInteger("-1")));
        if (option) {
            return p.multiply(q).multiply(f);
        }else {
            return f;
        }
    }

    static BigInteger Find_d(BigInteger e, BigInteger f){
        return e.modInverse(f);
    }

    static BigInteger Decrypt(BigInteger c, BigInteger d, BigInteger n){
        return c.modPow(d,n);
    }

}