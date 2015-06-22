package com.sample.mysamples;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by truong.tam on 15/06/18.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Jsonデータを作成
        //CreateJsonDataSample();

        //Jsonデータを取得
        //getJsonData("json");
        //GetJsonDataSample();

//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true); // RecyclerViewのサイズを維持し続ける
////        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        mNavigationView = (NavigationView)findViewById(R.id.navigation);
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                mToolbar.setTitle(menuItem.getTitle());
//                switch (menuItem.getItemId()) {
//                    case R.id.action_text_input_layout:
//                        replaceFragment(new TextInputLayoutFragment());
//                        mDrawerLayout.closeDrawers();
//                        return true;
//                }
//                return false;
//            }
//        });

    }

    /**
     * Jsonデータの読み込み
     */
    private void getJsonData(String str) {

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            inputStream = assetManager.open(str);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String strData = bufferedReader.readLine();

            // JSONObject に変換します
            JSONObject json = new JSONObject(strData);

            // JSONObject を文字列に変換してログ出力します
            Log.d(TAG, json.toString());

            inputStream.close();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Jsonデータの取得
     * Data: sample
     */
    private void GetJsonDataSample() {
        // ファイルの読み込み
        InputStream input;
        try {
            //input = new FileInputStream(Environment.getExternalStorageDirectory() + "/" +  "sample.json");
            input = new FileInputStream("/data/" +  "sample.json");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // Json読み込み
            String json = new String(buffer);
            JSONObject jsonObject = new JSONObject(json);

            // データ追加
            JSONArray jsonArray = jsonObject.getJSONArray("Employee");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonOneRecord = jsonArray.getJSONObject(i);
                Log.d("TAG", jsonOneRecord.getString("Name"));
                Log.d("TAG", String.valueOf(jsonOneRecord.getInt("Age")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Jsonデータの作成
     * Data: sample
     */
    private void CreateJsonDataSample() {

        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            // jsonデータの作成
            JSONObject jsonOneData;
            jsonOneData = new JSONObject();
            jsonOneData.put("Name", "田中　一郎");
            jsonOneData.put("Age", 25);
            jsonArray.put(jsonOneData);

            jsonOneData = new JSONObject();
            jsonOneData.put("Name", "鈴木　次郎");
            jsonOneData.put("Age", 30);
            jsonArray.put(jsonOneData);

            jsonOneData = new JSONObject();
            jsonOneData.put("Name", "斉藤　三郎");
            jsonOneData.put("Age", 46);
            jsonArray.put(jsonOneData);

            jsonOneData = new JSONObject();
            jsonOneData.put("Name", "高橋　花子");
            jsonOneData.put("Age", 35);
            jsonArray.put(jsonOneData);

            jsonObject.put("Employee", jsonArray);

            // jsonファイル出力
            //File file = new File(Environment.getExternalStorageDirectory() + "/" + "sample.json");
            File file = new File("/data/" + "sample.json");
            FileWriter filewriter;

            filewriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(filewriter);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(jsonObject.toString());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
