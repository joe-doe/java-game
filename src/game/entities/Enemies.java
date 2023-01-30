package game.entities;

import game.surroundings.AbstractBoard;
import java.util.Vector;

public class Enemies {
	public Vector<Enemy> enemiesVector = null;
	private AbstractBoard board = null;
	private Hero hero = null;

	public Enemies(AbstractBoard board, Hero hero) {
		this.enemiesVector = new Vector<Enemy>();
		this.board = board;
		this.hero = hero;
		loadEnemies();
	}

	private void loadEnemies() {
		enemiesVector.add(new Enemy1(board, hero, 650, 600, 0.1F, 0));
		enemiesVector.add(new Enemy2(board, hero, 800, 600, 0.2F, 10));
//		enemiesVector.add(new Enemy1(board, hero, 10000 - 800, 600, 0.5F, 0));
	}

	public void addEnemy(Enemy newEnemy) {
		enemiesVector.add(newEnemy);
	}

	public void removeEnemy(Enemy thatEnemy) {
		enemiesVector.remove(thatEnemy);
	}

}
