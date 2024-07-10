package br.com.challenge.caju.transaction.service.enums;

import java.util.HashMap;
import java.util.Map;

public enum MCC {
    FOOD_5411("5411", BalanceType.FOOD),
    FOOD_5412("5412", BalanceType.FOOD),
    MEAL_5811("5811", BalanceType.MEAL),
    MEAL_5812("5812", BalanceType.MEAL),
    CASH_DEFAULT("default", BalanceType.CASH);

    private final String code;
    private final BalanceType balanceType;

    MCC(String code, BalanceType balanceType) {
        this.code = code;
        this.balanceType = balanceType;
    }

    public String getCode() {
        return code;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    private static final Map<String, BalanceType> mccToBalanceTypeMap = new HashMap<>();

    static {
        for (MCC mcc : values()) {
            mccToBalanceTypeMap.put(mcc.code, mcc.balanceType);
        }
    }

    public static BalanceType getBalanceType(String code) {
        return mccToBalanceTypeMap.getOrDefault(code, BalanceType.CASH);
    }
}