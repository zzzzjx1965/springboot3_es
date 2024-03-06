package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ESTest {
    private RestHighLevelClient client;

    @Before
    public void setUp() {
        client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.174.135:9200")));
    }
    @After
    public void tearDown() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //test1
    @Test
    public void test1() {
        System.out.println(client);
    }


}
