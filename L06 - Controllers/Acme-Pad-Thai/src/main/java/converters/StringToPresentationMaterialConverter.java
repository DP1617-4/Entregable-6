package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PresentationMaterialRepository;
import domain.PresentationMaterial;

@Component
@Transactional
public class StringToPresentationMaterialConverter implements Converter<String, PresentationMaterial> {

	@Autowired
	PresentationMaterialRepository presentationMaterialRepository;

	@Override
	public PresentationMaterial convert(String text) {
		PresentationMaterial result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = presentationMaterialRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
