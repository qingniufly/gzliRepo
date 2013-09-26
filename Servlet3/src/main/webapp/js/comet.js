/**
 * 客户端Comet JS 渲染部分
 *
 * @author yongboy@gmail.com
 * @date 2010-10-18
 * @version 1.0
 */
String.prototype.template = function() {
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(m, i) {
		return args[i];
	});
}
var html = '<div class="logDiv">' + '<div class="contentDiv">{0}</div>'
		+ '<div class="tipDiv">last date : {1}</div>'
		+ '<div class="clear"> </div>' + '</div>';

function showContent(json) {
	$("#showDiv").prepend(html.template(json.content, json.date));
}
var server = 'blogpush';
var comet = {
	connection : false,
	iframediv : false,

	initialize : function() {
		if (navigator.appVersion.indexOf("MSIE") != -1) {
			comet.connection = new ActiveXObject("htmlfile");
			comet.connection.open();
			comet.connection.write("<html>");
			comet.connection.write("<script>document.domain = '"
					+ document.domain + "'");
			comet.connection.write("</html>");
			comet.connection.close();
			comet.iframediv = comet.connection.createElement("div");
			comet.connection.appendChild(comet.iframediv);
			comet.connection.parentWindow.comet = comet;
			comet.iframediv.innerHTML = "<iframe id='comet_iframe' src='"
					+ server + "'></iframe>";
		} else if (navigator.appVersion.indexOf("KHTML") != -1
				|| navigator.userAgent.indexOf('Opera') >= 0) {
			comet.connection = document.createElement('iframe');
			comet.connection.setAttribute('id', 'comet_iframe');
			comet.connection.setAttribute('src', server);
			with (comet.connection.style) {
				position = "absolute";
				left = top = "-100px";
				height = width = "1px";
				visibility = "hidden";
			}
			document.body.appendChild(comet.connection);
		} else {
			comet.connection = document.createElement('iframe');
			comet.connection.setAttribute('id', 'comet_iframe');
			with (comet.connection.style) {
				left = top = "-100px";
				height = width = "1px";
				visibility = "hidden";
				display = 'none';
			}
			comet.iframediv = document.createElement('iframe');
			comet.iframediv.setAttribute('onLoad', 'comet.frameDisconnected()');
			comet.iframediv.setAttribute('src', server);
			comet.connection.appendChild(comet.iframediv);
			document.body.appendChild(comet.connection);
		}
	},
	frameDisconnected : function() {
		comet.connection = false;
		$('#comet_iframe').remove();
		// setTimeout("chat.showConnect();",100);
	},
	showMsg : function(data) {
		showContent(data);
	},
	timeout : function() {
		var url = server + "?time=" + new Date().getTime();
		if (navigator.appVersion.indexOf("MSIE") != -1) {
			comet.iframediv.childNodes[0].src = url;
		} else if (navigator.appVersion.indexOf("KHTML") != -1
				|| navigator.userAgent.indexOf('Opera') >= 0) {
			document.getElementById("comet_iframe").src = url;
		} else {
			comet.connection.removeChild(comet.iframediv);
			document.body.removeChild(comet.connection);
			comet.iframediv.setAttribute('src', url);
			comet.connection.appendChild(comet.iframediv);
			document.body.appendChild(comet.connection);
		}
	},
	onUnload : function() {
		if (comet.connection) {
			comet.connection = false;
		}
	}
}

if (window.addEventListener) {
	window.addEventListener("load", comet.initialize, false);
	window.addEventListener("unload", comet.onUnload, false);
} else if (window.attachEvent) {
	window.attachEvent("onload", comet.initialize);
	window.attachEvent("onunload", comet.onUnload);
}
