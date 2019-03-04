package com.agrifood.percyku.agrifood;


import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.agrifood.percyku.agrifood.tool.AgriGood;
import com.agrifood.percyku.agrifood.tool.FarmRoom;
import com.agrifood.percyku.agrifood.tool.GetStringByURL;
import com.agrifood.percyku.agrifood.tool.Nearby;
import com.agrifood.percyku.agrifood.tool.StayFood;
import com.agrifood.percyku.agrifood.tool.ToolArray;
import com.clean.house.percyku.mylibrary.FloatingActionButton;
import com.clean.house.percyku.mylibrary.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TabInformation extends Fragment implements LocationListener,OnMapReadyCallback {

	private FloatingActionMenu menuAgri;


	private Double myLatitude=25.0216697;
	private Double myLongitude = 121.5331069;

	static String mJsonstr="";

	private ProgressDialog mypDialog;

	static  int index;

	boolean internet=true;

	TextView tv;

	MapFragment fragment;

	GoogleMap googleMapTest;

	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {

			if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
					!= PackageManager.PERMISSION_GRANTED)
			{
				ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


			}else{
				getJson("http://140.137.51.155:443/FarmRoomJson.aspx?");

			}

		}else{
			getJson("http://140.137.51.155:443/FarmRoomJson.aspx?");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mView;

		mView=inflater.inflate(R.layout.tab_information, container, false);
		tv=(TextView)mView.findViewById(R.id.scrollKK);
		tv.setVisibility(View.GONE);
		return mView;


	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);


		fragment= (MapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
		fragment.getMapAsync(this);
		fragment.getView().setVisibility(View.GONE);
		menuAgri = (FloatingActionMenu) view.findViewById(R.id.menu_agri);
		ContextThemeWrapper context = new ContextThemeWrapper(getActivity(), R.style.MenuButtonsStyle);
		FloatingActionButton programFab1 = new FloatingActionButton(context);
		FloatingActionButton programFab2 = new FloatingActionButton(context);
		FloatingActionButton programFab3 = new FloatingActionButton(context);
		FloatingActionButton programFab4 = new FloatingActionButton(context);
		programFab1.setButtonSize(FloatingActionButton.SIZE_NORMAL);
		programFab2.setButtonSize(FloatingActionButton.SIZE_NORMAL);
		programFab3.setButtonSize(FloatingActionButton.SIZE_NORMAL);
		programFab4.setButtonSize(FloatingActionButton.SIZE_NORMAL);
		programFab1.setLabelText("食宿資訊");
		programFab1.setImageResource(R.drawable.ic_stayfood);
		programFab2.setLabelText("周邊景點");
		programFab2.setImageResource(R.drawable.ic_sport);
		programFab3.setLabelText("農村好讚");
		programFab3.setImageResource(R.drawable.ic_farmgood);
		programFab4.setLabelText("休閒農場");
		programFab4.setImageResource(R.drawable.ic_freefarm);

		menuAgri.addMenuButton(programFab1);
		menuAgri.addMenuButton(programFab2);
		menuAgri.addMenuButton(programFab3);
		menuAgri.addMenuButton(programFab4);
		menuAgri.hideMenuButton(false);


		programFab1.setLabelColors(ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton));
		programFab1.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
		programFab2.setLabelColors(ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton));
		programFab2.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
		programFab3.setLabelColors(ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton));
		programFab3.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
		programFab4.setLabelColors(ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton),ContextCompat.getColor(getActivity(), R.color.floatbutton));
		programFab4.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				menuAgri.close(true);
				fragment.getView().setVisibility(View.GONE);
				googleMapTest.clear();tv.setVisibility(View.GONE);
				mCheck(3);
				getJson("http://140.137.51.169/StayFoodJson.aspx?");
				googleMapTest.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude,myLongitude), 10.0f));
				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(myLatitude,myLongitude)).title("目前所在位置").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_myself))).showInfoWindow();
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
//						Toast.makeText(getActivity(),""+87,Toast.LENGTH_SHORT).show();

					}
				});


			}
        });
        programFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				menuAgri.close(true);
				fragment.getView().setVisibility(View.GONE);
				googleMapTest.clear();tv.setVisibility(View.GONE);
				mCheck(2);
				getJson("http://140.137.51.169/NearbyJson.aspx?");
				googleMapTest.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude,myLongitude), 10.0f));
				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(myLatitude,myLongitude)).title("目前所在位置").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_myself))).showInfoWindow();
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
//						Toast.makeText(getActivity(),""+87,Toast.LENGTH_SHORT).show();

					}
				});


			}
        });

        programFab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				menuAgri.close(true);
				fragment.getView().setVisibility(View.GONE);
				googleMapTest.clear();tv.setVisibility(View.GONE);
				mCheck(1);
				getJson("http://140.137.51.169/AgriGoodJson.aspx?");
				googleMapTest.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude,myLongitude), 10.0f));
				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(myLatitude,myLongitude)).title("目前所在位置").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_myself))).showInfoWindow();
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
//						Toast.makeText(getActivity(),""+87,Toast.LENGTH_SHORT).show();

					}
				});

			}
        });
		programFab4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				menuAgri.close(true);
				fragment.getView().setVisibility(View.GONE);
				googleMapTest.clear();tv.setVisibility(View.GONE);
				mCheck(0);
				getJson("http://140.137.51.169/FarmRoomJson.aspx?");
				googleMapTest.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude,myLongitude), 10.0f));
				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(myLatitude,myLongitude)).title("目前所在位置").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_myself))).showInfoWindow();
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
//						Toast.makeText(getActivity(),""+87,Toast.LENGTH_SHORT).show();
					}
				});



			}
		});

	}

	private void mCheck(int a){

		for(int i=0;i<4;i++){
			if(a==i){
				ToolArray.checkdata[i]=true;
			}else{
				ToolArray.checkdata[i]=false;
			}

		}





	}

	@Override
	public void onMapReady(GoogleMap googleMap) {

		googleMapTest=googleMap;
		googleMapTest.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLatitude,myLongitude), 10.0f));
		googleMapTest.addMarker(new MarkerOptions().position(new LatLng(myLatitude,myLongitude)).title("目前所在位置").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_myself))).showInfoWindow();
		googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
