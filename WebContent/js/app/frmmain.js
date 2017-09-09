$(document).ready(function() {
	mainForm = new frmMain();
	mainForm.run();
});

var currentTheme = 'metrodark';
var rigthPanelWidth = 300;
var btnContHeight = 120; 
var winMinWidth = 1024; 
var winMinHeight = 500; 
	

function frmMain() {
	this.CLOSE = 0;
	this.SHOW_OLDCARDS = 1;
	this.SHOW_NEWCARDS = 2;
	this.currentCard  = null;
	this.getPositionAndSize = function(posSizeData) {
		var viewportWidth = window.innerWidth;
		var viewportHeight = window.innerHeight;
		var horizontalmargin = Number((viewportWidth * 30 / 100).toFixed(0));
		var verticalmargin = Number((viewportHeight * 20 / 100).toFixed(0));
		posSizeData.width = viewportWidth - horizontalmargin;
		posSizeData.height = viewportHeight - verticalmargin;
		posSizeData.left = Number((horizontalmargin / 2).toFixed(0));
		posSizeData.top = Number((verticalmargin / 2).toFixed(0));
	}

	this.noMoreCardAction = function (selectedAction) {
		alert("selected action=" + selectedAction)
	}
	
	this.resetSessionStatLabels = function() {
		$("#lblSxTotalCards").html("Tolal cards: 0");
		$("#lblSxReviwedCards").html("Reviewed cards: 0");
		$("#lblSxTotalAnswer").html("Total answers: 0");
		$("#lblSxCorretAnswer").html("Correct answers: 0 (0%)");
		$("#lblSxFailedAnswer").html("Failed answers: 0 (0%)");
	}

	this.closeApp = function() {
		$("#content").hide();
	}

	this.doAction = function() {
		if ($('#rbtNewCards').jqxRadioButton('checked')) {
			this.resetSessionStatLabels();
			this.loadOnlyNewCards();
		} else if ($('#rbtOldCards').jqxRadioButton('checked')) {
			this.resetSessionStatLabels();
			this.loadOnlyOldCards();
		} else {
			this.closeApp();
		}
	}
	this.noMoreCards = function(parendId) {
		var _this = this;
		var modalWidth = 270;
		var modalHeight = 220;
		var parentPosition = $("#"+parendId).position();
		var parentHieght = $("#"+parendId).height();
		var parentWidth = $("#"+parendId).width();
		var modalLeft = parentPosition.left + Math.trunc(parentWidth/2) - modalWidth/2;
		var modalTop = parentPosition.top + Math.trunc(parentHieght/2) - modalHeight/2;
		var result = this.SHOW_NEWCARDS;
		$('#dlgNoMoreCards').jqxWindow({
			position : {
				x : modalLeft,
				y : modalTop
			},
			theme : currentTheme,
			showCloseButton : false,
			maxHeight : window.innerHeight - 10,
			maxWidth : window.innerWidth - 10,
			minHeight : 0,
			minWidth : 0,
			height : 220,
			width : 300,
			isModal: true,
			initContent : function() {
	            $("#rbtNewCards").jqxRadioButton({ theme : currentTheme, width: 270, height: 25});
	            $("#rbtOldCards").jqxRadioButton({ theme : currentTheme, width: 240, height: 25 });				
	            $("#rbtCloseSession").jqxRadioButton({ theme : currentTheme, checked:true, width: 120, height: 25 });				
				$("#btnNoMoreCardsOK").jqxButton({
					theme : currentTheme
				});
				$("#btnNoMoreCardsOK").on('click', function() {
					$('#dlgNoMoreCards').jqxWindow('close');
					_this.doAction()
				});
				$('#dlgNoMoreCards').jqxWindow('focus');
			}
		});
		$('#dlgNoMoreCards').jqxWindow('open');
	}

	this.setStatisticsValues = function(statData, isSession) {
		var prefix="";
		if (isSession) {
			prefix = "Sx"
		}
		var correctPerc = statData.correctAnswersPerc;
		if (statData.correctAnswersPerc*100 != parseInt(statData.correctAnswersPerc*100)) {
			correctPerc = (Math.round(statData.correctAnswersPerc*100)/100).toFixed(2);
		}
		var failedPerc = statData.failedAnswersPerc;
		if (statData.failedAnswersPerc*100 != parseInt(statData.failedAnswersPerc*100)) {
			failedPerc = (Math.round(statData.failedAnswersPerc*100)/100).toFixed(2);
		}

		var correctAnsersStrValue = "" + statData.correctAnswers + " ("+correctPerc +"%)";
		var failedAnsersStrValue = "" + statData.failedAnswers + " ("+failedPerc +"%)";
		$("#lbl"+prefix+"TotalCards").html("Tolal cards: "+ statData.totalCards);
		$("#lbl"+prefix+"ReviwedCards").html("Reviewed cards: "+ statData.reviewedCards);
		$("#lbl"+prefix+"TotalAnswer").html("Total answers: "+ statData.totalAnswers);
		$("#lbl"+prefix+"CorretAnswer").html("Correct answers: "+ correctAnsersStrValue);
		$("#lbl"+prefix+"FailedAnswer").html("Failed answers: "+ failedAnsersStrValue);
	} 
	this.loadStatistics = function(replay) {
		if (typeof(replay.totalStatistics) !== "undefined" &&  replay.totalStatistics !== null) {
			this.setStatisticsValues(replay.totalStatistics, false);
		}
		if (typeof(replay.sessionStatistics) !== "undefined" &&  replay.sessionStatistics !== null) {
			this.setStatisticsValues(replay.sessionStatistics, true);
		}
	}
	this.loadShowCardsData = function(replay) {
		this.currentCard = replay.card;
		$("#question").html(this.currentCard.question);
		$("#answer").html("");
		this.loadStatistics(replay);
	}
	
	this.execCommandOnReplay = function(replay) {
		if (replay.command === "showCard") {
			this.loadShowCardsData(replay);
		} else if (replay.command === "noMoreCards") {
			this.loadStatistics(replay);
			this.noMoreCards("window");
		} else if (replay.command === "showError") {
			this.loadForm("frmShowServerError", replay, '#dlgErrorMessage','#frmShowServerErrorStyle');
			//this.loadErrorForm("window",replay.errorMessage);
			//alert("Server Error:" + replay.errorMessage);
		} else {
			alert("invalid command:" + replay.command);
		}
		$("#show-button").show();
		$("#answer-buttons").hide();
	}
	
	this.showAnswer = function() {
		$("#answer").html(this.currentCard.answer);
		$("#show-button").hide();
		$("#answer-buttons").show();
	}

	this.loadForm = function(formToLoad, replay) {
		var formData = {
				command : "loadForm",
				params : formToLoad
		};
		var _this = this;  
		var _replay = replay;
		$.ajax({
			url : "/tangocho/maincontroller",
			type : "post",
			encoding : "UTF-8",
			data : formData,
			dataType : "html",
			success : function(data, textStatus, jqXHR) {
				console.log(data);
				$('#'+formToLoad).remove();
				$('#'+formToLoad+"Style").remove();
				$('#'+formToLoad+"Js").remove();
				$('body').append(data);	
				var frmclass = window[formToLoad];
				var currentForm = new frmclass();
				currentForm.run(_replay);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error!" + errorThrown);
			}
		});
	}

	this.loadOnlyNewCards = function() {
		var formData = {
				command : "loadOnlyNewCards",
			};
			var _this = this;  
			$.ajax({
				url : "/tangocho/maincontroller",
				type : "post",
				encoding : "UTF-8",
				data : formData,
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					_this.execCommandOnReplay(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error!" + errorThrown);
				}
			});
	}
	
	this.loadOnlyOldCards = function() {
		var formData = {
				command : "loadOnlyOldCards",
			};
			var _this = this;  
			$.ajax({
				url : "/tangocho/maincontroller",
				type : "post",
				encoding : "UTF-8",
				data : formData,
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					_this.execCommandOnReplay(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error!" + errorThrown);
				}
			});
	}

	this.setAnswer = function(nextTime) {
		var _this = this;
		var failed=false;
		if (nextTime === 0) {
			failed=true;
		}
		var formData = {
			command : "setAnswer",
			params : '{"cardId":"'+_this.currentCard.id+'", "failed":"'+failed+'", "skip":"false", "nextTime":"'+nextTime+'"}'
		};

		$.ajax({
			url : "/tangocho/maincontroller",
			type : "post",
			encoding : "UTF-8",
			data : formData,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				_this.execCommandOnReplay(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error!" + errorThrown);
			}
		});
	}
	
	this.setSkipCard = function() {
		var _this = this;
		var failed=false;
		_this.currentCard.failed = false;
		_this.currentCard.skip = true;
		_this.currentCard.nextTime = 0;
		var formData = {
			command : "skipCard",
			params : _this.currentCard
		};

		$.ajax({
			url : "/tangocho/maincontroller",
			type : "post",
			encoding : "UTF-8",
			data : formData,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				_this.execCommandOnReplay(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error!" + errorThrown);
			}
		});
	}
	
	this.startNormalSession = function() {
		var formData = {
			command : "startNormalSession",
		};
		var _this = this;  
		$.ajax({
			url : "/tangocho/maincontroller",
			type : "post",
			encoding : "UTF-8",
			data : formData,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				_this.execCommandOnReplay(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error!" + errorThrown);
			}
		});
	}
	this.setAnswerButtonPos = function()  {
	    var panelWidth = $("#answer-buttons").width();
	    var startLeftPoint = 50;
	    var topLine1 = 25;
	    var topLine2 = 60;
	    var bigBtnSize = 65;
	    var margin = 5
	    var btnWidth = Math.trunc((panelWidth - startLeftPoint*2 - bigBtnSize*2 - margin*5)/4);
	    
	    var btnNum=1;
		for (i=1; i<=4; i++) {
			var id = "#day"+btnNum
			var leftPos =  startLeftPoint + bigBtnSize + (i*margin) + ((i-1)*btnWidth);
			$(id).width(btnWidth);
			$(id).css("left",leftPos+"px");
			$(id).css("top",topLine1 + "px");
			btnNum = btnNum * 2;
		}
		for (i=1; i<=4; i++) {
			var id = "#day"+btnNum
			var leftPos =  startLeftPoint + bigBtnSize + (i*margin) + ((i-1)*btnWidth);
			$(id).width(btnWidth);
			$(id).css("left",leftPos+"px");
			$(id).css("top",topLine2+"px");
			btnNum = btnNum * 2;
		}
		$("#failed").css("left", startLeftPoint+"px"); 
		$("#failed").css("top", topLine1+"px"); 

		var skipLeftPos =  startLeftPoint + bigBtnSize + margin*5 + ((4)*btnWidth);
		$("#skip").css("left", skipLeftPos+"px"); 
		$("#skip").css("top", topLine1+"px");
		
		var showAnswerLefPos =  startLeftPoint + bigBtnSize + margin;
		var showAnswerWidth = panelWidth - bigBtnSize*2 - startLeftPoint*2 - margin*4;
		$("#show-answer").css("left", showAnswerLefPos+"px"); 
		$("#show-answer").css("top", topLine1+"px");
		$("#show-answer").width(showAnswerWidth);
		

	}
	this.resizePanels = function() {
		var frmWidth = $('#window').jqxWindow('width');
		var frmHeight = $('#window').jqxWindow('height');
     	if (frmWidth < winMinWidth) {
     		frmWidth = winMinWidth;
     	} 
     	if (frmHeight < winMinHeight) {
     		frmHeight = winMinHeight;
     	} 
		
		$('#window').jqxWindow({height:frmHeight+"px"});
		$('#window').jqxWindow({width:frmWidth+"px"});
		$('#content').height(frmHeight);
		$('#content').width(frmWidth);

		$("#left").height(frmHeight-31);
		$("#left").width(frmWidth-rigthPanelWidth);
		
	    $("#right").height(frmHeight-31);
	    $("#right").width(rigthPanelWidth-1);
	    $("#right").css('left',(frmWidth-rigthPanelWidth-1));

	    var qHeight = Math.trunc((frmHeight-31 -120)/2);
		$("#question").height(qHeight);
		$("#question").width(frmWidth-rigthPanelWidth);
		$("#question").css("line-height", qHeight+"px");
	    
		var aHeight = frmHeight - 31 - qHeight - btnContHeight;
		$("#answer").height(aHeight);
		$("#answer").width(frmWidth-rigthPanelWidth);
	    $("#answer").css('top',qHeight);
		$("#answer").css("line-height", aHeight+"px");
	    
	    $("#answer-buttons").width(frmWidth-rigthPanelWidth);				
	    $("#answer-buttons").css('top',$("#answer").height() + $("#question").height());
	    $("#answer-buttons").css('left',0);

	    $("#show-button").width(frmWidth-rigthPanelWidth);				
	    $("#show-button").css('top',$("#answer").height() + $("#question").height());
	    $("#show-button").css('left',0);
	    this.setAnswerButtonPos();
	};

	this.createWindow = function() {
		var me = this;
		var posSizeData = {};
		posSizeData.width = 400;
		posSizeData.height = 200;
		posSizeData.left = 10;
		posSizeData.top = 10;
		this.getPositionAndSize(posSizeData);
		var _this = this;
		$('#window').jqxWindow({
			position : {
				x : posSizeData.left,
				y : posSizeData.top
			},
			theme : currentTheme,
			showCloseButton : false,
			maxHeight : window.innerHeight - 10,
			maxWidth : window.innerWidth - 10,
			minHeight : 0,
			minWidth : 0,
			height : posSizeData.height,
			width : posSizeData.width,
			initContent : function() {
				var btnNum=1;
				for (i=1; i<=8; i++) {
					$("#btnDay"+btnNum).jqxButton({
						theme : currentTheme,
						width : '100%',
						height : '30'
					});
					btnNum = btnNum * 2;
				}
				$("#btnDay1").on('click', function() {
					_this.setAnswer(1);					
				});
				$("#btnDay2").on('click', function() {
					_this.setAnswer(2);					
				});
				$("#btnDay4").on('click', function() {
					_this.setAnswer(4);					
				});
				$("#btnDay8").on('click', function() {
					_this.setAnswer(4);					
				});
				$("#btnDay16").on('click', function() {
					_this.setAnswer(16);					
				});
				$("#btnDay32").on('click', function() {
					_this.setAnswer(32);					
				});
				$("#btnDay64").on('click', function() {
					_this.setAnswer(64);					
				});
				$("#btnDay128").on('click', function() {
					_this.setAnswer(128);					
				});
				$("#btnFailed").on('click', function() {
					_this.setAnswer(0);					
				});
				$("#btnFailed").jqxButton({
					theme : currentTheme,
					width : '100%',
					height : '100%'
				});
				$("#btnSkip").jqxButton({
					theme : currentTheme,
					width : '100%',
					height : '100%'
				});
				$("#btnShowAnswer").jqxButton({
					theme : currentTheme,
					width : '100%',
					height : '100%'
				});
				$("#btnShowAnswer").on('click', function() {
					_this.showAnswer();
				});
				$("#btnSkip").on('click', function() {
					_this.setSkipCard();
				});
				$('#window').jqxWindow('focus');
			}
		});
		$('#window').on('resized', function (event) {
			me.resizePanels();
		});
		this.resetSessionStatLabels();
		this.resizePanels();
		
	};

	this.run = function() {
		this.createWindow();
		$('#window').jqxWindow('open');
		this.startNormalSession();
	};
}


