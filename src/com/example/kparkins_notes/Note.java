/**
 * 
 */
package com.example.kparkins_notes;

import java.util.Calendar;

/**
 * @author kparkins
 *
 */
public class Note {
	
	private String title;
	private String body;
	private Calendar date;
	private int characterCount;
	private int wordCount;
	
	public Note (String noteTitle, String noteBody, Calendar noteDate, int numChars, int numWords) {
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

}