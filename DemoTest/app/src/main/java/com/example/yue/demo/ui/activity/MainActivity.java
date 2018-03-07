package com.example.yue.demo.ui.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yue.demo.R;
import com.example.yue.demo.adapter.FragmentAdapter;
import com.example.yue.demo.adapter.ItemAdapter;
import com.example.yue.demo.data.Cate;
import com.example.yue.demo.data.Hotel;
import com.example.yue.demo.data.View;
import com.example.yue.demo.ui.fragment.FragmentCate;
import com.example.yue.demo.ui.fragment.FragmentHotel;
import com.example.yue.demo.ui.fragment.FragmentView;
import com.example.yue.demo.util.SpaceItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity<T extends RealmObject> extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public static final String TAG = "main";
    private ViewPager mViewPager;

    private TabLayout mTabLayout;
    private FragmentAdapter mFragmentAdapter;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private FragmentCate mFragmentCate;
    private FragmentHotel mFragmentHotel;
    private FragmentView mFragmentView;
    private List<Fragment> mFragments;
    private List<Cate> mCateList;
    private List<Hotel> mHotelList;
    private List<View> mViewList;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        initView();
        initData();
        if (isOPen(getApplicationContext())){

        }else {
            Toast.makeText(getApplicationContext(),"请打开定位",Toast.LENGTH_SHORT).show();

        }
    }

    private void initView(){

        mToolbar = findViewById(R.id.main_toolbar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);

        mFragmentCate = new FragmentCate();
        mFragmentHotel = new FragmentHotel();
        mFragmentView = new FragmentView();
        mFragments = new ArrayList<>();
        mFragments.add(mFragmentCate);
        mFragments.add(mFragmentHotel);
        mFragments.add(mFragmentView);

        mViewPager = findViewById(R.id.main_pager);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mFragmentAdapter);

        mRecyclerView = findViewById(R.id.main_recycle);

        mTabLayout = findViewById(R.id.main_tab);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        mManager = new LinearLayoutManager(getApplicationContext());
        mRealm = Realm.getDefaultInstance();
        mRecyclerView.setLayoutManager(mManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        final MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView mSearchView = (SearchView) item.getActionView();

        mSearchView.setQueryHint("请输入要查找的内容");

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        mSearchView.setOnSearchClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<RealmObject> newList = new ArrayList<>();
                mSearchView.setIconified(false);
                mCateList = mRealm.where(Cate.class)
                        .contains("name",query).findAll();
                mHotelList = mRealm.where(Hotel.class).contains("name",query).findAll();
                mViewList = mRealm.where(View.class).contains("name",query).findAll();
                newList.addAll(mCateList);
                newList.addAll(mHotelList);
                newList.addAll(mViewList);
                if (!query.isEmpty() ) {
                    List<RealmObject> list = new ArrayList<>();
                    for (RealmObject r : newList){
                        if (!list.contains(r)) {
                            list.add(r);
                        }
                    }
                    if (!list.isEmpty()) {
                        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
                        Log.i(TAG, "onQueryTextSubmit: " + list);
                        mRecyclerView.setAdapter(itemAdapter);
                        removeViewPager();
                    }else {
                        recoverView();
                    }
                }else {
                    recoverView();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<RealmObject> newList = new ArrayList<>();
                mSearchView.setIconified(false);
                mCateList = mRealm.where(Cate.class)
                        .contains("name",newText).findAll();
                mHotelList = mRealm.where(Hotel.class).contains("name",newText).findAll();
                mViewList = mRealm.where(View.class).contains("name",newText).findAll();
                newList.addAll(mCateList);
                newList.addAll(mHotelList);
                newList.addAll(mViewList);
                if (!newText.isEmpty() ) {
                    List<RealmObject> list = new ArrayList<>();
                    for (RealmObject r : newList){
                        if (!list.contains(r)) {
                            list.add(r);
                        }
                    }
                    if (!list.isEmpty()) {
                        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
                        Log.i(TAG, "onQueryTextSubmit: " + list);
                        mRecyclerView.setAdapter(itemAdapter);
                        removeViewPager();
                    }else {
                        recoverView();
                    }
                }else {
                    recoverView();
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void removeViewPager(){
        mViewPager.setVisibility(android.view.View.GONE);
        mRecyclerView.setVisibility(android.view.View.VISIBLE);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
    }

    private void recoverView(){
        mViewPager.setVisibility(android.view.View.VISIBLE);
        mRecyclerView.setVisibility(android.view.View.GONE);
    }

    public static boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        } else {
            Toast.makeText(context,"请打开定位!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void initData(){

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("main.realm")
                .build();
        Realm.setDefaultConfiguration(config);


        mRealm = Realm.getDefaultInstance();
        RealmResults<Cate> cates = mRealm.where(Cate.class).findAll();
        RealmResults<Hotel> hotels = mRealm.where(Hotel.class).findAll();
        RealmResults<View> views = mRealm.where(View.class).findAll();
        if (cates.isEmpty()||hotels.isEmpty()||views.isEmpty()) {
            final String data = getData("data.json",getApplicationContext());
            Log.i(TAG, "initData: "+data);
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    jsonToRealm(data,realm);

                }
            });
        }
    }

//        把json存入realm

    private void jsonToRealm(String data,Realm realm){
        try {
            mCateList = new ArrayList<>();
            mHotelList = new ArrayList<>();
            mViewList = new ArrayList<>();
            JSONObject object = new JSONObject(data);
            JSONArray cateArray = object.getJSONArray("cate");
            for (int i = 0; i < 7; i++) {
                Cate cate = realm.createObject(Cate.class);
                JSONObject cateObj = cateArray.getJSONObject(i);
                cate.setName(cateObj.getString("name"));
                cate.setImage(cateObj.getString("image"));
                cate.setIntro(cateObj.getString("intro"));
                mCateList.add(cate);
            }
            
            JSONArray hotelArray = object.getJSONArray("hotel");
            for (int i = 0; i < 7; i++) {
                Hotel hotel = realm.createObject(Hotel.class);
                JSONObject hotelObj = hotelArray.getJSONObject(i);
                hotel.setName(hotelObj.getString("name"));
                hotel.setIntro(hotelObj.getString("intro"));
                hotel.setImage(hotelObj.getString("image"));
                mHotelList.add(hotel);
            }
            JSONArray viewArray = object.getJSONArray("view");
            for (int i = 0; i < 7; i++) {
                View view = realm.createObject(View.class);
                JSONObject viewObj = viewArray.getJSONObject(i);
                view.setName(viewObj.getString("name"));
                view.setImage(viewObj.getString("image"));
                view.setStrategy(viewObj.getString("strategy"));
                view.setRoute(viewObj.getString("route"));
                view.setTicket(viewObj.getString("ticket"));
                view.setIntro(viewObj.getString("intro"));
                mViewList.add(view);
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }



    private String getData(String fileName,Context context){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            AssetManager manager = context.getAssets();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(manager.open(fileName)));
            String line ;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }



    @Override
    public void onPageScrollStateChanged(int arg0){

    }

    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2){

    }

    @Override
    public void onPageSelected(int arg0){

    }

    @Override
    public void onDestroy(){
        mRealm.close();
        super.onDestroy();
    }
}
