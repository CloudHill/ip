package sam.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a user task.
 */
public abstract class Task {
    protected String title;
    protected boolean isDone;

    public Task(String title) {
        this(title, false);
    }

    /**
     * Constructs a new Task.
     *
     * @param title The title of the task.
     * @param isDone Indicates whether the task is done.
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a char representing the status of the task.
     *
     * @return An X if the task is done, or a blank space otherwise.
     */
    protected char getStatusIcon() {
        return isDone ? 'X' : ' ';
    }

    /**
     * Returns an integer representing the status of the task.
     *
     * @return A 1 if the task is done, or a 0 otherwise.
     */
    protected int getStatusNo() {
        return isDone ? 1 : 0;
    }

    /**
     * Formats the specified date to the format "MMM d yyyy"
     *
     * @param date The date to be formatted
     * @return A string representation of the date in the format "MMM d yyyy"
     */
    protected String formatDateDisplay(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the specified date to the format "d/M/yyyy"
     *
     * @param date The date to be formatted.
     * @return A string representation of the date in the format "d/M/yyyy"
     */
    protected String formatDateSave(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    /**
     * Returns a string to be used when saving the task to a file.
     *
     * @return A string representation of the task.
     */
    public abstract String toSaveFormat();
}
