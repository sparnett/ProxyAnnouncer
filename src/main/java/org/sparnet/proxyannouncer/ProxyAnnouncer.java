package org.sparnet.proxyannouncer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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

            // Ottieni il nome del server e del giocatore a cui il mittente è collegato
            String serverName = "Console";
            String playerName = "Console";
            if (sender instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) sender;
                serverName = player.getServer().getInfo().getName();
                playerName = player.getName();
            }

            StringBuilder message = new StringBuilder();
            for (String word : args) {
                message.append(word).append(" ");
            }

            // Aggiungi il prefix "&4[&4&lGLOBAL&4] &a[&a<server>&a] &f[<nicknameplayer>] &f" al messaggio
            String fullMessage = ChatColor.translateAlternateColorCodes('&', "&4[&4&lGLOBAL&4] &a[&a" + serverName + "&a] &f[&f" + playerName + "&f] &f") + message.toString();

            // Invia il messaggio a tutti i server collegati
            ProxyServer.getInstance().broadcast(fullMessage);
        }
    }
}
