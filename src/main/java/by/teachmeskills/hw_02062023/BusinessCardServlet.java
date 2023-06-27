package by.teachmeskills.hw_02062023;

import by.teachmeskills.hw_26052023.DBConnectionManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/businessсard")
public class BusinessCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DBConnectionManager dbConnectionManager = (DBConnectionManager) req.getServletContext().getAttribute("DBManager");
//        Map<String, String> userData = CRUDUtils.getInfo(dbConnectionManager.getConnection());
//        req.setAttribute("userData", userData);
//        getServletContext().getRequestDispatcher("/businesscard.jsp").forward(req, resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/businessCard.jsp");
        requestDispatcher.forward(req, resp);
    }
}
