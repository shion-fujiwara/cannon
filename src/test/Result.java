package test;

public class Result {

	private int winner;
	private int shots;
	private int countA;
	private int countB;

	public Result(int winner, int shots, int countA, int countB) {
		this.setWinner(winner);
		this.setShots(shots);
		this.setCountA(countA);
		this.setCountB(countB);
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public int getCountA() {
		return countA;
	}

	public void setCountA(int countA) {
		this.countA = countA;
	}

	public int getCountB() {
		return countB;
	}

	public void setCountB(int countB) {
		this.countB = countB;
	}
}
