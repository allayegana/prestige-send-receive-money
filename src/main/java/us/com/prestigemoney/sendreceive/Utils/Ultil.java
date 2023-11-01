package us.com.prestigemoney.sendreceive.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ultil {

    public static String MD5(String motpass) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, messageDigest.digest(motpass.getBytes()));
        return hash.toString(16);
    }
}
