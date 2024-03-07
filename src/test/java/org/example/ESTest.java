package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ESTest {
    static final String INDEX_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"info\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"email\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"name\": {\n" +
            "        \"type\": \"object\",\n" +
            "        \"properties\": {\n" +
            "          \"firstName\": {\n" +
            "            \"type\": \"keyword\"\n" +
            "          },\n" +
            "          \"lastName\": {\n" +
            "            \"type\": \"keyword\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
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

    @Test
    public void addIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("itheima");
        request.source(INDEX_TEMPLATE, XContentType.JSON);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    @Test
    public void hasIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("itheima");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void rmIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("itheima");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    public void addDocument() throws IOException {
        IndexRequest request = new IndexRequest("itheima").id("1").source("{\n" +
                "    \"info\": \"黑马程序员Java讲师\",\n" +
                "    \"email\": \"zy@itcast.cn\",\n" +
                "    \"name\": {\n" +
                "        \"firstName'\": \"云\",\n" +
                "        \"lastName\": \"赵\"\n" +
                "    }\n" +
                "}", XContentType.JSON);
        client.index(request,RequestOptions.DEFAULT);
    }

    @Test
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest("itheima").id("3");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    @Test
    public void updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("itheima", "1").doc("{\"email\":\"zhangsan@itcast.cn\"}", XContentType.JSON);
        client.update(request,RequestOptions.DEFAULT);
    }

    @Test
    public void rmDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("itheima", "1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.getId());
    }

    @Test
    public void bulkDocument() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("itheima").id("2").source("{\"info\":\"李四\",\"email\":\"itheima2\"}", XContentType.JSON));
        request.add(new IndexRequest("itheima").id("3").source("{\"info\":\"王五\",\"email\":\"itheima3\"}", XContentType.JSON));
        client.bulk(request,RequestOptions.DEFAULT);
    }
}
