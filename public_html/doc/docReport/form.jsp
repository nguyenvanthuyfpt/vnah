<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="docssend" method="post" />
<html:form action="docReviewSend"  method="post" />
<html:form action="docsrecv" method="post" />
<html:form action="docReviewRecv"  method="post" />
<html:form action="docreport" method="post" >
<html:hidden  name="docreport" property="workflowId"  styleId="workflowId"/>
<html:hidden  name="docreport" property="fromDate"  styleId="fromDate"/>
<html:hidden  name="docreport" property="toDate"  styleId="toDate"/>
<html:hidden  name="docreport" property="totalReport"  styleId="totalReport"/>
<html:hidden  name="docreport" property="typeReport"  styleId="typeReport"/>
<div class="padding-content">
 <div id="mailcol">
        <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
             <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="2"/></jsp:include>
            <div id="fragment-1">              
            <div class="content-calendar">             
                <bean:define name="docreport" property="typeReport" id="type" type="java.lang.Integer" />
                <bean:define name="docreport" property="workflowId" id="workflowId" type="java.lang.Integer" />
                <div class="textnone-2" style="padding-bottom:6px">
                    <table class="fromRepot" style="border-collapse: collapse" width="100%" align="center"  cellpadding="0" cellspacing="0">  
                      <tr>
                        <td colspan="3" class="listFormReport" align="center" id="totals" valign="top">
                              <logic:equal name="docreport" property="workflowId" value="1">
                                    <jsp:include page="/doc/docReport/choseObjRecv.jsp" />
                                </logic:equal>
                                <logic:equal name="docreport" property="workflowId" value="2">
                                    <jsp:include page="/doc/docReport/choseObjSend.jsp" />
                                </logic:equal>
                        </td>
                      </tr>
                    </table>
                </div>
            <logic:present name="BDocs" >
            <logic:equal name="docreport" property="workflowId" value="1">
                <jsp:include page="/doc/docReport/docrecv/list.jsp" />
            </logic:equal>
            <logic:equal name="docreport" property="workflowId" value="2">
                <jsp:include page="/doc/docReport/docsend/list.jsp" />
            </logic:equal>
            </logic:present>
  </div>

  </div>
</div>
</div>
</div>
</html:form>

