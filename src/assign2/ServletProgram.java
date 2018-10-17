package assign2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assign2.DAO.ProductDAO;
import assign2.model.Product;

/**
 * Servlet implementation class ServletProgram
 */
@WebServlet("/ServletProgram")
public class ServletProgram extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProgram() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAO();
		String productID = 
				request.getParameter("productID");
		Product product = productDAO.getProductById(productID);
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Product List</h1>");
		out.println("<table cellpadding=\"5\" text-align=center>");
		out.println("<tr>");
		out.println("<th> &nbsp; Product Id &nbsp; </th>");
		out.println("<th> &nbsp; Product Name &nbsp; </th>");
		out.println("<th> &nbsp; Quantity &nbsp; </th>");
		out.println("<th> &nbsp; Price &nbsp; </th>");
		out.println("<th> &nbsp; Category &nbsp; </th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>"+product.getProductId()+"</td>");
		out.println("<td>"+product.getProductName()+"</td>");
		out.println("<td>"+product.getQuantity()+"</td>");
		out.println("<td>"+product.getPrice()+"</td>");
		out.println("<td>"+product.getCategory()+"</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("addButton") != null) {
			String productID = 
					request.getParameter("productID");
			String productName = 
					request.getParameter("productName");
			int quantity = 
					Integer.parseInt(request.getParameter("quantity"));
			double price = 
					Double.parseDouble(request.getParameter("price"));
			String category = 
					request.getParameter("category");
			
			ProductDAO productDAO = new ProductDAO();
			int result = productDAO.addProductRow(productID, productName, quantity, price, category);
			PrintWriter out = response.getWriter();
			
			out.println("<html><body>");
			if(result == -1) {
				out.println("<h1>FAIL</h1>");
			} else {
				out.println("<h1>SUCCESS</h1>");
				
			}
			out.println("</body></html>");
		} else if(request.getParameter("viewButton") != null) {
			doGet(request, response);
		} else if(request.getParameter("editButton") != null) {
			String productID = request.getParameter("productID");
			Integer quantity = null;
			Double price = null;
			if(request.getParameter("quantity") != "") {
				quantity = Integer.parseInt(request.getParameter("quantity"));
			}
			if(request.getParameter("price") != "") {
				price = Double.parseDouble(request.getParameter("price"));
			}
			
			ProductDAO productDAO = new ProductDAO();
			int result = productDAO.editProduct(productID, quantity, price);
			PrintWriter out = response.getWriter();

			out.println("<html><body>");
			if( result > 0 ) {
				out.println("<h1>SUCCESS</h1>");
			} else {
				out.println("<h1>FAIL</h1>");
			}
			out.println("</body></html>");
		}
		// TODO Auto-generated method stub
		
	}

}
