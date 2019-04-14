package siit.ro.model;

import siit.ro.entity.Alert;
import siit.ro.entity.Car;
import siit.ro.entity.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  The DatabaseManager part of the Model layer of the MVC ,
 *  helps to connect to the dataBase - PostgreSQL,
 *  and helps with the request and response date from the Control layer
 */


public class DatabaseManager {
    private Connection connection;

    private static DatabaseManager instance;

    private DatabaseManager(String type, String host, String port, String dbName, String user, String pw) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }

        this.connection = buildConnection(type, host, port, dbName, user, pw);
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager("postgresql", "localhost", "5432", "carDB", "postgres", "qazasd");

        }
        return instance;
    }

    private Connection buildConnection(String type, String host, String port, String dbName, String user, String pw) {
        DriverManager.setLoginTimeout(60);  // wait 1 min; optional: DB may be busy, good to set a higher timeout
        try {
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append(type)
                    .append("://")
                    .append(host)
                    .append(":")
                    .append(port)
                    .append("/")
                    .append(dbName)
                    .append("?user=")
                    .append(user)
                    .append("&password=")
                    .append(pw).toString();
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Cannot connect to the database: " + e.getMessage());
        }
        return null;
    }

    /**
     * Owner Methods
     *
     *  getAllOwners - return all owners
     *  addOwner - add a owner to the SQL table
     *  deleteOwner - delete a owner from th SQL table
     *  updateOwner - update a owner from the SQL table
     *  getOwnerById - return data for o owner in relation to a specified Id
     *
     */

    public List<Owner> getAllOwners() {
        List<Owner> allOwners = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet owners = statement.executeQuery("SELECT * FROM owner");
            while (owners.next()) {
                Owner owner = new Owner();
                owner.setIdOwner(UUID.fromString(owners.getString(1)));
                owner.setName(owners.getString(2));
                owner.setTelephone(owners.getString(3));
                owner.setEmail(owners.getString(4));
                allOwners.add(owner);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allOwners;
    }

    public void addOwner(Owner owner) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO owner VALUES (?, ?, ?, ?)");
            insertStatement.setObject(1, owner.getIdOwner());
            insertStatement.setString(2, owner.getName());
            insertStatement.setString(3, owner.getTelephone());
            insertStatement.setString(4, owner.getEmail());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOwner(String ownerId) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM owner WHERE id_owner = ?");
            insertStatement.setObject(1, UUID.fromString(ownerId));
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOwner(Owner owner) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE owner SET name = ?, telephone = ?, email = ?  WHERE id_owner = ?");
            insertStatement.setObject(4, owner.getIdOwner());
            insertStatement.setString(1, owner.getName());
            insertStatement.setString(2, owner.getTelephone());
            insertStatement.setString(3, owner.getEmail());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Owner getOwnerById(String id_owner) {
        Owner owner = new Owner();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM owner WHERE id_owner = ?")) {
            statement.setObject(1, UUID.fromString(id_owner));
            ResultSet ownerResult = statement.executeQuery();
            ownerResult.next();
            owner.setIdOwner(UUID.fromString(ownerResult.getString(1)));
            owner.setName(ownerResult.getString(2));
            owner.setTelephone(ownerResult.getString(3));
            owner.setEmail(ownerResult.getString(4));
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return owner;
    }

    /**
     * Car Methods
     *
     *  getAllCars - return all cars
     *  addCar - add a car to the SQL table (it needs the owner Id)
     *  deleteCar - delete a car from th SQL table
     *  updateCar - update a car from the SQL table
     *  getCarById - return data for o car in relation to a specified Id
     *
     */


    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet cars = statement.executeQuery("select id_car,owner.id_owner, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner;");
            while (cars.next()) {
                Car car = new Car();
                car.setIdCar(UUID.fromString(cars.getString(1)));
                car.setIdOwner(UUID.fromString(cars.getString(2)));
                car.setName(cars.getString(3));
                car.setPlates(cars.getString(4));
                car.setRca(cars.getDate(5));
                car.setItp(cars.getDate(6));
                car.setRovinieta(cars.getDate(7));
                car.setFireExt(cars.getDate(8));
                car.setFirstAid(cars.getDate(9));

                allCars.add(car);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allCars;
    }

    public void addCars(Car car) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO car VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setObject(1, car.getIdCar());
            insertStatement.setObject(2, car.getIdOwner());
            insertStatement.setString(3, car.getPlates());
            insertStatement.setDate(4, car.getRca());
            insertStatement.setDate(5, car.getItp());
            insertStatement.setDate(6, car.getRovinieta());
            insertStatement.setDate(7, car.getFireExt());
            insertStatement.setDate(8, car.getFirstAid());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(Car car) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE car SET plates = ?, rca = ?, itp = ?, rovinieta = ?, fire_ext = ?, first_aid = ?  WHERE id_car = ?");
            insertStatement.setObject(7, car.getIdCar());
            insertStatement.setString(1, car.getPlates());
            insertStatement.setDate(2, car.getRca());
            insertStatement.setDate(3, car.getItp());
            insertStatement.setDate(4, car.getRovinieta());
            insertStatement.setDate(5, car.getFireExt());
            insertStatement.setDate(6, car.getFirstAid());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Car getCarById(String carId) {
        Car car = new Car();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM car WHERE id_car = ?")) {
            statement.setObject(1, UUID.fromString(carId));
            ResultSet carResult = statement.executeQuery();
            carResult.next();
            car.setIdCar(UUID.fromString(carResult.getString(1)));
            car.setIdOwner(UUID.fromString(carResult.getString(2)));
            car.setPlates(carResult.getString(3));
            car.setRca(Date.valueOf(carResult.getString(4)));
            car.setItp(Date.valueOf(carResult.getString(5)));
            car.setRovinieta(Date.valueOf(carResult.getString(6)));
            car.setFireExt(Date.valueOf(carResult.getString(7)));
            car.setFirstAid(Date.valueOf(carResult.getString(8)));
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return car;
    }

    public void deleteCar(String carId) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM car WHERE id_car = ?");
            insertStatement.setObject(1, UUID.fromString(carId));
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Alerts Methods
     *
     *  getAllAlerts - return all alerts
     *  getAllRca - return all Rca dates that are expired or have 30 days until expiration day
     *  getAllItp - return all Itp dates that are expired or have 30 days until expiration day
     *  getAllRovinieta - return all Rovinieta dates that are expired or have 30 days until expiration day
     *  getAllFireExt - return all Fire Extinguisher dates that are expired or have 30 days until expiration day
     *  getAllFirstAid - return all First Aid Kit's dates that are expired or have 30 days until expiration day
     *  updateAlert - updates the dates from a car in the alerts section
     *  countRcaAlerts - returns the number of the Rca Alerts
     *  countItpAlerts - returns the number of the Itp Alerts
     *  countRovinietaAlerts - returns the number of the Rovinieta Alerts
     *  countFirstExtAlerts - returns the number of the Fire Extinguisher Alerts
     *  countFirstAidAlerts - returns the number of the First Aid Kit's Alerts
     *
     *
     */

    public List<Alert> getAllAlerts() {
        List<Alert> allAlerts = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet alerts = statement.executeQuery("select id_car, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner where" +
                    "(rca - current_date) < 30  or (current_date > rca) or " +
                    "(itp - current_date) < 30 or (current_date > itp) or" +
                    "(rovinieta - current_date) < 30 or (current_date > rovinieta) or" +
                    "(fire_ext - current_date) < 30 or (current_date > fire_ext) or" +
                    "(first_aid - current_date) < 30 or (current_date > first_aid);");
            while (alerts.next()) {
                Alert alert = new Alert();
                alert.setIdCar(UUID.fromString(alerts.getString(1)));
                alert.setName(alerts.getString(2));
                alert.setPlates(alerts.getString(3));
                alert.setRca(Date.valueOf(alerts.getString(4)));
                alert.setItp(Date.valueOf(alerts.getString(5)));
                alert.setRovinieta(Date.valueOf(alerts.getString(6)));
                alert.setFireExt(Date.valueOf(alerts.getString(7)));
                alert.setFirstAid(Date.valueOf(alerts.getString(8)));

                allAlerts.add(alert);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allAlerts;
    }

    public List<Alert> getAllRca() {
        List<Alert> allRca = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet alerts = statement.executeQuery("select id_car, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner where" +
                    "(rca - current_date) < 30  or (current_date > rca)");
            while (alerts.next()) {
                Alert alertRca = new Alert();
                alertRca.setIdCar(UUID.fromString(alerts.getString(1)));
                alertRca.setName(alerts.getString(2));
                alertRca.setPlates(alerts.getString(3));
                alertRca.setRca(Date.valueOf(alerts.getString(4)));
                alertRca.setItp(Date.valueOf(alerts.getString(5)));
                alertRca.setRovinieta(Date.valueOf(alerts.getString(6)));
                alertRca.setFireExt(Date.valueOf(alerts.getString(7)));
                alertRca.setFirstAid(Date.valueOf(alerts.getString(8)));
                allRca.add(alertRca);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allRca;
    }

    public List<Alert> getAllItp() {
        List<Alert> allItp = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet alerts = statement.executeQuery("select id_car, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner where" +
                    "(itp - current_date ) < 30  or (current_date > itp)");
            while (alerts.next()) {
                Alert alert = new Alert();
                alert.setIdCar(UUID.fromString(alerts.getString(1)));
                alert.setName(alerts.getString(2));
                alert.setPlates(alerts.getString(3));
                alert.setRca(Date.valueOf(alerts.getString(4)));
                alert.setItp(Date.valueOf(alerts.getString(5)));
                alert.setRovinieta(Date.valueOf(alerts.getString(6)));
                alert.setFireExt(Date.valueOf(alerts.getString(7)));
                alert.setFirstAid(Date.valueOf(alerts.getString(8)));

                allItp.add(alert);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allItp;
    }

    public List<Alert> getAllRovinieta() {
        List<Alert> allRovinieta = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet alerts = statement.executeQuery("select id_car, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner where" +
                    "(rovinieta - current_date) < 30  or (current_date > rovinieta)");
            while (alerts.next()) {
                Alert alert = new Alert();
                alert.setIdCar(UUID.fromString(alerts.getString(1)));
                alert.setName(alerts.getString(2));
                alert.setPlates(alerts.getString(3));
                alert.setRca(Date.valueOf(alerts.getString(4)));
                alert.setItp(Date.valueOf(alerts.getString(5)));
                alert.setRovinieta(Date.valueOf(alerts.getString(6)));
                alert.setFireExt(Date.valueOf(alerts.getString(7)));
                alert.setFirstAid(Date.valueOf(alerts.getString(8)));

                allRovinieta.add(alert);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allRovinieta;
    }

    public List<Alert> getAllFireExt() {
        List<Alert> allFireExt = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet alerts = statement.executeQuery("select id_car, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner where" +
                    "(fire_ext - current_date) < 30  or (current_date > fire_ext)");
            while (alerts.next()) {
                Alert alert = new Alert();
                alert.setIdCar(UUID.fromString(alerts.getString(1)));
                alert.setName(alerts.getString(2));
                alert.setPlates(alerts.getString(3));
                alert.setRca(Date.valueOf(alerts.getString(4)));
                alert.setItp(Date.valueOf(alerts.getString(5)));
                alert.setRovinieta(Date.valueOf(alerts.getString(6)));
                alert.setFireExt(Date.valueOf(alerts.getString(7)));
                alert.setFirstAid(Date.valueOf(alerts.getString(8)));
                allFireExt.add(alert);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allFireExt;
    }
    public List<Alert> getAllFirstAid() {
        List<Alert> allFirstAid = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet alerts = statement.executeQuery("select id_car, name, plates, rca, itp, rovinieta, fire_ext, first_aid from car JOIN owner ON car.id_owner=owner.id_owner where" +
                    "(first_aid - current_date) < 30  or (current_date > first_aid)");
            while (alerts.next()) {
                Alert alert = new Alert();
                alert.setIdCar(UUID.fromString(alerts.getString(1)));
                alert.setName(alerts.getString(2));
                alert.setPlates(alerts.getString(3));
                alert.setRca(Date.valueOf(alerts.getString(4)));
                alert.setItp(Date.valueOf(alerts.getString(5)));
                alert.setRovinieta(Date.valueOf(alerts.getString(6)));
                alert.setFireExt(Date.valueOf(alerts.getString(7)));
                alert.setFirstAid(Date.valueOf(alerts.getString(8)));

                allFirstAid.add(alert);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        return allFirstAid;
    }

    public void updateAlert(Alert alert) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE car SET plates = ?, rca = ?, itp = ?, rovinieta = ?, fire_ext = ?, first_aid = ?  WHERE id_car = ?");
            insertStatement.setObject(7, alert.getIdCar());
            insertStatement.setString(1, alert.getPlates());
            insertStatement.setDate(2, alert.getRca());
            insertStatement.setDate(3, alert.getItp());
            insertStatement.setDate(4, alert.getRovinieta());
            insertStatement.setDate(5, alert.getFireExt());
            insertStatement.setDate(6, alert.getFirstAid());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countItpAlerts(){
        return getAllItp().size();
    }
    public int countRcaAlerts(){
        return getAllRca().size();
    }
    public int countRovinietaAlerts(){
        return getAllRovinieta().size();
    }
    public int countFireExt(){
        return getAllFireExt().size();
    }
    public int countFirstAid(){
        return getAllFirstAid().size();
    }

}
