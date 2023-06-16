package com.example.spring.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.spring.myapp.dto.PublicSearchRequestDto;
import com.google.gson.Gson;

public class ExternalAPIServiceCall {
	/**
	 * 1. api 접근 후 파일 다운로드
	 * @param crtfc_key : 접근 key
	 * @param spec : api url
	 * @param outputDir : 파일 다운로드 경로
	 * @return 
	 **/
	public String FileDownload(String crtfc_key , String spec, String outputDir) {
	    InputStream is = null;
        FileOutputStream os = null;
        String fileName = "";
        try {
        	File fileDir = new File(outputDir);
        	if (!fileDir.exists()) {
        		System.out.println("디럭토리 없군 생성 함");
        		fileDir.mkdir();// 폴더 생성
			}
        	
        	URL url = new URL(spec+crtfc_key);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = conn.getResponseCode();

            System.out.println("responseCode " + responseCode);
            
            // Status 가 200 일 때
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String disposition = conn.getHeaderField("Content-Disposition");
                String contentType = conn.getContentType();
                
                // 일반적으로 Content-Disposition 헤더에 있지만 
                // 없을 경우 url 에서 추출해 내면 된다.
                if (disposition != null) {
                    String target = "filename=";
                    int index = disposition.indexOf(target);
                    if (index != -1) {
                        fileName = disposition.substring(index + target.length() + 1);
                    }
                } else {
                    fileName = spec.substring(spec.lastIndexOf("/") + 1);
                }

                System.out.println("Content-Type = " + contentType);
                System.out.println("Content-Disposition = " + disposition);
                System.out.println("fileName = " + fileName);
                
                
                
                is = conn.getInputStream();
                os = new FileOutputStream(new File(fileDir, fileName));

                final int BUFFER_SIZE = 4096;
                int bytesRead;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
                System.out.println("File downloaded");
                //zipUtil.unCompressZip(outputDir+File.separator, fileName);
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            conn.disconnect();
            
		} catch (Exception e) {
            System.out.println("An error occurred while trying to download a file.");
            e.printStackTrace();
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }
		return fileName;
	}
	
	public String ApiCaller(String urlStr) {
		StringBuffer response = new StringBuffer();
		try {
			//https://opendart.fss.or.kr/api/list.json?crtfc_key={발급키}&corp_code={고시번호}&bgn_de={시작일자}&end_de={종료일자}&corp_cls={법인구분}
            URL url = new URL(urlStr); // replace with your API endpoint
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET"); // replace with the HTTP method you want to use

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                System.out.println("inputLine  " + inputLine);
            }
            in.close();

            // print the response from the API
          //  System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
		return response.toString();
	}
	
	public List<PublicSearchRequestDto> ApiCaller2(String urlStr) {
		List<PublicSearchRequestDto> requestList = new ArrayList<PublicSearchRequestDto>();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("content-type", "application/json");
			
			BufferedReader rd;
			
			// 서비스코드가 정상인경우 200-300사이의 숫자가 나옵니다.
			if ((200 <= conn.getResponseCode()) && (300 <= conn.getResponseCode())) {
				//rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				rd = new BufferedReader(new InputStreamReader(url.openStream() , "UTF-8"));
			}else {
				//rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				rd = new BufferedReader(new InputStreamReader(url.openStream() , "UTF-8"));
			}
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			System.out.println("결과 : : : : : " + sb.toString());
			rd.close();
			conn.disconnect();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			//System.out.println("결과 : : : : " + jsonObject.get("status"));
			//System.out.println("결과 : : : : " + jsonObject.get("message"));
			if (!"000".equals(jsonObject.get("status"))) {
				PublicSearchRequestDto requestDto = new PublicSearchRequestDto();
				requestDto.setStatus(jsonObject.get("status").toString());
				requestDto.setMessage(jsonObject.get("message").toString());
				requestList.add(requestDto);
			}else {
				JSONArray jsonArray = (JSONArray) jsonObject.get("list");
				Gson gson = new Gson();
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject listjsonObject  = (JSONObject) jsonArray.get(i);
					PublicSearchRequestDto pDto = gson.fromJson(listjsonObject.toString(), PublicSearchRequestDto.class);
					pDto.setStatus("000");
					requestList.add(pDto);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestList;
	}
}
