<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="reports" method="post" enctype="multipart/form-data">
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" >
                        <ul id="ui-tabs-nav">
                        <li class="ui-tabs-selected">
                        </li>
                        </ul>
                    </td>
                </tr>
            </table>            
    </div>   
     <div id="fragment-1">                                         
               <div class="content-calendar" align="left"> 
                <table class="tableForm" cellpadding="0" style="border-collapse: collapse" cellspacing="0" border="0" width="100%">    
                                            <tr>
                                                    <td valign="top" nowrap width="100px" ><span id="lableId" ><bean:message key="menu.top.report.caption" bundle="<%=interfaces%>"/></span></td>
                                                    <td valign="top" >

                                                        <select multiple="multiple" name="userIdS" id="userIdS" ondblclick="javascript:removeItem(this,this.form,0)" style="width:250px;height:60px">
                                                            <logic:notEmpty name="reports" property="empsRev" >
                                                                 <logic:iterate name="reports" property="empsRev" id="bRecv"  type="com.form.report.FReport"> 
                                                                        <option value="<bean:write name="bRecv" property="toPertion"/>">
                                                                        <bean:write name="bRecv" property="userFullName"/></option>
                                                                </logic:iterate>
                                                            </logic:notEmpty> 
                                                        </select>
                    
                                                    </td>
                                            </tr>
                                            <tr>
                                                <td  nowrap><bean:message key="title.report.label.name" bundle="<%=interfaces%>"/>:<bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD></td>
                                                <td align="left"><html:text name="reports" property="name" maxlength="100" style="width:100%;" /></td>
                                            </tr>            
                                            <tr>
                                                <td  nowrap><bean:message key="title.report.label.description" bundle="<%=interfaces%>"/>:</td>
                                                <td align="left">
                                                <textarea id="description" name="description" style="width:100%;height:40px">
                                                        <bean:write name="reports" property="description"/>
                                                </textarea>
                                                </td>
                                            </tr>
                                            
                                             <tr>
                                                <td  nowrap><bean:message key="title.report.label.upFile" bundle="<%=interfaces%>"/>:</td>
                                                <td align="left"><html:file name="reports" property="upFile" size="50px" /></td>
                                            </tr>   
                                        <tr>
                                            <td  nowrap><bean:message key="title.report.label.reportType_id" bundle="<%=interfaces%>"/>:</td>
                                            <td align="left">
                                            <html:select name="reports" property="reportType_id" styleClass="fieldSelect"  >
                                                    <logic:present name="BReportTypes">
                                                     <html:options collection="BReportTypes" property="id" labelProperty="name"/>
                                                     </logic:present>
                                              </html:select>
                                            </td>
                                        </tr>
                                    
                                    <tr>
                                    <td  align="left" class="toolCmd" colspan="2">
                                            <jsp:include page="/report/cmd.jsp" />
                                    </td>
                                    </tr>
                                </table>
                                <html:hidden name="reports" property="type" />                                  
                               </div>
                        </div>
</div>
</div>
</html:form>
