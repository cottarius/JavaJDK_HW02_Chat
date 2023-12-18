package server;

import server.client.Client;
import server.client.ClientGUI;
import server.repository.DataStorage;
import server.server.Server;
import server.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
