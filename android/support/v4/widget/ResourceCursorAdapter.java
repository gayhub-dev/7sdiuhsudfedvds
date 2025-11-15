package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;

    @Deprecated
    public ResourceCursorAdapter(Context context, int i7, Cursor cursor) {
        super(context, cursor);
        this.mDropDownLayout = i7;
        this.mLayout = i7;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.support.v4.widget.CursorAdapter
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mDropDownLayout, viewGroup, false);
    }

    @Override // android.support.v4.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mLayout, viewGroup, false);
    }

    public void setDropDownViewResource(int i7) {
        this.mDropDownLayout = i7;
    }

    public void setViewResource(int i7) {
        this.mLayout = i7;
    }

    @Deprecated
    public ResourceCursorAdapter(Context context, int i7, Cursor cursor, boolean z6) {
        super(context, cursor, z6);
        this.mDropDownLayout = i7;
        this.mLayout = i7;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context context, int i7, Cursor cursor, int i8) {
        super(context, cursor, i8);
        this.mDropDownLayout = i7;
        this.mLayout = i7;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
