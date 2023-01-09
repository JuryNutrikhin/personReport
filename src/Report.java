import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Report {
    int premium = 10000;
    String paymentType;
    int sum;
    int tax;
    int k = 1; //оплата частично в валюте.Задание 4

    int offshoreTax = 1;//задание 5

    String fileName = "Person.bin";
    List<Person> people=new ArrayList<>();
    int curs = 60;
    int dailyRate = 1200;// дневная ставка
    int hourlyRate = 200;//почасовая ставка

    int pieceworkRate = 2400; //сдельная ставка

    public Report() {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                people = (List<Person>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Заполнение файла данными
    public void SavePerson() {


        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(people);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



    void report1() {
        System.out.printf("%20s %20S %20S", "ФИО", "Вид оплаты", "Сумма\n");
        for (Person p : people) {
            if (p.getPaymentType() == 1) {
                sum = p.getHoursWorked() * dailyRate;
                paymentType = "ставка";

                tax = 20;
            } else if (p.getPaymentType() == 2) {
                sum = p.getHoursWorked() * hourlyRate;
                paymentType = "почасовая";

            } else {
                sum = p.getHoursWorked() * pieceworkRate;
                paymentType = "сдельная";

            }
            System.out.printf("%20s %20s %20d\n", p.getName(), paymentType, sum);
        }
        System.out.println();
    }
//Задание 2
//Для предыдущего задания вывести отчет с учетом налогов. Для сотрудников, которые на ставке и почасовой оплате
//– налог 20%.
//Для сотрудников со сдельной оплатой труда – 15%.
//Программа должна уметь выводить отчет по всем сотрудникам фирмы о заработной плате в виде:
    void report2() {

        System.out.printf("%20s %20s %20s %20s", "ФИО", "Налог,% ", "Сумма", "К оплате(руб)\n");
        for (Person p : people) {
            if (p.getPaymentType() == 1) {
                sum = p.getHoursWorked() * dailyRate;


                tax = 20;
            } else if (p.getPaymentType() == 2) {
                sum = p.getHoursWorked() * hourlyRate;

                tax = 20;

            } else {
                sum = p.getHoursWorked() * pieceworkRate;
                tax = 15;

            }
            System.out.printf("%20s %20d  %20d %20d\n", p.getName(), tax, sum, sum - ((sum / 100) * tax));
        }
        System.out.println();
    }
//Задание 3
//На основе предыдущего задания сделать новый отчет
//таким образом, что для сотрудников, у которых нет детей,
//ставка налога выше на 5%.
    void report3() {

        System.out.printf("%20s %20s %20s %20s", "ФИО", "Налог,% ", "Сумма", "К оплате(руб)\n");
        for (Person p : people) {
            if (p.getPaymentType() == 1) {
                sum = p.getHoursWorked() * dailyRate;


                tax = 20;
            } else if (p.getPaymentType() == 2) {
                sum = p.getHoursWorked() * hourlyRate;

                tax = 20;

            } else {
                sum = p.getHoursWorked() * pieceworkRate;
                tax = 15;

            }
            if (p.getPaymentType() != 1) tax = tax + 5;
            System.out.printf("%20s %20d  %20d %20d\n", p.getName(), tax, sum, sum - ((sum / 100) * tax));
        }
        System.out.println();
    }
//Задание 4
//На основе предыдущего задания переделать отчет, с учетом того, что сотрудники с почасовой оплатой, половину
//зарплаты получают в валюте (тугриках), по курсу на день
//начисления заработной платы.
//ФИО Налог, % Сумма (грн)

    void report4() {

        System.out.printf("%20s %20s %20s %20s", "ФИО", "Налог,% ", "Сумма", "К оплате(руб/$)\n");
        for (Person p : people) {
            if (p.getPaymentType() == 1) {
                sum = p.getHoursWorked() * dailyRate;


                tax = 20;
            } else if (p.getPaymentType() == 2) {
                sum = p.getHoursWorked() * hourlyRate;
                k = 2;
                tax = 20;

            } else {
                sum = p.getHoursWorked() * pieceworkRate;
                tax = 15;

            }

            System.out.printf("%20s %20d  %20d %10d/%d\n", p.getName(), tax, sum, sum - ((sum / 100) * tax) / k, (k == 2 ? (sum - ((sum / 100) * tax)) / curs : 0));
        }
        System.out.println();
    }
//Задание 5
//Фирма переводит часть сотрудников в офшорную зону.
//Сотрудники, находящиеся в офшоре, не платят налогов. Создать новый отчет с учетом данного нововведения.
    void report5() {

        System.out.printf("%20s %20s %20s %20s ", "ФИО", "Налог,% ", "Сумма", "К оплате(руб/$)\n");
        for (Person p : people) {
            if (p.getPaymentType() == 1) {
                sum = p.getHoursWorked() * dailyRate;


                tax = 20;
            } else if (p.getPaymentType() == 2) {
                sum = p.getHoursWorked() * hourlyRate;
                k = 2;
                tax = 20;

            } else {
                sum = p.getHoursWorked() * pieceworkRate;
                tax = 15;

            }
            if (p.getOffshore() == 1) tax = 0;

            System.out.printf("%20s %20d  %20d %10d/%d\n", p.getName(), tax, sum, sum - ((sum / 100) * tax) / k, (k == 2 ? (sum - ((sum / 100) * tax)) / curs : 0));
        }
        System.out.println();
    }
//Задание 6
//Фирма вводит премии сотрудникам, которые работали
//больше 200 часов в месяц, но не находятся в офшоре. Премии должны суммироваться в основную зарплату. Создать
//новый отчет с учетом изменений.
    void report6() {

        System.out.printf("%20s %20s %20s %20s ", "ФИО", "Налог,% ", "Сумма", "К оплате(руб/$)\n");
        for (Person p : people) {
            if (p.getPaymentType() == 1) {
                sum = p.getHoursWorked() * dailyRate;


                tax = 20;
            } else if (p.getPaymentType() == 2) {
                sum = p.getHoursWorked() * hourlyRate;
                k = 2;
                tax = 20;

            } else {
                sum = p.getHoursWorked() * pieceworkRate;
                tax = 15;

            }
            if (p.getOffshore() == 1) {
                tax = 0;

            }

            if (p.getOffshore() == 1) {
                offshoreTax = 0;
                premium = 0;
            }
            if(p.getPaymentType()>200) sum=sum+premium;


            System.out.printf("%20s %20d  %20d %10d/%d\n", p.getName(), tax, sum, sum - ((sum / 100) * tax) / k, (k == 2 ? (sum - ((sum / 100) * tax)) / curs : 0));
        }
        System.out.println();
    }


}
