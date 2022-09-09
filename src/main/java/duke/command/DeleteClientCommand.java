package duke.command;

import duke.*;
import duke.task.TaskList;

public class DeleteClientCommand extends Command {
    int phoneNumber;

    public DeleteClientCommand(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Runs command that will do various changes based on the command.
     *
     * @param taskList       task list.
     * @param storage        files storing task list.
     * @param clientList
     * @return String response of Duke regarding user input
     * @throws DukeException if error occurs during execution of command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        Client deletedClient = clientList.delete(phoneNumber);
        new SaveClientListCommand().execute(taskList, storage, clientList);
        return CommandOutputs.showDeletedClient(deletedClient);
    }
}
