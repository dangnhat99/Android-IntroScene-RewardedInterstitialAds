package android.dmnhat.introrewardads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.dmnhat.introscenerewardad.IntroSceneRewardedAdsFragment;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_introscene).setOnClickListener(v -> {
            //show intro scene
            IntroSceneRewardedAdsFragment introSceneRewardedAdsFragment = new IntroSceneRewardedAdsFragment(new IntroSceneRewardedAdsFragment.IntroSceneCallback() {
                @Override
                public void onAdDismiss() {
                    Log.d("TAG", "onAdDismiss: ");
                }

                @Override
                public void onCountDownFinish() {
                    //TODO show rewarded interstitial ads
                }
            });

            introSceneRewardedAdsFragment.setDrawable(ContextCompat.getDrawable(this,R.drawable.dota2_social));
            introSceneRewardedAdsFragment.setTextTitle("Watch this video to earn more coin");
            introSceneRewardedAdsFragment.setTimeCountDown(10); //time in second

            introSceneRewardedAdsFragment.show(getSupportFragmentManager(), "YOUR_TAG");
        });
    }
}