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

import repositories.PlaceholderRepository;
import domain.Placeholder;

@Component
@Transactional
public class StringToPlaceholderConverter implements Converter<String, Placeholder> {

	@Autowired
	PlaceholderRepository placeholderRepository;

	@Override
	public Placeholder convert(String text) {
		Placeholder result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = placeholderRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
