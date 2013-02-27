package com.echo.holographlibrarysample;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

public class BarFragment extends SherlockFragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_bargraph, container, false);
		ArrayList<Bar> points = new ArrayList<Bar>();
		Bar d = new Bar();
		d.setColor(Color.parseColor("#99CC00"));
		d.setName("Test1");
		d.setValue(10);
		Bar d2 = new Bar();
		d2.setColor(Color.parseColor("#FFBB33"));
		d2.setName("Test2");
		d2.setValue(20);
		points.add(d);
		points.add(d2);
		
		BarGraph g = (BarGraph)v.findViewById(R.id.bargraph);
		g.setBars(points);
		
		return v;
	}
}
