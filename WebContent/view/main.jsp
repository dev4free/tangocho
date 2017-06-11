<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.base.css" type="text/css" />
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
<script type="text/javascript" src="/tangocho/js/lib/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxwindow.js"></script>
<script type="application/javascript" src="/tangocho/js/app/frmmain.js"></script>
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.metrodark.css" type="text/css" />
<title>単語帳 第2段</title>
</head>
<body>
	<div id='content'>
		<div id='jqxWidget'>
			<div id="window">
				<div>英語ー日本語言葉</div>
				<div id=content>
					<div>
						question:<span id="question"></span><br>
					</div>
					<div>
						answer:<span id="answer"></span><br>
					</div>
					<div>
						<input type="button" value="Next card" id='jqxButton' />
						<input type="button" value="Failed" id='btnFailed' />
						<input type="button" value="Only new cards" id='btnOnlyNewCards' />
						<input type="button" value="Only old cards" id='btnOnlyOldCards' />
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	</script>
</body>
</html>
