package com.fronya.service;


import com.fronya.exception.FriendNotFoundException;
import com.fronya.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PersonServiceImplTest {
    private Person mainPerson;
    private Person firstFriend;
    private Person secondFriend;
    private Person thirdFriend;

    @Before
    public void init(){
        firstFriend = mock(Person.class);
        Calendar calendarFirstFriend = new GregorianCalendar(1995, 5, 20);
        when(firstFriend.getBirthday()).thenReturn(calendarFirstFriend.getTime());

        secondFriend = mock(Person.class);
        Calendar calendarSecondFriend = new GregorianCalendar(1995, 7, 16);
        when(secondFriend.getBirthday()).thenReturn(calendarSecondFriend.getTime());

        thirdFriend = mock(Person.class);
        Calendar calendarThirdFriend = new GregorianCalendar(1996, 2, 5);
        when(thirdFriend.getBirthday()).thenReturn(calendarThirdFriend.getTime());

        mainPerson = mock(Person.class);
        when(mainPerson.getFriends()).thenReturn(new ArrayList<Person>(){{
            add(firstFriend);
            add(secondFriend);
            add(thirdFriend);
        }});
    }

    @Test
    public void getFriendsBornSameYear_ValidYear_ListPerson() throws FriendNotFoundException{
        PersonServiceImpl service = new PersonServiceImpl();
        List<Person> friendsBornSameYear = service.getFriendsBornSameYear(mainPerson, 1995);

        List<Person> expectedFriends = new ArrayList<Person>(){{
            add(firstFriend);
            add(secondFriend);
        }};
        assertEquals(expectedFriends, friendsBornSameYear);
    }

    @Test(expected = FriendNotFoundException.class)
    public void getFriendsBornSameYear_InvalidYear_FriendNotFoundException() throws FriendNotFoundException {
        PersonServiceImpl service = new PersonServiceImpl();
        service.getFriendsBornSameYear(mainPerson, 2000);
    }

    @Test(expected = FriendNotFoundException.class)
    public void getFriendsBornSameYear_PersonNull_FriendNotFoundException() throws FriendNotFoundException{
        PersonServiceImpl service = new PersonServiceImpl();

        when(mainPerson.getFriends()).thenReturn(null);
        service.getFriendsBornSameYear(mainPerson, 1995);
    }

    @Test
    public void getFriendsBornSameYear_FriendNull_ListEmpty() throws FriendNotFoundException {
        when(mainPerson.getFriends()).thenReturn(new ArrayList<Person>(){{
            add(firstFriend);
            add(null);
            add(thirdFriend);
        }});
        PersonServiceImpl service = new PersonServiceImpl();
        List<Person> friendsBornSameYear = service.getFriendsBornSameYear(mainPerson, 1996);

        List<Person> expectedFriends = new ArrayList<Person>(){{
            add(thirdFriend);
        }};
        assertEquals(expectedFriends, friendsBornSameYear);
    }

    @Test(expected = FriendNotFoundException.class)
    public void getFriendsBornSameYear_FriendsNull_FriendNotFoundException() throws FriendNotFoundException {
        when(mainPerson.getFriends()).thenReturn(null);
        PersonServiceImpl service = new PersonServiceImpl();
        service.getFriendsBornSameYear(mainPerson, 1996);
    }
}
