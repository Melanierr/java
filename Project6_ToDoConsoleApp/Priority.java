public enum Priority {
    IMMINENT(1, "High"),
    IMPORTANT(2, "Medium"),
    NORMAL(3, "Low");

    private final String type;
    private final int level;

    Priority(int level, String type){
        this.level = level;
        this.type = type;
    }

    public int getLevel(){
        return this.level;
    }
}