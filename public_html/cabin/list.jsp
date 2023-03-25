<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
function inforEdit(){
        postAjax('cabinInput','right',anchor + ':_INPUT_OK');
}
</script>
<div>
<table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<bean:define name="cabin" id="order_by_temp" property="order_by" type="java.lang.Integer"/>
<bean:define name="cabin" id="type_temp" property="type" type="java.lang.Integer"/>
<tr valign="middle">

    <TH  align="left">
    <%
      String order_by= order_by_temp+"";
      String type=type_temp+"";
 
    if(order_by.equals("0")){%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:1');" >
            <bean:message key="title.cabin.label.name" bundle="<%=interfaces%>"/>
            <img style="border:0px" src="<%=contextPath%>/images/orders/downarrow0.png" />
        </a>
    <%}else if(order_by.equals("1")){%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:0');" >
            <bean:message key="title.cabin.label.name" bundle="<%=interfaces%>"/>
            <img style="border:0px" src="<%=contextPath%>/images/orders/uparrow0.png" />
        </a>
    <%}else{%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:0');" >
            <bean:message key="title.cabin.label.name" bundle="<%=interfaces%>"/>
        </a>
    
    <%}%>
    </TH>
    <TH nowrap style="width:100px">
    <%
    if(order_by.equals("2")){%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:3');" >
            <bean:message key="title.cabin.label.capacity" bundle="<%=interfaces%>"/>
            <img style="border:0px" src="<%=contextPath%>/images/orders/downarrow0.png" />
        </a>
    <%}else if(order_by.equals("3")){%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:2');" >
            <bean:message key="title.cabin.label.capacity" bundle="<%=interfaces%>"/>
            <img style="border:0px" src="<%=contextPath%>/images/orders/uparrow0.png" />
        </a>
    <%}else{%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:2');" >
            <bean:message key="title.cabin.label.capacity" bundle="<%=interfaces%>"/>
        </a>
    <%}%>
    </TH>
    <TH nowrap style="width:250px">
        <%
    if(order_by.equals("4")){%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:5');" >
            <bean:message key="title.cabin.label.creator" bundle="<%=interfaces%>"/>:<bean:message key="title.cabin.label.time" bundle="<%=interfaces%>"/>
            <img style="border:0px" src="<%=contextPath%>/images/orders/downarrow0.png" />
        </a>
    <%}else if(order_by.equals("5")){%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:4');" >
            <bean:message key="title.cabin.label.creator" bundle="<%=interfaces%>"/>:<bean:message key="title.cabin.label.time" bundle="<%=interfaces%>"/>
            <img style="border:0px" src="<%=contextPath%>/images/orders/uparrow0.png" />
        </a>
    <%}else{%>
        <a href="javascript:postAjax('cabin','right',anchor + ':_ORDERBY:order_by:4');" >
            <bean:message key="title.cabin.label.creator" bundle="<%=interfaces%>"/>:<bean:message key="title.cabin.label.time" bundle="<%=interfaces%>"/>
        </a>
    <%}%>
    </TH>
    
    <TH nowrap align="right"  style="width:70px" >                            
    </TH>
</tr>
<logic:notEqual name="cabin" property="cabinType_id" value="0"><tr>
    <TD align="left" valign="top" style="width:300px" colspan="4">
            <a href="javascript:javascript:postTabCabin('_CABIN:type:<bean:write name="cabin" property="type"/>:cabinType_id:<bean:write name="cabin" property="back"/>');" >
            <img style="border:0px" src="<%=contextPath%>/images/backroot.png" />
            ..
            </a>
    </td>
</tr>
</logic:notEqual> 
<logic:equal name="cabin" property="cabinType_id" value="0"><tr>
    <TD align="left" valign="top" height="5px" colspan="4">
    </td>
</tr>
</logic:equal> 
<logic:notEmpty name="BCabin">
<logic:present name="BCabin">
<%int i=0;%>
<logic:iterate name="BCabin" id="bean" type="com.form.cabin.FCabin">    
  <%i++;%>

<tr class="<%=(i%2==0)?"content1":"content"%>" >
    <TD align="left" valign="top" style="width:300px">
