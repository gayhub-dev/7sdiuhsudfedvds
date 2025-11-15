package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    private AccessibilityServiceInfoCompat() {
    }

    @NonNull
    public static String capabilityToString(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 4 ? i7 != 8 ? "UNKNOWN" : "CAPABILITY_CAN_FILTER_KEY_EVENTS" : "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY" : "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION" : "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
    }

    @NonNull
    public static String feedbackTypeToString(int i7) {
        StringBuilder sbM112a = C0413b.m112a("[");
        while (i7 > 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i7);
            i7 &= ~iNumberOfTrailingZeros;
            if (sbM112a.length() > 1) {
                sbM112a.append(", ");
            }
            if (iNumberOfTrailingZeros == 1) {
                sbM112a.append("FEEDBACK_SPOKEN");
            } else if (iNumberOfTrailingZeros == 2) {
                sbM112a.append("FEEDBACK_HAPTIC");
            } else if (iNumberOfTrailingZeros == 4) {
                sbM112a.append("FEEDBACK_AUDIBLE");
            } else if (iNumberOfTrailingZeros == 8) {
                sbM112a.append("FEEDBACK_VISUAL");
            } else if (iNumberOfTrailingZeros == 16) {
                sbM112a.append("FEEDBACK_GENERIC");
            }
        }
        sbM112a.append("]");
        return sbM112a.toString();
    }

    @Nullable
    public static String flagToString(int i7) {
        if (i7 == 1) {
            return "DEFAULT";
        }
        if (i7 == 2) {
            return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
        }
        if (i7 == 4) {
            return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
        }
        if (i7 == 8) {
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        }
        if (i7 == 16) {
            return "FLAG_REPORT_VIEW_IDS";
        }
        if (i7 != 32) {
            return null;
        }
        return "FLAG_REQUEST_FILTER_KEY_EVENTS";
    }

    public static int getCapabilities(@NonNull AccessibilityServiceInfo accessibilityServiceInfo) {
        return accessibilityServiceInfo.getCapabilities();
    }

    @Nullable
    public static String loadDescription(@NonNull AccessibilityServiceInfo accessibilityServiceInfo, @NonNull PackageManager packageManager) {
        return accessibilityServiceInfo.loadDescription(packageManager);
    }
}
