package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.appcompat.C0308R;
import android.support.v7.view.CollapsibleActionView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    public static final boolean DBG = false;
    public static final AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER = new AutoCompleteTextViewReflector();
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    public static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    public final ImageView mCloseButton;
    private final ImageView mCollapsedIcon;
    private int mCollapsedImeOptions;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    public final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    public View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
    public final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private final View mSearchPlate;
    public final SearchAutoComplete mSearchSrcTextView;
    private Rect mSearchSrcTextViewBounds;
    private Rect mSearchSrtTextViewBoundsExpanded;
    public SearchableInfo mSearchable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    public CursorAdapter mSuggestionsAdapter;
    private int[] mTemp;
    private int[] mTemp2;
    public View.OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private UpdatableTouchDelegate mTouchDelegate;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    public final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    public static class AutoCompleteTextViewReflector {
        private Method doAfterTextChanged;
        private Method doBeforeTextChanged;
        private Method ensureImeVisible;
        private Method showSoftInputUnchecked;

        public AutoCompleteTextViewReflector() throws NoSuchMethodException, SecurityException {
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.doBeforeTextChanged = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.doAfterTextChanged = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.ensureImeVisible = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        public void doAfterTextChanged(AutoCompleteTextView autoCompleteTextView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Method method = this.doAfterTextChanged;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void doBeforeTextChanged(AutoCompleteTextView autoCompleteTextView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Method method = this.doBeforeTextChanged;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void ensureImeVisible(AutoCompleteTextView autoCompleteTextView, boolean z6) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Method method = this.ensureImeVisible;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z6));
                } catch (Exception unused) {
                }
            }
        }
    }

    public interface OnCloseListener {
        boolean onClose();
    }

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    public interface OnSuggestionListener {
        boolean onSuggestionClick(int i7);

        boolean onSuggestionSelect(int i7);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v7.widget.SearchView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public boolean isIconified;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("SearchView.SavedState{");
            sbM112a.append(Integer.toHexString(System.identityHashCode(this)));
            sbM112a.append(" isIconified=");
            sbM112a.append(this.isIconified);
            sbM112a.append("}");
            return sbM112a.toString();
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeValue(Boolean.valueOf(this.isIconified));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isIconified = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private boolean mHasPendingShowSoftInputRequest;
        public final Runnable mRunShowSoftInputIfNecessary;
        private SearchView mSearchView;
        private int mThreshold;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i7 = configuration.screenWidthDp;
            int i8 = configuration.screenHeightDp;
            if (i7 < 960 || i8 < 720 || configuration.orientation != 2) {
                return i7 < 600 ? (i7 < 640 || i8 < 480) ? Opcodes.IF_ICMPNE : Opcodes.CHECKCAST : Opcodes.CHECKCAST;
            }
            return 256;
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.mThreshold <= 0 || super.enoughToFilter();
        }

        public boolean isEmpty() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        @Override // android.support.v7.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.mHasPendingShowSoftInputRequest) {
                removeCallbacks(this.mRunShowSoftInputIfNecessary);
                post(this.mRunShowSoftInputIfNecessary);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z6, int i7, Rect rect) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            super.onFocusChanged(z6, i7, rect);
            this.mSearchView.onTextFocusChanged();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i7, KeyEvent keyEvent) {
            if (i7 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.mSearchView.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i7, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z6) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            super.onWindowFocusChanged(z6);
            if (z6 && this.mSearchView.hasFocus() && getVisibility() == 0) {
                this.mHasPendingShowSoftInputRequest = true;
                if (SearchView.isLandscapeMode(getContext())) {
                    SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z6) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z6) {
                this.mHasPendingShowSoftInputRequest = false;
                removeCallbacks(this.mRunShowSoftInputIfNecessary);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.mHasPendingShowSoftInputRequest = true;
                    return;
                }
                this.mHasPendingShowSoftInputRequest = false;
                removeCallbacks(this.mRunShowSoftInputIfNecessary);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.mSearchView = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i7) {
            super.setThreshold(i7);
            this.mThreshold = i7;
        }

        public void showSoftInputIfNecessary() {
            if (this.mHasPendingShowSoftInputRequest) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.mHasPendingShowSoftInputRequest = false;
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, C0308R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i7) {
            super(context, attributeSet, i7);
            this.mRunShowSoftInputIfNecessary = new Runnable() { // from class: android.support.v7.widget.SearchView.SearchAutoComplete.1
                @Override // java.lang.Runnable
                public void run() {
                    SearchAutoComplete.this.showSoftInputIfNecessary();
                }
            };
            this.mThreshold = getThreshold();
        }
    }

    public static class UpdatableTouchDelegate extends TouchDelegate {
        private final Rect mActualBounds;
        private boolean mDelegateTargeted;
        private final View mDelegateView;
        private final int mSlop;
        private final Rect mSlopBounds;
        private final Rect mTargetBounds;

        public UpdatableTouchDelegate(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.mTargetBounds = new Rect();
            this.mSlopBounds = new Rect();
            this.mActualBounds = new Rect();
            setBounds(rect, rect2);
            this.mDelegateView = view;
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z6;
            boolean z7;
            int x6 = (int) motionEvent.getX();
            int y6 = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z8 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z7 = this.mDelegateTargeted;
                    if (z7 && !this.mSlopBounds.contains(x6, y6)) {
                        z8 = z7;
                        z6 = false;
                    }
                } else {
                    if (action == 3) {
                        z7 = this.mDelegateTargeted;
                        this.mDelegateTargeted = false;
                    }
                    z6 = true;
                    z8 = false;
                }
                z8 = z7;
                z6 = true;
            } else {
                if (this.mTargetBounds.contains(x6, y6)) {
                    this.mDelegateTargeted = true;
                    z6 = true;
                }
                z6 = true;
                z8 = false;
            }
            if (!z8) {
                return false;
            }
            if (!z6 || this.mActualBounds.contains(x6, y6)) {
                Rect rect = this.mActualBounds;
                motionEvent.setLocation(x6 - rect.left, y6 - rect.top);
            } else {
                motionEvent.setLocation(this.mDelegateView.getWidth() / 2, this.mDelegateView.getHeight() / 2);
            }
            return this.mDelegateView.dispatchTouchEvent(motionEvent);
        }

        public void setBounds(Rect rect, Rect rect2) {
            this.mTargetBounds.set(rect);
            this.mSlopBounds.set(rect);
            Rect rect3 = this.mSlopBounds;
            int i7 = this.mSlop;
            rect3.inset(-i7, -i7);
            this.mActualBounds.set(rect2);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private Intent createIntent(String str, Uri uri, String str2, String str3, int i7, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.mUserQuery);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.mAppSearchData;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i7 != 0) {
            intent.putExtra("action_key", i7);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.mSearchable.getSearchActivity());
        return intent;
    }

    private Intent createIntentFromSuggestion(Cursor cursor, int i7, String str) {
        String columnString;
        try {
            try {
                String columnString2 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_action");
                if (columnString2 == null) {
                    columnString2 = this.mSearchable.getSuggestIntentAction();
                }
                if (columnString2 == null) {
                    columnString2 = "android.intent.action.SEARCH";
                }
                String str2 = columnString2;
                String columnString3 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data");
                if (columnString3 == null) {
                    columnString3 = this.mSearchable.getSuggestIntentData();
                }
                if (columnString3 != null && (columnString = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data_id")) != null) {
                    columnString3 = columnString3 + ServiceReference.DELIMITER + Uri.encode(columnString);
                }
                return createIntent(str2, columnString3 == null ? null : Uri.parse(columnString3), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query"), i7, str);
            } catch (RuntimeException unused) {
                return null;
            }
        } catch (RuntimeException unused2) {
            cursor.getPosition();
            return null;
        }
    }

    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.mAppSearchData;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent createVoiceWebSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    private void getChildBoundsWithinSearchView(View view, Rect rect) {
        view.getLocationInWindow(this.mTemp);
        getLocationInWindow(this.mTemp2);
        int[] iArr = this.mTemp;
        int i7 = iArr[1];
        int[] iArr2 = this.mTemp2;
        int i8 = i7 - iArr2[1];
        int i9 = iArr[0] - iArr2[0];
        rect.set(i9, i8, view.getWidth() + i9, view.getHeight() + i8);
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        if (!this.mIconifiedByDefault || this.mSearchHintIcon == null) {
            return charSequence;
        }
        int textSize = (int) (this.mSearchSrcTextView.getTextSize() * 1.25d);
        this.mSearchHintIcon.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(C0308R.dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(C0308R.dimen.abc_search_view_preferred_width);
    }

    private boolean hasVoiceSearch() {
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
            intent = this.mVoiceWebSearchIntent;
        } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
            intent = this.mVoiceAppSearchIntent;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    public static boolean isLandscapeMode(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private boolean isSubmitAreaEnabled() {
        return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !isIconified();
    }

    private void launchIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed launch activity: ");
            sb.append(intent);
        }
    }

    private boolean launchSuggestion(int i7, int i8, String str) {
        Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor == null || !cursor.moveToPosition(i7)) {
            return false;
        }
        launchIntent(createIntentFromSuggestion(cursor, i8, str));
        return true;
    }

    private void postUpdateFocusedState() {
        post(this.mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int i7) {
        Editable text = this.mSearchSrcTextView.getText();
        Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i7)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceConvertToString = this.mSuggestionsAdapter.convertToString(cursor);
        if (charSequenceConvertToString != null) {
            setQuery(charSequenceConvertToString);
        } else {
            setQuery(text);
        }
    }

    private void updateCloseButton() {
        boolean z6 = true;
        boolean z7 = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        if (!z7 && (!this.mIconifiedByDefault || this.mExpandedInActionView)) {
            z6 = false;
        }
        this.mCloseButton.setVisibility(z6 ? 0 : 8);
        Drawable drawable = this.mCloseButton.getDrawable();
        if (drawable != null) {
            drawable.setState(z7 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void updateQueryHint() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(getDecoratedHint(queryHint));
    }

    private void updateSearchAutoComplete() {
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int inputType = this.mSearchable.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.mSearchSrcTextView.setInputType(inputType);
        CursorAdapter cursorAdapter = this.mSuggestionsAdapter;
        if (cursorAdapter != null) {
            cursorAdapter.changeCursor(null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSuggestionsAdapter = suggestionsAdapter;
            this.mSearchSrcTextView.setAdapter(suggestionsAdapter);
            ((SuggestionsAdapter) this.mSuggestionsAdapter).setQueryRefinement(this.mQueryRefinement ? 2 : 1);
        }
    }

    private void updateSubmitArea() {
        this.mSubmitArea.setVisibility((isSubmitAreaEnabled() && (this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) ? 0 : 8);
    }

    private void updateSubmitButton(boolean z6) {
        this.mGoButton.setVisibility((this.mSubmitButtonEnabled && isSubmitAreaEnabled() && hasFocus() && (z6 || !this.mVoiceButtonEnabled)) ? 0 : 8);
    }

    private void updateViewsVisibility(boolean z6) {
        this.mIconified = z6;
        int i7 = z6 ? 0 : 8;
        boolean z7 = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility(i7);
        updateSubmitButton(z7);
        this.mSearchEditFrame.setVisibility(z6 ? 8 : 0);
        this.mCollapsedIcon.setVisibility((this.mCollapsedIcon.getDrawable() == null || this.mIconifiedByDefault) ? 8 : 0);
        updateCloseButton();
        updateVoiceButton(!z7);
        updateSubmitArea();
    }

    private void updateVoiceButton(boolean z6) {
        int i7 = 8;
        if (this.mVoiceButtonEnabled && !isIconified() && z6) {
            this.mGoButton.setVisibility(8);
            i7 = 0;
        }
        this.mVoiceButton.setVisibility(i7);
    }

    public void adjustDropDownSizeAndPosition() {
        if (this.mDropDownAnchor.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.mSearchPlate.getPaddingLeft();
            Rect rect = new Rect();
            boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
            int dimensionPixelSize = this.mIconifiedByDefault ? resources.getDimensionPixelSize(C0308R.dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(C0308R.dimen.abc_dropdownitem_icon_width) : 0;
            this.mSearchSrcTextView.getDropDownBackground().getPadding(rect);
            this.mSearchSrcTextView.setDropDownHorizontalOffset(zIsLayoutRtl ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.mSearchSrcTextView.setDropDownWidth((((this.mDropDownAnchor.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mSearchSrcTextView.setImeVisibility(false);
        this.mClearingFocus = false;
    }

    public void forceSuggestionQuery() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        AutoCompleteTextViewReflector autoCompleteTextViewReflector = HIDDEN_METHOD_INVOKER;
        autoCompleteTextViewReflector.doBeforeTextChanged(this.mSearchSrcTextView);
        autoCompleteTextViewReflector.doAfterTextChanged(this.mSearchSrcTextView);
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    @Nullable
    public CharSequence getQueryHint() {
        CharSequence charSequence = this.mQueryHint;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.mSearchable;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.mDefaultQueryHint : getContext().getText(this.mSearchable.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    public int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    public void launchQuerySearch(int i7, String str, String str2) {
        getContext().startActivity(createIntent("android.intent.action.SEARCH", null, null, str2, i7, str));
    }

    @Override // android.support.v7.view.CollapsibleActionView
    public void onActionViewCollapsed() {
        setQuery("", false);
        clearFocus();
        updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    @Override // android.support.v7.view.CollapsibleActionView
    public void onActionViewExpanded() {
        if (this.mExpandedInActionView) {
            return;
        }
        this.mExpandedInActionView = true;
        int imeOptions = this.mSearchSrcTextView.getImeOptions();
        this.mCollapsedImeOptions = imeOptions;
        this.mSearchSrcTextView.setImeOptions(imeOptions | 33554432);
        this.mSearchSrcTextView.setText("");
        setIconified(false);
    }

    public void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            this.mSearchSrcTextView.setText("");
            this.mSearchSrcTextView.requestFocus();
            this.mSearchSrcTextView.setImeVisibility(true);
        } else if (this.mIconifiedByDefault) {
            OnCloseListener onCloseListener = this.mOnCloseListener;
            if (onCloseListener == null || !onCloseListener.onClose()) {
                clearFocus();
                updateViewsVisibility(true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.mUpdateDrawableStateRunnable);
        post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    public boolean onItemClicked(int i7, int i8, String str) {
        OnSuggestionListener onSuggestionListener = this.mOnSuggestionListener;
        if (onSuggestionListener != null && onSuggestionListener.onSuggestionClick(i7)) {
            return false;
        }
        launchSuggestion(i7, 0, null);
        this.mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
        return true;
    }

    public boolean onItemSelected(int i7) {
        OnSuggestionListener onSuggestionListener = this.mOnSuggestionListener;
        if (onSuggestionListener != null && onSuggestionListener.onSuggestionSelect(i7)) {
            return false;
        }
        rewriteQueryFromSuggestion(i7);
        return true;
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6) {
            getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
            Rect rect = this.mSearchSrtTextViewBoundsExpanded;
            Rect rect2 = this.mSearchSrcTextViewBounds;
            rect.set(rect2.left, 0, rect2.right, i10 - i8);
            UpdatableTouchDelegate updatableTouchDelegate = this.mTouchDelegate;
            if (updatableTouchDelegate != null) {
                updatableTouchDelegate.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
                return;
            }
            UpdatableTouchDelegate updatableTouchDelegate2 = new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
            this.mTouchDelegate = updatableTouchDelegate2;
            setTouchDelegate(updatableTouchDelegate2);
        }
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i7, int i8) {
        int i9;
        if (isIconified()) {
            super.onMeasure(i7, i8);
            return;
        }
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (mode == Integer.MIN_VALUE) {
            int i10 = this.mMaxWidth;
            size = i10 > 0 ? Math.min(i10, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.mMaxWidth;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i9 = this.mMaxWidth) > 0) {
            size = Math.min(i9, size);
        }
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    public void onQueryRefine(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        updateViewsVisibility(savedState.isIconified);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.isIconified = isIconified();
        return savedState;
    }

    public void onSearchClicked() {
        updateViewsVisibility(false);
        this.mSearchSrcTextView.requestFocus();
        this.mSearchSrcTextView.setImeVisibility(true);
        View.OnClickListener onClickListener = this.mOnSearchClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void onSubmitQuery() {
        Editable text = this.mSearchSrcTextView.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        OnQueryTextListener onQueryTextListener = this.mOnQueryChangeListener;
        if (onQueryTextListener == null || !onQueryTextListener.onQueryTextSubmit(text.toString())) {
            if (this.mSearchable != null) {
                launchQuerySearch(0, null, text.toString());
            }
            this.mSearchSrcTextView.setImeVisibility(false);
            dismissSuggestions();
        }
    }

    public boolean onSuggestionsKey(View view, int i7, KeyEvent keyEvent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.mSearchable != null && this.mSuggestionsAdapter != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i7 == 66 || i7 == 84 || i7 == 61) {
                return onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
            }
            if (i7 == 21 || i7 == 22) {
                this.mSearchSrcTextView.setSelection(i7 == 21 ? 0 : this.mSearchSrcTextView.length());
                this.mSearchSrcTextView.setListSelection(0);
                this.mSearchSrcTextView.clearListSelection();
                HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
                return true;
            }
            if (i7 == 19) {
                this.mSearchSrcTextView.getListSelection();
                return false;
            }
        }
        return false;
    }

    public void onTextChanged(CharSequence charSequence) {
        Editable text = this.mSearchSrcTextView.getText();
        this.mUserQuery = text;
        boolean z6 = !TextUtils.isEmpty(text);
        updateSubmitButton(z6);
        updateVoiceButton(!z6);
        updateCloseButton();
        updateSubmitArea();
        if (this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    public void onTextFocusChanged() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        updateViewsVisibility(isIconified());
        postUpdateFocusedState();
        if (this.mSearchSrcTextView.hasFocus()) {
            forceSuggestionQuery();
        }
    }

    public void onVoiceClicked() {
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        postUpdateFocusedState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i7, Rect rect) {
        if (this.mClearingFocus || !isFocusable()) {
            return false;
        }
        if (isIconified()) {
            return super.requestFocus(i7, rect);
        }
        boolean zRequestFocus = this.mSearchSrcTextView.requestFocus(i7, rect);
        if (zRequestFocus) {
            updateViewsVisibility(false);
        }
        return zRequestFocus;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAppSearchData(Bundle bundle) {
        this.mAppSearchData = bundle;
    }

    public void setIconified(boolean z6) {
        if (z6) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public void setIconifiedByDefault(boolean z6) {
        if (this.mIconifiedByDefault == z6) {
            return;
        }
        this.mIconifiedByDefault = z6;
        updateViewsVisibility(z6);
        updateQueryHint();
    }

    public void setImeOptions(int i7) {
        this.mSearchSrcTextView.setImeOptions(i7);
    }

    public void setInputType(int i7) {
        this.mSearchSrcTextView.setInputType(i7);
    }

    public void setMaxWidth(int i7) {
        this.mMaxWidth = i7;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.mOnSuggestionListener = onSuggestionListener;
    }

    public void setQuery(CharSequence charSequence, boolean z6) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.mUserQuery = charSequence;
        }
        if (!z6 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        onSubmitQuery();
    }

    public void setQueryHint(@Nullable CharSequence charSequence) {
        this.mQueryHint = charSequence;
        updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean z6) {
        this.mQueryRefinement = z6;
        CursorAdapter cursorAdapter = this.mSuggestionsAdapter;
        if (cursorAdapter instanceof SuggestionsAdapter) {
            ((SuggestionsAdapter) cursorAdapter).setQueryRefinement(z6 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.mSearchable = searchableInfo;
        if (searchableInfo != null) {
            updateSearchAutoComplete();
            updateQueryHint();
        }
        boolean zHasVoiceSearch = hasVoiceSearch();
        this.mVoiceButtonEnabled = zHasVoiceSearch;
        if (zHasVoiceSearch) {
            this.mSearchSrcTextView.setPrivateImeOptions(IME_OPTION_NO_MICROPHONE);
        }
        updateViewsVisibility(isIconified());
    }

    public void setSubmitButtonEnabled(boolean z6) {
        this.mSubmitButtonEnabled = z6;
        updateViewsVisibility(isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.mSuggestionsAdapter = cursorAdapter;
        this.mSearchSrcTextView.setAdapter(cursorAdapter);
    }

    public void updateFocusedState() {
        int[] iArr = this.mSearchSrcTextView.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.mSearchPlate.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.mSubmitArea.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0308R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mSearchSrcTextViewBounds = new Rect();
        this.mSearchSrtTextViewBoundsExpanded = new Rect();
        this.mTemp = new int[2];
        this.mTemp2 = new int[2];
        this.mUpdateDrawableStateRunnable = new Runnable() { // from class: android.support.v7.widget.SearchView.1
            @Override // java.lang.Runnable
            public void run() {
                SearchView.this.updateFocusedState();
            }
        };
        this.mReleaseCursorRunnable = new Runnable() { // from class: android.support.v7.widget.SearchView.2
            @Override // java.lang.Runnable
            public void run() {
                CursorAdapter cursorAdapter = SearchView.this.mSuggestionsAdapter;
                if (cursorAdapter == null || !(cursorAdapter instanceof SuggestionsAdapter)) {
                    return;
                }
                cursorAdapter.changeCursor(null);
            }
        };
        this.mOutsideDrawablesCache = new WeakHashMap<>();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: android.support.v7.widget.SearchView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                SearchView searchView = SearchView.this;
                if (view == searchView.mSearchButton) {
                    searchView.onSearchClicked();
                    return;
                }
                if (view == searchView.mCloseButton) {
                    searchView.onCloseClicked();
                    return;
                }
                if (view == searchView.mGoButton) {
                    searchView.onSubmitQuery();
                } else if (view == searchView.mVoiceButton) {
                    searchView.onVoiceClicked();
                } else if (view == searchView.mSearchSrcTextView) {
                    searchView.forceSuggestionQuery();
                }
            }
        };
        this.mOnClickListener = onClickListener;
        this.mTextKeyListener = new View.OnKeyListener() { // from class: android.support.v7.widget.SearchView.6
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i8, KeyEvent keyEvent) {
                SearchView searchView = SearchView.this;
                if (searchView.mSearchable == null) {
                    return false;
                }
                if (searchView.mSearchSrcTextView.isPopupShowing() && SearchView.this.mSearchSrcTextView.getListSelection() != -1) {
                    return SearchView.this.onSuggestionsKey(view, i8, keyEvent);
                }
                if (SearchView.this.mSearchSrcTextView.isEmpty() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i8 != 66) {
                    return false;
                }
                view.cancelLongPress();
                SearchView searchView2 = SearchView.this;
                searchView2.launchQuerySearch(0, null, searchView2.mSearchSrcTextView.getText().toString());
                return true;
            }
        };
        TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { // from class: android.support.v7.widget.SearchView.7
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                SearchView.this.onSubmitQuery();
                return true;
            }
        };
        this.mOnEditorActionListener = onEditorActionListener;
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() { // from class: android.support.v7.widget.SearchView.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j7) {
                SearchView.this.onItemClicked(i8, 0, null);
            }
        };
        this.mOnItemClickListener = onItemClickListener;
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: android.support.v7.widget.SearchView.9
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long j7) {
                SearchView.this.onItemSelected(i8);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.mOnItemSelectedListener = onItemSelectedListener;
        this.mTextWatcher = new TextWatcher() { // from class: android.support.v7.widget.SearchView.10
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
                SearchView.this.onTextChanged(charSequence);
            }
        };
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0308R.styleable.SearchView, i7, 0);
        LayoutInflater.from(context).inflate(tintTypedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.SearchView_layout, C0308R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(C0308R.id.search_src_text);
        this.mSearchSrcTextView = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.mSearchEditFrame = findViewById(C0308R.id.search_edit_frame);
        View viewFindViewById = findViewById(C0308R.id.search_plate);
        this.mSearchPlate = viewFindViewById;
        View viewFindViewById2 = findViewById(C0308R.id.submit_area);
        this.mSubmitArea = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(C0308R.id.search_button);
        this.mSearchButton = imageView;
        ImageView imageView2 = (ImageView) findViewById(C0308R.id.search_go_btn);
        this.mGoButton = imageView2;
        ImageView imageView3 = (ImageView) findViewById(C0308R.id.search_close_btn);
        this.mCloseButton = imageView3;
        ImageView imageView4 = (ImageView) findViewById(C0308R.id.search_voice_btn);
        this.mVoiceButton = imageView4;
        ImageView imageView5 = (ImageView) findViewById(C0308R.id.search_mag_icon);
        this.mCollapsedIcon = imageView5;
        ViewCompat.setBackground(viewFindViewById, tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.SearchView_queryBackground));
        ViewCompat.setBackground(viewFindViewById2, tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.SearchView_submitBackground));
        int i8 = C0308R.styleable.SearchView_searchIcon;
        imageView.setImageDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(i8));
        imageView2.setImageDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.SearchView_goIcon));
        imageView3.setImageDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(i8));
        this.mSearchHintIcon = tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.SearchView_searchHintIcon);
        TooltipCompat.setTooltipText(imageView, getResources().getString(C0308R.string.abc_searchview_description_search));
        this.mSuggestionRowLayout = tintTypedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.SearchView_suggestionRowLayout, C0308R.layout.abc_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = tintTypedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        searchAutoComplete.setOnClickListener(onClickListener);
        searchAutoComplete.addTextChangedListener(this.mTextWatcher);
        searchAutoComplete.setOnEditorActionListener(onEditorActionListener);
        searchAutoComplete.setOnItemClickListener(onItemClickListener);
        searchAutoComplete.setOnItemSelectedListener(onItemSelectedListener);
        searchAutoComplete.setOnKeyListener(this.mTextKeyListener);
        searchAutoComplete.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: android.support.v7.widget.SearchView.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z6) {
                SearchView searchView = SearchView.this;
                View.OnFocusChangeListener onFocusChangeListener = searchView.mOnQueryTextFocusChangeListener;
                if (onFocusChangeListener != null) {
                    onFocusChangeListener.onFocusChange(searchView, z6);
                }
            }
        });
        setIconifiedByDefault(tintTypedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.SearchView_iconifiedByDefault, true));
        int dimensionPixelSize = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(C0308R.styleable.SearchView_android_maxWidth, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.mDefaultQueryHint = tintTypedArrayObtainStyledAttributes.getText(C0308R.styleable.SearchView_defaultQueryHint);
        this.mQueryHint = tintTypedArrayObtainStyledAttributes.getText(C0308R.styleable.SearchView_queryHint);
        int i9 = tintTypedArrayObtainStyledAttributes.getInt(C0308R.styleable.SearchView_android_imeOptions, -1);
        if (i9 != -1) {
            setImeOptions(i9);
        }
        int i10 = tintTypedArrayObtainStyledAttributes.getInt(C0308R.styleable.SearchView_android_inputType, -1);
        if (i10 != -1) {
            setInputType(i10);
        }
        setFocusable(tintTypedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.SearchView_android_focusable, true));
        tintTypedArrayObtainStyledAttributes.recycle();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.mDropDownAnchor = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: android.support.v7.widget.SearchView.4
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
                    SearchView.this.adjustDropDownSizeAndPosition();
                }
            });
        }
        updateViewsVisibility(this.mIconifiedByDefault);
        updateQueryHint();
    }

    private void setQuery(CharSequence charSequence) {
        this.mSearchSrcTextView.setText(charSequence);
        this.mSearchSrcTextView.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }
}
