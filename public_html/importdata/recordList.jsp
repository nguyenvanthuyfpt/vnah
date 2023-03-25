<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
    <bean:message key="title.insertdata.record.list" bundle="<%=interfaces%>"/>
          <logic:notEmpty name="BRecordList"> 
    <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 900px; HEIGHT: 200px;">

        <table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                 <logic:iterate name="BColumnsList" id="bean" type="com.form.importdata.FImportData"> 
                    <th>
                        <bean:write name="bean" property="columnName" />
                    </th>
                </logic:iterate>
            </tr>
                <bean:define name="BRecordList" id="recordList" type="java.lang.String[][]"/>
                <%
                    for (int k = 0; k < recordList.length; k++) {%>
                    <tr class="<%=(k%2==0)?"content1":"content"%>">
                    <%
                    if (recordList[k]==null){
                        break;
                    }
                    for(int j = 0; j < recordList[k].length; j++){%>
                       <td>
                       <%=recordList[k][j]%>
                       </td>
                     <%}%>
                <%}%>
            </tr>
        </table>

    </div>
        <logic:present name="BRecordList" >
            <div class="toolCmd" style="padding-left:10px;WIDTH: 880px;" align="left">
            <table width="100%" cellpadding="0" cellspacing="0" width="100%" border="0">
                <tr>
                    <td align="left">
                    <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <%=recordList.length%></strong></td>
                    <td align="right">
                     <%String params = anchor + ":_SELECT_SQL:id_sql:1";%>
                    <jsp:include page="/paging.jsp">
                        <jsp:param name="BEANS" value="BPaging"/>
                         <jsp:param name="PARAMS" value="<%=params%>"/>
                        <jsp:param name="FORM" value="importdata"/>
                        <jsp:param name="METHOD" value="postAjax"/>
                        <jsp:param name="POSITION" value="idrecordlist"/>
                    </jsp:include>
                    </td>
                </tr>
            </table>
            </div>
            </logic:present>
     </logic:notEmpty>