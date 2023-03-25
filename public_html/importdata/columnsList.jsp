<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
                                          <logic:notEmpty name="BColumnsList"> 
                                            <table class="list-voffice" cellpadding="0" cellspacing="0">
                                                 <logic:iterate name="BColumnsList" id="bean" type="com.form.importdata.FImportData"> 
                                                    <tr>
                                                        <td width="100px">
                                                            <bean:write name="bean" property="columnName" />
                                                        </td>
                                                        <td width="100px">
                                                            <bean:write name="bean" property="columnTypeName" />
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