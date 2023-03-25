 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <logic:present name="BReportsList">
  <table  style="border-collapse:collapse" cellpadding="5" cellspacing="0" border="0" width="100%" >
    <TBODY>
        <tr>
            <td colspan="4" class="title" ><bean:message key="report.report.list" bundle="<%=interfaces%>"/>  <bean:write name="BReport" property="workerName"/></td>
        </tr>
        <TR>
            <th class="col" width="5%"><bean:message key="report.stt.caption" bundle="<%=interfaces%>"/></th>
            <th class="col"  nowrap width="15%"> <bean:message key="report.creator.caption" bundle="<%=interfaces%>"/></th>
             <th class="col"  nowrap> <bean:message key="report.name.caption" bundle="<%=interfaces%>"/></th>
            <th class="col"  nowrap width="15%" > <bean:message key="report.timeReport.caption" bundle="<%=interfaces%>"/></th>            
            <th class="col" width="10%" nowrap> <bean:message key="report.file.caption" bundle="<%=interfaces%>"/></th>            
        </tr>       
       
       
         <bean:define name="BReportsList" id="beans" type="com.form.FBeans"/>
        <%  int i = beans.getFirstRecord();%>
           <logic:iterate name="BReportsList" id="bean" type="com.form.tasks.report.FReport">        
            <TR>
                <td  align="center"><%=++i%></td>
                <td  align="center"></td>
                <td  ><bean:write name="bean" property="report"/></td>                
                <td  align="center"><bean:write name="bean" property="timeReport"/></td>               
                <td  align="center">
                 <logic:notEqual name="bean" property="fileName" value="">
                    <% String[] temp = bean.getFileName().split("#"); String nameTemp = temp[1] ;%>
                     <a href="javascript:post('problem',anchor + ':_GET:id:<%=bean.getId()%>:fileName:<%=bean.getFileName()%>');remove('problem',anchor)"><%=nameTemp%></a>
                  </logic:notEqual>  
                   
                </td>                
            </tr>
          </logic:iterate>
       
    </tbody>
  </table>   
  <table align="right">
      <tr>
            <td> <jsp:include page="/tasks/report/paging.jsp"/>  </td>
      </tr>
  </table>
 </logic:present>