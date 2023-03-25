<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BMycontactSearchs" >
       <div  id="subContent" class="jcontents-slider ">
       <table cellpadding="0" cellspacing="0" width="100%"  class="fontSubMycontent" >
                        <logic:iterate name="BMycontactSearchs" id="bean" type="com.form.mycontact.FMycontact">                                  
                        <tr onmouseover="className='row1'" onmouseout="className='row2'" >
                                      <td align="left" nowrap>
                                      <div class="fontSubMycontent" > 
                                      <span style="cursor: pointer;" onclick="javascript:if(getObj('idaddress')!=null){postAjax('sendMail','idaddress',anchor +':_ADD_ADDRES:accountId:<%=bean.getId()%>')};" ><bean:write name="bean" property="fullName" /></span>      
                                      </div>
                                      </td>
                                      <td align="right" nowrap>
                                      <img src="<%=contextPath%>/images/newImages/edit.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:write name="bean" property="fullName" />','','','',1);postAjax('formMyContact','at_share',anchor + ':_OPEN_WINDOW:id:<bean:write name="bean" property="id" />');"/>
                                      <img onclick="javascript:postAjax('formMyContact','tdMycontact',anchor + ':_DELETE_PMYCONTACT:id:<%=bean.getId()%>');" src="<%=contextPath%>/images/newImages/i_17.gif"/>
                                   </td>
                                   </tr>
                        </logic:iterate>    
</table>
</div>
</logic:present>
    







