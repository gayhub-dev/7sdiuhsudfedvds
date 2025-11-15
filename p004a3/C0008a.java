package p004a3;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import p186x2.C2073a;

/* compiled from: CtvitRsaUtils.java */
/* renamed from: a3.a */
/* loaded from: classes.dex */
public class C0008a {
    /* renamed from: a */
    public static String m8a(String str, String str2) {
        try {
            return m9b(str, m10c(str2));
        } catch (Exception e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0071: MOVE (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:51:0x0071 */
    /* renamed from: b */
    public static String m9b(String str, Key key) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        byte[] bArrDoFinal;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        try {
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, key);
                byte[] bArrDecode = Base64.decode(str, 0);
                int length = bArrDecode.length;
                byteArrayOutputStream = new ByteArrayOutputStream();
                int i7 = 0;
                int i8 = 0;
                while (true) {
                    int i9 = length - i7;
                    if (i9 <= 0) {
                        break;
                    }
                    if (i9 > 128) {
                        try {
                            bArrDoFinal = cipher.doFinal(bArrDecode, i7, 128);
                        } catch (UnsupportedEncodingException e7) {
                            e = e7;
                            C2073a.m2458c(e);
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e8) {
                                e8.printStackTrace();
                            }
                            return null;
                        } catch (InvalidKeyException e9) {
                            e = e9;
                            C2073a.m2458c(e);
                            byteArrayOutputStream.close();
                            return null;
                        } catch (NoSuchAlgorithmException e10) {
                            e = e10;
                            C2073a.m2458c(e);
                            byteArrayOutputStream.close();
                            return null;
                        } catch (BadPaddingException e11) {
                            e = e11;
                            C2073a.m2458c(e);
                            byteArrayOutputStream.close();
                            return null;
                        } catch (IllegalBlockSizeException e12) {
                            e = e12;
                            C2073a.m2458c(e);
                            byteArrayOutputStream.close();
                            return null;
                        } catch (NoSuchPaddingException e13) {
                            e = e13;
                            C2073a.m2458c(e);
                            byteArrayOutputStream.close();
                            return null;
                        }
                    } else {
                        bArrDoFinal = cipher.doFinal(bArrDecode, i7, i9);
                    }
                    byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                    i8++;
                    i7 = i8 * 128;
                }
                String str2 = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8.name());
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e14) {
                    e14.printStackTrace();
                }
                return str2;
            } catch (UnsupportedEncodingException e15) {
                e = e15;
                byteArrayOutputStream = null;
                C2073a.m2458c(e);
                byteArrayOutputStream.close();
                return null;
            } catch (InvalidKeyException e16) {
                e = e16;
                byteArrayOutputStream = null;
                C2073a.m2458c(e);
                byteArrayOutputStream.close();
                return null;
            } catch (NoSuchAlgorithmException e17) {
                e = e17;
                byteArrayOutputStream = null;
                C2073a.m2458c(e);
                byteArrayOutputStream.close();
                return null;
            } catch (BadPaddingException e18) {
                e = e18;
                byteArrayOutputStream = null;
                C2073a.m2458c(e);
                byteArrayOutputStream.close();
                return null;
            } catch (IllegalBlockSizeException e19) {
                e = e19;
                byteArrayOutputStream = null;
                C2073a.m2458c(e);
                byteArrayOutputStream.close();
                return null;
            } catch (NoSuchPaddingException e20) {
                e = e20;
                byteArrayOutputStream = null;
                C2073a.m2458c(e);
                byteArrayOutputStream.close();
                return null;
            } catch (Throwable th) {
                th = th;
                try {
                    byteArrayOutputStream3.close();
                } catch (IOException e21) {
                    e21.printStackTrace();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream3 = byteArrayOutputStream2;
            byteArrayOutputStream3.close();
            throw th;
        }
    }

    /* renamed from: c */
    public static PublicKey m10c(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes(), 0)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }

    /* renamed from: d */
    public static boolean m11d(String str, PublicKey publicKey, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey.getEncoded()));
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKeyGeneratePublic);
            signature.update(str.getBytes());
            return signature.verify(Base64.decode(str2.getBytes(), 0));
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e7) {
            C2073a.m2458c(e7);
            return false;
        }
    }
}
