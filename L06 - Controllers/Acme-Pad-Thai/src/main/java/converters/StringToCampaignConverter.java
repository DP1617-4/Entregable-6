/* StringToMasterClassConverter.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CampaignRepository;
import domain.Campaign;

@Component
@Transactional
public class StringToCampaignConverter implements Converter<String, Campaign> {

	@Autowired
	CampaignRepository campaignRepository;

	@Override
	public Campaign convert(String text) {
		Campaign result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = campaignRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
