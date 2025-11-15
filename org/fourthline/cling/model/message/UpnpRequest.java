package org.fourthline.cling.model.message;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p009b.C0413b;

/* loaded from: classes.dex */
public class UpnpRequest extends UpnpOperation {
    private Method method;
    private URI uri;

    public enum Method {
        GET("GET"),
        POST("POST"),
        NOTIFY("NOTIFY"),
        MSEARCH("M-SEARCH"),
        SUBSCRIBE("SUBSCRIBE"),
        UNSUBSCRIBE("UNSUBSCRIBE"),
        UNKNOWN("UNKNOWN");

        private static Map<String, Method> byName = new HashMap<String, Method>() { // from class: org.fourthline.cling.model.message.UpnpRequest.Method.1
            {
                for (Method method : Method.values()) {
                    put(method.getHttpName(), method);
                }
            }
        };
        private String httpName;

        Method(String str) {
            this.httpName = str;
        }

        public static Method getByHttpName(String str) {
            if (str == null) {
                return UNKNOWN;
            }
            Method method = byName.get(str.toUpperCase(Locale.ROOT));
            return method != null ? method : UNKNOWN;
        }

        public String getHttpName() {
            return this.httpName;
        }
    }

    public UpnpRequest(Method method) {
        this.method = method;
    }

    public String getHttpMethodName() {
        return this.method.getHttpName();
    }

    public Method getMethod() {
        return this.method;
    }

    public URI getURI() {
        return this.uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpMethodName());
        if (getURI() != null) {
            StringBuilder sbM112a = C0413b.m112a(" ");
            sbM112a.append(getURI());
            string = sbM112a.toString();
        } else {
            string = "";
        }
        sb.append(string);
        return sb.toString();
    }

    public UpnpRequest(Method method, URI uri) {
        this.method = method;
        this.uri = uri;
    }

    public UpnpRequest(Method method, URL url) {
        this.method = method;
        if (url != null) {
            try {
                this.uri = url.toURI();
            } catch (URISyntaxException e7) {
                throw new IllegalArgumentException(e7);
            }
        }
    }
}
