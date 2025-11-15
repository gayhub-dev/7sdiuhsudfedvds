package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.CaseInsensitiveHashMap;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.zip.CheckedInputStream;
import okhttp3.Headers;
import okhttp3.Response;

/* loaded from: classes.dex */
public abstract class AbstractResponseParser<T extends OSSResult> implements ResponseParser {
    private CaseInsensitiveHashMap<String, String> parseResponseHeader(Response response) {
        CaseInsensitiveHashMap<String, String> caseInsensitiveHashMap = new CaseInsensitiveHashMap<>();
        Headers headers = response.headers();
        for (int i7 = 0; i7 < headers.size(); i7++) {
            caseInsensitiveHashMap.put(headers.name(i7), headers.value(i7));
        }
        return caseInsensitiveHashMap;
    }

    public static void safeCloseResponse(ResponseMessage responseMessage) {
        try {
            responseMessage.close();
        } catch (Exception unused) {
        }
    }

    public boolean needCloseResponse() {
        return true;
    }

    @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
    public T parse(ResponseMessage responseMessage) {
        try {
            try {
                T t6 = (T) ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
                if (t6 != null) {
                    t6.setRequestId((String) responseMessage.getHeaders().get(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    t6.setStatusCode(responseMessage.getStatusCode());
                    t6.setResponseHeader(parseResponseHeader(responseMessage.getResponse()));
                    setCRC(t6, responseMessage);
                    t6 = (T) parseData(responseMessage, t6);
                }
                return t6;
            } catch (Exception e7) {
                IOException iOException = new IOException(e7.getMessage(), e7);
                e7.printStackTrace();
                OSSLog.logThrowable2Local(e7);
                throw iOException;
            }
        } finally {
            if (needCloseResponse()) {
                safeCloseResponse(responseMessage);
            }
        }
    }

    public abstract T parseData(ResponseMessage responseMessage, T t6);

    public <Result extends OSSResult> void setCRC(Result result, ResponseMessage responseMessage) {
        InputStream content = responseMessage.getRequest().getContent();
        if (content != null && (content instanceof CheckedInputStream)) {
            result.setClientCRC(Long.valueOf(((CheckedInputStream) content).getChecksum().getValue()));
        }
        String str = (String) responseMessage.getHeaders().get(OSSHeaders.OSS_HASH_CRC64_ECMA);
        if (str != null) {
            result.setServerCRC(Long.valueOf(new BigInteger(str).longValue()));
        }
    }
}
