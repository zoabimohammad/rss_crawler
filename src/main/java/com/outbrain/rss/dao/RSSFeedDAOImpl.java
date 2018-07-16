package com.outbrain.rss.dao;

import com.outbrain.rss.entity.RSSFeedUrl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class RSSFeedDAOImpl extends GenericDAOImpl<RSSFeedUrl, Long> implements RSSFeedDAO {
}
