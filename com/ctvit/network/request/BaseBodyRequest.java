package com.ctvit.network.request;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.ctvit.network.body.ProgressResponseCallBack;
import com.ctvit.network.body.RequestBodyUtils;
import com.ctvit.network.body.UploadProgressRequestBody;
import com.ctvit.network.model.HttpParams;
import com.ctvit.network.request.BaseBodyRequest;
import com.ctvit.network.utils.Utils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p194y3.AbstractC2120l;
import retrofit2.http.Body;

/* loaded from: classes.dex */
public abstract class BaseBodyRequest<R extends BaseBodyRequest> extends BaseRequest<R> {

    /* renamed from: bs */
    public byte[] f961bs;
    private UploadType currentUploadType;
    public String json;
    public MediaType mediaType;
    public Object object;
    public RequestBody requestBody;
    public String string;

    public enum UploadType {
        PART,
        BODY
    }

    public BaseBodyRequest(String str) {
        super(str);
        this.currentUploadType = UploadType.PART;
    }

    private MultipartBody.Part addFile(String str, HttpParams.FileWrapper fileWrapper) {
        RequestBody requestBody = getRequestBody(fileWrapper);
        Utils.checkNotNull(requestBody, "requestBody==null fileWrapper.file must is File/InputStream/byte[]");
        ProgressResponseCallBack progressResponseCallBack = fileWrapper.responseCallBack;
        if (progressResponseCallBack == null) {
            return MultipartBody.Part.createFormData(str, fileWrapper.fileName, requestBody);
        }
        return MultipartBody.Part.createFormData(str, fileWrapper.fileName, new UploadProgressRequestBody(requestBody, progressResponseCallBack));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RequestBody getRequestBody(HttpParams.FileWrapper fileWrapper) {
        T t6 = fileWrapper.file;
        if (t6 instanceof File) {
            return RequestBody.create(fileWrapper.contentType, (File) t6);
        }
        if (t6 instanceof InputStream) {
            return RequestBodyUtils.create(fileWrapper.contentType, (InputStream) t6);
        }
        if (t6 instanceof byte[]) {
            return RequestBody.create(fileWrapper.contentType, (byte[]) t6);
        }
        return null;
    }

    public R addFileParams(String str, List<File> list, ProgressResponseCallBack progressResponseCallBack) {
        this.params.putFileParams(str, list, progressResponseCallBack);
        return this;
    }

    public R addFileWrapperParams(String str, List<HttpParams.FileWrapper> list) {
        this.params.putFileWrapperParams(str, list);
        return this;
    }

    @Override // com.ctvit.network.request.BaseRequest
    public AbstractC2120l<ResponseBody> generateRequest() {
        RequestBody requestBody = this.requestBody;
        if (requestBody != null) {
            return this.apiManager.postBody(this.url, requestBody);
        }
        if (this.json != null) {
            return this.apiManager.postJson(this.url, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), this.json));
        }
        Object obj = this.object;
        if (obj != null) {
            return this.apiManager.postBody(this.url, obj);
        }
        String str = this.string;
        if (str != null) {
            return this.apiManager.postBody(this.url, RequestBody.create(this.mediaType, str));
        }
        if (this.f961bs != null) {
            return this.apiManager.postBody(this.url, RequestBody.create(MediaType.parse(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE), this.f961bs));
        }
        return this.params.fileParamsMap.isEmpty() ? this.apiManager.post(this.url, this.params.urlParamsMap) : this.currentUploadType == UploadType.PART ? uploadFilesWithParts() : uploadFilesWithBodys();
    }

    public R params(String str, File file, ProgressResponseCallBack progressResponseCallBack) {
        this.params.put(str, file, progressResponseCallBack);
        return this;
    }

    public R requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public R upBytes(byte[] bArr) {
        this.f961bs = bArr;
        return this;
    }

    public R upJson(String str) {
        this.json = str;
        return this;
    }

    public R upObject(@Body Object obj) {
        this.object = obj;
        return this;
    }

    public R upString(String str) {
        this.string = str;
        this.mediaType = MediaType.parse("text/plain");
        return this;
    }

    public AbstractC2120l<ResponseBody> uploadFilesWithBodys() {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : this.params.urlParamsMap.entrySet()) {
            map.put(entry.getKey(), RequestBody.create(MediaType.parse("text/plain"), entry.getValue()));
        }
        for (Map.Entry<String, List<HttpParams.FileWrapper>> entry2 : this.params.fileParamsMap.entrySet()) {
            for (HttpParams.FileWrapper fileWrapper : entry2.getValue()) {
                map.put(entry2.getKey(), new UploadProgressRequestBody(getRequestBody(fileWrapper), fileWrapper.responseCallBack));
            }
        }
        return this.apiManager.uploadFiles(this.url, map);
    }

    public AbstractC2120l<ResponseBody> uploadFilesWithParts() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : this.params.urlParamsMap.entrySet()) {
            arrayList.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<String, List<HttpParams.FileWrapper>> entry2 : this.params.fileParamsMap.entrySet()) {
            Iterator<HttpParams.FileWrapper> it = entry2.getValue().iterator();
            while (it.hasNext()) {
                arrayList.add(addFile(entry2.getKey(), it.next()));
            }
        }
        return this.apiManager.uploadFiles(this.url, arrayList);
    }

    public <T> R uploadType(UploadType uploadType) {
        this.currentUploadType = uploadType;
        return this;
    }

    public R params(String str, InputStream inputStream, String str2, ProgressResponseCallBack progressResponseCallBack) {
        this.params.put(str, (String) inputStream, str2, progressResponseCallBack);
        return this;
    }

    public R params(String str, byte[] bArr, String str2, ProgressResponseCallBack progressResponseCallBack) {
        this.params.put(str, bArr, str2, progressResponseCallBack);
        return this;
    }

    public R upString(String str, String str2) {
        this.string = str;
        Utils.checkNotNull(str2, "mediaType==null");
        this.mediaType = MediaType.parse(str2);
        return this;
    }

    public R params(String str, File file, String str2, ProgressResponseCallBack progressResponseCallBack) {
        this.params.put(str, (String) file, str2, progressResponseCallBack);
        return this;
    }

    public <T> R params(String str, T t6, String str2, MediaType mediaType, ProgressResponseCallBack progressResponseCallBack) {
        this.params.put(str, t6, str2, mediaType, progressResponseCallBack);
        return this;
    }
}
