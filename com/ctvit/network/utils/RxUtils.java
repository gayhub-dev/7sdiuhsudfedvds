package com.ctvit.network.utils;

import com.ctvit.network.func.HandleFuc;
import com.ctvit.network.func.HttpResponseFunc;
import com.ctvit.network.model.ApiResult;
import io.reactivex.android.schedulers.AndroidSchedulers;
import p009b.C0413b;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p174v4.C2012a;
import p186x2.C2073a;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;
import p201z3.InterfaceC2153b;

/* loaded from: classes.dex */
public class RxUtils {
    public static <T> InterfaceC2126r<ApiResult<T>, T> _io_main() {
        return new InterfaceC2126r<ApiResult<T>, T>() { // from class: com.ctvit.network.utils.RxUtils.2
            @Override // p194y3.InterfaceC2126r
            public InterfaceC2125q<T> apply(AbstractC2120l<ApiResult<T>> abstractC2120l) {
                AbstractC2128t abstractC2128t = C2012a.f5854b;
                return abstractC2120l.subscribeOn(abstractC2128t).unsubscribeOn(abstractC2128t).observeOn(AndroidSchedulers.mainThread()).map(new HandleFuc()).doOnSubscribe(new InterfaceC0446f<InterfaceC2153b>() { // from class: com.ctvit.network.utils.RxUtils.2.2
                    @Override // p014b4.InterfaceC0446f
                    public void accept(InterfaceC2153b interfaceC2153b) {
                        StringBuilder sbM112a = C0413b.m112a("doOnSubscribe=");
                        sbM112a.append(interfaceC2153b.isDisposed());
                        C2073a.m2459d(sbM112a.toString());
                    }
                }).doFinally(new InterfaceC0441a() { // from class: com.ctvit.network.utils.RxUtils.2.1
                    @Override // p014b4.InterfaceC0441a
                    public void run() {
                        C2073a.m2459d("doFinally");
                    }
                }).onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }

    public static <T> InterfaceC2126r<ApiResult<T>, T> _main() {
        return new InterfaceC2126r<ApiResult<T>, T>() { // from class: com.ctvit.network.utils.RxUtils.3
            @Override // p194y3.InterfaceC2126r
            public InterfaceC2125q<T> apply(AbstractC2120l<ApiResult<T>> abstractC2120l) {
                return abstractC2120l.map(new HandleFuc()).doOnSubscribe(new InterfaceC0446f<InterfaceC2153b>() { // from class: com.ctvit.network.utils.RxUtils.3.2
                    @Override // p014b4.InterfaceC0446f
                    public void accept(InterfaceC2153b interfaceC2153b) {
                        StringBuilder sbM112a = C0413b.m112a("doOnSubscribe=");
                        sbM112a.append(interfaceC2153b.isDisposed());
                        C2073a.m2459d(sbM112a.toString());
                    }
                }).doFinally(new InterfaceC0441a() { // from class: com.ctvit.network.utils.RxUtils.3.1
                    @Override // p014b4.InterfaceC0441a
                    public void run() {
                        C2073a.m2459d("doFinally");
                    }
                }).onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }

    public static <T> InterfaceC2126r<T, T> io_main() {
        return new InterfaceC2126r<T, T>() { // from class: com.ctvit.network.utils.RxUtils.1
            @Override // p194y3.InterfaceC2126r
            public InterfaceC2125q<T> apply(AbstractC2120l<T> abstractC2120l) {
                AbstractC2128t abstractC2128t = C2012a.f5854b;
                return abstractC2120l.subscribeOn(abstractC2128t).unsubscribeOn(abstractC2128t).doOnSubscribe(new InterfaceC0446f<InterfaceC2153b>() { // from class: com.ctvit.network.utils.RxUtils.1.2
                    @Override // p014b4.InterfaceC0446f
                    public void accept(InterfaceC2153b interfaceC2153b) {
                        StringBuilder sbM112a = C0413b.m112a("doOnSubscribe=");
                        sbM112a.append(interfaceC2153b.isDisposed());
                        C2073a.m2459d(sbM112a.toString());
                    }
                }).doFinally(new InterfaceC0441a() { // from class: com.ctvit.network.utils.RxUtils.1.1
                    @Override // p014b4.InterfaceC0441a
                    public void run() {
                        C2073a.m2459d("doFinally");
                    }
                }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
