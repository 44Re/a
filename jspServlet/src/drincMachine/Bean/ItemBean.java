package drincMachine.Bean;
//Serializableをインポート
import java.io.Serializable;
//Builderでさまざまな属性を変更したオブジェクトの状態をファイルに復元などする
public class ItemBean implements Serializable{

	private static final long serialVersionUID=1L;

	private String code;
	private String name;
	private String unitPrice;
	private String count;
	private String image;
	private String isPr;

	//コンストラクタでプロパティを初期化する
	public ItemBean(){
		code = "";
		name = "";
		unitPrice = "";
		count = "";
		image = "";
		isPr = "";
	}

	public String getCode() {
		return code;
	}

	public  void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIsPr() {
		return isPr;
	}

	public void setIsPr(String isPr) {
		this.isPr = isPr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
