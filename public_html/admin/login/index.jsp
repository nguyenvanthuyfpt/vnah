<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<% request.setAttribute("SESSION.DENY.GUEST","FALSE");%>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head>
	<jsp:include page="/commons/setVnah.jsp"/>
</head>
<%
    com.form.FBeans beansTinh = new com.bo.disability.categorys.BTinh().getAllRecordByParentId(0);
%>
<body>
<div id="pagewidth">
    <!--<div class="imgTop"><img src="<%=contextPath%>/images/front/img_head.gif" width="960" height="5" /></div>-->
    <div id="main">
    	<!--start head-->
        <div id="head" >
        	<div class="top">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td width="182px;">
                            <div class="logo"><img src="<%=contextPath%>/images/front/logo.jpg" /></div></td>
                        <td>
                            <div style="float:left;"><img src="<%=contextPath%>/images/front/slogan.jpg" /></div>
                      </td>
                    <td width="250px;">
                        <div class="logo">
                          <a href="https://www.vnah-hev.org/" target="_blank"><img src="images/front/logo-vnah.jpg" /></a>
                        </div>
                      </td> 
                    </tr>
                </table>
        	</div>
        </div>
        <!-- end head-->
        
        <!--start center-->
        <div>
        	<table cellpadding="0" cellspacing="0" border="0" width="953" height="374" class="table1">
            	<tr><td valign="top"><div class="boxLogin" align="center">
                <table cellpadding="0" cellspacing="0" height="173" border="0"><tr><td align="center">
                <html:form action="/login" method="post" onsubmit="pw2md5(document.login.md5pw,document.login.password);">
                <input type="hidden" name="password">
                <input type="hidden" name="<%=anchor%>" value="_LOGIN">

                        <input type="text" class="text" name="username" id="username" onblur="if (this.value=='') this.value='<bean:message key='username.caption' bundle='<%=interfaces%>'/> ...';" onfocus='this.value="";' value="<bean:message key='username.caption' bundle='<%=interfaces%>'/>..." /><br />
                        <input class="text" name="md5pw" type="password" style="margin:3px;" onblur="if (this.value=='') this.value='<bean:message key='passwork.caption' bundle='<%=interfaces%>'/> ...';" onfocus='this.value="";' value="<bean:message key='passwork.caption' bundle='<%=interfaces%>'/>..." />
                      
                        <br />
                        <span class="bt_left_Login"><span class="bt_right_Login"><span class="bt_center_Login">
                                <html:submit property="signin" >
                                      <bean:message key="login.auth" bundle="<%=interfaces%>"/>
                                </html:submit>
						</span></span></span><br />
                        <span class="forget">(*) <a href="#"><bean:message key="login.forgot.password" bundle="<%=interfaces%>"/></a></span>
                </html:form>
                </td></tr></table>
                </div></td></tr>
                <tr>
                    <td><div class="box1" style="display:none;">
                	<div class="fl">
                    	<table cellpadding="0" cellspacing="0" border="0">
                        	<tr><td><span style="padding:0 22px"><a href="#"><img src="<%=contextPath%>/images/front/ico_01.gif" /></a></span></td>
                            	<td><span class="linkWhite"><b><a href="#">Qu&#7843;n l&#253;<br />Th&#244;ng tin ng&#432;&#7901;i khuy&#7871;t t&#226;t.</a></b></span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="fr grouplink" style="display:none;">
                        <span class="bt_left"><span class="bt_right"><html:link page="/admin/home/">Trang ch&#7911;</html:link></span></span>
                    	<span class="bt_left"><span class="bt_right"><a href="#">Th&#432; n&#7897;i b&#7897;</a></span></span>
                        <span class="bt_left"><span class="bt_right"><a href="#">B&#7843;ng tin</a></span></span>                        
                        <span class="bt_left"><span class="bt_right"><a href="#">Th&#432; vi&#7879;n l&#432;u tr&#7919;</a></span></span>                        
                    </div>
                    <div class="clear"></div>
                </div></td></tr>                
            </table>
        </div>
        <!-- e: center-->
        
        <!-- start footer -->
        <div id="footer">
            <div class="copyright">    
                <table width="100%">
                <tr>
                    <td align="left">
                        <span class="logo2"><img src="<%=contextPath%>/images/front/logo-usaid.jpg" /></span>
                    </td>
                    <td align="center">
                        <bean:message key="app.company" bundle="<%=interfaces%>"/>
                    </td>
                    <td align="right">
                        <span class="logo2"><img src="<%=contextPath%>/images/front/logo2.gif" /></span>
                        <span class=""><img src="<%=contextPath%>/images/front/img_01.jpg" /></span>
                    </td>
                </tr>
                </table>
                <div class="clear"></div>
            </div>
        </div>
        <!-- e: footer -->
        
    </div>
     <div class="imgBottom"><img src="<%=contextPath%>/images/front/img_footer.gif" width="960" height="5" /></div>
</div>
</body>
</html>
