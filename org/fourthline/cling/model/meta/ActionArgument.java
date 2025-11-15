package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.Datatype;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ActionArgument<S extends Service> implements Validatable {
    private static final Logger log = Logger.getLogger(ActionArgument.class.getName());
    private Action<S> action;
    private final String[] aliases;
    private final Direction direction;
    private final String name;
    private final String relatedStateVariableName;
    private final boolean returnValue;

    public enum Direction {
        IN,
        OUT
    }

    public ActionArgument(String str, String str2, Direction direction) {
        this(str, new String[0], str2, direction, false);
    }

    public ActionArgument<S> deepCopy() {
        return new ActionArgument<>(getName(), getAliases(), getRelatedStateVariableName(), getDirection(), isReturnValue());
    }

    public Action<S> getAction() {
        return this.action;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public Datatype getDatatype() {
        return getAction().getService().getDatatype(this);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public String getName() {
        return this.name;
    }

    public String getRelatedStateVariableName() {
        return this.relatedStateVariableName;
    }

    public boolean isNameOrAlias(String str) {
        if (getName().equalsIgnoreCase(str)) {
            return true;
        }
        for (String str2 : this.aliases) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isReturnValue() {
        return this.returnValue;
    }

    public void setAction(Action<S> action) {
        if (this.action != null) {
            throw new IllegalStateException("Final value has been set already, model is immutable");
        }
        this.action = action;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(", ");
        sbM112a.append(getDirection());
        sbM112a.append(") ");
        sbM112a.append(getName());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getName() == null || getName().length() == 0) {
            Class<?> cls = getClass();
            StringBuilder sbM112a = C0413b.m112a("Argument without name of: ");
            sbM112a.append(getAction());
            arrayList.add(new ValidationError(cls, "name", sbM112a.toString()));
        } else if (!ModelUtil.isValidUDAName(getName())) {
            Logger logger = log;
            StringBuilder sbM112a2 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a2.append(getAction().getService().getDevice());
            logger.warning(sbM112a2.toString());
            logger.warning("Invalid argument name: " + this);
        } else if (getName().length() > 32) {
            Logger logger2 = log;
            StringBuilder sbM112a3 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a3.append(getAction().getService().getDevice());
            logger2.warning(sbM112a3.toString());
            logger2.warning("Argument name should be less than 32 characters: " + this);
        }
        if (getDirection() == null) {
            Class<?> cls2 = getClass();
            StringBuilder sbM112a4 = C0413b.m112a("Argument '");
            sbM112a4.append(getName());
            sbM112a4.append("' requires a direction, either IN or OUT");
            arrayList.add(new ValidationError(cls2, "direction", sbM112a4.toString()));
        }
        if (isReturnValue() && getDirection() != Direction.OUT) {
            Class<?> cls3 = getClass();
            StringBuilder sbM112a5 = C0413b.m112a("Return value argument '");
            sbM112a5.append(getName());
            sbM112a5.append("' must be direction OUT");
            arrayList.add(new ValidationError(cls3, "direction", sbM112a5.toString()));
        }
        return arrayList;
    }

    public ActionArgument(String str, String[] strArr, String str2, Direction direction) {
        this(str, strArr, str2, direction, false);
    }

    public ActionArgument(String str, String str2, Direction direction, boolean z6) {
        this(str, new String[0], str2, direction, z6);
    }

    public ActionArgument(String str, String[] strArr, String str2, Direction direction, boolean z6) {
        this.name = str;
        this.aliases = strArr;
        this.relatedStateVariableName = str2;
        this.direction = direction;
        this.returnValue = z6;
    }
}
