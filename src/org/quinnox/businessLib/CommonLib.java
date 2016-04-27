package org.quinnox.businessLib;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;


public class CommonLib {
	public HttpsURLConnection getApiStatus(String Resturl) throws IOException, NoSuchAlgorithmException, KeyManagementException{
		// configure the SSLContext with a TrustManager
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        URL url = new URL(Resturl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        System.out.println(conn.getResponseCode());
        
        conn.disconnect();
        return conn;
		
	}
	
	public HttpsURLConnection getResponseTextToValidate(String Resturl) throws IOException, JSONException, InvalidFormatException, NoSuchAlgorithmException, KeyManagementException{
		SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        URL url = new URL(Resturl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/xml");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            String body = "<_postarreceipt_batch_req> <_postarreceipt> <MotorolaInvoiceNum>998899</MotorolaInvoiceNum> <InvoiceTotal>999.00</InvoiceTotal> <batchno>20151105-1</batchno> <DueDate>2015-11-28</DueDate> <CompanyName>1234567800010001</CompanyName> <MotorolaInvoiceSerie>2</MotorolaInvoiceSerie> </_postarreceipt> </_postarreceipt_batch_req>";
            OutputStream output = new BufferedOutputStream(conn.getOutputStream());
            output.write(body.getBytes());
            output.flush();
            System.out.println(conn.getResponseCode());
            conn.disconnect();
            return conn;
	}
	
	public HttpsURLConnection getInvalidResponseTextToValidate(String Resturl) throws IOException, JSONException, InvalidFormatException, NoSuchAlgorithmException, KeyManagementException{
		SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        URL url = new URL(Resturl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/xml");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            String body = "<_postarre> <_postarreceipt> <MotorolaInvoiceNum>998899</MotorolaInvoiceNum> <InvoiceTotal>999.00</InvoiceTotal> <batchno>20151105-1</batchno> <DueDate>2015-11-28</DueDate> <CompanyName>1234567800010001</CompanyName> <MotorolaInvoiceSerie>2</MotorolaInvoiceSerie> </_postarreceipt> </_postarreceipt_batch_req>";
            OutputStream output = new BufferedOutputStream(conn.getOutputStream());
            output.write(body.getBytes());
            output.flush();
            System.out.println(conn.getResponseCode());
            conn.disconnect();
            return conn;
	}
	
	private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
	}
}
