package com.alibaba.sdk.android.oss;

import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectRequest;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketLifecycleRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketLoggingRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteMultipleObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteMultipleObjectResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectTaggingRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectTaggingResult;
import com.alibaba.sdk.android.oss.model.GeneratePresignedUrlRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetBucketInfoRequest;
import com.alibaba.sdk.android.oss.model.GetBucketInfoResult;
import com.alibaba.sdk.android.oss.model.GetBucketLifecycleRequest;
import com.alibaba.sdk.android.oss.model.GetBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.GetBucketLoggingRequest;
import com.alibaba.sdk.android.oss.model.GetBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.GetBucketRefererRequest;
import com.alibaba.sdk.android.oss.model.GetBucketRefererResult;
import com.alibaba.sdk.android.oss.model.GetObjectACLRequest;
import com.alibaba.sdk.android.oss.model.GetObjectACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectMetaRequest;
import com.alibaba.sdk.android.oss.model.GetObjectMetaResult;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.GetObjectTaggingRequest;
import com.alibaba.sdk.android.oss.model.GetObjectTaggingResult;
import com.alibaba.sdk.android.oss.model.GetSymlinkRequest;
import com.alibaba.sdk.android.oss.model.GetSymlinkResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.ImagePersistRequest;
import com.alibaba.sdk.android.oss.model.ImagePersistResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListBucketsRequest;
import com.alibaba.sdk.android.oss.model.ListBucketsResult;
import com.alibaba.sdk.android.oss.model.ListMultipartUploadsRequest;
import com.alibaba.sdk.android.oss.model.ListMultipartUploadsResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.MultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.PutBucketLifecycleRequest;
import com.alibaba.sdk.android.oss.model.PutBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.PutBucketLoggingRequest;
import com.alibaba.sdk.android.oss.model.PutBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.PutBucketRefererRequest;
import com.alibaba.sdk.android.oss.model.PutBucketRefererResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectTaggingRequest;
import com.alibaba.sdk.android.oss.model.PutObjectTaggingResult;
import com.alibaba.sdk.android.oss.model.PutSymlinkRequest;
import com.alibaba.sdk.android.oss.model.PutSymlinkResult;
import com.alibaba.sdk.android.oss.model.RestoreObjectRequest;
import com.alibaba.sdk.android.oss.model.RestoreObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableDownloadRequest;
import com.alibaba.sdk.android.oss.model.ResumableDownloadResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.TriggerCallbackRequest;
import com.alibaba.sdk.android.oss.model.TriggerCallbackResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;

