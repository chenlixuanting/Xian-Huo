package cn.edu.guet.xianhuo.network;


import cn.edu.guet.xianhuo.network.api.ApiAddressList;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiAddressListTest extends ApiSignInTest {

    @Test public void getAddressList() throws IOException {
        signIn();
        ApiAddressList apiAddressList = new ApiAddressList();
        ApiAddressList.Rsp rsp = client.execute(apiAddressList);
        Assert.assertTrue(rsp.getStatus().isSucceed());
    }
}
