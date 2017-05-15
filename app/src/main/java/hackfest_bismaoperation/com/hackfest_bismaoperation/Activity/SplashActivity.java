package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View easySplashScreenView = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(LoginActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.white)
                .withHeaderText("")
                .withFooterText("©Bisma Operation 2017")
                .withBeforeLogoText("")
                .withLogo(R.drawable.bisma)
                .withAfterLogoText("Mari Cari Guru Les Privat Terbaik")
                .create();

        setContentView(easySplashScreenView);
    }
}
