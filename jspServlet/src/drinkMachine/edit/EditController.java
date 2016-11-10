package drinkMachine.edit;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import drincMachine.Bean.ItemBean;
import drinkMachine.dao.T001ItemDao;

/**
 * Servlet implementation class EditController
 */
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
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
		// 受け取るデータの文字コードをUFT-8にセットする。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// request.getParameter("")で、リクエストパラメーター"code"の値をとりだし、変数nameに代入（商品名の入力）
		String code = request.getParameter("code");

		try {

			T001ItemDao itemDao = new T001ItemDao();
			//
			ItemBean item = itemDao.getItemDate(code);
			request.setAttribute("ItemBean",item);

			//編集ボタン押すと商品が最後に更新されたかを取得,getsessionに"beforeDateをセットする。
			//updatecontrollerで値を取得する
			String recordDate = itemDao.getRecordDate(code);
			request.getSession().setAttribute("beforeDate", recordDate);


		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String nextPage = "/edit.jsp";

		ServletContext application = getServletContext();
		application.getRequestDispatcher(nextPage).forward(request, response);

	}

}
