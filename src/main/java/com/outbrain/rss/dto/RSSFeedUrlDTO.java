package com.outbrain.rss.dto;

import com.outbrain.rss.entity.RSSFeedUrl;

import java.util.Date;

public class RSSFeedUrlDTO {

    String title;
    String link;
    String author;
    String publishedDate;

    public RSSFeedUrlDTO() { }

    public RSSFeedUrlDTO(String title, String link,
                         String author, String publishedDate) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public static RSSFeedUrl feedPostMapper(RSSFeedUrlDTO rssFeedUrlDTO){
        if (rssFeedUrlDTO != null) {
            String title = rssFeedUrlDTO.getTitle() != null ? rssFeedUrlDTO.getTitle() : "";
            String link = rssFeedUrlDTO.getLink() != null ? rssFeedUrlDTO.getLink() : "";
            String author = rssFeedUrlDTO.getAuthor() != null ? rssFeedUrlDTO.getAuthor() : "";
            String date =  rssFeedUrlDTO.getPublishedDate() != null ? rssFeedUrlDTO.getPublishedDate() : "" ;
            RSSFeedUrl rssFeedUrl = new RSSFeedUrl(title,link,author,date);
            return rssFeedUrl;
        }
        return null;
    }
}
