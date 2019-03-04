package com.agrifood.percyku.agrifood;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.agrifood.percyku.agrifood.tool.AgriGood;
import com.agrifood.percyku.agrifood.tool.FarmRoom;
import com.agrifood.percyku.agrifood.tool.Nearby;
import com.agrifood.percyku.agrifood.tool.StayFood;
import com.agrifood.percyku.agrifood.tool.ToolArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TextView toolBarName;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private SimpleAdapter simpleAdapter;
    private ListView lstDrawer;

    private List<Fragment> mViews;

    private static boolean  searchbar=false;

    private String chectText="";

    private int currentTab = 0;
    SearchView searchView;
    MenuItem menuItem;
    int[] logos = new int[] { R.drawable.information, R.drawable.farmer, R.drawable.traveler, R.drawable.records , R.drawable.setting};
    String[] names = new String[] { "農村資訊", "農友推薦", "旅人推薦", "紀錄軌跡" , "設定     "};
    boolean close=false;

    static String mJsonstr="";

    private boolean check2;

    ProgressDialog mypDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        System.out.println("test2");
        InitViewPager();
        System.out.println("test3");
        initDrawerList();
        System.out.println("test4");


    }




    private void initDrawer(){

        toolBar = (Toolbar) findViewById(R.id.app_bar);
//        Toolbar.LayoutParams params = (Toolbar.LayoutParams)toolBar.getLayoutParams();
//        params.height = 42;
//        toolBar.setLayoutParams(params);
//        toolBar.getMenu()
        toolBar.setOnMenuItemClickListener( new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBarName=(TextView) this.findViewById(R.id.app_bar_anme);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lstDrawer = (ListView) findViewById(R.id.left_drawer);
        lstDrawer.setDivider(null);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    private void InitViewPager(){

        Log.e("ft.commit", "execute");
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        mViews=new ArrayList<Fragment>();
        mViews.add(new TabInformation());
        mViews.add(new TabFarmer());
        mViews.add(new TabTraveler());
        mViews.add(new TabRecords());
        mViews.add(new TabSetting());
        mViews.add(new TabList());
        ft.add(R.id.tab_content, mViews.get(0)).commit();


    }

    private void initDrawerList(){

        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < logos.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", logos[i]);
            item.put("text", names[i]);
            items.add(item);
        }
        simpleAdapter = new SimpleAdapter(this,
                items, R.layout.drawer_list_item, new String[]{"image", "text"},
                new int[]{R.id.imglogo, R.id.name});
        lstDrawer.setAdapter(simpleAdapter);

        lstDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Toast.makeText(MainActivity.this, names[arg2], Toast.LENGTH_SHORT).show();
                toolBarName.setText(names[arg2]);
                drawerLayout.closeDrawer(GravityCompat.START);

                FragmentTransaction ft=getFragmentManager().beginTransaction();
                Fragment fragment = mViews.get(arg2);
                mViews.get(currentTab).onPause();

                if (fragment.isAdded()) {
                    Log.v("fragment", "isAdded");
                    fragment.onResume();
                    ft.hide(mViews.get(currentTab));
                    ft.show(mViews.get(arg2));
                } else {
                    Log.v("fragment", "Add");
                    ft.hide(mViews.get(currentTab));
                    ft.add(R.id.tab_content, fragment);
                }

                if(arg2!=0){
                    Log.v("searchView", "hide");
                    menuItem.setVisible(false);
                    searchView.setVisibility(View.GONE);
                }else{
                    Log.v("fragment", "show");
                    menuItem.setVisible(true);
                    searchView.setVisibility(View.VISIBLE);
                }


                currentTab = arg2;
                ft.commit();
            }
        });
    }




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            System.out.println("onBackPressed:"+currentTab);
            if(currentTab!=0){
                System.out.println("currentTab:"+currentTab);
                menuItem.setVisible(true);
                searchView.setVisibility(View.VISIBLE);
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                Fragment fragment = mViews.get(0);
                mViews.get(currentTab).onPause();
                fragment.onResume();
                ft.hide(mViews.get(currentTab));
                ft.show(fragment);
                ft.commit();
                currentTab=0;
                close=false;
                toolBarName.setText(names[0]);

            }else{
                if(close==true){
                    super.onBackPressed();
                }
                else{
                    close=true;
                    Toast.makeText(MainActivity.this,"請在點擊一次才能關閉",Toast.LENGTH_SHORT).show();
                }
            }

        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        menuItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        Log.d("Tag", "menu create");

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {//设置打开关闭动作监听
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                mViews.get(0).onPause();
                if (mViews.get(5).isAdded()) {
                    searchItems("");
                    Log.v("listfragment", "isAdded");
                    mViews.get(5).onResume();
                    ft.hide(mViews.get(0));
                    ft.show(mViews.get(5));
                } else {
                    Log.v("listfragment", "Add");
                    ft.hide(mViews.get(0));
                    ft.add(R.id.tab_content,  mViews.get(5));
                }
                ft.commit();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {


                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    mViews.get(5).onPause();
                    mViews.get(0).onResume();
                    ft.hide(mViews.get(5));
                    ft.show(mViews.get(0));
                    ft.commit();
                    ToolArray.searchbool = true;

                if(chectText!=null)
                    check2=true;



                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                System.out.println("onQueryTextChange____onStart="+arg0+"?");
                chectText=arg0;
                if(check2!=true){
                    if(searchbar==true){
                        searchItems(arg0);
                        FragmentTransaction ft=getFragmentManager().beginTransaction();
                        mViews.get(5).onPause();
                        mViews.get(5).onResume();
                        ft.hide(mViews.get(5)).show(mViews.get(5)).commit();
                        System.out.println("onQueryTextChange____onStart:true:   "+chectText+","+arg0);


                    }else{
                        searchItems(arg0);
                        searchbar=true;

                    }

                }else{
                    check2=false;
                    searchItems("");
                }


                return false;
            }



        });

        return super.onCreateOptionsMenu(menu);
    }


    public void searchItems(String query) {
        ToolArray.itemsearch = new LinkedList<Map<String, Object>>();
        if(ToolArray.checkdata[0]==true){
            ToolArray.farmRoomArray2=new FarmRoom[500];
            int a = 0;
            for (int i = 0; i < ToolArray.farmRoomItem.size(); i++) {
                int index = ToolArray.farmRoomArray[i].getNamw().indexOf(query);

                if (index != -1) {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    item.put("name", ToolArray.farmRoomArray[i].getNamw().trim());
                    ToolArray.farmRoomArray2[a] = new FarmRoom(ToolArray.farmRoomArray[i].getNamw().toString(),
                            ToolArray.farmRoomArray[i].getTel().toString(),
                            ToolArray.farmRoomArray[i].getIntroduction().toString(),
                            ToolArray.farmRoomArray[i].getOpenHours().toString(),
                            ToolArray.farmRoomArray[i].getLat().toString(),
                            ToolArray.farmRoomArray[i].getLon().toString(),
                            ToolArray.farmRoomArray[i].getDistance().toString());

                    ToolArray.itemsearch.add(item);

                    a++;

//                    System.out.println(ToolArray.farmRoomArray2[i].getNamw().toString());
//                    System.out.println(ToolArray.farmRoomArray2[i].getNamw().toString());
                }

            }

        }else if(ToolArray.checkdata[1]==true){
            ToolArray.agriGoodArray2=new AgriGood[500];
            int a = 0;
            for (int i = 0; i < ToolArray.agriGoodItem.size(); i++) {
                int index = ToolArray.agriGoodArray[i].getNamw().indexOf(query);

                if (index != -1) {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    item.put("name", ToolArray.agriGoodArray[i].getNamw().trim());
                    ToolArray.agriGoodArray2[a] = new AgriGood(
                            ToolArray.agriGoodArray[i].getNamw().toString(),
                            ToolArray.agriGoodArray[i].getSpot().toString(),
                            ToolArray.agriGoodArray[i].getLat().toString(),
                            ToolArray.agriGoodArray[i].getLon().toString(),
                            ToolArray.agriGoodArray[i].getDistance().toString());

                    ToolArray.itemsearch.add(item);

                    a++;
                }
            }

        }else if(ToolArray.checkdata[2]==true){

            ToolArray.nearbyArray2=new Nearby[500];
            int a = 0;
            for (int i = 0; i < ToolArray.nearbyItem.size(); i++) {
                int index = ToolArray.nearbyArray[i].getNamw().indexOf(query);

                if (index != -1) {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    item.put("name", ToolArray.nearbyArray[i].getNamw().trim());
                    ToolArray.nearbyArray2[a] = new Nearby(
                            ToolArray.nearbyArray[i].getNamw().toString(),
                            ToolArray.nearbyArray[i].getArealocation().toString(),
                            ToolArray.nearbyArray[i].getFeature().toString(),
                            ToolArray.nearbyArray[i].getTrafficGuidelines().toString(),
                            ToolArray.nearbyArray[i].getLat().toString(),
                            ToolArray.nearbyArray[i].getLon().toString(),
                            ToolArray.nearbyArray[i].getDistance().toString());

                    ToolArray.itemsearch.add(item);

                    a++;
                }
            }

        }else if(ToolArray.checkdata[3]==true){


            ToolArray.stayFoodArray2=new StayFood[500];
            int a = 0;
            for (int i = 0; i < ToolArray.stayFoodItem.size(); i++) {
                int index = ToolArray.stayFoodArray[i].getNamw().indexOf(query);

                if (index != -1) {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    item.put("name", ToolArray.stayFoodArray[i].getNamw().trim());
                    ToolArray.stayFoodArray2[a] = new StayFood(
                            ToolArray.stayFoodArray[i].getNamw().toString(),
                            ToolArray.stayFoodArray[i].getTel().toString(),
                            ToolArray.stayFoodArray[i].getHostWords().toString(),
                            ToolArray.stayFoodArray[i].getStayFeature().toString(),
                            ToolArray.stayFoodArray[i].getLat().toString(),
                            ToolArray.stayFoodArray[i].getLon().toString(),
                            ToolArray.stayFoodArray[i].getDistance().toString());
                    ToolArray.itemsearch.add(item);

                    a++;
                }
            }

        }




    }








}
