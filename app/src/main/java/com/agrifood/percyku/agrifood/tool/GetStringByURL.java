package com.agrifood.percyku.agrifood.tool;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetStringByURL {

	public  static String GetStringByURL(String urlPath){
		HttpURLConnection connection = null;
		String responseString="";
		try {
			// 初始化 URL
			URL url = new URL(urlPath);
			// 取得連線物件
			connection = (HttpURLConnection) url.openConnection();
			// 設定 request timeout
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			// 模擬 Chrome 的 user agent, 因為手機的網頁內容較不完整
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
			// 設定開啟自動轉址
			connection.setInstanceFollowRedirects(true);

			// 若要求回傳 200 OK 表示成功取得網頁內容
			if( connection.getResponseCode() == HttpsURLConnection.HTTP_OK ){
				// 讀取網頁內容
				InputStream     inputStream     = connection.getInputStream();
				BufferedReader  bufferedReader  = new BufferedReader( new InputStreamReader(inputStream) );

				String tempStr;
				StringBuffer stringBuffer = new StringBuffer();

				while( ( tempStr = bufferedReader.readLine() ) != null ) {
					stringBuffer.append( tempStr );
				}

				bufferedReader.close();
				inputStream.close();

				// 取得網頁內容類型
				String  mime = connection.getContentType();
				boolean isMediaStream = false;

				// 判斷是否為串流檔案
				if( mime.indexOf("audio") == 0 ||  mime.indexOf("video") == 0 ){
					isMediaStream = true;
				}

				// 網頁內容字串
				 responseString = stringBuffer.toString();

				Log.e("your",responseString);
//                Toast.makeText(MainActivity.this,responseString,Toast.LENGTH_SHORT).show();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// 中斷連線
			if( connection != null ) {
				connection.disconnect();
			}
		}

		return responseString;
	}


	

}
