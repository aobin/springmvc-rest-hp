package com.ernieyu.feedparser.impl;

import com.ernieyu.feedparser.Element;
import com.ernieyu.feedparser.FeedType;
import com.ernieyu.feedparser.FeedUtils;
import org.xml.sax.Attributes;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Item implementation for RSS 1.0 feeds.
 */
class Rss1Item extends BaseItem
{
    // XML elements for RSS items.
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "pubDate";
    private static final String CREATOR = "creator";
    private static final String IDENTIFIER = "identifier";

    /**
     * Constructs an Rss1Item with the specified namespace uri, name and
     * attributes.
     */
    public Rss1Item(String uri, String name, Attributes attributes)
    {
        super(uri, name, attributes);
    }

    public FeedType getType()
    {
        return FeedType.RSS_1_0;
    }

    public String getTitle()
    {
        Element title = getElement(TITLE);
        return (title != null) ? title.getContent() : null;
    }

    public String getLink()
    {
        Element link = getElement(LINK);
        return (link != null) ? link.getContent() : null;
    }

    public String getDescription()
    {
        Element descr = getElement(DESCRIPTION);
        return (descr != null) ? descr.getContent() : null;
    }

    public String getAuthor()
    {
        // Use Dublin Core element.
        Element author = getElement(CREATOR);
        return (author != null) ? author.getContent() : null;
    }

    public String getGuid()
    {
        // Use Dublin Core element.
        Element guid = getElement(IDENTIFIER);
        return (guid != null) ? guid.getContent() : null;
    }

    public Date getPubDate()
    {
        // Use Dublin Core element.
        Element pubDate = getElement(DATE);
        return (pubDate != null) ? FeedUtils.convertRss1Date(pubDate.getContent()) : null;
    }

    public List<String> getCategories()
    {
        return Collections.<String>emptyList();
    }
}
