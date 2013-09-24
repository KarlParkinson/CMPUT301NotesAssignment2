package com.example.kparkins_notes;

import java.util.ArrayList;
import android.app.Application;
import android.util.Log;

public class GlobalClass extends Application {

	private int numEntries = 0; // Number of entries in the log.
	private int numChars = 0;
	private int numWords = 0;
	private ArrayList<Note> notesList = new ArrayList<Note>(); // Array of log entries.

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
		notesList.add(0, note);
		numChars += note.getCharacterCount();
		numWords += note.getWordCount();
	}

	// Get the ith entry in the log.
	public Note retrieveLogEntry(int i) {
		return notesList.get(i);
	}

	// One entry added.
	public void incrementNumEntries() {
		numEntries++;
	}

	// One entry deleted.
	public void decrementNumEntries() {
		numEntries--;
	}

	// Return the number of entries.
	public int getNumEntries() {
		return numEntries;
	}

	// Replace the entry at position with the updated entry.
	public void editEntry(int position, String Body, String Title, int chars, int words) {
		notesList.get(position).setBody(Body);
		notesList.get(position).setTitle(Title);
		notesList.get(position).setCharacterCount(chars);
		notesList.get(position).setWordCount(words);

	}

	public void deleteEntry(int position) {
		Note note = notesList.get(position);
		numChars -= note.getCharacterCount();
		numWords -= note.getWordCount();
		notesList.remove(position);
	}

	public ArrayList<Note> getNotes() {
		return notesList;
	}

	public int getNumChars() {
		return numChars;
	}

	public int getNumWords() {
		return numWords;
	}


}

