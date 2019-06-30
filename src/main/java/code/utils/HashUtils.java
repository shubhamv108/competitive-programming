package code.utils;

//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang3.SerializationUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {


//     public static String md5 ( Serializable s ) {
//          return DigestUtils.md5Hex(
//                  SerializationUtils.serialize(s));
//     }
//
//     public static String md5 ( Serializable... s ) {
//          return DigestUtils.md5Hex(
//                  SerializationUtils.serialize(s));
//     }
//
//     public static String md5 ( String s ) {
//          MessageDigest md = null;
//          try {
//               md = MessageDigest.getInstance("MD5");
//          } catch (NoSuchAlgorithmException e) {
//               // e.printStackTrace();
//               throw new RuntimeException("Algorithm is Not Supported");
//          }
//          md.update(s.getBytes());
//          byte[] digest = md.digest();
//          return DatatypeConverter
//                  .printHexBinary(digest).toUpperCase();
//     }

}
