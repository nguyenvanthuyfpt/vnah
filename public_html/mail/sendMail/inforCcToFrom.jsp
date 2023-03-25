<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" class="infor-cc-hill">
<tr>
<td nowrap><B><bean:message key="mail.list.createUser" bundle="<%=interfaces%>"/>: </B><a href="#">
<span onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<bean:write name="BInforCC" property="from" />');">
    <bean:write name="BInforCC" property="from" /></span></a>
</td>
</tr>
<tr>
<td nowrap><B><bean:message key="mail.list.userRecv" bundle="<%=interfaces%>"/>: </B>
    <bean:define name="BInforCC" property="tos" id="tos" />
    <%javax.mail.internet.InternetAddress[] tosTem=(javax.mail.internet.InternetAddress[])tos;%>
    <%for( int i=0; i<tosTem.length; i++ ){%>
    <%String personal="";%>
    <%personal=tosTem[i].getPersonal()==null?"":tosTem[i].getPersonal();%>
    <a href="#">
    <span onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=personal + " <" + tosTem[i].getAddress() + ">" %>');">
            <%=  personal + " &lt;" + tosTem[i].getAddress() + "&gt;" + ";"%>
    </span></a>
            <br>
    <%}%>
</td>
</tr>
<tr>
<td nowrap><B><bean:message key="mail.list.cc" bundle="<%=interfaces%>"/>: </B>
    <bean:define name="BInforCC" property="ccs" id="ccs" />
    <%javax.mail.internet.InternetAddress[] ccsTem=(javax.mail.internet.InternetAddress[])ccs;%>
    
    <%for( int i=0; i<ccsTem.length; i++ ){%>
    <%String personal="";%>
    <%personal=ccsTem[i].getPersonal()==null?"":ccsTem[i].getPersonal();%>
            <a href="#"><span onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=  personal + " <" + ccsTem[i].getAddress() + ">" %>');">
            <%=  personal + " &lt;" + ccsTem[i].getAddress() + "&gt;" + ";"%>
            </span></a>
            <br>
    <%}%>
</td>
</tr>
</table>