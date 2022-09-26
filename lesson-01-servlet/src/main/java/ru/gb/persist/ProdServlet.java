package ru.gb.persist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/product")
public class ProdServlet extends HttpServlet {

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");

    private ProductRepository productRepository;
    private Product product;

    @Override
    public void init() throws ServletException {
        //this.product = new Product();
        this.productRepository = new ProductRepository();
        productRepository.insert(new Product(1,"Nissan teana", 2000));
        productRepository.insert(new Product(2,"lada vesta", 2300));
        productRepository.insert(new Product(3,"WV passat", 1200));
        productRepository.insert(new Product(4,"WV golf", 3500));
        productRepository.insert(new Product(5,"Mitsubishi galant",4500));
        productRepository.insert(new Product(6,"Mercedes-Benz GL",5000));
        productRepository.insert(new Product(7,"Nissan juke",4400));
        productRepository.insert(new Product(8,"Hundai solaris",2700));
        productRepository.insert(new Product(9,"Honda civik",4000));
        productRepository.insert(new Product(10,"Nissan patrol",3900));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("product", productRepository.findAll());
            getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);

            PrintWriter writer = resp.getWriter();
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>id</th>");
            writer.println("<th>title</th>");
            writer.println("<th>cost</th>");
            writer.println("</tr>");


            for (Product prod :
                    productRepository.findAll()) {
                writer.println("<tr>");
                writer.println("<th><a href='prod/" + prod.getId() + "'>" + prod.getId() + "</a></th>");
                writer.println("<th><a href='/" + req.getContextPath() + "title/" + prod.getTitle() + "'>" + prod.getTitle() + "<a/></th>");
                writer.println("<th><a href='cost/" + prod.getCost() + "'>" + prod.getCost() + "</a></th>");
            //    writer.println("<th>" + prod.getCost() + "</th>");
                writer.println("</tr>");

            }
            writer.println("</table>");
        }else {
           Matcher matcher =  PARAM_PATTERN.matcher(req.getPathInfo());
           if(matcher.matches()){
               long id = Long.parseLong(matcher.group(1));
               Product product1 = productRepository.findById(id);
               if (product1 == null){
                   resp.getWriter().println("User not found");
                   resp.setStatus(404);
                   return;
               }
               resp.getWriter().println("<p>id =" + product1.getId() + "</p>");
               resp.getWriter().println("<p>username =" + product1.getTitle() + "</p>");
           }else {
               resp.getWriter().println("Bad request");
               resp.setStatus(400);
           }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("product", productRepository.findById(product.getId()));
            getServletContext().getRequestDispatcher("/user_form.jsp").forward(req, resp);
                            resp.getWriter().println("<p>Id: " + product.getId() + "</p>");
                resp.getWriter().println("<p>Product title: " + product.getTitle() + "</p>");
        }
    }
}
