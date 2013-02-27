package com.echo.holographlibrarysample;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

public class LineFragment extends SherlockFragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_linegraph, container, false);
		Line l = new Line();
		LinePoint p = new LinePoint();
		p.setX(0);
		p.setY(5);
		l.addPoint(p);
		p = new LinePoint();
		p.setX(8);
		p.setY(8);
		l.addPoint(p);
		p = new LinePoint();
		p.setX(10);
		p.setY(4);
		l.addPoint(p);
		l.setColor(Color.parseColor("#FFBB33"));
		
		LineGraph li = (LineGraph)v.findViewById(R.id.linegraph);
		li.addLine(l);
		li.setRangeY(0, 10);
		li.setLineToFill(0);
		
		
		return v;
	}
}
