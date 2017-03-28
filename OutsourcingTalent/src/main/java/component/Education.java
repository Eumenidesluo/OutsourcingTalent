package component;

public enum Education {
	Secondary("中专"),
	Undergraduate("本科"),
	Maste("硕士"),
	Doctor("博士"),
	Junior("大专");
	private String name;
    private Education(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
