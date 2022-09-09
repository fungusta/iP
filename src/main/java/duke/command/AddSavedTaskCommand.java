package duke.command;

import duke.*;
import duke.task.*;

/**
 * Represents a command to transfer saved file content into task list.
 */
public class AddSavedTaskCommand extends Command {
    private static final Instructions TODO_INSTRUCTION = Instructions.todo;
    private static final String TODO_TIMING = "";
    private final String task;
    private final Instructions type;
    private final String timing;
    private final boolean done;

    /**
     * Constructor for AddSavedTaskCommand class,
     * to add todo tasks.
     *
     * @param task task in String.
     * @param done whether task is done.
     *             true if marked.
     *             false if unmarked.
     */
    public AddSavedTaskCommand(String task, boolean done) {
        this.task = task;
        this.type = TODO_INSTRUCTION;
        this.timing = TODO_TIMING;
        this.done = done;
    }

    /**
     * Another Constructor for AddSavedTaskCommand class,
     * to add deadline or event tasks.
     *
     * @param task task in String.
     * @param instruction specific instruction.
     * @param timing timing in String.
     * @param done whether task is done.
     *             true if marked.
     *             false if unmarked.
     */
    public AddSavedTaskCommand(String task, Instructions instruction, String timing, boolean done) {
        this.task = task;
        this.type = instruction;
        this.timing = timing;
        this.done = done;
    }

    /**
     * Adds task to task list.
     *
     * @param taskList list of tasks.
     * @param storage files storing task list.
     * @param clientList list of clients.
     * @return nothing.
     * @throws DukeException if format of save file is wrong.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
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
            throw new DukeException("Format of saved file is wrong");
        }
        taskList.add(savedTask);
        return null;
    }
}
