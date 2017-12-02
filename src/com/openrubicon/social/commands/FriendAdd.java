package com.openrubicon.social.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import com.openrubicon.social.RRPGSocial;
import com.openrubicon.social.classes.SocialProfile;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;

public class FriendAdd extends Command {
    @Override
    public String getCommandFormat() {
        return "friend add $ $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable interactable, ArrayList<DynamicPrimitive> args) {
        //Args:
        //[0] = Playername or "best"
        //[1] Optional playername for best friend

        if (((Player)interactable).getPlayer() != null)
            return;

        Player thePlayer = (Player) interactable;
        //Check friend list here.
        if(RRPGSocial.social == null){
            Bukkit.broadcastMessage("ITS NULL");
        }


        SocialProfile s= (SocialProfile) RRPGSocial.social.getProfile((OfflinePlayer)thePlayer.getPlayer());
        if(args.size() == 1){
            s.sendRequest(Bukkit.getPlayer(args.get(0).getString()));
        } else if (args.size() == 2) {
            if("best".equals(args.get(0).getString())) {
                s.sendBestRequest(Bukkit.getPlayer(args.get(1).getString()));
            } else {
                thePlayer.getPlayer().sendMessage("Incorrect usage for /friend add");
            }
        }
    }
}
