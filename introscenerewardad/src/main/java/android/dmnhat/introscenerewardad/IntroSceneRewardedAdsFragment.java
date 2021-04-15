package android.dmnhat.introscenerewardad;

import android.dmnhat.introscenerewardad.databinding.IntroSceneRewardedAdsFragmentBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class IntroSceneRewardedAdsFragment extends DialogFragment {
    private IntroSceneCallback callback;
    private IntroSceneRewardedAdsFragmentBinding binding;
    private CountDownTimer timer;

    private Drawable drawable;
    private String textTitle, textSkipAds, textVideoIn;
    private int timeCountDown = -1;

    public IntroSceneRewardedAdsFragment(IntroSceneCallback callback) {
        this.callback = callback;
        this.setCancelable(false);
    }

    public void setCallback(IntroSceneCallback callback) {
        this.callback = callback;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public void setTextSkipAds(String textSkipAds) {
        this.textSkipAds = textSkipAds;
    }

    public void setTextVideoIn(String textVideoIn) {
        this.textVideoIn = textVideoIn;
    }

    public void setTimeCountDown(int timeCountDown) {
        this.timeCountDown = timeCountDown;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.intro_scene_rewarded_ads_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = IntroSceneRewardedAdsFragmentBinding.bind(view);

        initView();
        initListener();
    }

    private void initView() {
        if (drawable != null) binding.image.setImageDrawable(drawable);
        if (textTitle != null) binding.txtTitle.setText(textTitle);
        if (textSkipAds != null) binding.btnSkipAd.setText(textSkipAds);
        if (textVideoIn != null) binding.txtVideoStart.setText(textVideoIn);

        timer = new CountDownTimer(timeCountDown != -1 && timeCountDown > 1 ? timeCountDown * 1000 : 5000, 1000) {
            @Override
            public void onTick(long l) {
                binding.txtCountDown.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                callback.onCountDownFinish();
                dismiss();
            }
        }.start();
    }

    private void initListener() {
        binding.btnSkipAd.setOnClickListener(v -> {
            if (timer != null) timer.cancel();

            callback.onAdDismiss();
            dismiss();
        });
    }

    public interface IntroSceneCallback {
        void onAdDismiss();

        void onCountDownFinish();
    }
}
