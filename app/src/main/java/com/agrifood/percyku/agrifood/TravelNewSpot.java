package com.agrifood.percyku.agrifood;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TravelNewSpot extends AppCompatActivity {
    private Toolbar toolBar;
    private String picUri = "";
    private Uri uri;

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framer_new_spot_v2);

        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tv= (TextView) this.findViewById(R.id.upload_1);
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


    public void upPic(View view){
        choicepicture();
    }


    public void choicepicture() {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(it, 101);

    }

    Uri converUri(Uri ur) {
        if (ur.toString().substring(0, 7).equals("content")) {

            String[] colName = { MediaStore.MediaColumns.DATA };
            Cursor cursor = getContentResolver().query(ur, colName, null, null,
                    null);
            cursor.moveToFirst();
            picUri = cursor.getString(0);
            ur = Uri.parse("file://" + cursor.getString(0));
            // picUri=ur.toString();
        }
        return ur;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 101:

                    picUri = data.getData().toString();
                    uri = converUri(data.getData());
                    tv.setVisibility(View.VISIBLE);
                    break;
                default:
                    tv.setVisibility(View.GONE);
                    break;
            }
            }


    }
}
