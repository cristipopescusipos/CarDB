package siit.ro.servlet;


import siit.ro.model.DatabaseManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 *  The car servlet part of the Control layer of the MVC ,
 *  populates the tables from the car part of the application
 *  and helps to add update and delete dates in the car section
 *
 */


@WebServlet(urlPatterns = {"/car"})
public class Car extends HttpServlet {
    private DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alert.populateAlerts(req, dbManager);
        String actionString = req.getParameter("action");
        String action = (actionString != null) ? actionString : "list";
        switch(action){
            case("add"):
                List<siit.ro.entity.Owner> owners = dbManager.getAllOwners();
                req.setAttribute("owners", owners);
                req.getRequestDispatcher("/jsps/car/add.jsp").forward(req, resp);
                break;
            case("edit"):
                String carId = req.getParameter("idCar");
                req.setAttribute("car", dbManager.getCarById(carId));
                req.getRequestDispatcher("/jsps/car/edit.jsp").forward(req, resp);
                break;
            case("delete"):
                carId = req.getParameter("idCar");
                dbManager.deleteCar(carId);
            default:
                List<siit.ro.entity.Car> cars = dbManager.getAllCars();
                req.setAttribute("cars", cars);
                req.getRequestDispatcher("/jsps/car/list.jsp").forward(req, resp);
        }
    }

    @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alert.populateAlerts(req, dbManager);
        String actionString = req.getParameter("action");
        String action = (actionString != null) ? actionString : "list";
        switch(action){
            case("add"):
                siit.ro.entity.Car car = new siit.ro.entity.Car();
                car.setIdCar(UUID.randomUUID());
                car.setIdOwner(UUID.fromString(req.getParameter("owner")));
                car.setPlates(req.getParameter("plates"));
                car.setRca(Date.valueOf(req.getParameter("rca")));
                car.setItp(Date.valueOf(req.getParameter("itp")));
                car.setRovinieta(Date.valueOf(req.getParameter("rovinieta")));
                car.setFireExt(Date.valueOf(req.getParameter("fireExt")));
                car.setFirstAid(Date.valueOf(req.getParameter("firstAid")));
                dbManager.addCars(car);

                List<siit.ro.entity.Car> cars = dbManager.getAllCars();
                req.setAttribute("cars", cars);
                req.getRequestDispatcher("/jsps/car/list.jsp").forward(req, resp);
                break;
            case("edit"):
                car = new siit.ro.entity.Car();
                car.setIdCar(UUID.fromString(req.getParameter("idCar")));
                car.setPlates(req.getParameter("plates"));
                car.setRca(Date.valueOf(req.getParameter("rca" )));
                car.setItp(Date.valueOf(req.getParameter("itp")));
                car.setRovinieta(Date.valueOf(req.getParameter("rovinieta")));
                car.setFireExt(Date.valueOf(req.getParameter("fireExt")));
                car.setFirstAid(Date.valueOf(req.getParameter("firstAid")));

                dbManager.updateCar(car);

                cars = dbManager.getAllCars();
                req.setAttribute("cars", cars);
                req.getRequestDispatcher("/jsps/car/list.jsp").forward(req, resp);
                break;
            default:
                cars = dbManager.getAllCars();
                req.setAttribute("cars", cars);
                req.getRequestDispatcher("/jsps/car/list.jsp").forward(req, resp);
        }
    }

}
