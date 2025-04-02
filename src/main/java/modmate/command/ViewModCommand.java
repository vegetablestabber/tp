package modmate.command;

import modmate.log.LogUtil;
import modmate.CommandCenter;
import modmate.user.User;

public class ViewModCommand implements Command {

    public static final String CLI_REPRESENTATION = "viewmod";

    private static final LogUtil logUtil = new LogUtil(ViewModCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: viewmod <mod code or name>");
            return;
        }

        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";
        logUtil.info("Viewing mod details for: " + inputCodeOrName);

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            System.out.println(mod.getDetailedString());
            logUtil.info("Mod details displayed.");
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            logUtil.info("Mod '" + inputCodeOrName + "' not found.");
        });
    }
}
