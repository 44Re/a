

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>CRUDサンプル</title>
<!--
    <link type="text/css" rel="stylesheet" href="exValidation/css/style.css" />
    -->
<link type="text/css" rel="stylesheet"
	href="exValidation/css/exvalidation.css" />
</head>
<body>

	<h1>商品登録</h1>


	<%
		if (request.getAttribute("kensaku") != null) {
			if (!request.getAttribute("kensaku").equals("0")) {
	%>
	<br>
	<b><font color="#ff0000"> 入力した商品はすでに登録されています。<br>商品名を変更して登録しなおしてください。
	</font></b>
	<br>
	<br>
	<%
		}
		}
	%>


	><a href="./list.html">一覧</a>
	<br>
	<br>

	<form action="AddController" method="post">
		<table border="0" bgcolor="#999999" cellspacing="1" cellpadding="8">
			<tbody>
				<tr>
					<th bgcolor="#EBEBEB" width="100">商品コード</th>
					<td bgcolor="#FFFFFF" width="250"><input type="text" id="code"
						name="code" readonly="readonly" value="{{ item.code }}"></td>
				</tr>
				<tr>
					<th bgcolor="#EBEBEB" width="100">商品名<sup><font
							color="#FF0000">*</font></sup></th>
					<td bgcolor="#FFFFFF" width="250"><input type="text" id="name"
						name="name" value="{{ item.name }}"></td>
				</tr>
				<tr>
					<th bgcolor="#EBEBEB" width="100">金額<sup><font
							color="#FF0000">*</font></sup></th>
					<td bgcolor="#FFFFFF" width="250"><input type="text"
						id="unitPrice" name="unitPrice" value="{{ item.unitPrice }}">
					</td>
				</tr>
				<tr>
					<th bgcolor="#EBEBEB" width="100">数量<sup><font
							color="#FF0000">*</font></sup></th>
					<td bgcolor="#FFFFFF" width="250"><input type="text"
						id="count" name="count" value="{{ item.count }}"></td>
				</tr>
				<tr>
					<th bgcolor="#EBEBEB" width="100">商品画像</th>
					<td bgcolor="#FFFFFF" width="250"><input type="file"
						id="image" name="image"></td>
				</tr>
				<tr>
					<th bgcolor="#EBEBEB">おすすめ商品</th>
					<td bgcolor="#FFFFFF"><input type="checkbox" id="isPR"
						name="isPR" value="True"{% ifitem.isPR %} checked{% endif %}>おすすめ商品棚に並べる</input>
					</td>
				</tr>
			</tbody>
		</table>
		<br> <input type="submit"
			onclick="if(confirm('商品情報を登録しますか？'))
    {return true;
    }else{
    return false;}"
			value="登録する"></a>
		</tb>
	</form>

	<br>
	<font color="#FF0000">*</font>は必須項目

</body>
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