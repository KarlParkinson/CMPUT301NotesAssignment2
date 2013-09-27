package com.example.kparkins_notes;

import java.util.Comparator;

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
 * A comparator used to sort the notes.
 */
public class NoteComparator implements Comparator<Note> {

	@Override
	public int compare(Note n1, Note n2) {
		// TODO Auto-generated method stub
		return n1.getDate().compareTo(n2.getDate());
	}

}
