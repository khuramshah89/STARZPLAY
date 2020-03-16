package com.starzplay.payment.payment.Exception;

/**
 * Created by Khuram on 3/14/2020.
 */
public class RecordNotFound extends RuntimeException {

    public RecordNotFound() {
        super("Record Not Found");
    }

    public RecordNotFound(String msg) {
        super(msg);
    }

}
