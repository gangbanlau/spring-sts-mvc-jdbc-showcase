<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<%String path = request.getContextPath(); %>
<title>Products EasyUI Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
/* $('#products_datagrid').datagrid({
	title: 'Products',
	width: 'auto',
	height: 'auto',
	fitColumns: true,
	striped:true,
	idField:'id',
	pagination:true,
	rownumbers:true,
	frozenColumns:[[
           {field:'ck',checkbox:true}
	]],
	columns:[[
	  {field:'id',hidden:true,sortable:true},
	  {field:'username',title:'Username',width:100,
		  editor:{
			  type:'validatebox',
			  options:{
				  required:true
			  }
		  }
	  },
	  {field:'password',title:'Password',width:100,
		  formatter:function(value,row,index){
			  return "******";
             },
		  editor:{
			  type:'validatebox',
			  options:{
				  required:true
			  }
		  }
	  }
	]]
}); */
</script>
</head>
<body>
	<div id="products_datagrid"></div>
</body>
</html>