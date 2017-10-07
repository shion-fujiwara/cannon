package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
	static long seed =1L;
	static Random random = new Random(seed);

	public static void main(String[] args) {
		//Cannon(1門あたりの命中率,砲数)
		Rating(new Cannon(1.0, 1), new Cannon(0.01, 100), 1000);//百発百中1門vs百発一中100門
		Rating(new Cannon(1.0, 2), new Cannon(0.01, 100), 1000);//百発百中2門vs百発一中100門
		Rating(new Cannon(1.0, 3), new Cannon(0.01, 100), 1000);//百発百中3門vs百発一中100門
		Rating(new Cannon(1.0, 4), new Cannon(0.01, 100), 1000);//百発百中4門vs百発一中100門
		Rating(new Cannon(1.0, 5), new Cannon(0.01, 100), 1000);//百発百中5門vs百発一中100門
		Rating(new Cannon(1.0, 6), new Cannon(0.01, 100), 1000);//百発百中6門vs百発一中100門
		Rating(new Cannon(1.0, 7), new Cannon(0.01, 100), 1000);//百発百中7門vs百発一中100門
		Rating(new Cannon(1.0, 8), new Cannon(0.01, 100), 1000);//百発百中8門vs百発一中100門
		Rating(new Cannon(1.0, 9), new Cannon(0.01, 100), 1000);//百発百中0門vs百発一中100門
		Rating(new Cannon(1.0, 10), new Cannon(0.01, 100), 1000);//百発百中10門vs百発一中100門
		Rating(new Cannon(1.0, 11), new Cannon(0.01, 100), 1000);//百発百中11門vs百発一中100門
		Rating(new Cannon(1.0, 12), new Cannon(0.01, 100), 1000);//百発百中12門vs百発一中100門
	}

	private static void Rating(Cannon cannonA, Cannon cannonB, int rep) {
		List<Result> results = fieldTest(cannonA, cannonB, rep);
		double draw = 0;
		double aveA = 0;
		double aveB = 0;
		double aveShots = 0;
		double aveCountA = 0;
		double aveCountB = 0;
		for(Result result : results) {
			if(1 == result.getWinner()) {
				aveA+=1;
			}else if(-1 == result.getWinner()) {
				aveB+=1;
			}else {
				draw+=1;
			}
			aveShots = aveShots + result.getShots();
			aveCountA = aveCountA + result.getCountA();
			aveCountB = aveCountB + result.getCountB();
		}
		System.out.println("引き分け率："+draw/rep + ","+ "前者勝率："+aveA/rep
				+ ","+ "後者勝率："+aveB/rep + ","+ "平均射撃数："+aveShots/rep
				+ ","+ "前者平均残数："+aveCountA/rep + ","+ "後者平均残数："+aveCountB/rep
				+ ","+ "試行回数："+rep);
	}

	private static int shoot(Cannon cannon, int numOfFire) {
		int hits = 0;
		for(int i = 0; numOfFire > i; i++) {
			if(cannon.getDex() >= random.nextDouble()) {
				hits=hits+1;
			}
		}
		//System.out.println(cannon.getCannonName() + ":" +hits);
		return hits;
	}
	private static Result battle(Cannon cannonA, Cannon cannonB) {
		int shots = 0;
		int countA = cannonA.getCount();
		int countB = cannonB.getCount();
		int fireA = countA;
		int fireB = countB;
		while(countA > 0 && countB > 0) {
			countA = countA - shoot(cannonB, fireB);
			countB = countB - shoot(cannonA, fireA);
			shots= shots+1;
			fireA = countA;
			fireB = countB;
		}
		int winner =0;
		if(countA > 0) {winner=1;}
		else {winner=-1;}
		return new Result(winner, shots, countA, countB);
	}

	private static List<Result> fieldTest(Cannon cannonA, Cannon cannonB, int rep) {
		List<Result> results = new ArrayList<Result>();
		int battles = 0;
		while(battles < rep) {
			results.add(battle(cannonA, cannonB));
			battles+=1;
		}
		return results;
	}
}
