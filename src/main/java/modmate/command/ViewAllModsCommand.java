package modmate.command;

import modmate.log.Log;
import modmate.user.User;
import modmate.CommandCenter;

import java.util.Map;

public class ViewAllModsCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        Log.saveLog("[MAIN]   Viewing all mods.");
        CommandCenter.allModCodesAndNames
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach((modCode) ->
                System.out.println(modCode.getKey()
                        + ": "
                        + modCode.getValue().getName()
                )
            );
    }
}
