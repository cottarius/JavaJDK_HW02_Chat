package server.server;

import server.client.Client;
import server.client.ClientGUI;
import server.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private Repository repository;
    private ServerView serverView;
    List<Client> clients;
    private boolean work;

    public Server(ServerView serverView, Repository repository){
        this.repository = repository;
        this.serverView = serverView;
        clients = new ArrayList<>();
    }

    public void message(String text){
        if (!work){
            return;
        }
        showMessage(text);
        answerAll(text);
        saveInLog(text);
    }

    private void showMessage(String text) {
        serverView.showMessage(String.format("%s\n", text));
    }

    private void saveInLog(String text) {
        repository.saveInLog(text);
    }

    private void answerAll(String text){
        for (Client client : clients){
            client.answerFromServer(text);
        }
    }
    public boolean connectUser(Client client) {
        if(!work){
            return false;
        }
        clients.add(client);
        return true;
    }

    public void disconnectUser(Client client) {
        clients.remove(client);
        if(client != null){
            client.disconnectFromServer();
        }
    }

    public void serverStop(){
        if (!work){
            showMessage("Сервер уже был остановлен");
        } else {
            work = false;
            while (!clients.isEmpty()){
                disconnectUser(clients.get(clients.size()-1));
            }
            showMessage("Сервер остановлен!");
        }
    }
    public void serverStart(){
        if(work){
            showMessage("Сервер уже был запущен");
        } else {
            work = true;
            showMessage("Сервер запущен");
        }
    }
    public String getHistory() {
        return repository.readLog();
    }
}
