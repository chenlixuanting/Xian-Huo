package cn.edu.guet.xianhuo.network.core;

import androidx.annotation.NonNull;

import java.util.List;

import cn.edu.guet.xianhuo.network.entity.Address;
import cn.edu.guet.xianhuo.network.entity.CartBill;
import cn.edu.guet.xianhuo.network.entity.CartGoods;
import cn.edu.guet.xianhuo.network.entity.Session;
import cn.edu.guet.xianhuo.network.entity.User;

public interface IUserManager {

    // --------账号管理-------- //
    void setUser(@NonNull User user, @NonNull Session session);

    void retrieveUserInfo();

    void clear();

    boolean hasUser();

    Session getSession();

    User getUser();

    // --------购物车管理-------- //
    void retrieveCartList();

    boolean hasCart();

    List<CartGoods> getCartGoodsList();

    CartBill getCartBill();

    // --------收件地址管理-------- //
    void retrieveAddressList();

    boolean hasAddress();

    List<Address> getAddressList();

    Address getDefaultAddress();

}
