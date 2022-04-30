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
            //Dosya bulunamadýðýnda hata mesajý yazdýrýp programdan çýkýyoruz.
            System.out.println("Dosya Bulunamadý.");
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

            System.out.println("Öðrenci ve Öðretmen Bilgi Sistemi");
            System.out.println("1. Öðrenci Giriþi");
            System.out.println("2. Öðretmen Giriþi");
            System.out.println("3. Çýkýþ");

            System.out.print("Seçim Yapýnýz: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    //Öðrenci kullanýcý adý ve þifre sorulacak.
                    //Öðrenci listesinden öðrencinin olup olmadýðýný sorgular.
                    //Öðrenci yoksa "Kayýt Bulunamadý" uyarýsý ver.
                    //Öðrenci varsa sisteme giriþ yap.

                    System.out.print("Kullanýcý Adý: ");
                    s_id = scan.nextInt();
                    System.out.print("Þifre: ");
                    s_password = scan.next();

                    //Þifre doðru mu diye kontrol edelim
                    for (int i = 0; i < studentCount; i++) {

                        if (s_id == studentsList[i].s_id && s_password.equals(studentsList[i].s_password)) {
                            //Öðrenci bulundu
                            isAuthenticated = true;
                            userIndex = i;
                            break;
                        }
                    }
                    //Þifre doðru ise öðrenci portalý menüsü gelsin
                    if (isAuthenticated) {
                        boolean isTerminated = false;

                        while (!isTerminated) {
                            //Öðreci bilgi sistemine giriþ yap.
                            System.out.println("Öðrenci Bilgi Sistemine Giriþ Yaptýnýz.");
                            System.out.println("1. Karne Görüntüle");
                            System.out.println("2. Çýkýþ");

                            option = scan.nextInt();

                            switch (option) {
                                case 1:
                                    //Karne görüntüle
                                    studentsList[userIndex].showTranscript();
                                    break;
                                case 2:
                                    //Ana menüye dön
                                    isTerminated = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Kayýt Bulunamadý!");
                    }
                    break;
                case 2:
                    //Öðretmen kullanýcý adý ve þifre sorulacak.
                    //Öðretmen listesinden öðretmenin olup olmadýðý sorgulanacak.
                    //Öðretmen yoksa "Kayýt Bulunamadý" uyarýsý verecek.
                    //Öðretmen varsa sisteme giriþ yapýlacak.

                    System.out.print("Kullanýcý Adý: ");
                    t_id = scan.nextInt();
                    System.out.print("Þifre: ");
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

                            //Öðretmen sistemine giriþ yap.
                            System.out.println("Öðretmen Sistemine Giriþ Yaptýnýz.");
                            System.out.println("1. Öðrenci Seç");
                            System.out.println("2. Çýkýþ");

                            option = scan.nextInt();

                            switch (option) {
                                case 1:
                                    //Öðrenci id numarasý ile öðrenci seç

                                    //Öðrencinin,notunu deðiþtir,devamsýzlýk düzenle,geri

                                    boolean isTerminatedStudentMenu = false;
                                    boolean isStudent = false;
                                    int studentIndex = 0;

                                    System.out.print("Öðrencinin Numarasýný Giriniz: ");
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

                                            System.out.println("Yapmak Ýstediðiniz Ýþlemi Seçiniz.");
                                            System.out.println("1. Notunu Deðiþtir");
                                            System.out.println("2. Devamsýzlýk Düzenle");
                                            System.out.println("3. Geri");

                                            option = scan.nextInt();

                                            switch (option) {
                                                case 1:
                                                    //Ýlgili dersin notunu deðiþtir.
                                                    System.out.print("Öðrencinin Notunu Giriniz: ");
                                                    int grade = scan.nextInt();
                                                    teachersList[userIndex].changeStudentGrade(studentsList[studentIndex], grade);
                                                    break;
                                                case 2:
                                                    //Devamsýzlýk düzenle
                                                    System.out.print("Öðrencinin Devamsýzlýðýný Güncelleyiniz: ");
                                                    int nonattendance = scan.nextInt();
                                                    teachersList[userIndex].changeStudentNonAttendance(studentsList[studentIndex], nonattendance);
                                                    break;
                                                case 3:
                                                    //Geri git,öðretmen bilgi sistemine geri dön.
                                                    isTerminatedStudentMenu = true;
                                                    break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Kayýt Bulunamadý.");
                                    }
                                    break;
                                case 2:
                                    //Ana menüye dön
                                    isTerminated = true;
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    //Çýkýþ yap,programý sonlandýr.
                    return;
            }
        }
    }
}