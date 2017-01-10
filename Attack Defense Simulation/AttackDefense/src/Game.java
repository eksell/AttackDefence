import java.util.*;

public class Game {

	/** results[x][y][z]
	 * 	x = p1 (0) or p2 (1)
	 * 	y = p1 choice (0-2)
	 *  z = p2 choice (0-2)
	 */
	private int [][][] results =
		{
				{
					{5,0,2},
					{2,1,1},
					{0,3,1}
				},
				{
					{0,4,1},
					{0,1,3},
					{2,0,0}
				}
		};

	Agent p1, p2;

	void initPlayers(int player1, int player2){
		p1 = new Agent(player1);
		p2 = new Agent(player2);
	}

	void run(){
		//set settings and run game until all possibilities have been calculated
		System.out.println("ATTACKER RESULTS:");
		initPlayers(0,1);
		round();


		System.out.println("DEFENDER RESULTS:");
		initPlayers(1,0);
		round();
	}

	void round(){

		int totalWon = 0, totalLost = 0; 
		List<String> totalScore = new ArrayList<String>(); 

		while(!p1.done && p1.isAttacker() || !p2.done && p2.isAttacker()){
			while(!p1.done && !p1.isAttacker() || !p2.done && !p2.isAttacker()){
				//				System.out.println("Strat: "+Arrays.toString(p1.nextStrat()));

				for(int roundCount = 0; roundCount < 3; roundCount++){
					distPoints(p1.getStrat()[roundCount],p2.getStrat()[roundCount]);

					if(p1.getScore() > 4){
						if(p1.isAttacker()){
							totalWon++;
						}else totalLost++;

						break;
					}
					else if(p2.getScore() > 4|| roundCount == 2){
						if(p2.isAttacker()){
							totalWon++;
						}else totalLost++;
						break;
					}
				}

				p1.reset();
				p2.reset();
				if(p1.isAttacker())	p2.nextStrat();
				else p1.nextStrat();
			}

			String strat;
			if(p1.isAttacker())
				strat = Arrays.toString(p1.getStrat());
			else 
				strat = Arrays.toString(p2.getStrat());

			totalScore.add("Strat:"+strat+" Result: "+totalWon+" - "+totalLost);
			totalWon = 0;
			totalLost = 0;

			if(p1.isAttacker()){
				p1.nextStrat();
				p2.done = false;
			}
			else{
				p2.nextStrat();
				p1.done = false;
			}

		}

		Iterator<String> scoreIterator = totalScore.iterator();

		while(scoreIterator.hasNext()){
			System.out.println(scoreIterator.next());
		}
	}

	void distPoints(int x, int y){
		p1.addPoints(results[0][x][y]);
		p2.addPoints(results[1][x][y]);
	}

}
