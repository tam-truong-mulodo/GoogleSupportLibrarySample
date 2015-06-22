package com.sample.mysamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by truong.tam on 15/06/19.
 */
public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userId;
    private EditText pass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_use_support_library);

        findViews(); //viewの読み込み
    }

    /**
     * Viewの読み込み
     */
    private void findViews() {
        userId = (EditText)findViewById(R.id.user_id);  //user ID
        pass = (EditText)findViewById(R.id.password);   //password
        btnLogin = (Button)findViewById(R.id.btnLogin); //Login button

        // ボタンのイベントリスナーを設定する
        btnLogin.setOnClickListener(this);
    }

    /**
     * ボタンクリックのイベントリスナー。
     * ボタンが押された時のアクションを実行する。
     * @param view 押されたボタンのViwe
     */
    public void onClick( View view ) {
        if (view == btnLogin) {


            //[todo]
            //
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }
}
