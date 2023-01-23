package sam;

import sam.command.Command;
import sam.command.ExitCommand;
import sam.parser.Parser;
import sam.storage.SamLoadFailedException;
import sam.storage.Storage;
import sam.task.TaskList;

public class Sam {
  private Ui ui;
  private Storage storage;
  private TaskList tasks;
  private boolean live;

  public Sam(String first, String ...more) {
    ui = new Ui();
    storage = new Storage(first, more);
    tasks = new TaskList();
    live = false;
  }

  public void run() {
    live = true;
    ui.showLogo();
    ui.talk("Hello, I am Sam!");
    try {
      storage.load(tasks);
    } catch (SamLoadFailedException e) {
      ui.talk(e.getMessage());
    }
    while (live) {
      String input = ui.acceptInput();
      try {
        Command c = Parser.parseCommand(input);
        c.execute(tasks, ui, storage);
        live = !(c instanceof ExitCommand);
      } catch (SamException e) {
        ui.talk(e.getMessage());
      }
    }
  }

	public static void main(String[] args) {
    new Sam("data", "sam.txt").run();
  }
}
