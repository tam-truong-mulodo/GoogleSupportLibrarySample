package com.sample.mysamples;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;


/**
 * Created by truong.tam on 15/06/19.
 */
public class loginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_use_support_library);

//        //hintテキストはEditTextのものを引っ張る
//        TextInputLayout userId = (TextInputLayout)findViewById(R.id.user_id);
//        TextInputLayout pass = (TextInputLayout)findViewById(R.id.password);
//
//        //setErrorEnabledをセットしておくと、エラー表示の部分のスペースが予め確保される
//        // false指定の場合はsetErrorしたときにエラーが追加される
//        userId.setErrorEnabled(true);
//        pass.setErrorEnabled(true);
//
//        //エラー文言のセット
//        pass.setError("Error !!");
    }
}
