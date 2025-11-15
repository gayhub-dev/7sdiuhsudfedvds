package p041e5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import p065h5.C1107o;
import p073i5.C1155h;
import p073i5.InterfaceC1152e;
import p161t5.C1926r;

/* compiled from: ContentExchange.java */
/* renamed from: e5.f */
/* loaded from: classes.dex */
public class C0961f extends C0960e {
    private int _bufferSize;
    private String _encoding;
    private File _fileForUpload;
    private ByteArrayOutputStream _responseContent;

    public C0961f() {
        super(false);
        this._bufferSize = 4096;
        this._encoding = "utf-8";
    }

    private synchronized InputStream getInputStream() {
        return new FileInputStream(this._fileForUpload);
    }

    public synchronized File getFileForUpload() {
        return this._fileForUpload;
    }

    public synchronized String getResponseContent() {
        ByteArrayOutputStream byteArrayOutputStream = this._responseContent;
        if (byteArrayOutputStream == null) {
            return null;
        }
        return byteArrayOutputStream.toString(this._encoding);
    }

    public synchronized byte[] getResponseContentBytes() {
        ByteArrayOutputStream byteArrayOutputStream = this._responseContent;
        if (byteArrayOutputStream == null) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // p041e5.C0965j
    public synchronized void onResponseContent(InterfaceC1152e interfaceC1152e) {
        super.onResponseContent(interfaceC1152e);
        if (this._responseContent == null) {
            this._responseContent = new ByteArrayOutputStream(this._bufferSize);
        }
        interfaceC1152e.mo1335r(this._responseContent);
    }

    @Override // p041e5.C0960e, p041e5.C0965j
    public synchronized void onResponseHeader(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        String strM2252b;
        int iIndexOf;
        super.onResponseHeader(interfaceC1152e, interfaceC1152e2);
        int iM1357e = C1107o.f2326d.m1357e(interfaceC1152e);
        if (iM1357e == 12) {
            this._bufferSize = C1155h.m1366d(interfaceC1152e2);
        } else if (iM1357e == 16 && (iIndexOf = (strM2252b = C1926r.m2252b(interfaceC1152e2.toString())).indexOf("charset=")) > 0) {
            String strSubstring = strM2252b.substring(iIndexOf + 8);
            this._encoding = strSubstring;
            int iIndexOf2 = strSubstring.indexOf(59);
            if (iIndexOf2 > 0) {
                this._encoding = this._encoding.substring(0, iIndexOf2);
            }
        }
    }

    @Override // p041e5.C0960e, p041e5.C0965j
    public synchronized void onResponseStatus(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
        ByteArrayOutputStream byteArrayOutputStream = this._responseContent;
        if (byteArrayOutputStream != null) {
            byteArrayOutputStream.reset();
        }
        super.onResponseStatus(interfaceC1152e, i7, interfaceC1152e2);
    }

    @Override // p041e5.C0965j
    public synchronized void onRetry() {
        if (this._fileForUpload != null) {
            setRequestContent(null);
            setRequestContentSource(getInputStream());
        } else {
            super.onRetry();
        }
    }

    public synchronized void setFileForUpload(File file) {
        this._fileForUpload = file;
        setRequestContentSource(getInputStream());
    }

    public C0961f(boolean z6) {
        super(z6);
        this._bufferSize = 4096;
        this._encoding = "utf-8";
    }
}
