package android.support.v7.widget;

import android.arch.lifecycle.C0063n;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.support.constraint.motion.C0080b;
import android.text.TextUtils;
import android.util.Xml;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes.dex */
class ActivityChooserModel extends DataSetObservable {
    public static final String ATTRIBUTE_ACTIVITY = "activity";
    public static final String ATTRIBUTE_TIME = "time";
    public static final String ATTRIBUTE_WEIGHT = "weight";
    public static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    public static final String TAG_HISTORICAL_RECORD = "historical-record";
    public static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private OnChooseActivityListener mActivityChoserModelPolicy;
    public final Context mContext;
    public final String mHistoryFileName;
    private Intent mIntent;
    public static final String LOG_TAG = "ActivityChooserModel";
    private static final Object sRegistryLock = new Object();
    private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
    private final Object mInstanceLock = new Object();
    private final List<ActivityResolveInfo> mActivities = new ArrayList();
    private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
    private ActivitySorter mActivitySorter = new DefaultSorter();
    private int mHistoryMaxSize = 50;
    public boolean mCanReadHistoricalData = true;
    private boolean mReadShareHistoryCalled = false;
    private boolean mHistoricalRecordsChanged = true;
    private boolean mReloadActivities = false;

    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ActivityResolveInfo.class == obj.getClass() && Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo) obj).weight);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public String toString() {
            StringBuilder sbM94a = C0080b.m94a("[", "resolveInfo:");
            sbM94a.append(this.resolveInfo.toString());
            sbM94a.append("; weight:");
            sbM94a.append(new BigDecimal(this.weight));
            sbM94a.append("]");
            return sbM94a.toString();
        }

        @Override // java.lang.Comparable
        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }
    }

    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    public static final class DefaultSorter implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();

        @Override // android.support.v7.widget.ActivityChooserModel.ActivitySorter
        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Map<ComponentName, ActivityResolveInfo> map = this.mPackageNameToActivityMap;
            map.clear();
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                ActivityResolveInfo activityResolveInfo = list.get(i7);
                activityResolveInfo.weight = 0.0f;
                ActivityInfo activityInfo = activityResolveInfo.resolveInfo.activityInfo;
                map.put(new ComponentName(activityInfo.packageName, activityInfo.name), activityResolveInfo);
            }
            float f7 = ActivityChooserModel.DEFAULT_HISTORICAL_RECORD_WEIGHT;
            for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                HistoricalRecord historicalRecord = list2.get(size2);
                ActivityResolveInfo activityResolveInfo2 = map.get(historicalRecord.activity);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight = (historicalRecord.weight * f7) + activityResolveInfo2.weight;
                    f7 *= WEIGHT_DECAY_COEFFICIENT;
                }
            }
            Collections.sort(list);
        }
    }

    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(String str, long j7, float f7) {
            this(ComponentName.unflattenFromString(str), j7, f7);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || HistoricalRecord.class != obj.getClass()) {
                return false;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            ComponentName componentName = this.activity;
            if (componentName == null) {
                if (historicalRecord.activity != null) {
                    return false;
                }
            } else if (!componentName.equals(historicalRecord.activity)) {
                return false;
            }
            return this.time == historicalRecord.time && Float.floatToIntBits(this.weight) == Float.floatToIntBits(historicalRecord.weight);
        }

        public int hashCode() {
            ComponentName componentName = this.activity;
            int iHashCode = componentName == null ? 0 : componentName.hashCode();
            long j7 = this.time;
            return Float.floatToIntBits(this.weight) + ((((iHashCode + 31) * 31) + ((int) (j7 ^ (j7 >>> 32)))) * 31);
        }

        public String toString() {
            StringBuilder sbM94a = C0080b.m94a("[", "; activity:");
            sbM94a.append(this.activity);
            sbM94a.append("; time:");
            sbM94a.append(this.time);
            sbM94a.append("; weight:");
            sbM94a.append(new BigDecimal(this.weight));
            sbM94a.append("]");
            return sbM94a.toString();
        }

        public HistoricalRecord(ComponentName componentName, long j7, float f7) {
            this.activity = componentName;
            this.time = j7;
            this.weight = f7;
        }
    }

    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    public final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void> {
        public PersistHistoryAsyncTask() {
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(Object... objArr) throws IOException {
            List list = (List) objArr[0];
            try {
                FileOutputStream fileOutputStreamOpenFileOutput = ActivityChooserModel.this.mContext.openFileOutput((String) objArr[1], 0);
                XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
                try {
                    try {
                        xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                        xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                        xmlSerializerNewSerializer.startTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                        int size = list.size();
                        for (int i7 = 0; i7 < size; i7++) {
                            HistoricalRecord historicalRecord = (HistoricalRecord) list.remove(0);
                            xmlSerializerNewSerializer.startTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                            xmlSerializerNewSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_ACTIVITY, historicalRecord.activity.flattenToString());
                            xmlSerializerNewSerializer.attribute(null, "time", String.valueOf(historicalRecord.time));
                            xmlSerializerNewSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_WEIGHT, String.valueOf(historicalRecord.weight));
                            xmlSerializerNewSerializer.endTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                        }
                        xmlSerializerNewSerializer.endTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                        xmlSerializerNewSerializer.endDocument();
                        ActivityChooserModel.this.mCanReadHistoricalData = true;
                    } catch (IOException unused) {
                        String str = ActivityChooserModel.LOG_TAG;
                        String str2 = ActivityChooserModel.this.mHistoryFileName;
                        ActivityChooserModel.this.mCanReadHistoricalData = true;
                        if (fileOutputStreamOpenFileOutput != null) {
                        }
                    } catch (IllegalArgumentException unused2) {
                        String str3 = ActivityChooserModel.LOG_TAG;
                        String str4 = ActivityChooserModel.this.mHistoryFileName;
                        ActivityChooserModel.this.mCanReadHistoricalData = true;
                        if (fileOutputStreamOpenFileOutput != null) {
                        }
                    } catch (IllegalStateException unused3) {
                        String str5 = ActivityChooserModel.LOG_TAG;
                        String str6 = ActivityChooserModel.this.mHistoryFileName;
                        ActivityChooserModel.this.mCanReadHistoricalData = true;
                        if (fileOutputStreamOpenFileOutput != null) {
                        }
                    }
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException unused4) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    ActivityChooserModel.this.mCanReadHistoricalData = true;
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException unused5) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused6) {
                String str7 = ActivityChooserModel.LOG_TAG;
                return null;
            }
        }
    }

    private ActivityChooserModel(Context context, String str) {
        this.mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(HISTORY_FILE_EXTENSION)) {
            this.mHistoryFileName = str;
        } else {
            this.mHistoryFileName = C0063n.m88a(str, HISTORY_FILE_EXTENSION);
        }
    }

    private boolean addHistoricalRecord(HistoricalRecord historicalRecord) {
        boolean zAdd = this.mHistoricalRecords.add(historicalRecord);
        if (zAdd) {
            this.mHistoricalRecordsChanged = true;
            pruneExcessiveHistoricalRecordsIfNeeded();
            persistHistoricalDataIfNeeded();
            sortActivitiesIfNeeded();
            notifyChanged();
        }
        return zAdd;
    }

    private void ensureConsistentState() {
        boolean zLoadActivitiesIfNeeded = loadActivitiesIfNeeded() | readHistoricalDataIfNeeded();
        pruneExcessiveHistoricalRecordsIfNeeded();
        if (zLoadActivitiesIfNeeded) {
            sortActivitiesIfNeeded();
            notifyChanged();
        }
    }

    public static ActivityChooserModel get(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (sRegistryLock) {
            Map<String, ActivityChooserModel> map = sDataModelRegistry;
            activityChooserModel = map.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                map.put(str, activityChooserModel);
            }
        }
        return activityChooserModel;
    }

    private boolean loadActivitiesIfNeeded() {
        if (!this.mReloadActivities || this.mIntent == null) {
            return false;
        }
        this.mReloadActivities = false;
        this.mActivities.clear();
        List<ResolveInfo> listQueryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
        int size = listQueryIntentActivities.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.mActivities.add(new ActivityResolveInfo(listQueryIntentActivities.get(i7)));
        }
        return true;
    }

    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if (TextUtils.isEmpty(this.mHistoryFileName)) {
                return;
            }
            new PersistHistoryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.mHistoricalRecords), this.mHistoryFileName);
        }
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int size = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (size <= 0) {
            return;
        }
        this.mHistoricalRecordsChanged = true;
        for (int i7 = 0; i7 < size; i7++) {
            this.mHistoricalRecords.remove(0);
        }
    }

    private boolean readHistoricalDataIfNeeded() throws IOException {
        if (!this.mCanReadHistoricalData || !this.mHistoricalRecordsChanged || TextUtils.isEmpty(this.mHistoryFileName)) {
            return false;
        }
        this.mCanReadHistoricalData = false;
        this.mReadShareHistoryCalled = true;
        readHistoricalDataImpl();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readHistoricalDataImpl() throws java.io.IOException {
        /*
            r9 = this;
            android.content.Context r0 = r9.mContext     // Catch: java.lang.Throwable -> L8e
            java.lang.String r1 = r9.mHistoryFileName     // Catch: java.lang.Throwable -> L8e
            java.io.FileInputStream r0 = r0.openFileInput(r1)     // Catch: java.lang.Throwable -> L8e
            org.xmlpull.v1.XmlPullParser r1 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            java.lang.String r2 = "UTF-8"
            r1.setInput(r0, r2)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            r2 = 0
        L12:
            r3 = 1
            if (r2 == r3) goto L1d
            r4 = 2
            if (r2 == r4) goto L1d
            int r2 = r1.next()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            goto L12
        L1d:
            java.lang.String r2 = "historical-records"
            java.lang.String r4 = r1.getName()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            if (r2 == 0) goto L79
            java.util.List<android.support.v7.widget.ActivityChooserModel$HistoricalRecord> r2 = r9.mHistoricalRecords     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            r2.clear()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
        L2e:
            int r4 = r1.next()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            if (r4 != r3) goto L3a
            if (r0 == 0) goto L8e
        L36:
            r0.close()
            goto L8e
        L3a:
            r5 = 3
            if (r4 == r5) goto L2e
            r5 = 4
            if (r4 != r5) goto L41
            goto L2e
        L41:
            java.lang.String r4 = r1.getName()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            java.lang.String r5 = "historical-record"
            boolean r4 = r5.equals(r4)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            if (r4 == 0) goto L71
            java.lang.String r4 = "activity"
            r5 = 0
            java.lang.String r4 = r1.getAttributeValue(r5, r4)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            java.lang.String r6 = "time"
            java.lang.String r6 = r1.getAttributeValue(r5, r6)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            long r6 = java.lang.Long.parseLong(r6)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            java.lang.String r8 = "weight"
            java.lang.String r5 = r1.getAttributeValue(r5, r8)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            float r5 = java.lang.Float.parseFloat(r5)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            android.support.v7.widget.ActivityChooserModel$HistoricalRecord r8 = new android.support.v7.widget.ActivityChooserModel$HistoricalRecord     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            r8.<init>(r4, r6, r5)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            r2.add(r8)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            goto L2e
        L71:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            java.lang.String r2 = "Share records file not well-formed."
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            throw r1     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
        L79:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            java.lang.String r2 = "Share records file does not start with historical-records tag."
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
            throw r1     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L88 org.xmlpull.v1.XmlPullParserException -> L8b
        L81:
            r1 = move-exception
            if (r0 == 0) goto L87
            r0.close()     // Catch: java.io.IOException -> L87
        L87:
            throw r1
        L88:
            if (r0 == 0) goto L8e
            goto L36
        L8b:
            if (r0 == 0) goto L8e
            goto L36
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActivityChooserModel.readHistoricalDataImpl():void");
    }

    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter == null || this.mIntent == null || this.mActivities.isEmpty() || this.mHistoricalRecords.isEmpty()) {
            return false;
        }
        this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
        return true;
    }

    public Intent chooseActivity(int i7) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == null) {
                return null;
            }
            ensureConsistentState();
            ActivityInfo activityInfo = this.mActivities.get(i7).resolveInfo.activityInfo;
            ComponentName componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
            Intent intent = new Intent(this.mIntent);
            intent.setComponent(componentName);
            if (this.mActivityChoserModelPolicy != null) {
                if (this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                    return null;
                }
            }
            addHistoricalRecord(new HistoricalRecord(componentName, System.currentTimeMillis(), DEFAULT_HISTORICAL_RECORD_WEIGHT));
            return intent;
        }
    }

    public ResolveInfo getActivity(int i7) {
        ResolveInfo resolveInfo;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            resolveInfo = this.mActivities.get(i7).resolveInfo;
        }
        return resolveInfo;
    }

    public int getActivityCount() {
        int size;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            size = this.mActivities.size();
        }
        return size;
    }

    public int getActivityIndex(ResolveInfo resolveInfo) {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            List<ActivityResolveInfo> list = this.mActivities;
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (list.get(i7).resolveInfo == resolveInfo) {
                    return i7;
                }
            }
            return -1;
        }
    }

    public ResolveInfo getDefaultActivity() {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            if (this.mActivities.isEmpty()) {
                return null;
            }
            return this.mActivities.get(0).resolveInfo;
        }
    }

    public int getHistoryMaxSize() {
        int i7;
        synchronized (this.mInstanceLock) {
            i7 = this.mHistoryMaxSize;
        }
        return i7;
    }

    public int getHistorySize() {
        int size;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            size = this.mHistoricalRecords.size();
        }
        return size;
    }

    public Intent getIntent() {
        Intent intent;
        synchronized (this.mInstanceLock) {
            intent = this.mIntent;
        }
        return intent;
    }

    public void setActivitySorter(ActivitySorter activitySorter) {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter == activitySorter) {
                return;
            }
            this.mActivitySorter = activitySorter;
            if (sortActivitiesIfNeeded()) {
                notifyChanged();
            }
        }
    }

    public void setDefaultActivity(int i7) {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = this.mActivities.get(i7);
            ActivityResolveInfo activityResolveInfo2 = this.mActivities.get(0);
            float f7 = activityResolveInfo2 != null ? (activityResolveInfo2.weight - activityResolveInfo.weight) + 5.0f : DEFAULT_HISTORICAL_RECORD_WEIGHT;
            ActivityInfo activityInfo = activityResolveInfo.resolveInfo.activityInfo;
            addHistoricalRecord(new HistoricalRecord(new ComponentName(activityInfo.packageName, activityInfo.name), System.currentTimeMillis(), f7));
        }
    }

    public void setHistoryMaxSize(int i7) {
        synchronized (this.mInstanceLock) {
            if (this.mHistoryMaxSize == i7) {
                return;
            }
            this.mHistoryMaxSize = i7;
            pruneExcessiveHistoricalRecordsIfNeeded();
            if (sortActivitiesIfNeeded()) {
                notifyChanged();
            }
        }
    }

    public void setIntent(Intent intent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == intent) {
                return;
            }
            this.mIntent = intent;
            this.mReloadActivities = true;
            ensureConsistentState();
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        synchronized (this.mInstanceLock) {
            this.mActivityChoserModelPolicy = onChooseActivityListener;
        }
    }
}
