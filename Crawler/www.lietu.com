<html>
	 <style>
      div.auto_complete {
        width: 315px;
        background: #fff;
      }
      div.auto_complete ul {
        border:1px solid #888;
        margin:0;
        padding:0;
        width:100%;
        list-style-type:none;
      }
      div.auto_complete ul li {
        margin:0;
        padding:3px;
      }
      div.auto_complete ul li.selected {
        background-color: #ffb;
      }
      div.auto_complete ul strong.highlight {
        color: #800;
        margin:0;
        padding:0;
      }
    .k {	BORDER-RIGHT: #666666 thin; BORDER-TOP: #666666 thin; BORDER-LEFT: #666666 thin; BORDER-BOTTOM: #666666 thin
}
.line2 {	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-LEFT-COLOR: #999999; BORDER-TOP-COLOR: #999999; BORDER-BOTTOM: #999999 1px dotted; BORDER-RIGHT-WIDTH: 1px; BORDER-RIGHT-COLOR: #999999
}
.z12 {	FONT-SIZE: 12px
}
</style>
	     <script language="JavaScript" type="text/javascript" src="scripts/prototype/prototype.js"></script>
    <script language="JavaScript" type="text/javascript" src="scripts/scriptaculous/scriptaculous.js"></script>
    <script language="JavaScript" type="text/javascript" src="scripts/scriptaculous/controls.js"></script>
    <script language="JavaScript" type="text/javascript" src="scripts/scriptaculous/effects.js"></script>

<head>
  <title>猎兔搜索</title>
  <META http-equiv=Content-Type content="text/html; charset=utf-8">
  <SCRIPT LANGUAGE = "JavaScript">
		var isCE = navigator.appVersion.indexOf("Windows CE")>0;
		if (isCE)
		{
		window.location.href="pda.htm";
		}
	</script>
	<STYLE>.SELECT {
		BORDER-RIGHT: #cccccc 1pxsolid; BORDER-TOP: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; BORDER-BOTTOM: #cccccc 1px solid
	}
	.form-button {
		BORDER-RIGHT: #6699cc 1px solid; BORDER-TOP: #6699cc 1px solid; BORDER-LEFT: #6699cc 1px solid; BORDER-BOTTOM: #6699cc 1px solid
	}
	.form-button-hover {
		BORDER-RIGHT: #6699cc 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #6699cc 1px solid
	}
	.form-text {
		BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: Arial
	}
	A.alpha {
		COLOR: #000000; TEXT-DECORATION: none
	}
	A.alpha:hover {
		COLOR: #000000; TEXT-DECORATION: underline
	}
	TR.alpha {
		BACKGROUND-COLOR: #6699cc
	}
	TD.alpha {
		BACKGROUND-COLOR: #6699cc
	}
	FONT.alpha {
		COLOR: #000000; FONT-FAMILY: Tahoma, Arial
	}
	.alpha-neg-alert {
		COLOR: #ff0000
	}
	.alpha-pos-alert {
		COLOR: #007f00
	}
	A.beta {
		COLOR: #000000; TEXT-DECORATION: none
	}
	A.beta:hover {
		COLOR: #000000; TEXT-DECORATION: underline
	}
	TR.beta {
		BACKGROUND-COLOR: #b6cbeb
	}
	TD.beta {
		BACKGROUND-COLOR: #b6cbeb
	}
	FONT.beta {
		COLOR: #000000; FONT-FAMILY: Tahoma, Arial
	}
	.beta-neg-alert {
		COLOR: #ff0000
	}
	.beta-pos-alert {
		COLOR: #007f00
	}
	A.gamma {
		COLOR: #000000; TEXT-DECORATION: none
	}
	A.gamma:hover {
		COLOR: #000000; TEXT-DECORATION: underline
	}
	TR.gamma {
		BACKGROUND-COLOR: #eeeeee
	}
	TD.gamma {
		BACKGROUND-COLOR: #eeeeee
	}
	FONT.gamma {
		COLOR: #000000; FONT-FAMILY: Tahoma, Arial
	}
	.gamma-neg-alert {
		COLOR: #ff0000
	}
	.gamma-pos-alert {
		COLOR: #007f00
	}
	A.bg {
		COLOR: #000000; TEXT-DECORATION: none
	}
	A.bg:hover {
		COLOR: #000000; TEXT-DECORATION: underline
	}
	TR.bg {
		BACKGROUND-COLOR: #ffffff
	}
	TD.bg {
		BACKGROUND-COLOR: #ffffff
	}
	FONT.bg {
		COLOR: #000000; FONT-FAMILY: Tahoma, Arial
	}
	.bg-neg-alert {
		COLOR: #ff0000
	}
	.bg-pos-alert {
		COLOR: #007f00
	}
	.resultInfo {
	   color:#f80;
	   background-color: transparent;
	   text-transform:Uppercase;
	   padding: 5px 5px 5px 0px;
	   margin: 0;
	   font-size: .7em;
	}
	.rnav {
	    padding: 0;
	    font-family: Verdana, Arial, Helvetica, Sans-serif;
	    font-size: 1em;
	    color:#333;
	    background-color:#fff;
	    font-weight:bold; 
	    font-size: .7em;
	}
	.rnavLabel {
	    text-transform: Uppercase;
	    color:#f80;
	    background-color: transparent;
	}
	a.rnavLink {
	    color: #415481;
	    background-color: transparent;
	}
	a:visited.rnavLink {
	    color: #8A9CBD;
	    background-color: transparent;
	}
	a:hover.rnavLink {
	    color: #f80;
	    text-decoration: none;
	    background-color: transparent;
	}
	#login {
    	PADDING-RIGHT: 10px; FONT-SIZE: 12px; FONT-FAMILY: Arial; WHITE-SPACE: nowrap; TEXT-ALIGN: right
    }
	</STYLE>
