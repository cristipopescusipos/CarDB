package siit.ro.servlet;

import siit.ro.model.DatabaseManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *  The owner servlet part of the Control layer of the MVC ,
 *  populates the tables from the owner part of the application
 *  and helps to add update and delete dates in the owner section
 *
 */

@WebServlet(urlPatterns = {"/owner"})
public class Owner extends HttpServlet {


    private DatabaseManager dbManager = DatabaseManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alert.populateAlerts(req, dbManager);
        String actionString = req.getParameter("action");
        String action = (actionString != null) ? actionString : "list";
        switch(action){
            case("add"):
                req.getRequestDispatcher("/jsps/owner/add.jsp").forward(req, resp);
                break;
            case("edit"):
                String ownerId = req.getParameter("idOwner");
                req.setAttribute("owner", dbManager.getOwnerById(ownerId));
                req.getRequestDispatcher("/jsps/owner/edit.jsp").forward(req, resp);
                break;
            case("delete"):
                ownerId = req.getParameter("idOwner");
                dbManager.deleteOwner(ownerId);
            default:
                List<siit.ro.entity.Owner> owners = dbManager.getAllOwners();
                req.setAttribute("owners", owners);
                req.getRequestDispatcher("/jsps/owner/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alert.populateAlerts(req, dbManager);
        String actionString = req.getParameter("action");
        String action = (actionString != null) ? actionString : "list";
          switch(action){
            case("add"):
                siit.ro.entity.Owner owner  = new siit.ro.entity.Owner();
                owner.setIdOwner(UUID.randomUUID());
                owner.setName(req.getParameter("name"));
                owner.setTelephone(req.getParameter("telephone"));
                owner.setEmail(req.getParameter("email"));

                dbManager.addOwner(owner);

                List<siit.ro.entity.Owner> owners = dbManager.getAllOwners();
                req.setAttribute("owners", owners);
                req.getRequestDispatcher("/jsps/owner/list.jsp").forward(req, resp);
                break;
            case("edit"):
                owner = new siit.ro.entity.Owner();
                owner.setIdOwner(UUID.fromString(req.getParameter("idOwner")));
                owner.setName(req.getParameter("name"));
                owner.setTelephone(req.getParameter("telephone"));
                owner.setEmail(req.getParameter("email"));

                dbManager.updateOwner(owner);

                owners = dbManager.getAllOwners();
                req.setAttribute("owners", owners);
                req.getRequestDispatcher("/jsps/owner/list.jsp").forward(req, resp);
                break;
            default:
                owners = dbManager.getAllOwners();
                req.setAttribute("owners", owners);
                req.getRequestDispatcher("/jsps/owner/list.jsp").forward(req, resp);
        }
    }

}
