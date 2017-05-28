package com.fronya.service;


import com.fronya.exception.FriendNotFoundException;
import com.fronya.model.Person;
import com.fronya.model.PersonService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonServiceImpl implements PersonService{

    @Override
    public List<Person> getFriendsBornSameYear(Person person, int year) throws FriendNotFoundException {
        List<Person> friends = new ArrayList<>();

        Calendar currentBirthDayFriend = Calendar.getInstance();
        if(person != null && person.getFriends() != null){
            for (Person friend: person.getFriends()) {
                if(friend != null){
                    currentBirthDayFriend.setTime(friend.getBirthday());
                    if(currentBirthDayFriend.get(Calendar.YEAR) == year){
                        friends.add(friend);
                    }
                }
            }
        }
        if(friends.size() == 0){
            throw new FriendNotFoundException();
        }
        return friends;
    }
}
