package com.outbrain.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class WriteTest {



    public static void main(String[] args) {

        String url = "https://www.heise.de/tp/news-atom.xml";
        try {
            try (CloseableHttpClient client = HttpClients.createMinimal()) {
                HttpUriRequest request = new HttpGet(url);
                try (CloseableHttpResponse response = client.execute(request);
                     InputStream stream = response.getEntity().getContent()) {
                    SyndFeedInput input = new SyndFeedInput();
                    SyndFeed feed = null;
                    try {
                        feed = input.build(new XmlReader(stream));
                    } catch (FeedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(feed.getTitle());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
