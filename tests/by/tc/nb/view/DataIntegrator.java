package by.tc.nb.view;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataIntegrator {

    List<Data> tutorList = new ArrayList<>();
    List<Data> studentList = new ArrayList<>();

    public List<Data> returnCommands(String testObject) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXHandler handler = new SAXHandler();
        javax.xml.parsers.SAXParser parser = parserFactor.newSAXParser();
        parser.parse(new FileInputStream("src/res/xml.xml"),
                handler);

        for (Data data : handler.getTestList()) {
            if (data.testedObj.equals("Tutor")) {
                tutorList.add(data);
            }
            if (data.testedObj.equals("Student")) {
                studentList.add(data);
            }
        }
        if (testObject.equals("Tutor")) {
            return tutorList;
        } else {
            return studentList;
        }
    }

}
