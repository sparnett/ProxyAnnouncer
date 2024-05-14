package org.sparnet.proxyannouncer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class ProxyAnnouncer extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin abilitato!");
        // Registra il comando
        getProxy().getPluginManager().registerCommand(this, new AnnuncioCommand());
    }

    private class AnnuncioCommand extends Command {

        public AnnuncioCommand() {
            super("annuncio", "admin.annuncio");
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            if (args.length == 0) {
                sender.sendMessage("Utilizzo: /annuncio <messaggio>");
                return;
            }

            StringBuilder message = new StringBuilder();
            for (String word : args) {
                message.append(word).append(" ");
            }

            // Aggiungi il prefix "[Annuncio]" al messaggio
            String fullMessage = "[Annuncio] " + ChatColor.RED + message.toString();

            // Invia il messaggio a tutti i server collegati
            ProxyServer.getInstance().broadcast(fullMessage);
        }
    }
}
