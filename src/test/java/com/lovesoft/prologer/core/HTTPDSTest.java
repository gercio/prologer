package com.lovesoft.prologer.core;


//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class HTTPDSTest {

	@Test
	public void testIt() throws Exception {
//		HttpClient client;
//		client = HttpClients.createDefault();
//		
//	HttpGet httpget = new HttpGet("http://localhost:8080/Install.log");
//       HttpResponse response = client.execute(httpget);
//       HttpEntity entity = response.getEntity();
//       if (entity != null) {
//           long len = entity.getContentLength();
//           InputStream inputStream = entity.getContent();
//          
//           byte[] b = new byte[100];
//           int count  = inputStream.read(b);
////           do {
////        	   if(count > 0) {
////        		   String s = new String(b,0, count);
////        		   System.out.println(s);        		   
////        	   }
////        	   count = inputStream.read(b);
////        	   if(count < 0) {
////        		   inputStream.close();
////        		   response = client.execute(httpget);
////        		   entity = response.getEntity();
////        		   inputStream = entity.getContent();
////        	   }
////        	   Thread.sleep(10);
////           } while(true);
//       }
	}
}
