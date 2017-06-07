package com.alcord.utility;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AWSS3Utility {

	public static S3ObjectSummary getLatestObject(ObjectListing objectListing) {
		if (objectListing != null && objectListing.getObjectSummaries() != null
				&& objectListing.getObjectSummaries().size() > 0) {
			List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
			if (s3ObjectSummaries.size() == 1) {
				return s3ObjectSummaries.get(0);
			} else {
				// Apparently the list is already sorted by lastModified.
				// So I will go ahead and pick up the latest
				return s3ObjectSummaries.get(s3ObjectSummaries.size() - 1);
			}

		}
		return null;
	}

	public static String getFileNameWithExtension(MultipartFile file, String fileName) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		return fileName + "." + extension;
	}
}
