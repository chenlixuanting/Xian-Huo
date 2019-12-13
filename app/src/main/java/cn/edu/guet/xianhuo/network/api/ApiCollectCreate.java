package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 收藏商品
 */
public class ApiCollectCreate implements ApiInterface {

    private final Req mReq;

    public ApiCollectCreate(int goodsId) {
        mReq = new Req();
        mReq.mGoodsId = goodsId;
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.COLLECT_CREATE;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return mReq;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @SerializedName("goods_id")
        private int mGoodsId;

        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
    }

}
