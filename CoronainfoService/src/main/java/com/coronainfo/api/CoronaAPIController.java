package com.coronainfo.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.coronainfo.service.CoronaInfoService;
import com.coronainfo.vo.CoronaAgeAndGenVO;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class CoronaAPIController {
    @Autowired
    CoronaInfoService service;
    // 코로나 현황
    @GetMapping("/api/corona")
    public Map<String, Object> getCoronaInfo(@RequestParam String startDt, @RequestParam String endDt)throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=Qvh%2FPxBBmg3Pp64QitOr7PScIkH25vOjdehJK4Fr4N2ITDAoFZl7TONz6l%2Bovat%2BrMpoRgfFwWIXMssHOkAmVw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8")); /*검색할 생성일 범위의 종료*/
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);
            Element elem = (Element) node;
            CoronaInfoVO vo = new CoronaInfoVO();
            vo.setAccExamCnt(Integer.parseInt(getTagValue("accExamCnt", elem)));
            vo.setAccExamCompCnt(Integer.parseInt(getTagValue("accExamCompCnt", elem)));
            vo.setCareCnt(Integer.parseInt(getTagValue("careCnt", elem)));
            vo.setClearCnt(Integer.parseInt(getTagValue("clearCnt", elem)));
            vo.setDeathCnt(Integer.parseInt(getTagValue("deathCnt", elem)));
            vo.setDecideCnt(Integer.parseInt(getTagValue("decideCnt", elem)));
            vo.setExamCnt(Integer.parseInt(getTagValue("examCnt", elem)));
            vo.setResultNegCnt(Integer.parseInt(getTagValue("resutlNegCnt", elem)));
            // String to Date
            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt = dtFormat.parse(getTagValue("createDt", elem));
            vo.setStateTime(dt);
            service.insertCoronaInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
    }
    @GetMapping("/api/coronaInfo/{date}")
    public Map<String, Object> getCoronaInfo(@PathVariable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        CoronaInfoVO data = null;
        // /api/coronaInfo/today
        if(date.equals("today")){
            data = service.selectTodayCoronaInfo();
        }
        resultMap.put("status", true);
        resultMap.put("data", data);
        return resultMap;
    }
    // 시도별 감염 현황
    @GetMapping("/api/coronaSido")
    public Map<String, Object> getCoronaSido(@RequestParam String startDt, @RequestParam String endDt)throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=Qvh%2FPxBBmg3Pp64QitOr7PScIkH25vOjdehJK4Fr4N2ITDAoFZl7TONz6l%2Bovat%2BrMpoRgfFwWIXMssHOkAmVw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8")); /*검색할 생성일 범위의 종료*/
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);
            Element elem = (Element) node;
            CoronaSidoVO vo = new CoronaSidoVO();
            vo.setDeathCnt(Integer.parseInt(getTagValue("deathCnt", elem)));
            vo.setDefCnt(Integer.parseInt(getTagValue("defCnt", elem)));
            vo.setGubun(getTagValue("gubun", elem));
            vo.setIncDec(Integer.parseInt(getTagValue("incDec", elem)));
            vo.setIsolClearCnt(Integer.parseInt(getTagValue("isolClearCnt", elem)));
            vo.setIsolIngCnt(Integer.parseInt(getTagValue("isolIngCnt", elem)));
            vo.setLocalOccCnt(Integer.parseInt(getTagValue("localOccCnt", elem)));
            vo.setOverFlowCnt(Integer.parseInt(getTagValue("overFlowCnt", elem)));
            // String to Date
            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt = dtFormat.parse(getTagValue("createDt", elem));
            vo.setCreateDt(dt);
            service.insertCoronaSido(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
    }
    // 시도별 감염자 날짜 검색
    @GetMapping("/api/coronaSido/{date}")
    public Map<String, Object> getCoronaSido(@PathVariable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date.equals("today")){
            List<CoronaSidoVO> list = service.selectTodayCoronaSido();
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        else{
            List<CoronaSidoVO> list = service.selectTodayCoronaSidoByDate(date);
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        return resultMap;
    }

    // 연령 및 성별 데이터
    @GetMapping("/api/corona/ageAndgen")
    public Map<String, Object> getCoronaAgeAndGen(@RequestParam String startDt, @RequestParam String endDt)throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=Qvh%2FPxBBmg3Pp64QitOr7PScIkH25vOjdehJK4Fr4N2ITDAoFZl7TONz6l%2Bovat%2BrMpoRgfFwWIXMssHOkAmVw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8")); /*검색할 생성일 범위의 종료*/

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i = 0; i < nList.getLength(); i++){
            // Node node = nList.item(i);
            // Element elem = (Element) node;
            Element elem = (Element)nList.item(i);
            CoronaAgeAndGenVO vo = new CoronaAgeAndGenVO();
            vo.setConfCase(Integer.parseInt(getTagValue("confCase", elem)));
            vo.setConfCaseRate(Double.parseDouble(getTagValue("confCaseRate", elem)));
            vo.setGubun(getTagValue("gubun", elem));
            vo.setCriticalRate(Double.parseDouble(getTagValue("criticalRate", elem)));
            vo.setDeath(Integer.parseInt(getTagValue("death", elem)));
            vo.setDeathRate(Double.parseDouble(getTagValue("deathRate", elem)));
            // String to Date
            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt = dtFormat.parse(getTagValue("createDt", elem));
            vo.setCreateDt(dt);
            service.insertAgeAndGen(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");

        return resultMap;
    }
    // 연령 및 성별 데이터 날짜 검색
    @GetMapping("/api/coronaAge/{date}")
    public Map<String, Object> getCoronaAgeByDate(@PathVariable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date.equals("today")){
            List<CoronaAgeAndGenVO> list = service.selectTodayCoronaAge();
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        else{
            List<CoronaAgeAndGenVO> list = service.selectCoronaAgeByDate(date);
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        return resultMap;
    }
    @GetMapping("/api/coronaGen/{date}")
    public Map<String, Object> getCoronaGenByDate(@PathVariable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date.equals("today")){
            List<CoronaAgeAndGenVO> list = service.selectTodayCoronaGen();
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        else{
            List<CoronaAgeAndGenVO> list = service.selectCoronaGenByDate(date);
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        return resultMap;
    }
    // json type 
    // 백신 현황 조회
    @GetMapping("/api/corona/vaccine")
    public Map<String, Object> rateOfVaccinated(@RequestParam @Nullable String targetDt) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
        String today = format.format (System.currentTimeMillis());
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15077756/v1/vaccine-stat"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=Qvh%2FPxBBmg3Pp64QitOr7PScIkH25vOjdehJK4Fr4N2ITDAoFZl7TONz6l%2Bovat%2BrMpoRgfFwWIXMssHOkAmVw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8")+"="+URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8")+"="+URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과 수*/
        if(targetDt == null) {
            targetDt = today;
        urlBuilder.append("&" + URLEncoder.encode("cond[baseDate::EQ]", "UTF-8")+"="+targetDt+"%2000%3A00%3A00"); /*페이지 번호*/
        }
        System.out.println(urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        //??
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        //??
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();


        JSONObject jsonObject = new JSONObject(sb.toString());
        // JSONObject cntObj = jsonObject.getJSONObject("count");
        Integer cnt = jsonObject.getInt("currentCount");
        System.out.println("Count : "+cnt);

        JSONArray dataArray = jsonObject.getJSONArray("data");
        for(int i = 0; i<dataArray.length(); i++){
            JSONObject obj = dataArray.getJSONObject(i);
            Integer accumulatedFirstCnt = obj.getInt("accumulatedFirstCnt");
            Integer accumulatedSecondCnt = obj.getInt("accumulatedSecondCnt");
            String baseDate = obj.getString("baseDate");
            Integer firstCnt = obj.getInt("firstCnt");
            Integer secondCnt = obj.getInt("secondCnt");
            String sido = obj.getString("sido");

            SimpleDateFormat fromatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = fromatter.parse(baseDate);

            CoronaVaccineInfoVO vo = new CoronaVaccineInfoVO();
            vo.setAccFirstCnt(accumulatedFirstCnt);
            vo.setAccSecondCnt(accumulatedSecondCnt);
            vo.setRegDt(dt);
            vo.setFirstCnt(firstCnt);
            vo.setSecondCnt(secondCnt);
            vo.setSido(sido);
            
            service.insertCoronaVaccineInfo(vo);
        }

        return resultMap;
    }
    
    @GetMapping("/api/coronaVaccine/{date}")
    public Map<String, Object> getCoronaVaccineInfo(@PathVariable String date){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date.equals("today")){
            List<CoronaVaccineInfoVO> list = service.selectTodayCoronaVaccineInfo();
            resultMap.put("data", list);
            return resultMap;
        }
        List<CoronaVaccineInfoVO> list = service.selectCoronaVaccineInfo(date);
        resultMap.put("data", list);
        return resultMap;

    }

    public static String getTagValue(String tag, Element elem){
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }
}
