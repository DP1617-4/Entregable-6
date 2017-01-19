package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TextMaterialRepository;
import domain.TextMaterial;

@Component
@Transactional
public class StringToTextMaterialConverter implements Converter<String, TextMaterial> {

	@Autowired
	TextMaterialRepository textMaterialRepository;

	@Override
	public TextMaterial convert(String text) {
		TextMaterial result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = textMaterialRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
