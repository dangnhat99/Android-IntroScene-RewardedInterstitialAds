package android.dmnhat.introrewardads;

import android.dmnhat.introscenerewardad.IntroSceneRewardedAdsFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        introSceneRewardedAdsFragment.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.dota2_social));
        introSceneRewardedAdsFragment.setTextTitle("Watch this video to earn more coin");
        introSceneRewardedAdsFragment.setTimeCountDown(10); //time in second

        introSceneRewardedAdsFragment.show(getChildFragmentManager(), "YOUR_TAG");
    }
}