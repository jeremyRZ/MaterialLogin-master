package cn.fanrunqi.materiallogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.alibaba.fastjson.JSONValidator.Type.Array;

public class MaterialActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_listview);
        post("http://147.8.147.247:6969/api/taskUi/getModuleListByStep","","");
        postAsynHttp();

        //设置Adapter
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:

                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 3) {
                    case 0:
                        return "Structural";
                    case 1:
                        return "Architectural";
                    case 2:
                        return "MEP";
//                    case 3:
//                        return "Divertissement";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://online.norwich.edu/sites/default/files/styles/resource_standard_hero/public/content/resources/header/nu_mce_how_to_become_a_structural_engineer.jpg?itok=QcYYbyeX");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "https://www.exponent.com.pk//eenew/wp-content/uploads/2017/08/structural-engineering-2.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "https://www.peforhire.com/wp-content/uploads/2019/09/structural-engineer-costs-850x567.jpeg");
//                    case 3:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.red,
//                                "https://raw.githubusercontent.com/jeremyRZ/eInStar/master/app/src/main/res/drawable/bg.png");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });


        Toolbar toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }


        //设置setViewPager
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void post(String url,String key,String value){
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add(key,value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                System.out.println("@@@@@@@@");
                //...
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    System.out.println("!!!!!!!!!!!!!!");

                    String result = response.body().string();
                    System.out.println(response.body());
                    //处理UI需要切换到UI线程处理
                }
            }
        });

    }

    public void postAsynHttp() {
        HTTP http = HTTP.builder()
                .baseUrl("http://147.8.147.247:6969/api/taskUiMaterials/getMaterialsList")
                .build();
        http.async("")
                .setOnResponse((HttpResult res) -> {
                    // 得到目标数据
                    HttpResult.Body body = res.getBody();
                    String json = body.toString();
                    JSONObject jsonObject =JSON.parseObject(json);
                    System.out.println("@@@@@@@@");
//                    System.out.println(jsonObject.getString("data"));
//                    jsonObject.getString("data");
                    JSONArray recordMap= JSON.parseArray(jsonObject.getString("data").toString());
                    //取出data中的第一条数据
                    JSONObject jsObj2=JSON.parseObject(recordMap.get(0).toString());
                    System.out.println(jsObj2);
//                    JSONArray json = JSONArray.fromObject(jsonObject);
                    int size = recordMap.size();
                    //   第一种遍历方式
//                    Set setresult = jsonObject.entrySet();
//                    Iterator iterator = setresult.iterator();
//                    while (iterator.hasNext()) {
//                        Object value = iterator.next();
//
//                        System.out.println(value);
//                    }

//                    System.out.println("!!!!!");
//                    System.out.println(jsonObject.get("data"));
//                    System.out.println(jsonObject.getString("data"));
                })

                .get();
    }
    protected ArrayList<String> initData() {
        ArrayList<String> mDatas = new ArrayList<String>();
        for (int i = 0; i < 1; i++) {
            mDatas.add("我是商品" + i);
        }
        return mDatas;
    }




}