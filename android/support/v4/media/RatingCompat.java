package android.support.v4.media;

import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new Parcelable.Creator<RatingCompat>() { // from class: android.support.v4.media.RatingCompat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat[] newArray(int i7) {
            return new RatingCompat[i7];
        }
    };
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface StarStyle {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface Style {
    }

    public RatingCompat(int i7, float f7) {
        this.mRatingStyle = i7;
        this.mRatingValue = f7;
    }

    public static RatingCompat fromRating(Object obj) {
        RatingCompat ratingCompatNewUnratedRating = null;
        if (obj != null) {
            Rating rating = (Rating) obj;
            int ratingStyle = rating.getRatingStyle();
            if (rating.isRated()) {
                switch (ratingStyle) {
                    case 1:
                        ratingCompatNewUnratedRating = newHeartRating(rating.hasHeart());
                        break;
                    case 2:
                        ratingCompatNewUnratedRating = newThumbRating(rating.isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        ratingCompatNewUnratedRating = newStarRating(ratingStyle, rating.getStarRating());
                        break;
                    case 6:
                        ratingCompatNewUnratedRating = newPercentageRating(rating.getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                ratingCompatNewUnratedRating = newUnratedRating(ratingStyle);
            }
            ratingCompatNewUnratedRating.mRatingObj = obj;
        }
        return ratingCompatNewUnratedRating;
    }

    public static RatingCompat newHeartRating(boolean z6) {
        return new RatingCompat(1, z6 ? 1.0f : 0.0f);
    }

    public static RatingCompat newPercentageRating(float f7) {
        if (f7 < 0.0f || f7 > 100.0f) {
            return null;
        }
        return new RatingCompat(6, f7);
    }

    public static RatingCompat newStarRating(int i7, float f7) {
        float f8;
        if (i7 == 3) {
            f8 = 3.0f;
        } else if (i7 == 4) {
            f8 = 4.0f;
        } else {
            if (i7 != 5) {
                return null;
            }
            f8 = 5.0f;
        }
        if (f7 < 0.0f || f7 > f8) {
            return null;
        }
        return new RatingCompat(i7, f7);
    }

    public static RatingCompat newThumbRating(boolean z6) {
        return new RatingCompat(2, z6 ? 1.0f : 0.0f);
    }

    public static RatingCompat newUnratedRating(int i7) {
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new RatingCompat(i7, -1.0f);
            default:
                return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.mRatingStyle;
    }

    public float getPercentRating() {
        if (this.mRatingStyle == 6 && isRated()) {
            return this.mRatingValue;
        }
        return -1.0f;
    }

    public Object getRating() {
        if (this.mRatingObj == null) {
            if (isRated()) {
                int i7 = this.mRatingStyle;
                switch (i7) {
                    case 1:
                        this.mRatingObj = Rating.newHeartRating(hasHeart());
                        break;
                    case 2:
                        this.mRatingObj = Rating.newThumbRating(isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.mRatingObj = Rating.newStarRating(i7, getStarRating());
                        break;
                    case 6:
                        this.mRatingObj = Rating.newPercentageRating(getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                this.mRatingObj = Rating.newUnratedRating(this.mRatingStyle);
            }
        }
        return this.mRatingObj;
    }

    public int getRatingStyle() {
        return this.mRatingStyle;
    }

    public float getStarRating() {
        int i7 = this.mRatingStyle;
        if ((i7 == 3 || i7 == 4 || i7 == 5) && isRated()) {
            return this.mRatingValue;
        }
        return -1.0f;
    }

    public boolean hasHeart() {
        return this.mRatingStyle == 1 && this.mRatingValue == 1.0f;
    }

    public boolean isRated() {
        return this.mRatingValue >= 0.0f;
    }

    public boolean isThumbUp() {
        return this.mRatingStyle == 2 && this.mRatingValue == 1.0f;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Rating:style=");
        sbM112a.append(this.mRatingStyle);
        sbM112a.append(" rating=");
        float f7 = this.mRatingValue;
        sbM112a.append(f7 < 0.0f ? "unrated" : String.valueOf(f7));
        return sbM112a.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.mRatingStyle);
        parcel.writeFloat(this.mRatingValue);
    }
}
