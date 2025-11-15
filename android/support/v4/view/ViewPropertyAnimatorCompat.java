package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class ViewPropertyAnimatorCompat {
    public static final int LISTENER_TAG_ID = 2113929216;
    private static final String TAG = "ViewAnimatorCompat";
    private WeakReference<View> mView;
    public Runnable mStartAction = null;
    public Runnable mEndAction = null;
    public int mOldLayerType = -1;

    public static class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
        public boolean mAnimEndCalled;
        public ViewPropertyAnimatorCompat mVpa;

        public ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.mVpa = viewPropertyAnimatorCompat;
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View view) {
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationCancel(view);
            }
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            int i7 = this.mVpa.mOldLayerType;
            if (i7 > -1) {
                view.setLayerType(i7, null);
                this.mVpa.mOldLayerType = -1;
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVpa;
            Runnable runnable = viewPropertyAnimatorCompat.mEndAction;
            if (runnable != null) {
                viewPropertyAnimatorCompat.mEndAction = null;
                runnable.run();
            }
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationEnd(view);
            }
            this.mAnimEndCalled = true;
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
            this.mAnimEndCalled = false;
            if (this.mVpa.mOldLayerType > -1) {
                view.setLayerType(2, null);
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVpa;
            Runnable runnable = viewPropertyAnimatorCompat.mStartAction;
            if (runnable != null) {
                viewPropertyAnimatorCompat.mStartAction = null;
                runnable.run();
            }
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationStart(view);
            }
        }
    }

    public ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference<>(view);
    }

    private void setListenerInternal(final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new AnimatorListenerAdapter() { // from class: android.support.v4.view.ViewPropertyAnimatorCompat.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationCancel(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationEnd(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationStart(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public ViewPropertyAnimatorCompat alpha(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().alpha(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().alphaBy(f7);
        }
        return this;
    }

    public void cancel() {
        View view = this.mView.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long getDuration() {
        View view = this.mView.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public Interpolator getInterpolator() {
        View view = this.mView.get();
        if (view != null) {
            return (Interpolator) view.animate().getInterpolator();
        }
        return null;
    }

    public long getStartDelay() {
        View view = this.mView.get();
        if (view != null) {
            return view.animate().getStartDelay();
        }
        return 0L;
    }

    public ViewPropertyAnimatorCompat rotation(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotation(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationX(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationXBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationY(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().rotationYBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleX(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleXBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleY(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().scaleYBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long j7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setDuration(j7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = this.mView.get();
        if (view != null) {
            setListenerInternal(view, viewPropertyAnimatorListener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long j7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().setStartDelay(j7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().setUpdateListener(viewPropertyAnimatorUpdateListener != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v4.view.ViewPropertyAnimatorCompat.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    viewPropertyAnimatorUpdateListener.onAnimationUpdate(view);
                }
            } : null);
        }
        return this;
    }

    public void start() {
        View view = this.mView.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat translationX(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationX(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationXBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationY(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationYBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationZ(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().translationZBy(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().withEndAction(runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer() {
        View view = this.mView.get();
        if (view != null) {
            view.animate().withLayer();
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().withStartAction(runnable);
        }
        return this;
    }

    /* renamed from: x */
    public ViewPropertyAnimatorCompat m104x(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().x(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().xBy(f7);
        }
        return this;
    }

    /* renamed from: y */
    public ViewPropertyAnimatorCompat m105y(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().y(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().yBy(f7);
        }
        return this;
    }

    /* renamed from: z */
    public ViewPropertyAnimatorCompat m106z(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().z(f7);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f7) {
        View view = this.mView.get();
        if (view != null) {
            view.animate().zBy(f7);
        }
        return this;
    }
}
