
package com.example.kparkins_notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * This is a custom implementation of an Adapter to interact with the ListView in the NotesFragment. It accepts a list of notes
 * and then displays the note with the note's title first, followed by the notes's date, followed by the note's body.
 */

// see: http://www.vogella.com/articles/AndroidListView/article.html


public class MyAdapter extends BaseAdapter {
	
	private ArrayList<Note> notes;
	private final Context context;
	
	public MyAdapter(ArrayList<Note> notesList, Context ctx) {
		
		notes = notesList; // List of notes/
		context = ctx;
		
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notes.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View row = inflater.inflate(R.layout.list_adapter_layout, parent, false); // Layout of each row in ListView
		TextView titleTextView = (TextView) row.findViewById(R.id.titleTextView); // Where title is displayed/
		TextView bodyTextView = (TextView) row.findViewById(R.id.bodyTextView);	// Where body is displayed
		TextView dateTextView = (TextView) row.findViewById(R.id.dateTextView); // Where date is displayed
		
		titleTextView.setText(notes.get(position).getTitle()); // Display the title
		bodyTextView.setText(notes.get(position).getBody()); // Display the body
		String date = null;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); // Proper date format
		date = format.format(notes.get(position).getDate().getTime());
		dateTextView.setText(date); // Display the date.
		return row;
	}
	

}
