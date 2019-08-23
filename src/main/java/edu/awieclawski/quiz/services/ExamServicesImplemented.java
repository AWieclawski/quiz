package edu.awieclawski.quiz.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.awieclawski.quiz.models.QuestionSet;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.repositories.QuestionSetRepository;

@Service("examServices")
public class ExamServicesImplemented implements ExamServices {

	private static final Logger logger = LogManager.getLogger(ExamServicesImplemented.class.getName());

	@Autowired
	private QuestionSetRepository questionSetRepository;

	@Override
	public Map<Integer, String> userSelectionsMapInitialization(int sizeQuestionSets) {
		Map<Integer, String> selections = new HashMap<>();
		for (int i = 0; i < sizeQuestionSets; i++) {
			selections.put(i, "N/S");
		}
		return selections;
	}

	@Override
	public List<QuestionSetProxy> questionSetProxyListSetup(Test selectedTest) {
		// getQuestionSetsByTestList from data source
		List<QuestionSet> thisList = getQuestionSetsByTestList(selectedTest);
		List<QuestionSetProxy> questionSetProxyList = new ArrayList<>();

		if (!thisList.isEmpty()) {
			int orderNumberOfQuestion = 0;
			int numberOfAnswers = 4;
			for (int i = 0; i < thisList.size(); i++) {
				QuestionSet questionSet = thisList.get(i);
				String[] options = getArrayOfAnswersFromQuestionSet(numberOfAnswers, questionSet);
				orderNumberOfQuestion = i + 1;
				QuestionSetProxy questionSetProxy = new QuestionSetProxy(orderNumberOfQuestion,
						questionSet.getQuestion(), options);
				questionSetProxyList.add(questionSetProxy);
			}
		}
		return questionSetProxyList;
	}

	@Override
	public List<TestRecapitulation> userSelectionsMapVerification(Test selectedTest,
			Map<Integer, String> mapOfUserAnswers) {
		List<QuestionSet> thisList = getQuestionSetsByTestList(selectedTest);
		List<TestRecapitulation> userSelectionsVerificationResults = new ArrayList<TestRecapitulation>();
		if (!thisList.isEmpty()) {
			for (int i = 0; i < thisList.size(); i++) {
				QuestionSet questionSet = thisList.get(i);
				TestRecapitulation verificatedResult = new TestRecapitulation();
				verificatedResult.setUserSelectionsVerificationResult(false);
				verificatedResult.setQuestionNumberInRecapitulation(i + 1);
				String stringOfSelectedAnswer = mapOfUserAnswers.get(i);
//check if answer is the same as the assigned as the correct one
				if (isNumeric(stringOfSelectedAnswer)) {
					Integer numberOfSelectedAnswer = Integer.valueOf(stringOfSelectedAnswer);
					if (questionSet.getCorrectAnswer().equals(numberOfSelectedAnswer)) {
						verificatedResult.setUserSelectionsVerificationResult(true);
					}
				}
				verificatedResult.setSelectedAnswerInRecapitulation(mapOfUserAnswers.get(i));
				String contentOfSelectedAnswer = getContentOfSelectedAnswer(stringOfSelectedAnswer, questionSet);
				verificatedResult.setSelectedAnswerContentInRecapitulation(contentOfSelectedAnswer);
				userSelectionsVerificationResults.add(verificatedResult);
			}
		}
		return userSelectionsVerificationResults;
	}

	@Override
	public int userSelectionsCorrectAnswersCount(List<TestRecapitulation> userSelectionsVerificationResults) {
		int correctAnswersCount = 0;
		if (!userSelectionsVerificationResults.isEmpty()) {
			for (TestRecapitulation result : userSelectionsVerificationResults) {
				if (result.getUserSelectionsVerificationResult()) {
					correctAnswersCount++;
				}
			}
		}
		return correctAnswersCount;
	}

	private List<QuestionSet> getQuestionSetsByTestList(Test selectedTest) {
		List<QuestionSet> thisList = questionSetRepository.findQuestionSetsByTest(selectedTest);
		return thisList;
	}

	private String[] getArrayOfAnswersFromQuestionSet(int numberOfAnswers, QuestionSet questionSet) {
		String[] options = new String[numberOfAnswers];
		options[0] = questionSet.getFirstAnswer();
		options[1] = questionSet.getSecondAnswer();
		options[2] = questionSet.getThirdAnswer();
		options[3] = questionSet.getFourthAnswer();
		return options;
	}

	private String getContentOfSelectedAnswer(String numberOfSelectedAnswer, QuestionSet questionSet) {
		String contentOfSelectedAnswer = "";
		switch (numberOfSelectedAnswer) {

		case "1":
			contentOfSelectedAnswer = questionSet.getFirstAnswer();
			break;

		case "2":
			contentOfSelectedAnswer = questionSet.getSecondAnswer();
			break;

		case "3":
			contentOfSelectedAnswer = questionSet.getThirdAnswer();
			break;

		case "4":
			contentOfSelectedAnswer = questionSet.getFourthAnswer();
			break;
		}
		return contentOfSelectedAnswer;
	}

	private boolean isNumeric(String strNum) {
		try {
			Integer i = Integer.valueOf(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

}
