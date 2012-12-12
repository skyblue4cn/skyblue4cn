package cn.insurance.utils;


import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
/***
 * 用户点击某个栏目标签时，该标签将使用另外的样式，以表示当前状态，该类就是根据label-jsp.xml里面的对栏目与页面的
 * 配置和相应的style，以便jsp页面调用，显示正确的样式�?
 * @author BMXP
 *
 */
public class ParseLabelXml
{
    public static void main(String[] args) 
    throws ParserConfigurationException, SAXException, 
           IOException, XPathExpressionException {

      
//     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
//     domFactory.setNamespaceAware(true); // never forget this!
//     DocumentBuilder builder = domFactory.newDocumentBuilder();
//     InputStream is=ParseLabelXml.class.getResourceAsStream("label-jsp.xml");
//     //Document doc = builder.parse("title.xml");
//     Document doc = builder.parse(is);
//     XPathFactory factory = XPathFactory.newInstance();
//     XPath xpath = factory.newXPath();
//     XPathExpression expr 
//      = xpath.compile("//label[jsppath='/People/peopleSearchList.jsp' and id='peopleSearchLists']/match-style/text()");
//
//     Object result = expr.evaluate(doc, XPathConstants.STRING);
//     if((String)result!=""){
//     System.out.print((String)result);
//     }else{
//         XPathExpression exprs
//         = xpath.compile("//label[id='peopleSearchList']/not-match-style/text()");
//         Object result2 = exprs.evaluate(doc, XPathConstants.STRING);
//         System.out.print((String)result2);
//     }
//     NodeList nodes = (NodeList) result;
//     
//     
//     for (int i = 0; i < nodes.getLength(); i++) {
//         System.out.println(nodes.item(i).getNodeValue()); 
//     }

   }
    /**
     * 
     * @param labelId label id
     * @param jspName 当前的jsp路径
     * @return 相对应的style名称�?
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    public static String getStyleName(String labelId,String jspName) throws ParserConfigurationException, SAXException, 
    IOException, XPathExpressionException 
    {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        InputStream is=ParseLabelXml.class.getResourceAsStream("label-jsp.xml");
        //Document doc = builder.parse("title.xml");
        Document doc = builder.parse(is);
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr 
         = xpath.compile("//label[jsppath='"+jspName+"' and id='"+labelId+"']/match-style/text()");
        Object result = expr.evaluate(doc, XPathConstants.STRING);
        if((String)result!=""){
        return (String)result;
        }else{
            XPathExpression exprs
            = xpath.compile("//label[id='"+labelId+"']/not-match-style/text()");
            Object result2 = exprs.evaluate(doc, XPathConstants.STRING);
            return (String)result2;
        }
        
    }
    
}