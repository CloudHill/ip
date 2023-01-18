public class SamException extends Exception {
  public SamException(String message) {
    super(message);
  }
}

class SamUnknownCommandException extends SamException {
  public SamUnknownCommandException() {
    super("Sorry, I don't know what that means");
  }
}

class SamMissingTaskException extends SamException {
  public SamMissingTaskException() {
    super("Oops, you forgot to specify a task!");
  }
}

class SamMissingTaskTitleException extends SamException {
  public SamMissingTaskTitleException() {
    super("Oops, you forgot a title for your task!");
  }
}

class SamMissingTaskArgException extends SamException {
  public SamMissingTaskArgException() {
    super("Oops, you're missing an argument!");
  }
}

class SamMissingTaskValueException extends SamException {
  public SamMissingTaskValueException() {
    super("Oops, an argument is missing a value!");
  }
}

class SamInvalidTaskException extends SamException {
  public SamInvalidTaskException() {
    super("Oops, that task does not exist!");
  }
}
