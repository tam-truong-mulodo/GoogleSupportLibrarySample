package com.sample.mysamples;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sample.mysamples.app.AppController;
import com.sample.mysamples.entity.User;
import com.sample.mysamples.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by truong.tam on 15/06/19.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mUserId;
    private EditText mPass;
    private Button mBtnLogin;

    private UserModel mUserModel;
    private String mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_use_support_library);
        getSupportActionBar().hide();
        mUserModel = new UserModel(getApplicationContext());
        findViews(); //viewの読み込み

        List<User> users = mUserModel.findAll();
        outputLog(users);
    }

    /**
     * Viewの読み込み
     */
    private void findViews() {
        mUserId = (EditText) findViewById(R.id.user_id);  //user ID
        mPass = (EditText) findViewById(R.id.password);   //password
        mBtnLogin = (Button) findViewById(R.id.btnLogin); //Login button
        // ボタンのイベントリスナーを設定する
        mBtnLogin.setOnClickListener(this);
    }

    /**
     * ボタンクリックのイベントリスナー。
     * ボタンが押された時のアクションを実行する。
     *
     * @param view 押されたボタンのViwe
     */
    public void onClick(View view) {
        if (view == mBtnLogin) {
            //call login api
            requestLoginApi();
        }
    }

    private void outputLog(List<User> users) {
        if (users!=null) {
            for(User user : users) {
                Log.d(TAG, user.toString());
            }
        }
    }

    /**
     * リクエスト処理
     */
    private void requestLoginApi() {
        String tagJsonObj = getResources().getString(R.string.json_obj_req);
        String url = getResources().getString(R.string.api_login_url);
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(getResources().getString(R.string.loading_message));
        pDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                    boolean result = false;
                    pDialog.dismiss();
                    try {
                        result = response.getBoolean("response");
                        mUid = response.getString("uid");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    List<User> users = mUserModel.findUser(mUid);
                    if (users.size() > 0) {
                        //set loginFlag to true
                    } else {
                        new Thread(new DbAccessRunnable(LoginActivity.this)).start();
                    }
                    outputLog(users);
                    if (result) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    pDialog.dismiss();
                    if (error instanceof NetworkError) {
                    } else if (error instanceof ServerError) {
                    } else if (error instanceof AuthFailureError) {
                    } else if (error instanceof ParseError) {
                    } else if (error instanceof NoConnectionError) {
                    } else if (error instanceof TimeoutError) {
                    }
                }
            }
        );
        AppController.getInstance().addToRequestQueue(jsonObjReq, tagJsonObj);
    }

    private static class DbAccessRunnable implements Runnable {

        private final WeakReference<LoginActivity> mActivity;

        public DbAccessRunnable(LoginActivity activity) {
            this.mActivity = new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void run() {
            LoginActivity activity = mActivity.get();
            if (activity == null) {
                return;
            }
            User addUser = new User(activity.getUid(), activity.getInputUserId(), activity.getInputPass(), true);
            activity.getUserModel().save(addUser);
        }
    }

    private UserModel getUserModel() {
        return mUserModel;
    }

    private String getUid() {
        return mUid;
    }

    private String getInputUserId() {
        return mUserId.getText().toString();
    }

    private String getInputPass() {
        return mPass.getText().toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            String tagJsonObj = getResources().getString(R.string.json_obj_req);
            AppController.getInstance().cancelPendingRequests(tagJsonObj);
        }
        return super.onKeyDown(keyCode, event);
    }
}