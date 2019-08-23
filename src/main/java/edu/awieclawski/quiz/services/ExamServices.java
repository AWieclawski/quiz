package edu.awieclawski.quiz.services;

import java.util.List;
import java.util.Map;

import edu.awieclawski.quiz.models.Test;

public interface ExamServices {

	public Map<Integer, String> userSelectionsMapInitialization(int sizeQuestionSets);

	public List<QuestionSetProxy> questionSetProxyListSetup(Test selectedTest);

	public List<TestRecapitulation> userSelectionsMapVerification(Test selectedTest,
			Map<Integer, String> mapOfUserAnswers);

	public int userSelectionsCorrectAnswersCount(List<TestRecapitulation> userSelectionsVerificationResults);

}
