package com.ernieyu.feedparser.impl;

import com.ernieyu.feedparser.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

import java.io.Serializable;
import java.util.*;

/**
 * Base class for XML elements.
 */
class BaseElement implements Element ,Serializable
{

    private final String uri;
    private final String name;
    private final Attributes attributes;
    private final Map<String, List<Element>> elementMap = new HashMap<String, List<Element>>();
    private String content;

    /**
     * Constructs an Element with the specified namespace uri and name.
     */
    public BaseElement(String uri, String name)
    {
        this.uri = uri;
        this.name = name;
        this.attributes = new AttributesImpl();
    }

    /**
     * Constructs an Element with the specified namespace uri, name and
     * attributes.
     */
    public BaseElement(String uri, String name, Attributes attributes)
    {
        this.uri = uri;
        this.name = name;
        this.attributes = new AttributesImpl(attributes);
    }

    public String getUri()
    {
        return uri;
    }

    public String getName()
    {
        return name;
    }

    public Attributes getAttributes()
    {
        return attributes;
    }

    public String getContent()
    {
        return content;
    }

    /**
     * Sets the element content.
     */
    void setContent(String data)
    {
        this.content = data;
    }

    public Element getElement(String name)
    {
        List<Element> elements = elementMap.get(name);
        return (elements != null) ? elements.get(0) : null;
    }

    public List<Element> getElementList(String name)
    {
        return elementMap.containsKey(name) ? elementMap.get(name) : Collections.<Element>emptyList();
    }

    public Set<String> getElementKeys()
    {
        return elementMap.keySet();
    }

    /**
     * Adds the specified child element to the element.
     */
    void addElement(String name, Element element)
    {
        if (elementMap.containsKey(name))
        {
            elementMap.get(name).add(element);
        }
        else
        {
            List<Element> newList = new ArrayList<Element>();
            newList.add(element);
            elementMap.put(name, newList);
        }
    }
}
