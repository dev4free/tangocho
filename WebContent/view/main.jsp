<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.base.css" type="text/css" />
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.metrodark.css" type="text/css" />
<link rel="stylesheet" href="/tangocho/js/app/frmmain.css" type="text/css" />
<script type="text/javascript" src="/tangocho/js/lib/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxdockpanel.js"></script>
<script type="application/javascript" src="/tangocho/js/app/frmmain.js"></script>
<title>単語帳 第2段</title>
</head>
<body>
	<div id='main_content'>
		<!-- 		<div id='jqxWidget' style="width: 600px; height: 600px; font-size: 13px; font-family: Verdana;"> -->
		<!-- 			<div id='jqxDockPanel'> -->
		<!-- 				<div id='left' style='background: #486974;'>First Div</div> -->
		<!-- 				<div id='right' style='background: #368ba7;'>Second Div</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<div id='jqxWidget'>
			<div id="window">
				<div id="title" style="position: absolute; display: block; height: 20px; width: 100%">英語ー日本語言葉</div>

				<div id="content" style="position: absolute; display: block; height: auto; width: 100%">
					<div id='jqxDockPanel'>
						<div id='left' style='background: #486974; margin: "0px"; padding: "0px"'>
							<div id='left_top' style='background: #456074; margin: "0px"; padding: "0px"'>
								<div id="question">
									question:<span id="question"></span><br>
								</div>
								<div id="answer">
									answer:<span id="answer"></span><br>
								</div>
							</div>
							<div id='left_bottom' style='background: #246074; '>
								<div id="buttons-container">aaaa
								</div>
							</div>
						</div>
						<div id='right' style='background: #1687a7;'>
								<div>
									<input type="button" value="Next card" id='jqxButton' /> <input type="button" value="Failed" id='btnFailed' /> <input type="button" value="Only new cards" id='btnOnlyNewCards' /> <input type="button" value="Only old cards" id='btnOnlyOldCards' />
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	</script>
</body>
</html>
