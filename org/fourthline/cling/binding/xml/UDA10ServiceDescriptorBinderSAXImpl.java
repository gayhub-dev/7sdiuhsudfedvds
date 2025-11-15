package org.fourthline.cling.binding.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import org.fourthline.cling.binding.staging.MutableAction;
import org.fourthline.cling.binding.staging.MutableActionArgument;
import org.fourthline.cling.binding.staging.MutableAllowedValueRange;
import org.fourthline.cling.binding.staging.MutableService;
import org.fourthline.cling.binding.staging.MutableStateVariable;
import org.fourthline.cling.binding.xml.Descriptor;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.meta.StateVariableEventDetails;
import org.fourthline.cling.model.types.CustomDatatype;
import org.fourthline.cling.model.types.Datatype;
import org.seamless.xml.C1728g;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import p009b.C0413b;

/* loaded from: classes.dex */
public class UDA10ServiceDescriptorBinderSAXImpl extends UDA10ServiceDescriptorBinderImpl {
    private static Logger log = Logger.getLogger(ServiceDescriptorBinder.class.getName());

    /* renamed from: org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl$1 */
    public static /* synthetic */ class C16591 {

        /* renamed from: $SwitchMap$org$fourthline$cling$binding$xml$Descriptor$Service$ELEMENT */
        public static final /* synthetic */ int[] f4860x632136c5;

        static {
            int[] iArr = new int[Descriptor.Service.ELEMENT.values().length];
            f4860x632136c5 = iArr;
            try {
                iArr[Descriptor.Service.ELEMENT.name.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.direction.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.relatedStateVariable.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.retval.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.dataType.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.defaultValue.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.allowedValue.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.minimum.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.maximum.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4860x632136c5[Descriptor.Service.ELEMENT.step.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static class ActionArgumentHandler extends ServiceDescriptorHandler<MutableActionArgument> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4861EL = Descriptor.Service.ELEMENT.argument;

        public ActionArgumentHandler(MutableActionArgument mutableActionArgument, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(mutableActionArgument, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void endElement(Descriptor.Service.ELEMENT element) {
            int i7 = C16591.f4860x632136c5[element.ordinal()];
            if (i7 == 1) {
                getInstance().name = getCharacters();
                return;
            }
            if (i7 != 2) {
                if (i7 == 3) {
                    getInstance().relatedStateVariable = getCharacters();
                    return;
                } else {
                    if (i7 != 4) {
                        return;
                    }
                    getInstance().retval = true;
                    return;
                }
            }
            String characters = getCharacters();
            try {
                getInstance().direction = ActionArgument.Direction.valueOf(characters.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException unused) {
                UDA10ServiceDescriptorBinderSAXImpl.log.warning("UPnP specification violation: Invalid action argument direction, assuming 'IN': " + characters);
                getInstance().direction = ActionArgument.Direction.IN;
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4861EL);
        }
    }

    public static class ActionArgumentListHandler extends ServiceDescriptorHandler<List<MutableActionArgument>> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4862EL = Descriptor.Service.ELEMENT.argumentList;

        public ActionArgumentListHandler(List<MutableActionArgument> list, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(list, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4862EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
            if (element.equals(ActionArgumentHandler.f4861EL)) {
                MutableActionArgument mutableActionArgument = new MutableActionArgument();
                getInstance().add(mutableActionArgument);
                new ActionArgumentHandler(mutableActionArgument, this);
            }
        }
    }

    public static class ActionHandler extends ServiceDescriptorHandler<MutableAction> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4863EL = Descriptor.Service.ELEMENT.action;

        public ActionHandler(MutableAction mutableAction, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(mutableAction, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void endElement(Descriptor.Service.ELEMENT element) {
            if (C16591.f4860x632136c5[element.ordinal()] != 1) {
                return;
            }
            getInstance().name = getCharacters();
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4863EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
            if (element.equals(ActionArgumentListHandler.f4862EL)) {
                ArrayList arrayList = new ArrayList();
                getInstance().arguments = arrayList;
                new ActionArgumentListHandler(arrayList, this);
            }
        }
    }

    public static class ActionListHandler extends ServiceDescriptorHandler<List<MutableAction>> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4864EL = Descriptor.Service.ELEMENT.actionList;

        public ActionListHandler(List<MutableAction> list, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(list, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4864EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
            if (element.equals(ActionHandler.f4863EL)) {
                MutableAction mutableAction = new MutableAction();
                getInstance().add(mutableAction);
                new ActionHandler(mutableAction, this);
            }
        }
    }

    public static class AllowedValueListHandler extends ServiceDescriptorHandler<List<String>> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4865EL = Descriptor.Service.ELEMENT.allowedValueList;

        public AllowedValueListHandler(List<String> list, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(list, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void endElement(Descriptor.Service.ELEMENT element) {
            if (C16591.f4860x632136c5[element.ordinal()] != 7) {
                return;
            }
            getInstance().add(getCharacters());
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4865EL);
        }
    }

    public static class AllowedValueRangeHandler extends ServiceDescriptorHandler<MutableAllowedValueRange> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4866EL = Descriptor.Service.ELEMENT.allowedValueRange;

        public AllowedValueRangeHandler(MutableAllowedValueRange mutableAllowedValueRange, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(mutableAllowedValueRange, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void endElement(Descriptor.Service.ELEMENT element) {
            try {
                switch (C16591.f4860x632136c5[element.ordinal()]) {
                    case 8:
                        getInstance().minimum = Long.valueOf(getCharacters());
                        break;
                    case 9:
                        getInstance().maximum = Long.valueOf(getCharacters());
                        break;
                    case 10:
                        getInstance().step = Long.valueOf(getCharacters());
                        break;
                }
            } catch (Exception unused) {
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4866EL);
        }
    }

    public static class RootHandler extends ServiceDescriptorHandler<MutableService> {
        public RootHandler(MutableService mutableService, C1728g c1728g) {
            super(mutableService, c1728g);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
            if (element.equals(ActionListHandler.f4864EL)) {
                ArrayList arrayList = new ArrayList();
                getInstance().actions = arrayList;
                new ActionListHandler(arrayList, this);
            }
            if (element.equals(StateVariableListHandler.f4868EL)) {
                ArrayList arrayList2 = new ArrayList();
                getInstance().stateVariables = arrayList2;
                new StateVariableListHandler(arrayList2, this);
            }
        }
    }

    public static class ServiceDescriptorHandler<I> extends C1728g.b<I> {
        public ServiceDescriptorHandler(I i7) {
            super(i7);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            super.endElement(str, str2, str3);
            Descriptor.Service.ELEMENT elementValueOrNullOf = Descriptor.Service.ELEMENT.valueOrNullOf(str2);
            if (elementValueOrNullOf == null) {
                return;
            }
            endElement(elementValueOrNullOf);
        }

        public void endElement(Descriptor.Service.ELEMENT element) {
        }

        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            Descriptor.Service.ELEMENT elementValueOrNullOf = Descriptor.Service.ELEMENT.valueOrNullOf(str2);
            return elementValueOrNullOf != null && isLastElement(elementValueOrNullOf);
        }

        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return false;
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            super.startElement(str, str2, str3, attributes);
            Descriptor.Service.ELEMENT elementValueOrNullOf = Descriptor.Service.ELEMENT.valueOrNullOf(str2);
            if (elementValueOrNullOf == null) {
                return;
            }
            startElement(elementValueOrNullOf, attributes);
        }

        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
        }

        public ServiceDescriptorHandler(I i7, C1728g c1728g) {
            super(i7, c1728g);
        }

        public ServiceDescriptorHandler(I i7, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(i7, serviceDescriptorHandler);
        }

        public ServiceDescriptorHandler(I i7, C1728g c1728g, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(i7, c1728g, serviceDescriptorHandler);
        }
    }

    public static class StateVariableHandler extends ServiceDescriptorHandler<MutableStateVariable> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4867EL = Descriptor.Service.ELEMENT.stateVariable;

        public StateVariableHandler(MutableStateVariable mutableStateVariable, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(mutableStateVariable, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void endElement(Descriptor.Service.ELEMENT element) {
            int i7 = C16591.f4860x632136c5[element.ordinal()];
            if (i7 == 1) {
                getInstance().name = getCharacters();
                return;
            }
            if (i7 != 5) {
                if (i7 != 6) {
                    return;
                }
                getInstance().defaultValue = getCharacters();
            } else {
                String characters = getCharacters();
                Datatype.Builtin byDescriptorName = Datatype.Builtin.getByDescriptorName(characters);
                getInstance().dataType = byDescriptorName != null ? byDescriptorName.getDatatype() : new CustomDatatype(characters);
            }
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4867EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
            if (element.equals(AllowedValueListHandler.f4865EL)) {
                ArrayList arrayList = new ArrayList();
                getInstance().allowedValues = arrayList;
                new AllowedValueListHandler(arrayList, this);
            }
            if (element.equals(AllowedValueRangeHandler.f4866EL)) {
                MutableAllowedValueRange mutableAllowedValueRange = new MutableAllowedValueRange();
                getInstance().allowedValueRange = mutableAllowedValueRange;
                new AllowedValueRangeHandler(mutableAllowedValueRange, this);
            }
        }
    }

    public static class StateVariableListHandler extends ServiceDescriptorHandler<List<MutableStateVariable>> {

        /* renamed from: EL */
        public static final Descriptor.Service.ELEMENT f4868EL = Descriptor.Service.ELEMENT.serviceStateTable;

        public StateVariableListHandler(List<MutableStateVariable> list, ServiceDescriptorHandler serviceDescriptorHandler) {
            super(list, serviceDescriptorHandler);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public boolean isLastElement(Descriptor.Service.ELEMENT element) {
            return element.equals(f4868EL);
        }

        @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderSAXImpl.ServiceDescriptorHandler
        public void startElement(Descriptor.Service.ELEMENT element, Attributes attributes) {
            if (element.equals(StateVariableHandler.f4867EL)) {
                MutableStateVariable mutableStateVariable = new MutableStateVariable();
                String value = attributes.getValue(Descriptor.Service.ATTRIBUTE.sendEvents.toString());
                mutableStateVariable.eventDetails = new StateVariableEventDetails(value != null && value.toUpperCase(Locale.ROOT).equals("YES"));
                getInstance().add(mutableStateVariable);
                new StateVariableHandler(mutableStateVariable, this);
            }
        }
    }

    @Override // org.fourthline.cling.binding.xml.UDA10ServiceDescriptorBinderImpl, org.fourthline.cling.binding.xml.ServiceDescriptorBinder
    public <S extends Service> S describe(S s6, String str) throws ValidationException, DescriptorBindingException {
        if (str == null || str.length() == 0) {
            throw new DescriptorBindingException("Null or empty descriptor");
        }
        try {
            log.fine("Reading service from XML descriptor");
            C1728g c1728g = new C1728g();
            MutableService mutableService = new MutableService();
            hydrateBasic(mutableService, s6);
            new RootHandler(mutableService, c1728g);
            c1728g.parse(new InputSource(new StringReader(str.trim())));
            return (S) mutableService.build(s6.getDevice());
        } catch (ValidationException e7) {
            throw e7;
        } catch (Exception e8) {
            StringBuilder sbM112a = C0413b.m112a("Could not parse service descriptor: ");
            sbM112a.append(e8.toString());
            throw new DescriptorBindingException(sbM112a.toString(), e8);
        }
    }
}
