package android.support.v7.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.graphics.drawable.C0116a;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() { // from class: android.support.v7.util.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Snake snake, Snake snake2) {
            int i7 = snake.f167x - snake2.f167x;
            return i7 == 0 ? snake.f168y - snake2.f168y : i7;
        }
    };

    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i7, int i8);

        public abstract boolean areItemsTheSame(int i7, int i8);

        @Nullable
        public Object getChangePayload(int i7, int i8) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        public DiffResult(Callback callback, List<Snake> list, int[] iArr, int[] iArr2, boolean z6) {
            this.mSnakes = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z6;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (snake != null && snake.f167x == 0 && snake.f168y == 0) {
                return;
            }
            Snake snake2 = new Snake();
            snake2.f167x = 0;
            snake2.f168y = 0;
            snake2.removal = false;
            snake2.size = 0;
            snake2.reverse = false;
            this.mSnakes.add(0, snake2);
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i7, int i8, int i9) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onInserted(i7, i8);
                return;
            }
            for (int i10 = i8 - 1; i10 >= 0; i10--) {
                int[] iArr = this.mNewItemStatuses;
                int i11 = i9 + i10;
                int i12 = iArr[i11] & 31;
                if (i12 == 0) {
                    listUpdateCallback.onInserted(i7, 1);
                    Iterator<PostponedUpdate> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().currentPos++;
                    }
                } else if (i12 == 4 || i12 == 8) {
                    int i13 = iArr[i11] >> 5;
                    listUpdateCallback.onMoved(removePostponedUpdate(list, i13, true).currentPos, i7);
                    if (i12 == 4) {
                        listUpdateCallback.onChanged(i7, 1, this.mCallback.getChangePayload(i13, i11));
                    }
                } else {
                    if (i12 != 16) {
                        StringBuilder sbM98a = C0116a.m98a("unknown flag for pos ", i11, " ");
                        sbM98a.append(Long.toBinaryString(i12));
                        throw new IllegalStateException(sbM98a.toString());
                    }
                    list.add(new PostponedUpdate(i11, i7, false));
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i7, int i8, int i9) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onRemoved(i7, i8);
                return;
            }
            for (int i10 = i8 - 1; i10 >= 0; i10--) {
                int[] iArr = this.mOldItemStatuses;
                int i11 = i9 + i10;
                int i12 = iArr[i11] & 31;
                if (i12 == 0) {
                    listUpdateCallback.onRemoved(i7 + i10, 1);
                    Iterator<PostponedUpdate> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().currentPos--;
                    }
                } else if (i12 == 4 || i12 == 8) {
                    int i13 = iArr[i11] >> 5;
                    PostponedUpdate postponedUpdateRemovePostponedUpdate = removePostponedUpdate(list, i13, false);
                    listUpdateCallback.onMoved(i7 + i10, postponedUpdateRemovePostponedUpdate.currentPos - 1);
                    if (i12 == 4) {
                        listUpdateCallback.onChanged(postponedUpdateRemovePostponedUpdate.currentPos - 1, 1, this.mCallback.getChangePayload(i11, i13));
                    }
                } else {
                    if (i12 != 16) {
                        StringBuilder sbM98a = C0116a.m98a("unknown flag for pos ", i11, " ");
                        sbM98a.append(Long.toBinaryString(i12));
                        throw new IllegalStateException(sbM98a.toString());
                    }
                    list.add(new PostponedUpdate(i11, i7 + i10, true));
                }
            }
        }

        private void findAddition(int i7, int i8, int i9) {
            if (this.mOldItemStatuses[i7 - 1] != 0) {
                return;
            }
            findMatchingItem(i7, i8, i9, false);
        }

        private boolean findMatchingItem(int i7, int i8, int i9, boolean z6) {
            int i10;
            int i11;
            int i12;
            if (z6) {
                i8--;
                i11 = i7;
                i10 = i8;
            } else {
                i10 = i7 - 1;
                i11 = i10;
            }
            while (i9 >= 0) {
                Snake snake = this.mSnakes.get(i9);
                int i13 = snake.f167x;
                int i14 = snake.size;
                int i15 = i13 + i14;
                int i16 = snake.f168y + i14;
                if (z6) {
                    for (int i17 = i11 - 1; i17 >= i15; i17--) {
                        if (this.mCallback.areItemsTheSame(i17, i10)) {
                            i12 = this.mCallback.areContentsTheSame(i17, i10) ? 8 : 4;
                            this.mNewItemStatuses[i10] = (i17 << 5) | 16;
                            this.mOldItemStatuses[i17] = (i10 << 5) | i12;
                            return true;
                        }
                    }
                } else {
                    for (int i18 = i8 - 1; i18 >= i16; i18--) {
                        if (this.mCallback.areItemsTheSame(i10, i18)) {
                            i12 = this.mCallback.areContentsTheSame(i10, i18) ? 8 : 4;
                            int i19 = i7 - 1;
                            this.mOldItemStatuses[i19] = (i18 << 5) | 16;
                            this.mNewItemStatuses[i18] = (i19 << 5) | i12;
                            return true;
                        }
                    }
                }
                i11 = snake.f167x;
                i8 = snake.f168y;
                i9--;
            }
            return false;
        }

        private void findMatchingItems() {
            int i7 = this.mOldListSize;
            int i8 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i9 = snake.f167x;
                int i10 = snake.size;
                int i11 = i9 + i10;
                int i12 = snake.f168y + i10;
                if (this.mDetectMoves) {
                    while (i7 > i11) {
                        findAddition(i7, i8, size);
                        i7--;
                    }
                    while (i8 > i12) {
                        findRemoval(i7, i8, size);
                        i8--;
                    }
                }
                for (int i13 = 0; i13 < snake.size; i13++) {
                    int i14 = snake.f167x + i13;
                    int i15 = snake.f168y + i13;
                    int i16 = this.mCallback.areContentsTheSame(i14, i15) ? 1 : 2;
                    this.mOldItemStatuses[i14] = (i15 << 5) | i16;
                    this.mNewItemStatuses[i15] = (i14 << 5) | i16;
                }
                i7 = snake.f167x;
                i8 = snake.f168y;
            }
        }

        private void findRemoval(int i7, int i8, int i9) {
            if (this.mNewItemStatuses[i8 - 1] != 0) {
                return;
            }
            findMatchingItem(i7, i8, i9, true);
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i7, boolean z6) {
            int size = list.size() - 1;
            while (size >= 0) {
                PostponedUpdate postponedUpdate = list.get(size);
                if (postponedUpdate.posInOwnerList == i7 && postponedUpdate.removal == z6) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).currentPos += z6 ? 1 : -1;
                        size++;
                    }
                    return postponedUpdate;
                }
                size--;
            }
            return null;
        }

        public int convertNewPositionToOld(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7) {
            if (i7 >= 0) {
                int[] iArr = this.mNewItemStatuses;
                if (i7 < iArr.length) {
                    int i8 = iArr[i7];
                    if ((i8 & 31) == 0) {
                        return -1;
                    }
                    return i8 >> 5;
                }
            }
            StringBuilder sbM98a = C0116a.m98a("Index out of bounds - passed position = ", i7, ", new list size = ");
            sbM98a.append(this.mNewItemStatuses.length);
            throw new IndexOutOfBoundsException(sbM98a.toString());
        }

        public int convertOldPositionToNew(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7) {
            if (i7 >= 0) {
                int[] iArr = this.mOldItemStatuses;
                if (i7 < iArr.length) {
                    int i8 = iArr[i7];
                    if ((i8 & 31) == 0) {
                        return -1;
                    }
                    return i8 >> 5;
                }
            }
            StringBuilder sbM98a = C0116a.m98a("Index out of bounds - passed position = ", i7, ", old list size = ");
            sbM98a.append(this.mOldItemStatuses.length);
            throw new IndexOutOfBoundsException(sbM98a.toString());
        }

        public void dispatchUpdatesTo(@NonNull RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        @VisibleForTesting
        public List<Snake> getSnakes() {
            return this.mSnakes;
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback = listUpdateCallback instanceof BatchingListUpdateCallback ? (BatchingListUpdateCallback) listUpdateCallback : new BatchingListUpdateCallback(listUpdateCallback);
            ArrayList arrayList = new ArrayList();
            int i7 = this.mOldListSize;
            int i8 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i9 = snake.size;
                int i10 = snake.f167x + i9;
                int i11 = snake.f168y + i9;
                if (i10 < i7) {
                    dispatchRemovals(arrayList, batchingListUpdateCallback, i10, i7 - i10, i10);
                }
                if (i11 < i8) {
                    dispatchAdditions(arrayList, batchingListUpdateCallback, i10, i8 - i11, i11);
                }
                for (int i12 = i9 - 1; i12 >= 0; i12--) {
                    int[] iArr = this.mOldItemStatuses;
                    int i13 = snake.f167x;
                    if ((iArr[i13 + i12] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(i13 + i12, 1, this.mCallback.getChangePayload(i13 + i12, snake.f168y + i12));
                    }
                }
                i7 = snake.f167x;
                i8 = snake.f168y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }
    }

    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t6, @NonNull T t7);

        public abstract boolean areItemsTheSame(@NonNull T t6, @NonNull T t7);

        @Nullable
        public Object getChangePayload(@NonNull T t6, @NonNull T t7) {
            return null;
        }
    }

    public static class PostponedUpdate {
        public int currentPos;
        public int posInOwnerList;
        public boolean removal;

        public PostponedUpdate(int i7, int i8, boolean z6) {
            this.posInOwnerList = i7;
            this.currentPos = i8;
            this.removal = z6;
        }
    }

    public static class Range {
        public int newListEnd;
        public int newListStart;
        public int oldListEnd;
        public int oldListStart;

        public Range() {
        }

        public Range(int i7, int i8, int i9, int i10) {
            this.oldListStart = i7;
            this.oldListEnd = i8;
            this.newListStart = i9;
            this.newListEnd = i10;
        }
    }

    public static class Snake {
        public boolean removal;
        public boolean reverse;
        public int size;

        /* renamed from: x */
        public int f167x;

        /* renamed from: y */
        public int f168y;
    }

    private DiffUtil() {
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback) {
        return calculateDiff(callback, true);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:52:0x00cd
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:195)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:62)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:124)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:124)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.support.v7.util.DiffUtil.Snake diffPartial(android.support.v7.util.DiffUtil.Callback r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.util.DiffUtil.diffPartial(android.support.v7.util.DiffUtil$Callback, int, int, int, int, int[], int[], int):android.support.v7.util.DiffUtil$Snake");
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback, boolean z6) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int iAbs = Math.abs(oldListSize - newListSize) + oldListSize + newListSize;
        int i7 = iAbs * 2;
        int[] iArr = new int[i7];
        int[] iArr2 = new int[i7];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake snakeDiffPartial = diffPartial(callback, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, iArr, iArr2, iAbs);
            if (snakeDiffPartial != null) {
                if (snakeDiffPartial.size > 0) {
                    arrayList.add(snakeDiffPartial);
                }
                snakeDiffPartial.f167x += range.oldListStart;
                snakeDiffPartial.f168y += range.newListStart;
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                if (snakeDiffPartial.reverse) {
                    range2.oldListEnd = snakeDiffPartial.f167x;
                    range2.newListEnd = snakeDiffPartial.f168y;
                } else if (snakeDiffPartial.removal) {
                    range2.oldListEnd = snakeDiffPartial.f167x - 1;
                    range2.newListEnd = snakeDiffPartial.f168y;
                } else {
                    range2.oldListEnd = snakeDiffPartial.f167x;
                    range2.newListEnd = snakeDiffPartial.f168y - 1;
                }
                arrayList2.add(range2);
                if (!snakeDiffPartial.reverse) {
                    int i8 = snakeDiffPartial.f167x;
                    int i9 = snakeDiffPartial.size;
                    range.oldListStart = i8 + i9;
                    range.newListStart = snakeDiffPartial.f168y + i9;
                } else if (snakeDiffPartial.removal) {
                    int i10 = snakeDiffPartial.f167x;
                    int i11 = snakeDiffPartial.size;
                    range.oldListStart = i10 + i11 + 1;
                    range.newListStart = snakeDiffPartial.f168y + i11;
                } else {
                    int i12 = snakeDiffPartial.f167x;
                    int i13 = snakeDiffPartial.size;
                    range.oldListStart = i12 + i13;
                    range.newListStart = snakeDiffPartial.f168y + i13 + 1;
                }
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        return new DiffResult(callback, arrayList, iArr, iArr2, z6);
    }
}
