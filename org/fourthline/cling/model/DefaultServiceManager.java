package org.fourthline.cling.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.state.StateVariableAccessor;
import org.fourthline.cling.model.state.StateVariableValue;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class DefaultServiceManager<T> implements ServiceManager<T> {
    private static Logger log = Logger.getLogger(DefaultServiceManager.class.getName());
    public final ReentrantLock lock;
    public PropertyChangeSupport propertyChangeSupport;
    public final LocalService<T> service;
    public final Class<T> serviceClass;
    public T serviceImpl;

    public class DefaultPropertyChangeListener implements PropertyChangeListener {
        public DefaultPropertyChangeListener() {
        }

        @Override // java.beans.PropertyChangeListener
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            Logger logger = DefaultServiceManager.log;
            StringBuilder sbM112a = C0413b.m112a("Property change event on local service: ");
            sbM112a.append(propertyChangeEvent.getPropertyName());
            logger.finer(sbM112a.toString());
            if (propertyChangeEvent.getPropertyName().equals(ServiceManager.EVENTED_STATE_VARIABLES)) {
                return;
            }
            String[] strArrFromCommaSeparatedList = ModelUtil.fromCommaSeparatedList(propertyChangeEvent.getPropertyName());
            Logger logger2 = DefaultServiceManager.log;
            StringBuilder sbM112a2 = C0413b.m112a("Changed variable names: ");
            sbM112a2.append(Arrays.toString(strArrFromCommaSeparatedList));
            logger2.fine(sbM112a2.toString());
            try {
                Collection<StateVariableValue> currentState = DefaultServiceManager.this.getCurrentState(strArrFromCommaSeparatedList);
                if (currentState.isEmpty()) {
                    return;
                }
                DefaultServiceManager.this.getPropertyChangeSupport().firePropertyChange(ServiceManager.EVENTED_STATE_VARIABLES, (Object) null, currentState);
            } catch (Exception e7) {
                Logger logger3 = DefaultServiceManager.log;
                Level level = Level.SEVERE;
                StringBuilder sbM112a3 = C0413b.m112a("Error reading state of service after state variable update event: ");
                sbM112a3.append(C2074b.m2475O(e7));
                logger3.log(level, sbM112a3.toString(), (Throwable) e7);
            }
        }
    }

    public DefaultServiceManager(LocalService<T> localService) {
        this(localService, null);
    }

    public PropertyChangeListener createPropertyChangeListener(T t6) {
        return new DefaultPropertyChangeListener();
    }

    public PropertyChangeSupport createPropertyChangeSupport(T t6) throws SecurityException {
        Method methodM2488k = C2074b.m2488k(t6.getClass(), "propertyChangeSupport");
        if (methodM2488k == null || !PropertyChangeSupport.class.isAssignableFrom(methodM2488k.getReturnType())) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Creating new PropertyChangeSupport for service implementation: ");
            sbM112a.append(t6.getClass().getName());
            logger.fine(sbM112a.toString());
            return new PropertyChangeSupport(t6);
        }
        Logger logger2 = log;
        StringBuilder sbM112a2 = C0413b.m112a("Service implementation instance offers PropertyChangeSupport, using that: ");
        sbM112a2.append(t6.getClass().getName());
        logger2.fine(sbM112a2.toString());
        return (PropertyChangeSupport) methodM2488k.invoke(t6, new Object[0]);
    }

    public T createServiceInstance() {
        Class<T> cls = this.serviceClass;
        if (cls == null) {
            throw new IllegalStateException("Subclass has to provide service class or override createServiceInstance()");
        }
        try {
            return cls.getConstructor(LocalService.class).newInstance(getService());
        } catch (NoSuchMethodException unused) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Creating new service implementation instance with no-arg constructor: ");
            sbM112a.append(this.serviceClass.getName());
            logger.fine(sbM112a.toString());
            return this.serviceClass.newInstance();
        }
    }

    @Override // org.fourthline.cling.model.ServiceManager
    public void execute(Command<T> command) {
        lock();
        try {
            command.execute(this);
        } finally {
            unlock();
        }
    }

    @Override // org.fourthline.cling.model.ServiceManager
    public Collection<StateVariableValue> getCurrentState() {
        lock();
        try {
            Collection<StateVariableValue> initialEventedStateVariableValues = readInitialEventedStateVariableValues();
            if (initialEventedStateVariableValues != null) {
                log.fine("Obtained initial state variable values for event, skipping individual state variable accessors");
                return initialEventedStateVariableValues;
            }
            ArrayList arrayList = new ArrayList();
            for (StateVariable<LocalService> stateVariable : getService().getStateVariables()) {
                if (stateVariable.getEventDetails().isSendEvents()) {
                    StateVariableAccessor accessor = getService().getAccessor(stateVariable);
                    if (accessor == null) {
                        throw new IllegalStateException("No accessor for evented state variable");
                    }
                    arrayList.add(accessor.read(stateVariable, getImplementation()));
                }
            }
            return arrayList;
        } finally {
            unlock();
        }
    }

    @Override // org.fourthline.cling.model.ServiceManager
    public T getImplementation() {
        lock();
        try {
            if (this.serviceImpl == null) {
                init();
            }
            return this.serviceImpl;
        } finally {
            unlock();
        }
    }

    public int getLockTimeoutMillis() {
        return 500;
    }

    @Override // org.fourthline.cling.model.ServiceManager
    public PropertyChangeSupport getPropertyChangeSupport() {
        lock();
        try {
            if (this.propertyChangeSupport == null) {
                init();
            }
            return this.propertyChangeSupport;
        } finally {
            unlock();
        }
    }

    @Override // org.fourthline.cling.model.ServiceManager
    public LocalService<T> getService() {
        return this.service;
    }

    public void init() {
        log.fine("No service implementation instance available, initializing...");
        try {
            T tCreateServiceInstance = createServiceInstance();
            this.serviceImpl = tCreateServiceInstance;
            PropertyChangeSupport propertyChangeSupportCreatePropertyChangeSupport = createPropertyChangeSupport(tCreateServiceInstance);
            this.propertyChangeSupport = propertyChangeSupportCreatePropertyChangeSupport;
            propertyChangeSupportCreatePropertyChangeSupport.addPropertyChangeListener(createPropertyChangeListener(this.serviceImpl));
        } catch (Exception e7) {
            throw new RuntimeException("Could not initialize implementation: " + e7, e7);
        }
    }

    public void lock() {
        try {
            if (this.lock.tryLock(getLockTimeoutMillis(), TimeUnit.MILLISECONDS)) {
                if (log.isLoggable(Level.FINEST)) {
                    log.finest("Acquired lock");
                }
            } else {
                throw new RuntimeException("Failed to acquire lock in milliseconds: " + getLockTimeoutMillis());
            }
        } catch (InterruptedException e7) {
            throw new RuntimeException("Failed to acquire lock:" + e7);
        }
    }

    public Collection<StateVariableValue> readInitialEventedStateVariableValues() {
        return null;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") Implementation: ");
        sbM112a.append(this.serviceImpl);
        return sbM112a.toString();
    }

    public void unlock() {
        if (log.isLoggable(Level.FINEST)) {
            log.finest("Releasing lock");
        }
        this.lock.unlock();
    }

    public DefaultServiceManager(LocalService<T> localService, Class<T> cls) {
        this.lock = new ReentrantLock(true);
        this.service = localService;
        this.serviceClass = cls;
    }

    public Collection<StateVariableValue> getCurrentState(String[] strArr) {
        lock();
        try {
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                String strTrim = str.trim();
                StateVariable<LocalService> stateVariable = getService().getStateVariable(strTrim);
                if (stateVariable != null && stateVariable.getEventDetails().isSendEvents()) {
                    StateVariableAccessor accessor = getService().getAccessor(stateVariable);
                    if (accessor == null) {
                        log.warning("Ignoring evented state variable without accessor: " + strTrim);
                    } else {
                        arrayList.add(accessor.read(stateVariable, getImplementation()));
                    }
                } else {
                    log.fine("Ignoring unknown or non-evented state variable: " + strTrim);
                }
            }
            return arrayList;
        } finally {
            unlock();
        }
    }
}
