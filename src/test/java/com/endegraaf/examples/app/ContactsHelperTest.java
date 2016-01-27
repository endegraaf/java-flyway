package com.endegraaf.examples.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContactsHelperTest {

	@After
	public void close() {
		DbHelper.getInstance().close();
	}

	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();

		try (Connection connection = DbHelper.getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.execute("TRUNCATE TABLE contacts");
			// stmt.execute("ALTER TABLE contacts ALTER COLUMN id RESTART WITH 1"); // only needed for h2 driver. 
		}
	}

	@Test
	public void testLoad() throws SQLException {

		List<Contact> contacts = ContactsHelper.getInstance().getContacts();

		Assert.assertNotNull(contacts);
		Assert.assertTrue(contacts.isEmpty());

		try (Connection connection = DbHelper.getInstance().getConnection();
				Statement stmt = connection.createStatement()) {

			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Liam Bakker', 'Bakker');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Sem Beek', 'Beek');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Lucas Berg', 'Berg');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Luuk Boer', 'Boer');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Noah Bos', 'Bos');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Milan Bosch', 'Bosch');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Daan Brink', 'Brink');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Levi Broek', 'Broek');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Finn Brouwer', 'Brouwer');");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Jesse Bruin', 'Bruin');");

			contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertNotNull(contacts);
			Assert.assertEquals(10, contacts.size());

			Contact contact = contacts.get(0);
			Assert.assertNotNull(contact);
			Assert.assertEquals(1L, contact.getId());
			Assert.assertEquals("Liam Bakker", contact.getName());
			Assert.assertEquals("Bakker", contact.getContacts());

			contact = contacts.get(2);
			Assert.assertNotNull(contact);
			Assert.assertEquals(3L, contact.getId());
			Assert.assertEquals("Lucas Berg", contact.getName());
			Assert.assertEquals("Berg", contact.getContacts());

		}

	}

}
