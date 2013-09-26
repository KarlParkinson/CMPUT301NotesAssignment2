/**
 * 
 */
package com.example.kparkins_notes;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author  kparkins
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
	
	
	public String getTitle() {
		return title;
	}
	
	
	public String getBody() {
		return body;
	}
	
	
	public Calendar getDate() {
		return date;
	}
	
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	
	public void setBody(String newBody) {
		body = newBody;
	}
	
	
	public void setDate(Calendar newDate) {
		date = newDate;
	}
	
	
	public int getCharacterCount() {
		return characterCount;
	}
	
	
	public int getWordCount() {
		return wordCount;
	}
	
	public void setCharacterCount(int numChars) {
		characterCount = numChars;
	}
	
	public void setWordCount(int numWords) {
		wordCount = numWords;
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
			throws IOException {
		out.writeObject(date);
		out.writeObject(body);
		out.writeObject(title);
		out.writeObject(characterCount);
		out.writeObject(wordCount);
	}
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