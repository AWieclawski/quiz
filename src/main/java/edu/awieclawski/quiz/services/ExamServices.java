package edu.awieclawski.quiz.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("examServices")
public class ExamServices {

	public Map<Integer, String> userSelectionsMapInit(int sizeQuestionSets) {

		Map<Integer, String> selections = new HashMap<Integer, String>();
		for (int i = 0; i < sizeQuestionSets; i++) {
			selections.put(i, "Nothing selected");
		}
		return selections;
	}

}
