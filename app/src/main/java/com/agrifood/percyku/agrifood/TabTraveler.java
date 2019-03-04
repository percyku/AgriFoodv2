package com.agrifood.percyku.agrifood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
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


/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.2@gmail.com
 * Date: 13-6-14 Time: 下�??2:39
 */

public class TabTraveler extends Fragment {

	private View mView;

	private SimpleAdapter adapter;
	private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	private int[] farmpic= new int[] { R.drawable.f2, R.drawable.f1, R.drawable.f3, R.drawable.f4, R.drawable.f6,
			R.drawable.f5, R.drawable.f7 };

	private boolean[] heart = new boolean[] { true, false,true,false,false,true,false };

	private int[] heart_num = new int[] { 56, 143,300, 587,560,453,609,};

	private int[] farmerpic= new int[] { R.drawable.heada, R.drawable.headk, R.drawable.heady, R.drawable.headg, R.drawable.head1,
			R.drawable.head3, R.drawable.head4 };

	private String[] farmnerame = new String[] {"Arro Wen","Kevin Yuag","Yen Yen","Good ","小黃","小玩","阿哲"};

	private String[] farmname = new String[] {"花先縱谷田農場","有機休閒農場","清淨乾淨農場","真美麗有機農場","神木農場","大巨木樹農場","台神拉有機農場"};

	private String[] data = new String[] {"2016/10/21","2016/07/31","2016/05/30","2016/4/20","2016/3/15","2016/02/28","2016/01/26"};

	private FloatingActionMenu menuTravel;

	private ListView listView;

	private ProgressDialog mypDialog;

	ImageView im;

	TextView tv;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("BBBBBBBBBB____onCreate");

//		mypDialog = new ProgressDialog(getActivity());
//		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		mypDialog.setMessage("搜尋中...");
//		mypDialog.setIndeterminate(false);
//		mypDialog.show();
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//
//					Thread.sleep(1000);

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



//					adapter = new SimpleAdapter(getActivity(), list, R.layout.travel_listitems,
//							new String[] { "farmpic", "heart", "heart_num","farmerpic","farmnerame","farmname","data" },
//							new int[] { R.id.picName, R.id.ic_heart, R.id.heart_number, R.id.farmer_head, R.id.farmer_name, R.id.farmer_local, R.id.farmer_date });
//
//
//
//				} catch (Exception e) {
//					e.printStackTrace();
//
//
//				}
//				getActivity().runOnUiThread(new Runnable() {
//					public void run() {
//						listView.setAdapter(adapter);
//						mypDialog.cancel();
//
//					}
//				});
//
//			}
//		}).start();


		}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("BBBBBBBBBB____onCreateView");
		mView = inflater.inflate(R.layout.tab_traveler, container, false);
		listView = (ListView) mView.findViewById(R.id.travel_listView);

//		listView.setAdapter(adapter);
		return mView;
	}



	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		menuTravel = (FloatingActionMenu) view.findViewById(R.id.menu_travel);
		menuTravel.setIconAnimated(false);


	}






	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		menuTravel.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
			@Override
			public void onMenuToggle(boolean opened) {
			startActivity(new Intent().setClass(getActivity(),TravelNewSpot.class));
			}
		});

		menuTravel.showMenuButton(true);

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



}