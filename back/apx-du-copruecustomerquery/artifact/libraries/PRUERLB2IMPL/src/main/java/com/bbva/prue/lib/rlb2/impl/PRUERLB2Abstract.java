package com.bbva.prue.lib.rlb2.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.prue.lib.rlb1.PRUERLB1;
import com.bbva.prue.lib.rlb2.PRUERLB2;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class PRUERLB2Abstract extends AbstractLibrary implements PRUERLB2 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected PRUERLB1 prueRLB1;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param prueRLB1 the this.prueRLB1 to set
	*/
	public void setPrueRLB1(PRUERLB1 prueRLB1) {
		this.prueRLB1 = prueRLB1;
	}

}