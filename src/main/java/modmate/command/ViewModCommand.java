package modmate.command;

import modmate.log.Log;
import modmate.CommandCenter;
import modmate.user.User;

public class ViewModCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: viewmod <mod code or name>");
            return;
        }

        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";
        Log.saveLog("[MAIN]   Viewing mod details for: " + inputCodeOrName);

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            System.out.println(mod.getDetailedString());
            Log.saveLog("[MAIN]   Mod details displayed.");
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            Log.saveLog("[MAIN]   Mod '" + inputCodeOrName + "' not found.");
        });
    }
}
