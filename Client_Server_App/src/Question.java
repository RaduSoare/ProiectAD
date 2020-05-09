import java.io.Serializable;

public class Question implements Serializable {
	private String question;
	private String answear1, answear2, answear3, answear4;
	private String correctAnswear;
	
	public Question(String question, String answear1, String answear2, String answear3, String answear4,
			String correctAnswear) {
		super();
		this.question = question;
		this.answear1 = answear1;
		this.answear2 = answear2;
		this.answear3 = answear3;
		this.answear4 = answear4;
		this.correctAnswear = correctAnswear;
		
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswear1() {
		return answear1;
	}

	public void setAnswear1(String answear1) {
		this.answear1 = answear1;
	}

	public String getAnswear2() {
		return answear2;
	}

	public void setAnswear2(String answear2) {
		this.answear2 = answear2;
	}

	public String getAnswear3() {
		return answear3;
	}

	public void setAnswear3(String answear3) {
		this.answear3 = answear3;
	}

	public String getAnswear4() {
		return answear4;
	}

	public void setAnswear4(String answear4) {
		this.answear4 = answear4;
	}

	public String getcorrectAnswear() {
		return correctAnswear;
	}

	public void setcorrectAnswear(String correctAnswear) {
		this.correctAnswear = correctAnswear;
	}
	
	public boolean iscorrectAnswear(String clientanswear) {
		if(clientanswear.equals(this.correctAnswear)) {
			return true;
		}
		return false;
	}
	
	
}
