package com.agrifood.percyku.agrifood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.agrifood.percyku.agrifood.tool.ToolArray;


/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.2@gmail.com
 * Date: 13-6-14 Time: 下�??2:39
 */

public class TabList extends Fragment {

	private View mView;

	private ListView sList;

	private SimpleAdapter mAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("TabList____onCreate");
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("TabList____onCreateView");
		mView = inflater.inflate(R.layout.tab_list, container, false);
		sList=(ListView)mView.findViewById(R.id.slistView);

		mAdapter = new SimpleAdapter(getActivity(), ToolArray.itemsearch, R.layout.listitem,
				new String[] { "name" }, new int[] {
				R.id.searchname}) ;
		sList.setAdapter(mAdapter);
		sList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {

				if(ToolArray.checkdata[0]==true){
					Intent it=new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("title",ToolArray.farmRoomArray2[position].getNamw().toString() );
					bundle.putString("context", ToolArray.farmRoomArray2[position].getIntroduction().toString().trim());
					it.putExtras(bundle);
					it.setClass(getActivity(),InformationContext.class);
					startActivity(it);

				}else if(ToolArray.checkdata[1]==true){
					Intent it=new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("title",ToolArray.agriGoodArray2[position].getNamw().toString() );
					bundle.putString("context", ToolArray.agriGoodArray2[position].getSpot().toString().trim());
					it.putExtras(bundle);
					it.setClass(getActivity(),InformationContext.class);
					startActivity(it);


				}else if(ToolArray.checkdata[2]==true){

					Intent it=new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("title",ToolArray.nearbyArray2[position].getNamw().toString() );
					bundle.putString("context", ToolArray.nearbyArray2[position].getFeature().toString().trim());
					it.putExtras(bundle);
					it.setClass(getActivity(),InformationContext.class);
					startActivity(it);

				}else if(ToolArray.checkdata[3]==true){

					Intent it=new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("title",ToolArray.stayFoodArray2[position].getNamw().toString() );
					bundle.putString("context", ToolArray.stayFoodArray2[position].getHostWords().toString().trim());
					it.putExtras(bundle);
					it.setClass(getActivity(),InformationContext.class);
					startActivity(it);

				}



			}
		});


		return mView;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		System.out.println("TabList____onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		System.out.println("TabList____onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("TabList____onResume");

		if(ToolArray.searchbool==true){
			System.out.println("TabList____onResume:true1");
			sList.setVisibility(View.VISIBLE);
			mAdapter = new SimpleAdapter(getActivity(), ToolArray.itemsearch, R.layout.listitem,
					new String[] { "name" }, new int[] {
					R.id.searchname}) ;
			System.out.println("TabList____onResume:true1");
			sList.setAdapter(mAdapter);
			sList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {

					if(ToolArray.checkdata[0]==true){
						Intent it=new Intent();
						Bundle bundle = new Bundle();
//						Log.e("error!!!!!!!!!!",""+ToolArray.farmRoomArray2[position].getNamw().toString().trim());
						bundle.putString("title",ToolArray.farmRoomArray2[position].getNamw().toString().trim() );
						bundle.putString("context", ToolArray.farmRoomArray2[position].getIntroduction().toString().trim());
						it.putExtras(bundle);
						it.setClass(getActivity(),InformationContext.class);
						startActivity(it);


					}else if(ToolArray.checkdata[1]==true){
						Intent it=new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("title",ToolArray.agriGoodArray2[position].getNamw().toString().trim() );
						bundle.putString("context", ToolArray.agriGoodArray2[position].getSpot().toString().trim());
						it.putExtras(bundle);
						it.setClass(getActivity(),InformationContext.class);
						startActivity(it);


					}else if(ToolArray.checkdata[2]==true){

						Intent it=new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("title",ToolArray.nearbyArray2[position].getNamw().toString() );
						bundle.putString("context", ToolArray.nearbyArray2[position].getFeature().toString().trim());
						it.putExtras(bundle);
						it.setClass(getActivity(),InformationContext.class);
						startActivity(it);

					}else if(ToolArray.checkdata[3]==true){

						Intent it=new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("title",ToolArray.stayFoodArray2[position].getNamw().toString() );
						bundle.putString("context", ToolArray.stayFoodArray2[position].getHostWords().toString().trim());
						it.putExtras(bundle);
						it.setClass(getActivity(),InformationContext.class);
						startActivity(it);

					}







				}
			});


		}else{
			ToolArray.searchbool=true;
		}



	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("Tablist____onPause");
		sList.clearTextFilter();
		sList.setVisibility(View.GONE);
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