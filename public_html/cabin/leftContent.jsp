<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function postTabCabin(forward){
     post('change',anchor + ':'+forward);
     messageImg('right');
}
</script>

<html:form action="cabinSelect" method="POST" />
<bean:define name="cabin" id="type_temp" property="type" type="java.lang.Integer"/>
    <%
      String type=type_temp+"";
    %>
<div id="left">  
         <div class="ctn-left">
            <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="title.template.label.cabin" bundle="<%=interfaces%>"/></div></div>
            <div class="csstable">
             <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px" nowrap><span class="add">
                        <Strong><bean:message key="cmd.cabin.add.caterory" bundle="<%=interfaces%>"/></strong>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                    <td align="right" nowrap width="100%" style="padding-right:10px;"></td>
                </tr>
                </table>
            </div>
                <% String type_CABIN_PRIVATE = com.inf.cabin.IKeyCabin.CABIN_PRIVATE + "";  %>
                <% String type_CABIN_SHARE_ = com.inf.cabin.IKeyCabin.CABIN_SHARE + "";  %>

                <logic:equal name="cabin" property="type" value="<%=type_CABIN_PRIVATE%>">
                    <div  class="csstable" align="left" id="idCabinType">
                            <jsp:include page="/cabin/cabinType/context.jsp" />
                    </div>
                </logic:equal>
                <%if(me.isRole(com.inf.IRoles.rCABIN_PUBLIC)){%>
                <% String type_rCABIN_PUBLIC = com.inf.cabin.IKeyCabin.CABIN_PUBLIC + "";  %>
                <logic:equal name="cabin" property="type" value="<%=type_rCABIN_PUBLIC%>">
                    <div  class="csstable" align="left" id="idCabinType">
                            <jsp:include page="/cabin/cabinType/context.jsp" />
                    </div>
                </logic:equal>
                <%}%>
                <%if(me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                <% String type_rCABIN_ONE = com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT + ""; 
                String demartment=(int)me.getDepartmentID()+"";%>
                <logic:equal name="cabin" property="departmentID" value="<%=demartment%>">
                    <logic:equal name="cabin" property="type" value="<%=type_rCABIN_ONE%>">
                        <div  class="csstable" align="left" id="idCabinType">
                                <jsp:include page="/cabin/cabinType/context.jsp" />
                        </div>
                    </logic:equal>
                </logic:equal>
                <%}%>

            <div align="left" style="height:5px">
            </div>


                <%  if(request.getSession().getAttribute("04.01") !=null){ %>
                <div class="tdTree" align="left">
                            <script language=javascript>
                                dTreeCabin0 = new dTree('dTreeCabin0','nodeDepartment');
                                <%String cabinPre=com.inf.cabin.IKeyCabin.CABIN_PRIVATE+""; %>
                                dTreeCabin0.add(0,-1,'<bean:message key="header.cabin.label.public" bundle="<%=interfaces%>"/>',"javascript:postTabCabin('_CABIN:type:0:cabinType_id:0');");
                                <logic:iterate id="bean" name="BCabinTypes0" type="com.form.cabin.cabinType.FCabinType">
                                dTreeCabin0.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:postTabCabin('_CABIN:type:0:cabinType_id:<%=bean.getId()%>')");
                                </logic:iterate>         
                                </script>
                                <script type="text/javascript">
                                document.write(dTreeCabin0);
                            </script> 
                </div>
                <%}%>
                
                <%  if(request.getSession().getAttribute("04.04") !=null){ %>
                <div class="tdTree" align="left">
                            <script language=javascript>
                                dTreeCabin3 = new dTree('dTreeCabin3','nodeDepartment');
                                <%String cabinPre=com.inf.cabin.IKeyCabin.CABIN_PRIVATE+""; %>
                                dTreeCabin3.add(0,-1,'<bean:message key="header.cabin.label.deps" bundle="<%=interfaces%>"/>',"javascript:postTabCabin('_CABIN:type:3:cabinType_id:0');");
                                <logic:iterate id="bean" name="BCabinTypes3" type="com.form.cabin.cabinType.FCabinType">
                                dTreeCabin3.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:postTabCabin('_CABIN:type:3:cabinType_id:<%=bean.getId()%>')");
                                </logic:iterate>         
                                </script>
                                <script type="text/javascript">
                                document.write(dTreeCabin3);
                            </script> 
                </div>

                 <%}%>
                
                <%  if(request.getSession().getAttribute("04.02") !=null){ %>
                <div class="tdTree" align="left">
                            <script language=javascript>
                                dTreeCabinType = new dTree('dTreeCabinType','nodeDepartment');
                                <%String cabinPre=com.inf.cabin.IKeyCabin.CABIN_PRIVATE+""; %>
                                dTreeCabinType.add(0,-1,'<bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/>',"javascript:postTabCabin('_CABIN:type:1:cabinType_id:0');");
                                <logic:iterate id="bean" name="BCabinTypes1" type="com.form.cabin.cabinType.FCabinType">
                                dTreeCabinType.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:postTabCabin('_CABIN:type:1:cabinType_id:<%=bean.getId()%>')");
                                </logic:iterate>         
                                </script>
                                <script type="text/javascript">
                                document.write(dTreeCabinType);
                            </script> 
                </div>
                 <%}%>
                  <%  if(request.getSession().getAttribute("04.03") !=null){ %>
                <div class="tdTree" align="left">
                            <script language=javascript>
                                dTreeCabin2 = new dTree('dTreeCabin2','nodeDepartment');
                                dTreeCabin2.add(0,-1,'<bean:message key="header.cabin.label.shere" bundle="<%=interfaces%>"/>',"javascript:postTabCabin('_CABIN:type:2:cabinType_id:0');",'','','images/fileshare.png');
                                </script>
                                <script type="text/javascript">
                                document.write(dTreeCabin2);
                            </script> 
                </div>
                 <%}%>
        </div> 
</div>     
