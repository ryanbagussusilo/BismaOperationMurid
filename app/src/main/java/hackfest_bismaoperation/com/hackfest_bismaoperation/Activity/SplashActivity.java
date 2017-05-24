package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;

public class SplashActivity extends AppCompatActivity {
    private SessionManager sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessions = new SessionManager(this);


        View easySplashScreenView = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
               // .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.white)
                .withHeaderText("")
                .withFooterText("©Bisma Operation 2017")
                .withBeforeLogoText("")
                .withLogo(R.drawable.bisma)
                .withAfterLogoText("Mari Cari Guru Les Privat Terbaik")
                .create();

        setContentView(easySplashScreenView);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (!sessions.isLoggedIn()) {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this, ListGuruActivity.class);
                    startActivity(i);
                }

            }
        }, 0);
    }
}
