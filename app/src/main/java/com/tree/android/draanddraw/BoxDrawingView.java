package com.tree.android.draanddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : tree_root
 *     e-mail : xxx@xx
 *     time   : 2018/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BoxDrawingView extends View{

    private static final String SAVE_TEMP_DATA = "save_temp";
    private static final String SAVE_TEMP_PARENT_STATE = "save_temp_parent_state";

    private BoxPaint mBoxPaint;
    private List<BoxPaint> mBoxPaints = new ArrayList<BoxPaint>();
    private Paint mPaint;
    private Paint mBackgroundPaint;

    //Used for java code
    public BoxDrawingView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    //Used for xml
    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF curPoint = new PointF(event.getX(), event.getY());
        String action = null;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action = "action_down";
                mBoxPaint = new BoxPaint(curPoint);
                mBoxPaints.add(mBoxPaint);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "action_move";
                if(mBoxPaint != null){
                    mBoxPaint.setCru(curPoint);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "action_up";
                mBoxPaint = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "action_cancel";
                mBoxPaint = null;
                break;
        }
        Log.i("MotionEvent", "onTouchEvent: "+ action.toString() + curPoint.x + " / " + curPoint.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawPaint(mBackgroundPaint);

        Log.i("onDraw", "onDraw: " + mBoxPaints.size());
        for ( BoxPaint mBoxPaint : mBoxPaints) {
            float left = Math.min(mBoxPaint.getCru().x, mBoxPaint.getOrigin().x);
            float top = Math.min(mBoxPaint.getCru().y, mBoxPaint.getOrigin().y);
            float right = Math.max(mBoxPaint.getCru().x, mBoxPaint.getOrigin().x);
            float bottom = Math.max(mBoxPaint.getCru().y, mBoxPaint.getOrigin().y);

            Log.i("onDraw", "onDraw: " + left + " / " + top + " / " + right + " / " + bottom + " / ");
            canvas.drawRect(left, top, right, bottom, mPaint);
        }

    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SAVE_TEMP_DATA, (Serializable) mBoxPaints);
        bundle.putParcelable(SAVE_TEMP_PARENT_STATE, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        mBoxPaints = (List<BoxPaint>) bundle.getSerializable(SAVE_TEMP_DATA);
        super.onRestoreInstanceState(bundle.getParcelable(SAVE_TEMP_PARENT_STATE));
    }
}
