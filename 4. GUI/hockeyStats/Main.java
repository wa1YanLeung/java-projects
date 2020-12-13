package hockeyStats;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ArrayList<String> arrayList = new ArrayList<String>(); 
			Scanner scanner = new Scanner(new FileInputStream("mp3_hockey_stats.txt"));
			String[] splitArr, stringArr;
			int[] intArr;
			double max = 0;
			GridPane root = new GridPane();

			while(scanner.hasNextLine()) {
				arrayList.add(scanner.nextLine());
			}
			scanner.close();

			stringArr = new String[arrayList.size()];
			intArr = new int[arrayList.size()];

			for (int i = 0; i < arrayList.size(); i++) {
				splitArr = arrayList.get(i).split(",");
				stringArr[i] = splitArr[0];
				intArr[i] = Integer.parseInt(splitArr[1]);
			}

			for (int i : intArr) {
				if (i > max) {
					max = i;
				}
			}

			for (int i = 0; i < intArr.length; i++) {				
				root.add(new Label(stringArr[i]), 0, i);

				Rectangle rectangle = new Rectangle();
				rectangle.heightProperty().bind(root.heightProperty().divide(stringArr.length));

				rectangle.widthProperty().bind(root.widthProperty().subtract(75).multiply(intArr[i]/max));
				root.add(rectangle, 1, i);

			}

			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}