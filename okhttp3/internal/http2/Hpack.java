package okhttp3.internal.http2;

import android.support.v7.widget.ActivityChooserView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.ctvit.network.model.HttpHeaders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
final class Hpack {
    public static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX;
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    public static final Header[] STATIC_HEADER_TABLE;

    public static final class Reader {
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        public int headerCount;
        private final List<Header> headerList;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;
        public int nextHeaderIndex;
        private final BufferedSource source;

        public Reader(int i7, Source source) {
            this(i7, i7, source);
        }

        private void adjustDynamicTableByteCount() {
            int i7 = this.maxDynamicTableByteCount;
            int i8 = this.dynamicTableByteCount;
            if (i7 < i8) {
                if (i7 == 0) {
                    clearDynamicTable();
                } else {
                    evictToRecoverBytes(i8 - i7);
                }
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int dynamicTableIndex(int i7) {
            return this.nextHeaderIndex + 1 + i7;
        }

        private int evictToRecoverBytes(int i7) {
            int i8;
            int i9 = 0;
            if (i7 > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    i8 = this.nextHeaderIndex;
                    if (length < i8 || i7 <= 0) {
                        break;
                    }
                    Header[] headerArr = this.dynamicTable;
                    i7 -= headerArr[length].hpackSize;
                    this.dynamicTableByteCount -= headerArr[length].hpackSize;
                    this.headerCount--;
                    i9++;
                }
                Header[] headerArr2 = this.dynamicTable;
                System.arraycopy(headerArr2, i8 + 1, headerArr2, i8 + 1 + i9, this.headerCount);
                this.nextHeaderIndex += i9;
            }
            return i9;
        }

        private ByteString getName(int i7) throws IOException {
            if (isStaticHeader(i7)) {
                return Hpack.STATIC_HEADER_TABLE[i7].name;
            }
            int iDynamicTableIndex = dynamicTableIndex(i7 - Hpack.STATIC_HEADER_TABLE.length);
            if (iDynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (iDynamicTableIndex < headerArr.length) {
                    return headerArr[iDynamicTableIndex].name;
                }
            }
            StringBuilder sbM112a = C0413b.m112a("Header index too large ");
            sbM112a.append(i7 + 1);
            throw new IOException(sbM112a.toString());
        }

        private void insertIntoDynamicTable(int i7, Header header) {
            this.headerList.add(header);
            int i8 = header.hpackSize;
            if (i7 != -1) {
                i8 -= this.dynamicTable[dynamicTableIndex(i7)].hpackSize;
            }
            int i9 = this.maxDynamicTableByteCount;
            if (i8 > i9) {
                clearDynamicTable();
                return;
            }
            int iEvictToRecoverBytes = evictToRecoverBytes((this.dynamicTableByteCount + i8) - i9);
            if (i7 == -1) {
                int i10 = this.headerCount + 1;
                Header[] headerArr = this.dynamicTable;
                if (i10 > headerArr.length) {
                    Header[] headerArr2 = new Header[headerArr.length * 2];
                    System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = headerArr2;
                }
                int i11 = this.nextHeaderIndex;
                this.nextHeaderIndex = i11 - 1;
                this.dynamicTable[i11] = header;
                this.headerCount++;
            } else {
                this.dynamicTable[dynamicTableIndex(i7) + iEvictToRecoverBytes + i7] = header;
            }
            this.dynamicTableByteCount += i8;
        }

        private boolean isStaticHeader(int i7) {
            return i7 >= 0 && i7 <= Hpack.STATIC_HEADER_TABLE.length - 1;
        }

        private int readByte() {
            return this.source.readByte() & 255;
        }

        private void readIndexedHeader(int i7) throws IOException {
            if (isStaticHeader(i7)) {
                this.headerList.add(Hpack.STATIC_HEADER_TABLE[i7]);
                return;
            }
            int iDynamicTableIndex = dynamicTableIndex(i7 - Hpack.STATIC_HEADER_TABLE.length);
            if (iDynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (iDynamicTableIndex < headerArr.length) {
                    this.headerList.add(headerArr[iDynamicTableIndex]);
                    return;
                }
            }
            StringBuilder sbM112a = C0413b.m112a("Header index too large ");
            sbM112a.append(i7 + 1);
            throw new IOException(sbM112a.toString());
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int i7) {
            insertIntoDynamicTable(-1, new Header(getName(i7), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() {
            insertIntoDynamicTable(-1, new Header(Hpack.checkLowercase(readByteString()), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int i7) throws IOException {
            this.headerList.add(new Header(getName(i7), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            this.headerList.add(new Header(Hpack.checkLowercase(readByteString()), readByteString()));
        }

        public List<Header> getAndResetHeaderList() {
            ArrayList arrayList = new ArrayList(this.headerList);
            this.headerList.clear();
            return arrayList;
        }

        public int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        public ByteString readByteString() {
            int i7 = readByte();
            boolean z6 = (i7 & 128) == 128;
            int i8 = readInt(i7, 127);
            return z6 ? ByteString.m1869of(Huffman.get().decode(this.source.readByteArray(i8))) : this.source.readByteString(i8);
        }

        public void readHeaders() throws IOException {
            while (!this.source.exhausted()) {
                int i7 = this.source.readByte() & 255;
                if (i7 == 128) {
                    throw new IOException("index == 0");
                }
                if ((i7 & 128) == 128) {
                    readIndexedHeader(readInt(i7, 127) - 1);
                } else if (i7 == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((i7 & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(i7, 63) - 1);
                } else if ((i7 & 32) == 32) {
                    int i8 = readInt(i7, 31);
                    this.maxDynamicTableByteCount = i8;
                    if (i8 < 0 || i8 > this.headerTableSizeSetting) {
                        StringBuilder sbM112a = C0413b.m112a("Invalid dynamic table size update ");
                        sbM112a.append(this.maxDynamicTableByteCount);
                        throw new IOException(sbM112a.toString());
                    }
                    adjustDynamicTableByteCount();
                } else if (i7 == 16 || i7 == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(i7, 15) - 1);
                }
            }
        }

        public int readInt(int i7, int i8) {
            int i9 = i7 & i8;
            if (i9 < i8) {
                return i9;
            }
            int i10 = 0;
            while (true) {
                int i11 = readByte();
                if ((i11 & 128) == 0) {
                    return i8 + (i11 << i10);
                }
                i8 += (i11 & 127) << i10;
                i10 += 7;
            }
        }

        public Reader(int i7, int i8, Source source) {
            this.headerList = new ArrayList();
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = r0.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = i7;
            this.maxDynamicTableByteCount = i8;
            this.source = Okio.buffer(source);
        }
    }

    public static final class Writer {
        private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
        private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        private boolean emitDynamicTableSizeUpdate;
        public int headerCount;
        public int headerTableSizeSetting;
        public int maxDynamicTableByteCount;
        public int nextHeaderIndex;
        private final Buffer out;
        private int smallestHeaderTableSizeSetting;
        private final boolean useCompression;

        public Writer(Buffer buffer) {
            this(4096, true, buffer);
        }

        private void adjustDynamicTableByteCount() {
            int i7 = this.maxDynamicTableByteCount;
            int i8 = this.dynamicTableByteCount;
            if (i7 < i8) {
                if (i7 == 0) {
                    clearDynamicTable();
                } else {
                    evictToRecoverBytes(i8 - i7);
                }
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i7) {
            int i8;
            int i9 = 0;
            if (i7 > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    i8 = this.nextHeaderIndex;
                    if (length < i8 || i7 <= 0) {
                        break;
                    }
                    Header[] headerArr = this.dynamicTable;
                    i7 -= headerArr[length].hpackSize;
                    this.dynamicTableByteCount -= headerArr[length].hpackSize;
                    this.headerCount--;
                    i9++;
                }
                Header[] headerArr2 = this.dynamicTable;
                System.arraycopy(headerArr2, i8 + 1, headerArr2, i8 + 1 + i9, this.headerCount);
                Header[] headerArr3 = this.dynamicTable;
                int i10 = this.nextHeaderIndex;
                Arrays.fill(headerArr3, i10 + 1, i10 + 1 + i9, (Object) null);
                this.nextHeaderIndex += i9;
            }
            return i9;
        }

        private void insertIntoDynamicTable(Header header) {
            int i7 = header.hpackSize;
            int i8 = this.maxDynamicTableByteCount;
            if (i7 > i8) {
                clearDynamicTable();
                return;
            }
            evictToRecoverBytes((this.dynamicTableByteCount + i7) - i8);
            int i9 = this.headerCount + 1;
            Header[] headerArr = this.dynamicTable;
            if (i9 > headerArr.length) {
                Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = headerArr2;
            }
            int i10 = this.nextHeaderIndex;
            this.nextHeaderIndex = i10 - 1;
            this.dynamicTable[i10] = header;
            this.headerCount++;
            this.dynamicTableByteCount += i7;
        }

        public void setHeaderTableSizeSetting(int i7) {
            this.headerTableSizeSetting = i7;
            int iMin = Math.min(i7, 16384);
            int i8 = this.maxDynamicTableByteCount;
            if (i8 == iMin) {
                return;
            }
            if (iMin < i8) {
                this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, iMin);
            }
            this.emitDynamicTableSizeUpdate = true;
            this.maxDynamicTableByteCount = iMin;
            adjustDynamicTableByteCount();
        }

        public void writeByteString(ByteString byteString) {
            if (!this.useCompression || Huffman.get().encodedLength(byteString) >= byteString.size()) {
                writeInt(byteString.size(), 127, 0);
                this.out.write(byteString);
                return;
            }
            Buffer buffer = new Buffer();
            Huffman.get().encode(byteString, buffer);
            ByteString byteString2 = buffer.readByteString();
            writeInt(byteString2.size(), 127, 128);
            this.out.write(byteString2);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void writeHeaders(java.util.List<okhttp3.internal.http2.Header> r14) {
            /*
                Method dump skipped, instructions count: 236
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Hpack.Writer.writeHeaders(java.util.List):void");
        }

        public void writeInt(int i7, int i8, int i9) {
            if (i7 < i8) {
                this.out.writeByte(i7 | i9);
                return;
            }
            this.out.writeByte(i9 | i8);
            int i10 = i7 - i8;
            while (i10 >= 128) {
                this.out.writeByte(128 | (i10 & 127));
                i10 >>>= 7;
            }
            this.out.writeByte(i10);
        }

        public Writer(int i7, boolean z6, Buffer buffer) {
            this.smallestHeaderTableSizeSetting = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = r0.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = i7;
            this.maxDynamicTableByteCount = i7;
            this.useCompression = z6;
            this.out = buffer;
        }
    }

    static {
        ByteString byteString = Header.TARGET_METHOD;
        ByteString byteString2 = Header.TARGET_PATH;
        ByteString byteString3 = Header.TARGET_SCHEME;
        ByteString byteString4 = Header.RESPONSE_STATUS;
        STATIC_HEADER_TABLE = new Header[]{new Header(Header.TARGET_AUTHORITY, ""), new Header(byteString, "GET"), new Header(byteString, "POST"), new Header(byteString2, ServiceReference.DELIMITER), new Header(byteString2, "/index.html"), new Header(byteString3, "http"), new Header(byteString3, "https"), new Header(byteString4, "200"), new Header(byteString4, "204"), new Header(byteString4, "206"), new Header(byteString4, "304"), new Header(byteString4, "400"), new Header(byteString4, "404"), new Header(byteString4, "500"), new Header("accept-charset", ""), new Header("accept-encoding", HttpHeaders.HEAD_VALUE_ACCEPT_ENCODING), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header(RequestParameters.SUBRESOURCE_LOCATION, ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header(RequestParameters.SUBRESOURCE_REFERER, ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
        NAME_TO_FIRST_INDEX = nameToFirstIndex();
    }

    private Hpack() {
    }

    public static ByteString checkLowercase(ByteString byteString) throws IOException {
        int size = byteString.size();
        for (int i7 = 0; i7 < size; i7++) {
            byte b7 = byteString.getByte(i7);
            if (b7 >= 65 && b7 <= 90) {
                StringBuilder sbM112a = C0413b.m112a("PROTOCOL_ERROR response malformed: mixed case name: ");
                sbM112a.append(byteString.utf8());
                throw new IOException(sbM112a.toString());
            }
        }
        return byteString;
    }

    private static Map<ByteString, Integer> nameToFirstIndex() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        int i7 = 0;
        while (true) {
            Header[] headerArr = STATIC_HEADER_TABLE;
            if (i7 >= headerArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(headerArr[i7].name)) {
                linkedHashMap.put(headerArr[i7].name, Integer.valueOf(i7));
            }
            i7++;
        }
    }
}