<%if((bean.getTypeFile()==0)){
if((bean.getRealName()==null)||(bean.getRealName().equals(""))){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" >
            <img style="border:0px" src="<%=contextPath%>/images/file-broken.png" />
            <bean:write name="bean" property="name"/>
            </a>
            </td>
               <td nowrap style="width:100px">                            
                    <bean:write name="bean" property="capacity"/>
                </td>
                <td nowrap style="width:250px">                            
                    <bean:write name="bean" property="fullName"/>:<bean:write name="bean" property="timeCreate"/>
                </td>
                <%if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_SHARE)){%>
                    <%if (bean.getUserId()!=(int)me.getId()){%>
                                    <td nowrap style="width:70px;" >                   <div align="right">   
                            <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="V&#224;o th&#432; vi&#7879;n c&#225; nh&#226;n" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>');"/>
                    </td>
                    <%}%>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PUBLIC)){%>
                    <%if(me.isRole(com.inf.IRoles.rCABIN_PUBLIC)){%>
                                        <td nowrap style="width:70px;" >                   <div align="right">      
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        </div></td>
                    <%}else{%>
                                        <td nowrap style="width:70px;" >                   <div align="right"> </div></td>

                    <%}%>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PRIVATE)){%>
                                        <td nowrap style="width:70px;" >                   <div align="right">                             
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        </div></td>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT)){%>
                    <%if(me.isRole(com.inf.IRoles.rCABIN_MUTI) || me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                        <%if (bean.getDepartmentID()==(int)me.getDepartmentID()){%>
                                        <td nowrap style="width:70px;" >                   <div align="right">    
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        </div></td>
                        <%}else{%>
                                            <td nowrap style="width:70px;" >                   <div align="right"> </div></td>
                    <%}%>
                    <%}else{%>
                                        <td nowrap style="width:70px;" >                   <div align="right"> </div></td>
                    <%}%>
                <%}else{%>
                                <td nowrap style="width:70px;" >                   <div align="right"> </div></td>
                <%}%>
                
                

<%}else{
String realNameTemp=bean.getRealName().substring(bean.getRealName().length()-4,bean.getRealName().length()).toUpperCase();
    if((bean.getRealName().substring(bean.getRealName().length()-4,bean.getRealName().length()).indexOf("."))==0){
         if(realNameTemp.equals(".DOC")){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
            <img style="border:0px" src="<%=contextPath%>/images/page_white_word.png" />
            <bean:write name="bean" property="name"/>
            </a>
            </td>
        <%}else if(realNameTemp.equals(".XLS")){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                <img style="border:0px" src="<%=contextPath%>/images/page_excel.png" />
                <bean:write name="bean" property="name"/>
                </a>
                </td>
        <%}else if(realNameTemp.equals(".RAR")){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                <img style="border:0px" src="<%=contextPath%>/images/rar.png" />
                <bean:write name="bean" property="name"/>
                </a>
                </td>
        <%}else if(realNameTemp.equals(".TXT")){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                <img style="border:0px" src="<%=contextPath%>/images/txt.png" />
                <bean:write name="bean" property="name"/>
                </a>
                </td>
        <%}else if(realNameTemp.equals(".PPT")){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                <img style="border:0px" src="<%=contextPath%>/images/page_white_powerpoint.png" />
                <bean:write name="bean" property="name"/>
                </a>
        </td>
        <%}else if((realNameTemp.equals(".GIF"))||(realNameTemp.equals(".PNG"))||(realNameTemp.equals(".JPG"))){%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                <img style="border:0px" src="<%=contextPath%>/images/image.gif" />
                <bean:write name="bean" property="name"/>
                </a>
        </td>
        <%}else{%>
            <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
            <img style="border:0px" src="<%=contextPath%>/images/unknown.png" />
            <bean:write name="bean" property="name"/>
            </a>
            </td>
        <%}%>
                <td nowrap style="width:100px">                            
                    <bean:write name="bean" property="capacity"/>
                </td>
                <td nowrap style="width:250px">                            
                    <bean:write name="bean" property="fullName"/>:<bean:write name="bean" property="timeCreate"/>
                </td>
                <%if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_SHARE)){%>
                    <%if (bean.getUserId()!=(int)me.getId()){%>
                                    <td nowrap style="width:70px;" >                   <div align="right">     
                            <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="V&#224;o th&#432; vi&#7879;n c&#225; nh&#226;n" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>');"/>
                    </td>
                    <%}%>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PUBLIC)){%>
                <%if(me.isRole(com.inf.IRoles.rCABIN_PUBLIC)){%>
                                        <td nowrap style="width:70px;" >                   <div align="right">      
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        </td>
                 <%}else{%>
                                        <td nowrap style="width:70px;" >                   <div align="right"> </div></td>

                 <%}%>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PRIVATE)){%>
                                        <td nowrap style="width:70px;" >                   <div align="right">                             
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        </div></td>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT)){%>
                    <%if(me.isRole(com.inf.IRoles.rCABIN_MUTI) && me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                        <%if(bean.getDepartmentID()==(int)me.getDepartmentID()){%>
                                            <td nowrap style="width:70px;" >                   <div align="right">     
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                            </div></td>
                        <%}else{%>
                         <td nowrap style="width:70px;" >  </td>
    
                        <%}%>
                    <%}else if(me.isRole(com.inf.IRoles.rCABIN_MUTI)){%>
                                            <td nowrap style="width:70px;" ></td>
                    <%}else if(me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                                            <td nowrap style="width:70px;" >                   <div align="right">    
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                            </div></td>
                     <%}else{%>
                                            <td nowrap style="width:70px;" >                   <div align="right"> </div></td>
                    <%}%>
                <%}else{%>
                                <td nowrap style="width:70px;" >                   <div align="right">                             
                        </div></td>
                <%}%>

    <%}else{%>
                    <a href="javascript:post('cabin',anchor + ':_DOWNLOAD:id:<bean:write name="bean" property="id"/>');remove('cabin',anchor);remove('cabin','id')" onmouseover="Tip('<%=bean.getRealName()%>',SHADOW, true, SHADOWWIDTH, 7)" onmouseout="UnTip()">
                    <img style="border:0px" src="<%=contextPath%>/images/unknown.png" />
                    <bean:write name="bean" property="name"/>
                    </a>
                </td>
                <td nowrap style="width:100px">                            
                            <bean:write name="bean" property="capacity"/>
                </td>
                <td nowrap style="width:250px">                            
                    <bean:write name="bean" property="fullName"/>:<bean:write name="bean" property="timeCreate"/>
                </td>
                <td nowrap style="width:70px;" >  
                <div align="right">
                <%if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PUBLIC)){%>
                <%if(me.isRole(com.inf.IRoles.rCABIN_PUBLIC)){%>
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                 <%}%>
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PRIVATE)){%>
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT)){%>
                    <%if(me.isRole(com.inf.IRoles.rCABIN_MUTI) && me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                        <%if(bean.getDepartmentID()==(int)me.getDepartmentID()){%>
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        <%}%>
                    <%}else if(me.isRole(com.inf.IRoles.rCABIN_MUTI)){%>
                    <%}else if(me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                        <%if (bean.getDepartmentID()==(int)me.getDepartmentID()){%>
                                <img style="border:0px" src="<%=contextPath%>/images/edit-copy.png" title="<bean:message key="title.cabin.copy" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:2')">
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:0')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:post('cabin',anchor + ':_PREPARED_EDIT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())postAjax('cabin','right',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:cabinType_id:<bean:write name="bean" property="cabinType_id"/>')">
                        <%}%>
                    <%}%>
                    <%}%>
                    </div>
                </td>
