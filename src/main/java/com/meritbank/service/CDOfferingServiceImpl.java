package com.meritbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.model.CDOffering;
import com.meritbank.repository.CDOfferingRepo;


@Service
public class CDOfferingServiceImpl implements CDOfferingService {
	@Autowired
	private CDOfferingRepo cdOfferingRepo;
	
	@Override
	public CDOffering addCDOffering(CDOffering cdOffering) throws InvalidArgumentException {
		if (cdOffering.getInterestRate() <= 0 || cdOffering.getInterestRate() >= 1 || cdOffering.getTerm() < 1) {
			throw new InvalidArgumentException("Invalid Term or Interest Rate");
		}
		return cdOfferingRepo.save(cdOffering);
	}

	@Override
	public List<CDOffering> getCDOfferings() {
		return cdOfferingRepo.findAll();
	}

	@Override
	public String clearCDOffering() {
		cdOfferingRepo.deleteAll();
		return "CD Offerings Cleared";
	}

	@Override
	public String deleteCDOffering(CDOffering cdOffering) {
		
		cdOfferingRepo.delete(cdOffering);
		return "CD Offerings deleted";
	}
}

