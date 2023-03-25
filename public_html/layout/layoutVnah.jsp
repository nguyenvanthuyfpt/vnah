<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head><jsp:include page="/commons/setVnah.jsp"/>
</head>
<body>
<script language="javascript" src="<%=contextPath%>/js/tooltip/wz_tooltip.js"></script>
<script language="javascript" src="<%=contextPath%>/js/tooltip/tip_centerwindow.js"></script>
<script language="javascript" src="<%=contextPath%>/js/tooltip/tip_followscroll.js"></script>
<html:form action="login" method="post"/>
<html:form action="lits" method="post"/>
<html:form action="tasks" method="post"/>
<html:form action="change" method="post"/>
<html:form action="changedata" method="post"/>
<html:form action="function" method="post" />
<html:form action="main" method="post" />
<html:form action="docssendMain" method="post" />
<html:form action="docsrecvMain" method="post" />
<html:form action="loginEmail" method="post" />
<html:form action="messsagesListMain" method="post" />
<html:form action="problemMain" method="post" />
<html:form action="addPortlet" method="post" />
<html:form action="emailListMain" method="post" />
<html:form action="disabilityFuntion" method="post" />

<div id="pagewidth">
	<div class="imgTop">
        <img src="images/front/img_head.gif" width="960" height="5" />
        </div>
    <div id="main">
    	<!--start head-->
        <div id="head">
        	<div class="top">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr><td width="182"><div class="logo"><a href="#"><img src="images/front/logo.jpg" /></a></div></td>
                        <td><img src="images/front/slogan.jpg" /></td>
                        <td valign="bottom" align="right"><div class="toplink fr">
                        <a href="https://www.vnah-hev.org/" target="_blank"><img src="images/front/logo-vnah.jpg" /></a>
                        <!--<span class="bt_left_current">
                            <span class="bt_right_current">
                            <a href="javascript:post('change',anchor + ':_MESSAGES:type:1')"><bean:message key="app.inbox.messages.main" bundle="<%=interfaces%>"/></a>
                            </span>
                        </span>
                        <span class="bt_left_current"><span class="bt_right_current">
                        <a href="javascript:post('change',anchor + ':_PREPARE_BROADCAST')"><bean:message key="broadcast.list.caption" bundle="<%=interfaces%>"/></a>
                        </span></span><span class="bt_left_normal"><span class="bt_right_normal">
                        <a href="javascript:post('change',anchor + ':_CABIN:type:1:cabinType_id:0')"><bean:message key="title.report.label.library" bundle="<%=interfaces%>"/></a>
                        </span></span>--></div></td>
                    </tr>
                </table>
        </div>
        <div class="banner" align="right"><img src="images/front/banner.jpg" width="959" height="130" /></div>
        <div class="spacer"><img src="images/front/spacer.gif" width="1" height="1" /></div>
        </div>
        <!-- end head-->
        
        <!--start center-->
        <div id="center-2">
        	<!--leftcol-->
        	<div id="leftcol">
            	<div class="leftmenu">
                	<tiles:insert attribute="tree" ignore="true"/>
                </div>
            </div>
            <!--e: leftcol-->
            <div id="maincol">
            	<div align="right" class="linkNav">
                <jsp:include page="/commons/mainMenuVnah.jsp"/>
                </div>
                <!-- e: -->
                <div class="box2">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <TR>
                                <TD id="right" ><tiles:insert attribute="content" ignore="true"/></td>
                            </tr>
                            <!--<TR>
                                <TD><jsp:include page="/admin/alert.jsp"/></td>
                            </tr>-->
                        </table>
                </div>
                <!-- e: -->
            </div>
            <div class="clear"></div>
        </div>
        <!-- e: center-->
        
        <!-- start footer -->
        <div id="footer" >
            <jsp:include page="/commons/footerVnah.jsp"/>
        </div>
        <!-- e: footer -->
        <!--<a href="#" id="back-to-top" title="Back to top">&uarr;</a>-->
    </div>
     <div class="imgBottom">
        <img src="images/front/img_footer.gif" width="960" height="5" />
    </div>
</div>

</body>
</html>
