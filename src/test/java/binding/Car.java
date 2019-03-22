package binding;

public class Car {

    private String regNumber;
    private String model;

    public Car setRegNumber(String regNumber) {
        this.regNumber = regNumber;
        return this;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }
}
