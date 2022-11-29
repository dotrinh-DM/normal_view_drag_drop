package com.dotrinh.view_drag_drop;

import static com.dotrinh.view_drag_drop.LogUtil.LogI;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
        // findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());

        findViewById(R.id.myimage1).setOnLongClickListener(this);
        findViewById(R.id.myimage2).setOnLongClickListener(this);
        findViewById(R.id.myimage3).setOnLongClickListener(this);
        findViewById(R.id.myimage4).setOnLongClickListener(this);


        findViewById(R.id.topleft).setOnDragListener(new MyDragListener());     //set target
        findViewById(R.id.topright).setOnDragListener(new MyDragListener());    //set target
        findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());  //set target
        findViewById(R.id.bottomright).setOnDragListener(new MyDragListener()); //set target

    }

    @Override
    public boolean onLongClick(View myView) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(myView);
        myView.startDrag(data, shadowBuilder, myView, 0); //allowing drag this view
        myView.setVisibility(View.INVISIBLE);
        return true;
    }

   /* private final class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View myView, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(myView);
                myView.startDrag(data, shadowBuilder, myView, 0);
                myView.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }*/

    class MyDragListener implements View.OnDragListener {

        Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View myView, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED: {//1
                    // do nothing
                    break;
                }
                case DragEvent.ACTION_DRAG_LOCATION: {//2
                    LogI("ACTION_DRAG_LOCATION di chuyen trong khu vuc view lang nghe " + myView.getId());
                    break;
                }
                case DragEvent.ACTION_DROP: {//3
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) myView;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                }
                case DragEvent.ACTION_DRAG_ENDED: {//4
                    myView.setBackground(normalShape);
                    LogI("ACTION_DRAG_ENDED ket thuc thanh cong hoac ko thanh cong  " + myView.getId());
                    break;
                }
                case DragEvent.ACTION_DRAG_ENTERED: {//5
                    myView.setBackground(enterShape);
                    LogI("ACTION_DRAG_ENTERED " + myView.getId());
                    break;
                }
                case DragEvent.ACTION_DRAG_EXITED: {//6
                    myView.setBackground(normalShape);
                    LogI("ACTION_DRAG_EXITED " + myView.getId());
                    break;
                }
            }
            return true;
        }
    }
}