package org.fourthline.cling.support.model.dlna.types;

/* loaded from: classes.dex */
public class CodedDataBuffer {
    private Long size;
    private TransferMechanism tranfer;

    public enum TransferMechanism {
        IMMEDIATELY,
        TIMESTAMP,
        OTHER
    }

    public CodedDataBuffer(Long l7, TransferMechanism transferMechanism) {
        this.size = l7;
        this.tranfer = transferMechanism;
    }

    public Long getSize() {
        return this.size;
    }

    public TransferMechanism getTranfer() {
        return this.tranfer;
    }
}
