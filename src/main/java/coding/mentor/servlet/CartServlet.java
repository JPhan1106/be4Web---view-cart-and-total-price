package coding.mentor.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coding.mentor.entity.Book;
import coding.mentor.service.BookService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String command = request.getParameter("command");
	        int bookId = 0;
	        BookService bookService = new BookService();
	        HttpSession session = request.getSession();
	        Map<Integer, Book> cart = (Map<Integer, Book>) session.getAttribute("cart");

	        if (cart == null) {
	            cart = new HashMap<Integer, Book>();
	        }

	        if (command != null && command.equals("ADD_TO_CART")) {
	            bookId = Integer.parseInt(request.getParameter("bookId"));
	            Book book = bookService.getBookDetails(bookId);
	            cart.put(book.getId(), book);
	            session.setAttribute("cart", cart);
	            response.sendRedirect("home?command=DETAIL&bookId=" + bookId);
	            
	        } else if (command != null && command.equals("VIEW_CART")) {
	            request.setAttribute("cart", cart);
	            request.getRequestDispatcher("cart.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
