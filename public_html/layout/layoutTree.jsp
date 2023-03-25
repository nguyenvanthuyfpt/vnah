<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="height:100%;"  xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head><jsp:include page="/commons/set.jsp"/>
</head>
<body style="height:100%;"  >
<script language="javascript" src="<%=contextPath%>/js/tooltip/wz_tooltip.js"></script>
<script language="javascript" src="<%=contextPath%>/js/tooltip/tip_centerwindow.js"></script>
<script language="javascript" src="<%=contextPath%>/js/tooltip/tip_followscroll.js"></script>
<span class="messageClass" >
<span style="width:50%;padding-right:0px;margin-right:0px;padding-top:0px;margin-top:0px;float:right;height:25px;">
<jsp:include page="/admin/alert.jsp"/>
</span>
</span>
<table cellpadding="0" cellspacing="0" border="0" width="100%" class="tblpage" >
        <tr>
            <td class="head" height="50px" valign="top">
                <div class="flash"></div>
            </td>
        </tr>
        <tr>
            <td valign="top" class="tdmenu">
                    <div id="menu">
                           <jsp:include page="/commons/mainMenu.jsp"/>
                    </div>
            </td>
        </tr>
        <tr>
        <td valign="top" class="pn_main" align="center">
                <table style="border-collapse: collapse" cellpadding="0" cellspacing="0" height="550px" border="0" width="100%"  bgcolor="#ffffff">
                    <tr>
                        <td width="20px" valign="top" bgcolor="#ececec" style="margin-right:5px" class="cssborder">
                            <jsp:include page="/commons/modules.jsp" />
                        </td>
                        <td style="width:200px;" valign="top"  id="left"><tiles:insert attribute="tree" ignore="true"/></td>
                        <td valign="top" class="cssborder" width="100%" style="margin-right:5px;height:100%"  id="right" >
                        <tiles:insert attribute="content" ignore="true"/>
                        </td>
                    </tr>
                    <tr><td colspan="3" class="footer">
                            <div style="padding:20px 0 15px 0">
                             <jsp:include page="/commons/footer.jsp"/>
                            </div>
                    </td></tr>
                </table> 
        </td>
        </tr>
</table> 
</body>
</html>
