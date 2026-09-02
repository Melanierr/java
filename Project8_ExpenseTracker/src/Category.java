public enum Category {
    SALARY("Salary"),
    FREELANCE("Freelance"),

    FOOD("Food"),
    TRANSPORT("Transport"),
    ENTERTAINMENT("Entertainment");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getCategory() {
        return description;
    }
}