package com.example.infrastructure.util.log;

/**
 * Copyright (C)
 * FileName: ElementExt
 *
 * @author: yufeng
 * @date: 2020/8/2 14:31
 * @description:
 */
public class ElementExt {

    private String traceId = "";
    private String spanId  = "";

    public ElementExt() {
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        if (this.spanId == null || this.spanId.equals("")) {
            this.spanId = SpanIdGenerator.getSpanId();
        }

        return this.spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }
}
