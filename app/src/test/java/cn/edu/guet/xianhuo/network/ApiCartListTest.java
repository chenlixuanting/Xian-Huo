package cn.edu.guet.xianhuo.network;


import cn.edu.guet.xianhuo.network.api.ApiCartList;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiCartListTest extends ApiCartCreateTest {

    @Test public void getCartList() throws IOException {
        signIn();
        ApiCartList apiCartList = new ApiCartList();

        ApiCartList.Rsp rsp = client.execute(apiCartList);
        Assert.assertTrue(rsp.getStatus().isSucceed());
    }
}
