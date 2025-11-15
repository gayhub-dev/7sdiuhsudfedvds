package org.fourthline.cling.support.model;

import android.arch.lifecycle.C0063n;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SortCriterion {
    public final boolean ascending;
    public final String propertyName;

    public SortCriterion(boolean z6, String str) {
        this.ascending = z6;
        this.propertyName = str;
    }

    public static String toString(SortCriterion[] sortCriterionArr) {
        if (sortCriterionArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (SortCriterion sortCriterion : sortCriterionArr) {
            sb.append(sortCriterion.toString());
            sb.append(",");
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static SortCriterion[] valueOf(String str) {
        if (str == null || str.length() == 0) {
            return new SortCriterion[0];
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            arrayList.add(new SortCriterion(str2.trim()));
        }
        return (SortCriterion[]) arrayList.toArray(new SortCriterion[arrayList.size()]);
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public boolean isAscending() {
        return this.ascending;
    }

    public SortCriterion(String str) {
        this(str.startsWith("+"), str.substring(1));
        if (!str.startsWith("-") && !str.startsWith("+")) {
            throw new IllegalArgumentException(C0063n.m88a("Missing sort prefix +/- on criterion: ", str));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ascending ? "+" : "-");
        sb.append(this.propertyName);
        return sb.toString();
    }
}
