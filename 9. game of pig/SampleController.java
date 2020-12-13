package application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
//class for constructing history record
class HistoryRecord {
	String gameResult; // win or lose
	LocalDateTime dateTime;
	int totalPoint;
	String player;

	HistoryRecord(int totalPoint, String player) {
		this.gameResult = totalPoint > 99 ? "Win" : "Lose";
		this.dateTime = LocalDateTime.now();
		this.totalPoint = totalPoint;
		this.player = player;
	}	
}
//GUI buttons and methods
public class SampleController {
	@FXML
	TextField tfRollValue1, tfRollValue2, tfScoreByTurn1, tfScoreByTurn2, tfScoreSum1, tfScoreSum2, tfGuide;
	@FXML
	Button btnHold1, btnHold2, btnRoll1, btnRoll2, btnStart, btnShowHistory, 
	lGameResult, lDateTime, lTotalPoint, lPlayer;
	@FXML
	GridPane gripPaneHistory;	

	int rollValue1, rollValue2, scoreByTurn1, scoreByTurn2, scoreSum1, scoreSum2;
	byte userTurn;
	boolean sortDirection;
	Random random = new Random();
	//use array list to store history records 
	ArrayList<HistoryRecord> historyRecords = new ArrayList<HistoryRecord>();

	//this method sets GUI at start
	public void start() {
		tfGuide.setText("guide: user1 rolls first.");
		tfRollValue1.setText("roll value: 0");
		tfScoreByTurn1.setText("score by turn: 0");
		tfScoreSum1.setText("score sum: 0");
		tfRollValue2.setText("roll value: 0");
		tfScoreByTurn2.setText("score by turn: 0");
		tfScoreSum2.setText("score sum: 0");

		scoreByTurn1 = 0;
		scoreByTurn2 = 0;
		scoreSum1 = 0;
		scoreSum2 = 0;
		userTurn = 1;
	}
	//method for user1 to roll the dice and to compute score for this turn
	public void roll1() {
		if (userTurn != 1) {
			return;
		}

		rollValue1 = random.nextInt(6) + 1; //roll value 1 to 6 inclusive		
		tfRollValue1.setText("roll value: " + rollValue1);
		//if user1 rolls 2 to 6
		if (rollValue1 > 1) {			
			scoreByTurn1 += rollValue1; //add roll value to score for this turn
			tfScoreByTurn1.setText("score by turn: " + scoreByTurn1);
		} else {
			//if roll value of user1 is 1, execute below
			scoreByTurn2 = 0; //set user2's initial score for his new turn be 0
			userTurn = 2; //it's user2's turn now
			tfRollValue2.setText("roll value: 0");
			tfScoreByTurn2.setText("score by turn: 0");
			tfGuide.setText("guide: user2 rolls now.");
			return;
		}
		//if sum of user1's score for this turn and his score sum > 99
		if (scoreSum1 + scoreByTurn1 > 99) {
			scoreSum1 += scoreByTurn1; //add user1's score for this turn to his score sum
			tfScoreSum1.setText("score sum: " + scoreSum1);			
			tfGuide.setText("guide: user1 won. Start or view history.");
			userTurn = 0;

			try {
				saveResult(scoreSum1, "user1");//save result for user1's score sum
				Thread.sleep(5);
				saveResult(scoreSum2, "user2");//save result for user2's score sum
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	//method for user1 to hold rolling the dice
	public void hold1() {
		if (userTurn != 1 || rollValue1 < 2) {
			return;
		}

		rollValue1 = 0;
		scoreSum1 += scoreByTurn1; //add score for this turn to score sum
		tfScoreSum1.setText("score sum: " + scoreSum1);			
		scoreByTurn2 = 0;
		tfRollValue2.setText("roll value: 0");
		tfScoreByTurn2.setText("score by turn: 0");
		userTurn = 2;
		tfGuide.setText("guide: user2 rolls now.");
	}
	//method for user2 to roll the dice and to compute score for this turn
	public void roll2() {
		if (userTurn != 2) {
			return;
		}

		rollValue2 = random.nextInt(6) + 1;		
		tfRollValue2.setText("roll value: " + rollValue2);
		//if user2 rolls 2 to 6
		if (rollValue2 > 1) {
			scoreByTurn2 += rollValue2; //add roll value to score for this turn
			tfScoreByTurn2.setText("score by turn: " + scoreByTurn2);
		} else {
			//execute below if user2 rolls 1
			scoreByTurn1 = 0; //set user1's initial score for his new turn be 0
			userTurn = 1; //it's user1's turn
			tfRollValue1.setText("roll value: 0");
			tfScoreByTurn1.setText("score by turn: 0");
			tfGuide.setText("guide: user1 rolls now.");
			return;
		}
		//if sum of user2's score for this turn and his score sum > 99
		if (scoreSum2 + scoreByTurn2 > 99) {
			scoreSum2 += scoreByTurn2;//add user2's score for this turn to his score sum 
			tfScoreSum2.setText("score sum: " + scoreSum2);			
			tfGuide.setText("guide: user2 won. Start or view history.");
			userTurn = 0;

			try {
				saveResult(scoreSum1, "user1");
				Thread.sleep(5);
				saveResult(scoreSum2, "user2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	//method for user2 to hold rolling the dice
	public void hold2() {
		if (userTurn != 2 || rollValue2 < 2) {
			return;
		}

		rollValue2 = 0;
		scoreSum2 += scoreByTurn2; //add score for this turn to score sum
		tfScoreSum2.setText("score sum: " + scoreSum2);			
		scoreByTurn1 = 0;
		tfRollValue1.setText("roll value: 0");
		tfScoreByTurn1.setText("score by turn: 0");
		userTurn = 1;
		tfGuide.setText("guide: user1 rolls now.");
	}
	//this method adds the two users' score sums to history record
	synchronized private void saveResult(int scoreSum, String user) {
		historyRecords.add(new HistoryRecord(scoreSum, user));
	}
	//this method lists history record on GUI
	public void showHistory() {
		if (userTurn > 0) {
			return;
		}

		gripPaneHistory.getChildren().clear();
		gripPaneHistory.add(lGameResult, 0, 0);
		gripPaneHistory.add(lDateTime, 1, 0);
		gripPaneHistory.add(lTotalPoint, 2, 0);
		gripPaneHistory.add(lPlayer, 3, 0);

		for (int i = 0; i < historyRecords.size(); i++) {
			gripPaneHistory.add(new Label(historyRecords.get(i).gameResult), 0, i+1);
			gripPaneHistory.add(new Label(historyRecords.get(i).dateTime.toString()), 1, i+1);
			gripPaneHistory.add(new Label(Integer.toString(historyRecords.get(i).totalPoint)), 2, i+1);
			gripPaneHistory.add(new Label(historyRecords.get(i).player), 3, i+1);
		}
	}
	//this method shows history record by sorting game result
	public void sortGameResult() {
		if (userTurn > 0) {
			return;
		}

		if (sortDirection) {
			historyRecords.sort((a,b)-> a.gameResult.compareTo(b.gameResult));
			sortDirection = false;
		} else {
			historyRecords.sort((a,b)-> b.gameResult.compareTo(a.gameResult));
			sortDirection = true;
		}

		showHistory();
	}
	//this method shows history record by sorting date and time
	public void sortDateTime() {
		if (userTurn > 0) {
			return;
		}

		if (sortDirection) {
			historyRecords.sort((a,b)-> a.dateTime.compareTo(b.dateTime));
			sortDirection = false;
		} else {
			historyRecords.sort((a,b)-> b.dateTime.compareTo(a.dateTime));
			sortDirection = true;
		}

		showHistory();
	}
	//this method shows history record by sorting total points
	public void sortTotalPoint() {
		if (userTurn > 0) {
			return;
		}

		if (sortDirection) {
			historyRecords.sort((a,b)-> a.totalPoint - b.totalPoint);
			sortDirection = false;
		} else {
			historyRecords.sort((a,b)-> b.totalPoint - a.totalPoint);
			sortDirection = true;
		}

		showHistory();
	}
	//this method shows history record by sorting the two users
	public void sortPlayer() {
		if (userTurn > 0) {
			return;
		}

		if (sortDirection) {
			historyRecords.sort((a,b)-> a.player.compareTo(b.player));
			sortDirection = false;
		} else {
			historyRecords.sort((a,b)-> b.player.compareTo(a.player));
			sortDirection = true;
		}

		showHistory();
	}
}