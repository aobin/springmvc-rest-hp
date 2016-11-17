package com.ernieyu.feedparser.impl;

import com.ernieyu.feedparser.Element;
import com.ernieyu.feedparser.FeedType;
import com.ernieyu.feedparser.FeedUtils;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Item implementation for RSS 2.0 feeds.
 */
class Rss2Item extends BaseItem
{
    // XML elements for RSS items.
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String PUB_DATE = "pubDate";
    private static final String AUTHOR = "author";
    private static final String GUID = "guid";
    private static final String CATEGORY = "category";

    /**
     * Constructs an Rss2Item with the specified namespace uri, name and
     * attributes.
     */
    public Rss2Item(String uri, String name, Attributes attributes)
    {
        super(uri, name, attributes);
    }

    public FeedType getType()
    {
        return FeedType.RSS_2_0;
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
        Element author = getElement(AUTHOR);
        return (author != null) ? author.getContent() : null;
    }

    public String getGuid()
    {
        Element guid = getElement(GUID);
        return (guid != null) ? guid.getContent() : null;
    }

    public Date getPubDate()
    {
        Element pubDate = getElement(PUB_DATE);
        return (pubDate != null) ? FeedUtils.convertRss2Date(pubDate.getContent()) : null;
    }

    public List<String> getCategories()
    {
        List<Element> elementList = getElementList(CATEGORY);

        // Create list of category terms.
        List<String> categories = new ArrayList<String>();
        for (Element element : elementList)
        {
            categories.add(element.getContent());
        }

        return categories;
    }
}
