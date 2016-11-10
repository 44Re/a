package drinkMachine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import drincMachine.Bean.ItemBean;

public class T001ItemDao {

	private Connection conn = null;
	PreparedStatement pstmt = null;

	public T001ItemDao() throws ClassNotFoundException, SQLException {

		// 接続文法　"jdbc:oracle:thin@ホスト名:ボート番号:SID";
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// jdbc経由でデータベースに接続する

		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.117:1521:zeroSchool",
				"EDU_S_KOMATSU", "ZEROSCHOOL");

	}

	// データの重複チェックメソッド
	public String checkItem(String name) {
		String sql = "SELECT count(*) as checkItem " + "FROM T001_ITEM"
				+ " WHERE ITEM_NM = '" + name + "'";
		String kensaku = "";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery(sql);

			// 重複チェックの結果取り出し
			resultSet.next();
			kensaku += resultSet.getString("checkItem");

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return kensaku;
	}

	// 商品登録メソッド
	public int addItem(String name, String unitPrice, String count) {
		int result = 0;
		try {

			// SQL文発行。code自動発行
			String query = "INSERT INTO T001_ITEM(ITEM_NO,ITEM_NM,UNIT_PRICE,STOCK_COUNT,RECORD_DATE)VALUES(TABLE_SEQ.NEXTVAL,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(query);
			// 各カラムに登録するデータをセット
			pstmt.setString(1, name);
			pstmt.setString(2, unitPrice);
			pstmt.setString(3, count);

			// SQL文を実行　.executeUpdate();はDBで更新された行数を返す
			// １なら登録成功、０なら失敗
			result = pstmt.executeUpdate();
			System.out.println("登録成功=1、失敗=0を表示:" + result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}//商品検索メソッド
	public List<ItemBean> searchItem(String code,String name){
		//検索結果を格納するLISTを宣言
		 List<ItemBean>list = new ArrayList();
		//商品コード検索(前方一致検索)、商品名検索(あいまい検索)
		//SELECT以下で出力したいデータを記述
		//今回の場合は４項目(コード、名前、金額、数量)
		try {

			 Statement statement=conn.createStatement();

		String sql1="SELECT ITEM_NO,ITEM_NM,UNIT_PRICE,STOCK_COUNT"+" FROM T001_ITEM "
		+"WHERE ITEM_NO Like '"+code+"%'"
		+"AND ITEM_NM Like '%"+name+"%'";
		System.out.println("daoの検索"+sql1);

				ResultSet resultSet =statement.executeQuery(sql1);

				//resultSetで返された結果を取得
				//ItemBeanのsetメソッドを使用して各値をセットする
				while(resultSet.next()){
					ItemBean selectedItem=new ItemBean();
//SQLの検索結果(resultset)をstringで取得してselectedItemにセットする
					selectedItem.setCode(resultSet.getString("ITEM_NO"));
					selectedItem.setName(resultSet.getString("ITEM_NM"));
					selectedItem.setUnitPrice(resultSet.getString("UNIT_PRICE"));
					selectedItem.setCount(resultSet.getString("STOCK_COUNT"));
					//商品データリストに追加
					list.add(selectedItem);
				}
System.out.println("商品検索結果："+list.size()+"件ヒット");
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


		return list;

	}

	public ItemBean getItemDate(String code) {
		ItemBean ItemBean = new ItemBean();
		try {

			Statement statement = conn.createStatement();
			String sql = "SELECT ITEM_NO,ITEM_NM,UNIT_PRICE,STOCK_COUNT"
					+ " FROM T001_ITEM WHERE ITEM_NO='" + code + "'";
			ResultSet resultSet = statement.executeQuery(sql);

			// 実行したsql文で取り出したデータresultsetを呼び出している
			resultSet.next();

			ItemBean.setCode(resultSet.getString("ITEM_NO"));
			ItemBean.setName(resultSet.getString("ITEM_NM"));
			ItemBean.setUnitPrice(resultSet.getString("UNIT_PRICE"));
			ItemBean.setCount(resultSet.getString("STOCK_COUNT"));

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return ItemBean;

	}

	// 商品更新メソッド
	public int update(ItemBean item) {
		int kousinn = 0;
		try {
			// SQL文発行。code自動発行
			String upsql = "UPDATE T001_ITEM SET " +
					"ITEM_NM = '" + item.getName() + "', " +
					"UNIT_PRICE = '" + item.getUnitPrice() + "', " +
					"STOCK_COUNT = '" + item.getCount() + "', " +
					"IS_PR = '" + item.getIsPr()+ "', " +
					 "ITEM_IMAGE_FILE_PATH = '" + item.getImage()+ "', " +
					 "RECORD_DATE = CURRENT_TIMESTAMP  " +
					"WHERE ITEM_NO = '" + item.getCode()+ "'" ;
			pstmt = conn.prepareStatement(upsql);

			// SQL文を実行　.executeUpdate();はDBで更新された行数を返す
			// １なら登録成功、０なら失敗
			kousinn = pstmt.executeUpdate();
			System.out.println("商品更新成功=1、失敗=0を表示:" + kousinn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kousinn;
	}

	//日時の取得メソッド
	public String getRecordDate(String code){
		String  recordDate = "";
		Statement statement;
		try {
			statement = conn.createStatement();
			String sql="SERECT * FROM T001_ITEM WHERE ITEM_NO ='"+ code +"'";
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();

			 recordDate = resultSet.getString("RECORD_DATE");

			} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return  recordDate;

	}

}
