package tempConvert;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SampleController {
	@FXML
	RadioButton radio1, radio2, radio3;
	@FXML
	Label label1, label2;
	@FXML
	TextField textfield1, textfield2;

	public void onRadioClicked() {
		if (radio1.isSelected()) {
			label1.setText("Mile");
			label2.setText("Kilometer");			
		} else if (radio2.isSelected()) {
			label1.setText("Celsius");
			label2.setText("Fahrenheit");			
		} else if (radio3.isSelected()) {
			label1.setText("Pound");
			label2.setText("Kilogram");			
		}
	}

	public void onKeyPressed(KeyEvent e) {
		if (e.getCode().toString().equals("ENTER")) {

		}
		if (e.getSource().equals(textfield1)) {
			textfield2.clear();
		} else {
			textfield1.clear();
		}
		if(e.getCode().toString().equals("ENTER"))
		{
			if (radio1.isSelected()) {
				if (!textfield2.getText().isEmpty()) {
					textfield1.setText(String.format("%.02f", ((Double.parseDouble(textfield2.getText())/1.609344))));
				} 
				if (!textfield1.getText().isEmpty()){
					textfield2.setText(String.format("%.02f", ((Double.parseDouble(textfield1.getText())*1.609344))));
				}
			} else if (radio2.isSelected()) {
				if (!textfield2.getText().isEmpty()) {
					textfield1.setText(String.format("%.02f", ((Double.parseDouble(textfield2.getText())-32)/9*5)));
				} 
				if (!textfield1.getText().isEmpty()) {
					textfield2.setText(String.format("%.02f", ((Double.parseDouble(textfield1.getText())*9/5)+32)));
				}
			} else if (radio3.isSelected()) {
				if (!textfield2.getText().isEmpty()) {
					textfield1.setText(String.format("%.02f", ((Double.parseDouble(textfield2.getText())/0.45359237))));
				} 
				if (!textfield1.getText().isEmpty()) {
					textfield2.setText(String.format("%.02f", ((Double.parseDouble(textfield1.getText())*0.45359237))));
				}
			}
		}
	}
}