package com.github.abhijeetburle.performancecomparator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonPatch;
import javax.json.JsonPatchBuilder;
import javax.json.JsonValue;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MapToJsonComparison {
	@Test
    public void convertMapToJson() {
		
			usingGSON(1);
			usingGSON(10);
			usingGSON(100);
			usingGSON(1000);
			usingGSON(10000);
			usingGSON(100000);

			usingFasterXMLJackson(1);
			usingFasterXMLJackson(10);
			usingFasterXMLJackson(100);
			usingFasterXMLJackson(1000);
			usingFasterXMLJackson(10000);
			usingFasterXMLJackson(100000);

			usingJsonP(1);
			usingJsonP(10);
			usingJsonP(100);
			usingJsonP(1000);
			usingJsonP(10000);
			usingJsonP(100000);
			
	}
	
	public void usingJsonP(long iterations) {
		long startTime;
        long totalTime;        
        startTime = System.nanoTime();
        final JsonObject initial = Json.createObjectBuilder().build();
        for(int i=0; i<iterations; i++) {
	        
			JsonPatchBuilder patchBuilder = Json.createPatchBuilder();
			patchBuilder.add("/header", Json.createObjectBuilder().build());
	        patchBuilder.add("/method", Json.createObjectBuilder().build());
	        patchBuilder.add("/uri", Json.createObjectBuilder().build());
			patchBuilder.add("/body", Json.createObjectBuilder().build());
			patchBuilder.add("/body/bundleItems", Json.createArrayBuilder().build());
			
	        patchBuilder.add("/body/bundleManageNumber", Json.createValue("load_bundle_number_1"));
			patchBuilder.add("/body/bundleName", Json.createValue("bundle for televisions 1"));
			patchBuilder.add("/body/bundleDescription", Json.createValue("this bundle1"));
			patchBuilder.add("/body/bundlState", Json.createValue("INACTIVE"));
			patchBuilder.add("/body/parentItemManageNumber", Json.createValue("normal-item-035"));
					
			patchBuilder.add("/body/bundleItems/0",
	                Json.createObjectBuilder().add("itemManageNumber", "normal-item-036")
	                .build());
			patchBuilder.add("/body/bundleItems/0/mandatory", JsonValue.FALSE);
			patchBuilder.add("/body/bundleItems/0/sequence", JsonValue.FALSE);
			
			patchBuilder.add("/body/bundleItems/1",
	                Json.createObjectBuilder().add("itemManageNumber", "normal-item-037")
	                .build());
			
			patchBuilder.add("/body/bundleItems/2",
	                Json.createObjectBuilder().add("itemManageNumber", "normal-item-038")
	                .build());
			patchBuilder.add("/body/bundleItems/2/mandatory", JsonValue.FALSE);
			patchBuilder.add("/body/bundleItems/2/sequence", JsonValue.TRUE);
			
			patchBuilder.add("/header/X-Request-Id", "1");
			patchBuilder.add("/header/Content-type", "application/json");
			patchBuilder.add("/uri", "http://test/test-open/shops/shopId/bundles/1?templateId=1");
			patchBuilder.add("/method", "POST");
			
			
			
	        JsonObject resultout = null;
	        if (patchBuilder != null) {
	            final JsonPatch patch = patchBuilder.build();
	            try {
	                resultout = patch.apply(initial);
	
	            } catch (JsonException ex) {
	            	ex.printStackTrace();
	            }
	        }
	        resultout.toString();
        }
        totalTime = System.nanoTime()-startTime;
        System.out.printf("JsonP___________,iterations,%10d,totalTime,%10.04fms,avg,%10.04fms\n",iterations,((double)totalTime/1000000),((double)totalTime/iterations/1000000));
	}
	
	public void usingFasterXMLJackson(long iterations) {
		ObjectMapper objectMapper = new ObjectMapper();

        long startTime;
        long totalTime;        
        startTime = System.nanoTime();
        for(int i=0; i<iterations; i++) {
        	try {
                objectMapper.writeValueAsString(generateTestData());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        totalTime = System.nanoTime()-startTime;
        System.out.printf("FasterXMLJackson,iterations,%10d,totalTime,%10.04fms,avg,%10.04fms\n",iterations,((double)totalTime/1000000),((double)totalTime/iterations/1000000));
	}
	
    public void usingGSON(long iterations) {
    	long startTime;
        long totalTime;        
        startTime = System.nanoTime();
        for(int i=0; i<iterations; i++) {
			Gson gson = new Gson();
	        Type gsonType = new TypeToken<Map<String, Object>>(){}.getType();
	        gson.toJson(generateTestData(),gsonType);
        }
        totalTime = System.nanoTime()-startTime;
        System.out.printf("GSON____________,iterations,%10d,totalTime,%10.04fms,avg,%10.04fms\n",iterations,((double)totalTime/1000000),((double)totalTime/iterations/1000000));
    }
    public Map<String, Object> generateTestData() {
    	Map<String, Object> bundleItem_0 = new TreeMap<String, Object>();
		bundleItem_0.put("itemManageNumber", "normal-item-036");
		bundleItem_0.put("mandatory", false);
		bundleItem_0.put("sequence", false);
		
		Map<String, Object> bundleItem_1 = new TreeMap<String, Object>();
		bundleItem_1.put("itemManageNumber", "normal-item-037");
		
		Map<String, Object> bundleItem_2 = new TreeMap<String, Object>();
		bundleItem_2.put("itemManageNumber", "normal-item-038");
		bundleItem_2.put("mandatory", false);
		bundleItem_2.put("sequence", true);
		
		List<Map<String, Object>> bundleItems = new ArrayList<Map<String, Object>>();
		bundleItems.add(bundleItem_0);
		bundleItems.add(bundleItem_1);
		bundleItems.add(bundleItem_2);

		Map<String, String> header = new TreeMap<String, String>();
		header.put("X-Request-Id", "1");
		header.put("Content-type", "application/json");
		
		Map<String, Object> body = new TreeMap<String, Object>();
		body.put("bundleManageNumber", "load_bundle_number_1");
		body.put("bundleName", "bundle for televisions 1");
		body.put("bundleDescription", "this bundle1");
		body.put("bundlState", "INACTIVE");
		body.put("parentItemManageNumber", "normal-item-035");
		body.put("bundleItems", bundleItems);
		
		
        Map<String, Object> elements = new TreeMap<String, Object>();
        elements.put("header", header);
        elements.put("method", "POST");
        elements.put("uri", "http://test/test-open/shops/shopId/bundles/1?templateId=1");
        elements.put("body", body);
        
        return elements;
    }
}
