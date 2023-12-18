package server.repository;

public interface Repository {
    public String readLog();
    public void saveInLog(String text);
}
