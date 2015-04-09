package com.kblyumkin.skuServices.exceptions;

import javax.xml.ws.WebFault;

@WebFault()
public class SkuFault extends Exception {
    public SkuFault() {
        super();
    }

    public SkuFault(String message) {
        super(message);
    }

    public SkuFault(String message, Throwable cause) {
        super(message, cause);
    }

}
