package com.example.app_readbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParese {
    public Document getDocument(String XML) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(XML));
        is.setEncoding("UTF-8");

        Document document = builder.parse(is);

        return document;
    }

    public String getValues(Element item,String name){
        NodeList nodeList = item.getElementsByTagName(name);
        return this.getTextNode(nodeList.item(0));
    }

    private String getTextNode(Node node){
        Node child;// node con

        if(node != null){//nếu node có giá trị
            if(node.hasChildNodes()){// nếu node có node con
                for(child = node.getFirstChild(); child != null; child.getNextSibling()){
//                    for chạy từ node con đầu tiên
//                            cho đến khi node con == null (không còn node con)
//                    mỗi lần chạy node con sẽ bằng node con tiếp theo
                    if(child.getNodeType() == Node.TEXT_NODE){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