</head>
<body>
<br>
<br>
<br>
<center><A href="http://www.lietu.com/"><IMG src="/images/logo.gif" border=0></A></center>
<center>

<form method="get" action="search.jsp">

<TABLE width="400" border=0 bgcolor="#e5ecf9">
			<TR align="center"><br>
				<TD colSpan=3 height=30>
					<FONT style="FONT-SIZE: 14px">
						&nbsp;网页&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="http://www.lietu.com/train">培训</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="http://www.lietu.com/job/">招聘</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="http://www.lietu.com/lietuwebsites.htm">网址</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="more.htm">更多&raquo;</a>
					</FONT>
				</TD>
			</TR>
			<TR>
				<TD>
					
					<input autocomplete="off" type="text" name="query" id="zip" style="width:315px;" class="wd" />
					<div class="auto_complete" id="zip_values"></div>
              <script type="text/javascript">
	          new Ajax.Autocompleter('zip', 'zip_values',
                                 'autoComplete', {afterUpdateElement : getSelectionId});
                  function getSelectionId(text, li) {
			window.location = "./index.jsp?query="+encodeURIComponent(text.value);
			}
           	  </script>
			
				</TD>
				<TD>
					<INPUT type=submit value=猎兔搜索>
				</TD>
				<TD>
					&nbsp;
					<!-- 
					<A class=c href="./AdvancedSearch.htm"><FONT style="FONT-SIZE: 14px">高级搜索</font></A>
					-->
				</TD>
			</TR>
</TABLE>
</form>
</center>

<br>
<br>
<br>


<br>
<br>
<br>
<br>

<center>
<TABLE border=0>
			<TR><TD>
				<FONT style="FONT-SIZE: 14px">
				<A class=c 
				href="./demo/index.jsp">搜索产品</A>
				<A class=c 
				href="./doc/index.jsp">技术文档</A>
				<A class=c 
				href="./case/index.jsp">成功案例</A>
				<A class=c 
				href="./news/index.jsp">猎兔新闻</A>
				<A class=c 
				href="./AboutUs.jsp">联系猎兔</A>
				<A class=c 
				href="./en/">ENGLISH</A><BR>
				</FONT>
			</TD></TR>
</TABLE>
</center>

<FONT style="FONT-SIZE: 14px"><CENTER>&copy;2012 Lietu   &nbsp;<a href=http://www.miibeian.gov.cn/ target=_blank>京ICP备06008726号</a></CENTER></FONT>
 </body>
</html>
