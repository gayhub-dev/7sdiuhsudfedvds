package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AccessibilityNodeProviderCompat {
    public static final int HOST_VIEW_ID = -1;
    private final Object mProvider;

    @RequiresApi(16)
    public static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
        public final AccessibilityNodeProviderCompat mCompat;

        public AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            this.mCompat = accessibilityNodeProviderCompat;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i7) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateAccessibilityNodeInfo = this.mCompat.createAccessibilityNodeInfo(i7);
            if (accessibilityNodeInfoCompatCreateAccessibilityNodeInfo == null) {
                return null;
            }
            return accessibilityNodeInfoCompatCreateAccessibilityNodeInfo.unwrap();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i7) {
            List<AccessibilityNodeInfoCompat> listFindAccessibilityNodeInfosByText = this.mCompat.findAccessibilityNodeInfosByText(str, i7);
            if (listFindAccessibilityNodeInfosByText == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listFindAccessibilityNodeInfosByText.size();
            for (int i8 = 0; i8 < size; i8++) {
                arrayList.add(listFindAccessibilityNodeInfosByText.get(i8).unwrap());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i7, int i8, Bundle bundle) {
            return this.mCompat.performAction(i7, i8, bundle);
        }
    }

    @RequiresApi(19)
    public static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
        public AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            super(accessibilityNodeProviderCompat);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i7) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatFindFocus = this.mCompat.findFocus(i7);
            if (accessibilityNodeInfoCompatFindFocus == null) {
                return null;
            }
            return accessibilityNodeInfoCompatFindFocus.unwrap();
        }
    }

    public AccessibilityNodeProviderCompat() {
        this.mProvider = new AccessibilityNodeProviderApi19(this);
    }

    @Nullable
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i7) {
        return null;
    }

    @Nullable
    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String str, int i7) {
        return null;
    }

    @Nullable
    public AccessibilityNodeInfoCompat findFocus(int i7) {
        return null;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    public boolean performAction(int i7, int i8, Bundle bundle) {
        return false;
    }

    public AccessibilityNodeProviderCompat(Object obj) {
        this.mProvider = obj;
    }
}
