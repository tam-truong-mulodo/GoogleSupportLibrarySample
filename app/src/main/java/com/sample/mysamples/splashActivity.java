package com.sample.mysamples;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by truong.tam on 15/06/18.
 */
public class splashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // タイトルを非表示にします。
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // splash.xmlをViewに指定します。
        setContentView(R.layout.splash);

        Handler hdl = new Handler();
        // 1s遅延させてsplashHandlerを実行します。
        hdl.postDelayed(new splashHandler(), 1 * 1000);

    }

    class splashHandler implements Runnable {
        public void run() {
            // スプラッシュ完了後に実行するActivityを指定します。
            Intent intent = new Intent(getApplication(), loginActivity.class);
            startActivity(intent);
            // SplashActivityを終了させます。
            splashActivity.this.finish();
        }
    }

    /**
     * 戻るボタンでアプリ終了.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            splashActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
