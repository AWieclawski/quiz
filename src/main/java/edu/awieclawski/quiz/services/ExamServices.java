package edu.awieclawski.quiz.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.awieclawski.quiz.models.QuestionSet;

@Service("examServices")
public class ExamServices {
	
    private static final Logger logger = LogManager.getLogger(ExamServices.class.getName());
	
    private QuizSetProxy thisQuestionAndAnswersSet = new QuizSetProxy();

	public Map<Integer, String> userSelectionsMapInitialization(int sizeQuestionSets) {

		Map<Integer, String> selections = new HashMap<Integer, String>();
		for (int i = 0; i < sizeQuestionSets; i++) {
			selections.put(i, "Nothing selected");
		}
		return selections;
	}
	
    public QuizSetProxy setQuestionAndAnswers(int i, List<QuestionSet> thisList) {

        int number = i;
        int numberOfAnswers = 4;
        String[] options = new String[numberOfAnswers];

        QuestionSet thisQuestionAndAnswers = thisList.get(i);

        options[0] = thisQuestionAndAnswers.getFirstAnswer();
        options[1] = thisQuestionAndAnswers.getSecondAnswer();
        options[2] = thisQuestionAndAnswers.getThirdAnswer();
        options[3] = thisQuestionAndAnswers.getFourthAnswer();

        thisQuestionAndAnswersSet.setQuestionNumber(number);
        thisQuestionAndAnswersSet.setQuestion(thisQuestionAndAnswers.getQuestion());
        thisQuestionAndAnswersSet.setArrayOfAnswers(options);

        logger.info(" !!! setQuestionAndAnswers " + thisQuestionAndAnswersSet.toString());

        return thisQuestionAndAnswersSet;
    }

}
