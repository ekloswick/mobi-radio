package nd.edu.mobileradio;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import nd.edu.mobileradio.RssItem;

/**
 * SAX tag handler
 * 
 * @author ITCuties
 *
 */
public class RssParseHandler extends DefaultHandler {

	private List<RssItem> rssItems;
	
	// Used to reference item while parsing
	private RssItem currentItem;
	
	// Parsing title indicator
	private boolean parsingTitle;
	// Parsing link indicator
	private boolean parsingLink;
	
	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}
	
	public List<RssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("content-item".equals(qName) || "item".equals(qName)) {
			currentItem = new RssItem();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
		} else if ("url".equals(qName) || "link".equals(qName)) {
			parsingLink = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("content-item".equals(qName) || "item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
		} else if ("url".equals(qName) || "link".equals(qName)) {
			parsingLink = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String temp = getXMLString(new String(ch, start, length));
		
		if (parsingTitle) {
			if (currentItem != null)
				currentItem.setTitle(temp);
		} else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setLink(temp);
				parsingLink = false;
			}
		}
	}
	
	public static String getXMLString(String str)
    {
        if (str.contains("'"))
        {
            str = str.replace("'", "");
        }
        else if (str.contains("�"))
        {
        	str = str.replace("�", "");
        }

        return str;
    }
	
}
