<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<%  if(request.getSession().getAttribute("01.03") !=null){ %>
<logic:notEqual name="01.03"  value="2" >
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="preview" onmouseover="this.className='previewMoveOver'" onmouseout="this.className='preview'">
 <div class="title-01">
       <table width="100%" cellpadding="0" cellspacing="0">
                    <tr><td align="left">
                    <a href="javascript:post('change',anchor + ':_DOCS_SEND_DT_LIST:type:2:statusId:-3:app:<%=menuActiveTemp[0]%>')" class="li-title-04"><bean:message key="form.docs.type.header.send.dt" bundle="<%=interfaces%>"/></a> 
                    <span>
                    <logic:present name="BDocsDtSendRead">
                    (<bean:write name="BDocsDtSendRead" property="amount"/>) 
                    </logic:present>                     
                    </span>
                    </td>
                    <td align="center" width="10px">
                                <logic:notEqual name="01.03" value="0" >
                                <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','01.03',anchor +':_MINIMIZE:minimize:0:menuId:01.03:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                </logic:notEqual>
                                <logic:equal name="01.03"  value="0" >
                                <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','01.03',anchor +':_MINIMIZE:minimize:1:menuId:01.03:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                </logic:equal>
                    </td>
                    <td align="center" width="10px">
                <img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('01.03').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:01.03:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                    </td>
                    </tr>
        </table>

 </div>
             
  <div style="padding-left: 10px">
        <logic:present name="BDocsDtSendRead">        
        <ul class="MainUl">
        
            <li>
                <a href="javascript:post('change',anchor + ':_DOCS_SEND_DT_LIST:type:2:statusId:<%=com.inf.doc.IKeyDoc.STATUS_UNREAD%>:app:<%=menuActiveTemp[0]%>')">
                        <logic:equal name="BDocsDtSendRead" property="amountUnRead" value="0">
                        <bean:message key="status.wait" bundle="<%=interfaces%>"/> (0)
                        </logic:equal>
                    <logic:notEqual name="BDocsDtSendRead" property="amountUnRead" value="0">
                    <Strong><bean:message key="status.wait" bundle="<%=interfaces%>"/> (<bean:write name="BDocsDtSendRead" property="amountUnRead"/>)</strong>
                    </logic:notEqual>
                </a>
            </li>                    
             <logic:notEqual name="BDocsDtSendRead" property="amountRead" value="0" >
            <li>
            <a class="mainDispose" href="javascript:post('change',anchor + ':_DOCS_SEND_DT_LIST:type:2:statusId:-2:app:<%=menuActiveTemp[0]%>')">
                 <bean:message key="status.read" bundle="<%=interfaces%>"/> (<bean:write name="BDocsDtSendRead" property="amountRead"/>)
            </a>
          <ul class="MainUl">
                                <logic:present name="BDocsDtSend">
                                    <logic:iterate name="BDocsDtSend" id="bean" type="com.form.main.FMain">        
                                    <li>
                                    <a href="javascript:post('change',anchor + ':_DOCS_SEND_DT_LIST:type:2:statusId:<%=bean.getStatusId()%>:app:<%=menuActiveTemp[0]%>')"><bean:write name="bean" property="name" /> (<bean:write name="bean" property="amount" />)</a>
                                    </li>                                
                                    </logic:iterate>           
                                </logic:present>
                                </ul>
            
            </li>
        </logic:notEqual>    
        </ul>                    
        </logic:present>

  </div>

   
    
    </div>
 </logic:notEqual>
<%}%>
  
   