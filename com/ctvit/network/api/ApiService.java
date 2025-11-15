package com.ctvit.network.api;

import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p194y3.AbstractC2120l;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* loaded from: classes.dex */
public interface ApiService {
    @DELETE
    AbstractC2120l<ResponseBody> delete(@Url String str, @QueryMap Map<String, String> map);

    @HTTP(hasBody = true, method = "DELETE")
    AbstractC2120l<ResponseBody> deleteBody(@Url String str, @Body Object obj);

    @HTTP(hasBody = true, method = "DELETE")
    AbstractC2120l<ResponseBody> deleteBody(@Url String str, @Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @HTTP(hasBody = true, method = "DELETE")
    AbstractC2120l<ResponseBody> deleteJson(@Url String str, @Body RequestBody requestBody);

    @Streaming
    @GET
    AbstractC2120l<ResponseBody> downloadFile(@Url String str);

    @GET
    AbstractC2120l<ResponseBody> get(@Url String str, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    AbstractC2120l<ResponseBody> post(@Url String str, @FieldMap Map<String, String> map);

    @POST
    AbstractC2120l<ResponseBody> postBody(@Url String str, @Body Object obj);

    @POST
    AbstractC2120l<ResponseBody> postBody(@Url String str, @Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST
    AbstractC2120l<ResponseBody> postJson(@Url String str, @Body RequestBody requestBody);

    @PUT
    AbstractC2120l<ResponseBody> put(@Url String str, @QueryMap Map<String, String> map);

    @PUT
    AbstractC2120l<ResponseBody> putBody(@Url String str, @Body Object obj);

    @PUT
    AbstractC2120l<ResponseBody> putBody(@Url String str, @Body RequestBody requestBody);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @PUT
    AbstractC2120l<ResponseBody> putJson(@Url String str, @Body RequestBody requestBody);

    @POST
    @Multipart
    AbstractC2120l<ResponseBody> uploadFiles(@Url String str, @Part List<MultipartBody.Part> list);

    @POST
    @Multipart
    AbstractC2120l<ResponseBody> uploadFiles(@Url String str, @PartMap Map<String, RequestBody> map);

    @POST
    @Multipart
    AbstractC2120l<ResponseBody> uploadFlie(@Url String str, @Part("description") RequestBody requestBody, @Part("files") MultipartBody.Part part);
}
