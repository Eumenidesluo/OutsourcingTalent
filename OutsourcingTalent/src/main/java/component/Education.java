package component;

public enum Education {
	Undergraduate("Undergraduate"),
	Maste("Maste"),
	Doctor("Doctor"),
	Junior("Junior");
	private String name;
    private Education(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
