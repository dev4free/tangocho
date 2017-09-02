/**
 * 
 */
//# sourceURL=frmShowServerError.js
function frmShowServerError() {
	this.run = function(replay) {
		this.loadErrorForm("window",replay.errorMessage)
	}
	this.loadErrorForm = function(parendId, errorMessage) {
		var _this = this;
		var modalWidth = 800;
		var modalHeight = 400;
		var parentPosition = $("#"+parendId).position();
		var parentHieght = $("#"+parendId).height();
		var parentWidth = $("#"+parendId).width();
		var modalLeft = parentPosition.left + Math.trunc(parentWidth/2) - modalWidth/2;
		var modalTop = parentPosition.top + Math.trunc(parentHieght/2) - modalHeight/2;
		$('#messageToShow').html(errorMessage); 
		$('#frmShowServerError').jqxWindow({
			position : {
				x : modalLeft,
				y : modalTop
			},
			theme : currentTheme,
			showCloseButton : true,
			maxHeight : window.innerHeight - 10,
			maxWidth : window.innerWidth - 10,
			minHeight : 0,
			minWidth : 0,
			height : modalHeight,
			width : modalWidth,
			isModal: true,
			initContent : function() {
//	            $("#rbtNewCards").jqxRadioButton({ theme : currentTheme, width: 270, height: 25});
//	            $("#rbtOldCards").jqxRadioButton({ theme : currentTheme, width: 240, height: 25 });				
//	            $("#rbtCloseSession").jqxRadioButton({ theme : currentTheme, checked:true, width: 120, height: 25 });				
//				$("#btnNoMoreCardsOK").jqxButton({
//					theme : currentTheme
//				});
//				$("#btnNoMoreCardsOK").on('click', function() {
//					$('#dlgNoMoreCards').jqxWindow('close');
//					_this.doAction()
//				});
				$('#frmShowServerError').jqxWindow('focus');
			}
		});
		$('#frmShowServerError').jqxWindow('open');		
	}
	
}
