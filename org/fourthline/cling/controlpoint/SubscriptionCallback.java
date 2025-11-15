package org.fourthline.cling.controlpoint;

import android.support.constraint.motion.C0080b;
import android.support.v7.widget.ActivityChooserView;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.GENASubscription;
import org.fourthline.cling.model.gena.LocalGENASubscription;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.protocol.ProtocolCreationException;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class SubscriptionCallback implements Runnable {
    public static Logger log = Logger.getLogger(SubscriptionCallback.class.getName());
    private ControlPoint controlPoint;
    public final Integer requestedDurationSeconds;
    public final Service service;
    private GENASubscription subscription;

    /* renamed from: org.fourthline.cling.controlpoint.SubscriptionCallback$1 */
    public class C16601 extends LocalGENASubscription {
        public C16601(LocalService localService, Integer num, List list) {
            super(localService, num, list);
        }

        @Override // org.fourthline.cling.model.gena.LocalGENASubscription
        public void ended(CancelReason cancelReason) {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.setSubscription(null);
                SubscriptionCallback.this.ended(this, cancelReason, null);
            }
        }

        @Override // org.fourthline.cling.model.gena.GENASubscription
        public void established() {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.setSubscription(this);
                SubscriptionCallback.this.established(this);
            }
        }

        @Override // org.fourthline.cling.model.gena.GENASubscription
        public void eventReceived() {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.log.fine("Local service state updated, notifying callback, sequence is: " + getCurrentSequence());
                SubscriptionCallback.this.eventReceived(this);
                incrementSequence();
            }
        }

        public void failed(Exception exc) {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.setSubscription(null);
                SubscriptionCallback.this.failed(null, null, exc);
            }
        }
    }

    /* renamed from: org.fourthline.cling.controlpoint.SubscriptionCallback$2 */
    public class C16612 extends RemoteGENASubscription {
        public C16612(RemoteService remoteService, int i7) {
            super(remoteService, i7);
        }

        @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
        public void ended(CancelReason cancelReason, UpnpResponse upnpResponse) {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.setSubscription(null);
                SubscriptionCallback.this.ended(this, cancelReason, upnpResponse);
            }
        }

        @Override // org.fourthline.cling.model.gena.GENASubscription
        public void established() {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.setSubscription(this);
                SubscriptionCallback.this.established(this);
            }
        }

        @Override // org.fourthline.cling.model.gena.GENASubscription
        public void eventReceived() {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.eventReceived(this);
            }
        }

        @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
        public void eventsMissed(int i7) {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.eventsMissed(this, i7);
            }
        }

        @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
        public void failed(UpnpResponse upnpResponse) {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.setSubscription(null);
                SubscriptionCallback.this.failed(this, upnpResponse, null);
            }
        }

        @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
        public void invalidMessage(UnsupportedDataException unsupportedDataException) {
            synchronized (SubscriptionCallback.this) {
                SubscriptionCallback.this.invalidMessage(this, unsupportedDataException);
            }
        }
    }

    public SubscriptionCallback(Service service) {
        this.service = service;
        this.requestedDurationSeconds = 1800;
    }

    public static String createDefaultFailureMessage(UpnpResponse upnpResponse, Exception exc) {
        if (upnpResponse != null) {
            StringBuilder sbM94a = C0080b.m94a("Subscription failed: ", " HTTP response was: ");
            sbM94a.append(upnpResponse.getResponseDetails());
            return sbM94a.toString();
        }
        if (exc == null) {
            return "Subscription failed:  No response received.";
        }
        return "Subscription failed:  Exception occured: " + exc;
    }

    private void endLocalSubscription(LocalGENASubscription localGENASubscription) {
        log.fine("Removing local subscription and ending it in callback: " + localGENASubscription);
        getControlPoint().getRegistry().removeLocalSubscription(localGENASubscription);
        localGENASubscription.end(null);
    }

    private void endRemoteSubscription(RemoteGENASubscription remoteGENASubscription) {
        log.fine("Ending remote subscription: " + remoteGENASubscription);
        getControlPoint().getConfiguration().getSyncProtocolExecutorService().execute(getControlPoint().getProtocolFactory().createSendingUnsubscribe(remoteGENASubscription));
    }

    private void establishLocalSubscription(LocalService localService) {
        LocalGENASubscription c16601;
        if (getControlPoint().getRegistry().getLocalDevice(localService.getDevice().getIdentity().getUdn(), false) == null) {
            log.fine("Local device service is currently not registered, failing subscription immediately");
            failed(null, null, new IllegalStateException("Local device is not registered"));
            return;
        }
        try {
            c16601 = new LocalGENASubscription(localService, Integer.valueOf(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED), Collections.EMPTY_LIST) { // from class: org.fourthline.cling.controlpoint.SubscriptionCallback.1
                public C16601(LocalService localService2, Integer num, List list) {
                    super(localService2, num, list);
                }

                @Override // org.fourthline.cling.model.gena.LocalGENASubscription
                public void ended(CancelReason cancelReason) {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.setSubscription(null);
                        SubscriptionCallback.this.ended(this, cancelReason, null);
                    }
                }

                @Override // org.fourthline.cling.model.gena.GENASubscription
                public void established() {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.setSubscription(this);
                        SubscriptionCallback.this.established(this);
                    }
                }

                @Override // org.fourthline.cling.model.gena.GENASubscription
                public void eventReceived() {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.log.fine("Local service state updated, notifying callback, sequence is: " + getCurrentSequence());
                        SubscriptionCallback.this.eventReceived(this);
                        incrementSequence();
                    }
                }

                public void failed(Exception exc) {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.setSubscription(null);
                        SubscriptionCallback.this.failed(null, null, exc);
                    }
                }
            };
            try {
                log.fine("Local device service is currently registered, also registering subscription");
                getControlPoint().getRegistry().addLocalSubscription(c16601);
                log.fine("Notifying subscription callback of local subscription availablity");
                c16601.establish();
                log.fine("Simulating first initial event for local subscription callback, sequence: " + c16601.getCurrentSequence());
                eventReceived(c16601);
                c16601.incrementSequence();
                log.fine("Starting to monitor state changes of local service");
                c16601.registerOnService();
            } catch (Exception e7) {
                e = e7;
                Logger logger = log;
                StringBuilder sbM112a = C0413b.m112a("Local callback creation failed: ");
                sbM112a.append(e.toString());
                logger.fine(sbM112a.toString());
                log.log(Level.FINE, "Exception root cause: ", C2074b.m2475O(e));
                if (c16601 != null) {
                    getControlPoint().getRegistry().removeLocalSubscription(c16601);
                }
                failed(c16601, null, e);
            }
        } catch (Exception e8) {
            e = e8;
            c16601 = null;
        }
    }

    private void establishRemoteSubscription(RemoteService remoteService) {
        try {
            getControlPoint().getProtocolFactory().createSendingSubscribe(new RemoteGENASubscription(remoteService, this.requestedDurationSeconds.intValue()) { // from class: org.fourthline.cling.controlpoint.SubscriptionCallback.2
                public C16612(RemoteService remoteService2, int i7) {
                    super(remoteService2, i7);
                }

                @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
                public void ended(CancelReason cancelReason, UpnpResponse upnpResponse) {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.setSubscription(null);
                        SubscriptionCallback.this.ended(this, cancelReason, upnpResponse);
                    }
                }

                @Override // org.fourthline.cling.model.gena.GENASubscription
                public void established() {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.setSubscription(this);
                        SubscriptionCallback.this.established(this);
                    }
                }

                @Override // org.fourthline.cling.model.gena.GENASubscription
                public void eventReceived() {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.eventReceived(this);
                    }
                }

                @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
                public void eventsMissed(int i7) {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.eventsMissed(this, i7);
                    }
                }

                @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
                public void failed(UpnpResponse upnpResponse) {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.setSubscription(null);
                        SubscriptionCallback.this.failed(this, upnpResponse, null);
                    }
                }

                @Override // org.fourthline.cling.model.gena.RemoteGENASubscription
                public void invalidMessage(UnsupportedDataException unsupportedDataException) {
                    synchronized (SubscriptionCallback.this) {
                        SubscriptionCallback.this.invalidMessage(this, unsupportedDataException);
                    }
                }
            }).run();
        } catch (ProtocolCreationException e7) {
            failed(this.subscription, null, e7);
        }
    }

    public synchronized void end() {
        GENASubscription gENASubscription = this.subscription;
        if (gENASubscription == null) {
            return;
        }
        if (gENASubscription instanceof LocalGENASubscription) {
            endLocalSubscription((LocalGENASubscription) gENASubscription);
        } else if (gENASubscription instanceof RemoteGENASubscription) {
            endRemoteSubscription((RemoteGENASubscription) gENASubscription);
        }
    }

    public abstract void ended(GENASubscription gENASubscription, CancelReason cancelReason, UpnpResponse upnpResponse);

    public abstract void established(GENASubscription gENASubscription);

    public abstract void eventReceived(GENASubscription gENASubscription);

    public abstract void eventsMissed(GENASubscription gENASubscription, int i7);

    public void failed(GENASubscription gENASubscription, UpnpResponse upnpResponse, Exception exc) {
        failed(gENASubscription, upnpResponse, exc, createDefaultFailureMessage(upnpResponse, exc));
    }

    public abstract void failed(GENASubscription gENASubscription, UpnpResponse upnpResponse, Exception exc, String str);

    public synchronized ControlPoint getControlPoint() {
        return this.controlPoint;
    }

    public Service getService() {
        return this.service;
    }

    public synchronized GENASubscription getSubscription() {
        return this.subscription;
    }

    public void invalidMessage(RemoteGENASubscription remoteGENASubscription, UnsupportedDataException unsupportedDataException) {
        log.info("Invalid event message received, causing: " + unsupportedDataException);
        if (log.isLoggable(Level.FINE)) {
            log.fine("------------------------------------------------------------------------------");
            log.fine(unsupportedDataException.getData() != null ? unsupportedDataException.getData().toString() : "null");
            log.fine("------------------------------------------------------------------------------");
        }
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        if (getControlPoint() == null) {
            throw new IllegalStateException("Callback must be executed through ControlPoint");
        }
        if (getService() instanceof LocalService) {
            establishLocalSubscription((LocalService) this.service);
        } else if (getService() instanceof RemoteService) {
            establishRemoteSubscription((RemoteService) this.service);
        }
    }

    public synchronized void setControlPoint(ControlPoint controlPoint) {
        this.controlPoint = controlPoint;
    }

    public synchronized void setSubscription(GENASubscription gENASubscription) {
        this.subscription = gENASubscription;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(SubscriptionCallback) ");
        sbM112a.append(getService());
        return sbM112a.toString();
    }

    public SubscriptionCallback(Service service, int i7) {
        this.service = service;
        this.requestedDurationSeconds = Integer.valueOf(i7);
    }
}
