package cn.edu.guet.xianhuo.base;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.guet.xianhuo.network.XHClient;
import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.core.UiCallback;
import cn.edu.guet.xianhuo.network.event.UserEvent;
import okhttp3.Call;

/**
 * 通用Activity基类.
 */
public abstract class BaseActivity extends TransitionActivity {

    private Unbinder mUnbind;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        mUnbind = ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        XHClient.getInstance().cancelByTag(getClass().getSimpleName());
        EventBus.getDefault().unregister(this);
        mUnbind.unbind();
        mUnbind = null;
    }

    /**
     * 异步请求
     *
     * @param apiInterface
     * @return
     */
    protected Call enqueue(final ApiInterface apiInterface) {
        UiCallback uiCallback = new UiCallback() {
            @Override
            public void onBusinessResponse(boolean success, ResponseEntity responseEntity) {
                BaseActivity.this.onBusinessResponse(apiInterface.getPath(), success, responseEntity);
            }
        };
        return XHClient.getInstance().enqueue(apiInterface, uiCallback, getClass().getSimpleName());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(UserEvent event) {
    }

    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

    protected abstract void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp);

}
