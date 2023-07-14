package lv.side.AtlidzibasPlugin;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.ActionType;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class AtlidzibasPluginaKommanda implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("Usage: /darbuatlidziba <Job>");
            return true;
        }

        String jobName = args[0];
        Job job = Jobs.getJob(jobName);

        if (job == null) {
            player.sendMessage("Invalid job name.");
            return true;
        }

        player.sendMessage(ChatColor.GOLD + "Job: " + job.getName() + " Rewards for breaking:");

        Map<ActionType, List<JobInfo>> jobInfoList = job.getJobInfoList();

        for (Map.Entry<ActionType, List<JobInfo>> entry : jobInfoList.entrySet()) {
            ActionType actionType = entry.getKey();
            List<JobInfo> jobInfos = entry.getValue();

            for (JobInfo jobInfo : jobInfos) {
                String blockType = jobInfo.getName();
                double income = jobInfo.getBaseIncome();
                double experience = jobInfo.getBaseXp();

                player.sendMessage(ChatColor.YELLOW + blockType + ": Income - " + income + ", Experience - " + experience);
            }
        }

        return true;
    }
}
