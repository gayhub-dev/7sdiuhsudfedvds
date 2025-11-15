package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.widget.ListView;

/* loaded from: classes.dex */
public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    public ListViewAutoScrollHelper(@NonNull ListView listView) {
        super(listView);
        this.mTarget = listView;
    }

    @Override // android.support.v4.widget.AutoScrollHelper
    public boolean canTargetScrollHorizontally(int i7) {
        return false;
    }

    @Override // android.support.v4.widget.AutoScrollHelper
    public boolean canTargetScrollVertically(int i7) {
        ListView listView = this.mTarget;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i8 = firstVisiblePosition + childCount;
        if (i7 > 0) {
            if (i8 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i7 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // android.support.v4.widget.AutoScrollHelper
    public void scrollTargetBy(int i7, int i8) {
        ListViewCompat.scrollListBy(this.mTarget, i8);
    }
}
