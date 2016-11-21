package com.hormiga6.androidtesttoolpractice;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.Assert.assertThat;

/**
 * Created by kotaro.arimura on 2016/11/21.
 */

public class MockeServerTest {

    @Test
    public void testMockServer() throws IOException, InterruptedException {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("Hello world!"));
        server.enqueue(new MockResponse().setBody("sup, bra?"));
        server.enqueue(new MockResponse().setBody("yo dog"));

        server.start();
        HttpUrl baseUrl = server.url("/v1/chat/");

        Client client = new Client(baseUrl.toString());
        client.loadMore();

        assertThat(client.message, is("Hello world!"));

        client.loadMore();
        assertThat(client.message, is("sup, bra?"));

        RecordedRequest request1 = server.takeRequest();
        assertThat(request1.getPath(), is("/v1/chat/0"));

        RecordedRequest request2 = server.takeRequest();
        assertThat(request2.getPath(), is("/v1/chat/1"));

        server.shutdown();
    }

    @Ignore
    @Test
    public void testChat() throws IOException {
        Client client = new Client("http://www.d8aspring.com");
        client.loadMore();
        assertThat(client.message, is(any(String.class)));
    }

    static class Client {
        private String message;
        private String url;
        private int index;

        Client(String url) {
            this.url = url;
        }

        void loadMore() throws IOException {
            URL url = new URL(this.url + index);
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    StringBuffer buf = new StringBuffer();
                    String str = null;
                    while ((str = bufferedReader.readLine()) != null) {
                        buf.append(str);
                    }
                    message = buf.toString();
                }
            } catch (IOException e) {
                throw e;
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                this.index++;
            }
        }
    }
}
