package com.ernieyu.feedparser.impl;

import com.ernieyu.feedparser.Element;
import com.ernieyu.feedparser.FeedType;
import com.ernieyu.feedparser.FeedUtils;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Item implementation for Atom feeds.
 */
class AtomItem extends BaseItem
{
    // XML elements for Atom items.
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String UPDATED = "updated";
    private static final String ID = "id";
    private static final String CONTENT = "content";
    private static final String SUMMARY = "summary";
    private static final String AUTHOR = "author";
    private static final String NAME = "name";
    private static final String CATEGORY = "category";

    /**
     * Constructs an AtomItem with the specified namespace uri, name and
     * attributes.
     */
    public AtomItem(String uri, String name, Attributes attributes)
    {
        super(uri, name, attributes);
    }

    public FeedType getType()
    {
        return FeedType.ATOM_1_0;
    }

    public String getTitle()
    {
        Element title = getElement(TITLE);
        return (title != null) ? title.getContent() : null;
    }

    public String getLink()
    {
        Element link = getElement(LINK);
        return (link != null) ? link.getAttributes().getValue("href") : null;
    }

    public String getDescription()
    {
        Element descr = getElement(CONTENT);
        if (descr == null)
            descr = getElement(SUMMARY);
        return (descr != null) ? descr.getContent() : null;
    }

    public String getAuthor()
    {
        Element author = getElement(AUTHOR);
        if (author != null)
        {
            Element name = author.getElement(NAME);
            return (name != null) ? name.getContent() : null;
        }
        return null;
    }

    public String getGuid()
    {
        Element guid = getElement(ID);
        return (guid != null) ? guid.getContent() : null;
    }

    public Date getPubDate()
    {
        Element pubDate = getElement(UPDATED);
        return (pubDate != null) ? FeedUtils.convertAtomDate(pubDate.getContent()) : null;
    }

    public List<String> getCategories()
    {
        List<Element> elementList = getElementList(CATEGORY);

        // Create list of category terms.
        List<String> categories = new ArrayList<String>();
        for (Element element : elementList)
        {
            categories.add(element.getAttributes().getValue("term"));
        }

        return categories;
    }
}
