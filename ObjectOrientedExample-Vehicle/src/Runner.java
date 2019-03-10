import model.car.PoliceCar;
import model.car.RaceCar;
import model.garage.Garage;
import model.general.CarSeat;
import model.general.Vendor;
import model.general.Wheel;

public class Runner {
    private static String SPARATE = "************************************************";
    public static void main(String[] args) {
        CarSeat[] policeCarSeats = new CarSeat[4];
        CarSeat[] raceCarSeats = new CarSeat[2];

        Wheel[] policeCarWheels = new Wheel[4];
        Wheel[] raceCarWheels = new Wheel[4];

        for(int i = 0; i < policeCarSeats.length; i++){
            policeCarSeats[i] = new CarSeat(new Vendor("Ferrari", "Italy"), "Leather");
        }

        for(int i = 0; i < raceCarSeats.length; i++){
            raceCarSeats[i] = new CarSeat(new Vendor("Peugeot", "France"), "Nylon");
        }

        for(int i = 0; i < policeCarWheels.length; i++){
            policeCarWheels[i] = new Wheel(new Vendor("Bridgestone", "Japan"), "Tire");
        }

        for(int i = 0; i < raceCarWheels.length; i++){
            raceCarWheels[i] = new Wheel(new Vendor("Dunlop", "America"), "Tire");
        }

        PoliceCar policeCar = new PoliceCar("Rowan Police Car", "Black", new Vendor("Ferrari", "Italy"), policeCarWheels, 300, 2, 3, policeCarSeats);
        RaceCar raceCar = new RaceCar("Dimas Race Car", "White", new Vendor("Peugeot", "France"), raceCarWheels, 350, 2, 3, raceCarSeats);

        System.out.println(policeCar.getInformation());
        policeCar.goStraight();
        policeCar.turnLeft();
        policeCar.turnRight();
        policeCar.beep();
        policeCar.turnOnLamp();
        policeCar.stop();

        System.out.println(SPARATE);

        System.out.println(raceCar.getInformation());
        raceCar.race();
        raceCar.goStraight();
        raceCar.turnLeft();
        raceCar.turnRight();
        raceCar.turbo();
        raceCar.stop();

        Garage garage = new Garage("Dimas's Garage", 12);

        garage.addVehicle(policeCar);
        garage.addVehicle(raceCar);
        garage.showVehicles();

    }
}
