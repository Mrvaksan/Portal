import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Teacher[] teachersList=new Teacher[5];
        Student[] studentsList=new Student[5];
        Courses[] courseList=new Courses[5];
        int studentCount=0;
        int teacherCount=0;
        int courseCount=0;

        Scanner teachersFile;
        Scanner studentsFile;
        Scanner coursesFile;
        try {
            teachersFile=new Scanner(new File("teachers.txt"));
            studentsFile=new Scanner(new File("students.txt"));
            coursesFile=new Scanner(new File("courses.txt"));

        } catch (FileNotFoundException e) {
            //Dosya bulunamadığında hata mesajı yazdırıp programdan çıkıyoruz.
            System.out.println("Dosya bulunamadı.");
            return;
        }

        teachersFile.useDelimiter(",|\\n");
        studentsFile.useDelimiter(",|\\n");
        coursesFile.useDelimiter(",|\\n");

        while (teachersFile.hasNext()) {

            String t_name;
            int t_id;
            String t_password;
            int t_branch;

            t_name=teachersFile.next();
            t_id=teachersFile.nextInt();
            t_password=teachersFile.next();
            t_branch=teachersFile.nextInt();

            teachersList[teacherCount]=new Teacher(t_name,t_id,t_password,t_branch);
            teacherCount++;

            if(teacherCount==5){
                break;
            }
        }
        while (studentsFile.hasNext()) {

            String s_name;
            String s_surname;
            int s_id;
            String s_password;

            s_name=studentsFile.next();
            s_surname=studentsFile.next();
            s_id=studentsFile.nextInt();
            s_password=studentsFile.next();

            studentsList[studentCount]=new Student(s_name,s_surname,s_id,s_password);
            studentCount++;

            if(studentCount==5){
                break;
            }
        }
        while (coursesFile.hasNext()) {

            int c_index;
            int c_weight;
            String c_name;

            c_index=coursesFile.nextInt();
            c_weight=coursesFile.nextInt();
            c_name=coursesFile.next();

            courseList[courseCount]=new Courses(c_index,c_weight,c_name);
            courseCount++;

            if(courseCount==5){
                break;
            }
        }


        int option;
        int t_id;
        String t_password;
        int s_id;
        String s_password;


        while (true) {
            System.out.println("Öğrenci ve Öğretmen Bilgi Sistemi");
            System.out.println("1. Öğrenci Girişi");
            System.out.println("2. Öğretmen Girişi");
            System.out.println("3. Çıkış");

            option=scan.nextInt();
            t_id=scan.nextInt();
            t_password=scan.next();
            s_id=scan.nextInt();
            s_password=scan.next();

            switch (option) {
                case 1:
                    //Öğrenci kullanıcı adı ve şifre sorulacak.
                    //Öğrenci listesinden öğrencinin olup olmadığını sorgular.
                    //Öğrenci yoksa "Kayıt Bulunamadı" uyarısı ver.
                    //Öğrenci varsa sisteme giriş yap.

                    System.out.println("Kullancı Adı: ");
                    s_id=scan.nextInt();
                    System.out.println("Şifre: ");
                    s_password=scan.next();

                    for(int i=0;i<studentCount;i++){
                        if(studentsList[i].equals(s_id) && studentsList[i].equals(s_password)) {
                            //Öğreci bilgi sistemine giriş yap.
                            System.out.println("Öğrenci Bilgi Sistemine Giriş Yaptınız.");
                            System.out.println("1. Karne Görüntüle");
                            System.out.println("2. Çıkış");

                            option=scan.nextInt();

                            switch (option) {
                                case 1:
                                    //Karne görüntüle

                                case 2:
                                    //Ana menüye dön

                            }
                        }else{
                                System.out.println("Kayıt Bulunamadı!");
                                break;

                        }
                    }
                case 2:
                    //Öğretmen kullanıcı adı ve şifre sorulacak.
                    //Öğretmen listesinden öğretmenin olup olmadığı sorgulanacak.
                    //Öğretmen yoksa "Kayıt Bulunamadı" uyarısı verecek.
                    //Öğretmen varsa sisteme giriş yapılacak.

                    System.out.println("Kullancı Adı: ");
                    t_id=scan.nextInt();
                    System.out.println("Şifre: ");
                    t_password=scan.next();

                    for(int i=0;i<teacherCount;i++){
                        if(teachersList[i].equals(t_id) && teachersList[i].equals(t_password)) {
                            //Öğretmen sistemine giriş yap.
                            System.out.println("Öğretmen Sistemine Giriş Yaptınız.");
                            System.out.println("1. Öğrenci Seç");
                            System.out.println("2. Çıkış");

                            option=scan.nextInt();

                            switch (option) {
                                case 1:
                                   //Öğrenci id numarası ile öğrenci seç

                                   //Öğrencinin,notunu değiştir,devamsızlık düzenle,geri

                                    System.out.println("Yapmak İstediğiniz İşlemi Seçiniz.");
                                    System.out.println("1. Notunu Değiştir");
                                    System.out.println("2. Devamsızlık Düzenle");
                                    System.out.println("3. Geri");

                                    option=scan.nextInt();

                                    switch (option){
                                        case 1:
                                            //İlgili dersin notunu değiştir.
                                            break;
                                        case 2:
                                            //Devamsızlık düzenle
                                            break;
                                        case 3:
                                            //Geri git,öğretmen bilgi sistemine geri dön.
                                    }


                                case 2:
                                    //Ana menüye dön

                            }
                        }else{
                            System.out.println("Kayıt Bulunamadı!");
                            break;

                        }
                    }
                case 3:
                    //Çıkış yap,programı sonlandır.




            }
        }




















    }
}