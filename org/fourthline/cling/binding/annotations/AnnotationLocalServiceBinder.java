package org.fourthline.cling.binding.annotations;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.fourthline.cling.binding.LocalServiceBinder;
import org.fourthline.cling.binding.LocalServiceBindingException;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.action.ActionExecutor;
import org.fourthline.cling.model.action.QueryStateVariableExecutor;
import org.fourthline.cling.model.meta.Action;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.QueryStateVariableAction;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.state.StateVariableAccessor;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDAServiceId;
import org.fourthline.cling.model.types.UDAServiceType;
import org.fourthline.cling.model.types.csv.CSV;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class AnnotationLocalServiceBinder implements LocalServiceBinder {
    private static Logger log = Logger.getLogger(AnnotationLocalServiceBinder.class.getName());

    public static String toJavaActionName(String str) {
        if (str.length() < 1) {
            throw new IllegalArgumentException("Variable name must be at least 1 character long");
        }
        return str.substring(0, 1).toLowerCase(Locale.ROOT) + str.substring(1);
    }

    public static String toJavaStateVariableName(String str) {
        if (str.length() < 1) {
            throw new IllegalArgumentException("Variable name must be at least 1 character long");
        }
        return str.substring(0, 1).toLowerCase(Locale.ROOT) + str.substring(1);
    }

    public static String toUpnpActionName(String str) {
        if (str.length() < 1) {
            throw new IllegalArgumentException("Action name must be at least 1 character long");
        }
        return str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    public static String toUpnpStateVariableName(String str) {
        if (str.length() < 1) {
            throw new IllegalArgumentException("Variable name must be at least 1 character long");
        }
        return str.substring(0, 1).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    public boolean isActionExcluded(Action action) {
        return false;
    }

    @Override // org.fourthline.cling.binding.LocalServiceBinder
    public LocalService read(Class<?> cls) {
        log.fine("Reading and binding annotations of service implementation class: " + cls);
        if (!cls.isAnnotationPresent(UpnpService.class)) {
            throw new LocalServiceBindingException("Given class is not an @UpnpService");
        }
        UpnpService upnpService = (UpnpService) cls.getAnnotation(UpnpService.class);
        UpnpServiceId upnpServiceIdServiceId = upnpService.serviceId();
        UpnpServiceType upnpServiceTypeServiceType = upnpService.serviceType();
        return read(cls, upnpServiceIdServiceId.namespace().equals(UDAServiceId.DEFAULT_NAMESPACE) ? new UDAServiceId(upnpServiceIdServiceId.value()) : new ServiceId(upnpServiceIdServiceId.namespace(), upnpServiceIdServiceId.value()), upnpServiceTypeServiceType.namespace().equals("schemas-upnp-org") ? new UDAServiceType(upnpServiceTypeServiceType.value(), upnpServiceTypeServiceType.version()) : new ServiceType(upnpServiceTypeServiceType.namespace(), upnpServiceTypeServiceType.value(), upnpServiceTypeServiceType.version()), upnpService.supportsQueryStateVariables(), readStringConvertibleTypes(upnpService.stringConvertibleTypes()));
    }

    public Map<Action, ActionExecutor> readActions(Class<?> cls, Map<StateVariable, StateVariableAccessor> map, Set<Class> set) {
        HashMap map2 = new HashMap();
        Iterator it = ((ArrayList) C2074b.m2494q(cls, UpnpAction.class)).iterator();
        while (it.hasNext()) {
            Action actionAppendAction = new AnnotationActionBinder((Method) it.next(), map, set).appendAction(map2);
            if (isActionExcluded(actionAppendAction)) {
                map2.remove(actionAppendAction);
            }
        }
        return map2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
    
        r7 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<org.fourthline.cling.model.meta.StateVariable, org.fourthline.cling.model.state.StateVariableAccessor> readStateVariables(java.lang.Class<?> r12, java.util.Set<java.lang.Class> r13) throws java.lang.NoSuchFieldException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 396
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder.readStateVariables(java.lang.Class, java.util.Set):java.util.Map");
    }

    public Set<Class> readStringConvertibleTypes(Class[] clsArr) throws NoSuchMethodException, SecurityException {
        for (Class cls : clsArr) {
            if (!Modifier.isPublic(cls.getModifiers())) {
                throw new LocalServiceBindingException("Declared string-convertible type must be public: " + cls);
            }
            try {
                cls.getConstructor(String.class);
            } catch (NoSuchMethodException unused) {
                throw new LocalServiceBindingException("Declared string-convertible type needs a public single-argument String constructor: " + cls);
            }
        }
        HashSet hashSet = new HashSet(Arrays.asList(clsArr));
        hashSet.add(URI.class);
        hashSet.add(URL.class);
        hashSet.add(CSV.class);
        return hashSet;
    }

    @Override // org.fourthline.cling.binding.LocalServiceBinder
    public LocalService read(Class<?> cls, ServiceId serviceId, ServiceType serviceType, boolean z6, Class[] clsArr) {
        return read(cls, serviceId, serviceType, z6, new HashSet(Arrays.asList(clsArr)));
    }

    public LocalService read(Class<?> cls, ServiceId serviceId, ServiceType serviceType, boolean z6, Set<Class> set) throws NoSuchFieldException, SecurityException {
        Map<StateVariable, StateVariableAccessor> stateVariables = readStateVariables(cls, set);
        Map<Action, ActionExecutor> actions = readActions(cls, stateVariables, set);
        if (z6) {
            actions.put(new QueryStateVariableAction(), new QueryStateVariableExecutor());
        }
        try {
            return new LocalService(serviceType, serviceId, actions, stateVariables, set, z6);
        } catch (ValidationException e7) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Could not validate device model: ");
            sbM112a.append(e7.toString());
            logger.severe(sbM112a.toString());
            Iterator<ValidationError> it = e7.getErrors().iterator();
            while (it.hasNext()) {
                log.severe(it.next().toString());
            }
            throw new LocalServiceBindingException("Validation of model failed, check the log");
        }
    }
}
