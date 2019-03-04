package com.agrifood.percyku.agrifood;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.agrifood.percyku.agrifood.tool.ToolArray;

import java.util.Random;

public class InformationContext extends AppCompatActivity {

    private TextView txtName,txtTel,txtIntro;
    private ImageView imgPic;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_context_v2);

        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        imgPic=(ImageView) this.findViewById(R.id.imgPic);
        txtName=(TextView)this.findViewById(R.id.txtName);
        txtTel=(TextView)this.findViewById(R.id.txtTel);
        txtIntro=(TextView)this.findViewById(R.id.txtIntro);


        Bundle bundle0311 =this.getIntent().getExtras();


        imgPic.setImageResource(ToolArray.farmpic[new Random().nextInt(10)]);
        txtName.setText(bundle0311.getString("title"));
        txtTel.setText(ToolArray.name[new Random().nextInt(10)]+"："+ ToolArray.tel[new Random().nextInt(10)]);
        txtIntro.setText(bundle0311.getString("context"));
        txtIntro.getViewTreeObserver().addOnGlobalLayoutListener(new OnTvGlobalLayoutListener());


}



    private class OnTvGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

                 @Override
                 public void onGlobalLayout() {
                     txtIntro.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                         final String newText = autoSplitText(txtIntro);
                         if (!TextUtils.isEmpty(newText)) {
                             txtIntro.setText(newText);
                             }
                     }
             }

                 private String autoSplitText(final TextView tv) {
                 final String rawText = tv.getText().toString();
                 final Paint tvPaint = tv.getPaint();
                 final int tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight();

                 //autoSplitText begin....
                 String newText = rawText;
                 //autoSplitText end....

                 return newText;
             }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
