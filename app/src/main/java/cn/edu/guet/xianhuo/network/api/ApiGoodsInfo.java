package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.GoodsInfo;

/**
 * 获取商品详情.
 */
public class ApiGoodsInfo implements ApiInterface {

    private final Req mReq;

    public ApiGoodsInfo(int goodsId) {
        mReq = new Req();
        mReq.mGoodsId = goodsId;
    }

    @NonNull
    @Override public String getPath() {
        return ApiPath.GOODS;
    }

    @Nullable
    @Override public RequestParam getRequestParam() {
        return mReq;
    }

    @NonNull @Override public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    private static class Req extends RequestParam {
        @SerializedName("goods_id")
        private int mGoodsId;
        @Override protected int sessionUsage() {
            return SESSION_OPTIONAL;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("data")
        private GoodsInfo mData;
        public GoodsInfo getData() {
            return mData;
        }
    }

}
