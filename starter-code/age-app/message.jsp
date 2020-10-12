<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
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
	<table cellpadding="2px" cellspacing="1px" bgcolor="#F4F5F7"
			width="400px" class="tableBorder" align="center">
			<tr>
				<td colspan="2" class = "message">
					<div id = "msg">
						<h3>${requestScope["message"]}
						</h3>
					</div>
				</td>
			</tr>			
		</table>

	
</body>
</html>