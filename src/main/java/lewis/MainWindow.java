package lewis;

import static java.lang.Thread.sleep;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lewis lewis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image lewisImage = new Image(this.getClass().getResourceAsStream("/images/lewis.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Lewis instance */
    public void setLewis(Lewis lewis) {
        this.lewis = lewis;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lewis.getResponse(input);
        if (response.equals("BYE")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(ByeCommand.getString(), lewisImage)
            );
            try {
                sleep(3000);
                Platform.exit();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, lewisImage)
        );
        userInput.clear();
    }
}
