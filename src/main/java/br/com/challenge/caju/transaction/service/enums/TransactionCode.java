package br.com.challenge.caju.transaction.service.enums;

public enum TransactionCode {

    APPROVED("00"),
    INSUFFICIENT_FUND("51"),
    NOT_PROCESSED("07");

    private final String code;

    TransactionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
