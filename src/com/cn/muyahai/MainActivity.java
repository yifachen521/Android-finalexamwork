package com.cn.muyahai;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static Handler handler=new Handler();
	private Button button;
	private Button btn_back;
	List<Map<String, Object>> data;
	ListView lsiView;
	String length;
	Context context;
	List<News> newes = null;
	MyAdapter myAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.btn_right);
		btn_back = (Button)findViewById(R.id.button1);
		btn_back.setOnClickListener(mGoBack);
		button.setOnCreateContextMenuListener(this);
        lsiView = (ListView)this.findViewById(R.id.listView1);
		getLastNews();
        myAdapter = new MyAdapter(this);
        
			handler = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					Log.i("infor","3");
					
					lsiView.setAdapter(myAdapter);
				}
				
			};
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
				Intent intent = new Intent(MainActivity.this,ShowActivity.class);
				startActivity(intent);
				break;
			case R.id.action_2:
				Intent intent2 = new Intent(MainActivity.this,IndexActivity.class);
				Bundle bundle =new Bundle();
				bundle.putString("cname", "101181601");
				intent2.putExtras(bundle);
				startActivity(intent2);
				break;
			case R.id.action_3:
				Intent intent3 = new Intent(MainActivity.this,QueryActivity.class);
				startActivity(intent3);
				break;
			case R.id.action_4:
				Intent intent4 = new Intent(MainActivity.this,MainActivity.class);
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
	    class MyAdapter extends BaseAdapter{
	    	Context context;
	    	LayoutInflater inflater;
	    	public MyAdapter(Context context) {
	    		this.context=context;
	    		inflater = LayoutInflater.from(context);
	    	}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				if(newes.size() == 0){
					Log.i("getcount","0");
				}
				return newes.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override

			public View getView(int position, View convertView, ViewGroup parent) {
				News news = newes.get(position);
				ViewHolder viewHolder = null;
				if (convertView == null) {
					convertView = inflater.inflate(R.layout.item, null);
					viewHolder = new ViewHolder();
					viewHolder.sImageView = (SmartImageView)convertView.findViewById(R.id.sv_1);
					viewHolder.nameView = (TextView)convertView.findViewById(R.id.name);
					viewHolder.stateView = (TextView)convertView.findViewById(R.id.state);
					viewHolder.windirView = (TextView)convertView.findViewById(R.id.windir);
					viewHolder.winpowerView = (TextView)convertView.findViewById(R.id.winpower);
					viewHolder.nowtempView = (TextView)convertView.findViewById(R.id.nowtemp);
					convertView.setTag(viewHolder);
				} else {
					viewHolder = (ViewHolder) convertView.getTag();
				}
	 
				viewHolder.sImageView.setImageUrl(news.getImageurl());
				viewHolder.nameView.setText(news.getCityname());
				viewHolder.stateView.setText(news.getState());
				viewHolder.winpowerView.setText(news.getWinpower());
				viewHolder.windirView.setText(news.getWindir());
				viewHolder.nowtempView.setText(news.getNowtemp()+"℃");
				//返回填充的布局item_listview
				return convertView;
			}
			class ViewHolder{
				TextView nameView;
				TextView stateView;
				TextView winpowerView;
				TextView windirView;
				TextView nowtempView;
				SmartImageView sImageView;
			}
	    	
	    }
	    private void getLastNews() {
			// TODO Auto-generated method stub
	    	Thread thread = new Thread() {
	    		@Override
	    		public void run() {
					// TODO Auto-generated method stub
	    			super.run();
    				String  path = "http://10.131.177.174:8080/SaveInformation/ServletForXML";
	    			try {
	    				HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
	    				conn.setConnectTimeout(5000);
	    				conn.setRequestMethod("GET");
	    				if(conn.getResponseCode() == 200) {
	    					Log.i("informatioon", "1");
	    					InputStream xml = conn.getInputStream();
	    					parseXML(xml);
	    				}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
	    	};
	    	thread.start();
		}
		private void parseXML(InputStream xml) {
			try {

				// TODO Auto-generated method stub
				News news = null ;
				XmlPullParser pullParser = Xml.newPullParser();
				pullParser.setInput(xml,"UTF-8");
				int event = pullParser.getEventType();
				while (event !=XmlPullParser.END_DOCUMENT) {
					switch (event) {
					case XmlPullParser.START_DOCUMENT:
						newes = new ArrayList<News>();
						break;
					case XmlPullParser.START_TAG:
						if("news".equals(pullParser.getName())) {
							String cityname = pullParser.getAttributeValue(0);
							news = new News();
							news.setCityname(cityname);
						}
							Log.i("informatioon", "2");
							
							if ("state".equals(pullParser.getName())) {
								news.setState(pullParser.nextText());
							}
							if ("mintemp".equals(pullParser.getName())) {
								news.setMintemp(pullParser.nextText());
							}
							if ("maxtemp".equals(pullParser.getName())) {
								news.setMaxtemp(pullParser.nextText());
							}
							if ("nowtemp".equals(pullParser.getName())) {
								news.setNowtemp(pullParser.nextText());
							}
							if ("windir".equals(pullParser.getName())) {
								news.setWindir(pullParser.nextText());
							}
							if ("winpower".equals(pullParser.getName())) {
								news.setWinpower(pullParser.nextText());
							}
							if ("imageurl".equals(pullParser.getName())) {
								news.setImageurl(pullParser.nextText());
							}
							break;
					case XmlPullParser.END_TAG:
						if("news".equals(pullParser.getName())) {
							newes.add(news);
							System.out.println(newes.size());
						}
						break;
						
					}
					event = pullParser.next();
				}
				handler.sendEmptyMessage(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		public OnClickListener mGoBack = new OnClickListener() {

			public void onClick(View v) {
			finish();
			}
			};
	
	}
