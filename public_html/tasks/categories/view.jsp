<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div class="padding-content">
<div id="mailcol">     
    <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" >
                        <ul id="ui-tabs-nav">  
                        
                        </ul>
                    </td>
                </tr>
            </table>            
    </div>   
     <div id="fragment-1">                                  
          <div  style="text-align:center">
               <div class="content-calendar"> 
                <table width="100%" border="0px" cellpadding="0" cellspacing="0">                   
                <html:form action="categories">                 
                    <html:hidden name="problem" property="type"/>
                    <input type="hidden" name="app" value="2" 
                    <tr>
                        <td>
                            <div class="toolCmd" align="right" style="padding-right:10px;">     
                                <table width="100%">
                                <tr><td align="left" nowrap width="200px" class="BGSearch">
                                    
                                    <table class="tableClassSearch" cellpadding="0" border="0" cellspacing="0" >
                                            <tr>
                                                <td><input class="inputClassSearch" type="text" onkeydown="if(event.keyCode==13){postAjax('categories','MainCate',anchor + ':_SEARCH_PAGE');return false;}" onfocus="javascript:if(this.value == '') this.value='';" onblur="javascript:if(this.value=='') this.value='';" name="titleSearch" id="titleSearch" value=""/>
                                                </td>
                                                <td class="imgClassSearch" height="18px" width="20px" onclick="postAjax('categories','MainCate',anchor + ':_SEARCH_PAGE');messageImg('MainCate');" >&nbsp;</td>
                                            </tr>
                                    </table>
                                    
                                </td>
                                <td align="right">
                                <a class="modal-button" href="categories<%=extention%>?<%=anchor%>=_PREPARED_CREATE" rel="{handler: 'iframe', size: {x: 320, y: 210}}">   
                                        <html:button property="_PREPARED_CREATE" styleClass="button"  >
                                             <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                        </html:button>
                                </a>
                                </td></tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <TR>
                           <TD class="ct-celendar" id="MainCate" valign="top">
                                 <jsp:include page="/tasks/categories/list.jsp"/>
                            </td>
                    </tr>                                    
                  </html:form>  
                    </table>
                </div>
            </div>
</div>
     </div>
 </div>


