package com.ctvit.network.cache.converter;

import com.ctvit.network.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ConcurrentModificationException;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class GsonDiskConverter implements IDiskConverter {
    private Gson gson;

    public GsonDiskConverter() {
        this.gson = new Gson();
        this.gson = new Gson();
    }

    @Override // com.ctvit.network.cache.converter.IDiskConverter
    public <T> T load(InputStream inputStream, Type type) {
        try {
            try {
                return this.gson.getAdapter(TypeToken.get(type)).read(this.gson.newJsonReader(new InputStreamReader(inputStream)));
            } catch (JsonIOException e7) {
                e = e7;
                C2073a.m2456a(e.getMessage());
                Utils.close(inputStream);
                return null;
            } catch (JsonSyntaxException e8) {
                e = e8;
                C2073a.m2456a(e.getMessage());
                Utils.close(inputStream);
                return null;
            } catch (IOException e9) {
                e = e9;
                C2073a.m2456a(e.getMessage());
                Utils.close(inputStream);
                return null;
            } catch (ConcurrentModificationException e10) {
                e = e10;
                C2073a.m2456a(e.getMessage());
                Utils.close(inputStream);
                return null;
            } catch (Exception e11) {
                C2073a.m2456a(e11.getMessage());
                Utils.close(inputStream);
                return null;
            }
        } finally {
            Utils.close(inputStream);
        }
    }

    @Override // com.ctvit.network.cache.converter.IDiskConverter
    public boolean writer(OutputStream outputStream, Object obj) {
        try {
            try {
                byte[] bytes = this.gson.toJson(obj).getBytes();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                return true;
            } catch (JsonIOException e7) {
                e = e7;
                C2073a.m2456a(e.getMessage());
                return false;
            } catch (JsonSyntaxException e8) {
                e = e8;
                C2073a.m2456a(e.getMessage());
                return false;
            } catch (IOException e9) {
                e = e9;
                C2073a.m2456a(e.getMessage());
                return false;
            } catch (ConcurrentModificationException e10) {
                e = e10;
                C2073a.m2456a(e.getMessage());
                return false;
            } catch (Exception e11) {
                C2073a.m2456a(e11.getMessage());
                return false;
            }
        } finally {
            Utils.close(outputStream);
        }
    }

    public GsonDiskConverter(Gson gson) {
        this.gson = new Gson();
        Utils.checkNotNull(gson, "gson ==null");
        this.gson = gson;
    }
}
