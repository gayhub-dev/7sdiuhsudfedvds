package com.ctvit.dlna.discreteness;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes.dex */
public class DlnaWebView extends WebView {

    /* renamed from: e */
    public WebSettings f955e;

    /* renamed from: com.ctvit.dlna.discreteness.DlnaWebView$a */
    public class C0624a extends WebViewClient {
        public C0624a() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (DlnaWebView.this.f955e.getLoadsImagesAutomatically()) {
                return;
            }
            DlnaWebView.this.f955e.setLoadsImagesAutomatically(true);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    public DlnaWebView(Context context) {
        super(context);
        m526a();
    }

    /* renamed from: a */
    public final void m526a() {
        WebSettings settings = getSettings();
        this.f955e = settings;
        settings.setJavaScriptEnabled(true);
        this.f955e.setJavaScriptCanOpenWindowsAutomatically(true);
        this.f955e.setDomStorageEnabled(true);
        this.f955e.setAllowFileAccess(true);
        this.f955e.setSavePassword(false);
        this.f955e.setLoadsImagesAutomatically(true);
        this.f955e.setMixedContentMode(2);
        this.f955e.setTextZoom(100);
        this.f955e.setUseWideViewPort(true);
        this.f955e.setLoadWithOverviewMode(true);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        setWebViewClient(new C0624a());
    }

    public DlnaWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m526a();
    }

    public DlnaWebView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m526a();
    }
}
