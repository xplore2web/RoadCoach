package com.alcord.utility;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;

import com.alcord.enums.DriverDocumentType;
import com.alcord.enums.VehicleDocumentType;
import com.alcord.exception.ProcessFailed;
import com.alcord.modelmappers.DocumentDataDetail;

public class RequestValidatorUtility {

	public static void validateDocumentDetail(DocumentDataDetail documentDataDetail) throws ParseException {
		if (documentDataDetail != null) {
			if (StringUtility.isEmpty(documentDataDetail.getValidity())) {
				throw new ProcessFailed("Validity cannot be empty");
			}
			String dateString = documentDataDetail.getValidity();
			try {
				Date date = DateUtil.parseStandardDocumentDate(dateString);
				DateTime documentDate = new DateTime(date.getTime());
				if (documentDate.minusMonths(Constant.MINIMUM_VALIDITY).isBeforeNow()) {
					throw new ProcessFailed(
							"Document should have an expiry for atleast +" + Constant.MINIMUM_VALIDITY + " months");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				throw new ProcessFailed("Date should be of format:" + DateUtil.parseStandardDocumentString(new Date()));
			}

			if (StringUtility.isEmpty(documentDataDetail.getName())) {
				throw new ProcessFailed("Name cannot be empty:" + DateUtil.parseStandardDocumentString(new Date()));
			}

			if (StringUtility.isEmpty(documentDataDetail.getType())) {
				throw new ProcessFailed("Type cannot be empty. Could be one of these:" + enumValues());
			}

		} else {
			throw new ProcessFailed("Document data detail cannot be empty");
		}
	}

	public static String enumValues() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < DriverDocumentType.values().length; i++) {
			DriverDocumentType driverDocumentType = DriverDocumentType.values()[i];
			stringBuilder.append(driverDocumentType.getValue() + " , ");
		}
		stringBuilder.append("OR for Vehicles \n");
		for (int i = 0; i < VehicleDocumentType.values().length; i++) {
			VehicleDocumentType vehicleDocumentType = VehicleDocumentType.values()[i];
			stringBuilder.append(vehicleDocumentType.getValue() + " , ");
		}
		return stringBuilder.toString();
	}
}
