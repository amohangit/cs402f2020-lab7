<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Age App</title>
<style>
.label {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #0066FF;
}

.tableBorder {
	border: solid 1px #0066FF;
	margin-top: 100px;
}

.message {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-weight: bold;
	color: #0066FF;
}
</style>
</head>
<body>
	<form id = "frmMain1" action = "agehandler" method = "post">
		<table cellpadding="2px" cellspacing="1px" bgcolor="#F4F5F7"
			width="400px" class="tableBorder" align="center">
			<tr>
				<td colspan="2" bgcolor="#0066FF">&nbsp;</td>
			</tr>
			<tr>
				<td class="label">
					<b> Enter your name </b>
				</td>
				<td>
					<input type = "text" name = "name" value = "" size = "20" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b> Enter your age </b>
				</td>
				<td>
					<input type = "text" name = "age" value = "" size = "20" />
				</td>
			</tr>
			<tr>
			 <td align="left" colspan = "2">
			 	<input type="submit" id="btnSubmit" value="Submit"/> </td>
			</tr>
		</table>
			
	</form>
	<script>
		function invoke(){
			location.href = "agehandler?name=" + $('#name').val() + "age=" 
				+ $('#age').val();
		}
	</script>
</body>
</html>