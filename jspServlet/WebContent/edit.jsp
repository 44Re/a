<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ItemBeanをインポート -->
	<%@ page import = "drincMachine.Bean.*" %>
	<!-- リストをインポート -->
	<%@ page import="java.io.*,java.util.*,java.sql.*,javax.naming.*"%>


    <html xml:lang="ja" lang="ja">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--
    <link type="text/css" rel="stylesheet" href="exValidation/css/style.css" />
    -->
    <link type="text/css" rel="stylesheet" href="exValidation/css/exvalidation.css" />
    <title>CRUDサンプル</title>
    </head>
    <body>
    <h1>商品変更</h1>
    <% ItemBean item =(ItemBean)request.getAttribute("ItemBean");
    if(item == null){
    	item = new ItemBean();
    }


    %>

    <%
		if (request.getAttribute("kensaku") != null) {
			if (!request.getAttribute("kensaku").equals("0")) {
	%>
	<b><font color="#ff0000"> 入力した商品はすでに登録されています。<br>商品名を変更して登録しなおしてください。
	</font></b>
	<%
		}
		}
	%>



    > <a href="./list.html">一覧</a><br><br>

    <form action="UpdateController" method="post">

    <table cellspacing="1" cellpadding="8" border="0" bgcolor="#999999">
      <tbody><tr>
        <th width="100" bgcolor="#EBEBEB">商品コード</th>
        <td width="250" bgcolor="#FFFFFF"><input type="text" id="code" name="code" readonly="readonly" value=" <%= item.getCode()%>"></td>
      </td></tr>
      <tr>
        <th width="100" bgcolor="#EBEBEB">商品名<sup><font color="#FF0000">*</font></sup></th>

        <td width="250" bgcolor="#FFFFFF"><input type="text" id="name" name="name" value="<%= item.getName()%>"> </td>
      </tr>
      <tr>
        <th width="100" bgcolor="#EBEBEB">金額<sup><font color="#FF0000">*</font></sup></th>
        <td width="250" bgcolor="#FFFFFF"><input type="text" id="unitPrice" name="unitPrice" value="<%= item.getUnitPrice()%>"> </td>
      </tr>
      <tr>
        <th width="100" bgcolor="#EBEBEB">数量<sup><font color="#FF0000">*</font></sup></th>

        <td width="250" bgcolor="#FFFFFF"><input type="text" id="count" name="count" value="<%= item.getCount()%>"> </td>
      </tr>
      <tr>
        <th width="100" bgcolor="#EBEBEB">商品画像</th>
        <td width="250" bgcolor="#FFFFFF"><input type="file" id="image" name="image"> </td>
      </tr>
      <tr>
        <th bgcolor="#EBEBEB">おすすめ商品</th>

        <td bgcolor="#FFFFFF"><input type="checkbox" id="isPR" name="isPR" value="True"{% if item.isPR %} checked{% endif %}>おすすめ商品棚に並べる</td>
      </tr>
    </tbody></table><br>
    <input type="submit"
    onclick="if(confirm('商品情報を変更しますか？'))
    {return true;
    }else{
    return false;}"
    value="変更する">
    </form>

    <br>
    <font color="#FF0000">*</font>は必須項目

    </body>

    <!--[removed][removed]-->
    <!--<script type="text/javascript"src="jquery.js"></script>-->
<script type="text/javascript" src="exValidation/js/jquery.min.js"></script>
<script type="text/javascript" src="exValidation/js/jquery.easing.js"></script>
<script type="text/javascript" src="exValidation/js/jQselectable.js"></script>
<script type="text/javascript" src="exValidation/js/exvalidation.js"></script>
<script type="text/javascript" src="exValidation/js/exchecker-ja.js"></script>
<script type="text/javascript">
	var validation = $("form").exValidation({
		rules : {
			name : "chkrequired chkmax200",
			unitPrice : "chkrequired chknumonly chkmin1 chknum1000",
			count : "chkrequired chknumonly chkmin1 chkmax3",
			filename : "chkfile"
		},
		stepValidation : true
	});
</script>


    </html>