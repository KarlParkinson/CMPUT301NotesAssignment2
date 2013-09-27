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
 * The GlobalClass is responsible for storing all the data for the application, as well as writing and reading to a file.
 */
public class GlobalClass extends Application {

	
	
	private ArrayList<Note> notesList; // List of notes.
	private HashMap<String, Integer> wordMap; // Map of words to how often they appear.
	
	private static final String FILENAME = "file.sav";

	/*
	 * (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 *
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


	// Delete the entry at position and reset the word map
	public void deleteEntry(int position) {
		notesList.remove(position);
		wordMap.clear();
		wordMap = getWordsMap();
	}


	// Return the list of notes
	public ArrayList<Note> getNotes() {
		return notesList;
	}

	// Return how many characters in all notes
	public int getNumChars() {
		int chars = 0;
		for (Note n: notesList) {
			chars += n.getCharacterCount();
		}
		return chars;
			
	}

	// Return how many words in all notes
	public int getNumWords() {
		int words = 0;
		for (Note n: notesList) {
			words += n.getWordCount();
		}
		return words;
			
	}
	
	// Sort notes by date.
	private void sortNotes() {
		Collections.sort(notesList, new NoteComparator());
		Collections.reverse(notesList);
	}
	
	// Create a string of all the note bodies
	public String aggregateNotes() {
		String s = " ";
		for (Note n : notesList) {
			s += n.getBody();
			s += " ";
		}
		
		return s;
	}
	
	
	// Create the HashMap of words to their word count.
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
	
	
	// Sort the HashMap by value.
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