//				Toast.makeText(getActivity(),""+87,Toast.LENGTH_SHORT).show();
			}
		});


	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		menuAgri.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
			@Override
			public void onMenuToggle(boolean opened) {
//				String text;
//				if (opened) {
//					text = "Menu opened";
//				} else {
//					text = "Menu closed";
//				}
//				Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
			}
		});

		menuAgri.showMenuButton(true);


	}




	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == 1)
		{
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
			{
				Toast.makeText(getActivity(), "open", Toast.LENGTH_SHORT).show();
				getJson("http://140.137.51.155:443/FarmRoomJson.aspx?");
			} else
			{
				// Permission Denied
				Toast.makeText(getActivity(), "Permission Denied:1", Toast.LENGTH_SHORT).show();
			}
			return;
		}
	}


String a;

	private void getJson(String net){
		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setMessage("搜尋中...");
		mypDialog.setIndeterminate(false);
		mypDialog.show();
		a=net;
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					mJsonstr = GetStringByURL.GetStringByURL(a+"latitude=25.023178&longitude=121.544186");


				} catch (Exception e) {
					e.printStackTrace();
					internet=false;


				}
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						if(internet){
							makeTable(mJsonstr);
						}
						else{
							Toast.makeText(getActivity(),"確認網路是否有開",Toast.LENGTH_SHORT).show();;
						}

						mypDialog.cancel();

						tv.setVisibility(View.VISIBLE);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd   HH:mm");
						SimpleDateFormat sdf6 = new SimpleDateFormat("a");
						Date dt=new Date();
						String dts=sdf.format(dt);
						String dts2=sdf6.format(dt);
						if(dts2.equals("上午"))
							tv.setText(dts +" "+"am"+"    您周遭的相關環境指數     PM2.5 : 19     紫外線： 7      鄰近水域： 新店溪輕度汙染，懸浮粒子9.0");
						else
							tv.setText(dts +" "+"pm"+"    您周遭的相關環境指數     PM2.5 : 19     紫外線： 7      鄰近水域： 新店溪輕度汙染，懸浮粒子9.0");
						tv.setSelected(true);
						//tv.setText(str);
						Log.v("getData",mJsonstr);

						//Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();
					}
				});

			}
		}).start();
	}


	private void makeTable(String menus) {

		try {
			Log.e("getJson","2");
			if(ToolArray.checkdata[0]==true){
				ToolArray.farmRoomItem = new ArrayList<Map<String, Object>>();
				Log.e("getJson","3");
				JSONArray a = new JSONArray(menus);
				Log.e("getJson","5:"+a.length());
				ToolArray.farmRoomArray=new FarmRoom[a.length()];
				Log.d("JsonArrayNum",""+a.length());
				for (int i = 0; i < a.length(); i++) {
//				Log.e("getJson",""+i);
					HashMap<String, Object> item = new HashMap<String, Object>();
					JSONObject current = a.getJSONObject(i);
					item.put("name", current.getString("name").toString());
					ToolArray.farmRoomArray[i] = new FarmRoom(current
							.getString("name").trim().toString(), current
							.getString("tel").trim().toString(), current
							.getString("introduction").trim().toString(), current
							.getString("openHours").trim().toString(), current
							.getString("latitude").trim().toString(), current
							.getString("longitude").trim().toString(), current
							.getString("distance").trim().toString());
					ToolArray.farmRoomItem.add(item);
				}
			}else if(ToolArray.checkdata[1]==true){
				ToolArray.agriGoodItem= new ArrayList<Map<String, Object>>();
				JSONArray a = new JSONArray(menus);
				ToolArray.agriGoodArray= new AgriGood[a.length()];
				for (int i = 0; i < a.length(); i++) {
					HashMap<String, Object> item = new HashMap<String, Object>();
					JSONObject current = a.getJSONObject(i);
					item.put("name", current.getString("name").toString());

					ToolArray.agriGoodArray[i] = new AgriGood(current
							.getString("name").trim().toString(), current
							.getString("spot").trim().toString(), current
							.getString("latitude").trim().toString(), current
							.getString("longitude").trim().toString(), current
							.getString("distance").trim().toString());
					ToolArray.agriGoodItem.add(item);
				}

			}else if(ToolArray.checkdata[2]==true){
				ToolArray.nearbyItem= new ArrayList<Map<String, Object>>();
				JSONArray a = new JSONArray(menus);
				ToolArray.nearbyArray= new Nearby[a.length()];
				for (int i = 0; i < a.length(); i++) {
					HashMap<String, Object> item = new HashMap<String, Object>();
					JSONObject current = a.getJSONObject(i);
					item.put("name", current.getString("name").toString());
					ToolArray.nearbyArray[i] = new Nearby(current
							.getString("name").trim().toString(), current
							.getString("arealocation").trim().toString(),current
							.getString("feature").trim().toString(),
							current.getString("TrafficGuidelines").trim().toString(), current
							.getString("latitude").trim().toString(), current
							.getString("longitude").trim().toString(), current
							.getString("distance").trim().toString());
					ToolArray.nearbyItem.add(item);
				}



			}else if(ToolArray.checkdata[3]==true){

				ToolArray.stayFoodItem= new ArrayList<Map<String, Object>>();
				JSONArray a = new JSONArray(menus);
				ToolArray.stayFoodArray= new StayFood[a.length()];
				for (int i = 0; i < a.length(); i++) {
					HashMap<String, Object> item = new HashMap<String, Object>();
					JSONObject current = a.getJSONObject(i);
					item.put("name", current.getString("name").toString());
					ToolArray.stayFoodArray[i] = new StayFood(current
							.getString("name").trim().toString(), current
							.getString("tel").trim().toString(),current
							.getString("hostWords").trim().toString(),current
							.getString("stayFeature").trim().toString(), current
							.getString("latitude").trim().toString(), current
							.getString("longitude").trim().toString(), current
							.getString("distance").trim().toString());
					ToolArray.stayFoodItem.add(item);

				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}







		if(ToolArray.checkdata[0]==true){
			for (int i = 0; i < ToolArray.farmRoomItem.size(); i++) {

				index=i;
				Double lat=Double.parseDouble(ToolArray.farmRoomArray[i].getLat().toString());
				Double lon=Double.parseDouble(ToolArray.farmRoomArray[i].getLon().toString());

				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title("休閒農場").snippet(ToolArray.farmRoomArray[i].getNamw().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maker_others)));
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {

						String tit;

						for(int i=0;i<ToolArray.farmRoomItem.size();i++){
							System.out.println(""+i);

							if(ToolArray.farmRoomArray[i].getNamw().toString().equals(marker.getSnippet())){
								tit=ToolArray.farmRoomArray[i].getNamw().toString();
								Toast.makeText(getActivity(),""+tit,Toast.LENGTH_SHORT).show();

								Intent it=new Intent();
								Bundle bundle = new Bundle();
								bundle.putString("title",ToolArray.farmRoomArray[i].getNamw().toString() );
								bundle.putString("context", ToolArray.farmRoomArray[i].getIntroduction().toString().trim());
								it.putExtras(bundle);
								it.setClass(getActivity(),InformationContext.class);
								startActivity(it);

							}
						}
					}
				});


			}
		}else if(ToolArray.checkdata[1]==true){
			for (int i = 0; i < ToolArray.agriGoodItem.size(); i++) {

				index=i;
				Double lat=Double.parseDouble(ToolArray.agriGoodArray[i].getLat().toString());
				Double lon=Double.parseDouble(ToolArray.agriGoodArray[i].getLon().toString());

				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title("農村好讚").snippet(ToolArray.agriGoodArray[i].getNamw().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maker_others)));
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {

						String tit;

						for(int i=0;i<ToolArray.agriGoodItem.size();i++){
							System.out.println(""+i);

							if(ToolArray.agriGoodArray[i].getNamw().toString().equals(marker.getSnippet())){
								tit=ToolArray.agriGoodArray[i].getNamw().toString();
								Toast.makeText(getActivity(),""+tit,Toast.LENGTH_SHORT).show();

								Intent it=new Intent();
								Bundle bundle = new Bundle();
								bundle.putString("title",ToolArray.agriGoodArray[i].getNamw().toString() );
								bundle.putString("context", ToolArray.agriGoodArray[i].getSpot().toString().trim());
								it.putExtras(bundle);
								it.setClass(getActivity(),InformationContext.class);
								startActivity(it);

							}
						}
					}
				});
			}
		}else if(ToolArray.checkdata[2]==true){
			for (int i = 0; i < ToolArray.nearbyItem.size(); i++) {

				index=i;
				Double lat=Double.parseDouble(ToolArray.nearbyArray[i].getLat().toString());
				Double lon=Double.parseDouble(ToolArray.nearbyArray[i].getLon().toString());

				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title("周邊景點").snippet(ToolArray.nearbyArray[i].getNamw().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maker_others)));
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {

						String tit;

						for(int i=0;i<ToolArray.nearbyItem.size();i++){
							System.out.println(""+i);

							if(ToolArray.nearbyArray[i].getNamw().toString().equals(marker.getSnippet())){
								tit=ToolArray.nearbyArray[i].getNamw().toString();
								Toast.makeText(getActivity(),""+tit,Toast.LENGTH_SHORT).show();

								Intent it=new Intent();
								Bundle bundle = new Bundle();
								bundle.putString("title",ToolArray.nearbyArray[i].getNamw().toString() );
								bundle.putString("context", ToolArray.nearbyArray[i].getFeature().toString().trim());
								it.putExtras(bundle);
								it.setClass(getActivity(),InformationContext.class);
								startActivity(it);

							}
						}
					}
				});


			}

		}else if(ToolArray.checkdata[3]==true){

			for (int i = 0; i < ToolArray.stayFoodItem.size(); i++) {

				index=i;
				Double lat=Double.parseDouble(ToolArray.stayFoodArray[i].getLat().toString());
				Double lon=Double.parseDouble(ToolArray.stayFoodArray[i].getLon().toString());

				googleMapTest.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title("吃＆住").snippet(ToolArray.stayFoodArray[i].getNamw().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maker_others)));
				googleMapTest.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {

						String tit;

						for(int i=0;i<ToolArray.stayFoodItem.size();i++){
							System.out.println(""+i);

							if(ToolArray.stayFoodArray[i].getNamw().toString().equals(marker.getSnippet())){
								tit=ToolArray.stayFoodArray[i].getNamw().toString();
								Toast.makeText(getActivity(),""+tit,Toast.LENGTH_SHORT).show();

								Intent it=new Intent();
								Bundle bundle = new Bundle();
								bundle.putString("title",ToolArray.stayFoodArray[i].getNamw().toString() );
								bundle.putString("context", ToolArray.stayFoodArray[i].getHostWords().toString().trim());
								it.putExtras(bundle);
								it.setClass(getActivity(),InformationContext.class);
								startActivity(it);

							}
						}
					}
				});


			}


		}






		fragment.getView().setVisibility(View.VISIBLE);
	}







	@Override
	public void onResume() {
		super.onResume();
		System.out.println("AAAAAAAAAA____onResume");


	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("AAAAAAAAAA____onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("AAAAAAAAAA____onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("AAAAAAAAAA____onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("BAAAAAAAAAA____onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		System.out.println("AAAAAAAAAA____onDetach");
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		super.onStart();
		System.out.println("AAAAAAAAAA____onStart");
	}


}