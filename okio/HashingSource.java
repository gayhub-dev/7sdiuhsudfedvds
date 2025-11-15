package okio;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class HashingSource extends ForwardingSource {
    private final Mac mac;
    private final MessageDigest messageDigest;

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
            this.mac = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public static HashingSource hmacSha1(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA1");
    }

    public static HashingSource hmacSha256(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA256");
    }

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, "SHA-1");
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, "SHA-256");
    }

    public final ByteString hash() {
        MessageDigest messageDigest = this.messageDigest;
        return ByteString.m1869of(messageDigest != null ? messageDigest.digest() : this.mac.doFinal());
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(Buffer buffer, long j7) throws IllegalStateException {
        long j8 = super.read(buffer, j7);
        if (j8 != -1) {
            long j9 = buffer.size;
            long j10 = j9 - j8;
            Segment segment = buffer.head;
            while (j9 > j10) {
                segment = segment.prev;
                j9 -= segment.limit - segment.pos;
            }
            while (j9 < buffer.size) {
                int i7 = (int) ((segment.pos + j10) - j9);
                MessageDigest messageDigest = this.messageDigest;
                if (messageDigest != null) {
                    messageDigest.update(segment.data, i7, segment.limit - i7);
                } else {
                    this.mac.update(segment.data, i7, segment.limit - i7);
                }
                j10 = (segment.limit - segment.pos) + j9;
                segment = segment.next;
                j9 = j10;
            }
        }
        return j8;
    }

    private HashingSource(Source source, ByteString byteString, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        super(source);
        try {
            Mac mac = Mac.getInstance(str);
            this.mac = mac;
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            this.messageDigest = null;
        } catch (InvalidKeyException e7) {
            throw new IllegalArgumentException(e7);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }
}
