package duke.command;

import duke.*;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Represents a command to transfer saved file content into task list.
 */
public class AddSavedInputCommand extends Command {
    private static final Instructions TODO_INSTRUCTION = Instructions.todo;
    private static final String TODO_TIMING = "";
    private final String task;
    private final Instructions type;
    private final String timing;
    private final boolean done;

    /**
     * Constructor for AddSavedInputCommand class,
     * to add todo tasks.
     *
     * @param task task in String.
     * @param done whether task is done.
     *             true if marked.
     *             false if unmarked.
     */
    public AddSavedInputCommand(String task, boolean done) {
        this.task = task;
        this.type = TODO_INSTRUCTION;
        this.timing = TODO_TIMING;
        this.done = done;
    }

    /**
     * Another Constructor for AddSavedInputCommand class,
     * to add deadline or event tasks.
     *
     * @param task task in String.
     * @param instruction specific instruction.
     * @param timing timing in String.
     * @param done whether task is done.
     *             true if marked.
     *             false if unmarked.
     */
    public AddSavedInputCommand(String task, Instructions instruction, String timing, boolean done) {
        this.task = task;
        this.type = instruction;
        this.timing = timing;
        this.done = done;
    }

    /**
     * Adds saved task into task list.
     *
     * @param taskList task list.
     * @param commandOutputs user interface of program.
     * @param storage files storing task list.
     *
     * @return String response of Duke regarding user input
     *
     * @throws DukeException if timing is of the wrong format.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) throws DukeException {
        Task savedTask;
        switch (type) {
        case todo:
            savedTask = new ToDos(task, done);
            break;
        case deadline:
            savedTask = new Deadlines(task, timing, done);
            break;
        case event:
            savedTask = new Events(task, timing, done);
            break;
        default:
            throw new DukeException("Unknown Error");
        }
        taskList.add(savedTask);
        return null;
    }
}
