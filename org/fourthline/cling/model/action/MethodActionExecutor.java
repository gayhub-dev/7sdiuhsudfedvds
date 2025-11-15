package org.fourthline.cling.model.action;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.state.StateVariableAccessor;

/* loaded from: classes.dex */
public class MethodActionExecutor extends AbstractActionExecutor {
    private static Logger log = Logger.getLogger(MethodActionExecutor.class.getName());
    public Method method;

    public MethodActionExecutor(Method method) {
        this.method = method;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0157  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object[] createInputArgumentValues(org.fourthline.cling.model.action.ActionInvocation<org.fourthline.cling.model.meta.LocalService> r17, java.lang.reflect.Method r18) throws org.fourthline.cling.model.action.ActionException, java.lang.NoSuchMethodException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.model.action.MethodActionExecutor.createInputArgumentValues(org.fourthline.cling.model.action.ActionInvocation, java.lang.reflect.Method):java.lang.Object[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c7  */
    @Override // org.fourthline.cling.model.action.AbstractActionExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(org.fourthline.cling.model.action.ActionInvocation<org.fourthline.cling.model.meta.LocalService> r6, java.lang.Object r7) throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.reflect.Method r0 = r5.method
            java.lang.Object[] r0 = r5.createInputArgumentValues(r6, r0)
            org.fourthline.cling.model.meta.Action r1 = r6.getAction()
            boolean r1 = r1.hasOutputArguments()
            if (r1 != 0) goto L2a
            java.util.logging.Logger r6 = org.fourthline.cling.model.action.MethodActionExecutor.log
            java.lang.String r1 = "Calling local service method with no output arguments: "
            java.lang.StringBuilder r1 = p009b.C0413b.m112a(r1)
            java.lang.reflect.Method r2 = r5.method
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r6.fine(r1)
            java.lang.reflect.Method r6 = r5.method
            p186x2.C2074b.m2497t(r6, r7, r0)
            return
        L2a:
            java.lang.reflect.Method r1 = r5.method
            java.lang.Class r1 = r1.getReturnType()
            java.lang.Class r2 = java.lang.Void.TYPE
            boolean r1 = r1.equals(r2)
            java.util.logging.Logger r2 = org.fourthline.cling.model.action.MethodActionExecutor.log
            java.lang.String r3 = "Calling local service method with output arguments: "
            java.lang.StringBuilder r3 = p009b.C0413b.m112a(r3)
            java.lang.reflect.Method r4 = r5.method
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.fine(r3)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L63
            java.util.logging.Logger r1 = org.fourthline.cling.model.action.MethodActionExecutor.log
            java.lang.String r4 = "Action method is void, calling declared accessors(s) on service instance to retrieve ouput argument(s)"
            r1.fine(r4)
            java.lang.reflect.Method r1 = r5.method
            p186x2.C2074b.m2497t(r1, r7, r0)
            org.fourthline.cling.model.meta.Action r0 = r6.getAction()
            java.lang.Object r7 = r5.readOutputArgumentValues(r0, r7)
            goto L7e
        L63:
            boolean r1 = r5.isUseOutputArgumentAccessors(r6)
            if (r1 == 0) goto L80
            java.util.logging.Logger r1 = org.fourthline.cling.model.action.MethodActionExecutor.log
            java.lang.String r4 = "Action method is not void, calling declared accessor(s) on returned instance to retrieve ouput argument(s)"
            r1.fine(r4)
            java.lang.reflect.Method r1 = r5.method
            java.lang.Object r7 = p186x2.C2074b.m2497t(r1, r7, r0)
            org.fourthline.cling.model.meta.Action r0 = r6.getAction()
            java.lang.Object r7 = r5.readOutputArgumentValues(r0, r7)
        L7e:
            r0 = 1
            goto L8e
        L80:
            java.util.logging.Logger r1 = org.fourthline.cling.model.action.MethodActionExecutor.log
            java.lang.String r4 = "Action method is not void, using returned value as (single) output argument"
            r1.fine(r4)
            java.lang.reflect.Method r1 = r5.method
            java.lang.Object r7 = p186x2.C2074b.m2497t(r1, r7, r0)
            r0 = 0
        L8e:
            org.fourthline.cling.model.meta.Action r1 = r6.getAction()
            org.fourthline.cling.model.meta.ActionArgument[] r1 = r1.getOutputArguments()
            if (r0 == 0) goto Lbe
            boolean r0 = r7 instanceof java.lang.Object[]
            if (r0 == 0) goto Lbe
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            java.util.logging.Logger r0 = org.fourthline.cling.model.action.MethodActionExecutor.log
            java.lang.String r3 = "Accessors returned Object[], setting output argument values: "
            java.lang.StringBuilder r3 = p009b.C0413b.m112a(r3)
            int r4 = r7.length
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.fine(r3)
        Lb1:
            int r0 = r1.length
            if (r2 >= r0) goto Lc6
            r0 = r1[r2]
            r3 = r7[r2]
            r5.setOutputArgumentValue(r6, r0, r3)
            int r2 = r2 + 1
            goto Lb1
        Lbe:
            int r0 = r1.length
            if (r0 != r3) goto Lc7
            r0 = r1[r2]
            r5.setOutputArgumentValue(r6, r0, r7)
        Lc6:
            return
        Lc7:
            org.fourthline.cling.model.action.ActionException r6 = new org.fourthline.cling.model.action.ActionException
            org.fourthline.cling.model.types.ErrorCode r7 = org.fourthline.cling.model.types.ErrorCode.ACTION_FAILED
            java.lang.String r0 = "Method return does not match required number of output arguments: "
            java.lang.StringBuilder r0 = p009b.C0413b.m112a(r0)
            int r1 = r1.length
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r7, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.model.action.MethodActionExecutor.execute(org.fourthline.cling.model.action.ActionInvocation, java.lang.Object):void");
    }

    public Method getMethod() {
        return this.method;
    }

    public boolean isUseOutputArgumentAccessors(ActionInvocation<LocalService> actionInvocation) {
        for (ActionArgument actionArgument : actionInvocation.getAction().getOutputArguments()) {
            if (getOutputArgumentAccessors().get(actionArgument) != null) {
                return true;
            }
        }
        return false;
    }

    public MethodActionExecutor(Map<ActionArgument<LocalService>, StateVariableAccessor> map, Method method) {
        super(map);
        this.method = method;
    }
}
