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

public class NotesFragment extends ListFragment {

	private MyAdapter adapter;
	private GlobalClass state;
	private ArrayList<Note> notes;


	/*
	 * Need to configure when to access global class (onCreate()? onResume()?) and pull data from it. Then need to
	 * populate list view using this data.
	 */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); // Fragment has menu items to contribute
		state = ((GlobalClass) getActivity().getApplicationContext());
		notes = state.getNotes();
		adapter = new MyAdapter(notes, getActivity());
		setListAdapter(adapter);
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
			launchNewLogActivity();
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
		adapter.notifyDataSetChanged();
	}


	// Method to be called when an item in the list is selected. Pass the position of the object and the data.
	public void onListItemClick (ListView l, View v, int position, long id) {
		Intent intent = new Intent(getActivity(), NoteActivity.class);
		intent.putExtra("Position", position);
		startActivity(intent);
	}





}