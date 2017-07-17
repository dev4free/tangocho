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
<script type="text/javascript" src="/tangocho/js/lib/jqwidgets/jqxradiobutton.js"></script>
<script type="application/javascript" src="/tangocho/js/app/frmmain.js"></script>
<title>単語帳 第2段</title>
</head>
<body>
	<div id='main_content'>
		<div id="window">
			<div id="title" style="position: absolute; display: block; height: 20px; width: 100%">英語ー日本語言葉</div>
			<div id="content" style="position: absolute; display: block; overflow: none">
				<div id='left'>
					<div id="question">
						<span id="question"></span><br>
					</div>
					<div id="answer">
						<span id="answer"></span><br>
					</div>
					<div id="buttons-container">
						<div id="answer-buttons">
							<div id="failed">
								<input type="button" value="不正解" id='btnFailed' />
							</div>
							<div id="day1" class="nextDay">
								<input type="button" value="1 日" id='btnDay1' />
							</div>
							<div id="day2" class="nextDay">
								<input type="button" value="2 日" id='btnDay2' />
							</div>
							<div id="day4" class="nextDay">
								<input type="button" value="4 日" id='btnDay4' />
							</div>
							<div id="day8" class="nextDay">
								<input type="button" value="8 日" id='btnDay8' />
							</div>
							<div id="day16" class="nextDay">
								<input type="button" value="16 日" id='btnDay16' />
							</div>
							<div id="day32" class="nextDay">
								<input type="button" value="32 日" id='btnDay32' />
							</div>
							<div id="day64" class="nextDay">
								<input type="button" value="64 日" id='btnDay64' />
							</div>
							<div id="day128" class="nextDay">
								<input type="button" value="128 日" id='btnDay128' />
							</div>
							<div id="skip">
								<input type="button" value="スキップ" id='btnSkip' />
							</div>
						</div>
						<div id="show-button">
							<div id="show-answer">
								<input type="button" value="解答表示" id='btnShowAnswer' style="font-size: 24px;"/>
							</div>
						</div>
					</div>
				</div>
				<div id='right'>
					<div id="statistics_box">
						<div id="stat_title">統計</div>
						<div id="stat_content">
							<div id="lblTotalCards" class="lblStat">Total cards:</div>
							<div id="lblReviwedCards" class="lblStat">Reviewed cards:</div>
							<div id="lblTotalAnswer" class="lblStat">Total answers:</div>
							<div id="lblCorretAnswer" class="lblStat">Correct answers:</div>
							<div id="lblFailedAnswer" class="lblStat">Failed answers:</div>
						</div>
					</div>
					<div id="session_statistics_box">
						<div id="session_stat_title">セッション統計</div>
						<div id="session_stat_content">
							<div id="lblSxTotalCards" class="lblStat">Total cards:</div>
							<div id="lblSxReviwedCards" class="lblStat">Reviewed cards:</div>
							<div id="lblSxTotalAnswer" class="lblStat">Total answers:</div>
							<div id="lblSxCorretAnswer" class="lblStat">Correct answers:</div>
							<div id="lblSxFailedAnswer" class="lblStat">Failed answers:</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dlgNoMoreCards" style="display:none">
			<div id="dlgNoMoreCardsTitle" style="position: absolute; display: block; height: 20px; width: 100%">勉強完了</div>
			<div id="dlgNoMoreCardsCnt" >
				<div id="lblLessonOver">今日の勉強を終わりました、どうしますか?</div>
				<div id="rbtNewCards" style="margin-top: 20px">新しいカードを勉強する</div>
				<div id="rbtOldCards" style="margin-top: 5px">古いカードを復習</div>
				<div id="rbtCloseSession" style="margin-top: 5px">終了</div>
				<div id="btnNoMoreCardsOK" style="margin-top: 20px; margin-left: 40px; margin-right: 40px">OK</div>
			</div>
	</div>
</body>
</html>
