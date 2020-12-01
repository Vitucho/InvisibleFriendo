package org.vituchon.invisiblefriend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Party {

    public final Collection<Relationship> friendships;
    public final Collection<Friend> friends;

    public Party(Collection<? extends Person> persons) {
       this.friendships = buildFriendship(persons);
       this.friends = toFriends(this.friendships);
    }
    
    private Collection<Relationship> buildFriendship(Collection<? extends Person> persons) {
        Collection<Relationship> _friendships = buildFriendships(persons);
        return _friendships;
    }
    
    private Collection<Friend> toFriends(Collection<Relationship> relationships) {
        Collection<Friend> _friends = new LinkedList<Friend>();
        
        for (Relationship relationship : relationships) {
            Person person = relationship.from;
            Person givesTo = relationship.to;
            Person receivesFrom = findGiver(person,friendships);
            Friend friend = new Friend(person, new Relationship(receivesFrom, givesTo));
            _friends.add(friend);
        }
        return _friends;
    }

    private Collection<Relationship> buildFriendships(Collection<? extends Person> persons) {
        Collection<Relationship> relationships = new LinkedList<Relationship>();
        ArrayList<Person> peopleLeft = new ArrayList<Person>(persons);
        for (Person person : persons) {
            Person giver = person;
            Person receiver = findReciever(giver, peopleLeft);
            peopleLeft.remove(receiver);
            
            Relationship relationship = new Relationship(giver, receiver);
            relationships.add(relationship);
        }
        return relationships;
    }
    
    private Person findReciever(Person giver, ArrayList<Person> peopleLeft) {
    	ArrayList<Person> copy = new ArrayList<Person>(peopleLeft);
        copy.remove(giver);

        if (copy.isEmpty()) {
            throw new RuntimeException("Programing Error that may happen is there is auto encirclement within... so well... error!");
        }
        Random r = new Random();
        int index = r.nextInt(copy.size());
        Person receiver = copy.get(index);
        return receiver;
    }

    private Person findGiver(Person receiver, Collection<Relationship> friendships) {
        for (Relationship friendship : friendships) {
            if (friendship.to == receiver) {
             return friendship.from;
           }
        }
        System.out.println(friendships);
        throw new RuntimeException("Programing Logic error!");
    }
    
    public Person getReciever(Person receiver) {
        for (Relationship friendship : friendships) {
            if (friendship.from == receiver) {
             return friendship.to;
           }
        }
        throw new RuntimeException("Programing Logic error!");
    }

}
