package com.lovesoft.androger.core;


import com.lovesoft.androger.LogDataObserver;
import com.lovesoft.androger.core.datasource.LogDataSource;

public class HTTPDS implements LogDataSource{

	@Override
	public void addLogDataObserver(LogDataObserver logDataObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLogDataObserver(LogDataObserver logDataObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(boolean paused) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getVisibleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogID getLogID() {
		// TODO Auto-generated method stub
		return null;
	}
//
//	private HttpClient client;
//	private LogID url;
//	
//	public HTTPDS(LogID url) {
//		// TODO Auto-generated constructor stub
//		client = HttpClients.createDefault();
//		this.url = url;
////		 HttpGet httpget = new HttpGet(url);
////         HttpResponse response = httpClient.execute(httpget);
////         HttpEntity entity = response.getEntity();
////         if (entity != null) {
////             long len = entity.getContentLength();
////             InputStream inputStream = entity.getContent();
////             // How do I write it?
////         }
//	}
//	
//	@Override
//	public void addLogDataObserver(LogDataObserver logDataObserver) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void removeLogDataObserver(LogDataObserver logDataObserver) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pause(boolean paused) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isPaused() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void start() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void stop() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String getVisibleName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public LogID getLogID() {
//		return url;
//	}

}
