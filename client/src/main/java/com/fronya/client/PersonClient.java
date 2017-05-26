package com.fronya.client;


import com.fronya.exception.FriendNotFoundException;
import com.fronya.model.Person;
import com.fronya.model.PersonService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PersonClient {
    private PersonService service;
    private Person person;

    public PersonClient() {
        init();
    }

    public List<Person> getFriendsBornSameYear(int year){
        try {
            return service.getFriendsBornSameYear(person, year);
        } catch (FriendNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Person> getAllFriends(){
        return person.getFriends();
    }

    public void setService(PersonService service) {
        this.service = service;
    }

    private void init(){
        try {
            Person firstFriend = getPerson("Ann", 20, 5, 1995);
            Person secondFriend = getPerson("Max", 16, 7, 1995);
            Person thirdFriend = getPerson("Nik", 5, 2, 1996);

            person = getPerson("Andy", 4, 3, 1997);
            person.getFriends().add(firstFriend);
            person.getFriends().add(secondFriend);
            person.getFriends().add(thirdFriend);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    private Person getPerson(String name, int day, int month, int year) throws DatatypeConfigurationException {
        Person friend = new Person();
        friend.setName(name);
        Date birthday = new Date();
        birthday.setDate(day);
        birthday.setMonth(month);
        birthday.setYear(year);
        friend.setBirthday(birthday);
        return friend;
    }
}
