package android.support.constraint.motion;

import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.MotionScene;

/* loaded from: classes.dex */
public class TransitionBuilder {
    private static final String TAG = "TransitionBuilder";

    public static MotionScene.Transition buildTransition(MotionScene motionScene, int i7, int i8, ConstraintSet constraintSet, int i9, ConstraintSet constraintSet2) {
        MotionScene.Transition transition = new MotionScene.Transition(i7, motionScene, i8, i9);
        updateConstraintSetInMotionScene(motionScene, transition, constraintSet, constraintSet2);
        return transition;
    }

    private static void updateConstraintSetInMotionScene(MotionScene motionScene, MotionScene.Transition transition, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
        int startConstraintSetId = transition.getStartConstraintSetId();
        int endConstraintSetId = transition.getEndConstraintSetId();
        motionScene.setConstraintSet(startConstraintSetId, constraintSet);
        motionScene.setConstraintSet(endConstraintSetId, constraintSet2);
    }

    public static void validate(MotionLayout motionLayout) {
        MotionScene motionScene = motionLayout.mScene;
        if (motionScene == null) {
            throw new RuntimeException("Invalid motion layout. Layout missing Motion Scene.");
        }
        if (!motionScene.validateLayout(motionLayout)) {
            throw new RuntimeException("MotionLayout doesn't have the right motion scene.");
        }
        if (motionScene.mCurrentTransition == null || motionScene.getDefinedTransitions().isEmpty()) {
            throw new RuntimeException("Invalid motion layout. Motion Scene doesn't have any transition.");
        }
    }
}
