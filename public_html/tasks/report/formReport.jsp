<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<bean:define name="report" property="complate" id="complate"  type="java.lang.Integer"/>
<html:hidden name="report" property="secureId"/> 
<html:hidden name="report" property="assignId"/>
<html:hidden name="report" property="incharge"/>
<html:hidden name="report" property="problemId"/>
<html:hidden name="report" property="creatorName"/>   
<bean:define name="report" property="creator" id="creatorValue"  type="java.lang.Integer"/>

<table  style="border-collapse:collapse;background-color:#ABBDD5;border:1px solid #ABBDD5" align="left" cellpadding="0"  cellspacing="0"  width="100%" >
        <tr>
            <td align="left" valign="top" width="50px" nowrap style="padding-left: 30px;padding-right: 8px;"><bean:message key="report.name.caption" bundle="<%=interfaces%>"/></td>
            <td align="left"> <html:textarea name="report" property="report" style="width:300px;height:50px"/></td>
            <% if (creatorValue.intValue()==(int)me.getId()){ %>
            <logic:present name="BEmpRecv">
            <td rowspan="3" valign="top" align="center" style="margin:0px;padding:0px">
                    <div class="toolCmd" style="margin:0px;padding:0px" ><Strong><bean:message key="list.report.from.userrecv" bundle="<%=interfaces%>"/></strong></div>
                    <div class="scorTaskChoseEmp" >
                    <logic:iterate name="BEmpRecv" id="bean" type="com.form.tasks.problem.FProblem">  
                            <div><input type="checkbox" name="checkEmp" id="checkEmp" checked="true" value="<bean:write name="bean" property="worker" />" ><bean:write name="bean" property="workerName" /></div>
                    </logic:iterate>
                    </div>
            </td>
            </logic:present>
            <%}%>
        </tr>
        <tr>
            <td align="left" style="padding-left: 30px;padding-right: 8px" nowrap><bean:message key="report.file.caption" bundle="<%=interfaces%>"/></td>
            <td align="left" ><html:file name="report" property="fileUplaod" size="40"/> </td>
        </tr>   
      
         <tr>
            <td valign="top" align="left" style="padding-left: 30px;padding-right: 8px" nowrap>
            <% if (creatorValue.intValue()!=(int)me.getId()){ %><bean:message key="problem.complate" bundle="<%=interfaces%>"/><%}%>
            </td>
            <td align="left">
                    <% if (creatorValue.intValue()!=(int)me.getId()){ %>
                    <select  styleClass="inputbox" style="width:60px" name="complate" id="complate">                                   
                    <%for (int i=0;i<=100;i+=5){
                    String selected = complate.intValue() == i?"selected":"";                                     
                    %>                                    
                    <option  value="<%=i%>" <%=selected%>><%=i%> %</option>
                    <%}%>
                    </select>
                     <%}%>
                 <html:button property="_EDIT"  style="width:60px" onclick="this.disabled=true;post('problem',anchor+':_CREATE_REPORT')" styleClass="button">                       
                        <% if (creatorValue.intValue()==(int)me.getId()){ %>
                            <bean:message key="report.review.caption" bundle="<%=interfaces%>"/>
                      <%}else{%>
                              <bean:message key="task.cmd.report.caption" bundle="<%=interfaces%>"/>
                       <%}%> 
                 </html:button>                                                 
            </td>
        </tr>
         
        
     
        
</table>        

     
            
            
  
 
 