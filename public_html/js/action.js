var empDep = "";

function addMemeber(obj) {
    if (obj.checked) {
        if (empDep != '')
            empDep += ','
        empDep += obj.value;
    }
    else {
        empDep = empDep.replace(obj.value, "");
    }
    empDep = empDep.replace(",,", ",");
}

function checkX(obj) {
    var check = obj.checked;
    var objs = obj.form.disposeUser;
    var thisvalue = obj.value.split("#")[1];
    if (typeof objs != "undefined") {
        if (typeof objs.length != "undefined") {
            for (i = 0;i < objs.length;i++) {
                var valuex = objs[i].value.split("#")[1];
                if (valuex == thisvalue) {
                    objs[i].checked = false;//loai bo ra khoi mang
                    var aa = obj.form.members.value;
                    obj.form.members.value = aa.replace(objs[i].value, "");
                }
            }
            obj.checked = check;
            obj.form.members.value = addRemoveMembers(obj);
        }
        else {
            if (objs.checked) {
                obj.form.members.value = objs.value;
            }
        }
    }
}

function checkY(obj) {
    var check = obj.checked;
    var objs = obj.form.disposeUser;
    var thisvalue = obj.value.split("#")[0];
    if (typeof objs != "undefined") {
        if (typeof objs.length != "undefined") {
            for (i = 0;i < objs.length;i++) {
                var valuex = objs[i].value.split("#")[0];
                if (valuex == thisvalue) {
                    objs[i].checked = false;//loai bo ra khoi mang
                    var aa = obj.form.members.value;
                    obj.form.members.value = aa.replace(objs[i].value, "");
                }
            }
            obj.checked = check;
            obj.form.members.value = addRemoveMembers(obj);
        }
        else {
            if (objs.checked) {
                obj.form.members.value = objs.value;
            }
        }
    }

}

function addRemoveMembers(objs) {
    var members = objs.form.members.value;
    if (objs.checked) {
        if (members != '')
            members += ','
        members += objs.value;
    }
    else {
        members = members.replace(objs.value, "");
    }
    members = members.replace(",,", ",")
    return members;
}

var checkClose = null;

function orderAjax(form, id, params) {
    postAjax(form, id, params);
    message('MainMessage', 'Xin ch&#7901; trong gi&#226;y l&#225;t...')
}

function navigatorGe(formName, objResult, params) {
    postAjax(formName, objResult, params);
    message('MainProplem', 'Xin ch&#7901; trong gi&#226;y l&#225;t...');
}

function onclickTr(obj, styleClass) {
    var totalstd = obj.parentNode.cells;
    for (i = 0;i < totalstd.length;i++) {
        totalstd[i].className = totalstd[i].className == '' ? styleClass : '';
        if (i == 1) {
            totalstd[1].childNodes[0].checked = totalstd[i].className == '' ? false : true;
        }
    }
}

function removeItem(obj, form, index) {
    for (i = obj.length - 1;i >= 0;i--) {
        if (obj.options[i].selected) {
            if (index == 1) {
                for (k = form.incharge.length - 1;k > 0;k--) {
                    if (form.incharge.options[k].value == obj.options[i].value) {
                        form.incharge.remove(k);
                    }
                }
            }
            obj.remove(i);
        }
    }
}

function removeItemPrio(obj, objRemove, form, index) {

    for (i = obj.length - 1;i >= 0;i--) {
        if (obj.options[i].selected) {
            for (j = objRemove.length - 1;j >= 0;j--) {
                if (objRemove.options[j].value == obj.options[i].value) {
                    objRemove.remove(j);
                }
            }
        }
    }
}

function addUserCheckExits(id, obj) {
    check = false;
    for (i = obj.length - 1;i >= 0;i--) {
        if (obj.options[i].value == id) {
            check = true
            break;
        }
    }
    return check;
}

//function checkAll(checkname,exby) { 
//   if (checkname!=null){
//         if(checkname.length>1){
//           for (i = 0; i < checkname.length; i++){          
//             checkname[i].checked = exby.checked;    
//           }    
//         }else if(!checkname.length){       
//           checkname.checked = exby.checked;   
//         }
//    }
//}
function checkedAll(obj) {
    for (i = 0;i < obj.length;i++) {
        obj.options[i].selected = true;
    }
    return obj.length;
}

