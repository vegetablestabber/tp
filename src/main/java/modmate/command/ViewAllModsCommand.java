package modmate.command;

import modmate.log.LogUtil;
import modmate.user.User;
import modmate.CommandCenter;

import java.util.Map;

public class ViewAllModsCommand implements Command {

    public static final String CLI_REPRESENTATION = "viewallmods";

    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        logUtil.info("Viewing all mods.");

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
