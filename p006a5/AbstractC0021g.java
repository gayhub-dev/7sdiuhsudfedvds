package p006a5;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.ResourceBundle;

/* compiled from: GenericServlet.java */
/* renamed from: a5.g */
/* loaded from: classes.dex */
public abstract class AbstractC0021g implements InterfaceC0024j, InterfaceC0025k, Serializable {
    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
    private transient InterfaceC0025k config;

    @Override // p006a5.InterfaceC0024j
    public void destroy() {
    }

    @Override // p006a5.InterfaceC0025k
    public String getInitParameter(String str) {
        InterfaceC0025k servletConfig = getServletConfig();
        if (servletConfig != null) {
            return servletConfig.getInitParameter(str);
        }
        throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
    }

    @Override // p006a5.InterfaceC0025k
    public Enumeration<String> getInitParameterNames() {
        InterfaceC0025k servletConfig = getServletConfig();
        if (servletConfig != null) {
            return servletConfig.getInitParameterNames();
        }
        throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
    }

    public InterfaceC0025k getServletConfig() {
        return this.config;
    }

    @Override // p006a5.InterfaceC0025k
    public InterfaceC0026l getServletContext() {
        InterfaceC0025k servletConfig = getServletConfig();
        if (servletConfig != null) {
            return servletConfig.getServletContext();
        }
        throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
    }

    public String getServletInfo() {
        return "";
    }

    @Override // p006a5.InterfaceC0025k
    public String getServletName() {
        InterfaceC0025k servletConfig = getServletConfig();
        if (servletConfig != null) {
            return servletConfig.getServletName();
        }
        throw new IllegalStateException(lStrings.getString("err.servlet_config_not_initialized"));
    }

    public void init() {
    }

    @Override // p006a5.InterfaceC0024j
    public void init(InterfaceC0025k interfaceC0025k) {
        this.config = interfaceC0025k;
        init();
    }

    public void log(String str) {
        getServletContext().log(getServletName() + ": " + str);
    }

    @Override // p006a5.InterfaceC0024j
    public abstract void service(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w);

    public void log(String str, Throwable th) {
        getServletContext().mo18g(getServletName() + ": " + str, th);
    }
}
