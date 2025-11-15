package com.ctvit.network.cache.converter;

import com.ctvit.network.utils.Utils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class SerializableDiskConverter implements IDiskConverter {
    @Override // com.ctvit.network.cache.converter.IDiskConverter
    public <T> T load(InputStream inputStream, Type type) throws Throwable {
        ObjectInputStream objectInputStream;
        Closeable closeable = (T) null;
        try {
            try {
                objectInputStream = new ObjectInputStream(inputStream);
            } catch (IOException e7) {
                e = e7;
                objectInputStream = null;
                C2073a.m2458c(e);
                Utils.close(objectInputStream);
                return (T) closeable;
            } catch (ClassNotFoundException e8) {
                e = e8;
                objectInputStream = null;
                C2073a.m2458c(e);
                Utils.close(objectInputStream);
                return (T) closeable;
            } catch (Throwable th) {
                th = th;
                Utils.close(closeable);
                throw th;
            }
            try {
                closeable = (T) objectInputStream.readObject();
            } catch (IOException e9) {
                e = e9;
                C2073a.m2458c(e);
                Utils.close(objectInputStream);
                return (T) closeable;
            } catch (ClassNotFoundException e10) {
                e = e10;
                C2073a.m2458c(e);
                Utils.close(objectInputStream);
                return (T) closeable;
            }
            Utils.close(objectInputStream);
            return (T) closeable;
        } catch (Throwable th2) {
            th = th2;
            closeable = (T) objectInputStream;
            Utils.close(closeable);
            throw th;
        }
    }

    @Override // com.ctvit.network.cache.converter.IDiskConverter
    public boolean writer(OutputStream outputStream, Object obj) throws Throwable {
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(outputStream);
                try {
                    objectOutputStream2.writeObject(obj);
                    objectOutputStream2.flush();
                    Utils.close(objectOutputStream2);
                    return true;
                } catch (IOException e7) {
                    e = e7;
                    objectOutputStream = objectOutputStream2;
                    C2073a.m2458c(e);
                    Utils.close(objectOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    Utils.close(objectOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e8) {
            e = e8;
        }
    }
}
