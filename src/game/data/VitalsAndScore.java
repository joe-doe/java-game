package game.data;

public class VitalsAndScore {

	public long score = 0;
	public int lifes = 3;
	public int maxLifeLevel = 240;
	public int currentLifeLevel = 240;
	private static VitalsAndScore instance = null;

	private VitalsAndScore() {

	}

	public static VitalsAndScore getInstance() {

		if (instance == null) {
			instance = new VitalsAndScore();
		}

		return instance;
	}

	public long getScore() {
		return this.score;
	}

	public void setScore(long newScore) {
		this.score += newScore;
	}

	public int getLifes() {
		return this.lifes;
	}

	public void setLifes(int newNumberOfLifes) {
		this.lifes += newNumberOfLifes;
	}

	public int getCurrentLifeLevel() {
		return this.currentLifeLevel;
	}

	public void setCurrentLifeLevel(int newLifeLevel) {
		this.currentLifeLevel += newLifeLevel;
	}
	public int getMaxLevel(){
		return this.maxLifeLevel;
	}
	public void reset(){
		score = 0;
		lifes = 3;
		currentLifeLevel = 240;
	}

}
