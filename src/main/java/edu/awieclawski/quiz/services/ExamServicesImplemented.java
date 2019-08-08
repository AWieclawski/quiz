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
			selections.put(i, "Not selected");
		}
		return selections;
	}

	@Override
	public List<QuestionSetProxy> questionSetProxyListSetup(Test selectedTest) {

		List<QuestionSet> thisList = getQuestionSetsByTestList(selectedTest);

		QuestionSetProxy questionSetProxy = new QuestionSetProxy();
		List<QuestionSetProxy> questionSetProxyList = new ArrayList<>();

		int orderNumberOfQuestion = 0;
		int numberOfAnswers = 4;

		for (int i = 0; i < thisList.size(); i++) {
			QuestionSet questionSet = thisList.get(i);
			String[] options = getArrayOfAnswersFromQuestionSet(numberOfAnswers, questionSet);

			orderNumberOfQuestion = i + 1;

			questionSetProxy.setQuestionNumber(orderNumberOfQuestion);
			questionSetProxy.setQuestion(questionSet.getQuestion());
			questionSetProxy.setArrayOfAnswers(options);

			logger.info(" !!! questionSetProxy " + questionSetProxy.toString());
			questionSetProxyList.add(questionSetProxy);
			logger.info(" !!! questionSetProxyList " + questionSetProxyList.toString());
		}

		return questionSetProxyList;

	}

	private List<QuestionSet> getQuestionSetsByTestList(Test selectedTest) {

		List<QuestionSet> thisList = questionSetRepository.findQuestionSetsByTest(selectedTest);
		logger.info(" $$$ resultsThatMeetSelectedCriteria enumeration: " + thisList.toString());

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

}
