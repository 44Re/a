package drinkMachine.Update;

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
 * Servlet implementation class UpdateController
 */
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateController() {
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
		// 受け取るデータの文字コードをUFT-8にセットする。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// editjspから受け取った値をitembeanにセットする
		ItemBean item = new ItemBean();

		item.setCode(request.getParameter("code"));
		item.setName(request.getParameter("name"));
		item.setCount(request.getParameter("count"));
		item.setUnitPrice(request.getParameter("unitPrice"));
		item.setImage(request.getParameter("image"));
		// item.setIsPr(request.getParameter("isPR"));
		// updateしたいデータをitembeanに格納してeditjspにset
		request.setAttribute("ItemBean", item);

		try {
			String nextPage = "";
			T001ItemDao itemDao = new T001ItemDao();
			String kensaku = itemDao.checkItem(item.getName());
			request.setAttribute("kensaku", kensaku);

			// 編集ボタン押すと商品が最後に更新されたかを取得

			// 更新画面に入ったときの最終更新時間（beforeDate）と、更新しようとしたときの最終更新時間(afterDate)を比較したい

			String beforeDate = (String) request.getSession().getAttribute(
					"beforeDate");
			String afterDate = itemDao.getRecordDate(item.getCode());

			// 重複してたら再度、商品更新画面へ
			if (!kensaku.equals("0")) {
				nextPage = "/edit.jsp";
				// 重複していなかったら
			} else {

				// 商品更新メソッドupdateの呼び出し
				if (beforeDate.equals(afterDate)) {
					int kousinn = itemDao.update(item);

					// 画面遷移先判定　result=１成功→一覧画面　０失敗→更新画面
					if (kousinn == 1) {
						nextPage = "/list.jsp";

					} else {
						request.setAttribute("errorMessage", "更新済みエラー");
						nextPage = "/edit.jsp";
					}
				}
			}
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
