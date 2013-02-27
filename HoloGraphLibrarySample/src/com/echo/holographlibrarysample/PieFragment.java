package com.echo.holographlibrarysample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

public class PieFragment extends SherlockFragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_piegraph, container, false);
		PieGraph pg = (PieGraph)v.findViewById(R.id.piegraph);
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor("#99CC00"));
		slice.setValue(2);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#FFBB33"));
		slice.setValue(3);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#AA66CC"));
		slice.setValue(8);
		pg.addSlice(slice);		
		
		return v;
	}
}
