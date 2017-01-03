import java.util.Arrays;

public class Agent {

	private int score = 0;
	public String result = ""; 
	private int[] choiceArray = new int[] {0,0,0};
	boolean won = false;
	private int id;
	protected boolean done;

	public Agent(int id){
		score = 0;
		done = false;
		this.id  = id;
	};

	void addPoints(int pointsAdded){
		score = score + pointsAdded;
	}

	int getScore(){
		return score; 
	}

	String getChoiceString(int party, int selection){
		String choice = "";

		if(party == 0){			
			switch (selection){
			case 0: choice = "push"; 
			case 1: choice = "shoot"; 
			case 2: choice = "flash"; 
			}

		}
		else{
			switch (selection){
			case 0: choice = "wait"; 
			case 1: choice = "shoot"; 
			case 2: choice = "smoke"; 
			}
		}
		return choice;

	}

	int getChoice(int x){
		return choiceArray[x];
	}

	int[] getChoices(){
		return choiceArray;
	}

	void rotateChoice(){
		int[] buffer = choiceArray.clone();
		for(int x=0;x<choiceArray.length;x++){
			if(x==choiceArray.length-1){
				choiceArray[x] = buffer[0];
			}else{
				choiceArray[x] = buffer[x+1];
			}
		}
	}

	boolean done(){
		if(choiceArray[0] == 2 && choiceArray[1] == 2&&choiceArray[2] == 2){
			System.out.println(Arrays.toString(choiceArray));
			return true;
		}
		else{
			System.out.println("Done: "+Arrays.toString(choiceArray));
			return false;
		}
	}

	void won(){
		won = true;
	}

	void reset(){
		score = 0;
		won = false;
	}

	void setID(int newID){
		id = newID;
	}

	int getID(){
		return id;
	}

	public int [] nextStrat(){

		if(choiceArray[2] < 2){
			choiceArray[2]++;
		}else{
			choiceArray[2] = 0;
			if(choiceArray[1] < 2){
				choiceArray[1]++;
			}else{
				choiceArray[1] = 0;
				if(choiceArray[0] < 2){
					choiceArray[0]++;
				}else{
					choiceArray[0] = 0;
					done = true;
				}
			}
		}
		
		//System.out.println(Arrays.toString(choiceArray));
		return choiceArray;
	}

	public int[] getStrat() {
		return choiceArray;
	}

}
