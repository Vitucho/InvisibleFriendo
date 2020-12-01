package org.vituchon.invisiblefriend.util;

public interface EMailClient {
	void send(String to, String subject, String content);
}
