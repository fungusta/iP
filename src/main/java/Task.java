import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    private String task;
    private boolean done;

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public void markTask() {
        done = true;
    }

    public void unmarkTask() {
        done = false;
    }

    public boolean getDone() {
        return done;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + task;
        }
        return "[ ] " + task;
    }

    public String toSaveString() {
        int d = done ? 1 : 0;
        return String.format("%d %s", d, task);
    }

    public LocalDateTime ConvertDateTime(String dateTime) throws DukeException {
        try {
            String[] split = dateTime.split(" ", 2);
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
            LocalDate date = LocalDate.parse(split[0], formatDate);
            LocalTime time = LocalTime.parse(split[1], formatTime);
            return LocalDateTime.of(date, time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and time needs to be in the format dd/MM/yyyy HHmm");
        }
    }
}
