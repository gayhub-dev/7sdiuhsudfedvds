package android.support.v4.provider;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.util.Base64;
import java.util.List;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class FontRequest {
    private final List<List<byte[]>> mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<List<byte[]>> list) {
        String str4 = (String) Preconditions.checkNotNull(str);
        this.mProviderAuthority = str4;
        String str5 = (String) Preconditions.checkNotNull(str2);
        this.mProviderPackage = str5;
        String str6 = (String) Preconditions.checkNotNull(str3);
        this.mQuery = str6;
        this.mCertificates = (List) Preconditions.checkNotNull(list);
        this.mCertificatesArray = 0;
        this.mIdentifier = str4 + "-" + str5 + "-" + str6;
    }

    @Nullable
    public List<List<byte[]>> getCertificates() {
        return this.mCertificates;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getIdentifier() {
        return this.mIdentifier;
    }

    @NonNull
    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    @NonNull
    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    @NonNull
    public String getQuery() {
        return this.mQuery;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbM112a = C0413b.m112a("FontRequest {mProviderAuthority: ");
        sbM112a.append(this.mProviderAuthority);
        sbM112a.append(", mProviderPackage: ");
        sbM112a.append(this.mProviderPackage);
        sbM112a.append(", mQuery: ");
        sbM112a.append(this.mQuery);
        sbM112a.append(", mCertificates:");
        sb.append(sbM112a.toString());
        for (int i7 = 0; i7 < this.mCertificates.size(); i7++) {
            sb.append(" [");
            List<byte[]> list = this.mCertificates.get(i7);
            for (int i8 = 0; i8 < list.size(); i8++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i8), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.mCertificatesArray);
        return sb.toString();
    }

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @ArrayRes int i7) {
        String str4 = (String) Preconditions.checkNotNull(str);
        this.mProviderAuthority = str4;
        String str5 = (String) Preconditions.checkNotNull(str2);
        this.mProviderPackage = str5;
        String str6 = (String) Preconditions.checkNotNull(str3);
        this.mQuery = str6;
        this.mCertificates = null;
        Preconditions.checkArgument(i7 != 0);
        this.mCertificatesArray = i7;
        this.mIdentifier = str4 + "-" + str5 + "-" + str6;
    }
}
