$(document).ready(function() {
	mainForm = new frmMain();
	mainForm.run();
});

var currentTheme = 'metrodark'

function frmMain() {
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

	this.execCommandOnReplay = function(replay) {
		if (replay.command === "showCard") {
			this.currentCard = replay.card;
			$("#question").html(this.currentCard.question);
			$("#answer").html(this.currentCard.answer);
		} else if (replay.command === "noMoreCards") {
			alert("no more cards");
		} else {
			alert("invalid command");
		}
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
	this.createWindow = function() {
		var jqxWidget = $('#jqxWidget');
		var offset = jqxWidget.offset();
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
			minHeight : 200,
			minWidth : 200,
			height : posSizeData.height,
			width : posSizeData.width,
			initContent : function() {
				$("#jqxButton").jqxButton({
					theme : currentTheme,
					width : '150',
					height : '25'
				});
				$("#btnFailed").jqxButton({
					theme : currentTheme,
					width : '150',
					height : '25'
				});
				$("#btnOnlyNewCards").jqxButton({
					theme : currentTheme,
					width : '150',
					height : '25'
				});
				$("#btnOnlyOldCards").jqxButton({
					theme : currentTheme,
					width : '150',
					height : '25'
				});
				$("#jqxButton").on('click', function() {
					_this.setAnswer(Math.pow(2, Math.trunc(Math.random()*3)));
				});
				$("#btnFailed").on('click', function() {
					$("#jqxDockPanel").jqxDockPanel('render');
					//_this.setAnswer(0);
				});
				$("#btnOnlyNewCards").on('click', function() {
					_this.loadOnlyNewCards(0);
				});
				$("#btnOnlyOldCards").on('click', function() {
					_this.loadOnlyOldCards(0);
				});
				$('#window').jqxWindow('focus');
			}
		});
		$('#window').on('resizing', function (event) {
			//console.log("resizing");
	        //$('#left').attr('dock', 'left');
	        $('#left').width('70%');
	        $('#left').height('100%');
	        //$('#right').attr('dock', 'right');
	        $('#right').width('30%');
	        $('#right').height('100%');
			var winHeight = $('#content').height();
	        $('#jqxDockPanel').jqxDockPanel({height:winHeight})
	        $('#jqxDockPanel').jqxDockPanel('render');
		});
		
	};

	this.testPanes = function() {
		// Create jqxDockPanel
		var winHeight = $('#content').height();
        $("#jqxDockPanel").jqxDockPanel({ width: "100%", height: winHeight, lastchildfill: false});
        $("#jqxDockPanel div").css('color', '#fff');
        // Apply custom layout depending on the user's choice.
        //$("#jqxDockPanel > div > div").css({ width: 'auto', height: 'auto' });
        $('#left').attr('dock', 'left');
        $('#left').width('70%');
        $('#left').height('100%');
        $('#right').attr('dock', 'right');
        $('#right').width('30%');
        $('#right').height('100%');
        
        $('#left_top').attr('dock', 'top');
        $('#left_top').width('100%');
        $('#left_top').height('500px');
        
        $('#left_bottom').attr('dock', 'bottom');
        $('#left_bottom').width('100%');
        $('#left_bottom').height('200px');
        
        $('#jqxDockPanel').jqxDockPanel('render');
//            } else
//                if (position < 115) {
//                    $("#jqxDockPanel > div > div").css({ width: '100px' });
//                    $('#first').attr('dock', 'left');
//                    $('#second').attr('dock', 'right');
//                    $('#third').attr('dock', 'bottom');
//                    $('#third').height('140px');
//                    $('#fourth').attr('dock', 'top');
//                    $('#fourth').height('70px');
//                } else if (position < 175) {
//                    $("#jqxDockPanel > div > div").css({ width: '150px' });
//                    $('#first').attr('dock', 'left');
//                    $('#second').attr('dock', 'left');
//                    $('#third').attr('dock', 'left');
//                    $('#fourth').attr('dock', 'left');
//                } else if (position < 235) {
//                    $("#jqxDockPanel > div > div").css({ height: '70px' });
//                    $('#first').attr('dock', 'top');
//                    $('#second').attr('dock', 'top');
//                    $('#third').attr('dock', 'top');
//                    $('#fourth').attr('dock', 'top');
//                }
//                else {
//                    $("#jqxDockPanel > div > div").css({ width: '100px' });
//                    $('#first').attr('dock', 'left');
//                    $('#second').attr('dock', 'left');
//                    $('#third').attr('dock', 'left');
//                    $('#fourth').attr('dock', 'left');
//                }
	}

	this.run = function() {
		this.createWindow();
		this.testPanes();
		$('#window').jqxWindow('open');
//		this.startNormalSession();
	};
}


