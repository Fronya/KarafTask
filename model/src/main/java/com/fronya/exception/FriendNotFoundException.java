package com.fronya.exception;

import javax.xml.ws.WebFault;

@WebFault
public class FriendNotFoundException extends Exception{

    public FriendNotFoundException() {
        super("Person doesn't have friends that were born on specified year");
    }
}
