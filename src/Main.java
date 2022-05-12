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
            //Dosya bulunamadığında hata mesajı yazdırıp programdan çıkıyoruz.
            System.out.println("Dosya Bulunamadı.");
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

            System.out.println("Öğrenci ve Öğretmen Bilgi Sistemi");
            System.out.println("1. Öğrenci Girişi");
            System.out.println("2. Öğretmen Girişi");
            System.out.println("3. Çıkış");

            System.out.print("Seçim Yapınız: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    //Öğrenci kullanıcı adı ve şifre sorulacak.
                    //Öğrenci listesinden öğrencinin olup olmadığını sorgular.
                    //Öğrenci yoksa "Kayıt Bulunamadı" uyarısı ver.
                    //Öğrenci varsa sisteme giriş yap.

                    System.out.print("Kullanıcı Adı: ");
                    s_id = scan.nextInt();
                    System.out.print("Şifre: ");
                    s_password = scan.next();

                    //Şifre doğru mu diye kontrol edelim
                    for (int i = 0; i < studentCount; i++) {

                        if (s_id == studentsList[i].s_id && s_password.equals(studentsList[i].s_password)) {
                            //Öğrenci bulundu
                            isAuthenticated = true;
                            userIndex = i;
                            break;
                        }
                    }
                    //Şifre doğru ise öğrenci portalı menüsü gelsin
                    if (isAuthenticated) {
                        boolean isTerminated = false;

                        while (!isTerminated) {
                            //Öğreci bilgi sistemine giriş yap.
                            System.out.println("Öğrenci Bilgi Sistemine Giriş Yaptınız.");
                            System.out.println("1. Karne Görüntüle");
                            System.out.println("2. Çıkış");

                            System.out.print("Seçim Yapınız: ");
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
                        System.out.println("Kayıt Bulunamadı!");
                    }
                    break;
                case 2:
                    //Öğretmen kullanıcı adı ve şifre sorulacak.
                    //Öğretmen listesinden öğretmenin olup olmadığı sorgulanacak.
                    //Öğretmen yoksa "Kayıt Bulunamadı" uyarısı verecek.
                    //Öğretmen varsa sisteme giriş yapılacak.

                    System.out.print("Kullanıcı Adı: ");
                    t_id = scan.nextInt();
                    System.out.print("Şifre: ");
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

                            //Öğretmen sistemine giriş yap.
                            System.out.println("Öğretmen Sistemine Giriş Yaptınız.");
                            System.out.println("1. Öğrenci Seç");
                            System.out.println("2. Çıkış");

                            System.out.print("Seçim Yapınız: ");
                            option = scan.nextInt();

                            switch (option) {
                                case 1:
                                    //Öğrenci id numarası ile öğrenci seç

                                    //Öğrencinin,notunu değiştir,devamsızlık düzenle,geri

                                    boolean isTerminatedStudentMenu = false;
                                    boolean isStudent = false;
                                    int studentIndex = 0;

                                    System.out.print("Öğrencinin Numarasını Giriniz: ");
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

                                            System.out.println("Yapmak İstediğiniz İşlemi Seçiniz.");
                                            System.out.println("1. Notunu Değiştir");
                                            System.out.println("2. Devamsızlık Düzenle");
                                            System.out.println("3. Geri");

                                            System.out.print("Seçim Yapınız: ");
                                            option = scan.nextInt();

                                            switch (option) {
                                                case 1:
                                                    //İlgili dersin notunu değiştir.
                                                    System.out.print("Öğrencinin Notunu Giriniz: ");
                                                    int grade = scan.nextInt();
                                                    teachersList[userIndex].changeStudentGrade(studentsList[studentIndex], grade);
                                                    break;
                                                case 2:
                                                    //Devamsızlık düzenle
                                                    System.out.print("Öğrencinin Devamsızlığını Güncelleyiniz: ");
                                                    int nonattendance = scan.nextInt();
                                                    teachersList[userIndex].changeStudentNonAttendance(studentsList[studentIndex], nonattendance);
                                                    break;
                                                case 3:
                                                    //Geri git,öğretmen bilgi sistemine geri dön.
                                                    isTerminatedStudentMenu = true;
                                                    break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Kayıt Bulunamadı.");
                                    }
                                    break;
                                case 2:
                                    //Ana menüye dön
                                    isTerminated = true;
                                    break;
                            }
                        }
                    }else {
                        System.out.println("Kayıt Bulunamadı!");
                    }
                    break;
                case 3:
                    //Çıkış yap,programı sonlandır.
                    return;
            }
        }
    }
}