package com.alcord.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.VehicleDocumentDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleDocument;
import com.alcord.service.VehicleDocumentService;

@Service
@Transactional
public class VehicleDocumentServiceImpl implements VehicleDocumentService {

	@Autowired
	private VehicleDocumentDao vehicleDocumentDao;

	/**
	 * {@inheritDoc}
	 */
	public VehicleDocument getById(UUID id) throws ProcessFailed {
		return vehicleDocumentDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(VehicleDocument vehicleDocument) throws ProcessFailed {
		return vehicleDocumentDao.save(vehicleDocument);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(VehicleDocument vehicleDocument) throws ProcessFailed {
		vehicleDocumentDao.update(vehicleDocument);

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		vehicleDocumentDao.getById(id);

	}

}
