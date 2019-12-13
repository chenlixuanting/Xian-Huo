package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.CartBill;

/**
 * 更新购物车
 */
public class ApiCartUpdate implements ApiInterface {

    private Req mReq;

    public ApiCartUpdate(int recId, int newNumber) {
        mReq = new Req();
        mReq.mRecId = recId;
        mReq.mNumber = newNumber;
    }


    @NonNull
    @Override
    public String getPath() {
        return ApiPath.CART_UPDATE;
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
        @SerializedName("new_number")
        private int mNumber;
        @SerializedName("rec_id")
        int mRecId;

        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("total")
        private CartBill mCartBill;

        public CartBill getCartBill() {
            return mCartBill;
        }
    }

}
