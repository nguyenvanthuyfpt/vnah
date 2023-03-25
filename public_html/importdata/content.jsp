<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function showtables(obj){
            document.importdata.nameTable.value=obj.options[obj.selectedIndex].text;
            post('importdata',anchor + ':_SELECT');
    }
    function removetables(obj){
            document.importdata.nameTable.value=obj.options[obj.selectedIndex].text;
            postAjax('importdata','idtablelist',anchor + ':_DELETE');
    }
</script>
<html:form action="importdata" method="POST" >

<div class="padding-content">     
<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
        <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" width="180px" nowrap>
                            <div class="ctn-left">
                                <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic">
                                    <bean:message key="title.insertdata.listname" bundle="<%=interfaces%>"/>
                                </div></div>
                            </div>  
                    </td>
                    <td valign="top" >
                        <ul id="ui-tabs-nav"><li class="ui-tabs-selected"></li><li class="" ></li></ul>
                    </td>
                </tr>
            </table>        
        <div id="fragment-1">           
            <div class="content-calendar" id="vtCalendar" align="left">
                
                <table width="100%" cellpadding="0" cellspacing="0"   >
                    <tr>
                        <td width="200px" align="left">
                            <table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
                                    <tr>
                                        <td><input class="inputClassSearch" type="text" onkeyup="postAjax('importdata','idtablelist',anchor + ':_SELECT_TABLE');" name="contentSearch" id="contentSearch" value=""/></td>
                                        <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('importdata','idtablelist',anchor + ':_SELECT_TABLE');" >&nbsp;</td>
                                    </tr>
                            </table>    
                        <br>
                            <table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
                                    <tr>
                                        <td><input class="inputClassSearch" type="text" name="nameTableAdd" id="nameTableAdd" value=""/></td>
                                        <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('importdata','idtablelist',anchor + ':_ADD_TABLE');" >&nbsp;</td>
                                    </tr>
                            </table>    
                        <br>
                        <div id="idtablelist">
                            <jsp:include page="/importdata/tableList.jsp" />
                        </div>
                        </td>
                        <td>
                            <table class="list-voffice" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>
                                        <table class="list-voffice" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <th width="100px"><bean:message key="title.insertdata.field.name" bundle="<%=interfaces%>"/></th>
                                                    <th width="100px"><bean:message key="title.insertdata.field.type" bundle="<%=interfaces%>"/></th>
                                                    <th width="100px"><bean:message key="title.insertdata.field.clock" bundle="<%=interfaces%>"/></th>
                                                    <th width="100px"><bean:message key="title.insertdata.field.from" bundle="<%=interfaces%>"/></th>
                                                    <th width="100px"><bean:message key="title.insertdata.field.to" bundle="<%=interfaces%>"/></th>
                                                </tr>
                                        </table>
                                         <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 500px; HEIGHT: 250px;">
                                         <logic:notEmpty name="BColumnsList"> 
                                            <table class="list-voffice" cellpadding="0" cellspacing="0">
                                                 <logic:iterate name="BColumnsList" id="bean" type="com.form.importdata.FImportData"> 
                                                    <tr>
                                                        <td width="100px">
                                                            <bean:write name="bean" property="columnName" />
                                                        </td>
                                                        <td width="100px">
                                                            <html:select  style="width:60px" name="bean" property="columnTypeName" >
                                                                    <html:option value="Integer">Integer</html:option>
                                                                    <html:option value="String">String</html:option>
                                                                    <html:option value="Float">Float</html:option>
                                                                    <html:option value="Date">Date</html:option>
                                                              </html:select>
                                                        </td>
                                                     <%if(bean.getNotNull()==2){%>
                                                        <td width="100px" align="center">
                                                            <img style="border:0px" src="<%=contextPath%>/images/checkbox.png" title="Not null"/>
                                                            <img style="border:0px" src="<%=contextPath%>/images/key.png" title="Primary Key"/>
                                                        </td>
                                                        <td width="200px" colspan="2">
                                                        </td>
                                                    <%}else if(bean.getNotNull()==1){%>
                                                         <%if(bean.getColumnTypeName().indexOf("time")==0){%>
                                                            <td width="100px">
                                                            <img style="border:0px" src="<%=contextPath%>/images/checkbox.png" title="Not null"/>
                                                            </td>
                                                            <td width="100px">
                                                                <input disabled type="text" name="<%=bean.getColumnName()+"from"%>" id="<%=bean.getColumnName()+"from"%>" style="width:30px"  />
                                                            </td>
                                                            <td width="100px">
                                                                <input disabled type="text" name="<%=bean.getColumnName()+"to"%>" id="<%=bean.getColumnName()+"to"%>" style="width:50px"  />
                                                            </td>
                                                        <%}else{%>
                                                            <td width="100px">
                                                            <img style="border:0px" src="<%=contextPath%>/images/checkbox.png" title="Not null"/>
                                                            </td>
                                                            <td width="100px">
                                                                <input type="text" name="<%=bean.getColumnName()+"from"%>" id="<%=bean.getColumnName()+"from"%>" style="width:30px"  />
                                                            </td>
                                                            <td width="100px">
                                                                <input type="text" name="<%=bean.getColumnName()+"to"%>" id="<%=bean.getColumnName()+"to"%>" style="width:50px"  />
                                                            </td>
                                                    <%}}else{%>
                                                         <%if(bean.getColumnTypeName().indexOf("time")==0){%>
                                                            <td width="100px">
                                                            <img style="border:0px" src="<%=contextPath%>/images/unchecked.gif" title="Null"/>
                                                            </td>
                                                            <td width="100px">
                                                                <input disabled type="text" name="<%=bean.getColumnName()+"from"%>" id="<%=bean.getColumnName()+"from"%>" style="width:30px"  />
                                                            </td>
                                                            <td width="100px">
                                                                <input disabled type="text" name="<%=bean.getColumnName()+"to"%>" id="<%=bean.getColumnName()+"to"%>" style="width:50px"  />
                                                            </td>
                                                        <%}else{%>
                                                            <td width="100px">
                                                            <img style="border:0px" src="<%=contextPath%>/images/unchecked.gif" title="Null"/>
                                                            </td>
                                                            <td width="100px">
                                                                <input type="text" name="<%=bean.getColumnName()+"from"%>" id="<%=bean.getColumnName()+"from"%>" style="width:30px"  />
                                                            </td>
                                                            <td width="100px">
                                                                <input type="text" name="<%=bean.getColumnName()+"to"%>" id="<%=bean.getColumnName()+"to"%>" style="width:50px"  />
                                                            </td>
                
                                                    <%}}%>
                
                                                    </tr>
                                                </logic:iterate>
                                            </table>
                                         </logic:notEmpty>
                                         </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="550px" colspan="2">
                                            <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                                <tr>
                                                        <td align="right" nowrap><bean:message key="title.insertdata.field.recordnumber" bundle="<%=interfaces%>"/>:</td>
                                                        <td nowrap>
                                                            <html:text name="bean" property="recordNumber" style="width:30px" />
                                                            <a href="javascript:postAjax('importdata','idRunInsertData',anchor + ':_CREATE:nameTable:<bean:write name="importdata" property="nameTable"/>')" >
                                                                <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                                            </a>
                                                        </td>
                                                </tr>
                                            </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="550px" colspan="2">
                                            <table cellpadding="0" cellspacing="0" width="100%" border="0">
                                                <tr>
                                                        <td width="550px">
                                                            <html:textarea  name="importdata" property="nameSQL"  style="width:550px;height:50px;" />
                                                        </td>
                                                        <td nowrap>
                                                            <a href="javascript:postAjax('importdata','idrecordlist',anchor + ':_SELECT_SQL:id_sql:1')" >
                                                                run
                                                            </a>
                                                        </td>
                                                </tr>
                                                <tr>
                                                        <td colspan="2" id="idRunInsertData">
                                                        </td>
                                                </tr>
                                            </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" id="idrecordlist">
                            <jsp:include page="/importdata/recordList.jsp" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
</div>
</div>
</div>
</html:form>