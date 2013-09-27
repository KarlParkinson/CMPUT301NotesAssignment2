
package com.example.kparkins_notes;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Calendar;

/*kparkins-notes. Stores and displays a list of notes along with some statistics.
Copyright (C) 2013 Karl Parkinson.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * A Note object consists of a title, body, date, character count, and word count
 */

public class Note implements Serializable {
	
	private String title;
	private String body;
	private Calendar date;
	private Integer characterCount;
	private Integer wordCount;
	
	public Note (String noteTitle, String noteBody, Calendar noteDate, Integer numChars, Integer numWords) {
		super();
		title = noteTitle;
		body = noteBody;
		date = noteDate;
		characterCount = numChars;
		wordCount = numWords;
	}
	
	// Get title of note.
	public String getTitle() {
		return title;
	}
	
	// Get body of note
	public String getBody() {
		return body;
	}
	
	// Get the date of the note
	public Calendar getDate() {
		return date;
	}
	
	// Set the title of note
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	// Set the body of note
	public void setBody(String newBody) {
		body = newBody;
	}
	
	// Set the date of note
	public void setDate(Calendar newDate) {
		date = newDate;
	}
	
	// Get the character count of the note
	public int getCharacterCount() {
		return characterCount;
	}
	
	// Get the word count of the note
	public int getWordCount() {
		return wordCount;
	}
	
	// Set the character count of the note
	public void setCharacterCount(int numChars) {
		characterCount = numChars;
	}
	
	// Set the word count of the note
	public void setWordCount(int numWords) {
		wordCount = numWords;
	}
	
	// Save the note to a file
	private void writeObject(java.io.ObjectOutputStream out)
			throws IOException {
		out.writeObject(date);
		out.writeObject(body);
		out.writeObject(title);
		out.writeObject(characterCount);
		out.writeObject(wordCount);
	}
	
	// Read a note from a file
	private void readObject(java.io.ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		date = (Calendar) in.readObject();
		body = (String) in.readObject();
		title = (String) in.readObject();
		characterCount = (Integer) in.readObject();
		wordCount = (Integer) in.readObject();
	}
	private void readObjectNoData() 
			throws ObjectStreamException {
	}

}