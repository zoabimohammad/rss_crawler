package com.outbrain.rss.service;

import com.outbrain.rss.dao.RSSFeedDAO;
import com.outbrain.rss.dto.RSSFeedUrlDTO;
import com.outbrain.rss.entity.RSSFeedUrl;
import com.outbrain.rss.util.CommonHelper;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class RSSFeedCrawlerServiceImpl extends GenericServiceImpl<RSSFeedUrl,
        Long> implements RSSFeedCrawlerService {

    @Autowired
    RSSFeedDAO rssFeedDAO;

    @Override
    public List<RSSFeedUrlDTO> crawlUrls(List<String> urls) {
        final List<RSSFeedUrlDTO> rssPostsToBeShown =
                urls.parallelStream()
                        .flatMap(u -> crawlUrl(u.toString()).stream())
                        .collect(toList());

        List<RSSFeedUrl> rssPostsToBeSaved = rssPostsToBeShown.stream().map
                (post ->
                        RSSFeedUrlDTO.feedPostMapper(post)).collect
                (Collectors.toList
                ());
        rssFeedDAO.add(rssPostsToBeSaved);
        return rssPostsToBeShown;
    }

    @Override
    public List<RSSFeedUrlDTO> crawlUrl(String url) {
        SyndFeed feed = null;
        try {
            try (CloseableHttpClient client = HttpClients.createMinimal()) {
                HttpUriRequest request = new HttpGet(url);
                try (CloseableHttpResponse response = client.execute(request);
                     InputStream stream = response.getEntity().getContent()) {
                    SyndFeedInput input = new SyndFeedInput();
                    feed = input.build(new XmlReader(stream));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
        return CommonHelper.convertToDTO(feed);
    }
}
