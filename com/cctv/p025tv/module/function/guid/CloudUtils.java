package com.cctv.p025tv.module.function.guid;

import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes.dex */
public class CloudUtils {
    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";

    public static String encryptByPublicKey(String str, String str2) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str2.getBytes(), 0)));
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(1, publicKeyGeneratePublic);
        byte[] bArrDoFinal = cipher.doFinal(str.getBytes());
        if (bArrDoFinal != null) {
            return Base64.encodeToString(bArrDoFinal, 2);
        }
        return null;
    }

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
