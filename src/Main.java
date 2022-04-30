import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Teacher[] teachersList = new Teacher[5];
        Student[] studentsList = new Student[5];
        Courses[] courseList = new Courses[5];
        int studentCount = 0;
        int teacherCount = 0;
        int courseCount = 0;

        Scanner teachersFile;
        Scanner studentsFile;
        Scanner coursesFile;
        try {
            teachersFile = new Scanner(new File("teachers.txt"));
            studentsFile = new Scanner(new File("students.txt"));
            coursesFile = new Scanner(new File("courses.txt"));

        } catch (FileNotFoundException e) {
            //Dosya bulunamad���nda hata mesaj� yazd�r�p programdan ��k�yoruz.
            System.out.println("Dosya Bulunamad�.");
            return;
        }

        teachersFile.useDelimiter(",|\\n|\\r\\n");
        studentsFile.useDelimiter(",|\\n|\\r\\n");
        coursesFile.useDelimiter(",|\\n|\\r\\n");

        while (teachersFile.hasNext()) {

            String t_name;
            int t_id;
            String t_password;
            int t_branch;

            t_name = teachersFile.next();
            t_id = teachersFile.nextInt();
            t_password = teachersFile.next();
            t_branch = teachersFile.nextInt();

            teachersList[teacherCount] = new Teacher(t_name, t_id, t_password, t_branch);
            teacherCount++;

            if (teacherCount == 5) {
                break;
            }
        }
        while (coursesFile.hasNext()) {

            int c_index;
            int c_weight;
            String c_name;

            c_index = coursesFile.nextInt();
            c_weight = coursesFile.nextInt();
            c_name = coursesFile.next();

            courseList[courseCount] = new Courses(c_index, c_weight, c_name);
            courseCount++;

            if (courseCount == 5) {
                break;
            }
        }
        while (studentsFile.hasNext()) {

            String s_name;
            String s_surname;
            int s_id;
            String s_password;

            s_name = studentsFile.next();
            s_surname = studentsFile.next();
            s_id = studentsFile.nextInt();
            s_password = studentsFile.next();

            studentsList[studentCount] = new Student(s_name, s_surname, s_id, s_password);
            studentsList[studentCount].courseList = courseList;
            studentCount++;

            if (studentCount == 5) {
                break;
            }
        }

        int option;
        int t_id;
        String t_password;
        int s_id;
        String s_password;

        while (true) {
            boolean isAuthenticated = false;
            int userIndex = -1;

            System.out.println("��renci ve ��retmen Bilgi Sistemi");
            System.out.println("1. ��renci Giri�i");
            System.out.println("2. ��retmen Giri�i");
            System.out.println("3. ��k��");

            System.out.print("Se�im Yap�n�z: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    //��renci kullan�c� ad� ve �ifre sorulacak.
                    //��renci listesinden ��rencinin olup olmad���n� sorgular.
                    //��renci yoksa "Kay�t Bulunamad�" uyar�s� ver.
                    //��renci varsa sisteme giri� yap.

                    System.out.print("Kullan�c� Ad�: ");
                    s_id = scan.nextInt();
                    System.out.print("�ifre: ");
                    s_password = scan.next();

                    //�ifre do�ru mu diye kontrol edelim
                    for (int i = 0; i < studentCount; i++) {

                        if (s_id == studentsList[i].s_id && s_password.equals(studentsList[i].s_password)) {
                            //��renci bulundu
                            isAuthenticated = true;
                            userIndex = i;
                            break;
                        }
                    }
                    //�ifre do�ru ise ��renci portal� men�s� gelsin
                    if (isAuthenticated) {
                        boolean isTerminated = false;

                        while (!isTerminated) {
                            //��reci bilgi sistemine giri� yap.
                            System.out.println("��renci Bilgi Sistemine Giri� Yapt�n�z.");
                            System.out.println("1. Karne G�r�nt�le");
                            System.out.println("2. ��k��");

                            option = scan.nextInt();

                            switch (option) {
                                case 1:
                                    //Karne g�r�nt�le
                                    studentsList[userIndex].showTranscript();
                                    break;
                                case 2:
                                    //Ana men�ye d�n
                                    isTerminated = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Kay�t Bulunamad�!");
                    }
                    break;
                case 2:
                    //��retmen kullan�c� ad� ve �ifre sorulacak.
                    //��retmen listesinden ��retmenin olup olmad��� sorgulanacak.
                    //��retmen yoksa "Kay�t Bulunamad�" uyar�s� verecek.
                    //��retmen varsa sisteme giri� yap�lacak.

                    System.out.print("Kullan�c� Ad�: ");
                    t_id = scan.nextInt();
                    System.out.print("�ifre: ");
                    t_password = scan.next();

                    for (int i = 0; i < teacherCount; i++) {
                        if (t_id == teachersList[i].t_id && t_password.equals(teachersList[i].t_password)) {

                            isAuthenticated = true;
                            userIndex = i;
                            break;
                        }
                    }
                    if (isAuthenticated) {
                        boolean isTerminated = false;

                        while (!isTerminated) {

                            //��retmen sistemine giri� yap.
                            System.out.println("��retmen Sistemine Giri� Yapt�n�z.");
                            System.out.println("1. ��renci Se�");
                            System.out.println("2. ��k��");

                            option = scan.nextInt();

                            switch (option) {
                                case 1:
                                    //��renci id numaras� ile ��renci se�

                                    //��rencinin,notunu de�i�tir,devams�zl�k d�zenle,geri

                                    boolean isTerminatedStudentMenu = false;
                                    boolean isStudent = false;
                                    int studentIndex = 0;

                                    System.out.print("��rencinin Numaras�n� Giriniz: ");
                                    s_id = scan.nextInt();

                                    for (int i = 0; i < studentCount; i++) {
                                        if (s_id == studentsList[i].s_id) {
                                            isStudent = true;
                                            studentIndex = i;
                                            break;
                                        }
                                    }
                                    if (isStudent) {

                                        while (!isTerminatedStudentMenu) {

                                            System.out.println("Yapmak �stedi�iniz ��lemi Se�iniz.");
                                            System.out.println("1. Notunu De�i�tir");
                                            System.out.println("2. Devams�zl�k D�zenle");
                                            System.out.println("3. Geri");

                                            option = scan.nextInt();

                                            switch (option) {
                                                case 1:
                                                    //�lgili dersin notunu de�i�tir.
                                                    System.out.print("��rencinin Notunu Giriniz: ");
                                                    int grade = scan.nextInt();
                                                    teachersList[userIndex].changeStudentGrade(studentsList[studentIndex], grade);
                                                    break;
                                                case 2:
                                                    //Devams�zl�k d�zenle
                                                    System.out.print("��rencinin Devams�zl���n� G�ncelleyiniz: ");
                                                    int nonattendance = scan.nextInt();
                                                    teachersList[userIndex].changeStudentNonAttendance(studentsList[studentIndex], nonattendance);
                                                    break;
                                                case 3:
                                                    //Geri git,��retmen bilgi sistemine geri d�n.
                                                    isTerminatedStudentMenu = true;
                                                    break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Kay�t Bulunamad�.");
                                    }
                                    break;
                                case 2:
                                    //Ana men�ye d�n
                                    isTerminated = true;
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    //��k�� yap,program� sonland�r.
                    return;
            }
        }
    }
}