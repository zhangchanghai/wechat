package com.wechat.util;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 * 
 * @author sky
 *处理xml与object的相互转化
 */
public class XmlUtil {
	public static Object xmlToObject(String strXml,Class objclass){
		JAXBContext jaxbContext;
		Object obj=null;
		try {
			jaxbContext = JAXBContext.newInstance(objclass);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Reader r=new StringReader(strXml);
	        obj=jaxbUnmarshaller.unmarshal(r);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public static String objectToXml(Object obj) throws JAXBException{
		JAXBContext jaxbContext;
		jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller jaxbMarshaller=jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringWriter sw=new StringWriter();	
		jaxbMarshaller.marshal(obj,sw);
		return sw.toString();
	}
}
