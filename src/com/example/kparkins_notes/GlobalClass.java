package com.example.kparkins_notes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import android.app.Application;
import android.util.Log;


public class GlobalClass extends Application {

	
	//private ArrayList<Note> notesList = new ArrayList<Note>(); // Array of log entries.
	//private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	
	private ArrayList<Note> notesList;
	private HashMap<String, Integer> wordMap;
	
	private static final String FILENAME = "file.sav";

	/*
	 * (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 *
	 * Will want to eventually recreate application data from persistent storage in an SQLite DB every time this is called.
	 * For now, just assume this class will never be destroyed by the OS (not true).
	 */

	public void onCreate() {
		super.onCreate();
		notesList = readFromFile();
		wordMap = new HashMap<String, Integer>();
	}

	// From LonelyTwitter
	private ArrayList<Note> readFromFile() {
		
		ArrayList<Note> notes = new ArrayList<Note>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (true) {
				Note n = (Note) ois.readObject();
				notes.add(n);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}

	// Add a new entry to the list of notes.
	public void appendNotesList(Note note) {
		notesList.add(note);
		sortNotes();
	}


	// Get the ith entry in the log.
	public Note retrieveLogEntry(int i) {
		return notesList.get(i);
	}


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


	public void deleteEntry(int position) {
		notesList.remove(position);
		wordMap.clear();
		wordMap = getWordsMap();
		Log.d("numNotes: ", String.valueOf(notesList.size()));
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
		String s = " ";
		for (Note n : notesList) {
			s += n.getBody();
			s += " ";
		}
		
		return s;
	}
	
	public HashMap<String, Integer> getWordsMap() {
		String words = aggregateNotes();
		String[] wordsArray = words.split("\\s");
		Integer count;
		
		for (int i = 0; i < wordsArray.length; i++) {
			wordsArray[i] = wordsArray[i].replaceAll("[^\\w]", "");
			count = wordMap.get(wordsArray[i]);
			if (count == null) {
				wordMap.put(wordsArray[i], 1);
			} else {
				wordMap.put(wordsArray[i], count+1);
			}
		}
		
		return wordMap;
		
	}
	
	
	public HashMap<String, Integer> getSortedWordsMap() {
		String words = aggregateNotes();
		String[] wordsArray = words.split("\\s");
		Integer count;
		
		for (int i = 0; i < wordsArray.length; i++) {
			wordsArray[i] = wordsArray[i].replaceAll("[^\\w]", "");
			count = wordMap.get(wordsArray[i]);
			if (count == null) {
				wordMap.put(wordsArray[i], 1);
			} else {
				wordMap.put(wordsArray[i], count+1);
			}
		}
		
		Map<String, Integer> sortedMap = sortByValue(wordMap);
		return (HashMap<String, Integer>) sortedMap;
		
	}

	
	//http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java?lq=1
	Map<String, Integer> sortByValue(Map<String, Integer> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry)it.next();
			result.put((String) entry.getKey(), (Integer) entry.getValue());
		}
		return result;
	}

	// From LonelyTwitter
	public void saveNotes() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Note n : notesList) {
				oos.writeObject(n);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}

