package com.echo.holographlibrary;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PieGraph extends SurfaceView implements SurfaceHolder.Callback {

	private ArrayList<PieSlice> slices = new ArrayList<PieSlice>();
	private int indexSelected = -1;
	private int thickness = 50;
	private OnSliceClickedListener listener;
	
	
	public PieGraph(Context context) {
		super(context);
		this.setZOrderOnTop(true); //necessary                
	    getHolder().setFormat(PixelFormat.TRANSPARENT); 
	    getHolder().addCallback(this); 
	}
	public PieGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setZOrderOnTop(true); //necessary                
	    getHolder().setFormat(PixelFormat.TRANSPARENT); 
	    getHolder().addCallback(this); 
	}
	
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		float midX, midY, radius, innerRadius;
		Path p = new Path();
		
		float currentAngle = 270;
		float currentSweep = 0;
		int totalValue = 0;
		float padding = 2;
		
		midX = getWidth()/2;
		midY = getHeight()/2;
		if (midX < midY){
			radius = midX;
		} else {
			radius = midY;
		}
		radius -= padding;
		innerRadius = radius - thickness;
		
		for (PieSlice slice : slices){
			totalValue += slice.getValue();
		}
		
		int count = 0;
		for (PieSlice slice : slices){
			p = new Path();
			paint.setColor(slice.getColor());
			currentSweep = (slice.getValue()/totalValue)*(360);
			p.arcTo(new RectF(midX-radius, midY-radius, midX+radius, midY+radius), currentAngle+padding, currentSweep - padding);
			p.arcTo(new RectF(midX-innerRadius, midY-innerRadius, midX+innerRadius, midY+innerRadius), (currentAngle+padding) + (currentSweep - padding), -(currentSweep-padding));
			p.close();
			
			slice.setPath(p);
			slice.setRegion(new Region((int)(midX-radius), (int)(midY-radius), (int)(midX+radius), (int)(midY+radius)));
			canvas.drawPath(p, paint);
			
			if (indexSelected == count && listener != null){
				Path p2 = new Path();
				paint.setColor(slice.getColor());
				paint.setColor(Color.parseColor("#33B5E5"));
				paint.setAlpha(100);
				p2.arcTo(new RectF(midX-radius-(padding*2), midY-radius-(padding*2), midX+radius+(padding*2), midY+radius+(padding*2)), currentAngle, currentSweep+padding);
				p2.arcTo(new RectF(midX-innerRadius+(padding*2), midY-innerRadius+(padding*2), midX+innerRadius-(padding*2), midY+innerRadius-(padding*2)), currentAngle + currentSweep + padding, -(currentSweep + padding));
				p2.close();
				canvas.drawPath(p2, paint);
				paint.setAlpha(255);
			}
			
			currentAngle = currentAngle+currentSweep;
			
			count++;
		}
		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

	    Point point = new Point();
	    point.x = (int) event.getX();
	    point.y = (int) event.getY();
	    
	    int count = 0;
	    for (PieSlice slice : slices){
	    	Region r = new Region();
	    	r.setPath(slice.getPath(), slice.getRegion());
	    	if (r.contains((int)point.x,(int) point.y) && event.getAction() == MotionEvent.ACTION_DOWN){
	    		indexSelected = count;
	    	} else if (event.getAction() == MotionEvent.ACTION_UP){
	    		if (r.contains((int)point.x,(int) point.y) && listener != null){
	    			listener.onClick(indexSelected);
	    		}
	    		indexSelected = -1;
	    	}
		    count++;
	    }
	    
	    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP){
	    	postInvalidate();
	    }
	    
	    

	    return true;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		setWillNotDraw(false); 					//Allows us to use invalidate() to call onDraw()
		postInvalidate();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}
	
	public ArrayList<PieSlice> getSlices() {
		return slices;
	}
	public void setSlices(ArrayList<PieSlice> slices) {
		this.slices = slices;
	}
	public PieSlice getSlice(int index) {
		return slices.get(index);
	}
	public void addSlice(PieSlice slice) {
		this.slices.add(slice);
	}
	public void setOnSliceClickedListener(OnSliceClickedListener listener) {
		this.listener = listener;
	}
	
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public abstract class OnSliceClickedListener {
		abstract void onClick(int index);
	}

}
