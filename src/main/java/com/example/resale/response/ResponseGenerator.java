package com.example.resale.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {
	
	private static final Logger logger = Logger.getLogger(ResponseGenerator.class);

	public TransactionContext generatorTransactioncontext(HttpHeaders httpHeaders) {
		
		TransactionContext context = new TransactionContext();
//		if(httpHeaders == null) {
//			context.setCorrelationId("demo");
//			context.setApplicationLabel("demo");
//			return context;
//		}
		if(httpHeaders.get("CorrelationId")!=null) {
			context.setCorrelationId(httpHeaders.get("CorrelationId").toString());
			}else {
				context.setCorrelationId("demo");
			}
		if(httpHeaders.get("ApplicationLabel")!= null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString()); 
		}else {
			context.setApplicationLabel("demo");
		}
		return context;	
	}
	
	public ResponseEntity<?> successResponse(TransactionContext context ,Object object,HttpStatus httpStatus){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("CorrelationId", context.getCorrelationId());
		httpHeaders.add("ApplicationLabel", context.getApplicationLabel());
		Response response = new Response();
		response.setData(object);
		response.setTimeStamp(new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date()));
		logger.debug("response class is "+ Date.class);
		logger.debug("response status is "+ httpStatus.toString());
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response,httpHeaders,httpStatus);
		return responseEntity;
	}
	public ResponseEntity<?> errorResponse(TransactionContext context, String errorMessage, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLable", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		Error error = new Error();
		error.setCode(httpStatus.toString()+"0001");
		error.setReason(errorMessage);
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}

}
