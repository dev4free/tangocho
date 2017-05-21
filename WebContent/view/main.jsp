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
<link rel="stylesheet" href="/tangocho/js/lib/jqwidgets/styles/jqx.metrodark.css" type="text/css" />
<title>単語帳 第2段</title>
</head>
<body>
	<div id='content'>
		<div id='jqxWidget'>
			<div id="window">
				<div>Titolo</div>
				<div id=content>
					<div>
						question:<span id="question"></span><br>
					</div>
					<div>
						answer:<span id="answer"></span><br>
					</div>
					<div>
						<input type="button" value="Button" id='jqxButton' />
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function getPositionAndSize(posSizeData) {
			var viewportWidth = window.innerWidth;
			var viewportHeight = window.innerHeight;
			var horizontalmargin = Number((viewportWidth * 30 / 100).toFixed(0));
			var verticalmargin = Number((viewportHeight * 20 / 100).toFixed(0));
			posSizeData.width = viewportWidth - horizontalmargin;
			posSizeData.height = viewportHeight - verticalmargin;
			posSizeData.left = Number((horizontalmargin / 2).toFixed(0));
			posSizeData.top = Number((verticalmargin / 2).toFixed(0));
		}
		function getNextCard() {
			var formData = {
				command : "getNextCard",
				paramas : {}
			};

			$.ajax({
				url : "/tangocho/maincontroller",
				type : "post",
				encoding : "UTF-8",
				data : formData,
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					var response = data;
					$("#question").html(data.question);
					$("#answer").html(data.answer);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error!" + errorThrown);
				}
			});
		}
		function createWindow() {
			var jqxWidget = $('#jqxWidget');
			var offset = jqxWidget.offset();
			var posSizeData = {};
			posSizeData.width = 400;
			posSizeData.height = 200;
			posSizeData.left = 10;
			posSizeData.top = 10;
			getPositionAndSize(posSizeData);
			$('#window').jqxWindow({
				position : {
					x : posSizeData.left,
					y : posSizeData.top
				},
				theme : 'metrodark',
				showCloseButton : false,
				maxHeight : window.innerHeight - 10,
				maxWidth : window.innerWidth - 10,
				minHeight : 200,
				minWidth : 200,
				height : posSizeData.height,
				width : posSizeData.width,
				initContent : function() {
					$("#jqxButton").jqxButton({
						theme : 'metrodark',
						width : '150',
						height : '25'
					});
					$("#jqxButton").on('click', function() {
						getNextCard();
					});
					$('#window').jqxWindow('focus');
				}
			});
		};
		$(document).ready(function() {
			createWindow();
			$('#window').jqxWindow('open');
			getNextCard();
		});
	</script>
</body>
</html>
