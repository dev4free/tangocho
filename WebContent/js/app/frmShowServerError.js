/**
 * 
 */
//# sourceURL=frmShowServerError.js
var XFoo = document.registerElement('x-randomtag');
document.body.appendChild(new XFoo());	

function frmShowServerError() {
	this.errorDetailIsHidden = true;
	this.run = function(replay) {
		this.loadErrorForm("window",replay.errorMessage, replay.errorMessageDetail)
	}
	this.loadErrorForm = function(parendId, errorMessage, errorMessageDetail) {
		var _this = this;
		var modalWidth = 700;
		var modalHeight = 170;
		var parentPosition = $("#"+parendId).position();
		var parentHieght = $("#"+parendId).height();
		var parentWidth = $("#"+parendId).width();
		var modalLeft = parentPosition.left + Math.trunc(parentWidth/2) - modalWidth/2;
		var modalTop = parentPosition.top + Math.trunc(parentHieght/2) - modalHeight/2;
		$('#errorText').html(errorMessage); 
		$('#messageToShow').html(errorMessageDetail); 
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
			resizable: false ,
			height : modalHeight,
			width : modalWidth,
			isModal: true,
			initContent : function() {
	            $("#btnErrorDetail").jqxButton({ theme : currentTheme, width: 200, height: 25});
	            $("#btnClose").jqxButton({ theme : currentTheme, width: 200, height: 25});
//	            $("#rbtOldCards").jqxRadioButton({ theme : currentTheme, width: 240, height: 25 });				
//	            $("#rbtCloseSession").jqxRadioButton({ theme : currentTheme, checked:true, width: 120, height: 25 });				
//				$("#btnNoMoreCardsOK").jqxButton({
//					theme : currentTheme
//				});
				$("#btnErrorDetail").on('click', function() {
					if (_this.errorDetailIsHidden) {
						$("#plnErrorDetail.frmShowServerError").show();
						$('#frmShowServerError').jqxWindow('height',450);
						$("#btnErrorDetail.frmShowServerError").prop('value', 'エラー詳細未表示');
						_this.errorDetailIsHidden = false;
					} else {
						$("#plnErrorDetail.frmShowServerError").hide();
						$('#frmShowServerError').jqxWindow('height',170);
						$("#btnErrorDetail.frmShowServerError").prop('value', 'エラー詳細表示');
						_this.errorDetailIsHidden = true;
					}
				});
				$('#btnClose').on('click', function() {
					$('#frmShowServerError').jqxWindow('close');		
				});
			}
		});
		$('#frmShowServerError').jqxWindow('open');		
	}
}
