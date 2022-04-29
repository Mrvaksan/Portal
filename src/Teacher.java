import java.util.Scanner;

public class Teacher {
    Scanner scan = new Scanner(System.in);
    String t_name;
    int t_id;
    String t_password;
    int t_branch;

    public Teacher(String t_name, int t_id, String t_password, int t_branch) {
        this.t_name = t_name;
        this.t_id = t_id;
        this.t_password = t_password;
        this.t_branch = t_branch;
    }

    void chooseStudent(Student studentList) {
        System.out.println("Öğrencinin Numarası: ");
        int s_id;
        s_id = scan.nextInt();
    }

    public void changeStudentGrade(Student student, int grade) {
        student.setGrade(grade, t_branch);
    }

    public void changeStudentNonAttendance(Student student, int nonattendance) {
        student.setNonAttendance(nonattendance, t_branch);
    }
}
