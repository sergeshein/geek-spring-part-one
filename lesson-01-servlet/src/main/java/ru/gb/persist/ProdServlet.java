package ru.gb.persist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product")
public class ProdServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
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
            writer.println("<th>" + prod.getId() + "</th>");
            writer.println("<th>" + prod.getTitle() + "</th>");
            writer.println("<th>" + prod.getCost() + "</th>");
            writer.println("</tr>");
            
        }
        writer.println("</table>");
    }

}
