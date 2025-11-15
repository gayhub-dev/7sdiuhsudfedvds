package com.cctv.p025tv.mvp.p026ui.view.versionupdate;

import android.content.Context;
import android.net.TrafficStats;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.ctvit.appupdate.entity.AppUpdateEntity;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.cache.model.CacheMode;
import java.util.Timer;
import p043f.C0988e;
import p070i2.C1145c;
import p078j2.C1186a;
import p094l2.C1420a;
import p110n2.InterfaceC1517a;
import p150s1.C1864a;
import p150s1.C1865b;
import p150s1.C1868e;
import p179w2.C2026b;
import p186x2.C2073a;
import p193y2.C2107b;
import p193y2.C2108c;

/* loaded from: classes.dex */
public class VersionDownloadingView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: e */
    public Button f911e;

    /* renamed from: f */
    public TextView f912f;

    /* renamed from: g */
    public TextView f913g;

    /* renamed from: h */
    public TextView f914h;

    /* renamed from: i */
    public ProgressBar f915i;

    /* renamed from: j */
    public C2108c f916j;

    /* renamed from: k */
    public AppUpdateEntity.AndroidBean f917k;

    /* renamed from: com.cctv.tv.mvp.ui.view.versionupdate.VersionDownloadingView$a */
    public class C0622a implements InterfaceC1517a {
        public C0622a() {
        }

        /* renamed from: a */
        public void m523a(int i7) {
            VersionDownloadingView.this.f916j.m2551a();
            if (i7 == 1) {
                if (MyApplication.f563g != null && (VersionDownloadingView.this.getContext() instanceof MainActivity)) {
                    C2073a.m2459d("由投屏操作从后台唤起的，并且还有更新");
                    ((MainActivity) VersionDownloadingView.this.getContext()).m479v(MyApplication.f563g, false);
                    MyApplication.f563g = null;
                } else if (C1186a.m1385e()) {
                    C0988e.m971O(((AppCompatActivity) VersionDownloadingView.this.getContext()).getSupportFragmentManager(), "MAIN_FRAGMENT");
                } else {
                    C0988e.m971O(((AppCompatActivity) VersionDownloadingView.this.getContext()).getSupportFragmentManager(), "WELCOME");
                }
                C1868e.f5447c = true;
                C1868e.f5445a = false;
                VersionDownloadingView.this.f911e.setText(C2026b.m2379b(R.string.install));
                VersionDownloadingView.this.f911e.setTag("install");
            } else {
                VersionDownloadingView.this.f911e.setText(C2026b.m2379b(R.string.redownload));
                VersionDownloadingView.this.f911e.setTag("redownload");
                VersionDownloadingView.this.f915i.setProgress(0);
            }
            VersionDownloadingView.this.f911e.setVisibility(0);
        }
    }

    public VersionDownloadingView(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.download_stop_view) {
            return;
        }
        Object tag = view.getTag();
        if (tag == null) {
            C0988e.m971O(((AppCompatActivity) getContext()).getSupportFragmentManager(), "MAIN_FRAGMENT");
            C1420a.m1604a();
            return;
        }
        String string = tag.toString();
        if (!"install".equals(string)) {
            if ("redownload".equals(string)) {
                this.f911e.setText(C2026b.m2379b(R.string.download_stop));
                setVersionEntity(this.f917k);
                C0580b.m417c("DOWNLOAD_CANCEL", "VersionUpdateFragment");
                return;
            }
            return;
        }
        C1868e.m2140c(C1420a.m1605b(), C1420a.f4156d + "CCTV_APP_UPDATE.apk");
        C0580b.m417c("INSTALL", "VersionUpdateFragment");
    }

    public void setVersionEntity(AppUpdateEntity.AndroidBean androidBean) {
        this.f917k = androidBean;
        if (androidBean != null) {
            this.f914h.setText(androidBean.getBrief());
            C2108c c2108c = new C2108c();
            this.f916j = c2108c;
            c2108c.f6241b = 2;
            c2108c.f6242c = new C1145c(this);
            c2108c.m2551a();
            c2108c.f6240a = TrafficStats.getTotalRxBytes();
            c2108c.f6243d = new Timer(C2108c.class.getSimpleName());
            c2108c.f6243d.schedule(new C2107b(c2108c), 1000L, c2108c.f6241b * 1000);
            String appUrl = androidBean.getAppUrl();
            C0622a c0622a = new C0622a();
            C1865b c1865b = new C1865b(appUrl);
            c1865b.f5441b = c0622a;
            C1420a.m1604a();
            C1420a.m1606c().f4160c.add(CtvitHttp.downLoad(c1865b.f5440a).context(C1420a.m1605b()).cacheMode(CacheMode.NO_CACHE).saveName("CCTV_APP_UPDATE.apk").savePath(C1420a.f4156d).execute(new C1864a(c1865b)));
        }
    }

    public VersionDownloadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VersionDownloadingView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_downloading, this);
        this.f911e = (Button) findViewById(R.id.download_stop_view);
        this.f912f = (TextView) findViewById(R.id.progress_text_view);
        this.f913g = (TextView) findViewById(R.id.download_speed);
        this.f915i = (ProgressBar) findViewById(R.id.progress_view);
        this.f914h = (TextView) findViewById(R.id.version_update_text);
        if (C1868e.f5445a) {
            this.f911e.setVisibility(8);
        }
        this.f911e.requestFocus();
        this.f911e.setOnClickListener(this);
    }
}
