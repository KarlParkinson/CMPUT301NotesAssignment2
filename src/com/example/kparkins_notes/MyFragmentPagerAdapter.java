package com.example.kparkins_notes;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

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
 * This is myn extension of the FragmentPagerAdapter class. It has a list of two fragments, a NotesFragment and a StatsFragment, and
 * displays the appropriate fragment when an action bar tab is clicked or the user swipes.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	private static final int numFragments = 2;
	private List<Object> fragmentList = new ArrayList<Object>();

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		// Add fragments to list
		fragmentList.add(new NotesFragment());
		fragmentList.add(new StatsFragment());

	}

	@Override
	public Fragment getItem(int i) {
		if (i == 0) {
			return (ListFragment) fragmentList.get(i); // Cast to ListFragment
		} else {
			return (Fragment) fragmentList.get(i); // Cast to Fragment
		}
	}

	@Override
	public int getCount() {
		return numFragments;
	}

}