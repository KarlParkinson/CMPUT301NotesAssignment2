/**
 * 
 */
package com.example.kparkins_notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author kparkins
 *
 */
public class MyAdapter extends BaseAdapter {
	
	private ArrayList<Note> notes;
	private final Context context;
	
	public MyAdapter(ArrayList<Note> notesList, Context ctx) {
		
		notes = notesList;
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
		View row = inflater.inflate(R.layout.list_adapter_layout, parent, false);
		TextView titleTextView = (TextView) row.findViewById(R.id.titleTextView);
		TextView bodyTextView = (TextView) row.findViewById(R.id.bodyTextView);
		TextView dateTextView = (TextView) row.findViewById(R.id.dateTextView);
		
		titleTextView.setText(notes.get(position).getTitle());
		bodyTextView.setText(notes.get(position).getBody());
		String date = null;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		date = format.format(notes.get(position).getDate().getTime());
		dateTextView.setText(date);
		return row;
	}
	
	public void printToLog() {
		Log.d("Size of notes list:", String.valueOf(notes.size()));
	}

}
