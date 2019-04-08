package siit.ro.servlet;


import siit.ro.model.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static siit.ro.servlet.Alert.populateAlerts;

@WebServlet(urlPatterns = {"/index"})
public class Index extends HttpServlet {
    private DatabaseManager dbManager = DatabaseManager.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        populateAlerts(req, dbManager);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
