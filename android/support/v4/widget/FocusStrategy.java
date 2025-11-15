package android.support.v4.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
class FocusStrategy {

    public interface BoundsAdapter<T> {
        void obtainBounds(T t6, Rect rect);
    }

    public interface CollectionAdapter<T, V> {
        V get(T t6, int i7);

        int size(T t6);
    }

    public static class SequentialComparator<T> implements Comparator<T> {
        private final BoundsAdapter<T> mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1 = new Rect();
        private final Rect mTemp2 = new Rect();

        public SequentialComparator(boolean z6, BoundsAdapter<T> boundsAdapter) {
            this.mIsLayoutRtl = z6;
            this.mAdapter = boundsAdapter;
        }

        @Override // java.util.Comparator
        public int compare(T t6, T t7) {
            Rect rect = this.mTemp1;
            Rect rect2 = this.mTemp2;
            this.mAdapter.obtainBounds(t6, rect);
            this.mAdapter.obtainBounds(t7, rect2);
            int i7 = rect.top;
            int i8 = rect2.top;
            if (i7 < i8) {
                return -1;
            }
            if (i7 > i8) {
                return 1;
            }
            int i9 = rect.left;
            int i10 = rect2.left;
            if (i9 < i10) {
                return this.mIsLayoutRtl ? 1 : -1;
            }
            if (i9 > i10) {
                return this.mIsLayoutRtl ? -1 : 1;
            }
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            if (i11 < i12) {
                return -1;
            }
            if (i11 > i12) {
                return 1;
            }
            int i13 = rect.right;
            int i14 = rect2.right;
            if (i13 < i14) {
                return this.mIsLayoutRtl ? 1 : -1;
            }
            if (i13 > i14) {
                return this.mIsLayoutRtl ? -1 : 1;
            }
            return 0;
        }
    }

    private FocusStrategy() {
    }

    private static boolean beamBeats(int i7, @NonNull Rect rect, @NonNull Rect rect2, @NonNull Rect rect3) {
        boolean zBeamsOverlap = beamsOverlap(i7, rect, rect2);
        if (beamsOverlap(i7, rect, rect3) || !zBeamsOverlap) {
            return false;
        }
        return !isToDirectionOf(i7, rect, rect3) || i7 == 17 || i7 == 66 || majorAxisDistance(i7, rect, rect2) < majorAxisDistanceToFarEdge(i7, rect, rect3);
    }

