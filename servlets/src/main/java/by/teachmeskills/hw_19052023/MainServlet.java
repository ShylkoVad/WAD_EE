package by.teachmeskills.hw_19052023;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/calculator")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        String operation = request.getParameterValues("operation")[0];
        if (operation != null) {
            switch (operation) {
                case "sumOperation" ->
                        request.setAttribute("result", Calculator.sum(Double.parseDouble(number1), Double.parseDouble(number2)));
                case "substrOperation" ->
                        request.setAttribute("result", Calculator.substr(Double.parseDouble(number1), Double.parseDouble(number2)));
                case "multiplyOperation" ->
                        request.setAttribute("result", Calculator.multiply(Double.parseDouble(number1), Double.parseDouble(number2)));
                case "divOperation" ->
                        request.setAttribute("result", Calculator.div(Double.parseDouble(number1), Double.parseDouble(number2)));
            }
        }
        requestDispatcher.forward(request, response);
    }
}
