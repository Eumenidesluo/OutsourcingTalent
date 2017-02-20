package component;

/**
 * Created by Eumenides on 2017/2/19.
 */
public enum Sex {
    male("man"),
    female("woman");

    private String name;
    private Sex(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
