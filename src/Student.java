public class Student {
    String s_name;
    String s_surname;
    int s_id;
    String s_password;
    int grades[] = new int[5];
    int nonattendances[] = new int[5];
    Courses[] courseList = new Courses[5];
    int nonAttendances;
    int akts;
    int weightedTotals;
    double gpa;

    public Student(String s_name, String s_surname, int s_id, String s_password) {
        this.s_name = s_name;
        this.s_surname = s_surname;
        this.s_id = s_id;
        this.s_password = s_password;
    }

    public void setGrade(int grade, int t_branch) {
        grades[t_branch] = grade;
    }

    public void setNonAttendance(int nonattendance, int t_branch) {
        nonattendances[t_branch] = nonattendance;
    }

    public void showTranscript() {
        System.out.printf("%-30s%30s\n", "İSİM:", s_name);
        System.out.printf("%-30s%30s\n", "SOYİSİM:", s_surname);
        System.out.printf("%-30s%30d\n", "OKUL NUMARASI:", s_id);
        System.out.println("___________________________NOTLAR___________________________");
        System.out.printf("%-20s%20s%20s\n", "DERS ADI", "AKTS", "ORTALAMA");

        for (int i = 0; i < grades.length; i++) {
            if (courseList[i] != null) {
                System.out.printf("%-20s%20d%20d\n", courseList[i].c_name, courseList[i].c_weight, grades[i]);
            }
        }
        System.out.println("____________________________________________________________");
        nonAttendances = 0;
        for (int i = 0; i < nonattendances.length; i++) {
            nonAttendances += nonattendances[i];
        }
        System.out.printf("%-30s%30d\n", "DEVAMSIZLIK", nonAttendances);
        System.out.println("____________________________________________________________");

        akts = 0;
        weightedTotals = 0;
        for (int i = 0; i < courseList.length; i++) {
            if (courseList[i] != null) {
                akts += courseList[i].c_weight;
                weightedTotals += courseList[i].c_weight * grades[i];
            }
        }
        gpa = weightedTotals / akts;
        System.out.printf("%-30s%30f\n", "GENEL ORTALAMA:", gpa);
    }
}
