public class Student {
    private String name;
    private int age;
    private String studentId;
    private double gpa;

    public Student(String name, int age, String studentId, double gpa) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.gpa = gpa;
    }
    @Override
    public String toString() {
        return String.format("Name: %s\n Age: %d\n ID: %s\n GPA: %.1f", getName(), getAge(), getStudentId(), this.gpa);
    }
    public String toSaveString() {
        return String.format("%s,%d,%s,%.2f",
                name,
                age,
                studentId,
                gpa);
    }
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public String getStudentId() {
        return this.studentId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

}
