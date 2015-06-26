package com.sample.mysamples.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sample.mysamples.data.entity.ListData;
import com.sample.mysamples.view.adapter.MyBaseAdapter;
import com.sample.mysamples.R;
import com.sample.mysamples.model.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by truong.tam on 15/06/18.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<ListData> myList = new ArrayList<ListData>();
    private ListView mlvDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        getSupportActionBar().hide();   //ActionBarを隠す

        //Listにデータを格納し、画面に表示する
        getDataInList();

    }

    /**
     * リクエスト処理
     */
    private void getDataInList() {

        String tag_json_obj = getResources().getString(R.string.json_obj_req);
        String url = getResources().getString(R.string.api_list_url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // JSONObjectのパース、ListViewへの追加
                        Log.d(TAG, response.toString());
                        try {
                            // Json読み込み
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonOneRecord = jsonArray.getJSONObject(i);
                                String index = jsonOneRecord.getString("index");
                                String message = jsonOneRecord.getString("message");
                                String url = jsonOneRecord.getString("url");
                                Log.d(TAG, "index : " + index + ", message : " + message + ", url : " + url);

                                // Create a new object for each list item
                                ListData ld = new ListData();
                                ld.setImgUrl(url);
                                ld.setMessage(message);

                                // Add this object into the ArrayList myList
                                myList.add(ld);

                            }
                            //表示
                            getView();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error: " + error.getMessage());
                        if (error instanceof NetworkError) {
                            Log.d(TAG, "Error: NetworkError");
                        } else if (error instanceof ServerError) {
                            Log.d(TAG, "Error: ServerError");
                        } else if (error instanceof AuthFailureError) {
                            Log.d(TAG, "Error: AuthFailureError");
                        } else if (error instanceof ParseError) {
                            Log.d(TAG, "Error: ParseError");
                        } else if (error instanceof NoConnectionError) {
                            Log.d(TAG, "Error: NoConnectionError");
                        } else if (error instanceof TimeoutError) {
                            Log.d(TAG, "Error: TimeoutError");
                        } else {
                            Log.d(TAG, "Error: Other");
                        }
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    /**
     * 表示
     */
    private void getView() {
        Log.d(TAG, "getView()");
        mlvDetail = (ListView) findViewById(R.id.lvCustomList);

        MyBaseAdapter adapter = new MyBaseAdapter(this, myList);
        mlvDetail.setAdapter(adapter);
    }
}
