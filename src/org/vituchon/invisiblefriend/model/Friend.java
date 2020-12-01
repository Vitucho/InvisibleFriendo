package org.vituchon.invisiblefriend.model;

public class Friend {
    public final Person who;
    public final Relationship friendship;

    public Friend(Person who, Relationship friendship) {
        this.who = who;
        this.friendship = friendship;
    }
}
