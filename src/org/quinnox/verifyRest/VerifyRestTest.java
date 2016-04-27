package org.quinnox.verifyRest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.quinnox.businessLib.CommonLib;
import org.quinnox.genericLib.ExcelLib;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyRestTest {
	ExcelLib eLib;
	CommonLib cLib;
	
	@BeforeClass
	public void configBeforeClass(){
		
		//objet intalization
		eLib = new ExcelLib();
		cLib = new CommonLib();			
	}
	
	@Test
	public void testRestApi() throws IOException, InvalidFormatException, KeyManagementException, NoSuchAlgorithmException{
		//System.setProperty("javax.net.ssl.trustStore", "C:/.keystore");
		//System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		//Step 1: getting the URL from excel
		ExcelLib excelLib = new ExcelLib();
		String Resturl = excelLib.getExcelData("Sheet1", 1, 2);
		System.out.println(Resturl);
		//Step 2: map the rest URL
		HttpsURLConnection connectionStatus = cLib.getApiStatus(Resturl);
		
		//Step 3: Getting the status code from the API
		int statusCode = connectionStatus.getResponseCode();
		
		//Step 4: Getting the Response Text
		String response = connectionStatus.getResponseMessage();
		
		//Step 5: verify the status code
		Assert.assertEquals(statusCode, 202,response+"!");
	}
}