<%}}%>
   <%}else{%>
   
            <a href="javascript:postTabCabin('_CABIN:type:<%=bean.getType()%>:cabinType_id:<%=bean.getId()%>');remove('cabin',anchor);" >
            <img style="border:0px" src="<%=contextPath%>/images/folder_open_document_text.png" />
            <bean:write name="bean" property="name"/>
            </a>
            </td>
            <td valign="middle" colspan="2" align="right"></td>
                <td nowrap style="width:70px;" >     
                <div align="right">
                <%if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_PUBLIC)){%>
                <%if(me.isRole(com.inf.IRoles.rCABIN_PUBLIC)){%>
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:1')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('cabinTypeSelect','idCabinType',anchor + ':_SELECT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('cabinTypeSelect',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:parentID:<bean:write name="bean" property="cabinType_id"/>:type:<bean:write name="bean" property="type"/>')">
                 <%}%>
                 
                 <%}else if(type.equals(""+com.inf.cabin.IKeyCabin.CABIN_DEPARTMENT)){%>
                 <%if(me.isRole(com.inf.IRoles.rCABIN_ONE)){%>
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:1')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('cabinTypeSelect','idCabinType',anchor + ':_SELECT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('cabinTypeSelect',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:parentID:<bean:write name="bean" property="cabinType_id"/>:type:<bean:write name="bean" property="type"/>')">
                 <%}%>
                 
                 <%}else{%>
                                <img style="border:0px" src="<%=contextPath%>/images/move_16x16.gif" title="<bean:message key="title.cabin.move" bundle="<%=interfaces%>"/>" onClick="checkedInnerHtml();addthis_open(this,'<bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>','','','',1);postAjax('cabin','at_share',anchor + ':_INPUT_STORE:id:<bean:write name="bean" property="id"/>:typeFile:1')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/edit.gif" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('cabinTypeSelect','idCabinType',anchor + ':_SELECT:id:<bean:write name="bean" property="id"/>')">
                                <img style="border:0px" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('cabinTypeSelect',anchor + ':_DELETE:id:<bean:write name="bean" property="id"/>:parentID:<bean:write name="bean" property="cabinType_id"/>:type:<bean:write name="bean" property="type"/>')">
                 <%}%>
                </div>
                </td>
<%}%>

</tr>
</logic:iterate>

</logic:present>
</logic:notEmpty>
</table>
</div>
