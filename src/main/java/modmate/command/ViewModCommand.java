package modmate.command;

import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

public class ViewModCommand extends Command {

    public static final String CLI_REPRESENTATION = "viewmod";

    private static final LogUtil logUtil = new LogUtil(ViewModCommand.class);

    public ViewModCommand(Input input) {
        super(input);

        if (input.getArgument().isEmpty()) {
            throw new CommandException(this, "Mod code or name cannot be empty");
        }
    }

    @Override
    public String getSyntax() {
        return CommandUtil.concatenate(
            CLI_REPRESENTATION,
            "<mod code or name>"
        );
    }

    @Override
    public String getDescription() {
        return "View details of a mod by its mod code or name.";
    }

    @Override
    public String getUsage() {
        return super.getUsage() + "  <mod code or name>: The code or name of the mod to view.";
    }

    @Override
    public void execute(User user) throws ApiException {
        String identifier = input.getArgument();
        logUtil.info("Viewing mod details for: " + identifier);

        Mod mod = NUSModsAPI.modFromIdentifier(identifier);
        System.out.println(mod.getDetailedString());

        logUtil.info("Mod details displayed.");
    }

}
