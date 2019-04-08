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

@WebServlet(urlPatterns = {"/alert"})
public class Alert extends HttpServlet {
    private DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        populateAlerts(req, dbManager);
        String type = req.getParameter("type");
        type = type != null ? type : "all";

        List<siit.ro.entity.Alert> alerts;
        switch (type) {
            case "rca":
                alerts = dbManager.getAllRca();
                break;
            case "itp":
                alerts = dbManager.getAllItp();
                break;
            case "rovinieta":
                alerts = dbManager.getAllRovinieta();
                break;
            case "fireExt":
                alerts = dbManager.getAllFireExt();
                break;

            case "firstAid":
                alerts = dbManager.getAllFirstAid();
                break;
            case "edit":
                alerts = dbManager.getAllAlerts();
                String idCar = req.getParameter("idCar");
                req.setAttribute("alert", dbManager.getCarById(idCar));
                req.getRequestDispatcher("/jsps/alerts/edit.jsp").forward(req, resp);
                break;
            default:
                alerts = dbManager.getAllAlerts();
        }



        req.setAttribute("alerts", alerts);

        req.getRequestDispatcher("/jsps/alerts/list.jsp").forward(req, resp);

    }

    static void populateAlerts(HttpServletRequest req, DatabaseManager dbManager) {
        int totalAlertsCount = 0;
        int itpAlertsCount = dbManager.countItpAlerts();
        int rcaAlertCount = dbManager.countRcaAlerts();
        int rovinietaCount = dbManager.countRovinietaAlerts();
        int fireExtCount = dbManager.countFireExt();
        int firstAidCount = dbManager.countFirstAid();
        totalAlertsCount += itpAlertsCount;
        totalAlertsCount += rcaAlertCount;
        totalAlertsCount += rovinietaCount;
        totalAlertsCount += fireExtCount;
        totalAlertsCount += firstAidCount;
        req.setAttribute("itpCount", dbManager.countItpAlerts());
        req.setAttribute("rcaCount", dbManager.countRcaAlerts());
        req.setAttribute("rovinietaCount", dbManager.countRovinietaAlerts());
        req.setAttribute("fireExtCount", dbManager.countFireExt());
        req.setAttribute("firstAidCount", dbManager.countFirstAid());
        req.setAttribute("totalCount", totalAlertsCount);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        siit.ro.entity.Alert alert  = new siit.ro.entity.Alert();
        alert.setIdCar(UUID.fromString(req.getParameter("idCar")));
        alert.setPlates(req.getParameter("plates"));
        alert.setRca(Date.valueOf(req.getParameter("rca" )));
        alert.setItp(Date.valueOf(req.getParameter("itp")));
        alert.setRovinieta(Date.valueOf(req.getParameter("rovinieta")));
        alert.setFireExt(Date.valueOf(req.getParameter("fireExt")));
        alert.setFirstAid(Date.valueOf(req.getParameter("firstAid")));
        dbManager.updateAlert(alert);
        List<siit.ro.entity.Alert> alerts = dbManager.getAllAlerts();
        req.setAttribute("alerts", alerts);
        req.getRequestDispatcher("/jsps/alerts/list.jsp").forward(req, resp);



    }
}