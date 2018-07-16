package com.outbrain.rss.service;

import com.outbrain.rss.dto.RSSFeedUrlDTO;
import com.outbrain.rss.entity.RSSFeedUrl;

import java.util.List;

public interface RSSFeedCrawlerService extends GenericService<RSSFeedUrl, Long>  {
        List<RSSFeedUrlDTO> crawlUrls(List<String> url);
        List<RSSFeedUrlDTO> crawlUrl(String url);
}
