<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<Script type="text/javascript">
var indexCalendar=0;
function getValueLink(){
        var valueIndex=new Array('_CALENDA_PRIVATE:type:1','_CALENDA_PRIVATE:type:0','_CALENDA_PRIVATE:type:2')
        post('change',anchor + ':' + valueIndex[indexCalendar]);
}
function mdotabMain(obj,option){
if(obj.className=='tab1'){
        for(i=0;i<obj.parentNode.childNodes.length;i++){
                if(obj.parentNode.childNodes[i].className=='tabactive1') obj.parentNode.childNodes[i].className='tab1';
        }
        obj.className='tabactive1';
        if(option=='idCalendarPublic'){
            indexCalendar=0;
            getObj('idCalendarPublic').style.display='block';
            document.getElementById('idCalendarDeps').style.display='none';
            document.getElementById('idCalendarPrivate').style.display='none';
        }else if(option=='idCalendarDeps'){
            indexCalendar=2;
            getObj('idCalendarDeps').style.display='block';
            document.getElementById('idCalendarPublic').style.display='none';
            document.getElementById('idCalendarPrivate').style.display='none';
        }else{
            indexCalendar=1;
            getObj('idCalendarPrivate').style.display='block';
            document.getElementById('idCalendarPublic').style.display='none';
            document.getElementById('idCalendarDeps').style.display='none';
        }
    }
}
</script>

 <%  if(request.getSession().getAttribute("06.01") !=null){ %>  
<logic:notEqual name="06.01" value="2">
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="col1-ctn1 clearfix">


                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground">
                                            <div class="textbold">
                                            <table width="100%" border="0">
                                            <tr>
                                                <td align="left">
                                                    <span class="li-title-07" />
                                                     <span class="tabactive1"  onclick="mdotabMain(this,'idCalendarPublic');" ><bean:message key="publicAgenda.form.edit.title.hearder" bundle="<%=interfaces%>"/></span> | 
                                                     <span class="tab1" onclick="mdotabMain(this,'idCalendarDeps');"><bean:message key="main.calendar.in.department" bundle="<%=interfaces%>"/></span> | 
                                                     <span class="tab1" onclick="mdotabMain(this,'idCalendarPrivate');"><bean:message key="agenda.form.edit.title.hearder" bundle="<%=interfaces%>"/></span>
                                                </td>
                                            </tr>
                                            </table>
                                            
                                            </div>
                                        </td>
                                        <td width="30px" class="repeatbackground" >
                                                <span id="linksId">
                                                <img src="<%=contextPath%>/images/newImages/links.gif" onclick="javascript:getValueLink()" title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                </span>
                                                <logic:notEqual name="06.01" value="0" >
                                                <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','06.01',anchor +':_MINIMIZE:minimize:0:menuId:06.01:app:<%=menuActiveTemp[3]%>')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                </logic:notEqual>
                                                <logic:equal name="06.01"  value="0" >
                                                <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','06.01',anchor +':_MINIMIZE:minimize:1:menuId:06.01:app:<%=menuActiveTemp[3]%>')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                </logic:equal>
                                        </td>
                                        <td width="15" class="repeatbackground"><img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('06.01').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:06.01:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
                                        <td width="7"><img src="<%=contextPath%>/images/newImages/i_30.gif" width="7" height="44" /></td>
                                    </tr>
                                </table>
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                	<div class="line-left">
                                    	<div class="line-right" >
                                        <div class="ct">
                                         <logic:equal name="06.01" value="1" >
                                                          <div id="idCalendarPublic" class="b-news" align="left">  
                                                            <logic:present name="BPublicAgendas" >
                                                            <logic:notEmpty name="BPublicAgendas" >
                                                                <div class="tblnews-calendar">
                                                                <p class="link01 last">
                                                                <logic:iterate name="BPublicAgendas" id="bean" indexId="k" type="com.form.calendar.agenda.FAgenda">
                                                                    
                                                                <div class="mainCalendarTitle">
                                                                <bean:message key="label.module.calendar.what" bundle="<%=interfaces%>"/>
                                                                : <bean:write name="bean" property="what" /></div>
                                                                <div><bean:message key="label.module.calendar.time" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="times" /> - <bean:write name="bean" property="toTimes" /> <bean:message key="label.module.calendar.timeevent" bundle="<%=interfaces%>"/> <bean:write name="bean" property="timeEvent" /></div>
                                                                <div><bean:message key="label.module.calendar.where" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="where" /></div>
                                                                    
                                                                </logic:iterate>
                                                                </p>
                                                                </div>
                                                            </logic:notEmpty>
                                                            </logic:present>
                                                    </div>
                                                    
                                                    <div id="idCalendarDeps" style="display:none">
                                                     <jsp:include page="/main/calendarDep.jsp" />
                                                    </div>
                                                    
                                                    <div id="idCalendarPrivate" style="display:none">
                                                    <jsp:include page="/main/calendaprivate.jsp" />
                                                    </div>
                                                    </logic:equal>
                                               
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
 </logic:notEqual>
 
                  <%}%>      
   
