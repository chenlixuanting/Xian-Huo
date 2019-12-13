package cn.edu.guet.xianhuo.feature.help;


import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.base.wrapper.ToolbarWrapper;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 帮助页面
 */
public class HelpActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_help;
    }

    @Override
    protected void initView() {
        //设置标题
        new ToolbarWrapper(this).setCustomTitle(R.string.mine_help);
        setWebView();
        webView.loadUrl(ApiPath.HELP);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    /**
     * 设置WebView
     */
    private void setWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

}
