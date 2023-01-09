import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int paymentType;
    private  int children;
    private int offshore;//1 -> это в работает в офшоре , 0 нет
    private  int hoursWorked;


    public String getName() {
        return name;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public int getChildren() {
        return children;
    }

    public int getOffshore() {
        return offshore;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public Person(String name, int paymentType, int children, int offshore, int hoursWorked) {
        this.name = name;
        this.paymentType = paymentType;
        this.children = children;
        this.offshore = offshore;
        this.hoursWorked = hoursWorked;
    }
}
