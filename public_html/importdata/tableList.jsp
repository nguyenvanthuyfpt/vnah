<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
                        <bean:message key="title.insertdata.table.list" bundle="<%=interfaces%>"/><br>
                                     <select multiple="multiple" name="nameTable" id="nameTable" class="combo-text" style="width:150px;height:250px" ondblclick="showtables(this);">
                                             <logic:iterate name="BTablesList" id="bean" type="com.form.importdata.FImportData"> 
                                                    <option value="<bean:write name="bean" property="nameTable"/>"><bean:write name="bean" property="nameTable"/></option>
                                            </logic:iterate>
                                    </select>
