package com.example.spring.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.spring.myapp.dto.CorpCdoeDto;

public class CompressUtil {
	
	// xml 파일 파싱
	public List<Object> ReadXMLFile2(File fileName) {
		List<Object> corList = new ArrayList<Object>();
		System.out.println("파일ㄹ : "+fileName);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fileName);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("list");
			System.out.println("-----------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				CorpCdoeDto cdoeDto = new CorpCdoeDto();
				Map<String, Object> mapDate = new HashMap<String, Object>();
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					cdoeDto.setCorp_code(getTagValue("corp_code", eElement));
					cdoeDto.setCorp_name(getTagValue("corp_name", eElement));
					cdoeDto.setStock_code(getTagValue("stock_code", eElement));
					cdoeDto.setModify_date(getTagValue("modify_date", eElement));
				}
				corList.add(cdoeDto);
			}
			//System.out.println(corList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	return corList;
	}
	
	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
	
	
	
	
}
