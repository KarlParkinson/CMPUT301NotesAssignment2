package com.example.kparkins_notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Application;


public class GlobalClass extends Application {

	
	private ArrayList<Note> notesList = new ArrayList<Note>(); // Array of log entries.
	private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

	/*
	 * (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 *
	 * Will want to eventually recreate application data from persistent storage in an SQLite DB every time this is called.
	 * For now, just assume this class will never be destroyed by the OS (not true).
	 */

	public void onCreate() {
		super.onCreate();
	}

	// Add a new entry to the list of notes.
	public void appendNotesList(Note note) {
		notesList.add(note);
		sortNotes();
	}

	private void updateWordMap(Note note) {
		String[] words = parseWords(note.getBody());
		Integer count;
		for (String s : words) {
			count = wordMap.get(s);
			if (count == null) {
				wordMap.put(s, 1);
			} else {
				wordMap.put(s, count+1);
			}
		}
		
	}

	private String[] parseWords(String body) {
		String[] s = body.split("\\s");
		for (int i = 0; i<s.length; i++) {
			s[i] = s[i].replaceAll("[^\\w]", "");
		}
		return s;
	}


	// Get the ith entry in the log.
	public Note retrieveLogEntry(int i) {
		return notesList.get(i);
	}

	// One entry added.
	//public void incrementNumEntries() {
		//numEntries++;
	//}

	// One entry deleted.
	//public void decrementNumEntries() {
		//numEntries--;
	//}

	// Return the number of entries.
	public int getNumEntries() {
		return notesList.size();
	}

	// Replace the entry at position with the updated entry.
	public void editEntry(int position, String Body, String Title, int chars, int words) {
		notesList.get(position).setBody(Body);
		notesList.get(position).setTitle(Title);
		notesList.get(position).setCharacterCount(chars);
		notesList.get(position).setWordCount(words);
		sortNotes();

	}

	private String[] compareNotes(String oldBody, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteEntry(int position) {
		notesList.remove(position);
	}

	private void updateWordsMap2(Note note) {
		String[] words = parseWords(note.getBody());
		Integer count;
		for (String s : words) {
			count = wordMap.get(s);
			wordMap.put(s, count-1);
		}
		
	}

	public ArrayList<Note> getNotes() {
		return notesList;
	}

	public int getNumChars() {
		int chars = 0;
		for (Note n: notesList) {
			chars += n.getCharacterCount();
		}
		return chars;
			
	}

	public int getNumWords() {
		int words = 0;
		for (Note n: notesList) {
			words += n.getWordCount();
		}
		return words;
			
	}
	
	private void sortNotes() {
		Collections.sort(notesList, new NoteComparator());
		Collections.reverse(notesList);
	}
	
	public String aggregateNotes() {
		String s = "";
		for (Note n : notesList) {
			s += n.getBody();
			s += " ";
		}
		
		return s;
	}

}

