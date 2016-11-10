package drinkMachine.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import drincMachine.Bean.ItemBean;
import drinkMachine.dao.T001ItemDao;

/**
 * Servlet implementation class ListController
 */
@SuppressWarnings("unused")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 受け取るデータの文字コードをUFT-8にセットする。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// request.getParameter("")で、リクエストパラメーター"Name"の値をとりだし、変数nameに代入（商品名の入力）
		String Code = request.getParameter("code");
		String Name = request.getParameter("name");

		System.out.println("商品名：" + Name);
		System.out.println("コード：" + Code);

			// daoコンストラクタの呼び出し
			try {
				T001ItemDao itemDao = new T001ItemDao();
				List list = itemDao.searchItem(Code,Name);
				//検索結果をリクエストにセット
				request.getSession().setAttribute("sItem",list);
				System.out.println("検索結果をリクエストにセット"+list);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String nextPage = "/list.jsp";

			ServletContext application = getServletContext();
			application.getRequestDispatcher(nextPage).forward(request,response);

	}

}
