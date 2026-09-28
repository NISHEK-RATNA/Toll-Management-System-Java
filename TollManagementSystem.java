import java.util.*;

public class TollManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static HashSet<String> numbers = new HashSet<>();
    static HashMap<String, Integer> count = new HashMap<>();

    static String[] types = {"Bike", "Car", "Bus", "Truck"};

    static Vehicle createVehicle(String type, String number)
            throws InvalidVehicleException {

        for (String t : types) {
            if (t.equalsIgnoreCase(type)) {

                switch (t) {
                    case "Bike": return new Bike(number);
                    case "Car": return new Car(number);
                    case "Bus": return new Bus(number);
                    case "Truck": return new Truck(number);
                }
            }
        }

        throw new InvalidVehicleException("Invalid vehicle type!");
    }

    static void addVehicle() {
        try {
            System.out.print("Vehicle Number: ");
            String number = sc.nextLine();

            System.out.print("Vehicle Type: ");
            String type = sc.nextLine();

            Vehicle v = createVehicle(type, number);

            vehicles.add(v);
            numbers.add(number);
            count.put(v.type(), count.getOrDefault(v.type(), 0) + 1);

            System.out.println("Toll = Rs." + v.toll());
            System.out.println("Vehicle added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewVehicles() {
        double total = 0;

        for (Vehicle v : vehicles) {
            System.out.println(
                v.getNumber() + " | " +
                v.type() + " | Rs." + v.toll()
            );

            total += v.toll();
        }

        System.out.println("Total Collection = Rs." + total);
    }

    static void summary() {

        for (String type : types) {

            int matches = 0;

            for (Vehicle v : vehicles) {
                if (v.type().equals(type))
                    matches++;
            }

            System.out.println(
                type + " = " + matches + " vehicle(s)"
            );
        }

        System.out.println("Unique Vehicles = " + numbers.size());
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n--- TOLL MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Vehicle Summary");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addVehicle();
                    break;

                case 2:
                    viewVehicles();
                    break;

                case 3:
                    summary();
                    break;

                case 4:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);
    }
}


class InvalidVehicleException extends Exception {
    InvalidVehicleException(String msg) {
        super(msg);
    }
}


abstract class Vehicle {

    private String number;

    Vehicle(String number) {
        this.number = number;
    }

    String getNumber() {
        return number;
    }

    abstract String type();
    abstract double toll();
}


class Bike extends Vehicle {

    Bike(String n) {
        super(n);
    }

    String type() {
        return "Bike";
    }

    double toll() {
        return 20;
    }
}


class Car extends Vehicle {

    Car(String n) {
        super(n);
    }

    String type() {
        return "Car";
    }

    double toll() {
        return 50;
    }
}


class Bus extends Vehicle {

    Bus(String n) {
        super(n);
    }

    String type() {
        return "Bus";
    }

    double toll() {
        return 100;
    }
}


class Truck extends Vehicle {

    Truck(String n) {
        super(n);
    }

    String type() {
        return "Truck";
    }

    double toll() {
        return 150;
    }
}