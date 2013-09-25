package com.example.kparkins_notes;

import java.util.Comparator;

public class NoteComparator implements Comparator<Note> {

	@Override
	public int compare(Note n1, Note n2) {
		// TODO Auto-generated method stub
		return n1.getDate().compareTo(n2.getDate());
	}

}
