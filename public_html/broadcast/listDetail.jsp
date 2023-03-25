<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="broadcast" method="post" >
<div class="padding-content">     
<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
        <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" width="180px">
                            <div class="ctn-left">
                                <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic">
                                    <bean:message key="broadcast.list.caption" bundle="<%=interfaces%>"/>
                                </div></div>
                            </div>  
                    </td>
                    <td valign="top" >
                        <ul id="ui-tabs-nav"><li class="ui-tabs-selected"></li><li class="" ></li></ul>
                    </td>
                </tr>
            </table>        
        <div id="fragment-1">   
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                             <td  align="left" style="padding-left:6px">                      
                                        <div >
                                           <strong>                                           
                                             <bean:write name="BRoadcast" property="title" />                                      
                                           </strong>
                                        </div>
                                        
                                         <logic:notEqual name="BRoadcast" property="content" value="">
                                            <bean:define  name="BRoadcast" property="content" id="content" />
                                            <div><%=content%></div>
                                         </logic:notEqual>
                                         <logic:notEqual name="BRoadcast" property="fulltext" value="">
                                            <bean:define  name="BRoadcast" property="fulltext" id="fulltext" />
                                            <div><%=fulltext%></div>
                                         </logic:notEqual>
                                         
                             </td>                             
                        </tr>
                        <tr>
                            <td align="right"> <span style="float: right;font-size: 10px;padding-right:4px"><strong><bean:write name="BRoadcast" property="fullName" /></strong>: <bean:write name="BRoadcast" property="createtime" /></span></td>
                        </tr>
                       <logic:present name="BRoadcasts" > 
                        <tr>
                            <td align="left" style="padding-left:8px"><hr style="width:100%"></td>
                        </tr>  
                        <tr>
                            <td align="left" style="padding-left:8px"><strong><bean:message key="broadcast.order.onfor.caption" bundle="<%=interfaces%>"/></strong></td>
                        </tr>                        
                        <tr>
                            <td valign="top" align="left">
                                <logic:iterate name="BRoadcasts" id="bean" type="com.form.broadcast.FBroadcast">
                                     <ul class="broacastList">
                                        <li> <a href="javascript:post('broadcast',anchor + ':_DETAIL:broadcastId:<%=bean.getBroadcastId()%>:user_id:<%=bean.getUser_id()%>')"><bean:write name="bean" property="title" /><span class="broatDateTime"> (<bean:write name="bean" property="createtime" />)</span></a> </li>
                                     </ul>
                                </logic:iterate>              
                            </td> 
                        </tr>
                      
                     </logic:present> 
                    </table>
          
        </div>              
    </div>
</div>    
</div>

</html:form>
