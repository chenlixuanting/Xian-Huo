package cn.edu.guet.xianhuo.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
 * 通用Fragment基类.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbind;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewLayout(), container, false);
        mUnbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    public final void onDestroyView() {
        super.onDestroyView();
        XHClient.getInstance().cancelByTag(getClass().getSimpleName());
        EventBus.getDefault().unregister(this);
        mUnbind.unbind();
        mUnbind = null;
    }

    /**
     * 执行异步请求
     * @param apiInterface
     * @return
     */
    protected final Call enqueue(final ApiInterface apiInterface) {
        UiCallback uiCallback = new UiCallback() {
            @Override
            public void onBusinessResponse(boolean success, ResponseEntity responseEntity) {
                BaseFragment.this.onBusinessResponse(apiInterface.getPath(), success, responseEntity);
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
