/**
 * Copyright (c) 2012-2014 Axelor. All Rights Reserved.
 *
 * The contents of this file are subject to the Common Public
 * Attribution License Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://license.axelor.com/.
 *
 * The License is based on the Mozilla Public License Version 1.1 but
 * Sections 14 and 15 have been added to cover use of software over a
 * computer network and provide for limited attribution for the
 * Original Developer. In addition, Exhibit A has been modified to be
 * consistent with Exhibit B.
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is part of "Axelor Business Suite", developed by
 * Axelor exclusively.
 *
 * The Original Developer is the Initial Developer. The Initial Developer of
 * the Original Code is Axelor.
 *
 * All portions of the code written by Axelor are
 * Copyright (c) 2012-2014 Axelor. All Rights Reserved.
 */
package com.axelor.apps.account.web;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.swing.text.html.CSS;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.apps.account.service.debtrecovery.PayerQualityService;
import com.axelor.apps.base.db.CurrencyConversionLine;
import com.axelor.apps.base.db.General;
import com.axelor.apps.base.service.CurrencyConversionService;
import com.axelor.apps.base.service.CurrencyService;
import com.axelor.apps.base.service.administration.GeneralService;
import com.axelor.exception.service.TraceBackService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GeneralController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CurrencyService.class);

	@Inject
	private Injector injector;
	
	@Inject
	GeneralService gs;
	
	@Inject 
	CurrencyConversionService ccs;
	
	public void payerQualityProcess(ActionRequest request, ActionResponse response)  {
		
		try  {
			PayerQualityService pqs = injector.getInstance(PayerQualityService.class);
			pqs.payerQualityProcess();
		}
		catch (Exception e) { TraceBackService.trace(response, e); }
	}
	
	public void updateCurrencyConversion(ActionRequest request, ActionResponse response){
		 General general = request.getContext().asType(General.class);
		 LocalDate today = gs.getTodayDate();
		 
		 for(CurrencyConversionLine ccl : general.getCurrencyConversionLineList()){
			CurrencyConversionLine cclCoverd = CurrencyConversionLine.all().filter("startCurrency = ?1 AND endCurrency = ?2 AND fromDate >= ?3 AND (toDate <= ?3 OR toDate = null)",ccl.getStartCurrency(),ccl.getEndCurrency(),today).fetchOne();
			LOG.info("Currency Conversion Line for {} already covered : {}",today,ccl);
			if(ccl.isSelected() && ccl.getToDate() == null & cclCoverd == null){
				BigDecimal currentRate = ccs.convert(ccl.getStartCurrency(), ccl.getEndCurrency());
				if(currentRate.compareTo(new BigDecimal(-1)) == 0){
					response.setFlash("Currency conversion webservice not working");
					break;
				}
				ccl = CurrencyConversionLine.find(ccl.getId());
				ccl.setToDate(today.minusDays(1));
				ccs.saveCurrencyConversionLine(ccl);
				BigDecimal previousRate = ccl.getExchangeRate();
				String variations = ccs.getVariations(currentRate, previousRate);
				ccs.createCurrencyConversionLine(ccl.getStartCurrency(), ccl.getEndCurrency(), today, currentRate, General.find(general.getId()), variations);
			}
		 }
		 response.setReload(true);
	}
}
