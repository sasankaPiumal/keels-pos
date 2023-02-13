package com.pointOfSale.Keels.pointofsale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class EntryNotFoundException  extends RuntimeException{
    public EntryNotFoundException(String msg){
        super(msg);
    }
}
