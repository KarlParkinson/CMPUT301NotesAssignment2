package com.example.kparkins_notes;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


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
 * The NewLogActivity is responsible for creating new notes from user input. It receives a notes title, body, and date.
 * It then creates a new note and passes it to the GlobalClass for storage.
 */


public class NewLogActivity extends Activity implements 
DatePicker.OnDateChangedListener{

	private EditText noteEntryET;
	private EditText titleEntryET;
	private TextView charCountTextView;
	private DatePicker datePicker;

	private Calendar calendar;
	
	private int charCount;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_log);
		// Show the Up button in the action bar.
		setupActionBar();

	}
	
	protected void onResume() {
		super.onResume();
		
		noteEntryET = (EditText) findViewById(R.id.enterNoteET);
		titleEntryET = (EditText) findViewById(R.id.enterTitleET);
		charCountTextView = (TextView) findViewById(R.id.charCountTextView); // Dynamic character counter
		setUpTextWatcher();
		charCount = noteEntryET.getText().toString().length();
		datePicker = (DatePicker) findViewById(R.id.setDatePicker);
		datePicker.setCalendarViewShown(false);
		calendar = Calendar.getInstance();

	}

	private void setUpTextWatcher() {
		
		noteEntryET.addTextChangedListener(new TextWatcher() {

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
				charCountTextView.setText(String.valueOf(s.length())); // Update the character counter
				charCount = s.length();
				
			}
			
		});
		
	}

	private void saveAndExit() {
		final GlobalClass state = (GlobalClass) getApplicationContext();
		String body = noteEntryET.getText().toString();
		onDateChanged(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()); // Update the date
		int wordCount = countWords(body);
		Note newNote = new Note(titleEntryET.getText().toString(), body, calendar, charCount, wordCount); // Create ne Note object
		state.appendNotesList(newNote); // Pass note to GlobalClass to store.
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
		getMenuInflater().inflate(R.menu.new_log, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (android.R.id.home):
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
		return true;
		case (R.id.save_new_log):
			saveAndExit();
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onDateChanged(DatePicker view, int chosenYear, int monthOfYear,
			int dayOfMonth) {


		calendar.set(chosenYear, monthOfYear, dayOfMonth); // Set date to what is entered in date picker
		

	}

}
