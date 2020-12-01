package org.vituchon.invisiblefriend.model;

public class Relationship {
    /** From who */
    public final Person from;
    /** To who. */
    public final Person to;
    
    public Relationship(Person from,Person to) {
        this.from = from;
        this.to = to;
    }
    
    @Override
    public String toString() {
    	return "[from:" + this.from.toString() + ",to:" + this.to.toString() + "]";
    }
    
}
