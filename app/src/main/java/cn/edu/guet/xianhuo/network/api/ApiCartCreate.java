package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 服务器接口: 添加到购物车.
 */
public class ApiCartCreate implements ApiInterface {

    private Req mReq;

    public ApiCartCreate(int goodsId, int number) {
        mReq = new Req();
        mReq.mId = goodsId;
        mReq.mNumber = number;
    }

    @NonNull
    @Override public String getPath() {
        return ApiPath.CART_CREATE;
    }

    @Nullable
    @Override public RequestParam getRequestParam() {
        return mReq;
    }

    @NonNull @Override public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @SerializedName("goods_id") private int mId;
        @SerializedName("number") private int mNumber;
        @SerializedName("spec") private List<Integer> mSpecs;
        @Override protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
    }

}
