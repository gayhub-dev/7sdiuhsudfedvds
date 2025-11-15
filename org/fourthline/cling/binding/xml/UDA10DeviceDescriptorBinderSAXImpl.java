package org.fourthline.cling.binding.xml;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.binding.staging.MutableDevice;
import org.fourthline.cling.binding.staging.MutableIcon;
import org.fourthline.cling.binding.staging.MutableService;
import org.fourthline.cling.binding.staging.MutableUDAVersion;
import org.fourthline.cling.binding.xml.Descriptor;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.DLNACaps;
import org.fourthline.cling.model.types.DLNADoc;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDN;
import org.seamless.xml.C1728g;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import p009b.C0413b;
import p098l6.C1448b;

/* loaded from: classes.dex */
public class UDA10DeviceDescriptorBinderSAXImpl extends UDA10DeviceDescriptorBinderImpl {
    private static Logger log = Logger.getLogger(DeviceDescriptorBinder.class.getName());

    /* renamed from: org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl$1 */
    public static /* synthetic */ class C16581 {

        /* renamed from: $SwitchMap$org$fourthline$cling$binding$xml$Descriptor$Device$ELEMENT */
        public static final /* synthetic */ int[] f4852xcea4acb6;

        static {
            int[] iArr = new int[Descriptor.Device.ELEMENT.values().length];
            f4852xcea4acb6 = iArr;
            try {
                iArr[Descriptor.Device.ELEMENT.URLBase.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.major.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.minor.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.deviceType.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.friendlyName.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.manufacturer.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.manufacturerURL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.modelDescription.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.modelName.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.modelNumber.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.modelURL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.presentationURL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.UPC.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.serialNumber.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.UDN.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.X_DLNADOC.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.X_DLNACAP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.width.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.height.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.depth.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.url.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.mimetype.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.serviceType.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.serviceId.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.SCPDURL.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.controlURL.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f4852xcea4acb6[Descriptor.Device.ELEMENT.eventSubURL.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    public static class DeviceDescriptorHandler<I> extends C1728g.b<I> {
        public DeviceDescriptorHandler(I i7) {
            super(i7);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            super.endElement(str, str2, str3);
            Descriptor.Device.ELEMENT elementValueOrNullOf = Descriptor.Device.ELEMENT.valueOrNullOf(str2);
            if (elementValueOrNullOf == null) {
                return;
            }
            endElement(elementValueOrNullOf);
        }

        public void endElement(Descriptor.Device.ELEMENT element) {
        }

        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            Descriptor.Device.ELEMENT elementValueOrNullOf = Descriptor.Device.ELEMENT.valueOrNullOf(str2);
            return elementValueOrNullOf != null && isLastElement(elementValueOrNullOf);
        }

        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return false;
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            super.startElement(str, str2, str3, attributes);
            Descriptor.Device.ELEMENT elementValueOrNullOf = Descriptor.Device.ELEMENT.valueOrNullOf(str2);
            if (elementValueOrNullOf == null) {
                return;
            }
            startElement(elementValueOrNullOf, attributes);
        }

        public void startElement(Descriptor.Device.ELEMENT element, Attributes attributes) {
        }

        public DeviceDescriptorHandler(I i7, C1728g c1728g) {
            super(i7, c1728g);
        }

        public DeviceDescriptorHandler(I i7, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(i7, deviceDescriptorHandler);
        }

        public DeviceDescriptorHandler(I i7, C1728g c1728g, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(i7, c1728g, deviceDescriptorHandler);
        }
    }

    public static class DeviceHandler extends DeviceDescriptorHandler<MutableDevice> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4853EL = Descriptor.Device.ELEMENT.device;

        public DeviceHandler(MutableDevice mutableDevice, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(mutableDevice, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void endElement(Descriptor.Device.ELEMENT element) {
            switch (C16581.f4852xcea4acb6[element.ordinal()]) {
                case 4:
                    getInstance().deviceType = getCharacters();
                    break;
                case 5:
                    getInstance().friendlyName = getCharacters();
                    break;
                case 6:
                    getInstance().manufacturer = getCharacters();
                    break;
                case 7:
                    getInstance().manufacturerURI = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                    break;
                case 8:
                    getInstance().modelDescription = getCharacters();
                    break;
                case 9:
                    getInstance().modelName = getCharacters();
                    break;
                case 10:
                    getInstance().modelNumber = getCharacters();
                    break;
                case 11:
                    getInstance().modelURI = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                    break;
                case 12:
                    getInstance().presentationURI = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                    break;
                case 13:
                    getInstance().upc = getCharacters();
                    break;
                case 14:
                    getInstance().serialNumber = getCharacters();
                    break;
                case 15:
                    getInstance().udn = UDN.valueOf(getCharacters());
                    break;
                case 16:
                    String characters = getCharacters();
                    try {
                        getInstance().dlnaDocs.add(DLNADoc.valueOf(characters));
                        break;
                    } catch (InvalidValueException unused) {
                        UDA10DeviceDescriptorBinderSAXImpl.log.info("Invalid X_DLNADOC value, ignoring value: " + characters);
                        return;
                    }
                case 17:
                    getInstance().dlnaCaps = DLNACaps.valueOf(getCharacters());
                    break;
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return element.equals(f4853EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void startElement(Descriptor.Device.ELEMENT element, Attributes attributes) {
            if (element.equals(IconListHandler.f4856EL)) {
                ArrayList arrayList = new ArrayList();
                getInstance().icons = arrayList;
                new IconListHandler(arrayList, this);
            }
            if (element.equals(ServiceListHandler.f4858EL)) {
                ArrayList arrayList2 = new ArrayList();
                getInstance().services = arrayList2;
                new ServiceListHandler(arrayList2, this);
            }
            if (element.equals(DeviceListHandler.f4854EL)) {
                ArrayList arrayList3 = new ArrayList();
                getInstance().embeddedDevices = arrayList3;
                new DeviceListHandler(arrayList3, this);
            }
        }
    }

    public static class DeviceListHandler extends DeviceDescriptorHandler<List<MutableDevice>> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4854EL = Descriptor.Device.ELEMENT.deviceList;

        public DeviceListHandler(List<MutableDevice> list, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(list, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return element.equals(f4854EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void startElement(Descriptor.Device.ELEMENT element, Attributes attributes) {
            if (element.equals(DeviceHandler.f4853EL)) {
                MutableDevice mutableDevice = new MutableDevice();
                getInstance().add(mutableDevice);
                new DeviceHandler(mutableDevice, this);
            }
        }
    }

    public static class IconHandler extends DeviceDescriptorHandler<MutableIcon> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4855EL = Descriptor.Device.ELEMENT.icon;

        public IconHandler(MutableIcon mutableIcon, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(mutableIcon, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void endElement(Descriptor.Device.ELEMENT element) {
            switch (C16581.f4852xcea4acb6[element.ordinal()]) {
                case 18:
                    getInstance().width = Integer.valueOf(getCharacters()).intValue();
                    break;
                case 19:
                    getInstance().height = Integer.valueOf(getCharacters()).intValue();
                    break;
                case 20:
                    try {
                        getInstance().depth = Integer.valueOf(getCharacters()).intValue();
                        break;
                    } catch (NumberFormatException e7) {
                        Logger logger = UDA10DeviceDescriptorBinderSAXImpl.log;
                        StringBuilder sbM112a = C0413b.m112a("Invalid icon depth '");
                        sbM112a.append(getCharacters());
                        sbM112a.append("', using 16 as default: ");
                        sbM112a.append(e7);
                        logger.warning(sbM112a.toString());
                        getInstance().depth = 16;
                        return;
                    }
                case 21:
                    getInstance().uri = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                    break;
                case 22:
                    try {
                        getInstance().mimeType = getCharacters();
                        C1448b.m1633a(getInstance().mimeType);
                        break;
                    } catch (IllegalArgumentException unused) {
                        Logger logger2 = UDA10DeviceDescriptorBinderSAXImpl.log;
                        StringBuilder sbM112a2 = C0413b.m112a("Ignoring invalid icon mime type: ");
                        sbM112a2.append(getInstance().mimeType);
                        logger2.warning(sbM112a2.toString());
                        getInstance().mimeType = "";
                        return;
                    }
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return element.equals(f4855EL);
        }
    }

    public static class IconListHandler extends DeviceDescriptorHandler<List<MutableIcon>> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4856EL = Descriptor.Device.ELEMENT.iconList;

        public IconListHandler(List<MutableIcon> list, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(list, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return element.equals(f4856EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void startElement(Descriptor.Device.ELEMENT element, Attributes attributes) {
            if (element.equals(IconHandler.f4855EL)) {
                MutableIcon mutableIcon = new MutableIcon();
                getInstance().add(mutableIcon);
                new IconHandler(mutableIcon, this);
            }
        }
    }

    public static class RootHandler extends DeviceDescriptorHandler<MutableDevice> {
        public RootHandler(MutableDevice mutableDevice, C1728g c1728g) {
            super(mutableDevice, c1728g);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void endElement(Descriptor.Device.ELEMENT element) throws SAXException {
            if (C16581.f4852xcea4acb6[element.ordinal()] != 1) {
                return;
            }
            try {
                String characters = getCharacters();
                if (characters == null || characters.length() <= 0) {
                    return;
                }
                getInstance().baseURL = new URL(characters);
            } catch (Exception e7) {
                StringBuilder sbM112a = C0413b.m112a("Invalid URLBase: ");
                sbM112a.append(e7.toString());
                throw new SAXException(sbM112a.toString());
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void startElement(Descriptor.Device.ELEMENT element, Attributes attributes) {
            if (element.equals(SpecVersionHandler.f4859EL)) {
                MutableUDAVersion mutableUDAVersion = new MutableUDAVersion();
                getInstance().udaVersion = mutableUDAVersion;
                new SpecVersionHandler(mutableUDAVersion, this);
            }
            if (element.equals(DeviceHandler.f4853EL)) {
                new DeviceHandler(getInstance(), this);
            }
        }
    }

    public static class ServiceHandler extends DeviceDescriptorHandler<MutableService> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4857EL = Descriptor.Device.ELEMENT.service;

        public ServiceHandler(MutableService mutableService, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(mutableService, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void endElement(Descriptor.Device.ELEMENT element) {
            try {
                switch (C16581.f4852xcea4acb6[element.ordinal()]) {
                    case 23:
                        getInstance().serviceType = ServiceType.valueOf(getCharacters());
                        break;
                    case 24:
                        getInstance().serviceId = ServiceId.valueOf(getCharacters());
                        break;
                    case 25:
                        getInstance().descriptorURI = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                        break;
                    case 26:
                        getInstance().controlURI = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                        break;
                    case 27:
                        getInstance().eventSubscriptionURI = UDA10DeviceDescriptorBinderImpl.parseURI(getCharacters());
                        break;
                }
            } catch (InvalidValueException e7) {
                Logger logger = UDA10DeviceDescriptorBinderSAXImpl.log;
                StringBuilder sbM112a = C0413b.m112a("UPnP specification violation, skipping invalid service declaration. ");
                sbM112a.append(e7.getMessage());
                logger.warning(sbM112a.toString());
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return element.equals(f4857EL);
        }
    }

    public static class ServiceListHandler extends DeviceDescriptorHandler<List<MutableService>> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4858EL = Descriptor.Device.ELEMENT.serviceList;

        public ServiceListHandler(List<MutableService> list, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(list, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            boolean zEquals = element.equals(f4858EL);
            if (zEquals) {
                Iterator<MutableService> it = getInstance().iterator();
                while (it.hasNext()) {
                    MutableService next = it.next();
                    if (next.serviceType == null || next.serviceId == null) {
                        it.remove();
                    }
                }
            }
            return zEquals;
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void startElement(Descriptor.Device.ELEMENT element, Attributes attributes) {
            if (element.equals(ServiceHandler.f4857EL)) {
                MutableService mutableService = new MutableService();
                getInstance().add(mutableService);
                new ServiceHandler(mutableService, this);
            }
        }
    }

    public static class SpecVersionHandler extends DeviceDescriptorHandler<MutableUDAVersion> {

        /* renamed from: EL */
        public static final Descriptor.Device.ELEMENT f4859EL = Descriptor.Device.ELEMENT.specVersion;

        public SpecVersionHandler(MutableUDAVersion mutableUDAVersion, DeviceDescriptorHandler deviceDescriptorHandler) {
            super(mutableUDAVersion, deviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public void endElement(Descriptor.Device.ELEMENT element) {
            int i7 = C16581.f4852xcea4acb6[element.ordinal()];
            if (i7 == 2) {
                String strTrim = getCharacters().trim();
                if (!strTrim.equals(DiskLruCache.VERSION_1)) {
                    UDA10DeviceDescriptorBinderSAXImpl.log.warning("Unsupported UDA major version, ignoring: " + strTrim);
                    strTrim = DiskLruCache.VERSION_1;
                }
                getInstance().major = Integer.valueOf(strTrim).intValue();
                return;
            }
            if (i7 != 3) {
                return;
            }
            String strTrim2 = getCharacters().trim();
            if (!strTrim2.equals("0")) {
                UDA10DeviceDescriptorBinderSAXImpl.log.warning("Unsupported UDA minor version, ignoring: " + strTrim2);
                strTrim2 = "0";
            }
            getInstance().minor = Integer.valueOf(strTrim2).intValue();
        }

        @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderSAXImpl.DeviceDescriptorHandler
        public boolean isLastElement(Descriptor.Device.ELEMENT element) {
            return element.equals(f4859EL);
        }
    }

    @Override // org.fourthline.cling.binding.xml.UDA10DeviceDescriptorBinderImpl, org.fourthline.cling.binding.xml.DeviceDescriptorBinder
    public <D extends Device> D describe(D d7, String str) throws ValidationException, DescriptorBindingException {
        if (str == null || str.length() == 0) {
            throw new DescriptorBindingException("Null or empty descriptor");
        }
        try {
            log.fine("Populating device from XML descriptor: " + d7);
            C1728g c1728g = new C1728g();
            MutableDevice mutableDevice = new MutableDevice();
            new RootHandler(mutableDevice, c1728g);
            c1728g.parse(new InputSource(new StringReader(str.trim())));
            return (D) mutableDevice.build(d7);
        } catch (ValidationException e7) {
            throw e7;
        } catch (Exception e8) {
            StringBuilder sbM112a = C0413b.m112a("Could not parse device descriptor: ");
            sbM112a.append(e8.toString());
            throw new DescriptorBindingException(sbM112a.toString(), e8);
        }
    }
}
