public enum Type {
    INCOME("income"),
    EXPENSE("expense");

    private String type;
    Type(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
