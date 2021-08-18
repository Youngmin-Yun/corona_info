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

import com.coronainfo.service.ConsignedCenterService;
import com.coronainfo.service.VaccinCenterService;
import com.coronainfo.vo.ConsignedCenterVO;
import com.coronainfo.vo.VaccineCenterVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccineCenterAPIController {
    @Autowired
    VaccinCenterService service;
    @Autowired
    ConsignedCenterService c_serivce;
    // 코로나 백신 센터 호출 api (적재 완료됨)
    @GetMapping("/api/corona/vaccine/center")
    public Map<String, Object> getCoronaVaccineCenter()throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15077586/v1/centers"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=Qvh%2FPxBBmg3Pp64QitOr7PScIkH25vOjdehJK4Fr4N2ITDAoFZl7TONz6l%2Bovat%2BrMpoRgfFwWIXMssHOkAmVw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8")+"="+URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8")+"="+URLEncoder.encode("10000", "UTF-8")); /*한 페이지 결과 수*/
        System.out.println(urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
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
            String address = obj.getString("address");
            String centerName = obj.getString("centerName");
            String centerType = obj.getString("centerType");
            String facilityName = obj.getString("facilityName");
            String createdAt = obj.getString("createdAt");
            String lat = obj.getString("lat");
            String lng = obj.getString("lng");
            String org = obj.getString("org");
            String phoneNumber = obj.getString("phoneNumber");
            String sido = obj.getString("sido");
            String sigungu = obj.getString("sigungu");
            SimpleDateFormat fromatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = fromatter.parse(createdAt);
            
            VaccineCenterVO vo = new VaccineCenterVO();
            vo.setAddress(address);
            vo.setCenterName(centerName);
            vo.setCenterType(centerType);
            vo.setFacilityName(facilityName);
            vo.setCreatedAt(dt);
            vo.setLat(lat);
            vo.setLng(lng);
            vo.setOrg(org);
            vo.setPhoneNumber(phoneNumber);
            vo.setSido(sido);
            vo.setSigungu(sigungu);
            service.insertCoronaVaccineCenter(vo);
        }
        return resultMap;
    }

    @GetMapping("/api/corona/vaccineCenter/region")
    public Map<String, Object> getCoronaCenterLocation(@RequestParam String region){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<VaccineCenterVO> vo = service.selectVaccineCenter(region);
        resultMap.put("data", vo);
        return resultMap;
    }

    @GetMapping("/api/corona/vaccineCenter/consigned")
    public Map<String, Object> getConsignedVaccineCenter()throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/apnmOrg/v1/list"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8")+"="+URLEncoder.encode("2", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8")+"="+URLEncoder.encode("10000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=Qvh%2FPxBBmg3Pp64QitOr7PScIkH25vOjdehJK4Fr4N2ITDAoFZl7TONz6l%2Bovat%2BrMpoRgfFwWIXMssHOkAmVw%3D%3D"); /*Service Key*/
        System.out.println(urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
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
            ConsignedCenterVO vo = new ConsignedCenterVO();
            if(obj.has("endTm") && obj.get("endTm") instanceof String){
                vo.setEndTm(obj.getString("endTm"));
            }
            if(obj.has("sttTm")&& obj.get("sttTm") instanceof String){
                vo.setSttTm(obj.getString("sttTm"));
            }
            // String endTm = obj.getString("endTm");
            String orgTlno = obj.getString("orgTlno");
            String orgZipaddr = obj.getString("orgZipaddr");
            String orgnm = obj.getString("orgnm");
            // String sttTm = obj.getString("sttTm");
            
            // vo.setEndTm(endTm);
            vo.setOrgTlno(orgTlno);
            vo.setOrgZipaddr(orgZipaddr);
            vo.setOrgnm(orgnm);
            // vo.setSttTm(sttTm);
            c_serivce.insertConsignedCenter(vo);
        }

        return resultMap;
    }

    @GetMapping("/api/corona/vaccineCenter/consigned/adr")
    public Map<String, Object>selectConsignedCenter(@RequestParam String adr){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        adr = adr+"%";
        List<ConsignedCenterVO> List = c_serivce.selectConsignedCenter(adr);
        System.out.println(adr);
        resultMap.put("List", List);
        return resultMap;
    }
}
