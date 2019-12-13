package cn.edu.guet.xianhuo.network;


import cn.edu.guet.xianhuo.network.api.ApiSearch;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiSearchTest extends ApiTest {

    @Test public void searchGoods() throws IOException {

        ApiSearch apiSearch = new ApiSearch(null, null);
        ApiSearch.Rsp rsp = client.execute(apiSearch);
        Assert.assertTrue(rsp.getStatus().isSucceed());
    }
}
