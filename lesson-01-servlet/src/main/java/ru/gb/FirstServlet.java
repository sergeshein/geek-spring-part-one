package ru.gb;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
@WebServlet(urlPatterns = "/first_svt")
public class FirstServlet implements Servlet {
    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;

    }



    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws  IOException {
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().println("<h1>привет из сервлета</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
