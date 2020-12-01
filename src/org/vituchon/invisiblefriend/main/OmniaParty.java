package org.vituchon.invisiblefriend.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.vituchon.invisiblefriend.model.Friend;
import org.vituchon.invisiblefriend.model.Party;
import org.vituchon.invisiblefriend.model.Person;
import org.vituchon.invisiblefriend.model.WebPerson;
import org.vituchon.invisiblefriend.util.EMailClient;
import org.vituchon.invisiblefriend.util.YahooEMailClient;

public class OmniaParty {

	public static final String[][] data = {
			{"Cat 1","cat_1@party.com"}, 
			{"Cat 2","cat_2@party.com"}, 
	};

	public static final WebPerson[] PERSONS;
	static {
		PERSONS = new WebPerson[data.length];
		for (int i = 1; i <= data.length; i++) {
			WebPerson webPerson = new WebPerson(i, data[i - 1][0], data[i - 1][1]);
			PERSONS[i - 1] = webPerson;
		}
	}

	public static Collection<String> toLines(Party party) {
		Collection<String> strings = new ArrayList<String>(party.friends.size());
		for (Friend friend : party.friends) {
			StringBuilder buffer = new StringBuilder();
			buffer.append(friend.who.name).append(" -> ").append(friend.friendship.to.name);
			strings.add(buffer.toString());
		}
		return strings;
	}

	public static void arrangeOmniaParty(EMailClient emailClient) {
		Collection<WebPerson> persons = Arrays.asList(PERSONS);
		Party party = new Party(persons);

		saveIntoFile(String.join(System.lineSeparator(), toLines(party)));
		for (WebPerson giver : persons) {
			Person reciever = party.getReciever(giver);
			String content = "content";
			emailClient.send(giver.email, "Invisible friendO", content);
		}
	}

	private static void saveIntoFile(String content) {
		System.out.println("Guardando archivo de relaciones...");
		try (PrintWriter out = new PrintWriter("party.txt", "UTF-8")) {
			out.write(content);
			System.out.println("Archivo de relaciones guardado OK!");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Error al guardar en archivo de relaciones: " + e);
			System.out.println("Contenido es " + content);
		}
	}

	public static void main(String[] args) {
		EMailClient emailClient = new YahooEMailClient();
		arrangeOmniaParty(emailClient);
	}

}
