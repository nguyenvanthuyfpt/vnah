<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="reportSystem" method="POST">
<TABLE class="adminlist" >
<TR>
    <TD nowrap>
    T&#7915; ng&#224;y  <html:text name="reportSystem" property="fromDate" style="width:80px" />
    <img src="<%=contextPath%>/images/ew_calendar.gif"  onClick="popUpCalendar(this,'fromDate','dd/mm/yyyy',null);"/>
    &#272;&#7871;n ng&#224;y  <html:text name="reportSystem" property="toDate" style="width:80px" />
    <img src="<%=contextPath%>/images/ew_calendar.gif"  onClick="popUpCalendar(this,'toDate','dd/mm/yyyy',null);"/>
    C&#7897;t <html:text name="reportSystem" property="columNumber" style="width:25px" />
    D&#242;ng <html:text name="reportSystem" property="rowNumber" style="width:25px" />
    
    list id <html:text name="reportSystem" property="listId" style="width:25px" />
    
    <html:radio name="reportSystem" property="stylePrint" value="0" />
    Xu&#7845;t d&#7885;c 
    <html:radio name="reportSystem" property="stylePrint" value="1" />
    Xu&#7845;t ngang
    <label for="total" style="cursor:pointer;">
    <html:checkbox name="reportSystem" property="total" styleId="total" value="1"  />
    T&#7893;ng h&#7907;p b&#225;o c&#225;o
    </label>
    </td>
    <td colspan="3" align="right" nowrap >
        
    </td>
</TR>
<tr>
    <td colspan="4">
    <div align="left">Truy v&#7845;n :</div>
        <html:textarea name="reportSystem" property="selectSql" styleId="selectSql" onkeyup="rencodeforsql(this)" style="width:750px;height:80px" />
    </td>
</tr>
<tr>
    <td colspan="4" nowrap>
        T&#234;n t&#7879;p b&#225;o c&#225;o 
        <html:text name="reportSystem" property="nameFile" style="width:120px" />
        T&#234;n c&#7911;a b&#225;o c&#225;o 
        <html:text name="reportSystem" property="nameOfFileVn" style="width:320px" />
    </td>
</tr>
<tr>
    <td colspan="4">
        <%String onclickCreate="postAjax('reportSystem','QFrameTree',anchor +':_CREATE')";%>
        <html:button property="_CREATE" onclick="<%=onclickCreate%>"  styleClass="button">
        <bean:message key="action.insert" bundle="<%=interfaces%>"/>
        </html:button>
        <bean:define name="reportSystem" property="id" id="id" type="java.lang.Integer" />
        <%String onclickUpdate="postAjax('reportSystem','QFrameTree',anchor +':_EDIT:id:"+id+"')";%>
        <html:button property="_EDIT" onclick="<%=onclickUpdate%>"  styleClass="button">
        L&#432;u l&#7841;i
        </html:button>
        <%String onclickDelete="postAjax('reportSystem','QFrameTree',anchor +':_DELETE:id:"+id+"')";%>
        <html:button property="_DELETE" onclick="<%=onclickDelete%>"  styleClass="button">
        X&#243;a
        </html:button>
        <%String onclickReport="post('reportSystem',anchor +':_REPORT');remove('reportSystem',anchor)";%>
        <html:button property="_REPORT"   styleClass="button" onclick="<%=onclickReport%>" style="width:120px">
        Xu&#7845;t b&#225;o c&#225;o
        </html:button>
        <%String onclickView="postAjax('reportSystem','tdodyList',anchor +':_VIEW:id:"+id+"')";%>
        <html:button property="_VIEW" onclick="<%=onclickView%>"  styleClass="button">
        Xem d&#7919; li&#7879;u
        </html:button>
    </td>
</tr>
<tr>
    <td colspan="4" id="tdodyList" >
        <jsp:include page="/admin/reportSystem/list.jsp" />
    </td>
</tr>
</TABLE>
</html:form>
