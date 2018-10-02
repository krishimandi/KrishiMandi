package com.krishimandi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.krishimandi.application.KrishiMandiException;
import com.krishimandi.application.RequestConstants;
import com.krishimandi.dataobjects.RequestObject;
import com.krishimandi.logs.LogUtil;
import com.krishimandi.request.handler.BaseRequestHandler;
import com.krishimandi.request.handler.SignupRequestHandler;

/**
 * This class is a Request Handler class. This class and is the entry point for all of the HTTP requests.
 *
 */
@RestController
public class RequestController {

	@RequestMapping(method=RequestMethod.POST, value="/request", headers="Accept=application/json")
	public void handleRequest(@RequestBody RequestObject requestObject) {

		if(null != requestObject) {

			try {

				logRequest(requestObject);
				getSpecificRequestHandler(requestObject);
			} catch (KrishiMandiException e) {

				e.printStackTrace();
			}
			System.out.println("Request: " + requestObject.toString());
		}
	}

	/**
	 * This method logs the request into KrishiMandi/RequestResponseLog/(TodaysLog) 
	 * @throws KrishiMandiException 
	 */
	private void logRequest(RequestObject requestObject) throws KrishiMandiException {

		LogUtil.logRequestResponse(null);
	}

	private BaseRequestHandler getSpecificRequestHandler(RequestObject requestObject) {
		return null;

//		short requestId = requestObject.getRequestId();
//
//		switch (requestId) {
//
//		case RequestConstants.REQUEST_CONSTANT_SIGNUP:
//
//			return new SignupRequestHandler();
//
//		default:
//
//			return null;
//		}
	}
}
