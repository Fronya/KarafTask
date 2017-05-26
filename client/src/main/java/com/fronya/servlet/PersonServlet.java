package com.fronya.servlet;


import com.fronya.client.PersonClient;
import com.fronya.model.Person;
import com.fronya.model.PersonService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonServlet extends HttpServlet {
    private PersonClient client;
    private List<Person> resultFriends;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream os = resp.getOutputStream();
        List<Person> allFriends = client.getAllFriends();
        os.println("<html><body>");
        os.println("<h2>All friends</h2>");
        os.println("<table>");
        os.println("<tr><th>Name</th><th>Day</th><th>Month</th><th>Year</th></tr>");
        for (Person person : allFriends) {
            os.println(String.format("<tr><td>%s</td><td>%d</td><td>%d</td><td>%d</td></tr>", person.getName(),
                    person.getBirthday().getDay(), person.getBirthday().getMonth(),
                    person.getBirthday().getYear()));
        }
        os.println("</table>");
        os.println("<h2>Find person born the same day</h2>");
        os.println("<form name='input' action='/personclient' method='post'>");
        os.println("<table>");
        os.println("<tr><td>Year</td><td><input type='number' name='year'/></td></tr>");
        os.println("<tr><td colspan='2'><input type='submit' value='Find'/></td></tr>");
        os.println("</form>");
        os.println("</table>");
        if(resultFriends == null){
            os.println("Friends not found");
        }else{
            os.println("<table>");
            os.println("<tr><th>Name</th><th>Year</th></tr>");
            for(Person currentResult: resultFriends){
                os.println(String.format("<tr><td>%s</td><td>%d</td></tr>", currentResult.getName(),
                        currentResult.getBirthday().getYear()));
            }
            os.println("</table>");
        }
        os.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = Integer.valueOf(req.getParameter("year"));
        resultFriends = client.getFriendsBornSameYear(year);
        resp.sendRedirect("/personclient");
    }

    public void setPersonService(PersonService personService) {
        if(client == null){
            client = new PersonClient();
        }
        client.setService(personService);
    }
}
