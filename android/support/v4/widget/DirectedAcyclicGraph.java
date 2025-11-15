package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Pools;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class DirectedAcyclicGraph<T> {
    private final Pools.Pool<ArrayList<T>> mListPool = new Pools.SimplePool(10);
    private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap<>();
    private final ArrayList<T> mSortResult = new ArrayList<>();
    private final HashSet<T> mSortTmpMarked = new HashSet<>();

    private void dfs(T t6, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t6)) {
            return;
        }
        if (hashSet.contains(t6)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t6);
        ArrayList<T> arrayList2 = this.mGraph.get(t6);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i7 = 0; i7 < size; i7++) {
                dfs(arrayList2.get(i7), arrayList, hashSet);
            }
        }
        hashSet.remove(t6);
        arrayList.add(t6);
    }

    @NonNull
    private ArrayList<T> getEmptyList() {
        ArrayList<T> arrayListAcquire = this.mListPool.acquire();
        return arrayListAcquire == null ? new ArrayList<>() : arrayListAcquire;
    }

    private void poolList(@NonNull ArrayList<T> arrayList) {
        arrayList.clear();
        this.mListPool.release(arrayList);
    }

    public void addEdge(@NonNull T t6, @NonNull T t7) {
        if (!this.mGraph.containsKey(t6) || !this.mGraph.containsKey(t7)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> emptyList = this.mGraph.get(t6);
        if (emptyList == null) {
            emptyList = getEmptyList();
            this.mGraph.put(t6, emptyList);
        }
        emptyList.add(t7);
    }

    public void addNode(@NonNull T t6) {
        if (this.mGraph.containsKey(t6)) {
            return;
        }
        this.mGraph.put(t6, null);
    }

    public void clear() {
        int size = this.mGraph.size();
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList<T> arrayListValueAt = this.mGraph.valueAt(i7);
            if (arrayListValueAt != null) {
                poolList(arrayListValueAt);
            }
        }
        this.mGraph.clear();
    }

    public boolean contains(@NonNull T t6) {
        return this.mGraph.containsKey(t6);
    }

    @Nullable
    public List getIncomingEdges(@NonNull T t6) {
        return this.mGraph.get(t6);
    }

    @Nullable
    public List<T> getOutgoingEdges(@NonNull T t6) {
        int size = this.mGraph.size();
        ArrayList arrayList = null;
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList<T> arrayListValueAt = this.mGraph.valueAt(i7);
            if (arrayListValueAt != null && arrayListValueAt.contains(t6)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.mGraph.keyAt(i7));
            }
        }
        return arrayList;
    }

    @NonNull
    public ArrayList<T> getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int size = this.mGraph.size();
        for (int i7 = 0; i7 < size; i7++) {
            dfs(this.mGraph.keyAt(i7), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }

    public boolean hasOutgoingEdges(@NonNull T t6) {
        int size = this.mGraph.size();
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList<T> arrayListValueAt = this.mGraph.valueAt(i7);
            if (arrayListValueAt != null && arrayListValueAt.contains(t6)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.mGraph.size();
    }
}