function AddUser(destination, obj) {
    if (destination != null) {
        var check = false;
        var id = obj.value;
        var valueText = obj.options[obj.selectedIndex].text;
        for (i = 0;i < destination.length;i++) {
            if (destination.options[i].value == id) {
                check = true;
                break;
            }
        }
        if (!check) {
            destination.options[destination.length] = new Option(valueText, id);
        }
    }
    else {
        return false;
    }
}

function AddAllUser(destination, obj) {
    if (obj != null && destination != null) {
        for (k = 0;k < obj.length;k++) {
            check = false;
            for (i = 0;i < destination.length;i++) {
                if (destination.options[i].value == obj.options[k].value) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                destination.options[destination.length] = new Option(obj.options[k].text, obj.options[k].value);
            }
        }
    }
}
//function AddUserPri(id,destination,valueText) {      
//    check = false ;
//     for (i = 0; i < destination.length; i++) {                              
//          if (destination.options[i].value == id){
//               check=true 
//               break;
//          }                              
//      }  
//      if (!check){ 
//          destination.options[destination.length] = new Option(valueText, id);
//      }
//}
function message(id, msg) {
    var msgbox = '<table style="border-right: #586B7A 1px solid;border-top: #DEE6E9 1px solid;border-left: #DEE6E9 1px solid;border-bottom: #586B7A 1px solid;" align="center">' + '<tr height=30px><td><IMG src="images/loading.gif"></td>' + '<td align="center"><span style="font-family:Tahoma,Arial;font-size=10px;FONT-WEIGHT: bold;">' + msg + '</span></td>' + '</tr></table>';
    try {
        getObj(id).innerHTML = msgbox;
    }
    catch (ex) {
    }
}

function messageImg(id) {
    var msgbox = '<table style="border: #586B7A 1px solid;" align="center">' + '<tr height=30px><td><IMG src="images/loading.gif"></td>' + '<td align="center"><span style="font-family:Tahoma,Arial;font-size=10px;FONT-WEIGHT: bold;">Xin ch&#7901; trong gi&#226;y l&#225;t...</span></td>' + '</tr></table>';
    try {
        getObj(id).innerHTML = msgbox;
    }
    catch (ex) {
    }
}

function trim(s) {
    return s.replace(/^\s*/, "").replace(/\s*$/, "");
}

function getObj(id) {
    var d = document;
    var obj = d.getElementById(id);
    if (obj == null) {
        try {
            obj = d.all(id);
        }
        catch (ex) {
        };
        if (obj == null) {
            try {
                obj = d.layers[id];
            }
            catch (ex) {
            };
        }
    }
    if (obj == null && id.innerHTML) {
        obj = id;
    }
    return obj;
}

function genCode(inputItem, outName) {
    var ps = inputItem.split(" ");
    var result = '';
    for (i = 0;i < ps.length;i++) {
        var item = trim(ps[i]);
        if (item != '') {
            result += item.charAt(0);
        }
    }
    var obj = getObj(outName);
    if (obj != null && trim(obj.value) == '') {
        obj.value = result.toUpperCase();
    }
}

function post(formName, params) {
    var postForm = document.forms[formName];
    var ps = params.split(":");
    if (1 == (ps.length % 2))
        return;
    var param;
    for (i = 0;i < ps.length / 2;i++) {
        param = document.createElement("input");
        param.type = "hidden";
        param.name = ps[2 * i];
        param.value = encodeURI(ps[2 * i + 1]);
        postForm.appendChild(param);
    }
    postForm.submit();
}

function hideshow(id, value) {
    if (value) {
        getObj(id).style.display = (value) ? 'block' : 'none';
    }
    else {
        var display = getObj(id).style.display;
        getObj(id).style.display = (display == '' || display == 'block') ? 'none' : 'block';
    }
}

function checkedInnerHtml() {
    var obj = getObj('addthis_dropdown15');

    if (obj != null) {
        obj.innerHTML = null;
    }
    if (obj != null && obj.style.display == 'block') {
        addthis_closewin15();
    }
}

function remove(formName, objName) {
    var postForm = document.forms[formName];
    if (postForm.elements != null)
        for (i = 0;i < postForm.elements.length;i++) {
            if (postForm.elements[i].name == objName) {
                postForm.removeChild(postForm.elements[i]);
            }
        }
}

