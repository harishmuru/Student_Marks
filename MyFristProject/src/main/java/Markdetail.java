

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Markdetail
 */
@WebServlet("/Markdetail")
public class Markdetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollno = request.getParameter("rollno");
        String password = request.getParameter("password");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
        	  
				Class.forName("com.mysql.cj.jdbc.Driver");
            // Establishing database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vsb", "root", "1234");

            // Query to fetch marks based on roll number and password
            String query = "SELECT * FROM login WHERE email= ? AND pwd= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollno);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve marks from the result set
                int tam = resultSet.getInt("tam");
                int math = resultSet.getInt("math");

                // Creating sessions for storing marks
                HttpSession session = request.getSession();
                session.setAttribute("u_name", tam);
                session.setAttribute("u_name1", math);

                // Forwarding request to student.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
                dispatcher.forward(request, response);
            } else {
                // If no matching user found, redirect back to login page
                response.sendRedirect("index.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Closing database connections and resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
