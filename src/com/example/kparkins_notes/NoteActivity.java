package com.example.kparkins_notes;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;

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
 * The NoteActivity is responsible for editing and deleting notes. It first gets the selected note
 * from the GlobalClass, and then accepts any input from the user. If the user wishes to save the changes
 * then NoteActivity tells GlobalClass to update the Note with the new input. If the user wishes to delete
 * the note, then NoteActivity tells GlobalClass to delete the appropriate note.
 */


public class NoteActivity extends Activity implements 
DatePicker.OnDateChangedListener{

	private EditText titleEntryET;
	private EditText logEntryET;
	private TextView charCountTextView;
	
	private DatePicker datePicker;
	
	private int charCount;
	private int wordCount;
	private int notePosition; // Position of note in list of notes
	

	private GlobalClass state;
	private Note note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		// Show the Up button in the action bar.
		setupActionBar();
		
		state = (GlobalClass) getApplicationContext();
		notePosition = getIntent().getIntExtra("Position", 0); // The position of the note selected by the user.
		note = state.retrieveLogEntry(notePosition); // The note selected by the user
		
		logEntryET = (EditText) findViewById(R.id.EditLogEntryET);
		titleEntryET = (EditText) findViewById(R.id.editTitleET);
		charCountTextView = (TextView) findViewById(R.id.charCountTextView2);
		setUpTextWatcher();
		
		datePicker = (DatePicker) findViewById(R.id.editDatePicker);
		datePicker.setCalendarViewShown(false);
		// Set the datePicker to display the date of the note
		datePicker.updateDate(note.getDate().get(Calendar.YEAR), note.getDate().get(Calendar.MONTH), note.getDate().get(Calendar.DAY_OF_MONTH));
		
		titleEntryET.setText(note.getTitle());
		logEntryET.setText(note.getBody());
		


	}
	private void setUpTextWatcher() {
		
		logEntryET.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				charCountTextView.setText(String.valueOf(s.length()));
				charCount = s.length(); // Update character count
				
			}
			
		});
		
	}

	private void saveAndExit() {
		
		String body = logEntryET.getText().toString();
		wordCount = countWords(body);
		onDateChanged(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		state.editEntry(notePosition, body, titleEntryET.getText().toString(), charCount, wordCount); // Tell GlobalClass to update the note with the new values
		finish();
		
	}
	
	private void deleteAndExit() {
	
		state.deleteEntry(notePosition); // Tell GlobalClass to delete the note
		finish();
		
	}

	// taken from: http://stackoverflow.com/questions/5864159/count-words-in-a-string-method
	private int countWords(String body) {
		String trim = body.trim();
		if (trim.isEmpty()) return 0;
		return trim.split("\\s+").length; //separate string around spaces
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

	// see: http://developer.android.com/guide/topics/ui/actionbar.html#Home
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case (R.id.save_new_edit):
			saveAndExit();
		return true;
		
		case (R.id.delete_note):
			deleteAndExit();
		return true;
		}
		return super.onOptionsItemSelected(item);
		
	}
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
		note.getDate().set(year, monthOfYear, dayOfMonth);
		
	}



}