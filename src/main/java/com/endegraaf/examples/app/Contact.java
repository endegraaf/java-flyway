/**
 * Copyright 2012-2014 Java Creed.
 * 
 * Licensed under the Apache License, Version 2.0 (the "<em>License</em>");
 * you may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at: 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 */
package com.endegraaf.examples.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Contact {

	private long id = -1;
	private String name;
	private String contacts;

	public String getContacts() {
		return contacts;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void delete() throws SQLException{
		
		if (id != -1) {
			final String sql = "DELETE FROM contacts WHERE id = ?";
			try (Connection connection = DbHelper.getConnection();
					PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setLong(1, id);
				pstmt.execute();
				id = -1;
			}
		}
	}
	
	
	public void save() throws SQLException {

		try (Connection connection = DbHelper.getConnection();) {
			if (id == -1) {
				// Insert
				final String sql = "INSERT INTO contacts(name, contacts) VALUES(?, ?)";
				try (PreparedStatement pstmt = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
					pstmt.setString(1, name);
					pstmt.setString(2, contacts);
					pstmt.execute();

					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						rs.next();
						id = rs.getLong(1);
					}
				}
			} else {
				final String sql = "UPDATE contacts SET name = ?, contacts = ? WHERE id = ?";
				try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
					pstmt.setString(1, name);
					pstmt.setString(2, contacts);
					pstmt.setLong(3, id);
					pstmt.execute();
				}
			}
		}
	}

	public void setContacts(final String contacts) {
		this.contacts = contacts;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
