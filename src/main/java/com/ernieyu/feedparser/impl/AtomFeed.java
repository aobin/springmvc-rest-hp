package com.ernieyu.feedparser.impl;

import com.ernieyu.feedparser.*;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Feed implementation for Atom 1.0.
 */
class AtomFeed extends BaseElement implements Feed
{
    // XML elements for Atom feeds.
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String SUB_TITLE = "subtitle";
    private static final String UPDATED = "updated";
    private static final String RIGHTS = "rights";
    private static final String CATEGORY = "category";
    private static final String ENTRY = "entry";

    /**
     * Constructs an AtomFeed with the specified namespace uri, name and
     * attributes.
     */
    public AtomFeed(String uri, String name, Attributes attributes)
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
        Element descr = getElement(SUB_TITLE);
        return (descr != null) ? descr.getContent() : null;
    }

    public String getLanguage()
    {
        // Not implemented.  Atom language is specified using the xml:lang
        // attribute on elements.
        return null;
    }

    public String getCopyright()
    {
        Element rights = getElement(RIGHTS);
        return (rights != null) ? rights.getContent() : null;
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

    public List<Item> getItemList()
    {
        // Get element list for entries.
        List<Element> elementList = getElementList(ENTRY);
        List<Item> itemList = new ArrayList<Item>();

        // Build item list.
        if (elementList != null)
        {
            for (Element element : elementList)
            {
                itemList.add((Item) element);
            }
        }

        return itemList;
    }

    public String toString()
    {
        return getTitle();
    }
}