    private static boolean beamsOverlap(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static <L, T> T findNextFocusInAbsoluteDirection(@NonNull L l7, @NonNull CollectionAdapter<L, T> collectionAdapter, @NonNull BoundsAdapter<T> boundsAdapter, @Nullable T t6, @NonNull Rect rect, int i7) {
        Rect rect2 = new Rect(rect);
        if (i7 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i7 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i7 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect2.offset(0, -(rect.height() + 1));
        }
        T t7 = null;
        int size = collectionAdapter.size(l7);
        Rect rect3 = new Rect();
        for (int i8 = 0; i8 < size; i8++) {
            T t8 = collectionAdapter.get(l7, i8);
            if (t8 != t6) {
                boundsAdapter.obtainBounds(t8, rect3);
                if (isBetterCandidate(i7, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t7 = t8;
                }
            }
        }
        return t7;
    }

    public static <L, T> T findNextFocusInRelativeDirection(@NonNull L l7, @NonNull CollectionAdapter<L, T> collectionAdapter, @NonNull BoundsAdapter<T> boundsAdapter, @Nullable T t6, int i7, boolean z6, boolean z7) {
        int size = collectionAdapter.size(l7);
        ArrayList arrayList = new ArrayList(size);
        for (int i8 = 0; i8 < size; i8++) {
            arrayList.add(collectionAdapter.get(l7, i8));
        }
        Collections.sort(arrayList, new SequentialComparator(z6, boundsAdapter));
        if (i7 == 1) {
            return (T) getPreviousFocusable(t6, arrayList, z7);
        }
        if (i7 == 2) {
            return (T) getNextFocusable(t6, arrayList, z7);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    private static <T> T getNextFocusable(T t6, ArrayList<T> arrayList, boolean z6) {
        int size = arrayList.size();
        int iLastIndexOf = (t6 == null ? -1 : arrayList.lastIndexOf(t6)) + 1;
        if (iLastIndexOf < size) {
            return arrayList.get(iLastIndexOf);
        }
        if (!z6 || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    private static <T> T getPreviousFocusable(T t6, ArrayList<T> arrayList, boolean z6) {
        int size = arrayList.size();
        int iIndexOf = (t6 == null ? size : arrayList.indexOf(t6)) - 1;
        if (iIndexOf >= 0) {
            return arrayList.get(iIndexOf);
        }
        if (!z6 || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    private static int getWeightedDistanceFor(int i7, int i8) {
        return (i8 * i8) + (i7 * 13 * i7);
    }

    private static boolean isBetterCandidate(int i7, @NonNull Rect rect, @NonNull Rect rect2, @NonNull Rect rect3) {
        if (!isCandidate(rect, rect2, i7)) {
            return false;
        }
        if (isCandidate(rect, rect3, i7) && !beamBeats(i7, rect, rect2, rect3)) {
            return !beamBeats(i7, rect, rect3, rect2) && getWeightedDistanceFor(majorAxisDistance(i7, rect, rect2), minorAxisDistance(i7, rect, rect2)) < getWeightedDistanceFor(majorAxisDistance(i7, rect, rect3), minorAxisDistance(i7, rect, rect3));
        }
        return true;
    }

    private static boolean isCandidate(@NonNull Rect rect, @NonNull Rect rect2, int i7) {
        if (i7 == 17) {
            int i8 = rect.right;
            int i9 = rect2.right;
            return (i8 > i9 || rect.left >= i9) && rect.left > rect2.left;
        }
        if (i7 == 33) {
            int i10 = rect.bottom;
            int i11 = rect2.bottom;
            return (i10 > i11 || rect.top >= i11) && rect.top > rect2.top;
        }
        if (i7 == 66) {
            int i12 = rect.left;
            int i13 = rect2.left;
            return (i12 < i13 || rect.right <= i13) && rect.right < rect2.right;
        }
        if (i7 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i14 = rect.top;
        int i15 = rect2.top;
        return (i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom;
    }

    private static boolean isToDirectionOf(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        if (i7 == 17) {
            return rect.left >= rect2.right;
        }
        if (i7 == 33) {
            return rect.top >= rect2.bottom;
        }
        if (i7 == 66) {
            return rect.right <= rect2.left;
        }
        if (i7 == 130) {
            return rect.bottom <= rect2.top;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    }

    private static int majorAxisDistance(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        return Math.max(0, majorAxisDistanceRaw(i7, rect, rect2));
    }

    private static int majorAxisDistanceRaw(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        int i8;
        int i9;
        if (i7 == 17) {
            i8 = rect.left;
            i9 = rect2.right;
        } else if (i7 == 33) {
            i8 = rect.top;
            i9 = rect2.bottom;
        } else if (i7 == 66) {
            i8 = rect2.left;
            i9 = rect.right;
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i8 = rect2.top;
            i9 = rect.bottom;
        }
        return i8 - i9;
    }

    private static int majorAxisDistanceToFarEdge(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(i7, rect, rect2));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        int i8;
        int i9;
        if (i7 == 17) {
            i8 = rect.left;
            i9 = rect2.left;
        } else if (i7 == 33) {
            i8 = rect.top;
            i9 = rect2.top;
        } else if (i7 == 66) {
            i8 = rect2.right;
            i9 = rect.right;
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i8 = rect2.bottom;
            i9 = rect.bottom;
        }
        return i8 - i9;
    }

    private static int minorAxisDistance(int i7, @NonNull Rect rect, @NonNull Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }
}
