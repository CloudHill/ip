package sam;

import java.nio.file.Path;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;

import sam.command.Command;
import sam.command.ExitCommand;
import sam.parser.Parser;
import sam.storage.SamLoadFailedException;
import sam.storage.Storage;
import sam.task.TaskList;

/**
 * Represents the main Sam program.
 */
public class Sam extends Application {
    private static Sam samInstance;

    public static Sam getSamInstance() {
        return samInstance;
    }

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Sam instance.
     */
    public Sam() {
        Path savePath = Path.of("data", "sam.txt");

        ui = new Ui();
        storage = new Storage(savePath);
        tasks = new TaskList();

        try {
            storage.load(tasks);
        } catch (SamLoadFailedException e) {
            ui.respond(e.getMessage());
        }
    }

    /**
     * Parses the input into a command and executes it.
     * 
     * @param input The command to execute.
     */
    public void issueCommand(String input) {
        try {
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            if (c instanceof ExitCommand) {
                ui.disable();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        } catch (SamException e) {
            ui.respond(e.getMessage());
        }
    }

    @Override
    public void init() {
        samInstance = this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ui.setStage(stage);
        stage.show();
    }
}