function minimize(id, minimize) {

    var section = getObj("h3" + id);
    var section1 = getObj("subContent" + id);

    if (section.className == "titleLeftEmail contents-toggler-down") {
        section.className = "contents-toggler titleLeftEmail"
        section1.className = "myContactClose"
    }
    else {
        section.className = "titleLeftEmail contents-toggler-down"
        section1.className = "myContactOpen"
    }

}

function clear() {
    if (document.getElementById('divAlert') != null) {
        document.getElementById('divAlert').innerHTML = '';
    }
    else {
        return false;
    }
}

var detailReport = null;
var idname = null;

function showDetail(formName, posittion, params, index) {
    if ((idname == posittion + index) && (detailReport != null && detailReport.innerHTML != '')) {
        detailReport.innerHTML = '';
    }
    else {
        if (detailReport != null && detailReport.innerHTML != '')
            detailReport.innerHTML = '';
        postAjax(formName, posittion + index, anchor + params);
        messageImg(posittion + index)
    }
    idname = posittion + index;
    detailReport = getObj(posittion + index);
}

function showDetailCallPack(formName, posittion, params, index, functionCallPack) {
    if ((idname == posittion + index) && (detailReport != null && detailReport.innerHTML != '')) {
        detailReport.innerHTML = '';
    }
    else {
        if (detailReport != null && detailReport.innerHTML != '')
            detailReport.innerHTML = '';
        postAjax(formName, posittion + index, anchor + params, functionCallPack);
        messageImg(posittion + index)
    }
    idname = posittion + index;
    detailReport = getObj(posittion + index);
}

function ReplaceAll(Source, stringToFind, stringToReplace) {

    var temp = Source;

    var index = temp.indexOf(stringToFind);

    while (index !=  - 1) {
        temp = temp.replace(stringToFind, stringToReplace);

        index = temp.indexOf(stringToFind);

    }

    return temp;

}
//NKT
var last_obj;

function set_click(id) {
    var obj = getObj(id);
    if (last_obj != null)
        last_obj.style.fontWeight = "normal";
    last_obj = obj;
    obj.style.fontWeight = "bold";
}

//search NKT
var emps = ',';

function processEmps(obj) {
    var i = emps.indexOf(',' + obj.value, 0);
    if (obj.checked) {
        if (i < 0)
            emps += obj.value + ',';
    }
    else {
        if (i >= 0)
            emps = emps.substr(0, i) + emps.substr(i + obj.value.length + 1, emps.length);
    }
}

function checkAll(checkname, exby) {
    if (checkname.length > 1) {
        for (i = 0;i < checkname.length;i++) {
            checkname[i].checked = exby.checked;
            document.searchdispeople.checkEmpAllPage.checked = exby.checked;
            checkname[i].disabled = exby.name == 'checkEmpAll' && exby.checked;
            if (exby.name == 'checkEmpAllPage') {
                processEmps(checkname[i]);
            }
        }
    }
    else if (!checkname.length) {
        checkname.checked = exby.checked;
        checkname[i].disabled = exby.name == 'checkEmpAll' && exby.checked;
    }
    document.searchdispeople.checkEmpAllPage.disabled = exby.name == "checkEmpAll" && exby.checked;
}

function listReport(pageIndex, sort, sortField) {
    if (document.searchdispeople.listName.value == "") {
        alert(unescape('Nh%u1EADp%20t%EAn%20danh%20s%E1ch%20%21'));
        document.searchdispeople.listName.focus()
        return false;
    }
    openWindow('searchdispeople', anchor + ':_CREATE');
    return true;
}

function createListReport() {
    if (document.searchdispeople.checkEmpAll.length != 2) {
        if (!document.searchdispeople.checkEmpAll.checked && emps == ",") {
            alert(unescape('Ch%u1ECDn%20ng%u01B0%u1EDDi%20khuy%u1EBFt%20t%u1EADt%21'));
            document.searchdispeople.checkEmpAll.focus();
            return false;
        }
        openWindow('searchdispeople', anchor + ':_PREPARED_CREATE:emps:' + emps);
    }

    return true;
}

function deleteListReport(id) {
    if (confirm(unescape('B%u1EA1n%20c%F3%20ch%u1EAFc%20ch%u1EAFn%20x%F3a%20kh%F4ng%20%3F'))) {
        postAjax('searchdispeople', 'divSearchResult', anchor + ':_DELETE:listID:' + id);
    }
    return true;
}

