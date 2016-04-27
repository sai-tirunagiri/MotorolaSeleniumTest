package org.quinnox.validateInvalidPostData;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.quinnox.businessLib.CommonLib;
import org.quinnox.genericLib.ExcelLib;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidateInvalidPostData {

	ExcelLib eLib;
	CommonLib cLib;
	
	@BeforeClass
	public void configBeforeClass(){
		
		//objet intalization
		eLib = new ExcelLib();
		cLib = new CommonLib();			
	}
	
	@Test
	public void validateInavlidPostRequest() throws IOException, JSONException, InvalidFormatException, KeyManagementException, NoSuchAlgorithmException{
		//Step 1: Get the Rest URL
		ExcelLib excelLib = new ExcelLib();
		String Resturl = excelLib.getExcelData("Sheet1", 1, 2);
		//String recentRecordValue = excelLib.getExcelData("Sheet2", 0, 0);
		
		//step 2: map the rest URL and get the response from the API
		HttpsURLConnection responseFromAPI = cLib.getInvalidResponseTextToValidate(Resturl);
		int statusCode = responseFromAPI.getResponseCode();
		//step 3: validate the data
		//Assert.assertTrue(responseText.matches("\\d+"),"Invoice number is invalid");
		String response = responseFromAPI.getResponseMessage();
		//Step 4: validate the previously inserted data
		//Assert.assertEquals(responseText, recentRecordValue,"Last record was not inserted");
		Assert.assertEquals(statusCode, 200,"You are trying to post invalid data!!");
	}
}
