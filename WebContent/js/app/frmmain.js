/**
 * 
 */

const current_theme = 'metrodark';

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

	this.setAnswer = function() {
		var _this = this;
		var formData = {
			command : "setAnswer",
			params : '{"cardId":"'+_this.currentCard.id+'", "failed":"false", "skip":"false", "nextTime":"'+Math.pow(2, Math.trunc(Math.random()*3))+'"}'
		};

		$.ajax({
			url : "/tangocho/maincontroller",
			type : "post",
			encoding : "UTF-8",
			data : formData,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				_this.currentCard = data;
				$("#question").html(data.question);
				$("#answer").html(data.answer);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error!" + errorThrown);
			}
		});
	}
	this.getNextCard = function() {
		var formData = {
			command : "getNextCard",
		};
		var _this = this;  
		$.ajax({
			url : "/tangocho/maincontroller",
			type : "post",
			encoding : "UTF-8",
			data : formData,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				_this.currentCard = data;
				$("#question").html(data.question);
				$("#answer").html(data.answer);
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
			theme : current_theme,
			showCloseButton : false,
			maxHeight : window.innerHeight - 10,
			maxWidth : window.innerWidth - 10,
			minHeight : 200,
			minWidth : 200,
			height : posSizeData.height,
			width : posSizeData.width,
			initContent : function() {
				$("#jqxButton").jqxButton({
					theme : current_theme,
					width : '150',
					height : '25'
				});
				$("#jqxButton").on('click', function() {
					_this.setAnswer(_this);
				});
				$('#window').jqxWindow('focus');
			}
		});
	};
	
	this.run = function() {
		this.createWindow();
		$('#window').jqxWindow('open');
		this.getNextCard();
	};
}


$(document).ready(function() {
	mainForm = new frmMain();
	mainForm.run();
});
