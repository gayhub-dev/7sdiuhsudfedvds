package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.util.HashMap;
import java.util.concurrent.Callable;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* loaded from: classes.dex */
public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    /* renamed from: com.alibaba.sdk.android.oss.network.OSSRequestTask$1 */
    public static /* synthetic */ class C05601 {
        public static final /* synthetic */ int[] $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod = iArr;
            try {
                iArr[HttpMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.GET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.HEAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext, int i7) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i7);
    }

    private ResponseMessage buildResponseMessage(RequestMessage requestMessage, Response response) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRequest(requestMessage);
        responseMessage.setResponse(response);
        HashMap map = new HashMap();
        Headers headers = response.headers();
        for (int i7 = 0; i7 < headers.size(); i7++) {
            map.put(headers.name(i7), headers.value(i7));
        }
        responseMessage.setHeaders(map);
        responseMessage.setStatusCode(response.code());
        responseMessage.setContentLength(response.body().contentLength());
        responseMessage.setContent(response.body().byteStream());
        return responseMessage;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0315 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0192 A[Catch: Exception -> 0x02c8, TryCatch #6 {Exception -> 0x02c8, blocks: (B:3:0x0004, B:5:0x000c, B:6:0x0019, B:8:0x003a, B:10:0x0043, B:12:0x0050, B:13:0x0062, B:15:0x0068, B:16:0x007f, B:69:0x01f7, B:27:0x00c6, B:28:0x00cc, B:29:0x00d2, B:33:0x00dd, B:35:0x00ec, B:62:0x0192, B:64:0x019a, B:65:0x01a5, B:67:0x01c6, B:68:0x01e3, B:36:0x0101, B:38:0x0109, B:41:0x0125, B:42:0x012c, B:43:0x012d, B:45:0x0135, B:48:0x0163, B:54:0x016e, B:55:0x0171, B:56:0x0172, B:58:0x017a, B:60:0x0189, B:11:0x004a, B:87:0x02c0, B:88:0x02c7), top: B:158:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c4  */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public T call() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1089
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.network.OSSRequestTask.call():com.alibaba.sdk.android.oss.model.OSSResult");
    }
}
