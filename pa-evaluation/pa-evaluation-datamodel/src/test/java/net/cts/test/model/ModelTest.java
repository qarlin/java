package net.cts.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import net.cts.model.CTSTest;
import net.cts.model.CTSTestAnswer;
import net.cts.model.Exam;
import net.cts.model.ExamConfiguration;
import net.cts.model.Library;
import net.cts.model.Role;
import net.cts.model.User;
import net.cts.model.enums.Difficulty;
import net.cts.model.question.AnswerMap;
import net.cts.model.question.OptionAnswer;
import net.cts.model.question.SingleOptionQuestion;
import net.cts.model.question.TextAnswer;
import net.cts.model.question.TextQuestion;

public class ModelTest {

	@Test
	public void questionTest() {
		Library library = new Library();
		library.setName("Java");
		library.setDescription("Java Library");
		
		TextQuestion textQuestion = new TextQuestion();
		textQuestion.setLibrary(library);
		textQuestion.setDifficulty(Difficulty.JUNIOR);
		textQuestion.setDateCreated(new Date());
		textQuestion.setScore(3);
		textQuestion.setTimeLimit(30L);
		textQuestion.setQuestionText("What is singleton");
		
		TextAnswer textAnswer = new TextAnswer();
		textAnswer.setAnswerText("Singleton is ... ");
		
		textQuestion.setAnswer(textAnswer);
		
		SingleOptionQuestion soQuestion = new SingleOptionQuestion();
		soQuestion.setLibrary(library);
		soQuestion.setDifficulty(Difficulty.JUNIOR);
		soQuestion.setDateCreated(new Date());
		soQuestion.setScore(3);
		soQuestion.setTimeLimit(30L);
		soQuestion.setQuestionText("Select the right answer about Singleton");
		
		Set<AnswerMap> optAnswers = new HashSet<AnswerMap>();
		
		AnswerMap answerMapA = new AnswerMap();
		answerMapA.setTextAnswers("Singleton is A");
		answerMapA.setRightAnswers(false);
		optAnswers.add(answerMapA);
		
		AnswerMap answerMapB = new AnswerMap();
		answerMapB.setTextAnswers("Singleton is B");
		answerMapB.setRightAnswers(true);
		optAnswers.add(answerMapB);
		
		AnswerMap answerMapC = new AnswerMap();
		answerMapC.setTextAnswers("Singleton is C");
		answerMapC.setRightAnswers(false);
		optAnswers.add(answerMapC);
		
		OptionAnswer optAnswer = new OptionAnswer();
		optAnswer.setAnswers(optAnswers);
		
		soQuestion.setAnswer(optAnswer);
		
		assertNotNull(soQuestion);
		assertEquals(3, soQuestion.getAnswer().getAnswers().size());
		
		Role role = new Role();
		role.setName("Administrador");
		role.setAdmin(true);
		
		User userAdmin = new User();
		userAdmin.setFirstname("Name 1");
		userAdmin.setEmail("mail@mail.com");
		userAdmin.setRole(role);
		
		Set<ExamConfiguration> configurations = new HashSet<ExamConfiguration>();
		ExamConfiguration examConfiguration = new ExamConfiguration();
		examConfiguration.setLibrary(library);
		examConfiguration.setDifficulty(Difficulty.JUNIOR);
		examConfiguration.setTotalQuestions(2);
		configurations.add(examConfiguration);
	
		Exam exam = new Exam();
		exam.setDescription("Java Junior for Company X");
		exam.setName("Java Junior Test");
		exam.setConfigurations(configurations);
		
		Set<CTSTestAnswer> questionAnswers = new HashSet<CTSTestAnswer>();
		CTSTestAnswer questionAnswerA = new CTSTestAnswer();
		questionAnswerA.setQuestion(textQuestion);
		
		TextAnswer ctsAnswerT = new TextAnswer();
		ctsAnswerT.setAnswerText("My answer");
		questionAnswerA.setAnswer(ctsAnswerT);
		
		questionAnswers.add(questionAnswerA);
		
		CTSTestAnswer questionAnswerB = new CTSTestAnswer();
		questionAnswerB.setQuestion(soQuestion);
		
		OptionAnswer optTestAnswer = soQuestion.getAnswer();
		for (AnswerMap answerMap : optTestAnswer.getAnswers()) {
			answerMap.setRightAnswers(false);
		} 
		
		questionAnswerB.setAnswer(optTestAnswer);
		
		questionAnswers.add(questionAnswerB);
		
		CTSTest test = new CTSTest();
		test.setAnswers(questionAnswers);
		test.setUser(userAdmin);
		
	}
	
	@Test
	public void TestTest(){
		
		
	}
}
