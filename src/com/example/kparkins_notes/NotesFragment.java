package com.example.kparkins_notes;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
 * The NotesFragment is responsible for displaying all the stored notes to the user in a ListView.
 * When first created, the list of notes is fetched from the GlobalClass and then passed to an instance
 * of MyAdapter. When onResume is called, it is assumed that a note has either been added, deleted, or edited, 
 * so the adapter is notified of a change in the data. The NoteFragment also spawns a NewLogActivity when the 
 * user requests to add a new note, and spawns a NoteActivity when the user selects to edit or delete a note.
 */

public class NotesFragment extends ListFragment {

	
	private MyAdapter adapter;
	private GlobalClass state;
	private ArrayList<Note> notes;


	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); // Fragment has menu items to contribute
		state = ((GlobalClass) getActivity().getApplicationContext());
		notes = state.getNotes();
		adapter = new MyAdapter(notes, getActivity());
		setListAdapter(adapter); // Display list of notes
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//Inflate the layout for this fragment
		return inflater.inflate(R.layout.notes_layout, container, false);
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.notes_menu, menu);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (R.id.add_new_note):
			launchNewLogActivity(); // User wants to create a new note
		return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void launchNewLogActivity() {
		Intent intent = new Intent(getActivity(), NewLogActivity.class);
		startActivity(intent);
	}

	public void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged(); // Display updated notes list
	}


	// Method to be called when an item in the list is selected. Pass the position of the object and the data.
	public void onListItemClick (ListView l, View v, int position, long id) {
		Intent intent = new Intent(getActivity(), NoteActivity.class);
		intent.putExtra("Position", position);
		startActivity(intent);
	}



}