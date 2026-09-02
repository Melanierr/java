public enum Type {
    INCOME("income"),
    EXPENSE("expense");

    private String type;
    private Category[] categories;

    Type(String type){
        this.type = type;
        if(type.equals("income")){
            this.categories = new Category[]{Category.SALARY, Category.FREELANCE};
        }
        else if(type.equals("expense")){
            this.categories = new Category[]{Category.FOOD, Category.ENTERTAINMENT, Category.TRANSPORT};
        }
        else{
            System.out.println("Invalid category.");
        }
    }
    public boolean hasCategory(Category category) {
        for (Category scan : categories) {
            if (scan == category){
                return true;
            }
        }
        return false;
    }
    public String getType(){
        return this.type;
    }
}
