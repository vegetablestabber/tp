package modmate.command;

import modmate.user.User;

public interface Command {
    void execute(String[] args, User currentUser);
}
