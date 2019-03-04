package com.agrifood.percyku.agrifood;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.2@gmail.com
 * Date: 13-6-14 Time: 下�??2:39
 */

public class TabSetting extends Fragment {

	private View mView;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("BBBBBBBBBB____onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("BBBBBBBBBB____onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("BBBBBBBBBB____onCreateView");
		mView = inflater.inflate(R.layout.tab_setting, container, false);

		return mView;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		System.out.println("BBBBBBBBBB____onActivityCreated");
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