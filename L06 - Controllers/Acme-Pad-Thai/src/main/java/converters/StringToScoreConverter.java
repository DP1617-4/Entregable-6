package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ScoreRepository;
import domain.Score;

@Component
@Transactional
public class StringToScoreConverter implements Converter<String, Score> {

	@Autowired
	ScoreRepository scoreRepository;

	@Override
	public Score convert(String text) {
		Score result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = scoreRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
