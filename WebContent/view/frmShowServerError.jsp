<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="application/javascript" src="/tangocho/js/app/frmShowServerError.js" id="frmShowServerErrorJs"></script>
<style type="text/css" id="frmShowServerErrorStyle"> @import url("/tangocho/js/app/frmShowServerError.css");</style>
<script type="text/javascript">
var XFoo = document.registerElement('x-randomtag');
document.body.appendChild(new XFoo());	
</script>
<div class="frmShowServerError" id="frmShowServerError">
	<div class="frmShowServerError" id="frmShowServerError_title">サーバエラー</div>
	<div class="frmShowServerError" id="mainContainer">
		<div class="frmShowServerError" id="pnlMain">
			<div class="frmShowServerError" id="errorIcon"></div>
			<div class="frmShowServerError" id="errorText"></div>
			<input class="frmShowServerError" id="btnErrorDetail" type="button" value="エラー詳細表示"  />
			<input class="frmShowServerError" id="btnClose" type="button" value="閉じる"  />
		</div>
		<div class="frmShowServerError" id="plnErrorDetail">
			<div class="frmShowServerError" id="messageToShow">no message</div>
		</div>
	</div>
</div>
