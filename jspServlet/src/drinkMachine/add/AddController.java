package drinkMachine.add;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import drinkMachine.dao.T001ItemDao;

/**
 * Servlet implementation class AddController
 */
public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 受け取るデータの文字コードをUFT-8にセットする。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// request.getParameter("")で、リクエストパラメーター"Name"の値をとりだし、変数nameに代入（商品名の入力）
		String name = request.getParameter("name");
		String unitPrice = request.getParameter("unitPrice");
		String count = request.getParameter("count");

		System.out.println("商品名：" + name);
		System.out.println("金額：" + unitPrice);
		System.out.println("数量：" + count);

		try {
			String nextPage = "";
			// daoコンストラクタの呼び出し
			T001ItemDao itemDao = new T001ItemDao();

			// 商品名重複確認
			String kensaku = itemDao.checkItem(name);
			// 重複件数セット?????????????????
			request.setAttribute("kensaku", kensaku);

			// 重複してたら再度、登録画面へ
			if (!kensaku.equals("0")) {
				nextPage = "/add.jsp";
				// 重複していなかったら
			} else {
				// 商品登録メソッドaddItemの呼び出し
				int result = itemDao.addItem(name, unitPrice, count);

				// 画面遷移先判定　result=１成功→一覧画面　０失敗→登録画面
				if (result == 1) {
					nextPage = "/list.jsp";
				} else {
					nextPage = "/add.jsp";
				}
			}// Dispの中に次に遷移する文字列nextPaageを代入する
			ServletContext application = getServletContext();
			application.getRequestDispatcher(nextPage).forward(request,
					response);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
