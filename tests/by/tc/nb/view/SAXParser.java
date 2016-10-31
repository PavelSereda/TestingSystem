package by.tc.nb.view;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SAXParser {


    public static void main(String[] args) throws Exception {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(new FileInputStream("src/res/xml.xml"),
                handler);
    }
}

class SAXHandler extends DefaultHandler {

    List<Data> testList = new ArrayList<>();

    public List<Data> getTestList() {
        return testList;
    }

    public void setTestList(List<Data> testList) {
        this.testList = testList;
    }

    Data data = null;
    String content = null;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case "test":
                data = new Data();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch (qName) {
            case "test":
                testList.add(data);
                break;
            case "DProviderName":
                data.DProviderName = content;
                break;
            case "commandName":
                data.commandName = content;
                break;
            case "testedObj":
                data.testedObj = content;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }
}

class Data {
    String DProviderName;
    String commandName;
    String testedObj;


    @Override
    public String toString() {
        return " DProviderName: " + DProviderName + '\n' + "commandName " + commandName + '\n' + "testedObj " + testedObj;
    }
}
