<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript"> 
    var formatTemplate = 'dd/mm/yy';
    var currYear = new Date().getFullYear;			
    var sys_date=new Date();
    var pyear=sys_date.getFullYear();    
    var dateFrom = "01/10/" + (pyear-1);
    var dateTo = "30/09/" + pyear;
    
    function excuteReport(anchorName){		
        post('kpi',anchor + ':'+ anchorName);
        remove('kpi',anchor);
    }
    
    $(function() {
        // alert(dateFrom + " - " + dateTo);
        $("#eventStartDate").focus();
    })
    
    /*
    function checkDate() {
        if($("#eventStartDate").val()==''){
            alert(<bean:message key="errors.common.date.start.end.invalid" bundle="<%=interfaces%>"/>);
            return false;
        }
        
        if($("#eventEndDate").val()==''){
            alert('1');
            return false;
        }
        
        try {
            if($.datepicker.parseDate(formatTemplate, $('#eventStartDate').val()) 
              > $.datepicker.parseDate(formatTemplate, $('#eventEndDate').val())) {
              alert(<bean:message key="errors.common.date.start.less.end" bundle="<%=interfaces%>"/>);              
              return false;
            }
        }
        catch(err) {
          alert(<bean:message key="errors.common.date.start.end.invalid" bundle="<%=interfaces%>"/>);
          return false;
        }
      
    }
    */
    function checkSubmitEvent(form){
        //checkDate();
        //isRequired($("#eventStartDate"));
        if(form.eventActivity.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
</script>
<logic:present name="kpi">
<table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">
<tr valign="center" align="middle">                    
    <td>
      <span class="bt_left_Search">
        <span class="bt_right_Search">
            <span class="bt_center_Search">
                <logic:equal name="kpi" property="dtlId" value="0">
                    <html:button property="_CREATE_EVENT" styleClass="button" onclick="if(checkSubmitEvent(this.form)){ post('kpi',anchor + ':_CREATE_EVENT');}">
                        <bean:message key="action.insert" bundle="<%=interfaces%>"/>                                 
                    </html:button>
                </logic:equal>
                <logic:notEqual name="kpi" property="dtlId" value="0">
                    <html:button property="_EDIT_EVENT" styleClass="button" onclick="if(checkSubmitEvent(this.form)){ post('kpi',anchor + ':_EDIT_EVENT');}">
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>                                 
                    </html:button>
                </logic:notEqual>
            </span>
        </span>
      </span> 
    </td>
    <logic:notEqual name="kpi" property="dtlId" value="0">
    <td>
        <span class="bt_left_Search">
          <span class="bt_right_Search">
              <span class="bt_center_Search">
                  <html:button property="_CHOICE_EVENT" styleClass="button" onclick="post('kpi',anchor + ':_CHOICE_EVENT');">
                      <bean:message key="btn.select" bundle="<%=interfaces%>"/>
                  </html:button>
              </span>
          </span>
       </span>
    </td>
    </logic:notEqual>
    <td>
      <span class="bt_left_Search">
        <span class="bt_right_Search">
            <span class="bt_center_Search">
                <html:button property="_CANCEL_EVENT" styleClass="button" onclick="post('kpi',anchor + ':_CANCEL_EVENT');">
                    <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                </html:button>
            </span>
        </span>
       </span> 
    </td>
    
    <td>
        <span class="bt_left_Search">
             <span class="bt_right_Search">
                <span class="bt_center_Search">
                    <html:button property="_EXPORT" styleClass="button" onclick="javascript:excuteReport('_EXPORT_EVENT');">
                        <bean:message key="btn.export-data" bundle="<%=interfaces%>"/>
                    </html:button>
                </span>
            </span>
        </span>
    </td>
    
</tr>
</table>
</logic:present>