function getSearchForm() {
    messageImg('divSearchResult', '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
    postAjax('searchdispeople', 'divSearchResult', anchor + ':_SEARCH_RESULT');
    hideshow('divSearchResult');
    hideshow('divSearchForm');
}

function getSearchPanel(obj) {
     $(".khung-div-search").show();
    if (trim(getObj('td' + obj.value).innerHTML) == '') {
        messageImg('td' + obj.value, '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
        postAjax('searchPanel', 'td' + obj.value, anchor + ':_PANEL:panel:' + obj.name);
    }    
    hideshow('td' + obj.value, obj.checked);
}

function getValue(obj) {
    var x = document.getElementById(obj);
    var selValue = '';
    var count = 0;
    for (var i = 0;i < x.options.length;i++) {
        if (x.options[i].selected == true) {
            if (count > 0)
                selValue = selValue + ',' + x.options[i].value;
            else 
                selValue = selValue + x.options[i].value;
            count++;
        }
    }
}

function getSearchResult() {
    var nguonHotroIds = getValue('nguonHoTroIds');
    messageImg('divSearchResult', '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
    postAjax('searchdispeople', 'divSearchResult', anchor + ':_SEARCH_RESULT:quanLyCa:0:nguonHoTroIds:' + nguonHotroIds);
    hideshow('divSearchResult');
    hideshow('divSearchForm');
}

function view_detail(id) {
    //var nguonHotroIds = getValue('nguonHoTroIds');
    //messageImg('divSearchResult', '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
    //post('dis_detail',anchor + ':_DETAIL:id:'+id);
    post('kpi_detail',anchor + ':_DETAIL:dtlId:'+id+':inputType:1:indId:56');
    //postAjax('searchdispeople', 'divSearchResult', anchor + ':_SEARCH_RESULT:quanLyCa:0:nguonHoTroIds:' + nguonHotroIds);
    //hideshow('divSearchResult');
    //hideshow('divSearchForm');
}

function getQuanLyCaResult() {
    messageImg('divSearchResult', '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
    postAjax('searchdispeople', 'divSearchResult', anchor + ':_QUANLYCA_RESULT:quanLyCa:1');
    hideshow('divSearchResult');
    hideshow('divSearchForm');
}
// Search Unit on home page
function getUnitResult() {
    messageImg('divSearchResult', '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
    postAjax('dr_unit', 'divSearchResult', anchor + ':_SEARCH_RESULT');
    hideshow('divSearchResult');
    hideshow('divSearchForm');
}

function doCheckAllInDiv(parentId) {
    var collection = document.getElementById(parentId).getElementsByTagName('INPUT');
    var flag = collection[0].checked;
    for (var x = 0;x < collection.length;x++) {
        if (collection[x].type.toUpperCase() == 'CHECKBOX')
            collection[x].checked = flag;
        collection[0].checked = flag;
    }
}

function checkHideComboBox() {
    if (document.getElementById('SearchProduct').style.display == 'block') {
        document.getElementById('SearchProduct').style.display = 'none';
    }
    if (document.getElementById('SearchProduct2').style.display == 'block') {
        document.getElementById('SearchProduct2').style.display = 'none';
    }
}

function resetEleDiv(div) {    
    var container = document.getElementById(div);
    var children = container.getElementsByTagName('select');
    for (var i = 0; i < children.length; i++) {        
        children[i].selectedIndex = -1;
    }    
    //$("#tinhId").
    
    $("#"+div).find('input[type=checkbox]:checked').removeAttr('checked');
    $("#"+div).find("input[type=text]").val('');
}

function showCongcu(obj, checked) {
    hideshow(obj, checked);
    resetEleDiv(obj);
}

function changeKyBC(value) {
    //alert(value);
    if (value == -1)
        hideshow('yearBC', false);
    else 
        hideshow('yearBC', true);
}

function hideshow(id, value) {
    if (value) {
        getObj(id).style.display = (value) ? 'block' : 'none';
    }
    else {
        var display = getObj(id).style.display;
        getObj(id).style.display = (display == '' || display == 'block') ? 'none' : 'block';
    }
}

//function getDanhGia(val,dtlId,rankId){
//    // postAjax('kpi','MainCate',anchor + ':_PRE_CRUD_RANK:dtlId:'+dtlId+':rankId:'+rankId);
//    alert(val);
//    //post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI:objId:' +objId+':indId:'+indId+':inputType:'+type+':yearReport:'+year);
//    //messageImg('right');
//}
