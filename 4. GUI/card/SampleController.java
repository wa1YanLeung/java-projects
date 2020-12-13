package card;

import java.util.Random;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SampleController {
	@FXML
	ImageView card1, card2, card3, card4;
	@FXML
	Button refreshBtn;

	int int1, int2, int3, int4;
	int[] cardOf4 = new int[4];
	Random random = new Random();

	public void initialize() {
		refreshCard();
	}

	public void refreshCard() {
		while (true) {
			int1 = random.nextInt(52);
			int2 = random.nextInt(52);
			int3 = random.nextInt(52);
			int4 = random.nextInt(52);

			System.out.println(int1+1);
			System.out.println(int2+1);
			System.out.println(int3+1);
			System.out.println(int4+1);
			System.out.println();

			if (int1 != int2 && int1 != int3 && int1 != int4 && int2 != int3 && int2 != int4 && int3 != int4) {
				break;
			}
		}
		cardOf4[0] = int1+1; 
		cardOf4[1] = int2+1; 
		cardOf4[2] = int3+1; 
		cardOf4[3] = int4+1;

		card1.setImage(new Image("image\\back.png"));
		card2.setImage(new Image("image\\back.png"));
		card3.setImage(new Image("image\\back.png"));
		card4.setImage(new Image("image\\back.png"));
	}

	public void onCardClicked(Event e) {

		if (e.getSource().equals(card1)) {
			card1.setImage(new Image("image\\" + cardOf4[0] + ".png"));
		} else if (e.getSource() == card2) {
			card2.setImage(new Image("image\\" + cardOf4[1] + ".png"));
		} else if (e.getSource() == card3) {
			card3.setImage(new Image("image\\" + cardOf4[2] + ".png"));
		} else {
			card4.setImage(new Image("image\\" + cardOf4[3] + ".png"));
		}
	}
}