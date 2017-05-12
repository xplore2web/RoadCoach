package com.ajit.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.responsemappers.ContainerResponse;
import com.ajit.responsemappers.OperationStatusType;
import com.ajit.responsemappers.TransactionResponse;

import io.swagger.annotations.ApiOperation;



/**
*
* @author AR
*/
@RestController
@RequestMapping(value = "/brandbot")
public class ContactFormController {
	
	
	@RequestMapping(value = "/v1/saveContactForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "save Contact form ")
	public ResponseEntity<ContainerResponse> saveContactForm(@RequestParam("emailAddress") String emailAddress) {
		//Gson gson = new Gson ();
		
			//@SuppressWarnings("unchecked")
//			Map<String, Object> requestBodyMap
//			= gson.fromJson(new BufferedReader(request.getReader()), Map.class);
			System.out.println( "reuest................" +emailAddress);
		
		System.out.println( "reuest................" );
		TransactionResponse transactionResponse = new TransactionResponse();
		
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
							} catch (Throwable ex) {
				Logger.getLogger(ContactFormController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
	}
	@RequestMapping("/greeting")
    public String greetingSubmit(@ModelAttribute ContactFormController greeting) {
		System.out.println("hiiiiiiii");
        return "result";
    }

}
