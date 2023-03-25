<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>  
<% int j = 0;%>
<table  class="listreview" width="100%" border="0" cellpadding="0" cellspacing="4">
<tbody>    
<logic:present name="BRmReviews">
<bean:define name="BRmReviews" id="beans" type="com.form.FBeans"/>
<logic:iterate name="BRmReviews" id="bean" indexId="i" type="com.form.require.requires.FRequire">
<%  ++j;%>
<tr>
 <td  nowrap style="padding-left:6px;"><span class="indexListReview"><%=j%> </span> </td>
<td  style="padding-left:4px;" width="97%">
          <div style="color:#949494">
                 <bean:write name="bean" property="timeCreate" />                   
          </div>
         <div>                         
                <input  type="radio" name="userReply" value="<%=bean.getCreator()%>" />  
                <strong><bean:write name="bean" property="creatorName" /></strong>:<bean:write name="bean" property="title" />
        </div>
</td>
</tr>
    
    </logic:iterate>
</logic:present>
<logic:present name="BRequire">
<tr>
      <td  nowrap style="padding-left:6px;"><span class="indexListReview"><%=++j%> </span> </td>
      <td width="97%">
             <div style="color:#949494"><bean:write name="BRequire" property="dateCreate" /></div>
            <div>
                <input  checked type="radio" name="userReply" value="<bean:write name="BRequire" property="creator" />" />       
                <bean:message key="require.rule.emp.create.caption" bundle="<%=interfaces%>"/> : <bean:write name="BRequire" property="creatorName" /> 
               
            </div>
     </td>
    </tr>
</logic:present>  
<logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_REQUIRES%>" property="comment" value="1">

<logic:notEqual name="BRequire" property="rmStatus" value="-1">
<tr>
        <td colspan="2">                      
            <strong><bean:message key="doc.review.title.caption" bundle="<%=interfaces%>"/> :</strong>                                              
            <textarea   name="title"  id="title" maxlength="200" style="width:350px;height:40px"></textarea>                         
         </td>
    </tr>
</logic:notEqual>
</logic:equal>
</tbody>
</table>