package com.openrubicon.social.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import com.openrubicon.social.RRPGSocial;
import com.openrubicon.social.classes.SocialProfile;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class FriendSendItem extends Command {
    @Override
    public String getCommandFormat() {
        return "friend item send $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable interactable, ArrayList<DynamicPrimitive> args) {
        if (((Player)interactable).getPlayer() != null)
            return;

        Player thePlayer = (Player) interactable;


        SocialProfile s= (SocialProfile) RRPGSocial.social.getProfile(thePlayer.getPlayer());
        if(args.size() == 1) {
            if(Bukkit.getPlayer(args.get(0).getString()).isOnline()) {
                s.sendItem(Bukkit.getPlayer(args.get(0).getString()));
                return;
            } else {
                thePlayer.getPlayer().sendMessage("That player is not online.");
            }
        } else {
            thePlayer.getPlayer().sendMessage("Incorrect usage for /senditem [player]");
        }
        return;
    }
}
