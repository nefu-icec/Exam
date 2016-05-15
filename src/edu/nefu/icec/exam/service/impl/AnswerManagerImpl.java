package edu.nefu.icec.exam.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import edu.nefu.icec.exam.bean.AnswerBean;
import edu.nefu.icec.exam.bean.ExamerBean;
import edu.nefu.icec.exam.domain.Answer;
import edu.nefu.icec.exam.domain.Examer;
import edu.nefu.icec.exam.domain.Question;
import edu.nefu.icec.exam.service.AnswerManager;
import edu.nefu.icec.exam.service.ExamerManager;
import edu.nefu.icec.exam.service.util.ManagerTemplate;

public class AnswerManagerImpl extends ManagerTemplate implements AnswerManager {

	@Override
	public List<AnswerBean> createOrGet(String examerid) {
		Examer examer = examerDao.get(examerid);
		int answerNum = examer.getAnswers().size();
		if (answerNum == 0) {
			int selected = examer.getExam().getSelected(); 
			String eid = examer.getExam().getEid();

			Set<Answer> set = examer.getAnswers();

			List<Question> listQuestions = questionDao.getByExam(eid);

			if(listQuestions.size()<selected){
			selected=listQuestions.size(); 
			}
			
			//生成selected个随机数   
			Random random = new Random();
			Integer x = random.nextInt(899999);  
			x = x+100000;
			int jiShu = x;
			
			for (int i = 0; i < selected; i++) {
				// addAnswer(listQuestions.get(i).getQid(), "请填写答案", examerid);
				Answer answer = new Answer();
				answer.setDescription("请填写答案"); 
				answer.setAnswerstatement(1);
				int index = jiShu%listQuestions.size(); 
				answer.setQuestion(listQuestions.get(index)); 
				jiShu++;
				answer.setMark(0.0);
				answerDao.save(answer);
				set.add(answer);
			}
			examer.setAnswers(set);
			examer.setHaslogin(true);
			examerDao.update(examer);
			
			
		} 
		
		return findAnswerByExamerId(examerid);
	}

	@Override
	public AnswerBean get(String id) {
		Answer answer = answerDao.get(id);
		return new AnswerBean(answer);
	}

	@Override
	public void modify(String id, String description) {
		Answer answer = answerDao.get(id);
		answer.setDescription(description);
		answer.setAnswerstatement(2);
		answerDao.update(answer);
	}

	@Override
	public void giveMark(String id, double mark) {
		Answer answer = answerDao.get(id);
		answer.setMark(mark);
		answerDao.update(answer);
	}

	@Override
	public List<AnswerBean> findAnswerByExamerId(String erid) {
		List<AnswerBean> list = new ArrayList<AnswerBean>();
		for (Answer answer : answerDao.findAnswerByExamer(erid)) {
			list.add(new AnswerBean(answer)); 
		} 
		return list;
	}

	@Override
	public List<AnswerBean> findAnswerByExamer(HttpSession session) {
		ExamerBean eb = (ExamerBean) session.getAttribute(ExamerManager.FLAG);
		String erid = eb.getErid();
		List<AnswerBean> list = new ArrayList<AnswerBean>();
		for (Answer answer : answerDao.findAnswerByExamer(erid)) {
			list.add(new AnswerBean(answer));
		}
		return list;
	}

	@Override
	public void updateAnswerState(String aid, int state) {
		Answer answer = answerDao.get(aid);
		answer.setAnswerstatement(state);
		answerDao.update(answer);
	}
 

}
