package com.outbrain.rss.controller;

import com.outbrain.rss.dto.RSSFeedUrlDTO;
import com.outbrain.rss.service.RSSFeedCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("rss_feed")
public class RSSFeedController {

    @Autowired
    RSSFeedCrawlerService feedCrawlerService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RSSFeedUrlDTO>> crawlUrls(@RequestBody List<String> urls){
        try {
            List<RSSFeedUrlDTO> crawledUrls = feedCrawlerService.crawlUrls(urls);
            if(crawledUrls != null && crawledUrls.size() > 0){
                return new ResponseEntity<>(crawledUrls, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
