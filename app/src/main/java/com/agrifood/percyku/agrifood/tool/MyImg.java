package com.agrifood.percyku.agrifood.tool;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

/**
 * Created by percyku on 16/11/4.
 */
public class MyImg extends ImageView {

    public MyImg(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        if (isPressed())
            canvas.drawColor(0x33000000);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        // TODO Auto-generated method stub
        super.dispatchSetPressed(pressed);
        invalidate();
    }
}
