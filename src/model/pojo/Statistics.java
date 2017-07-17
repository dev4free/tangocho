package model.pojo;

public class Statistics {
	public int totalCards = 0;
	public int reviewedCards = 0;
	public int totalAnswers = 0;
	public int correctAnswers = 0;
	public int failedAnswers = 0;
	public float correctAnswersPerc = 0;
	public float failedAnswersPerc = 0;
	public void clear() {
		totalCards = 0;
		reviewedCards = 0;
		totalAnswers = 0;
		correctAnswers = 0;
		failedAnswers = 0;
		correctAnswersPerc = 0;
		failedAnswersPerc = 0;
	}
}
