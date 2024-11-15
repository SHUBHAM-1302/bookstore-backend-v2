package com.rith.id.constants;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum Status {

    SOLD("sold"),
    AVAILABLE("available");

    String value;
    Status(String status) {
        value = status;
    }

    @Override
    public String toString() {
        return value;
    }

    public Status fromValue(String value) {
        for (Status b : Status.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
