package com.rith.id.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public final class RecordNotFoundException extends RuntimeException {

    @Getter
    private final HttpStatus errorcode;
    private String errormessage;
    private String advicemsg;

    public RecordNotFoundException(final String errormessage, final Throwable cause, HttpStatus errorcode, String advicemsg) {
        super(errormessage, cause);
        this.errormessage = errormessage;
        this.errorcode = errorcode;
        this.advicemsg = advicemsg;
    }

    public RecordNotFoundException(final String errormessage, HttpStatus errorcode, String advicemsg) {
        super(errormessage);
        this.errormessage = errormessage;
        this.errorcode = errorcode;
        this.advicemsg = advicemsg;
    }
}
