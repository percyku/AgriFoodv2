package com.agrifood.percyku.agrifood;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.clean.house.percyku.mylibrary.FloatingActionMenu;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.2@gmail.com
 * Date: 13-6-14 Time: 下�??2:39
 */

public class TabRecords extends Fragment {

	private View mView;

	private SimpleAdapter adapter;
	private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private ListView listView;

	private FloatingActionMenu menuRecord;

	private int[] mappic= new int[] { R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m2, R.drawable.m1,
			R.drawable.m3, R.drawable.m1 };


	private String[] farmnerame = new String[] {"Arro Wen","Kevin Yuag","Yen Yen","小明","小黃","小美","小潘"};

	private String[] farmname = new String[] {"明池有機農場","東山有機農場","清淨乾淨農場","神木大樹農場","神去村農場","真美麗有機農場","台神拉有機農場"};

	private String[] data = new String[] {"10/21 at 10:31 am","7/31 at 09:38 am","7/31 at 06:38 am","4/20 at 11:31 am","4/20 at 09:31 am","2/28 at 12:31 pm","1/26 at 15:45 pm"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("BBBBBBBBBB____onCreate");
		for (int i = 0; i < mappic.length; i++) {

			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("mappic", mappic[i]);
			item.put("head", R.drawable.heada);
			item.put("farmnerame", "Arro Wen");
			item.put("farmname", farmname[i]);
			item.put("data", data[i]);
			list.add(item);
		}

		adapter = new SimpleAdapter(getActivity(), list, R.layout.record_listitems,
				new String[] { "mappic", "head", "farmnerame","farmname","data" },
				new int[] { R.id.picName,  R.id.farmer_head, R.id.farmer_name, R.id.farmer_local, R.id.record_date });
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("BBBBBBBBBB____onCreateView");
		mView = inflater.inflate(R.layout.tab_records, container, false);

		listView = (ListView) mView.findViewById(R.id.record_listView);

		listView.setAdapter(adapter);

		return mView;
	}



	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);


		menuRecord = (FloatingActionMenu) view.findViewById(R.id.menu_record);
		menuRecord.setIconAnimated(false);




	}






	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		menuRecord.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
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

		menuRecord.showMenuButton(true);

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