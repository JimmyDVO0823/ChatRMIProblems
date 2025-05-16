package Model;

public class Facade {
    private static Facade instance;
    private Facade() {

    }
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }
}
