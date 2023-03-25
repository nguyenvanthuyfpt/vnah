<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head>
	<jsp:include page="/commons/setVnah.jsp"/>
</head>
<body>
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
	<div class="imgTop"><img src="<%=contextPath%>/images/front/img_head.gif" width="960" height="5" /></div>
    <div id="main">
    	<!--start head-->
        <div id="head">
        	<div class="top">
              <table cellpadding="0" cellspacing="0" border="0" width="100%">
              <tr>
                <td width="182"><div class="logo"><a href="#"><img src="<%=contextPath%>/images/front/logo.jpg" /></a></div></td>
                <td><img src="<%=contextPath%>/images/front/slogan.jpg" /></td>
                <td>
                  <div class="logo">
                    <a href="https://www.vnah-hev.org/" target="_blank"><img src="images/front/logo-vnah.jpg" /></a>
                  </div>
                </td> 
              </tr>
              </table>
        </div>
        <div class="banner" align="right"><img src="<%=contextPath%>/images/front/banner.jpg" width="959" height="130" /></div>
        <div class="spacer"><img src="<%=contextPath%>/images/front/spacer.gif" width="1" height="1" /></div>
        </div>
        <!-- end head-->
        
        <!--start center-->
        <div id="center-2">
        	
            <div id="maincol">
            	<div align="right" class="linkNav">
                <jsp:include page="/commons/mainMenuVnahHome.jsp"/>
                </div>
                <!-- e: -->
                <div class="box2">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <TR>
                                <TD id="right" ><tiles:insert attribute="content" ignore="true"/></td>
                            </tr>
                            <TR>
                                <TD><jsp:include page="/admin/alert.jsp"/></td>
                            </tr>
                        </table>
                </div>
                <!-- e: -->
            </div>
            
            <!--leftcol-->
        	<div id="leftcol">
            	<div class="leftmenu">
                	<tiles:insert attribute="tree" ignore="true"/>
                </div>
            </div>
            <!--e: leftcol-->
            	
            <div class="clear"></div>
        </div>
        <!-- e: center-->
        
        <!-- start footer -->
        <div id="footer">
         <jsp:include page="/commons/footerVnah.jsp"/>
        	</div>
        <!-- e: footer -->
        
    </div>
     <div class="imgBottom"><img src="<%=contextPath%>/images/front/img_footer.gif" width="960" height="5" /></div>
</div>
</body>
</html>
