package cn.edu.guet.xianhuo.feature;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.widget.ImageView;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * <p> App入口 - 启动页.
 * <p> 开始2秒的渐变动画, 然后跳转到主页面.
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.image_splash)
    ImageView ivSplash;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        // 渐变动画
        startAnimate();
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    /**
     * 跳转到主页面
     */
    private void toMainActivity() {
        Intent intent = new Intent(this, XHMainActivity.class);
        startActivity(intent);
        finishWithDefaultTransition();
    }

    /** 启动动画*/
    private void startAnimate() {
        ivSplash.setAlpha(0.3f);
        ivSplash.animate()
                .alpha(1.0f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        toMainActivity();
                    }
                })
                .start();
    }

}
