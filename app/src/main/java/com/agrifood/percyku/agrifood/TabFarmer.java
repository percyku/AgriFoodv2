package com.agrifood.percyku.agrifood;


import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.clean.house.percyku.mylibrary.FloatingActionMenu;

import java.util.ArrayList;
import java.util.HashMap;


public class TabFarmer extends Fragment {

	private View mView;

	SimpleAdapter adapter;
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	private int[] farmpic= new int[] { R.drawable.agri1, R.drawable.agri2, R.drawable.agri3, R.drawable.agri4, R.drawable.agri5,
			R.drawable.agri6, R.drawable.agri7 };

	private boolean[] heart = new boolean[] { true, false,true,false,false,true,false };

	private int[] heart_num = new int[] { 56, 143, 300, 587,560,453,609,};

	private int[] farmerpic= new int[] { R.drawable.farm1, R.drawable.farm3, R.drawable.farm2, R.drawable.farm4, R.drawable.farm5,
			R.drawable.farm6, R.drawable.farm7 };

	private String[] farmnerame = new String[] {"阿土伯","陳阿金","葉大雄","林火旺","陳小黃","余天","陳美美"};

	private String[] farmname = new String[] {"花田有機農場","超極有機農場","這沒什麼農場","神木大樹農場","神去村農場","無極限有機農場","太棒拉有機農場"};

	private String[] data = new String[] {"2016/10/21","2016/07/31","2016/05/30","2016/4/20","2016/3/15","2016/02/28","2016/01/26"};

	private FloatingActionMenu menuFarm;

	private ListView listView;

	private ProgressDialog mypDialog;

	ImageView im;

	TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("BBBBBBBBBB____onCreate");



		for (int i = 0; i < farmpic.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			if (heart[i]) {
				item.put("farmpic", farmpic[i]);
				item.put("heart", R.drawable.ic_heart_red);
				item.put("heart_num", heart_num[i]);
				item.put("farmerpic", farmerpic[i]);
				item.put("farmnerame", farmnerame[i]);
				item.put("farmname", farmname[i]);
				item.put("data", data[i]);
			} else {
				item.put("farmpic", farmpic[i]);
				item.put("heart", R.drawable.ic_heart_black);
				item.put("heart_num", heart_num[i]);
				item.put("farmerpic", farmerpic[i]);
				item.put("farmnerame", farmnerame[i]);
				item.put("farmname", farmname[i]);
				item.put("data", data[i]);
			}
			list.add(item);

		}




		//Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("BBBBBBBBBB____onCreateView");
		mView = inflater.inflate(R.layout.tab_farmer, container, false);

		listView = (ListView) mView.findViewById(R.id.farm_listView);




		return mView;



	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		System.out.println("BBBBBBBBBB____onViewCreated");

		im=(ImageView) view.findViewById(R.id.ic_heart);
//		menuFarm = (FloatingActionMenu) view.findViewById(R.id.menu_farmer);
//		menuFarm.setIconAnimated(false);

		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setMessage("載入中...");
		mypDialog.setIndeterminate(false);
		mypDialog.show();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					for (int i = 0; i < farmpic.length; i++) {
						HashMap<String, Object> item = new HashMap<String, Object>();
						if(heart[i]){
							item.put("farmpic", farmpic[i]);
							item.put("heart", R.drawable.ic_heart_red);
							item.put("heart_num", heart_num[i]);
							item.put("farmerpic", farmerpic[i]);
							item.put("farmnerame", farmnerame[i]);
							item.put("farmname", farmname[i]);
							item.put("data", data[i]);
						}else{
							item.put("farmpic", farmpic[i]);
							item.put("heart", R.drawable.ic_heart_black);
							item.put("heart_num", heart_num[i]);
							item.put("farmerpic", farmerpic[i]);
							item.put("farmnerame", farmnerame[i]);
							item.put("farmname", farmname[i]);
							item.put("data", data[i]);
						}
						list.add(item);
					}


//					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();


				}
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						mypDialog.cancel();

					}
				});

			}
		}).start();

		adapter = new SimpleAdapter(getActivity(), list, R.layout.farm_listitems,
				new String[] { "farmpic", "heart", "heart_num","farmerpic","farmnerame","farmname","data" },
				new int[] { R.id.picName, R.id.ic_heart, R.id.heart_number, R.id.farmer_head, R.id.farmer_name, R.id.farmer_local, R.id.farmer_date })
		{
			public View getView( final  int position, View convertView, ViewGroup parent) {

				View v = super.getView(position, convertView, parent);
				tv=(TextView) v.findViewById(R.id.heart_number);
				im=(ImageView) v.findViewById(R.id.ic_heart);
				im.setClickable(true);
				im.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if(heart[position]==true){
							im.setImageResource(R.drawable.ic_heart_black);
							int a=Integer.valueOf(heart_num[position])-1;
							tv.setText(""+a);
							heart[position]=false;
							heart_num[position]=a;
							System.out.println(""+heart[position]+":"+heart_num[position]);
						}else{
							im.setImageResource(R.drawable.ic_heart_red);
							int a=Integer.valueOf(heart_num[position])+1;
							tv.setText(""+a);
							heart[position]=true;
							heart_num[position]=a;
							System.out.println(""+heart[position]+":"+heart_num[position]);

						}

						refesh();

					}
				});

				return v;
			}
		};

		listView.setAdapter(adapter);



	}










	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		System.out.println("BBBBBBBBBB____onActivityCreated");

//		menuFarm.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
//			@Override
//			public void onMenuToggle(boolean opened) {
//
//				Intent it=new Intent();
//				it.setClass(getActivity(),FramerNewSpot.class);
//				startActivity(it);
//			}
//		});
//
//		menuFarm.showMenuButton(true);


	}





	@Override
	public void onStart() {
		super.onStart();
		System.out.println("BBBBBBBBBB____onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("BBBBBBBBBB____onResume");


	}



	@Override
	public void onPause() {
		super.onPause();
		System.out.println("BBBBBBBBBB____onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("BBBBBBBBBB____onStop");

		// creating pending intent:

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("BBBBBBBBBB____onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("BBBBBBBBBB____onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		System.out.println("BBBBBBBBBB____onDetach");
	}



	private void refesh(){
//		listView.clearTextFilter();
		list.clear();
		for (int i = 0; i < farmpic.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			if(heart[i]){
				item.put("farmpic", farmpic[i]);
				item.put("heart", R.drawable.ic_heart_red);
				item.put("heart_num", heart_num[i]);
				item.put("farmerpic", farmerpic[i]);
				item.put("farmnerame", farmnerame[i]);
				item.put("farmname", farmname[i]);
				item.put("data", data[i]);
			}else{
				item.put("farmpic", farmpic[i]);
				item.put("heart", R.drawable.ic_heart_black);
				item.put("heart_num", heart_num[i]);
				item.put("farmerpic", farmerpic[i]);
				item.put("farmnerame", farmnerame[i]);
				item.put("farmname", farmname[i]);
				item.put("data", data[i]);
			}
			list.add(item);

		}



		adapter.notifyDataSetChanged();

	}


}