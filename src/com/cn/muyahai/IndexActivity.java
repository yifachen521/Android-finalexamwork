package com.cn.muyahai;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends Activity {
	InputStream in = null;
	String citycode ;
	static String cityName;
	static String wendu;
	static String fengli;
	static String fengxiang;
	static String type;
	static String high;
	static String low;
	public static final int update = 1;
	ImageView weatherImg;
	TextView wenduView ;
	TextView tianqiView;
	TextView fengxiangView ;
	TextView fengliView;
	TextView typeView;
	TextView highView;
	TextView lowView;
	TextView citynameView;
	Button btn_back;
	Button button;
	Intent intent;
	Weather todayWeather = null;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case update:
				updateTodayWeather((Weather)msg.obj);
				break;
			}
		}
		
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		button = (Button)findViewById(R.id.btn_right);
		btn_back = (Button)findViewById(R.id.button1);
		btn_back.setOnClickListener(mGoBack);
		button.setOnCreateContextMenuListener(this);
		initView();
		Bundle bundle2 = this.getIntent().getExtras();
		citycode = bundle2.getString("cname");
		getWeatherDatafromNet(citycode);
		
	}
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
	        MenuInflater me = getMenuInflater();
	        me.inflate(R.menu.menu,menu);
	        super.onCreateContextMenu(menu, v, menuInfo);
	    }
	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
			case R.id.action_1:
				Intent intent = new Intent(IndexActivity.this,ShowActivity.class);
				startActivity(intent);
				break;
			case R.id.action_2:
				Intent intent2 = new Intent(IndexActivity.this,IndexActivity.class);
				Bundle bundle =new Bundle();
				bundle.putString("cname", "101181601");
				intent2.putExtras(bundle);
				startActivity(intent2);
				break;
			case R.id.action_3:
				Intent intent3 = new Intent(IndexActivity.this,QueryActivity.class);
				startActivity(intent3);
				break;
			case R.id.action_4:
				Intent intent4 = new Intent(IndexActivity.this,MainActivity.class);
				startActivity(intent4);
				break;
			default:
				break;
			}
	        return super.onContextItemSelected(item);
	    }
	    public void showmenu(View v) {
	    	v.showContextMenu();
	    }

void updateTodayWeather(Weather todayWeather)
    {
        citynameView.setText("城市:"+todayWeather.getCity());
        tianqiView.setText("天气:"+todayWeather.getType());
        wenduView.setText(todayWeather.getWendu()+"℃");
        highView.setText(todayWeather.getHigh()+"~"+todayWeather.getLow());
        fengliView.setText("风力:"+todayWeather.getFengli());
        fengxiangView.setText("风向:"+todayWeather.getFengxiang());

        if(todayWeather.getType()!=null) {
            if("晴".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);
            }else if ("阴".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_yin);
			}else if ("雾".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_wu);
			}else if ("多云".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_duoyun);
			}else if ("小雨".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
			}else if ("中雨".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
			}else if ("大雨".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_dayu);
			}else if ("阵雨".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
			}else if ("雷阵雨".equals(todayWeather.getType())) {
            	weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
			}
        }
    }
	void initView()
    {
		wenduView =(TextView)findViewById(R.id.wendu);
		tianqiView = (TextView)findViewById(R.id.tianqi);
		fengxiangView = (TextView)findViewById(R.id.fengxiang);
		weatherImg = (ImageView)findViewById(R.id.imageView1);
		highView = (TextView)findViewById(R.id.high);
		fengliView = (TextView)findViewById(R.id.fengli);
		citynameView = (TextView)findViewById(R.id.cityname);
        wenduView.setText("未知");
        tianqiView.setText("天气:未知");
        fengxiangView.setText("风向:未知");
        fengliView.setText("风力:未知");
        citynameView.setText("城市:请刷心当前页面");
        highView.setText("高低温:未知");
        weatherImg.setImageResource(R.drawable.back);
    }
private void getWeatherDatafromNet(String cityCode)
    {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey="+cityCode;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
            		InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer sb = new StringBuffer();
                    String str;
                    while((str=reader.readLine())!=null)
                    {
                        sb.append(str);
                    }
                    String response = sb.toString();
                    todayWeather = parseXML(response);
                    if(todayWeather !=null) {
                    	Message msg = new Message();
                    	msg.what = update;
                    	msg.obj=todayWeather;
                    	handler.sendMessage(msg);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
public static class CheckNet {
    public final static int NET_NONE = 0;
    public final static int NET_WIFI = 1;
    public final static int NET_MOBILE = 2;
    public static int getNetState(Context context)
    {
 
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null)
            return NET_NONE;
        int type = networkInfo.getType();
        if(type == ConnectivityManager.TYPE_MOBILE)
            return NET_MOBILE;
        else if(type == ConnectivityManager.TYPE_WIFI)
            return NET_WIFI;
        return NET_MOBILE;
    }
}

private Weather parseXML(String xmlData)
{
    Weather todayWeather = null;

    int fengliCount = 0;
    int fengxiangCount = 0;
    int highCount = 0;
    int lowCount = 0;
    int typeCount = 0;
    try {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = factory.newPullParser();
        xmlPullParser.setInput(new StringReader(xmlData));
        int eventType = xmlPullParser.getEventType();
        while(eventType!=xmlPullParser.END_DOCUMENT)
        {
            switch (eventType)
            {
                //文档开始位置
                case XmlPullParser.START_DOCUMENT:
                    break;
                //标签元素开始位置
                case XmlPullParser.START_TAG:
                    if(xmlPullParser.getName().equals("resp"))
                    {
                        todayWeather = new Weather();
                    }
                    if(todayWeather!=null) {
                        if (xmlPullParser.getName().equals("city")) {
                            eventType = xmlPullParser.next();
                            todayWeather.setCity(xmlPullParser.getText());
                            cityName=xmlPullParser.getText();
                        }else if (xmlPullParser.getName().equals("wendu")) {
                            eventType = xmlPullParser.next();
                            wendu = xmlPullParser.getText();
                            todayWeather.setWendu(xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                            eventType = xmlPullParser.next();
                            fengli = xmlPullParser.getText();
                            todayWeather.setFengli(xmlPullParser.getText());
                            fengliCount++;
                        } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                            eventType = xmlPullParser.next();
                            todayWeather.setFengxiang(xmlPullParser.getText());
                            fengxiang =xmlPullParser.getText();
                            fengxiangCount++;
                        } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                            eventType = xmlPullParser.next();
                            todayWeather.setHigh(xmlPullParser.getText());
                            high = xmlPullParser.getText();
                            highCount++;
                        } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                            eventType = xmlPullParser.next();
                            todayWeather.setLow(xmlPullParser.getText());
                            low = xmlPullParser.getText();
                            lowCount++;
                        } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                            eventType = xmlPullParser.next();
                            todayWeather.setType(xmlPullParser.getText());
                            type = xmlPullParser.getText();
                            typeCount++;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            }
            eventType=xmlPullParser.next();
        }
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    return todayWeather;
}
public OnClickListener mGoBack = new OnClickListener() {

	public void onClick(View v) {
	finish();
	}
	};

}