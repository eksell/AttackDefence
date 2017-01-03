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
		initPlayers(0,1);
		round();
	
		System.out.println("SWAP SIDES");
		initPlayers(1,0);
		round();
	}

	void round(){
		
		int totalWon = 0, totalLost = 0; 
		List<String> totalScore = new ArrayList<String>(); 
		
		while(!p1.done){
			while(!p2.done){
				//				System.out.println("Strat: "+Arrays.toString(p1.nextStrat()));

				for(int roundCount = 0; roundCount < 3; roundCount++){
					distPoints(p1.getStrat()[roundCount],p2.getStrat()[roundCount]);

					if(p1.getScore() > 4){
						totalWon++;
						break;
					}
					else if(p2.getScore() > 4|| roundCount == 2){
						totalLost++;
						break;
					}

				}

				p1.reset();
				p2.reset();
				p2.nextStrat();
			}

			totalScore.add("Strat:"+Arrays.toString(p1.getStrat())+" Result: "+totalWon+" - "+totalLost);
			totalWon = 0;
			totalLost = 0;
			p1.nextStrat();
			p2.done = false;
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