package org.vituchon.invisiblefriend.model;

public class WebPerson extends Person {
	public final String email;
	
    public WebPerson(Number id, String name, String email) {
        super(id,name);
        this.email = email;
    }	
    
    @Override
    public String toString() {
    	return ""+super.id;
    }
}