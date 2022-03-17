import java.math.BigInteger;

public class Main {

    public static void main(String[] args){

        BigInteger n = new BigInteger("1584586296183412107468474423529992275940096154074798537916936609523894209759157543");
        BigInteger e = new BigInteger("65537");
        BigInteger c = new BigInteger("964354128913912393938480857590969826308054462950561875638492039363373779803642185");
        // find p, q from factordb.com
        BigInteger p = new BigInteger("2434792384523484381583634042478415057961");
        BigInteger q = new BigInteger("650809615742055581459820253356987396346063");
        BigInteger f = Find_f(p, q);
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
        return c.modPow(d,n);
    }

}
