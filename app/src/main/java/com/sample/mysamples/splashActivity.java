package com.sample.mysamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.content.Intent;
import android.os.Handler;

/**
 * Created by truong.tam on 15/06/18.
 */
public class splashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // タイトルを非表示にします。
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // splash.xmlをViewに指定します。
        setContentView(R.layout.splash);

        Handler hdl = new Handler();
        // 3s遅延させてsplashHandlerを実行します。
        hdl.postDelayed(new splashHandler(), 3 * 1000);

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

}
