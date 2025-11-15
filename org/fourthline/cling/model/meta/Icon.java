package org.fourthline.cling.model.meta;

import android.support.v4.app.NotificationCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.types.BinHexDatatype;
import p009b.C0413b;
import p098l6.C1448b;
import p098l6.C1449c;
import p106m6.C1500c;

/* loaded from: classes.dex */
public class Icon implements Validatable {
    private static final Logger log = Logger.getLogger(StateVariable.class.getName());
    private final byte[] data;
    private final int depth;
    private Device device;
    private final int height;
    private final C1448b mimeType;
    private final URI uri;
    private final int width;

    public Icon(String str, int i7, int i8, int i9, URI uri) {
        this((str == null || str.length() <= 0) ? null : C1448b.m1633a(str), i7, i8, i9, uri, (byte[]) null);
    }

    public Icon deepCopy() {
        return new Icon(getMimeType(), getWidth(), getHeight(), getDepth(), getUri(), getData());
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDepth() {
        return this.depth;
    }

    public Device getDevice() {
        return this.device;
    }

    public int getHeight() {
        return this.height;
    }

    public C1448b getMimeType() {
        return this.mimeType;
    }

    public URI getUri() {
        return this.uri;
    }

    public int getWidth() {
        return this.width;
    }

    public void setDevice(Device device) {
        if (this.device != null) {
            throw new IllegalStateException("Final value has been set already, model is immutable");
        }
        this.device = device;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Icon(");
        sbM112a.append(getWidth());
        sbM112a.append("x");
        sbM112a.append(getHeight());
        sbM112a.append(", MIME: ");
        sbM112a.append(getMimeType());
        sbM112a.append(") ");
        sbM112a.append(getUri());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getMimeType() == null) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("UPnP specification violation of: ");
            sbM112a.append(getDevice());
            logger.warning(sbM112a.toString());
            logger.warning("Invalid icon, missing mime type: " + this);
        }
        if (getWidth() == 0) {
            Logger logger2 = log;
            StringBuilder sbM112a2 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a2.append(getDevice());
            logger2.warning(sbM112a2.toString());
            logger2.warning("Invalid icon, missing width: " + this);
        }
        if (getHeight() == 0) {
            Logger logger3 = log;
            StringBuilder sbM112a3 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a3.append(getDevice());
            logger3.warning(sbM112a3.toString());
            logger3.warning("Invalid icon, missing height: " + this);
        }
        if (getDepth() == 0) {
            Logger logger4 = log;
            StringBuilder sbM112a4 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a4.append(getDevice());
            logger4.warning(sbM112a4.toString());
            logger4.warning("Invalid icon, missing bitmap depth: " + this);
        }
        if (getUri() == null) {
            arrayList.add(new ValidationError(getClass(), NotificationCompat.MessagingStyle.Message.KEY_DATA_URI, "URL is required"));
        } else {
            try {
                if (getUri().toURL() == null) {
                    throw new MalformedURLException();
                }
            } catch (IllegalArgumentException unused) {
            } catch (MalformedURLException e7) {
                Class<?> cls = getClass();
                StringBuilder sbM112a5 = C0413b.m112a("URL must be valid: ");
                sbM112a5.append(e7.getMessage());
                arrayList.add(new ValidationError(cls, NotificationCompat.MessagingStyle.Message.KEY_DATA_URI, sbM112a5.toString()));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Icon(String str, int i7, int i8, int i9, URL url) throws URISyntaxException {
        URI uri;
        BitSet bitSet = C1449c.f4207a;
        if (url == null) {
            uri = null;
        } else {
            try {
                uri = url.toURI();
            } catch (URISyntaxException e7) {
                throw new RuntimeException(e7);
            }
        }
        this(str, i7, i8, i9, new File(uri));
    }

    public Icon(String str, int i7, int i8, int i9, File file) throws IOException {
        String name = file.getName();
        int i10 = C1500c.f4301a;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArrM1664a = C1500c.m1664a(fileInputStream);
            fileInputStream.close();
            this(str, i7, i8, i9, name, bArrM1664a);
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public Icon(String str, int i7, int i8, int i9, String str2, InputStream inputStream) {
        this(str, i7, i8, i9, str2, C1500c.m1664a(inputStream));
    }

    public Icon(String str, int i7, int i8, int i9, String str2, byte[] bArr) {
        this((str == null || str.length() <= 0) ? null : C1448b.m1633a(str), i7, i8, i9, URI.create(str2), bArr);
    }

    public Icon(String str, int i7, int i8, int i9, String str2, String str3) {
        this(str, i7, i8, i9, str2, (str3 == null || str3.equals("")) ? null : new BinHexDatatype().valueOf(str3));
    }

    public Icon(C1448b c1448b, int i7, int i8, int i9, URI uri, byte[] bArr) {
        this.mimeType = c1448b;
        this.width = i7;
        this.height = i8;
        this.depth = i9;
        this.uri = uri;
        this.data = bArr;
    }
}