/* loaded from: classes.dex */
public interface OSS {
    AbortMultipartUploadResult abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest);

    void abortResumableUpload(ResumableUploadRequest resumableUploadRequest);

    AppendObjectResult appendObject(AppendObjectRequest appendObjectRequest);

    OSSAsyncTask<AbortMultipartUploadResult> asyncAbortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest, OSSCompletedCallback<AbortMultipartUploadRequest, AbortMultipartUploadResult> oSSCompletedCallback);

    OSSAsyncTask<AppendObjectResult> asyncAppendObject(AppendObjectRequest appendObjectRequest, OSSCompletedCallback<AppendObjectRequest, AppendObjectResult> oSSCompletedCallback);

    OSSAsyncTask<CompleteMultipartUploadResult> asyncCompleteMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest, OSSCompletedCallback<CompleteMultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback);

    OSSAsyncTask<CopyObjectResult> asyncCopyObject(CopyObjectRequest copyObjectRequest, OSSCompletedCallback<CopyObjectRequest, CopyObjectResult> oSSCompletedCallback);

    OSSAsyncTask<CreateBucketResult> asyncCreateBucket(CreateBucketRequest createBucketRequest, OSSCompletedCallback<CreateBucketRequest, CreateBucketResult> oSSCompletedCallback);

    OSSAsyncTask<DeleteBucketResult> asyncDeleteBucket(DeleteBucketRequest deleteBucketRequest, OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult> oSSCompletedCallback);

    OSSAsyncTask<DeleteBucketLifecycleResult> asyncDeleteBucketLifecycle(DeleteBucketLifecycleRequest deleteBucketLifecycleRequest, OSSCompletedCallback<DeleteBucketLifecycleRequest, DeleteBucketLifecycleResult> oSSCompletedCallback);

    OSSAsyncTask<DeleteBucketLoggingResult> asyncDeleteBucketLogging(DeleteBucketLoggingRequest deleteBucketLoggingRequest, OSSCompletedCallback<DeleteBucketLoggingRequest, DeleteBucketLoggingResult> oSSCompletedCallback);

    OSSAsyncTask<DeleteMultipleObjectResult> asyncDeleteMultipleObject(DeleteMultipleObjectRequest deleteMultipleObjectRequest, OSSCompletedCallback<DeleteMultipleObjectRequest, DeleteMultipleObjectResult> oSSCompletedCallback);

    OSSAsyncTask<DeleteObjectResult> asyncDeleteObject(DeleteObjectRequest deleteObjectRequest, OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult> oSSCompletedCallback);

    OSSAsyncTask<DeleteObjectTaggingResult> asyncDeleteObjectTagging(DeleteObjectTaggingRequest deleteObjectTaggingRequest, OSSCompletedCallback<DeleteObjectTaggingRequest, DeleteObjectTaggingResult> oSSCompletedCallback);

    OSSAsyncTask<GetBucketACLResult> asyncGetBucketACL(GetBucketACLRequest getBucketACLRequest, OSSCompletedCallback<GetBucketACLRequest, GetBucketACLResult> oSSCompletedCallback);

    OSSAsyncTask<GetBucketInfoResult> asyncGetBucketInfo(GetBucketInfoRequest getBucketInfoRequest, OSSCompletedCallback<GetBucketInfoRequest, GetBucketInfoResult> oSSCompletedCallback);

    OSSAsyncTask<GetBucketLifecycleResult> asyncGetBucketLifecycle(GetBucketLifecycleRequest getBucketLifecycleRequest, OSSCompletedCallback<GetBucketLifecycleRequest, GetBucketLifecycleResult> oSSCompletedCallback);

    OSSAsyncTask<GetBucketLoggingResult> asyncGetBucketLogging(GetBucketLoggingRequest getBucketLoggingRequest, OSSCompletedCallback<GetBucketLoggingRequest, GetBucketLoggingResult> oSSCompletedCallback);

    OSSAsyncTask<GetBucketRefererResult> asyncGetBucketReferer(GetBucketRefererRequest getBucketRefererRequest, OSSCompletedCallback<GetBucketRefererRequest, GetBucketRefererResult> oSSCompletedCallback);

    OSSAsyncTask<GetObjectResult> asyncGetObject(GetObjectRequest getObjectRequest, OSSCompletedCallback<GetObjectRequest, GetObjectResult> oSSCompletedCallback);

    OSSAsyncTask<GetObjectACLResult> asyncGetObjectACL(GetObjectACLRequest getObjectACLRequest, OSSCompletedCallback<GetObjectACLRequest, GetObjectACLResult> oSSCompletedCallback);

    OSSAsyncTask<GetObjectMetaResult> asyncGetObjectMeta(GetObjectMetaRequest getObjectMetaRequest, OSSCompletedCallback<GetObjectMetaRequest, GetObjectMetaResult> oSSCompletedCallback);

    OSSAsyncTask<GetObjectTaggingResult> asyncGetObjectTagging(GetObjectTaggingRequest getObjectTaggingRequest, OSSCompletedCallback<GetObjectTaggingRequest, GetObjectTaggingResult> oSSCompletedCallback);

    OSSAsyncTask<GetSymlinkResult> asyncGetSymlink(GetSymlinkRequest getSymlinkRequest, OSSCompletedCallback<GetSymlinkRequest, GetSymlinkResult> oSSCompletedCallback);

    OSSAsyncTask<HeadObjectResult> asyncHeadObject(HeadObjectRequest headObjectRequest, OSSCompletedCallback<HeadObjectRequest, HeadObjectResult> oSSCompletedCallback);

    OSSAsyncTask<ImagePersistResult> asyncImagePersist(ImagePersistRequest imagePersistRequest, OSSCompletedCallback<ImagePersistRequest, ImagePersistResult> oSSCompletedCallback);

    OSSAsyncTask<InitiateMultipartUploadResult> asyncInitMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest, OSSCompletedCallback<InitiateMultipartUploadRequest, InitiateMultipartUploadResult> oSSCompletedCallback);

    OSSAsyncTask<ListBucketsResult> asyncListBuckets(ListBucketsRequest listBucketsRequest, OSSCompletedCallback<ListBucketsRequest, ListBucketsResult> oSSCompletedCallback);

    OSSAsyncTask<ListMultipartUploadsResult> asyncListMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest, OSSCompletedCallback<ListMultipartUploadsRequest, ListMultipartUploadsResult> oSSCompletedCallback);

    OSSAsyncTask<ListObjectsResult> asyncListObjects(ListObjectsRequest listObjectsRequest, OSSCompletedCallback<ListObjectsRequest, ListObjectsResult> oSSCompletedCallback);

    OSSAsyncTask<ListPartsResult> asyncListParts(ListPartsRequest listPartsRequest, OSSCompletedCallback<ListPartsRequest, ListPartsResult> oSSCompletedCallback);

    OSSAsyncTask<CompleteMultipartUploadResult> asyncMultipartUpload(MultipartUploadRequest multipartUploadRequest, OSSCompletedCallback<MultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback);

    OSSAsyncTask<PutBucketLifecycleResult> asyncPutBucketLifecycle(PutBucketLifecycleRequest putBucketLifecycleRequest, OSSCompletedCallback<PutBucketLifecycleRequest, PutBucketLifecycleResult> oSSCompletedCallback);

    OSSAsyncTask<PutBucketLoggingResult> asyncPutBucketLogging(PutBucketLoggingRequest putBucketLoggingRequest, OSSCompletedCallback<PutBucketLoggingRequest, PutBucketLoggingResult> oSSCompletedCallback);

    OSSAsyncTask<PutBucketRefererResult> asyncPutBucketReferer(PutBucketRefererRequest putBucketRefererRequest, OSSCompletedCallback<PutBucketRefererRequest, PutBucketRefererResult> oSSCompletedCallback);

    OSSAsyncTask<PutObjectResult> asyncPutObject(PutObjectRequest putObjectRequest, OSSCompletedCallback<PutObjectRequest, PutObjectResult> oSSCompletedCallback);

    OSSAsyncTask<PutObjectTaggingResult> asyncPutObjectTagging(PutObjectTaggingRequest putObjectTaggingRequest, OSSCompletedCallback<PutObjectTaggingRequest, PutObjectTaggingResult> oSSCompletedCallback);

    OSSAsyncTask<PutSymlinkResult> asyncPutSymlink(PutSymlinkRequest putSymlinkRequest, OSSCompletedCallback<PutSymlinkRequest, PutSymlinkResult> oSSCompletedCallback);

    OSSAsyncTask<RestoreObjectResult> asyncRestoreObject(RestoreObjectRequest restoreObjectRequest, OSSCompletedCallback<RestoreObjectRequest, RestoreObjectResult> oSSCompletedCallback);

    OSSAsyncTask<ResumableDownloadResult> asyncResumableDownload(ResumableDownloadRequest resumableDownloadRequest, OSSCompletedCallback<ResumableDownloadRequest, ResumableDownloadResult> oSSCompletedCallback);

    OSSAsyncTask<ResumableUploadResult> asyncResumableUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback);

    OSSAsyncTask<ResumableUploadResult> asyncSequenceUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback);

    OSSAsyncTask<TriggerCallbackResult> asyncTriggerCallback(TriggerCallbackRequest triggerCallbackRequest, OSSCompletedCallback<TriggerCallbackRequest, TriggerCallbackResult> oSSCompletedCallback);

    OSSAsyncTask<UploadPartResult> asyncUploadPart(UploadPartRequest uploadPartRequest, OSSCompletedCallback<UploadPartRequest, UploadPartResult> oSSCompletedCallback);

    CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest);

    CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest);

    CreateBucketResult createBucket(CreateBucketRequest createBucketRequest);

    DeleteBucketResult deleteBucket(DeleteBucketRequest deleteBucketRequest);

    DeleteBucketLifecycleResult deleteBucketLifecycle(DeleteBucketLifecycleRequest deleteBucketLifecycleRequest);

    DeleteBucketLoggingResult deleteBucketLogging(DeleteBucketLoggingRequest deleteBucketLoggingRequest);

    DeleteMultipleObjectResult deleteMultipleObject(DeleteMultipleObjectRequest deleteMultipleObjectRequest);

    DeleteObjectResult deleteObject(DeleteObjectRequest deleteObjectRequest);

    DeleteObjectTaggingResult deleteObjectTagging(DeleteObjectTaggingRequest deleteObjectTaggingRequest);

    boolean doesObjectExist(String str, String str2);

    GetBucketACLResult getBucketACL(GetBucketACLRequest getBucketACLRequest);

    GetBucketInfoResult getBucketInfo(GetBucketInfoRequest getBucketInfoRequest);

    GetBucketLifecycleResult getBucketLifecycle(GetBucketLifecycleRequest getBucketLifecycleRequest);

    GetBucketLoggingResult getBucketLogging(GetBucketLoggingRequest getBucketLoggingRequest);

    GetBucketRefererResult getBucketReferer(GetBucketRefererRequest getBucketRefererRequest);

    GetObjectResult getObject(GetObjectRequest getObjectRequest);

    GetObjectACLResult getObjectACL(GetObjectACLRequest getObjectACLRequest);

    GetObjectMetaResult getObjectMeta(GetObjectMetaRequest getObjectMetaRequest);

    GetObjectTaggingResult getObjectTagging(GetObjectTaggingRequest getObjectTaggingRequest);

    GetSymlinkResult getSymlink(GetSymlinkRequest getSymlinkRequest);

    HeadObjectResult headObject(HeadObjectRequest headObjectRequest);

    ImagePersistResult imagePersist(ImagePersistRequest imagePersistRequest);

    InitiateMultipartUploadResult initMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest);

    ListBucketsResult listBuckets(ListBucketsRequest listBucketsRequest);

    ListMultipartUploadsResult listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest);

    ListObjectsResult listObjects(ListObjectsRequest listObjectsRequest);

    ListPartsResult listParts(ListPartsRequest listPartsRequest);

    CompleteMultipartUploadResult multipartUpload(MultipartUploadRequest multipartUploadRequest);

    String presignConstrainedObjectURL(GeneratePresignedUrlRequest generatePresignedUrlRequest);

    String presignConstrainedObjectURL(String str, String str2, long j7);

    String presignPublicObjectURL(String str, String str2);

    PutBucketLifecycleResult putBucketLifecycle(PutBucketLifecycleRequest putBucketLifecycleRequest);

    PutBucketLoggingResult putBucketLogging(PutBucketLoggingRequest putBucketLoggingRequest);

    PutBucketRefererResult putBucketReferer(PutBucketRefererRequest putBucketRefererRequest);

    PutObjectResult putObject(PutObjectRequest putObjectRequest);

    PutObjectTaggingResult putObjectTagging(PutObjectTaggingRequest putObjectTaggingRequest);

    PutSymlinkResult putSymlink(PutSymlinkRequest putSymlinkRequest);

    RestoreObjectResult restoreObject(RestoreObjectRequest restoreObjectRequest);

    ResumableUploadResult resumableUpload(ResumableUploadRequest resumableUploadRequest);

    ResumableUploadResult sequenceUpload(ResumableUploadRequest resumableUploadRequest);

    ResumableDownloadResult syncResumableDownload(ResumableDownloadRequest resumableDownloadRequest);

    TriggerCallbackResult triggerCallback(TriggerCallbackRequest triggerCallbackRequest);

    void updateCredentialProvider(OSSCredentialProvider oSSCredentialProvider);

    UploadPartResult uploadPart(UploadPartRequest uploadPartRequest);
}
