package com.alibaba.fastjson.parser;

@Deprecated
/* loaded from: classes.dex */
public class DefaultExtJSONParser extends DefaultJSONParser {
    public DefaultExtJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance());
    }

    public DefaultExtJSONParser(String str, ParserConfig parserConfig) {
        super(str, parserConfig);
    }

    public DefaultExtJSONParser(String str, ParserConfig parserConfig, int i7) {
        super(str, parserConfig, i7);
    }

    public DefaultExtJSONParser(char[] cArr, int i7, ParserConfig parserConfig, int i8) {
        super(cArr, i7, parserConfig, i8);
    }
}
