package com.alibaba.fastjson.parser.deserializer;

import android.arch.lifecycle.C0063n;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexerBase;
import java.lang.reflect.Type;
import p009b.C0413b;

/* loaded from: classes.dex */
public class JSONPDeserializer implements ObjectDeserializer {
    public static final JSONPDeserializer instance = new JSONPDeserializer();

    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.alibaba.fastjson.JSONPObject] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        int i7;
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.getLexer();
        String strScanSymbolUnQuoted = jSONLexerBase.scanSymbolUnQuoted(defaultJSONParser.getSymbolTable());
        jSONLexerBase.nextToken();
        int i8 = jSONLexerBase.token();
        if (i8 == 25) {
            String strScanSymbolUnQuoted2 = jSONLexerBase.scanSymbolUnQuoted(defaultJSONParser.getSymbolTable());
            strScanSymbolUnQuoted = C0063n.m88a(strScanSymbolUnQuoted, ".") + strScanSymbolUnQuoted2;
            jSONLexerBase.nextToken();
            i8 = jSONLexerBase.token();
        }
        ?? r12 = (T) new JSONPObject(strScanSymbolUnQuoted);
        if (i8 != 10) {
            StringBuilder sbM112a = C0413b.m112a("illegal jsonp : ");
            sbM112a.append(jSONLexerBase.info());
            throw new JSONException(sbM112a.toString());
        }
        jSONLexerBase.nextToken();
        while (true) {
            r12.addParameter(defaultJSONParser.parse());
            i7 = jSONLexerBase.token();
            if (i7 != 16) {
                break;
            }
            jSONLexerBase.nextToken();
        }
        if (i7 != 11) {
            StringBuilder sbM112a2 = C0413b.m112a("illegal jsonp : ");
            sbM112a2.append(jSONLexerBase.info());
            throw new JSONException(sbM112a2.toString());
        }
        jSONLexerBase.nextToken();
        if (jSONLexerBase.token() == 24) {
            jSONLexerBase.nextToken();
        }
        return r12;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 0;
    }
}
