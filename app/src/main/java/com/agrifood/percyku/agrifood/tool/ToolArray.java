package com.agrifood.percyku.agrifood.tool;

import com.agrifood.percyku.agrifood.R;

import java.util.List;
import java.util.Map;

/**
 * Created by percyku on 16/10/31.
 */
public class ToolArray {

    public static boolean[] checkdata={true,false,false,false};

    public static boolean searchbool=false;

    public static FarmRoom[] farmRoomArray,farmRoomArray2;

    public static AgriGood[] agriGoodArray,agriGoodArray2;

    public static Nearby[] nearbyArray,nearbyArray2;

    public static StayFood[] stayFoodArray,stayFoodArray2;




    public static List<Map<String, Object>> farmRoomItem;
    public static List<Map<String, Object>> agriGoodItem;
    public static List<Map<String, Object>> nearbyItem;
    public static List<Map<String, Object>> stayFoodItem;

    public static List<Map<String,Object>> itemsearch;


    public static String[] name={"陳先生","陳小姐","黃先生","黃小姐","林先生","林小姐","古先生","古小姐","溫先生","溫小姐"};

    public static String[] tel={"0953595870","0933720985","0970847076","0931612545","0972392741","0937166384","0955299581","0923593677","0955468619","0916232042"};


    public static int[] farmpic= new int[] { R.drawable.f1, R.drawable.agri3, R.drawable.f2, R.drawable.f3, R.drawable.f4,
            R.drawable.f5, R.drawable.f6 , R.drawable.agri1, R.drawable.agri2, R.drawable.agri4};
}
