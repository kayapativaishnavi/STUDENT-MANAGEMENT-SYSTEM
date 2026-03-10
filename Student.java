class Student {

    int id;
    String name;
    String course;
    int marks;

    Student(int id, String name, String course, int marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    void display() {
        System.out.println(id + " " + name + " " + course + " " + marks);
    }
}