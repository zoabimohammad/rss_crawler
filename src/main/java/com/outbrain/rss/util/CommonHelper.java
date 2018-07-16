package com.outbrain.rss.util;

import com.outbrain.rss.dto.RSSFeedUrlDTO;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonHelper {

    public static List<RSSFeedUrlDTO> convertToDTO(SyndFeed feed){
        List<RSSFeedUrlDTO> posts = new ArrayList<>();
        RSSFeedUrlDTO rssFeedUrlDTO;
        for(SyndEntry item : feed.getEntries()){
            DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            if (item.getPublishedDate() != null) {
                cal.setTimeInMillis(item.getPublishedDate().getTime());
                rssFeedUrlDTO = new RSSFeedUrlDTO(item.getTitle(), item.getLink(),item.getAuthor(),df.format(cal.getTime()));
            }else {
                rssFeedUrlDTO = new RSSFeedUrlDTO(item.getTitle(), item.getLink(),item.getAuthor(),"N/A");
            }
            posts.add(rssFeedUrlDTO);
        }
        return posts;
    }

}
