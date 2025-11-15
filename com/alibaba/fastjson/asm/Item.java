package com.alibaba.fastjson.asm;

import android.support.v7.widget.ActivityChooserView;

/* loaded from: classes.dex */
final class Item {
    public int hashCode;
    public int index;
    public int intVal;
    public long longVal;
    public Item next;
    public String strVal1;
    public String strVal2;
    public String strVal3;
    public int type;

    public Item() {
    }

    public boolean isEqualTo(Item item) {
        int i7 = this.type;
        if (i7 != 1) {
            if (i7 != 15) {
                if (i7 == 12) {
                    return item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2);
                }
                if (i7 != 13) {
                    switch (i7) {
                        case 3:
                        case 4:
                            return item.intVal == this.intVal;
                        case 5:
                        case 6:
                            break;
                        case 7:
                        case 8:
                            break;
                        default:
                            return item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2) && item.strVal3.equals(this.strVal3);
                    }
                }
            }
            return item.longVal == this.longVal;
        }
        return item.strVal1.equals(this.strVal1);
    }

    public void set(int i7, String str, String str2, String str3) {
        this.type = i7;
        this.strVal1 = str;
        this.strVal2 = str2;
        this.strVal3 = str3;
        if (i7 != 1 && i7 != 7 && i7 != 8) {
            if (i7 == 12) {
                this.hashCode = ((str2.hashCode() * str.hashCode()) + i7) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
            } else if (i7 != 13) {
                this.hashCode = ((str3.hashCode() * str2.hashCode() * str.hashCode()) + i7) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
            }
        }
        this.hashCode = (str.hashCode() + i7) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    public Item(int i7, Item item) {
        this.index = i7;
        this.type = item.type;
        this.intVal = item.intVal;
        this.longVal = item.longVal;
        this.strVal1 = item.strVal1;
        this.strVal2 = item.strVal2;
        this.strVal3 = item.strVal3;
        this.hashCode = item.hashCode;
    }

    public void set(int i7) {
        this.type = 3;
        this.intVal = i7;
        this.hashCode = Integer.MAX_VALUE & (3 + i7);
    }
}
