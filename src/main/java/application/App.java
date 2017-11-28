package application;

public class App {

    public static void main(String[] args) {
        DIContainer diContainer = new DIContainer();
        Application eventManagement = diContainer.initialize();
        eventManagement.start();
    }
}
