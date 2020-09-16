package com.example.infrastructure.util.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright (C)
 * FileName: LogMessage
 *
 * @author: yufeng
 * @date: 2020/8/2 14:31
 * @description:
 */
public class LogMessage {

    public static String HEADER_TRACEID = "example-header-rid";
    public static String HEADER_SPANID = "example-header-spanid";
    private static String DEFAULT_DLTAG = "_undef";
    private static ThreadLocal<ElementExt> extElements = new InheritableThreadLocal<ElementExt>() {
        protected ElementExt initialValue() {
            return new ElementExt();
        }
    };
    private String dltag = "";
    private String cspanId = "";
    private List<LogKV> logElements = new ArrayList();

    public LogMessage() {
    }

    public static String getTraceId() {
        return ((ElementExt)extElements.get()).getTraceId();
    }

    public static String getSpanId() {
        return ((ElementExt)extElements.get()).getSpanId();
    }

    public static void setTraceId(String traceId) {
        ((ElementExt)extElements.get()).setTraceId(traceId);
    }

    public static void setSpanId(String spanId) {
        ((ElementExt)extElements.get()).setSpanId(spanId);
    }

    public static String generatorNewSpanid() {
        String newSpanid = SpanIdGenerator.getSpanId();
        return newSpanid;
    }

    public static String genertorNewTraceid() {
        String traceid = SpanIdGenerator.getTraceId();
        return traceid;
    }

    public LogMessage setCspanId(String cspanId) {
        this.cspanId = cspanId;
        return this;
    }

    public LogMessage setDltag(String dltag) {
        this.dltag = dltag;
        return this;
    }

    public LogMessage add(String key, Object value) {
        LogKV logKV = new LogKV(key, value);
        this.logElements.add(logKV);
        return this;
    }

    public List<LogKV> getLogElements() {
        return this.logElements;
    }

    public void setLogElements(List<LogKV> logElements) {
        this.logElements = logElements;
    }

    public String getDltag() {
        return this.dltag != null && !this.dltag.equals("") ? this.dltag : DEFAULT_DLTAG;
    }

    public String getCspanId() {
        return this.cspanId;
    }

    public static void remove() {
        extElements.remove();
    }

    public String toString() {
        ElementExt extElement = (ElementExt)extElements.get();
        StringBuffer sb = new StringBuffer();
        sb.append(this.getDltag());
        sb.append("||traceid=");
        sb.append(extElement.getTraceId());
        sb.append("||spanid=");
        sb.append(extElement.getSpanId());
        sb.append("||cspanid=");
        sb.append(this.getCspanId());
        if (this.logElements.size() > 0) {
            Iterator var4 = this.logElements.iterator();

            while(var4.hasNext()) {
                LogKV param = (LogKV)var4.next();
                sb.append("||");
                sb.append(param.getKey());
                sb.append("=");
                sb.append(param.getValue());
            }
        }

        return sb.toString();
    }
}
