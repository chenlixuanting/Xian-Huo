package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.CartBill;
import cn.edu.guet.xianhuo.network.entity.CartGoods;

/**
 * 服务器接口: 购物车列表.
 */
public class ApiCartList implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.CART_LIST;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return new Req();
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {

        @SerializedName("data")
        Data mData;

        public Data getData() {
            return mData;
        }

        public static class Data {
            @SerializedName("goods_list")
            private List<CartGoods> mGoodsList;
            @SerializedName("total")
            private CartBill mCartBill;

            public List<CartGoods> getGoodsList() {
                return mGoodsList;
            }

            public CartBill getCartBill() {
                return mCartBill;
            }
        }
    }

}
