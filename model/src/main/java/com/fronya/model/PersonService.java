package com.fronya.model;

import com.fronya.exception.FriendNotFoundException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "personService")
public interface PersonService {

    @WebMethod
    @WebResult(name = "friends")
    List<Person> getFriendsBornSameYear(@WebParam(name = "person") Person person,
                                        @WebParam(name="year") int year) throws FriendNotFoundException;
}
