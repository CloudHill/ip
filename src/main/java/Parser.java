import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
  public static LocalDate parseDate(String input) throws SamInvalidDateException {
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"));
    } catch (DateTimeParseException e) {
      throw new SamInvalidDateException();
    }
  }

  public static Map<String, String> parseTaskArgs(String input)
      throws SamMissingTaskTitleException, SamMissingTaskValueException {
    if (input.strip().charAt(0) == '/') {
      throw new SamMissingTaskTitleException();
    }
    
    Map<String, String> taskArgs = new HashMap<>();
    String[] args = input.split(" +/", 2);
    taskArgs.put("title", args[0]);

    if (args.length > 1) {
      for (String arg : args[1].split(" +/")) {
        String[] keyValue = splitFirst(arg);
        if (keyValue.length <= 1) throw new SamMissingTaskValueException();
        taskArgs.put(keyValue[0], keyValue[1]);
      }
    }

    return taskArgs;
  }

  public static String[] splitFirst(String input) {
    return input.split(" ", 2);
  }

  public static Command getCommand(String input) throws SamUnknownCommandException {
    Command command = null;
    for (Command c : Command.values())
      if (c.matches(input)) command = c;

    if (command == null) {
      throw new SamUnknownCommandException();
    }
    return command;
  }
}
