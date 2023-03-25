/**
 *  Cac thong bao loi
 */
var msgInvalidFormat = "Gi&#225; tr&#7883; c&#7911;a tr&#432;&#7901;ng n&#224;y kh&#244;ng h&#7907;p l&#7879;";
var msgInvalidSize = "Kích thước trư�?ng này vượt quá giới hạn cho phép!";
//var msgRequire = "Phải nhập dữ liệu cho trư?ng này!";
var msgRequire = "Phải nhập dữ liệu cho trư�?ng này!";
var msgInvalidPosValue = "Phải nhập giá trị dương!";
var msgInvalidValue = "Không chấp nhận giá trị âm! ";
/**
 *  Cac bien toan cuc
 */
var newWindow = true;
var strSeparatorArray = new Array("-", " ", "/", ".");
var minYear = 1000;
var maxYear = 3000;
var arrContent = new Array();
var arrContentLoaiAch = new Array();

var usedAddData = true;
var curRow;
var curRow2;
var key;
var shift = 0;
var err;
var OtherColor = '#F0F0F0';
var CurrentColor = '#CCE3FF';
var StartRow = 1;
var itemIndex =  - 1;
var EndRow = 100;
var cTable;
var cTable2;
var sodong = 10;
var preEl;
var orgBColor;
var orgTColor;
var out = new Object;
out['ok'] = 'false';
var arrKhoContent = new Array();
var pathFile = "";
var userSession = "";

/**
 * Cac ham hien thi thong bao
 * Tham so: Message thong bao toi nguoi su dung!
 */
function showError(msgErr) {
    alert(msgErr);
}

function showInfor(msgErr) {
    alert(msgErr);
}

function showWarning(msgErr) {
    alert(msgErr);
}

function showConfirm(msgErr) {
    return confirm(msgErr);
}

/** Ham showHideLayers()
 * An hien cac layer tren mot trang
 * Tham so:Idlayer, "", "show|hide"
 *  		show: Hien layer
 * 	 	hide: an layer
 * Su dung:
 */
function showHideLayers() {
    var i, p, v, obj, args = showHideLayers.arguments;
    for (i = 0;i < (args.length - 2);i += 3)
        if ((obj = findObj(args[i])) != null) {
            v = args[i + 2];
            if (obj.style) {
                obj = obj.style;
                v = (v == 'show') ? 'visible' : (v == 'hide') ? 'hidden' : v;
            }
            obj.visibility = v;
        }
}

/**
 * Ham: switchCheckAll()
 * Check va Uncheck tat ca cac Item check tren form.
 * Item thuc hien check, uncheck phai la check box, va ten la All
 * 	objName: Ten check Item.
 * Su dung: onClick="switchCheckAll('SelectedID')"
 */
function switchCheckAll(objName) {
    var selectObj = document.getElementsByName(objName);
    var all = document.getElementsByName("All");
    if (typeof (selectObj) == 'undefined')
        return;
    for (var i = 0;i < selectObj.length;i++) {
        if (!selectObj[i].disabled) {
            selectObj[i].checked = all[0].checked;
        }
    }
}

/**
 * Ham: isNull()
 * Kiem tra Null
 * 	pValue: Gia tri can kiem tra
 * Gia tri:
 *	true - neu gia tri do null
 *	false - neu gia tri not null
 */
function isNull(pValue) {
    return ((pValue == null) || (pValue == ""));
}

/**
 * Ham: validDateFormat()
 * Kiem tra, parse truong kieu Date theo cac format khac nhau
 * 	dateField: Item chua gia tri can kiem tra format kieu date
 * 	dateFormat: Kieu format can kiem tra
 * Gia tri:
 * 	true - neu hop le, Gia tri duoc dua ve dang dd/mm/yyyy
 * 	false - neu khong hop le
 * Su dung: onBlur="validDateFormat(this,'dd/mm/yyyy');
 */
function validDateFormat(dateField, dateFormat) {
    var dt = dateField;
    if ((dt.value == null) || (dt.value == ""))
        return true;
    var returndate;
    if (dateFormat.toUpperCase() == 'DD/MM/YYYY') {
        returndate = isDate(dt.value);
    }
    else if (dateFormat.toUpperCase() == 'MM/YYYY') {
        returndate = isMonth(dt.value);
    }
    else if (dateFormat.toUpperCase() == 'YYYY') {
        returndate = isYear(dt.value);
    }
    else {
        returndate = false;
    }

    if (returndate == false) {
        showError(msgInvalidFormat);
        err = 'error';
        dt.focus();
        return false;
    }
    dt.value = returndate;
    return returndate;
}

function validFormatSeriNew(pSeri) {
    if ((pSeri.value == null) || (pSeri.value == ""))
        return true;
    pSeri.value = trim(pSeri.value);
    pSeri.value = (pSeri.value).toUpperCase();
    if (pSeri.value.indexOf('/') < 0 && pSeri.value.length == 7) {
        pSeri.value = pSeri.value.substr(0, 4) + '/' + pSeri.value.substr(4, 3);//44AA10T
    }
    if (pSeri.value.length != len_hd_cucthuein + 2 && pSeri.value.length != len_hd_cucthuein + 2 - 1) {
        alert("Sai định dạng của ký hiệu ấn chỉ.(" + mapk + "CC/YYC)");
        err = 'error';
        pSeri.focus();
        return false;
    }
    var temp = pSeri.value.substring(2);
    var ma_pk = pSeri.value.substr(0, 2);
    if (temp.length == len_hd_cucthuein && ma_pk == mapk && regex_hd_cucthuein.test(temp))
        return true;
    else {
        alert("Sai định dạng của ký hiệu ấn chỉ.(" + mapk + "CC/YYC)");
        err = 'error';
        pSeri.focus();
        return false;
    }
}

function validFormatSeriNew2(pSeri) {
    if ((pSeri.value == null) || (pSeri.value == ""))
        return true;
    pSeri.value = trim(pSeri.value);
    pSeri.value = (pSeri.value).toUpperCase();
    if (pSeri.value.indexOf('/') < 0 && pSeri.value.length == 5) {
        pSeri.value = pSeri.value.substr(0, 2) + '/' + pSeri.value.substr(2, 3);//AA10T
    }
    var temp = pSeri.value;
    if (temp.length == len_hd_tochucin && regex_hd_tochucin.test(temp))
        return true;
    else {
        alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYC)");
        err = 'error';
        pSeri.focus();
        return false;
    }
}

/**
 *@author:toandd
 * Ham validFormatKyHieu()
 * Kiem tra dinh dang cua Ky hieu an chi
 * 	pSeri: Item chua gia tri ky hieu an chi
 * Su dung: onBlur="validFormatSeri(this)"
 */
function validFormatKyhieuNew2(pSeri) {
    if ((pSeri.value == null) || (pSeri.value == ""))
        return true;
    pSeri.value = trim(pSeri.value);
    pSeri.value = (pSeri.value).toUpperCase();
    var temp = pSeri.value;
    if (temp.length == len_hd_tochucin && regex_hd_tochucin.test(temp))
        return true;
    else {
        return false;
    }
}

//////////////////////////////////////////////////////////////////
/**
* Ham validFormatSeri()
* Kiem tra dinh dang cua Ky hieu an chi
* 	pSeri: Item chua gia tri ky hieu an chi
* Su dung: onBlur="validFormatSeri(this)"
*/
function validFormatSeri(pSeri) {
    var i;
    var strKyhieu = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    var strExt = "ABCDEFGHIJKLMNOPQRSTUVXYZ";

    if ((pSeri.value == null) || (pSeri.value == ""))
        return true;
    pSeri.value = trim(pSeri.value);

    pSeri.value = (pSeri.value).toUpperCase();
    while (pSeri.value.indexOf("-") >= 0)
        pSeri.value = pSeri.value.replace("-", "");
    pSeri.value = (pSeri.value).toUpperCase();

    if (pSeri.value.length > 8 || pSeri.value.length < 4) {
        alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC)");
        err = 'error';
        pSeri.focus();
        return false;
    }

    var vKH;
    var vNam;

    if (pSeri.value.indexOf("/") ==  - 1) {
        vKH = pSeri.value.substring(0, 2);
        vNam = pSeri.value.substring(2, pSeri.value.length);
    }
    else {
        var vPartition = (pSeri.value).split('/');
        if (vPartition.length != 2 && vPartition.length != 0) {
            alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC)");
            err = 'error';
            pSeri.focus();
            return false;
        }
        vKH = vPartition[0];
        vNam = vPartition[1];
    }
    if (vNam.length < 2) {
        alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC)");
        err = 'error';
        pSeri.focus();
        return false;
    }
    if (vNam.length < 4) {
        var sNam = vNam.substr(0, 2);
        //		var time=new Date();
        //		var sYear = ''+time.getYear();
        if (parseInt(sNam) > 80)
            vNam = "19" + vNam;
        else 
            vNam = "20" + vNam;
    }
    pSeri.value = vKH + "/" + vNam;

    if (vKH.length != 2) {
        alert("Phần ký hiệu phải là 2 chữ cái!");
        err = 'error';
        pSeri.focus();
        return false;
    }

    for (i = 0;i < vKH.length;i++) {
        if (strKyhieu.indexOf(vKH.charAt(i)) ==  - 1) {
            alert("Phần ký hiệu phải là 2 trong các chữ cái: ABCDEFGHIJKLMNOPQRSTUVXYZ!");
            err = 'error';
            pSeri.focus();
            return false;
        }
    }

    if (vNam.length == 4) {
        if (!isNumber2(vNam)) {
            alert("Phần số theo năm phải gồm 2 hoặc 4 chữ số!");
            err = 'error';
            pSeri.focus();
            return false;
        }
    }
    else if (vNam.length == 5) {
        if (!isNumber2(vNam.substring(0, 4))) {
            alert("Phần số theo năm phải gồm 2 hoặc 4 chữ số!");
            err = 'error';
            pSeri.focus();
            return false;
        }

        if (strExt.indexOf(vNam.charAt(vNam.length - 1)) ==  - 1) {
            alert("Phần ký hiệu mở rộng phải là 1 trong các chữ cái: ABCDEFGHIJKLMNOPQRSTUVXYZ!");
            err = 'error';
            pSeri.focus();
            return false;
        }

    }
    else {
        alert("Phần số theo năm phải gồm 2 hoặc 4 chữ số!");
        err = 'error';
        pSeri.focus();
        return false;
    }

    return true;
}

function checkSeriNumberTicket(pInput) {
    var i;
    var strKyhieu = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    var strExt = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    if ((pInput.value == null) || (pInput.value == ""))
        return true;
    pInput.value = trim(pInput.value);
    pInput.value = (pInput.value).toUpperCase();
    // Kiem tra do dai
    // Tach Seri va So 
    if (pInput.value.lastIndexOf("-") < 0 || pInput.value.lastIndexOf("-") >= pInput.value.length - 1) {
        alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC-Số)");
        err = 'error';
        pInput.focus();
        return false;
    }
    var pSeri = (pInput.value).substring(0, pInput.value.lastIndexOf("-"));
    //alert(pSeri);
    var pNumber = trim((pInput.value).substring(pInput.value.lastIndexOf("-") + 1));
    //alert(pNumber);
    // Xuly Seri
    pSeri = trim(pSeri);

    if (pSeri.length > 8 || pSeri.length < 4) {
        alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC-Số)");
        err = 'error';
        pInput.focus();
        return false;
    }

    var vKH;
    var vNam;

    if (pSeri.indexOf("/") ==  - 1) {
        vKH = pSeri.substring(0, 2);
        vNam = pSeri.substring(2, pSeri.length);
    }
    else {
        var vPartition = pSeri.split('/');
        if (vPartition.length != 2 && vPartition.length != 0) {
            alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC-Số)");
            err = 'error';
            pInput.focus();
            return false;
        }
        vKH = vPartition[0];
        vNam = vPartition[1];
    }
    if (vNam.length < 2) {
        alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC-Số)");
        err = 'error';
        pInput.focus();
        return false;
    }
    if (vNam.length < 4) {
        var sNam = vNam.substr(0, 2);
        //		var time=new Date();
        //		var sYear = ''+time.getYear();
        if (parseInt(sNam) > 80)
            vNam = "19" + vNam;
        else 
            vNam = "20" + vNam;
    }
    pSeri = vKH + "/" + vNam;

    if (vKH.length != 2) {
        alert("Phần ký hiệu phải là 2 chữ cái!");
        err = 'error';
        pInput.focus();
        return false;
    }

    for (i = 0;i < vKH.length;i++) {
        if (strKyhieu.indexOf(vKH.charAt(i)) ==  - 1) {
            alert("Phần ký hiệu phải là 2 trong các chữ cái: ABCDEFGHIJKLMNOPQRSTUVXYZ!");
            err = 'error';
            pInput.focus();
            return false;
        }
    }

    if (vNam.length == 4) {
        if (!isNumber2(vNam)) {
            alert("Phần số theo năm phải gồm 2 hoặc 4 chữ số!");
            err = 'error';
            pInput.focus();
            return false;
        }
    }
    else if (vNam.length == 5) {
        if (!isNumber2(vNam.substring(0, 4))) {
            alert("Phần số theo năm phải gồm 2 hoặc 4 chữ số!");
            err = 'error';
            pInput.focus();
            return false;
        }

        if (strExt.indexOf(vNam.charAt(vNam.length - 1)) ==  - 1) {
            alert("Phần ký hiệu mở rộng phải là 1 trong các chữ cái: ABCDEFGHIJKLMNOPQRSTUVXYZ!");
            err = 'error';
            pInput.focus();
            return false;
        }

    }
    else {
        alert("Phần số theo năm phải gồm 2 hoặc 4 chữ số!");
        err = 'error';
        pInput.focus();
        return false;
    }
    /*
	while(pSeri.value.indexOf("-")>=0)
		pSeri.value = pSeri.value.replace("-","");
	pSeri.value = (pSeri.value).toUpperCase();
	
	if (pSeri.value.length > 8 || pSeri.value.length < 4) {
		alert("Sai định dạng của ký hiệu ấn chỉ.(CC/YYYYC)");
		err='error';		
		pSeri.focus();
		return false;
	}
	*/
    //alert(pSeri.value);
    pInput.value = pSeri + "-" + pNumber;
}

/**
 * Ham : isNumber()
 * Kiem tra gia tri la kieu so - Number
 * 	inputVal: Gia tri can kiem tra
 * Su dung: x=isNumber(12310);
 */

function isNumber2(inputVal) {
    inputStr = inputVal.toString()
    for (var i = 0;i < inputStr.length;i++) {
        var oneChar = inputStr.charAt(i)
        if (oneChar < "0" || oneChar > "9") {
            return false
        }
    }
    return true;
}

function isNumber(inputVal) {
    inputStr = inputVal.toString()
    for (var i = 0;i < inputStr.length;i++) {
        var oneChar = inputStr.charAt(i)
        if (oneChar < "0" || oneChar > "9") {
            return false
        }
    }
    return true;
    //	return !isNaN(parseFloat(inputVal)); 
}

function isNumber1(obj, size) {

    var inputStr = obj.value;
    var oneChar;
    var isPoint = false;
    if (inputStr.charAt(0) == "." || inputStr.charAt(inputStr.length - 1) == ".")
        return false;
    for (var i = 0;i < inputStr.length;i++) {
        oneChar = inputStr.charAt(i);
        if (size == 2 && obj.name != 'donGia' && obj.name != 'donGiaBan' && obj.name != 'donGiaTToanTCuc' && obj.name != 'donGiaTToanCuc') {
            if (oneChar < "0" || oneChar > "9")
                return false;
        }
        else {
            if (oneChar == "." && !isPoint)
                isPoint = true;
            else if ((oneChar == "." && isPoint) || ((oneChar != ".") && (oneChar < "0" || oneChar > "9")))
                return false;
        }
    }
    return true;
}

/**
 Ham kiem tra cac ky tu xuat hien trong gia tri chuoi dua vao
 Khong chap nhan dau space va 1 so ky tu xac dinh
 txtIput : Tham so dua vao.(gia tri cua textbox)
 ma_kiemTra: Tham số này sẽ dùng để xác định chuỗi ký tự hợp lệ (kieu String)
 Su dung OnBlur: filterString(this,p_thamso_kiemtra)
 */
function filterString(txtInput, ma_kiemTra) {
    // Chuỗi ký tự hợp lệ mặc định
    var strInput = "";
    var strSubString = "";
    var strNotAllowChar = "";
    var strAllowChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ-_/";
    // Thêm các trư?ng hợp có chuỗi ký tự hợp lệ khác nhau
    if (ma_kiemTra == "common" || ma_kiemTra == "tenAch" || ma_kiemTra == "tenDvsd") {
        strAllowChar += " ?ÀẠẢÃĂẮẰẶẲẴÂẤẦẬẨẪ";
        //strAllowChar += " ?";
        strAllowChar += " ÉÈẸẺẼÊẾỀỆỂỄ";
        strAllowChar += " ?ÌỊỈĨ?ỲỴỶỸ";
        strAllowChar += " OÓÒỌỎÕƠỚỜỢỞỠÔ?ỒỘỔỖ";
        strAllowChar += " UÚÙỤỦŨƯỨỪỰỬỮ";
        strAllowChar += " ()+<>,.";// Cac ky tu dac biet
    }

    // ~ Thêm các trư?ng hợp
    strInput = trim(txtInput.value);
    /* Rao doan nay cua Liem lai va viet lai theo truong hop loai bo cac ky tu dac biet
	for (i=0; i < strInput.length; i++) {
		strSubString = strInput.charAt(i);
		strSubString = strSubString.toUpperCase();			
		if (strAllowChar.indexOf(strSubString) == -1)			
		{	
			if ((strNotAllowChar == "") || 
				(strNotAllowChar.indexOf(strSubString) == -1))
			{
				strNotAllowChar = strNotAllowChar + strSubString + " ";				
			}
		}
	}

	for (i=0; i < strInput.length; i++) {
		strSubString = strInput.charAt(i);
		strSubString = strSubString.toUpperCase();			
		if (isSpecialCharacter(strSubString))			
		{	
			strNotAllowChar = strNotAllowChar + strSubString + " ";				
		}
	}		
		if (strNotAllowChar != "") {
			alert("Kí tự: " 
				+ strNotAllowChar.substr(0, strNotAllowChar.length - 1) 
				+ " không hợp lệ" );
			txtInput.focus();
			return false;
		}	
	*/
    return true;
}

//Dung de kiem tra ky tu dac biet dau vao
function isSpecialCharacter(theString) {
    var myChars = ["~", "`", "!", "@", "#", "$", "%", "^", "*", "=", ";", "'", "|", "\\", "}", "{", "]", "[", "?", "/", "<", ">"];

    var flag = false;
    for (var i = 0;i < myChars.length;i++) {
        var index = theString.indexOf(myChars[i]);
        if (index !=  - 1) {
            flag = true;
            return flag;
        }
    }
    return flag;
}

/**
 * Ham : validNumber()
 * Kiem tra du lieu kieu so - Number
 * 	obj: Item chua gia tri can kiem tra
 * 	size: Kich thuoc cua gia tri
 * Su dung: onBlur="validNumber(this,10)";
 */
function validNumber(obj, size) {
    var arg = validNumber.arguments;
    if ((obj.value == null) || (obj.value == ""))
        return true;
    obj.value = trim(obj.value);
    obj.value = toNumber(obj.value);

    if (obj.value.indexOf('-') >= 0 && !obj.readOnly) {
        showError(msgInvalidValue);
        err = 'error';
        obj.focus();
        return false;
    }

    if (!isNumber1(obj, arg.length) && !obj.readOnly) {
        showError(msgInvalidFormat);
        err = 'error';
        obj.focus();
        return false;
    }

    if (obj.value.length > size && !obj.readOnly) {
        showError(msgInvalidSize);
        err = 'error';
        obj.focus();
        return false;
    }
    if ((obj.name == "tuQuyen" || obj.name == "denQuyen" || obj.name == "tuSo" || obj.name == "denSo") && parseInt(obj.value) <= 0 && !obj.readOnly) {
        showError(msgInvalidPosValue);
        err = 'error';
        obj.focus();
        return false;
    }
    /*	var rsObj = getCap(obj);
	if ((obj.name=='tuSo' || obj.name=='tuQuyen') && !isNull(rsObj.value) && (parseInt(toNumber(obj.value)) > parseInt(toNumber(rsObj.value))) ||
		(obj.name=='TU_SO' || obj.name=='DEN_SO') && !isNull(rsObj.value) && (parseInt(toNumber(obj.value)) > parseInt(toNumber(rsObj.value))) ||
		(obj.name=='denSo' || obj.name=='denQuyen') && !isNull(rsObj.value) && (parseInt(toNumber(obj.value)) < parseInt(toNumber(rsObj.value))))
	{
		if (obj.name.toUpperCase().indexOf("SO") != -1)
			alert("Từ số phải nh? hơn ?ến số!");
		else
			alert("Từ quyển phải nh? hơn ?ến quyển!");
		err='error';
		if (obj.name.toUpperCase().indexOf("DEN") != -1)
			obj.focus();
		else 
			rsObj.focus();
		return false;
	}
*/
    if (arg.length == 2 && obj.name != 'tongSoTien' && obj.name != 'soTien' && obj.name != 'donGia' && obj.name != 'donGiaBan' && obj.name != 'donGiaTToanTCuc' && obj.name != 'donGiaTToanCuc')
        obj.value = toFormatNumberDe(obj.value, 0);
    else 
        obj.value = toFormatNumberDe(obj.value, 3);// Thay doi de hien thi 3 so thap phan	
    return true;
}

function getCap(obj) {
    var e = event.srcElement;
    var eTable = e.parentElement.parentElement.parentElement;
    var row = e.parentElement.parentElement.rowIndex;
    var strName = '';
    var rsObj;
    if (obj.name == 'tuSo')
        strName = 'denSo';
    else if (obj.name == 'denSo')
        strName = 'tuSo';
    else if (obj.name == 'tuQuyen')
        strName = 'denQuyen';
    else if (obj.name == 'denQuyen')
        strName = 'tuQuyen';
    /*	else if (obj.name == 'TU_SO')
		strName = 'DEN_SO';
	else if (obj.name == 'DEN_SO')
		strName = 'TU_SO';
*/
    for (var i = 0;i < eTable.rows[row].cells.length;i++) {
        var cell = eTable.rows[row].cells[i];
        rsObj = getChild(cell, 0);
        if (rsObj != null && rsObj.type == 'text' && rsObj.name == strName)
            return rsObj;
    }
    return null;
}

/**
 * Ham : validTextNumber()
 * Kiem tra du lieu kieu so va khong them cac dau phay phan cach - Number
 * 	obj: Item chua gia tri can kiem tra
 * 	size: Kich thuoc cua gia tri
 * Su dung: onBlur="validTextNumber(this,10)";
 */
function validTextNumber(obj, size) {
    if ((obj.value == null) || (obj.value == ""))
        return true;
    obj.value = trim(obj.value);
    obj.value = toNumber(obj.value);
    if (!isNumber(obj.value) && !obj.readOnly) {
        showError(msgInvalidFormat);
        err = 'error';
        obj.focus();
        return false;
    }
    if (obj.value.length > size) {
        showError(msgInvalidSize);
        err = 'error';
        obj.focus();
        return false;
    }
    return true;
}

/**
 * Ham: validSize()
 * Kiem tra do dai cua du lieu
 * 	obj: Item chua gia tri can kiem tra
 * 	size: Kich thuoc cua gia tri
 * Su dung: onBlur="validSize(this, 10)"
 */
function validSize(obj, size) {
    if ((obj.value == null) || (obj.value == ""))
        return true;
    obj.value = trim(obj.value);
    if (obj.value.length > size) {
        showError(msgInvalidSize);
        obj.focus();
        return false;
    }
    return true;
}

/**
 * Ham : toFormatNumberDe()
 * Dinh dang 1 gia tri so theo dinh dang #,###.##.
 * 	pnumber : Gia tri so can format.
 * 	decimals : So chu so thap phan.
 * Gia tri: So da duoc format
 * Su dung: x = toFormatNumberDe(123,999, 2)
 */
function toFormatNumberDe(pnumber, decimals) {
    if (isNaN(pnumber))
        return '';
    if (pnumber == 0 || pnumber == '0')
        return '0';
    if (isNull(pnumber))
        return '';

    var snum = new String(pnumber);
    var sec = snum.split('.');

    var whole = parseFloat(sec[0]);
    var result = '';
    var temp = '';
    if (decimals != 0) {
        if (sec.length > 1) {
            var dec = new String(sec[1]);
            dec = String(parseFloat(sec[1]) / Math.pow(10, (dec.length - decimals)));
            dec = String(whole + Math.round(parseFloat(dec)) / Math.pow(10, decimals));
            var dot = dec.indexOf('.');
            if (dot ==  - 1) {
                dec += '.';
                for (i = 1;i <= decimals;i++) {
                    dec += '0';
                }
            }
            result = dec;
        }
        else {
            result = whole;
        }
    }
    else {
        result = whole;
    };
    snum = String(result);
    sec = snum.split('.');
    result = sec[0];
    if (sec[0].length > 3) {
        dec = sec[0];
        pos = dec.length % 3;
        temp = dec.substr(0, pos);
        dec = dec.substr(pos, dec.length);
        pos = (dec.length - pos) / 3;
        for (i = 0;i < pos;i++) {
            if (temp.length > 0)
                temp = temp + ',';
            temp += dec.substr(3 * i, 3);
        }
        result = temp;
    }

    if (sec.length > 1) {
        result += '.';
        temp = sec[1];
        pos = temp.length;
        result += temp;
    }
    return result;
}

/**
 * Ham: toFormatNumber()
 * Dat format cho cac truong kieu so theo dinh dang #.### (0 chu so thap phan)
 * 	pnumber: Gia tri so can format.
 * Gia tri:  Gia tri so da duoc format.
 * Su dung: x = toFormatNumber('123456');
 */
function toFormatNumber(pnumber) {
    return toFormatNumberDe(pnumber, 0)
}

/**
 * Ham: toNumber()
 * Bo format cho cac truong kieu so, chuyen tu dang #,###,### ve dang  ######
 * 	pNumber: Gia tri can convert
 * Gia tri: Gia tri cua so theo dang #####
 * Su dung: x = toNumber('123,456');
 */
function toNumber(pNumber) {
    s = new String(pNumber);
    while (s.indexOf(',') >= 0)
        s = s.replace(',', '');
    return s;
}
// Ham nay dung de Kiem tra nhap qua so dong quy dinh tren phieu nhap va phieu xuat
function kiemTraSoDongChitietNhapXuat(pSoDong, pAchId) {
    var i;
    var j = 0;
    var vAnchiId = document.getElementsByName(pAchId);

    for (i = 0;i < vAnchiId.length;i++) {

        if (!isNull(vAnchiId[i].value)) {
            j++;
        }
    }

    if (j > pSoDong) {
        window.alert("Nhập quá số dòng quy định (" + pSoDong + " dòng)!");
        return false;
    }
    else {
        return true;
    }
}

/*----NgaNTT owner-----------------------------*/

function lastYear() {
    var curDate = new Date();
    var year = curDate.getYear();
    var strDay = "31/12/" + year;
    return strDay;
}

function formatDate(dtStr, formatMark) {
    if (formatMark == "MM/YYYY") {
        dtStr = "01/" + dtStr;
    }
    else if (formatMark = "YYYY") {
        dtStr = "01/01/" + dtStr;
    }
    return dtStr;
}

function CompareDate(dtStr1, dtStr2) {
    var intDay1 = parseInt(dtStr1.substr(0, 1));
    var intMonth1 = parseInt(dtStr1.substr(3, 4));
    var intYear1 = parseInt(dtStr1.substr(6, 9));

    var intDay2 = parseInt(dtStr2.substr(0, 1));
    var intMonth2 = parseInt(dtStr2.substr(3, 4));
    var intYear2 = parseInt(dtStr2.substr(6, 9));
    var bReturn = true;
    if (intYear1 > intYear2) {
        bReturn = false;
    }
    else if (intMonth1 > intMonth2) {
        bReturn = false;
    }
    else if (intDay1 > intDay2) {
        bReturn = false;
    }
    return bReturn;
}

function CompareDate1(dtStr1, dtStr2) {
    var intDay1 = parseInt(dtStr1.substr(0, 2));
    var intMonth1 = parseInt(dtStr1.substr(3, 2));
    var intYear1 = parseInt(dtStr1.substr(6, 4));

    var intDay2 = parseInt(dtStr2.substr(0, 2));
    var intMonth2 = parseInt(dtStr2.substr(3, 2));
    var intYear2 = parseInt(dtStr2.substr(6, 4));
    var bReturn = 0;
    if (intYear1 > intYear2) {
        bReturn = 1;
    }
    else if (intYear1 < intYear2) {
        bReturn =  - 1;
    }
    else if (intMonth1 > intMonth2) {
        bReturn = 1;
    }
    else if (intMonth1 < intMonth2) {
        bReturn =  - 1;
    }
    else if (intDay1 > intDay2) {
        bReturn = 1;
    }
    else if (intDay1 < intDay2) {
        bReturn =  - 1;
    }
    else 
        bReturn = 0;

    return bReturn;
}

function CompareDate2(dateFrom, dateTo)// checks if datestr is grtr than todate 
{
    var dayFrom, dayTo;
    var monthFrom, monthTo;
    var yearFrom, yearTo;
    // For ToDate 
    var dateToPat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
    var matchArrayDateTo = dateTo.match(dateToPat);

    monthTo = matchArrayDateTo[3];
    dayTo = matchArrayDateTo[1];
    yearTo = matchArrayDateTo[4];

    // For From Date 
    var dateFromPat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
    var matchArrayDateFrom = dateFrom.match(dateFromPat);

    monthFrom = matchArrayDateFrom[3];
    dayFrom = matchArrayDateFrom[1];
    yearFrom = matchArrayDateFrom[4];

    // get fullYear 
    if (yearTo.length <= 2) {
        dtObj = new Date(yearTo, 10, 10);
        yearTo = dtObj.getFullYear();
    }
    if (yearFrom.length <= 2) {
        dtObj = new Date(yearFrom, 10, 10);
        yearFrom = dtObj.getFullYear();
    }
    if ((monthFrom.length == 1) && (monthFrom < 10))
        monthFrom = 0 + monthFrom;
    if ((monthTo.length == 1) && (monthTo < 10))
        monthTo = 0 + monthTo;

    if (dayTo.length == 1)
        dayTo = "0" + dayTo;
    if (dayFrom.length == 1)
        dayFrom = "0" + dayFrom;

    if (yearFrom > yearTo)
        return  - 1;
    else if (yearFrom < yearTo)
        return 1;

    else {
        if (monthFrom > monthTo)
            return  - 1
        else if (monthFrom < monthTo)
            return 1
        else {
            if (dayFrom > dayTo)
                return  - 1;
            else if (dayFrom < dayTo)
                return 1;
            else 
                return 0;
        }
    }

}
/* linhlt5 thuc hien ham tnh hi?u hai ngy trong js*/
function Hieuhaingay(substr1, substr2) {
    var day1 = parseInt(substr1.substr(0, 2));
    var month1 = parseInt(substr1.substr(3, 2));
    var year1 = parseInt(substr1.substr(6, 4));

    var day2 = parseInt(substr2.substr(0, 2));
    var month2 = parseInt(substr2.substr(3, 2));
    var year2 = parseInt(substr2.substr(6, 4));

    var mot = new Date(year1, month1 - 1, day1);
    var hai = new Date(year2, month2 - 1, day2);

    var offset = mot.getTime() - hai.getTime();
    var x = 1000 * 60 * 60 * 24;
    var totalDays = Math.round(offset / x);

    return totalDays;
}
/* h?t linhlt5 thuc hien ham tnh hi?u hai ngy trong js*/
/*----------~NgaNT~------------*/

/**
 * Ham: tinhSoluong()
 * Tinh so luong theo cac Item pTuso, pDen so, pTyle (DV chuan/DV tinh)
 * Dua ket qua tinh vao Item pSoluong
 * 	pTuso: Ten item chua gia tri Tu so
 * 	pDenso: Ten item chua gia tri Den so
 * 	pTyle: Ten item chua gia tri ty le DV chuan/DV tinh
 * 	pSoluong: Ten item chua gia tri So luong
 * Su dung: tinhSoluong('tuSo', 'denSo', 'tyLe', 'soLuong')
 */
function tinhSoluong(pTuso, pDenso, pTyle, pSoluong) {
    var i;
    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vTyle = document.getElementsByName(pTyle);
    var vSoluong = document.getElementsByName(pSoluong);
    var chuan, tinh;
    for (i = 0;i < vTuso.length;i++) {
        if (!isNull(vTuso[i].value)) {
            var strTL = (vTyle[i].value).split('/');
            // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
            // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
            if (strTL.length = 2) {
                chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
                tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
            }
            vSoluong[i].value = (parseInt(toNumber(vDenso[i].value)) - parseInt(toNumber(vTuso[i].value)) + 1) * tinh / chuan;
            vSoluong[i].value = toFormatNumber(vSoluong[i].value);
        }
    }
}

/**
 * Ham: tinhSoluongTheoQuyen()
 * Tinh so luong theo cac Item pTuso, pDen so, pTyle (DV chuan/DV tinh) tu thong tin ve quyen duoc nhap vao
 * Dua ket qua tinh vao Item pSoluong
 * 	pTuso: Ten item chua gia tri Tu so
 * 	pDenso: Ten item chua gia tri Den so
 * 	pTyle: Ten item chua gia tri ty le DV chuan/DV tinh
 * 	pSoluong: Ten item chua gia tri So luong
 * Su dung: tinhSoluongTheoQuyen('tuSo', 'denSo', 'tyLe', 'soLuong')
 */
function tinhSoluongTheoQuyen(pTuquyen, pDenquyen, pTuso, pDenso, pTyle, pSoluong) {

    var vTuquyen = document.getElementsByName(pTuquyen);
    var vDenquyen = document.getElementsByName(pDenquyen);
    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vTyle = document.getElementsByName(pTyle);
    var vSoluong = document.getElementsByName(pSoluong);
    var chuan, tinh;
    for (var i = 0;i < vTuquyen.length;i++) {
        if (!isNull(vTuquyen[i].value)) {
            var strTL = (vTyle[i].value).split('/');
            // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
            // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
            if (strTL.length = 2) {
                chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
                tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
            }
            vTuso[i].value = ((parseInt(toNumber(vTuquyen[i].value)) - 1)) * chuan / tinh + 1;
            vDenso[i].value = parseInt(toNumber(vDenquyen[i].value)) * chuan / tinh;
            vSoluong[i].value = parseInt(toNumber(vDenquyen[i].value)) - parseInt(toNumber(vTuquyen[i].value)) + 1;

            vTuso[i].value = toFormatNumber(vTuso[i].value);
            vDenso[i].value = toFormatNumber(vDenso[i].value);
            vSoluong[i].value = toFormatNumber(vSoluong[i].value);
        }
    }
}

/**
 * Ham: tinhSoluong_Quyen_So()
 * Cap nhat cac Item pTuso, pDenso, pSoluongp theo cac thong tin ve quyen duoc nhap vao
 * Dua ket qua tinh vao Item pSoluong
 *	pTuquyen: Ten item chua gia tri Tu quyen
 *	pDenquyen: Ten item chua gia tri Den quyen
 * 	pTuso: Ten item chua gia tri Tu so
 * 	pDenso: Ten item chua gia tri Den so
 * 	pTyle: Ten item chua gia tri ty le DV chuan/DV tinh
 * 	pSoluong: Ten item chua gia tri So luong
 * Su dung: tinhSoluong_Quyen_So('tuSo', 'denSo', 'tyLe', 'soLuong')
 */

function tinhSoluong_Quyen_So(pTuquyen, pDenquyen, pTuso, pDenso, pTyle, pSoluong) {

    var vTuquyen = document.getElementsByName(pTuquyen);
    var vDenquyen = document.getElementsByName(pDenquyen);
    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vTyle = document.getElementsByName(pTyle);
    var vChuan = document.getElementsByName("slgChuanAC");
    var vTinh = document.getElementsByName("slgTinhAC");
    var vLoaiKho = document.getElementsByName('loaiKho');

    var vSoluong = document.getElementsByName(pSoluong);
    var chuan, tinh, strTL;
    for (var i = 0;i < vTuso.length;i++) {

        if (!isNull(vTuso[i].value) && vTuso[i].value != '0') {
            if (typeof (vTyle[i]) != 'undefined') {
                strTL = (vTyle[i].value).split('/');
                // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
                // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
                if (strTL.length = 2) {
                    chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
                    tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
                }
            }
            else {
                chuan = parseInt(vChuan[i].value);
                tinh = parseInt(vTinh[i].value);
            }

            if (chuan != tinh && vTuquyen.length > 0 && typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0 && (vLoaiKho[0].value == 'H' || vLoaiKho[0].value == '')) {
                vTuquyen[i].value = toFormatNumber((parseInt(toNumber(vTuso[i].value)) - 1) / (chuan / tinh) + 1);
                vDenquyen[i].value = toFormatNumber(parseInt(toNumber(vDenso[i].value)) / (chuan / tinh));
                if (parseInt(toNumber(vDenso[i].value)) % (chuan / tinh) != 0)
                    vDenquyen[i].value = toFormatNumber(parseInt(toNumber(vDenquyen[i].value)) + 1);
                if (typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0 && vLoaiKho[0].value == 'H')
                    vSoluong[i].value = (parseInt(toNumber(vDenso[i].value)) - parseInt(toNumber(vTuso[i].value)) + 1);
                else 
                    vSoluong[i].value = (parseInt(toNumber(vDenquyen[i].value)) - parseInt(toNumber(vTuquyen[i].value)) + 1);
            }
            else 
                vSoluong[i].value = (parseInt(toNumber(vDenso[i].value)) - parseInt(toNumber(vTuso[i].value)) + 1) * tinh / chuan;
            vSoluong[i].value = toFormatNumber(vSoluong[i].value);

        }
        else if (!isNull(vTuquyen[i].value) && vTuquyen[i].value != '0') {
            if (typeof (vTyle[i]) != 'undefined') {
                strTL = (vTyle[i].value).split('/');
                // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
                // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
                if (strTL.length = 2) {
                    chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
                    tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
                }
            }
            else {
                chuan = parseInt(vChuan[i].value);
                tinh = parseInt(vTinh[i].value);
            }
            vTuso[i].value = ((parseInt(toNumber(vTuquyen[i].value)) - 1)) * chuan / tinh + 1;
            vDenso[i].value = parseInt(toNumber(vDenquyen[i].value)) * chuan / tinh;
            if (typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0 && vLoaiKho[0].value == 'H')
                vSoluong[i].value = (parseInt(toNumber(vDenso[i].value)) - parseInt(toNumber(vTuso[i].value)) + 1);
            else 
                vSoluong[i].value = parseInt(toNumber(vDenquyen[i].value)) - parseInt(toNumber(vTuquyen[i].value)) + 1;

            vTuso[i].value = toFormatNumber(vTuso[i].value);
            vDenso[i].value = toFormatNumber(vDenso[i].value);
            vSoluong[i].value = toFormatNumber(vSoluong[i].value);
        }
    }
}

/**
 * Ham: tinhSoluong2()
 * Tinh so luong theo cac Item pTuso, pDen so,
 * Dua ket qua tinh vao Item pSoluong
 * 	pTuso: Ten item chua gia tri Tu so
 * 	pDenso: Ten item chua gia tri Den so
 * 	pSoluong: Ten item chua gia tri So luong
 * Su dung: tinhSoluong('tuSo', 'denSo', 'soLuong')
 */
function tinhSoluong2(pTuso, pDenso, pSoluong) {

    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vSoluong = document.getElementsByName(pSoluong);
    for (var i = 0;i < vTuso.length;i++) {
        if (!isNull(vTuso[i].value)) {
            //kiem tra >0
            if (parseInt(toNumber(vTuso[i].value)) <= 0) {
                alert("Từ số phải lớn hơn 0");
                vTuso[i].focus();
                return false;
            }
            else if (parseInt(toNumber(vDenso[i].value)) <= 0) {
                alert("�?ến số phải lớn hơn 0");
                vDenso[i].focus();
                return false;
            }
            else {
                if (parseInt(toNumber(vDenso[i].value)) >= parseInt(toNumber(vTuso[i].value))) {
                    vSoluong[i].value = (parseInt(toNumber(vDenso[i].value)) - parseInt(toNumber(vTuso[i].value)) + 1);
                    vSoluong[i].value = toFormatNumber(vSoluong[i].value);
                }
                else {
                    vSoluong[i].value = "";
                }
            }

        }
    }

}

function tinhSolien(pSoluong, pSolien) {
    var vSoluong = document.getElementsByName(pSoluong);
    var vSolien = document.getElementsByName(pSolien);
    for (var i = 0;i < vSoluong.length;i++) {
        if (!isNull(vSolien[i].value)) {
            vSoluong[i].value = "";
        }
    }
}

function tinhSoluong3(pTuso, pDenso, pTuquyen, pDenquyen, pSoluong) {
    var e = event.srcElement;
    var row = e.parentElement.parentElement.rowIndex - 1;
    if (typeof (started) != 'undefined')
        row++;
    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vSoluong = document.getElementsByName(pSoluong);
    var vTuquyen = document.getElementsByName(pTuquyen);
    var vDenquyen = document.getElementsByName(pDenquyen);
    var vMaAC = document.getElementsByName("maAnchi");
    var idx =  - 1;
    for (var i = 0;i < arrContent.length;i++) {
        if ((arrContent[i].Ma).toUpperCase() == (vMaAC[row].value).toUpperCase()) {
            idx = i;
            break;
        }
    }
    if (idx ==  - 1)
        return;

    var tyLe = arrContent[idx].TyLeNhapXuat;
    var vCurrTyLe = tyLe.substring(0, tyLe.indexOf("|"));
    var vSoLuongChuan = vCurrTyLe.substring(0, vCurrTyLe.indexOf("/"));
    var vSoLuongTinh = vCurrTyLe.substring(vCurrTyLe.indexOf("/") + 1, vCurrTyLe.length);
    if (!isNull(vTuso[row].value) && !isNull(vDenso[row].value) && vSoLuongChuan != vSoLuongTinh) {
        if (parseInt(toNumber(vDenso[row].value)) >= parseInt(toNumber(vTuso[row].value))) {
            vTuquyen[row].value = toFormatNumber((parseInt(toNumber(vTuso[row].value)) - 1) / (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh)) + 1);
            vDenquyen[row].value = toFormatNumber(parseInt(toNumber(vDenso[row].value)) / (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh)));
            if (parseInt(parseInt(toNumber(vDenso[row].value)) % (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh))) != 0) {
                vDenquyen[row].value = toFormatNumber(parseInt(toNumber(vDenquyen[row].value)) + 1);
            }
            vSoluong[row].value = (parseInt(toNumber(vDenso[row].value)) - parseInt(toNumber(vTuso[row].value)) + 1);
            vSoluong[row].value = toFormatNumber(vSoluong[row].value);
        }
        else {
            vTuquyen[row].value = "";
            vDenquyen[row].value = "";
            vSoluong[i].value = "";
        }
    }

}

/*ThanhTC them thuoc tinh dmCQT
	*/
function convertDMCQTToString() {
    var strCvt = "new listCQT('"
    strCvt += this.ID + "','";
    strCvt += this.Ma + "','";
    strCvt += SetUnicode(this.Ten) + "','";
    strCvt += this.Ngay_TrienKhai + "'";
    strCvt += ");";
    return strCvt;
}

function listCQT(pID, pMaCQT, pTenCQT, pNgayTrienKhai) {
    this.ID = pID;
    this.Ma = pMaCQT;
    this.Ten = pTenCQT;
    this.Ngay_TrienKhai = pNgayTrienKhai
    this.convertDMCQTToString = convertDMCQTToString
}
/*ThanhTC end*/

/**
 * Ham: tinhSotien()
 * Tinh so tien theo so luong va don gia
 * Dua ket qua vao Item pSotien
 * 	pSoluong: Ten item chua gia tri So luong
 * 	pDongia: Ten item chua gia tri Don gia
 * 	pSotien: Ten item chua gia tri So tien
 * Su dung:	tinhSotien('Soluong', 'Dongia', 'Sotien')
 */
function tinhSotien(pSoluong, pDongia, pSotien) {

    var vSoluong = document.getElementsByName(pSoluong);
    var vDongia = document.getElementsByName(pDongia);
    var vSotien = document.getElementsByName(pSotien);

    for (var i = 0;i < vSoluong.length;i++) {
        if (!(isNull(vSoluong[i].value) || isNull(vDongia[i].value))) {
            vSotien[i].value = parseFloat(toNumber(vSoluong[i].value)) * parseFloat(toNumber(vDongia[i].value));
            vSotien[i].value = toFormatNumberDe(vSotien[i].value, 3);// Thay doi de hien thi 3 chu so thap phan
        }
        else 
            vSotien[i].value = "";
    }
}

/**
 * Ham: tinhTongso()
 * Tinh tong so gia tri cua mot item tren bang
 * 	pSoluong: Ten item chua gia tri can tinh tong
 * Gia tri: Tong so cac phan tu cua Item
 * Su dung:	x = tinhTongso('Soluong')
 */

function tinhTongso(pSoluong) {
    var i, vReturn;
    vReturn = 0;
    var vSoluong = document.getElementsByName(pSoluong);
    for (i = 0;i < vSoluong.length;i++) {
        if (!isNull(vSoluong[i].value)) {
            vReturn += parseFloat(toNumber(vSoluong[i].value));
        }
    }
    return vReturn;
}

/**
 * Ham: checkTusoDenso()
 * Kiem tra hop le cua Tu so, Den so voi tung loai AC va tung Ky hieu
 * 	pMa: Ten item chua gia tri Ma AC
 * 	pSeri: Ten item chua gia tri Ky hieu AC
 * 	pFrom: Ten item chua gia tri Tu so
 * 	pTo: Ten item chua gia tri Den so
 * Gia tri:
 * 	true - neu hop le
 * 	false - neu khong hop le
 * Su dung: if(checkTusoDenso('Ma', 'Seri', 'From', 'To')){}
 */
function checkTusoDenso(pMa, pSeri, pFrom, pTo) {
    var m = new Array();
    var vSeri = document.getElementsByName(pSeri);
    var vFrom = document.getElementsByName(pFrom);
    var vTo = document.getElementsByName(pTo);
    var vMa = document.getElementsByName(pMa);
    var vTdoi_Khieu = document.getElementsByName("theodoiXeri");

    var i;
    var j = 0;

    for (i = 0;i < vSeri.length;i++) {

        if (!isNull(vSeri[i].value)) {
            m[j] = new rowData(vMa[i].value, vSeri[i].value, toNumber(vFrom[i].value), toNumber(vTo[i].value), i + 1);
            j++;
        }
        else if (vTdoi_Khieu[i].value == 'Y' && !vSeri[i].readOnly && (typeof (isCheckData) == 'undefined')) {
            //nhap ky hieu
            alert("Phải nhập trư?ng ký hiệu trên dòng thứ " + (i + 1));
            vSeri[i].focus();
            err = 'error';
            return false;
        }
    }

    m.sort(sort_func);
    // Kiem tra Tuso
    for (i = 0;i < m.length;i++) {
        if (isNull(m[i].fromNum)) {
            window.alert("Phải nhập giá trị Từ số trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vFrom[i].focus();
            return false;
        }
    }
    // Kiem tra Denso
    for (i = 0;i < m.length;i++) {
        if (isNull(m[i].toNum)) {
            window.alert("Phải nhập giá trị đến số trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vTo[i].focus();
            return false;
        }
    }

    // Kiem tra Tuso <= Denso
    for (i = 0;i < m.length;i++) {
        if (parseInt(m[i].fromNum) > parseInt(m[i].toNum)) {
            window.alert("Dòng thứ " + m[i].rowNum + " sai. Từ số phải nh�? hơn đến số");
            err = 'error';
            vFrom[i].focus();
            return false;
        }
    }
    // Kiem tra Tu so, Den so khong bi giao nhau theo tung maAC + kyhieuAC	
    for (i = 1;i < m.length;i++) {
        if (m[i].seri == m[i - 1].seri)
            if (parseInt(m[i].fromNum) <= parseInt(m[i - 1].toNum)) {
                window.alert("Dòng thứ " + m[i].rowNum + " sai so với dòng thứ " + m[i - 1].rowNum + ". Từ số, đến số giao nhau!");
                err = 'error';
                vFrom[m[i].rowNum - 1].focus();
                return false;
            }
    }

    return true;
}

/**
 * Ham: checkTuquyenDenquyen()
 * Kiem tra hop le cua Tu quyen, Den quyen voi tung loai AC va tung Ky hieu
 * 	pMa: Ten item chua gia tri Ma AC
 * 	pSeri: Ten item chua gia tri Ky hieu AC
 * 	pFrom: Ten item chua gia tri Tu so
 * 	pTo: Ten item chua gia tri Den so
 * Gia tri:
 * 	true - neu hop le
 * 	false - neu khong hop le
 * Su dung: if(checkTuquyenDenquyen('Ma', 'Seri', 'From', 'To')){}
 */
function checkTuquyenDenquyen(pMa, pSeri, pFrom, pTo) {
    var m = new Array();
    var vSeri = document.getElementsByName(pSeri);
    var vFrom = document.getElementsByName(pFrom);
    var vTo = document.getElementsByName(pTo);
    var vMa = document.getElementsByName(pMa);
    var vTdoi_Khieu = document.getElementsByName("theodoiXeri");

    var i;
    var j = 0;

    for (i = 0;i < vSeri.length;i++) {

        if (!isNull(vSeri[i].value)) {
            m[j] = new rowData(vMa[i].value, vSeri[i].value, toNumber(vFrom[i].value), toNumber(vTo[i].value), i + 1);
            j++;
        }
        else if (vTdoi_Khieu[i].value == 'Y' && !vSeri[i].readOnly) {
            //nhap ky hieu
            alert("Phải nhập trư�?ng ký hiệu trên dòng thứ " + (i + 1));
            vSeri[i].focus();
            err = 'error';
            return false;
        }

    }

    m.sort(sort_func);
    // Kiem tra Tuso
    for (i = 0;i < m.length;i++) {
        if (isNull(m[i].fromNum)) {
            window.alert("Phải nhập giá trị Từ quyển trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vFrom[i].focus();
            return false;
        }
    }
    // Kiem tra Denso
    for (i = 0;i < m.length;i++) {
        if (isNull(m[i].toNum)) {
            window.alert("Phải nhập giá trị �?ến quyển trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vTo[i].focus();
            return false;
        }
    }

    // Kiem tra Tuso <= Denso
    for (i = 0;i < m.length;i++) {
        if (parseInt(m[i].fromNum) > parseInt(m[i].toNum)) {
            window.alert("Dòng thứ " + m[i].rowNum + " sai. Từ quyển phải nh�? hơn hoặc bằng �?ến quyển");
            err = 'error';
            vFrom[i].focus();
            return false;
        }
    }
    // Kiem tra Tu so, Den so khong bi giao nhau theo tung maAC + kyhieuAC	
    for (i = 1;i < m.length;i++) {
        if (m[i].seri == m[i - 1].seri)
            if (parseInt(m[i].fromNum) <= parseInt(m[i - 1].toNum)) {
                window.alert("Dòng thứ " + m[i].rowNum + " sai so với dòng thứ " + m[i - 1].rowNum + ". Từ quyển, �?ến quyển giao nhau!");
                err = 'error';
                return false;
            }
    }
    return true;
}

/**
 * Ham: check_Quyen_So()
 * Kiem tra hop le cua Tu quyen, Den quyen, Tu so, Den so voi tung loai AC va tung Ky hieu
 * 	pMa: Ten item chua gia tri Ma AC
 * 	pSeri: Ten item chua gia tri Ky hieu AC
 * 	pTuquyen: Ten item chua gia tri Tu quyen
 * 	pDenquyen: Ten item chua gia tri Den quyen
 * 	pTuso: Ten item chua gia tri Tu so
 * 	pDenso: Ten item chua gia tri Den so
 * Gia tri:
 * 	true - neu hop le
 * 	false - neu khong hop le
 * Su dung: if(check_Quyen_So('Ma', 'Seri', 'Tuquyen', 'Denquyen', 'Tuso', 'Denso')){}
 */

function check_Quyen_So(pMa, pSeri, pTuquyen, pDenquyen, pTuso, pDenso, pTyle) {
    var m = new Array();
    var vSeri = document.getElementsByName(pSeri);
    var vTuquyen = document.getElementsByName(pTuquyen);
    var vDenquyen = document.getElementsByName(pDenquyen);
    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vMa = document.getElementsByName(pMa);
    var vTyle = document.getElementsByName(pTyle);
    var vChuan = document.getElementsByName("slgChuanAC");
    var vTinh = document.getElementsByName("slgTinhAC");
    var vTdoi_Khieu = document.getElementsByName("theodoiXeri");
    var vSoluong = document.getElementsByName("soLuong");

    var i;
    var j = 0;
    var chuan;
    var tinh;

    for (i = 0;i < vSeri.length;i++) {
        if (!isNull(vSeri[i].value)) {
            m[j] = new rowDataQS(vMa[i].value, vSeri[i].value, toNumber(vTuquyen[i].value), toNumber(vDenquyen[i].value), toNumber(vTuso[i].value), toNumber(vDenso[i].value), i + 1);
            j++;
        }
        else if (vTdoi_Khieu[i].value == 'Y' && !vSeri[i].readOnly && ((typeof (vSoluong[i]) == 'undefined') || isNull(vSoluong[i].value))) {
            //nhap ky hieu
            alert("Phải nhập trư?ng ký hiệu trên dòng thứ " + (i + 1));
            vSeri[i].focus();
            err = 'error';
            return false;
        }

    }
    m.sort(sort_funcQS);

    for (i = 0;i < m.length;i++) {
        if (!isNull(vSoluong[i].value) && isNull(m[i].tuSo) && isNull(m[i].denSo) && isNull(m[i].tuQuyen) && isNull(m[i].denQuyen))
            continue;
        // Lay so luong chuan, so luong tinh
        if (typeof (vTyle[m[i].rowNum - 1]) != 'undefined') {
            var vCurrTyLe = vTyle[m[i].rowNum - 1].value.substring(0, vTyle[m[i].rowNum - 1].value.indexOf("|"));
            chuan = parseInt(vCurrTyLe.substring(0, vCurrTyLe.indexOf("/")));
            tinh = parseInt(vCurrTyLe.substring(vCurrTyLe.indexOf("/") + 1, vCurrTyLe.length));
        }
        else {
            chuan = parseInt(vChuan[m[i].rowNum - 1].value);
            tinh = parseInt(vTinh[m[i].rowNum - 1].value);
        }
        // Kiem tra tu quyen
        if (!vTuquyen[m[i].rowNum - 1].readOnly && (isNull(m[i].tuSo) || isNull(m[i].denSo)) && isNull(m[i].tuQuyen)) {
            window.alert("Phải nhập giá trị Từ quyển trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vTuquyen[m[i].rowNum - 1].focus();
            return false;
        }
        // Kiem tra den quyen
        if (!vDenquyen[m[i].rowNum - 1].readOnly && (isNull(m[i].tuSo) || isNull(m[i].denSo)) && isNull(m[i].denQuyen)) {
            window.alert("Phải nhập giá trị đến quyển trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vDenquyen[m[i].rowNum - 1].focus();
            return false;
        }
        // Kiem tra Tu quyen <= Den quyen
        if (!vTuquyen[m[i].rowNum - 1].readOnly && (parseInt(m[i].tuQuyen) > parseInt(m[i].denQuyen))) {
            window.alert("Dòng thứ " + m[i].rowNum + " sai. Từ quyển phải nh�?  hơn hoặc bằng đến quyển");
            err = 'error';
            vTuquyen[m[i].rowNum - 1].focus();
            return false;
        }
        // Kiem tra Tu quyen, Den quyen khong bi giao nhau theo tung maAC + kyhieuAC	
        if (i >= 1 && m[i].seri == m[i - 1].seri) {
            if (!vTuquyen[m[i].rowNum - 1].readOnly && (parseInt(m[i].tuQuyen) <= parseInt(m[i - 1].denQuyen))) {
                window.alert("Dòng thứ " + m[i].rowNum + " sai so với dòng thứ " + m[i - 1].rowNum + ". Từ quyển, đến quyển giao nhau!");
                err = 'error';
                vTuquyen[m[i].rowNum - 1].focus();
                return false;
            }
        }

        // Kiem tra Tuso
        if (!vTuso[m[i].rowNum - 1].readOnly && (isNull(m[i].tuQuyen) || isNull(m[i].denQuyen)) && isNull(m[i].tuSo)) {
            window.alert("Phải nhập giá trị Từ số trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vTuso[m[i].rowNum - 1].focus();
            return false;
        }
        // Kiem tra Denso
        if (!vDenso[m[i].rowNum - 1].readOnly && (isNull(m[i].tuQuyen) || isNull(m[i].denQuyen)) && isNull(m[i].denSo)) {
            window.alert("Phải nhập giá trị đến số trên dòng thứ " + m[i].rowNum);
            err = 'error';
            vDenso[m[i].rowNum - 1].focus();
            return false;
        }
        // Kiem tra Tuso <= Denso
        if (!vTuso[m[i].rowNum - 1].readOnly && (parseInt(m[i].tuSo) > parseInt(m[i].denSo))) {
            window.alert("Dòng thứ " + m[i].rowNum + " sai. Từ số phải nh�? hơn đến số");
            err = 'error';
            vTuso[m[i].rowNum - 1].focus();
            return false;
        }
        // Kiem tra Tu so, Den so khong bi giao nhau theo tung maAC + kyhieuAC	
        if (i >= 1 && m[i].seri == m[i - 1].seri) {
            if (!vTuso[m[i].rowNum - 1].readOnly && (parseInt(m[i].tuSo) <= parseInt(m[i - 1].denSo))) {
                window.alert("Dòng thứ " + m[i].rowNum + " sai so với dòng thứ " + m[i - 1].rowNum + ". Từ số, đến số giao nhau!");
                err = 'error';
                vTuso[m[i].rowNum - 1].focus();
                return false;
            }
        }
        // Kiem tra truong hop nhap Tu so, Den so con Tu quyen, Den quyen khong nhap voi xuat DVSD
        if (isNull(m[i].tuQuyen) && isNull(m[i].denQuyen) && !isNull(m[i].tuSo) && !isNull(m[i].denSo) && !vTuso[m[i].rowNum - 1].readOnly && !vTuquyen[m[i].rowNum - 1].readOnly) {
            var tuso = parseInt(toNumber(vTuso[m[i].rowNum - 1].value));
            var denso = parseInt(toNumber(vDenso[m[i].rowNum - 1].value));
            if (typeof (isDVSD) != 'undefined' && isDVSD && ((tuso - 1) % (chuan / tinh) != 0 || (denso % (chuan / tinh) != 0))) {
                window.alert("Dòng thứ " + m[i].rowNum + " có dữ liệu Từ số - �?ến số không đúng!");
                err = 'error';
                vTuso[m[i].rowNum - 1].focus();
                return false;
            }
        }
        // Kiem tra truong hop nhap ca hai cap du lieu Tuquyen Denquyen va Tuso Denso
        if (!isNull(m[i].tuQuyen) && !isNull(m[i].denQuyen) && !isNull(m[i].tuSo) && !isNull(m[i].denSo) && !vTuso[m[i].rowNum - 1].readOnly && !vTuquyen[m[i].rowNum - 1].readOnly) {

            var tuso = ((parseInt(toNumber(vTuquyen[m[i].rowNum - 1].value)) - 1)) * chuan / tinh + 1;
            var denso = parseInt(toNumber(vDenquyen[m[i].rowNum - 1].value)) * chuan / tinh;
            if ((tuso > parseInt(toNumber(vTuso[m[i].rowNum - 1].value))) || (denso < parseInt(toNumber(vDenso[m[i].rowNum - 1].value)))) {
                window.alert("Dòng thứ " + m[i].rowNum + " có dữ liệu Từ quyển - đến quyển không khớp với Từ số - �?ến số!");
                err = 'error';
                vTuso[m[i].rowNum - 1].focus();
                return false;
            }
        }
    }
    return true;
}

/**
 * Ham: checkAllRequireItem()
 * Kiem tra cac Required Item tren form
 * 	form: Form can kiem tra.
 * Gia tri:
 * 	true - neu hop le
 * 	false - neu khong hop le
 * Su dung: onSubmit="return checkAllRequireItem(this)"
 */
function checkAllRequireItem(form) {
    vReturn = true;
    obj = eval(form);
    for (i = 0;i < obj.length;i++) {
        var field_name = obj.elements[i].id;
        if (field_name.indexOf("_ck") !=  - 1) {
            if (isNull(obj.elements[i].value)) {
                vReturn = false;
                showError(msgRequire);
                obj.elements[i].focus();
                return vReturn;
            }
        }
    }
    return vReturn;
}

/**
 * Ham: checkAllRequire()
 * Kiem tra cac Required Item tren form, 0 cung coi la Null
 * 	form: Form can kiem tra.
 * Gia tri:
 * 	true - neu hop le
 * 	false - neu khong hop le
 * Su dung: onSubmit="return checkAllRequire(this)"
 */

function checkAllRequire(form) {
    vReturn = true;
    obj = eval(form);
    for (i = 0;i < obj.length;i++) {
        var field_name = obj.elements[i].id;
        if (field_name.indexOf("_ck") !=  - 1) {
            if (isNull(obj.elements[i].value) || obj.elements[i].value == "0") {
                vReturn = false;
                showError(msgRequire);
                obj.elements[i].focus();
                return vReturn;
            }
        }
    }
    return vReturn;
}

/**
 * Ham: showLovACForm()
 * Cac ham phuc vu cho show Lov AC tren form
 * 	pMaAnchi: Ten item chua gia tri ma AC
 * 	pTenAnchi: Ten item chua gia tri ten AC
 * 	pIdAnchi: Ten item chua gia tri ID cua loai AC
 * 	pTheodoiXeri: Ten item chua gia tri Mo ta loai AC co theo doi ky hieu khong
 * Su dung: onClick="showLovACForm('MaAnchi', 'TenAnchi', 'IdAnchi', 'TheodoiXeri')"
 */
function showLovACForm() {
    var result = window.showModalDialog('lovAnchi.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);

    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vACBan = document.getElementsByName("acBan");
    var hACBan = document.getElementsByName("AnchiBan");
    var vGiaBan = document.getElementsByName("donGiaBan");
    var vGiaTToanTCuc = document.getElementsByName("donGiaTToanTCuc");
    var vGiaTToanCuc = document.getElementsByName("donGiaTToanCuc");

    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[0].value = vLovVal[1];
        vT[0].value = vLovVal[2];

        var indx = parseInt(vLovVal[0]) - 1;
        //longvh: dat lai index cho trang Cap nhat ton an chi showLovACForm
        //var indx;
        //for(indx=0;indx<arrContent.length;indx++)
        // {
        //}
        vX[0].value = arrContent[indx].Theodoi;
        vI[0].value = arrContent[indx].ID;

        if (vACBan.length > 0)
            vACBan[0].checked = (arrContent[indx].AnchiBan == 'Y') ? true : false;
        if (vGiaBan.length > 0)
            vGiaBan[0].value = arrContent[indx].DonGiaBan;
        if (vGiaTToanTCuc.length > 0)
            vGiaTToanTCuc[0].value = arrContent[indx].DgTtoanTC;
        if (vGiaTToanCuc.length > 0)
            vGiaTToanCuc[0].value = arrContent[indx].DgTtoanCC;
        if (hACBan.length > 0)
            hACBan[0].value = arrContent[indx].AnchiBan;
    }
    else {
        return false;
    }
    return true;
}

/**
 *	ham phan tich du lieu tra va thanh cac mang
 **/
function parse1(things) {
    var arrTemp = things.split(";");
    var arrTemp1 = new Array();
    var i;
    for (i = 0;i < arrTemp.length;i++) {
        var temp = arrTemp[i].split("==");
        arrTemp1[i] = temp[1];
    }
    return arrTemp1;

}

/**
 *	ham hien thi ton kho an chi
 *	version 1.0.6 hiepvx sua do thay doi cho chon nhieu dong
 **/

function showLovTonKho(url) {
    var time = new Date();
    var strUrl = url + "&Time=" + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds() + ' ,' + time.getDate() + '/' + time.getMonth() + '/' + time.getYear();
    var result = window.showModalDialog(strUrl, arrContent, 'center:yes;resizable:no;status:no;dialogWidth:620px;dialogHeight:450px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var e = event.srcElement;
    while (e.tagName != 'TR') {
        e = e.parentElement;
    }
    var rowIndx = e.rowIndex - StartRow;
    var vLovVal = parse1(result);
    //	neu khong co gia tri tra ve
    if (vLovVal[0] != 'true') {

        return false;
    }
    //gia tri dong goc
    var vMaACHg = document.getElementsByName("maAnchi");
    var vTenACHg = document.getElementsByName("tenAnchi");
    var vIdACHg = document.getElementsByName("IdAnchi");
    var vTyleg = document.getElementsByName("tyLe");
    var vX = document.getElementsByName("theodoiXeri");
    var x;
    if (typeof (vX[rowIndx]) != 'undefined')
        x = vX[rowIndx].value;
    else 
        x = "";
    var MaACH = vMaACHg[rowIndx].value;
    var TenACH = vTenACHg[rowIndx].value;
    var IdACH = vIdACHg[rowIndx].value;
    var Tyle;
    //neu ty le co dinh nghia
    if (typeof (vTyleg[rowIndx]) != 'undefined')
        Tyle = vTyleg[rowIndx].value;
    if ((!isNull(vLovVal[1])))//truong hop theo doi ki hieu
    {

        var arrKyhieu = vLovVal[1].split(",");
        var arrTuquyen = vLovVal[2].split(",");
        var arrDenquyen = vLovVal[3].split(",");
        var arrTuso = vLovVal[4].split(",");
        var arrDenso = vLovVal[5].split(",");
        var arrSoluong = vLovVal[6].split(",");

        var i;
        for (i = 0;i < arrSoluong.length;i++) {
            //lay toan bo cac dong
            var vMaACH = document.getElementsByName("maAnchi");
            var vTenACH = document.getElementsByName("tenAnchi");
            var vIdACH = document.getElementsByName("IdAnchi");
            var vTyle = document.getElementsByName("tyLe");
            var vKyhieu = document.getElementsByName("kyHieu");
            var vTuquyen = document.getElementsByName("tuQuyen");
            var vDenquyen = document.getElementsByName("denQuyen");
            var vTuso = document.getElementsByName("tuSo");
            var vDenso = document.getElementsByName("denSo");
            var vSoluong = document.getElementsByName("soLuong");
            var vLoaiKho = document.getElementsByName('loaiKho');
            /**gan gia tri goc tranh mot so truong hop bang chi tiep ko tu dong copy gia tri*/
            if (isNull(vMaACH[rowIndx].value) && isNull(vTenACH[rowIndx].value) && isNull(vIdACH[rowIndx].value)) {
                vMaACH[rowIndx].value = MaACH;
                vTenACH[rowIndx].value = TenACH;
                vIdACH[rowIndx].value = IdACH;
                //neu truong Tyle co dinh nghia
                if (typeof (vTyle[rowIndx]) != 'undefined')
                    vTyle[rowIndx].value = Tyle;

            }
            //kiem tra cac o co dinh nghia va gan data??
            if (typeof (vX[rowIndx]) != 'undefined')
                vX[rowIndx].value = x;
            if (typeof (vKyhieu[rowIndx]) != 'undefined')
                vKyhieu[rowIndx].value = trim(arrKyhieu[i]);
            if (typeof (vTuquyen[rowIndx]) != 'undefined')
                vTuquyen[rowIndx].value = trim(arrTuquyen[i]);
            if (typeof (vDenquyen[rowIndx]) != 'undefined')
                vDenquyen[rowIndx].value = trim(arrDenquyen[i]);
            if (typeof (vTuso[rowIndx]) != 'undefined')
                vTuso[rowIndx].value = trim(arrTuso[i]);
            if (typeof (vDenso[rowIndx]) != 'undefined')
                vDenso[rowIndx].value = trim(arrDenso[i]);
            if (typeof (vSoluong[rowIndx]) != 'undefined') {
                if (typeof (vTuquyen[rowIndx]) != 'undefined' && !isNull(vTuquyen[rowIndx].value) && vLoaiKho.length == 0) {
                    vSoluong[rowIndx].value = parseInt(toNumber(vDenquyen[rowIndx].value)) - parseInt(toNumber(vTuquyen[rowIndx].value)) + 1;
                    vSoluong[rowIndx].value = toFormatNumber(vSoluong[rowIndx].value);
                }
                else 
                    vSoluong[rowIndx].value = trim(arrSoluong[i]);
            }
            //neu chon nhieu dong thi them dong
            if (arrSoluong.length != 1 && (i + 1) != arrSoluong.length) {
                addrow();
                rowIndx++;
            }
            //tinh dong
            tinh_row();
        }
    }
    //lydh2 them hien tri cot so luong khi an chi theo doi so luong
    else if ((!isNull(vLovVal[6])) && (isNull(vLovVal[1])))// truong hop theo doi so luong
    {
        var arrSoluong = vLovVal[6].split(",");
        var i;
        for (i = 0;i < arrSoluong.length;i++) {
            //lay toan bo cac dong
            var vMaACH = document.getElementsByName("maAnchi");
            var vTenACH = document.getElementsByName("tenAnchi");
            var vIdACH = document.getElementsByName("IdAnchi");
            var vTyle = document.getElementsByName("tyLe");
            var vKyhieu = document.getElementsByName("kyHieu");
            var vTuquyen = document.getElementsByName("tuQuyen");
            var vDenquyen = document.getElementsByName("denQuyen");
            var vTuso = document.getElementsByName("tuSo");
            var vDenso = document.getElementsByName("denSo");
            var vSoluong = document.getElementsByName("soLuong");
            var vLoaiKho = document.getElementsByName('loaiKho');
            /**gan gia tri goc tranh mot so truong hop bang chi tiep ko tu dong copy gia tri*/
            if (isNull(vMaACH[rowIndx].value) && isNull(vTenACH[rowIndx].value) && isNull(vIdACH[rowIndx].value)) {
                vMaACH[rowIndx].value = MaACH;
                vTenACH[rowIndx].value = TenACH;
                vIdACH[rowIndx].value = IdACH;
                //neu truong Tyle co dinh nghia
                if (typeof (vTyle[rowIndx]) != 'undefined')
                    vTyle[rowIndx].value = Tyle;

            }
            //kiem tra cac o co dinh nghia va gan data??	    
            if (typeof (vSoluong[rowIndx]) != 'undefined') {
                vSoluong[rowIndx].value = trim(arrSoluong[i]);
            }
            //neu chon nhieu dong thi them dong
            if (arrSoluong.length != 1 && (i + 1) != arrSoluong.length) {
                addrow();
                rowIndx++;
            }
            //tinh dong
            tinh_row();

        }
    }
    //lydh2 end
    else {
        return false;
    }
    return true;
}

function tinh_row() {
    tinh();
}
/*
//ham chuan cua 1.0.5
function showLovTonKho(url) { 
	var time=new Date();
	var strUrl=url+"&Time="+time.getHours()+':'+time.getMinutes()+':'+time.getSeconds()+' ,'+time.getDate()+'/'+time.getMonth()+'/'+time.getYear();
    var result=window.showModalDialog(strUrl,arrContent, 'center:yes;resizable:no;status:no;dialogWidth:620px;dialogHeight:450px'); 
    if (typeof(result)=='undefined' ) return -1;

	var e = event.srcElement;
	while (e.tagName != 'TR') {
		e = e.parentElement;
	}
	var rowIndx = e.rowIndex - StartRow;

	var vLovVal = parse(result); 
	var vKyhieu = document.getElementsByName("kyHieu");
	var vTuquyen = document.getElementsByName("tuQuyen");
	var vDenquyen = document.getElementsByName("denQuyen");
	var vTuso = document.getElementsByName("tuSo");
	var vDenso = document.getElementsByName("denSo");
	var vSoluong = document.getElementsByName("soLuong");
	var vLoaiKho = document.getElementsByName('loaiKho');

  	if ((vLovVal['ok']=='true') && (!isNull(vLovVal[1]))){
   	 	if (typeof(vKyhieu[rowIndx])!='undefined') vKyhieu[rowIndx].value = trim(vLovVal[0]); 
   	    if (typeof(vTuquyen[rowIndx])!='undefined') vTuquyen[rowIndx].value = trim(vLovVal[1]);
   	    if (typeof(vDenquyen[rowIndx])!='undefined') vDenquyen[rowIndx].value = trim(vLovVal[2]);
   	    if (typeof(vTuso[rowIndx])!='undefined') vTuso[rowIndx].value = trim(vLovVal[3]);
   	    if (typeof(vDenso[rowIndx])!='undefined') vDenso[rowIndx].value = trim(vLovVal[4]);
   	    if (typeof(vSoluong[rowIndx])!='undefined') {
   	    	if (typeof(vTuquyen[rowIndx])!='undefined' && !isNull(vTuquyen[rowIndx].value) && vLoaiKho.length==0) {
				vSoluong[rowIndx].value = parseInt(toNumber(vDenquyen[rowIndx].value)) - parseInt(toNumber(vTuquyen[rowIndx].value)) + 1 ;			
				vSoluong[rowIndx].value = toFormatNumber(vSoluong[rowIndx].value);
   	    	} 
   	    	else	
   	    		vSoluong[rowIndx].value = trim(vLovVal[5]);
   	    }
	}else{
		return false;
	}
	return true;
} 
*/

/**
 * Ham: showLovACTracuu()
 * Cac ham phuc vu cho show Lov AC tren form
 * Su dung: onClick="showLovACTracuu()"
 */
function showLovACTracuu() {
    var result = window.showModalDialog('lovAnchi.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        document.forms[0].maAnchi.value = vLovVal[1];
        document.forms[0].tenAnchi.value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        document.forms[0].IdAnchi.value = arrContent[indx].ID;
    }
    else {
        return false;
    }
    return true;
}
//Longvh
/**
* Ham: showLovACTracuuTCT()
* Cac ham phuc vu cho show Lov AC tren form
* Su dung: onClick="showLovACTracuu()"
*/
function showLovACTracuuTCT() {
    var result = window.showModalDialog('lovAch_TBPH.html', arrAchContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    //  var result=window.showModalDialog('lovAnchi.html',arrAchContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px'); 
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        document.forms[0].kyHieu.value = vLovVal[1];
        document.forms[0].ten.value = vLovVal[2];
    }
    else {
        return false;
    }
    return true;
}
var StartRow1 = 0;

/**
 * Ham: showLovAC()
 * Show Lov AC trong khi nhap nhieu dong du lieu
 * Gia tri:
 * 	-1: Neu khong chon
 * 	xx: Hien thi LOV tai phan tu thu xx cua cac Item.
 * Su dung: setEDItem(showLovAC(), 'Kyhieu','Tuso','Denso','Soluong')
 */
function showLovAC() {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }

    if (StartRow1 > 0)
        rowIndx = e1.rowIndex - StartRow1;
    else 
        rowIndx = e1.rowIndex - StartRow;
    var result;
    if (typeof (bBC20) != 'undefined' && bBC20)
        result = window.showModalDialog('lovAnchiDG.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    else 
        result = window.showModalDialog('lovAnchi.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vDvt = document.getElementsByName("DVT");
    var vMaDvt = document.getElementsByName("maDVT");
    var vDgTC = document.getElementsByName("donGiaTC");
    var vDgC = document.getElementsByName("donGia");
    var vSlgTinh = document.getElementsByName("slgTinhAC");
    var vSlgChuan = document.getElementsByName("slgChuanAC");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vTyLe = document.getElementsByName("tyLe");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vHtTheodoi = document.getElementsByName("hinhthucTheodoi");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[rowIndx].value = vLovVal[1];
        vT[rowIndx].value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        vX[rowIndx].value = arrContent[indx].Theodoi;
        vI[rowIndx].value = arrContent[indx].ID;

        if (typeof (vDvt[rowIndx]) != 'undefined' && vDvt[rowIndx].type == 'text')
            vDvt[rowIndx].value = arrContent[indx].DVTNhapXuat;
        if (typeof (vMaDvt[rowIndx]) != 'undefined')
            vMaDvt[rowIndx].value = arrContent[indx].DonGiaBan;
        if (typeof (vDgTC[rowIndx]) != 'undefined')
            vDgTC[rowIndx].value = arrContent[indx].DgTtoanTC;
        if (typeof (vDgC[rowIndx]) != 'undefined')
            vDgC[rowIndx].value = arrContent[indx].DonGiaIn;
        if (typeof (vSlgTinh[rowIndx]) != 'undefined')
            vSlgTinh[rowIndx].value = arrContent[indx].DVTLuuKho;
        if (typeof (vSlgChuan[rowIndx]) != 'undefined')
            vSlgChuan[rowIndx].value = arrContent[indx].TyLeLuuKho;
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[indx].SoKyHieu;
        if (typeof (vTyLe[rowIndx]) != 'undefined')
            vTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHtTheodoi[rowIndx]) != 'undefined')
            vHtTheodoi[rowIndx].value = arrContent[indx].HinhthucTheodoi;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[indx].HinhThucIn;
        //alert(arrContent[indx].HinhThucIn);
        //alert(arrContent[indx].LoaiAnchi);
        return rowIndx;
    }
    else {
        return  - 1;
    }
    return rowIndx;
}

function showLovACKoDonGia() {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }

    if (StartRow1 > 0)
        rowIndx = e1.rowIndex - StartRow1;
    else 
        rowIndx = e1.rowIndex - StartRow;
    var result;
    if (typeof (bBC20) != 'undefined' && bBC20)
        result = window.showModalDialog('lovAnchiDG.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    else 
    //result=window.showModalDialog('lovAnchi.html',arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px'); 
        result = window.showModalDialog('lovAch_TBPH.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vDvt = document.getElementsByName("DVT");
    var vMaDvt = document.getElementsByName("maDVT");
    var vDgTC = document.getElementsByName("donGiaTC");
    //var vDgC  = document.getElementsByName("donGia");
    var vSlgTinh = document.getElementsByName("slgTinhAC");
    var vSlgChuan = document.getElementsByName("slgChuanAC");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vTyLe = document.getElementsByName("tyLe");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vHtTheodoi = document.getElementsByName("hinhthucTheodoi");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[rowIndx].value = vLovVal[1];
        vT[rowIndx].value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        vX[rowIndx].value = arrContent[indx].Theodoi;
        vI[rowIndx].value = arrContent[indx].ID;

        if (typeof (vDvt[rowIndx]) != 'undefined' && vDvt[rowIndx].type == 'text')
            vDvt[rowIndx].value = arrContent[indx].DVTNhapXuat;
        if (typeof (vMaDvt[rowIndx]) != 'undefined')
            vMaDvt[rowIndx].value = arrContent[indx].DonGiaBan;
        if (typeof (vDgTC[rowIndx]) != 'undefined')
            vDgTC[rowIndx].value = arrContent[indx].DgTtoanTC;
        //if (typeof(vDgC[rowIndx])!='undefined') vDgC[rowIndx].value = arrContent[indx].DonGiaIn;
        if (typeof (vSlgTinh[rowIndx]) != 'undefined')
            vSlgTinh[rowIndx].value = arrContent[indx].DVTLuuKho;
        if (typeof (vSlgChuan[rowIndx]) != 'undefined')
            vSlgChuan[rowIndx].value = arrContent[indx].TyLeLuuKho;
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[indx].SoKyHieu;
        if (typeof (vTyLe[rowIndx]) != 'undefined')
            vTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHtTheodoi[rowIndx]) != 'undefined')
            vHtTheodoi[rowIndx].value = arrContent[indx].HinhthucTheodoi;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[indx].HinhThucIn;
        return rowIndx;
    }
    else {
        return  - 1;
    }
    return rowIndx;
}

/**
 * Ham: showLovLoaiAC()
 * Show LovLoaiAC trong khi nhap nhieu dong du lieu
 * Gia tri:
 * 	-1: Neu khong chon
 * 	xx: Hien thi LOV tai phan tu thu xx cua cac Item.
 */
function showLovLoaiAC() {
    var rowIndx = 0;
    var result;

    result = window.showModalDialog('lovAnchi.html', arrContentLoaiAch, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);
    var vM = document.getElementsByName("maAnchi_XM");
    var vT = document.getElementsByName("tenAnchi_XM");

    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[rowIndx].value = vLovVal[1];
        vT[rowIndx].value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        return rowIndx;
    }
    else {
        return  - 1;
    }

    return rowIndx;
}

/**
 * Ham: showLovDMKho()
 * Show Lov Kho trong khi nhap nhieu dong du lieu
 * Gia tri:
 * 	-1: Neu khong chon
 * 	xx: Hien thi LOV tai phan tu thu xx cua cac Item.
 * Su dung: setEDItem(showLovAC(), 'Kyhieu','Tuso','Denso','Soluong')
 */
function showLovDMKho() {

    var result = window.showModalDialog('lovDMKho.html', arrKhoContent, 'center:yes;resizable:no;status:no;dialogWidth:460px;dialogHeight:370px');

    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parse(result);
    var vDMKho = document.getElementById("dsKho");
    var vMa = document.getElementById("maKho");

    if (vLovVal['ok'] == 'true') {
        vDMKho.value = vLovVal['check' + 2];
        vMa.value = vLovVal['check' + 1];
    }
}

/**
 *	Ham: setNull();
 *	Thiet lap cac gia tri rong khi ma An chi rong
 *	indx: index cua MaAC
 */
function setNull(indx) {
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    //var vDvt   = document.getElementsByName("DVT");
    var vTenDvt = document.getElementsByName("tenDVT");
    var vMaDvt = document.getElementsByName("maDVT");
    var vDgTC = document.getElementsByName("donGiaTC");
    var vDgC = document.getElementsByName("donGia");
    var vSlgTinh = document.getElementsByName("slgTinhAC");
    var vSlgChuan = document.getElementsByName("slgChuanAC");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vTyLe = document.getElementsByName("tyLe");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vHtTheodoi = document.getElementsByName("hinhthucTheodoi");
    if (typeof (vM[indx]) != 'undefined')
        vM[indx].value = "";
    if (typeof (vT[indx]) != 'undefined')
        vT[indx].value = "";
    if (typeof (vI[indx]) != 'undefined')
        vI[indx].value = "";
    if (typeof (vX[indx]) != 'undefined')
        vX[indx].value = "";
    if (typeof (vTyLe[indx]) != 'undefined')
        vTyLe[indx].value = "";
    //if (typeof(vDvt[indx])!='undefined') vDvt[indx].value = "";
    if (typeof (vTenDvt[indx]) != 'undefined')
        vTenDvt[indx].value = "";
    if (typeof (vMaDvt[indx]) != 'undefined')
        vMaDvt[indx].value = "";
    if (typeof (vDgTC[indx]) != 'undefined')
        vDgTC[indx].value = "0";
    if (typeof (vDgC[indx]) != 'undefined')
        vDgC[indx].value = "";
    if (typeof (vSlgTinh[indx]) != 'undefined')
        vSlgTinh[indx].value = "";
    if (typeof (vSlgChuan[indx]) != 'undefined')
        vSlgChuan[indx].value = "";
    if (typeof (vSoKyHieu[indx]) != 'undefined')
        vSoKyHieu[indx].value = "";
    if (typeof (vLoaiAnchi[indx]) != 'undefined')
        vLoaiAnchi[indx].value = "";
    if (typeof (vHtTheodoi[indx]) != 'undefined')
        vHtTheodoi[indx].value = "";
}

/**
 * Ham: getTenAnchi();
 * Hien thi ten va thiet lap cac Item khac khi NSD go MaAC khi nhap nhieu dong.
 * Neu go khong dung thi hien thi LOV
 * 	pMaAC: Ten cua Item chua gia tri Ma AC
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * 	pTuso: Ten cua Item chua gia tri Tu so
 * 	pDenso: Ten cua Item chua gia tri Den so
 * 	pSoluong: Ten cua Item chua gia tri So luong
 * Su dung: onBlur="getTenAnchi('MaAC', 'Kyhieu','Tuso','Denso','Soluong')"
 */
function getTenAnchi(pMaAC, pKyhieu, pTuso, pDenso, pSoluong) {
    var idx;
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vDvt = document.getElementsByName("DVT");
    var vMaDvt = document.getElementsByName("maDVT");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");
    var vDgTC = document.getElementsByName("donGiaTC");
    var vDgC = document.getElementsByName("donGia");
    var vSlgTinh = document.getElementsByName("slgTinhAC");
    var vSlgChuan = document.getElementsByName("slgChuanAC");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vTyLe = document.getElementsByName("tyLe");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vKyhieu = document.getElementsByName(pKyhieu);
    var vTuso = document.getElementsByName(pTuso);
    var vDenso = document.getElementsByName(pDenso);
    var vSoluong = document.getElementsByName(pSoluong);
    var vHtTheodoi = document.getElementsByName("hinhthucTheodoi");
    var pClear = 0;
    var e1 = pMaAC;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    var rowIndx;
    if (StartRow1 > 0)
        rowIndx = e1.rowIndex - StartRow1;
    else 
        rowIndx = e1.rowIndex - StartRow;

    if (isNull(pMaAC.value)) {
        setNull(rowIndx);
        return true;
    }
    idx =  - 1;
    var i;
    var dem = 0;
    for (i = 0;i < arrContent.length;i++) {
        if ((arrContent[i].Ma).toUpperCase() == (pMaAC.value).toUpperCase()) {
            idx = i;
            dem = dem + 1;
            //	break;
        }
    }

    if (!isNull(vKyhieu[rowIndx].value) && !isNull(vTuso[rowIndx].value) && !isNull(vDenso[rowIndx].value) || !isNull(vSoluong[rowIndx].value))
        pClear = 1;

    if (idx !=  - 1 && dem == 1) {
        vM[rowIndx].value = arrContent[idx].Ma;
        vT[rowIndx].value = arrContent[idx].Ten;
        vX[rowIndx].value = arrContent[idx].Theodoi;
        vI[rowIndx].value = arrContent[idx].ID;

        if (typeof (vTyLe[rowIndx]) != 'undefined')
            vTyLe[rowIndx].value = arrContent[idx].TyLeNhapXuat;
        if (typeof (vDvt[rowIndx]) != 'undefined')
            vDvt[rowIndx].value = arrContent[idx].DVTNhapXuat;
        if (typeof (vMaDvt[rowIndx]) != 'undefined')
            vMaDvt[rowIndx].value = arrContent[idx].DonGiaBan;
        if (typeof (vDgTC[rowIndx]) != 'undefined')
            vDgTC[rowIndx].value = arrContent[idx].DgTtoanTC;
        if (typeof (vDgC[rowIndx]) != 'undefined' && (isNull(vDgC[rowIndx].value) || vDgC[rowIndx].readOnly))
            vDgC[rowIndx].value = arrContent[idx].DgTtoanCC;
        if (typeof (vSlgTinh[rowIndx]) != 'undefined')
            vSlgTinh[rowIndx].value = arrContent[idx].DVTLuuKho;
        if (typeof (vSlgChuan[rowIndx]) != 'undefined')
            vSlgChuan[rowIndx].value = arrContent[idx].TyLeLuuKho;
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[idx].SoKyHieu;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[idx].LoaiAnchi;
        if (typeof (vHtTheodoi[rowIndx]) != 'undefined')
            vHtTheodoi[rowIndx].value = arrContent[idx].HinhthucTheodoi;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[idx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[idx].HinhThucIn;

        if (typeof (vSlgChuan[rowIndx]) != 'undefined')
            setEDItem1(rowIndx, pKyhieu, pTuso, pDenso, pSoluong, pClear);
        else 
            setEDItem(rowIndx, pKyhieu, pTuso, pDenso, pSoluong, pClear);
    }
    else {
        if (typeof (vSlgChuan[rowIndx]) != 'undefined')
            setEDItem1(showLovAC(), pKyhieu, pTuso, pDenso, pSoluong, pClear);
        else 
            setEDItem(showLovAC(), pKyhieu, pTuso, pDenso, pSoluong, pClear);
    }
    idx =  - 1;
    for (i = 0;i < arrContent.length;i++) {
        if ((arrContent[i].Ma).toUpperCase() == (pMaAC.value).toUpperCase()) {
            idx = i;
            break;
        }
    }
    if (idx ==  - 1) {
        setNull(rowIndx);
        return false;
    }
    return true;
}
/**
 * Ham: getTenAnchi();
 * Hien thi ten va thiet lap cac Item khac khi NSD go MaAC khi nhap nhieu dong.
 * Neu go khong dung thi hien thi LOV
 * 	pMaAC: Ten cua Item chua gia tri Ma AC
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * 	pTuso: Ten cua Item chua gia tri Tu so
 * 	pDenso: Ten cua Item chua gia tri Den so
 * 	pSoluong: Ten cua Item chua gia tri So luong
 * Su dung: onBlur="getTenAnchi('MaAC', 'Kyhieu','Tuso','Denso','Soluong')"
 */
// Longvh them soLuong, soLien
function getSoLuongLien(pThayDoi, pNull) {
    var idx;

    var vNull = document.getElementsByName(pNull);

    var pClear = 0;
    var e1 = pThayDoi;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    var rowIndx;
    if (StartRow1 > 0)
        rowIndx = e1.rowIndex - StartRow1;
    else 
        rowIndx = e1.rowIndex - StartRow;
    //alert(rowIndx);
    //alert(vNull[rowIndx]);
    vNull[rowIndx].value = '';

    return true;
}

function getTenLoaiAch(pMaAch, formindex, layerindex) {
    var vT = document.getElementsByName("tenAnchi_XM");
    for (i = 0;i < arrContentLoaiAch.length;i++) {
        if (pMaAch == "" || pMaAch == null) {
            vT[formindex].value = "";
            return;
        }
        if (arrContentLoaiAch[i].Ma == pMaAch) {
            vT[formindex].value = arrContentLoaiAch[i].Ten;
            return;
        }
    }
    showLovLoaiAC();
    return;
}

function getTenAch_Tracuu(pMaAch, formindex, layerindex) {
    var vT = document.getElementsByName("tenAnchi");
    for (i = 0;i < arrContent.length;i++) {
        if (pMaAch == "" || pMaAch == null) {
            vT[formindex].value = "";
            return;
        }
        if (arrContent[i].Ma == pMaAch) {
            vT[formindex].value = arrContent[i].Ten;
            return;
        }
    }
    showLovACTracuu();
    return;
}

/**
 * Ham: setEDItem()
 * Dat cac thuoc tinh cua cac item Ky hieu, Tu so, Den so, So luong
 * tuy thuoc vao gia tri cua theodoiXeri
 * 	pInd: Thu tu phan tu cac Item
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * 	pTuso: Ten cua Item chua gia tri Tu so
 * 	pDenso: Ten cua Item chua gia tri Den so
 * 	pSoluong: Ten cua Item chua gia tri So luong
 * Su dung: xem ham showLovAC();
 */
function setEDItem(pInd, pKyhieu, pTuso, pDenso, pSoluong, pClear) {
    if (pInd >= 0) {

        var vX = document.getElementsByName("theodoiXeri");
        var vHT = document.getElementsByName("hinhthucTheodoi");
        var vTuso = document.getElementsByName(pTuso);
        var vDenso = document.getElementsByName(pDenso);
        var vKyhieu = document.getElementsByName(pKyhieu);
        var vSoluong = document.getElementsByName(pSoluong);
        if (vX[pInd].value == 'Y') {
            disableTextField(pClear, vSoluong[pInd]);
            enableTextField(vKyhieu[pInd], vTuso[pInd], vDenso[pInd]);
            if (vHT.length > 0 && vHT[pInd].value == '01') {
                enableTextField(vSoluong[pInd]);
                disableTextField(pClear, vKyhieu[pInd], vTuso[pInd], vDenso[pInd]);
            }
        }
        else {
            enableTextField(vSoluong[pInd]);
            disableTextField(pClear, vKyhieu[pInd], vTuso[pInd], vDenso[pInd]);
        }
    }

}

function setEDItem1(pInd, pKyhieu, pTuso, pDenso, pSoluong, pClear) {
    if (pInd >= 0) {

        var vX = document.getElementsByName("theodoiXeri");
        var vChuan = document.getElementsByName("slgChuanAC");
        var vTinh = document.getElementsByName("slgTinhAC");
        var vTuquyen = document.getElementsByName("tuQuyen");
        var vDenquyen = document.getElementsByName("denQuyen");
        var vTuso = document.getElementsByName(pTuso);
        var vDenso = document.getElementsByName(pDenso);
        var vKyhieu = document.getElementsByName(pKyhieu);
        var vSoluong = document.getElementsByName(pSoluong);

        if (vX[pInd].value == 'Y') {
            disableTextField(pClear, vSoluong[pInd]);
            enableTextField(vKyhieu[pInd]);
            if (vChuan[pInd].value == vTinh[pInd].value) {
                disableTextField(pClear, vTuquyen[pInd], vDenquyen[pInd]);
                enableTextField(vTuso[pInd], vDenso[pInd]);
            }
            else {
                //	    		disableTextField(pClear,vTuso[pInd], vDenso[pInd]);		    	    	
                enableTextField(vTuquyen[pInd], vDenquyen[pInd], vTuso[pInd], vDenso[pInd]);
            }
        }
        else {
            enableTextField(vSoluong[pInd]);
            disableTextField(pClear, vKyhieu[pInd], vTuso[pInd], vDenso[pInd], vTuquyen[pInd], vDenquyen[pInd]);
        }

        var vTable = document.getElementById("kq_table");
        if (typeof (vTable) != 'undefined' && vTable.rows[0].cells.length > 10) {
            vCell = vTable.rows[pInd].cells[10].firstChild;
            if (typeof (vCell) != 'undefined' && vCell.type == 'text' && isNull(vCell.value))
            //vCell.value='0'; bo lenh nay di de gia tri mac dinh cua don gia <>0
                vCell.value = '';
        }
    }

}

function disableTextField() {
    if (document.all || document.getElementById) {
        args = disableTextField.arguments;
        for (i = 1;i < args.length;i++) {
            //    		args[i].disabled = true;
            args[i].readOnly = true;
            if (args[0] != 1)
                args[i].value = "";
        }
    }
}

function enableTextField() {
    if (document.all || document.getElementById) {
        args = enableTextField.arguments;
        for (i = 0;i < args.length;i++) {
            args[i].readOnly = false;
        }
    }
}

/**
 * Ham: showLovACNhapXuat()
 * Show Lov AC trong khi nhap nhieu dong du lieu
 * Gia tri:
 * 	-1: Neu khong chon
 * 	xx: Hien thi LOV tai phan tu thu xx cua cac Item.
 * Su dung: setEDItem(showLovACNhapXuat(pLoaiPhieu), 'Kyhieu','Tuso','Denso','Soluong')
 */
function showLovACNhapXuat(pLoaiPhieu) {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    rowIndx = e1.rowIndex - StartRow;

    var result = window.showModalDialog('lovAnchi.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    //var result = window.showModalDialog('lovAch_TBPH.html',arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px'); 
    if (typeof (result) == 'undefined')
        return  - 1;
    if (result == null) {
        return  - 1;
    }

    var vLovVal = parse(result);
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vDonGia = document.getElementsByName("donGia");
    var vTenDVT = document.getElementsByName("tenDVT");
    var vTyLe = document.getElementsByName("tyLe");
    var vTmpTyLe = document.getElementsByName("tmpTyLe");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");

    var i;
    var maACH;
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[rowIndx].value = vLovVal[1];
        maACH = vM[rowIndx].value;
        vT[rowIndx].value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        vX[rowIndx].value = arrContent[indx].Theodoi;
        vI[rowIndx].value = arrContent[indx].ID;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[indx].HinhThucIn;
        // Neu la phieu xuat de huy hoac nhap thu hoi
        if ((parseInt(pLoaiPhieu) == 10) || (parseInt(pLoaiPhieu) == 5) || (parseInt(pLoaiPhieu) == 3) || (parseInt(pLoaiPhieu) == 4)) {
            // Thi dung dvt luu kho
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[indx].DVTLuuKho;
            vTyLe[rowIndx].value = arrContent[indx].TyLeLuuKho;
            if (typeof (vTmpTyLe[rowIndx]) != 'undefined')
                vTmpTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        }
        // Con lai
        else {
            // Dung dvt nhap xuat
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[indx].DVTNhapXuat;
            vTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        }

        // Neu co cot don gia
        if (vDonGia.length > 0) {
            // Lay don gia ban
            vDonGia[rowIndx].value = arrContent[indx].DonGiaBan;
        }
        //	   	    alert(vDonGia[rowIndx].value+' '+arrContent[indx].DonGiaBan);
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[indx].SoKyHieu;

        return rowIndx;
    }
    else {
        return  - 1;
    }

    return rowIndx;
}
//
function showLovACNhapXuat_pxhuy(pLoaiPhieu) {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    rowIndx = e1.rowIndex - StartRow;

    var result = window.showModalDialog('LovAchNhapXuat.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');
    //var result = window.showModalDialog('lovAch_TBPH.html',arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px'); 
    if (typeof (result) == 'undefined')
        return  - 1;
    if (result == null) {
        return  - 1;
    }

    var vLovVal = parse(result);
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vDonGia = document.getElementsByName("donGia");
    var vTenDVT = document.getElementsByName("tenDVT");
    var vTyLe = document.getElementsByName("tyLe");
    var vTmpTyLe = document.getElementsByName("tmpTyLe");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");

    var i;
    var maACH;
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[rowIndx].value = vLovVal[1];
        maACH = vM[rowIndx].value;
        vT[rowIndx].value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        vX[rowIndx].value = arrContent[indx].Theodoi;
        vI[rowIndx].value = arrContent[indx].ID;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[indx].HinhThucIn;
        // Neu la phieu xuat de huy hoac nhap thu hoi
        if ((parseInt(pLoaiPhieu) == 10) || (parseInt(pLoaiPhieu) == 5) || (parseInt(pLoaiPhieu) == 3) || (parseInt(pLoaiPhieu) == 4)) {
            // Thi dung dvt luu kho
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[indx].DVTLuuKho;
            vTyLe[rowIndx].value = arrContent[indx].TyLeLuuKho;
            if (typeof (vTmpTyLe[rowIndx]) != 'undefined')
                vTmpTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        }
        // Con lai
        else {
            // Dung dvt nhap xuat
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[indx].DVTNhapXuat;
            vTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        }

        // Neu co cot don gia
        if (vDonGia.length > 0) {
            // Lay don gia ban
            vDonGia[rowIndx].value = arrContent[indx].DonGiaBan;
        }
        //	   	    alert(vDonGia[rowIndx].value+' '+arrContent[indx].DonGiaBan);
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[indx].SoKyHieu;

        return rowIndx;
    }
    else {
        return  - 1;
    }

    return rowIndx;
}
/// linhlt5 thuc hien tao rieng mot lov an chi moi
function showLovAC_HOADON(pLoaiPhieu) {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    rowIndx = e1.rowIndex - StartRow;

    var result = window.showModalDialog('lovAch_TBPH.html', arrContent, 'center:yes;resizable:no;status:no;dialogWidth:506px;dialogHeight:550px');

    if (typeof (result) == 'undefined')
        return  - 1;
    if (result == null) {
        return  - 1;
    }

    var vLovVal = parse(result);
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vDonGia = document.getElementsByName("donGia");
    var vTenDVT = document.getElementsByName("tenDVT");
    var vTyLe = document.getElementsByName("tyLe");
    var vTmpTyLe = document.getElementsByName("tmpTyLe");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");
    var i;
    var maACH;
    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        vM[rowIndx].value = vLovVal[1];
        maACH = vM[rowIndx].value;
        vT[rowIndx].value = vLovVal[2];
        var indx = parseInt(vLovVal[0]) - 1;
        vX[rowIndx].value = arrContent[indx].Theodoi;
        vI[rowIndx].value = arrContent[indx].ID;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[indx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[indx].HinhThucIn;
        // Neu la phieu xuat de huy hoac nhap thu hoi
        if ((parseInt(pLoaiPhieu) == 10) || (parseInt(pLoaiPhieu) == 5) || (parseInt(pLoaiPhieu) == 3) || (parseInt(pLoaiPhieu) == 4)) {
            // Thi dung dvt luu kho
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[indx].DVTLuuKho;
            vTyLe[rowIndx].value = arrContent[indx].TyLeLuuKho;
            if (typeof (vTmpTyLe[rowIndx]) != 'undefined')
                vTmpTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        }
        // Con lai
        else {
            // Dung dvt nhap xuat
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[indx].DVTNhapXuat;
            vTyLe[rowIndx].value = arrContent[indx].TyLeNhapXuat;
        }

        // Neu co cot don gia
        //if (vDonGia.length > 0)
        //{
        // Lay don gia ban
        //	vDonGia[rowIndx].value = arrContent[indx].DonGiaBan;
        //} 
        //	   	    alert(vDonGia[rowIndx].value+' '+arrContent[indx].DonGiaBan);
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[indx].SoKyHieu;

        return rowIndx;
    }
    else {
        return  - 1;
    }

    return rowIndx;
}
//het  linhlt5
function showLovDVSD(indexForm) {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    rowIndx = e1.rowIndex - StartRow;
    var result = window.showModalDialog('lovDVSD.html', arrDVSDContent, 'center:yes;resizable:no;status:no;dialogWidth:456px;dialogHeight:550px');

    if (typeof (result) == 'undefined')
        return  - 1;
    if (result == null) {
        return  - 1;
    }
    //alert(parseLov(result));
    var vLovVal = parseLov(result);

    if ((vLovVal['ok'] == 'true') && (!isNull(vLovVal[1]))) {
        if (indexForm == "4") {
            document.forms[4].MaDVSD.value = vLovVal[1];
            document.forms[4].TenDVSD.value = vLovVal[1] + "-" + vLovVal[2];
            document.forms[4].NguoiTraAnChi.value = vLovVal[2];
        }
        else if (indexForm == "3") {
            document.forms[3].MaDVSD.value = vLovVal[1];
            document.forms[3].TenDVSD.value = vLovVal[1] + "-" + vLovVal[2];
        }
        //Sonpm2 them trong chuc nang nhap an chi
        else if (indexForm == "7") {
            document.forms[7].MaDVSD.value = vLovVal[1];
            document.forms[7].TenDVSD.value = vLovVal[1] + "-" + vLovVal[2];
            document.forms[7].NguoiTraAnChi.value = vLovVal[2];
        }
        // Sonpm2
        else if (indexForm == "2") {
            document.forms[2].MaDVSD.value = vLovVal[1];
            document.forms[2].TenDVSD.value = vLovVal[1] + "-" + vLovVal[2];
        }
        else if (indexForm == "1") {
            document.forms[1].MaDVSD.value = vLovVal[1];
            document.forms[1].TenDVSD.value = vLovVal[1] + "-" + vLovVal[2];
            //alert(document.forms[1].MaDVSD.value);
        }
        else if (indexForm == "0") {
            document.forms[0].MaDVSD.value = vLovVal[1];
            document.forms[0].TenDVSD.value = vLovVal[1] + "-" + vLovVal[2];
        }
    }
    else {
        return  - 1;
    }
}

/**
 * Ham: getTenAnchiCtuTToan();
 * Hien thi ten va thiet lap cac Item khac khi NSD go MaAC khi nhap nhieu dong.
 * Neu go khong dung thi hien thi LOV
 * 	pMaAC: Ten cua Item chua gia tri Ma AC
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * Su dung: onBlur="getTenAnchi('MaAC', 'Kyhieu')"
 */
function getTenAnchiCTuTToan(pMaAC) {
    var e1 = pMaAC;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    var rowIndx = e1.rowIndex - StartRow;

    if (isNull(pMaAC.value)) {
        setNull(rowIndx);
        return true;
    }

    var idx;
    var i;
    idx =  - 1;
    for (i = 0;i < arrContent.length;i++) {
        if ((arrContent[i].Ma).toUpperCase() == (pMaAC.value).toUpperCase()) {
            idx = i;
            break;
        }
    }
    if (idx !=  - 1) {

        var vM = document.getElementsByName("maAnchi");
        var vT = document.getElementsByName("tenAnchi");
        var vI = document.getElementsByName("IdAnchi");
        var vX = document.getElementsByName("theodoiXeri");
        var vSoKyHieu = document.getElementsByName("soKyHieu");

        vM[rowIndx].value = arrContent[idx].Ma;
        vT[rowIndx].value = arrContent[idx].Ten;
        vX[rowIndx].value = arrContent[idx].Theodoi;
        vI[rowIndx].value = arrContent[idx].ID;
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[idx].SoKyHieu;

    }
    else {
        showLovAC();
        idx =  - 1;
        for (i = 0;i < arrContent.length;i++) {
            if ((arrContent[i].Ma).toUpperCase() == (pMaAC.value).toUpperCase()) {
                idx = i;
                break;
            }
        }
        if (idx ==  - 1) {
            setNull(rowIndx);
            return true;
        }
    }
}

/**
 * Ham: setEDItemXuat()
 * Dat cac thuoc tinh cua cac item Ky hieu, Tu so, Den so, So luong  trong truong hop
 * xuat an chi
 * tuy thuoc vao gia tri cua theodoiXeri
 * 	pInd: Thu tu phan tu cac Item
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * 	pTuso: Ten cua Item chua gia tri Tu so
 * 	pDenso: Ten cua Item chua gia tri Den so
 * 	pSoluong: Ten cua Item chua gia tri So luong
 * Su dung: xem ham showLovAC();
 */
function setEDItemNhapXuat(pInd, pKyhieu, pTuso, pDenso, pSoluong, pClear, pLoaiPhieu) {
    if (pInd >= 0) {
        var vX = document.getElementsByName("theodoiXeri");
        var vTuso = document.getElementsByName(pTuso);
        var vDenso = document.getElementsByName(pDenso);
        var vTuquyen = document.getElementsByName("tuQuyen");
        var vDenquyen = document.getElementsByName("denQuyen");
        var vTyLe = document.getElementsByName("tyLe");
        var vKyhieu = document.getElementsByName(pKyhieu);
        var vSoluong = document.getElementsByName(pSoluong);
        var vLov = document.getElementsByName("acLov");
        var vLoaiKho = document.getElementsByName("LoaiKho");
        if (vLov[pInd].disabled)
            return;

        // Neu theo doi ky hieu    	
        if (vX[pInd].value == 'Y') {
            // Enable ky hieu va so luong
            enableTextField(vKyhieu[pInd]);
            // Neu la phieu nhap
            if ((parseInt(pLoaiPhieu) < 6)) {
                // Khong enable truong so luong
                disableTextField(pClear, vSoluong[pInd]);
                // Neu la nhap thu hoi, Sonpm2 edded: Nhap DC Cap duoi(loaiPhieu = 3)
                if ((parseInt(pLoaiPhieu) == 5) || (parseInt(pLoaiPhieu) == 3) || //DC
(parseInt(pLoaiPhieu) == 4)) {
                    // thi enable tuso, denso
                    enableTextField(vTuso[pInd], vDenso[pInd]);
                    if ((vTuquyen.length > 0) && (vDenquyen.length > 0)) {
                        disableTextField(pClear, vTuquyen[pInd], vDenquyen[pInd]);
                    }
                }
                // Con neu la cac loai phieu nhap khac
                else {
                    // thi disable tuso, denso
                    disableTextField(pClear, vTuso[pInd], vDenso[pInd]);
                    if ((vTuquyen.length > 0) && (vDenquyen.length > 0)) {
                        enableTextField(vTuquyen[pInd], vDenquyen[pInd]);
                    }
                }
            }
            // Neu la phieu xuat
            else {
                enableTextField(vSoluong[pInd]);

                // Neu khong la phieu xuat huy
                if (parseInt(pLoaiPhieu) != 10) {
                    if ((vTuquyen.length > 0) && (vDenquyen.length > 0)) {
                        enableTextField(vTuquyen[pInd], vDenquyen[pInd]);
                    }

                }
                // Neu la 
                else {
                    enableTextField(vTuso[pInd], vDenso[pInd]);
                }
            }

            var vCurrTyLe = vTyLe[pInd].value.substring(0, vTyLe[pInd].value.indexOf("|"));
            var vSoLuongChuan = vCurrTyLe.substring(0, vCurrTyLe.indexOf("/"));
            var vSoLuongTinh = vCurrTyLe.substring(vCurrTyLe.indexOf("/") + 1, vCurrTyLe.length);
            if (vSoLuongChuan == vSoLuongTinh) {
                //disable tu quyen, den quyen
                if ((vTuquyen.length > 0) && (vDenquyen.length > 0))
                    disableTextField(pClear, vTuquyen[pInd], vDenquyen[pInd]);
                enableTextField(vTuso[pInd], vDenso[pInd]);
            }
            else if (parseInt(pLoaiPhieu) != 9 && parseInt(pLoaiPhieu) != 7)//xuat DVSD, xuat cap tren
            {
                if ((vTuquyen.length > 0) && (vDenquyen.length > 0)) {
                    enableTextField(vTuquyen[pInd], vDenquyen[pInd]);
                    disableTextField(pClear, vTuso[pInd], vDenso[pInd]);
                }
            }
            if (typeof (disableQuyen) != 'undefined' && disableQuyen) {
                disableTextField(pClear, vTuquyen[pInd], vDenquyen[pInd]);
                enableTextField(vTuso[pInd], vDenso[pInd]);
            }

            if (typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0)// && vLoaiKho[0].value=='H')
                enableTextField(vTuso[pInd], vDenso[pInd]);

        }
        // Neu khong theo doi ky hieu
        else {
            // Chi enable truong so luong
            enableTextField(vSoluong[pInd]);
            disableTextField(pClear, vKyhieu[pInd], vTuso[pInd], vDenso[pInd]);
            // Neu khong phai phieu nhap thu hoi hoac xuat de huy
            if (!(/*(parseInt(pLoaiPhieu) == 5)||
	 			(parseInt(pLoaiPhieu) == 3)||
	 			(parseInt(pLoaiPhieu) == 4)||*/(parseInt(pLoaiPhieu) == 10))) {
                if ((vTuquyen.length > 0) && (vDenquyen.length > 0)) {
                    disableTextField(pClear, vDenquyen[pInd], vTuquyen[pInd]);
                }
            }
        }
        // Neu la Thong bao phat hanh: Ma+Ten: Readonly
        if ((parseInt(pLoaiPhieu) == 11)) {
            var vMa = document.getElementsByName("maAnchi");
            vMa[pInd].readOnly = true;
        }
    }
}

/*---------------------------*/
function getTenAnchiForm(pMa) {
    var idx;
    var i;
    if (isNull(pMa.value)) {
        setNull(0);
        return true;
    }
    idx =  - 1;
    for (i = 0;i < arrContent.length;i++) {
        if ((arrContent[i].Ma).toUpperCase() == (pMa.value).toUpperCase()) {
            idx = i;
            break;
        }
    }
    if (idx !=  - 1) {
        var vM = document.getElementsByName("maAnchi");
        var vT = document.getElementsByName("tenAnchi");
        var vI = document.getElementsByName("IdAnchi");
        var vX = document.getElementsByName("theodoiXeri");

        var vACBan = document.getElementsByName("acBan");
        var hACBan = document.getElementsByName("AnchiBan");
        var vGiaBan = document.getElementsByName("donGiaBan");
        var vGiaTToanTCuc = document.getElementsByName("donGiaTToanTCuc");
        var vGiaTToanCuc = document.getElementsByName("donGiaTToanCuc");

        vM[0].value = arrContent[idx].Ma;
        vT[0].value = arrContent[idx].Ten;
        vX[0].value = arrContent[idx].Theodoi;
        vI[0].value = arrContent[idx].ID;
        if (vACBan.length > 0)
            vACBan[0].checked = (arrContent[idx].AnchiBan == 'Y') ? true : false;
        if (vGiaBan.length > 0)
            vGiaBan[0].value = arrContent[idx].DonGiaBan;
        if (vGiaTToanTCuc.length > 0)
            vGiaTToanTCuc[0].value = arrContent[idx].DgTtoanTC;
        if (vGiaTToanCuc.length > 0)
            vGiaTToanCuc[0].value = arrContent[idx].DgTtoanCC;
        if (hACBan.length > 0)
            hACBan[0].value = arrContent[idx].AnchiBan;

    }
    else {
        showLovACForm();
        idx =  - 1;
        for (i = 0;i < arrContent.length;i++) {
            if ((arrContent[i].Ma).toUpperCase() == (pMa.value).toUpperCase()) {
                idx = i;
                break;
            }
        }
        if (idx ==  - 1) {
            setNull(0);
            return true;
        }
    }
}

/**
 * Ham: getTenAnchiXuat();
 * Hien thi ten va thiet lap cac Item khac khi NSD go MaAC khi nhap nhieu dong.
 * Neu go khong dung thi hien thi LOV
 * 	pMaAC: Ten cua Item chua gia tri Ma AC
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * 	pTuso: Ten cua Item chua gia tri Tu so
 * 	pDenso: Ten cua Item chua gia tri Den so
 * 	pSoluong: Ten cua Item chua gia tri So luong
 * Su dung: onBlur="getTenAnchi('MaAC', 'Kyhieu','Tuso','Denso','Soluong')"
 */
function getTenAnchiNhapXuat(pMaAC, pKyhieu, pTuso, pDenso, pSoluong, pLoaiPhieu) {

    var idx;
    var e1 = pMaAC;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    var rowIndx = e1.rowIndex - StartRow;

    if (isNull(pMaAC.value)) {
        setNull(rowIndx);
        return true;
    }
    idx =  - 1;
    var i;
    var dem = 0;
    for (i = 0;i < arrContent.length;i++) {
        if ((arrContent[i].Ma).toUpperCase() == (pMaAC.value).toUpperCase()) {
            idx = i;
            dem = dem + 1;
        }
    }
    if (idx !=  - 1 && dem == 1) {

        var vM = document.getElementsByName("maAnchi");
        var vT = document.getElementsByName("tenAnchi");
        var vI = document.getElementsByName("IdAnchi");
        var vX = document.getElementsByName("theodoiXeri");
        var vTenDVT = document.getElementsByName("tenDVT");
        var vTyLe = document.getElementsByName("tyLe");
        var vDonGia = document.getElementsByName("donGia");
        var vSoKyHieu = document.getElementsByName("soKyHieu");
        var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
        var vKyhieu = document.getElementsByName("kyHieu");
        var vTuso = document.getElementsByName("tuSo");
        var vDenso = document.getElementsByName("denSo");
        var vSoluong = document.getElementsByName("soLuong");

        var vLacMa = document.getElementsByName("lacMa");
        var vHThucIn = document.getElementsByName("hThucIn");
        var pClear = 0;

        if (!isNull(vKyhieu[rowIndx].value) && !isNull(vTuso[rowIndx].value) && !isNull(vDenso[rowIndx].value) || !isNull(vSoluong[rowIndx].value))
            pClear = 1;

        vM[rowIndx].value = arrContent[idx].Ma;
        vT[rowIndx].value = arrContent[idx].Ten;
        vX[rowIndx].value = arrContent[idx].Theodoi;
        vI[rowIndx].value = arrContent[idx].ID;
        if (typeof (vLacMa[rowIndx]) != 'undefined')
            vLacMa[rowIndx].value = arrContent[idx].LoaiAnchi;
        if (typeof (vHThucIn[rowIndx]) != 'undefined')
            vHThucIn[rowIndx].value = arrContent[idx].HinhThucIn;
        if (typeof (vLoaiAnchi[rowIndx]) != 'undefined')
            vLoaiAnchi[rowIndx].value = arrContent[idx].LoaiAnchi;
        // Neu la phieu xuat de huy hoac nhap thu hoi		   	    	
        if ((parseInt(pLoaiPhieu) == 10) || (parseInt(pLoaiPhieu) == 5) || (parseInt(pLoaiPhieu) == 3) || (parseInt(pLoaiPhieu) == 4)) {
            // Thi dung dvt luu kho
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[idx].DVTLuuKho;
            vTyLe[rowIndx].value = arrContent[idx].TyLeLuuKho;
        }
        // Con lai
        else {
            // Dung dvt nhap xuat		   	    		
            if (vTenDVT.length > 0)
                vTenDVT[rowIndx].value = arrContent[idx].DVTNhapXuat;
            vTyLe[rowIndx].value = arrContent[idx].TyLeNhapXuat;
        }
        // Neu co cot don gia   	    	
        if (vDonGia.length > 0) {
            // Lay don gia ban
            vDonGia[rowIndx].value = arrContent[idx].DonGiaBan;
        }
        //   	    alert(vDonGia[rowIndx].value);
        if (typeof (vSoKyHieu[rowIndx]) != 'undefined')
            vSoKyHieu[rowIndx].value = arrContent[idx].SoKyHieu;
        setEDItemNhapXuat(rowIndx, pKyhieu, pTuso, pDenso, pSoluong, pClear, pLoaiPhieu);
    }
    else {
        setEDItemNhapXuat(showLovACNhapXuat(), pKyhieu, pTuso, pDenso, pSoluong, pClear, pLoaiPhieu);
        idx =  - 1;
        for (i = 0;i < arrContent.length;i++) {
            if ((arrContent[i].Ma).toUpperCase() == (pMaAC.value).toUpperCase()) {
                idx = i;
                break;
            }
        }
        if (idx ==  - 1) {
            setNull(rowIndx);
            return true;
        }
    }
}

/**
 * Ham: getTenDVSD();
 * Hien thi ten khi NSD go MaDVSD.
 * Neu go khong dung thi hien thi LOV
 * 	pMaDVSD: Ten cua Item chua gia tri Ma DVSD
 * Su dung: onBlur="getTenDVSD('MaDVSD')"
 */
function getTenDVSD(pMaDVSD, formindex) {
    var vT = document.getElementsByName("TenDVSD");
    for (i = 0;i < arrDVSDContent.length;i++) {
        if (pMaDVSD == "" || pMaDVSD == null) {
            vT[formindex].value = "";
            return;
        }
        if (arrDVSDContent[i].Ma == pMaDVSD) {
            vT[formindex].value = arrDVSDContent[i].Ten;
            return;
        }
    }
    showLovDVSD(formindex);
    return;
}
/*
	Ham nay co the dung thay cho ham getTenDVSD
	(Chuyen doi dan cac ham getTenDVSD tren ung dung thanh getTenDVSD_Layer)
*/
function getTenDVSD_Layer(pMaDVSD, formindex, layerindex) {
    var vT = document.getElementsByName("TenDVSD");
    //alert(pMaDVSD);
    for (i = 0;i < arrDVSDContent.length;i++) {
        if (pMaDVSD == "" || pMaDVSD == null) {
            vT[formindex].value = "";
            return;
        }
        if (arrDVSDContent[i].Ma == pMaDVSD) {
            vT[formindex].value = arrDVSDContent[i].Ten;
            return;
        }
    }
    showLovDVSD(layerindex);
    var vMa = document.getElementsByName("MaDVSD");
    var trung = false;
    for (i = 0;i < arrDVSDContent.length;i++) {
        if (arrDVSDContent[i].Ma == vMa[formindex].value) {
            trung = true;
        }
    }
    if (trung == false) {
        vT[formindex].value = "";
        vMa[formindex].value = "";
    }
    return;
}

/**
 * Ham: fillAnchi(); Dung trong truong hop xuat an chi
 * Hien thi cac thong tin AC va thiet lap cac Item khac khi load trang.
 * 	pKyhieu: Ten cua Item chua gia tri Ky hieu AC
 * 	pTuso: Ten cua Item chua gia tri Tu so
 * 	pDenso: Ten cua Item chua gia tri Den so
 * 	pSoluong: Ten cua Item chua gia tri So luong
 * Su dung: onLoad="fillAnchi('Kyhieu','Tuso','Denso','Soluong')"
 */
function fillAnchi(pKyhieu, pTuso, pDenso, pSoluong, pLoaiPhieu) {
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");
    var vX = document.getElementsByName("theodoiXeri");
    var vTenDVT = document.getElementsByName("tenDVT");
    var vTyLe = document.getElementsByName("tyLe");
    var vTmpTyLe = document.getElementsByName("tmpTyLe");
    var vDonGia = document.getElementsByName("donGia");
    var vTuso = document.getElementsByName("tuSo");
    var vDenso = document.getElementsByName("denSo");
    var vSoluong = document.getElementsByName("soLuong");
    var vTuQuyen = document.getElementsByName("tuQuyen");
    var vDenQuyen = document.getElementsByName("denQuyen");
    var vSoTien = document.getElementsByName("soTien");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vLoaiAnchi = document.getElementsByName("LoaiAnchi");
    var vLoi = document.getElementsByName("loi");

    for (var j = 0;j < vI.length;j++) {
        if (!isNull(vI[j])) {
            for (var i = 0;i < arrContent.length;i++) {
                if ((arrContent[i].ID).toUpperCase() == (vI[j].value).toUpperCase()) {
                    vM[j].value = arrContent[i].Ma;
                    vT[j].value = arrContent[i].Ten;
                    vX[j].value = arrContent[i].Theodoi;
                    if (typeof (vLoaiAnchi[j]) != 'undefined')
                        vLoaiAnchi[j].value = arrContent[i].LoaiAnchi;
                    vSoKyHieu = arrContent[i].SoKyHieu;
                    // Neu la phieu xuat de huy hoac nhap thu hoi		   	    	
                    if ((parseInt(pLoaiPhieu) == 10) || (parseInt(pLoaiPhieu) == 5) || (parseInt(pLoaiPhieu) == 3) || (parseInt(pLoaiPhieu) == 4)) {
                        // Thi dung dvt luu kho
                        if (vTenDVT.length > 0 && typeof (vTenDVT[j]) != 'undefined')
                            vTenDVT[j].value = arrContent[i].DVTLuuKho;
                        vTyLe[j].value = arrContent[i].TyLeLuuKho;
                        if (vTmpTyLe.length > 0 && typeof (vTmpTyLe[j]) != 'undefined')
                            vTmpTyLe[j].value = arrContent[i].TyLeNhapXuat;

                    }
                    // Con lai
                    else {
                        // Dung dvt nhap xuat	
                        if (vTenDVT.length > 0 && typeof (vTenDVT[j]) != 'undefined')
                            vTenDVT[j].value = arrContent[i].DVTNhapXuat;
                        vTyLe[j].value = arrContent[i].TyLeNhapXuat;
                    }
                    // Neu co cot don gia
                    if ((vDonGia.length > 0) && (parseInt(pLoaiPhieu) != 11)) {
                        // Lay don gia ban
                        vDonGia[j].value = toFormatNumberDe(toNumber(arrContent[i].DonGiaBan), 3);
                    }

                    if ((vDonGia.length > 0)) {
                        // Lay so tien			   	    
                        vSoTien[j].value = toFormatNumberDe(toNumber((parseFloat(toNumber(vDonGia[j].value))) * (parseInt(toNumber(vSoluong[j].value)))), 3);
                    }

                    if ((vTuso[j].value.length > 0) && (vDenso[j].value.length > 0)) {
                        if (vTuQuyen.length > 0) {
                            var vCurrTyLe = arrContent[i].TyLeNhapXuat.substring(0, arrContent[i].TyLeNhapXuat.indexOf("|"));
                            var vSoLuongChuan = vCurrTyLe.substring(0, vCurrTyLe.indexOf("/"));
                            var vSoLuongTinh = vCurrTyLe.substring(vCurrTyLe.indexOf("/") + 1, vCurrTyLe.length);

                            if (vSoLuongChuan != vSoLuongTinh && (typeof (tinhTuQuyen) != 'undefined' && tinhTuQuyen || parseInt(toNumber(vDenso[j].value)) % (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh)) == 0 && (parseInt(toNumber(vTuso[j].value)) - 1) % (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh)) == 0)) {
                                vTuQuyen[j].value = toFormatNumber((parseInt(toNumber(vTuso[j].value)) - 1) / (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh)) + 1);
                                vDenQuyen[j].value = toFormatNumber(parseInt(toNumber(vDenso[j].value)) / (parseInt(vSoLuongChuan) / parseInt(vSoLuongTinh)));
                            }
                        }
                    }

                    if (vLoi.length > 0) {
                        if (typeof (vLoi[j]) != 'undefined' && !isNull(vLoi[j]) && vLoi[j].value == 'true') {
                            setColor(j + 1, '#FF0000');
                        }
                    }
                    setEDItemNhapXuat(j, pKyhieu, pTuso, pDenso, pSoluong, 1, pLoaiPhieu);
                    if (!isNull(vTuso[j].value) && !isNaN(vTuso[j].value))
                        vTuso[j].value = toFormatNumber(toNumber(vTuso[j].value));
                    if (!isNull(vDenso[j].value) && !isNaN(vDenso[j].value))
                        vDenso[j].value = toFormatNumber(toNumber(vDenso[j].value));
                    vSoluong[j].value = toFormatNumber(toNumber(vSoluong[j].value));
                    break;
                }
            }
        }
    }
}

function setColor(rowID, pColor) {
    if (typeof (rowID) == 'undefined')
        return false;
    var vcells = cTable.rows[rowID].cells;
    for (i = 0;i < vcells.length;i++) {
        var vElement = vcells[i].firstChild;
        if ((vElement.tagName == 'INPUT') || (vElement.tagName == 'SELECT')) {
            vElement.style.backgroundColor = pColor;
        }
    }
}

function fillAnchi1() {
    var vM = document.getElementsByName("maAnchi");
    var vT = document.getElementsByName("tenAnchi");
    var vI = document.getElementsByName("IdAnchi");

    for (j = 0;j < vI.length;j++) {
        if (!isNull(vI[j])) {
            for (i = 0;i < arrContent.length;i++) {
                if ((arrContent[i].ID).toUpperCase() == (vI[j].value).toUpperCase()) {
                    vM[j].value = arrContent[i].Ma;
                    vT[j].value = arrContent[i].Ten;
                    break;
                }
            }
        }
    }
}

// Them dong, xoa dong, active dong hien tai
function setTable2(pTableId, pTableId2, start, end) {
    cTable = document.getElementById(pTableId);
    cTable2 = document.getElementById(pTableId2);
    StartRow = start;
    curRow = start;

    if (cTable.rows.length - (end + start) > 0) {
        EndRow = cTable.rows.length - (end + 1);
    }
    else {
        EndRow = 0;
    }

    if (cTable.rows.length == 0)
        return;
    for (i = StartRow;i <= EndRow;i++) {
        itemIndex = 0;

        cTable.rows[i].attachEvent("onclick", rowClick2);
        cTable2.rows[i].attachEvent("onclick", rowClick2);
    }
    if (typeof (curRow) != 'undefined')
        setCurRow2(curRow);

}

function setTable(pTableId, start, end) {
    cTable = document.getElementById(pTableId);
    var iStart;
    StartRow = start;
    if (typeof (StartRowed) != 'undefined')
        iStart = StartRowed;
    else 
        iStart = StartRow;

    curRow = iStart;
    if (cTable.rows.length - (end + start) > 0) {
        EndRow = cTable.rows.length - (end + 1);
    }
    else {
        EndRow = 0;
    }

    if (cTable.rows.length == 0)
        return;
    for (i = iStart;i <= EndRow;i++) {
        itemIndex = 0;
        cTable.rows[i].attachEvent("onclick", rowClick);
    }
    if (typeof (curRow) != 'undefined')
        setCurRow(curRow);
}

function resetValue(pValue) {
    s = new String(pValue);
    while (s.indexOf(" value=") >= 0)
        s = s.replace(" value=", "@");
    while (s.indexOf("@") >= 0)
        s = s.replace("@", " value='' ");
    return s;
}

/**
 *Hàm resetElementValue reset giá trị các phần tử trong một container v? giá trị ban đầu
 *Tham số vào:
 *	pElement: container chứa các phần tử
 clear: true - reset toàn bộ các phần tử
 false- reset toàn bộ các trư?ng trừ trư?ng (maAnchi, tenAnchi, tenDVT)
 */
function resetElementValue(pElement, clear) {
    var obj = pElement.all;
    for (var i = 0;i < obj.length;i++) {
        if (obj[i].tagName == 'INPUT') {
            if (obj[i].type == 'text' || obj[i].type == 'hidden') {
                if (clear || (obj[i].name != "maAnchi" && obj[i].name != "tenAnchi" && obj[i].name != "tenDVT" && obj[i].name != "donGia" && obj[i].name != "slgChuanAC" && obj[i].name != "theodoiXeri" && obj[i].name != "IdAnchi" && obj[i].name != "tyLe" && obj[i].name != "kyHieu" && obj[i].name != "slgTinhAC" && obj[i].name != "tmpTyLe" && obj[i].name != "TIN_DONVI_MUA" && obj[i].name != "TEN_DONVI_MUA" && obj[i].name != "DVT" && obj[i].name != "soKyHieu" && obj[i].name != "lacMa" && obj[i].name != "hThucIn"))
                    obj[i].value = '';
            }
            if (obj[i].type == 'checkbox')
                obj[i].checked = false;
        }

    }
}

function deleterow2() {

    if (typeof (curRow) == 'undefined')
        return false;
    var i = curRow;
    var leng = EndRow;
    var iStart = StartRow;
    if (typeof (StartRowed) != 'undefined')
        iStart = StartRowed;
    if (i < iStart || i > EndRow)
        return false;
    if (i < EndRow) {
        curRow = i;
        cTable.deleteRow(i);
        cTable2.deleteRow(i);
        EndRow = EndRow - 1;
    }
    else {
        if (i == StartRow) {
            var vcells = cTable.rows[curRow].cells;
            var j;
            for (j = 0;j < vcells.length;j++)
                resetElementValue(vcells[j], true);

            vcells = cTable2.rows[curRow].cells;
            for (j = 0;j < vcells.length;j++)
                resetElementValue(vcells[j], true);
            curRow = StartRow;
        }
        else {
            curRow = i - 1;
            cTable.deleteRow(i);
            cTable2.deleteRow(i);
            EndRow = EndRow - 1;
        }
    }

    if (typeof (curRow) != 'undefined')

        setCurRow2(curRow);
    return true;
}

function deleterow() {
    if (typeof (curRow) == 'undefined')
        return false;
    var i = curRow;
    var leng = EndRow;
    var lim = cTable.rows[0].innerHTML.indexOf("</TH>") ==  - 1 ? EndRow - 1 : EndRow;
    var iStart = StartRow;
    if (typeof (StartRowed) != 'undefined')
        iStart = StartRowed;
    if (i < iStart || i > EndRow)
        return false;
    if (i < lim) {
        curRow = i;
        cTable.deleteRow(i);
        EndRow = EndRow - 1;
    }
    else {
        if (i == StartRow) {
            var vcells = cTable.rows[curRow].cells;
            for (var j = 0;j < vcells.length;j++)
                resetElementValue(vcells[j], true);
            curRow = StartRow;
        }
        else {
            curRow = i - 1;
            cTable.deleteRow(i);
            EndRow = EndRow - 1;
        }
    }

    if (typeof (curRow) != 'undefined') {
        setCurRow(curRow);
        var vEle;
        var focused = false;
        for (i = 0;i < cTable.rows[curRow].cells.length;i++) {
            vEle = cTable.rows[curRow].cells[i].firstChild;
            if (!focused && vEle.tagName == 'INPUT' && vEle.type != 'hidden') {
                vEle.focus();
                focused = true;
            }
        }
    }
    return true;
}

function addrow2() {
    var focused = false;

    if (typeof (curRow) == 'undefined')
        return false;
    if (curRow < StartRow)
        curRow = StartRow;
    var vcells = cTable.rows[curRow].cells;
    var newRow = cTable.insertRow(curRow + 1);
    var newCell;

    var vSeri = document.getElementsByName("theodoiXeri");
    //var		arg		=	addrow2.arguments;
    for (i = 0;i < vcells.length;i++) {
        newCell = newRow.insertCell();
        newCell.innerHTML = vcells[i].innerHTML;
        if (vSeri[curRow - StartRow].value == 'Y' && usedAddData && typeof (isAddData) != 'undefined' && isAddData)
            resetElementValue(newCell, false);
        else 
            resetElementValue(newCell, true);
    }
    newRow.attachEvent("onclick", rowClick2);

    vcells = cTable2.rows[curRow].cells;
    var newRow2 = cTable2.insertRow(curRow + 1);
    for (i = 0;i < vcells.length;i++) {
        newCell = newRow2.insertCell();
        newCell.innerHTML = vcells[i].innerHTML;
        if (vSeri[curRow - StartRow].value == 'Y' && usedAddData && typeof (isAddData) != 'undefined' && isAddData)
            resetElementValue(newCell, false);
        else 
            resetElementValue(newCell, true);
    }
    newRow2.attachEvent("onclick", rowClick2);
    //dat focus
    var vEle;
    for (i = 0;i < newRow.cells.length;i++) {
        vEle = newRow.cells[i].firstChild;
        if (!focused && vEle.tagName == 'INPUT' && vEle.type != 'hidden') {
            if (vEle.type == 'text')
                vEle.select();
            else 
                vEle.focus();
            focused = true;
        }
    }

    EndRow = EndRow + 1;
    resetRow2(curRow);
    setCurRow2(curRow + 1);
    curRow++;
}

function addrow() {
    var focused = false;
    //	if(curRow=='') return false;
    if (typeof (curRow) == 'undefined')
        return false;
    if (curRow < StartRow)
        curRow = StartRow;
    var vCell = cTable.rows[curRow].cells;
    var newRow = cTable.insertRow(curRow + 1);
    var newCell;
    var vSeri = document.getElementsByName("theodoiXeri");
    for (i = 0;i < vCell.length;i++) {
        newCell = newRow.insertCell();
        newCell.innerHTML = vCell[i].innerHTML;
        if (typeof (vSeri[curRow - StartRow]) != 'undefined' && vSeri[curRow - StartRow].value == 'Y' && usedAddData && typeof (isAddData) != 'undefined' && isAddData)
            resetElementValue(newCell, false);
        else 
            resetElementValue(newCell, true);
    }
    //dat focus
    var vEle;
    for (i = 0;i < newRow.cells.length;i++) {
        vEle = newRow.cells[i].firstChild;
        //alert("tagname: " + vEle.tagName);
        if (!focused && vEle.tagName == 'INPUT' && vEle.type != 'hidden') {
            if (vEle.type == 'text')
                vEle.select();
            else 
                vEle.focus();
            focused = true;
        }
    }
    newRow.attachEvent("onclick", rowClick);
    EndRow = EndRow + 1;
    resetRow(curRow);
    setCurRow(curRow + 1);
    curRow++;

}

function rowClick2() {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    if ((e1.rowIndex != curRow)) {
        if ((e1.rowIndex <= EndRow)) {
            itemIndex = e1.rowIndex - StartRow;
            if (typeof (curRow) != 'undefined')
                resetRow2(curRow);
            curRow = e1.rowIndex;
        }
        if (!isInteger(e1.rowIndex))
            return false;
        else {
            setCurRow2(e1.rowIndex);
        }
    }
}

function rowClick() {
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }
    if ((e1.rowIndex != curRow)) {
        if ((e1.rowIndex >= StartRow) && (e1.rowIndex <= EndRow)) {
            itemIndex = e1.rowIndex - StartRow;
            if (typeof (curRow) != 'undefined')
                resetRow(curRow);
            curRow = e1.rowIndex;
            if (!isInteger(e1.rowIndex))
                return false;
            else 
                setCurRow(e1.rowIndex);
        }
    }
}

function resetRow2(pRow) {
    if (typeof (pRow) == 'undefined')
        return false;
    cTable.rows[pRow].style.backgroundColor = OtherColor;
    var vcells = cTable.rows[pRow].cells;
    for (i = 0;i < vcells.length;i++) {
        var vElement = vcells[i].firstChild;
        if ((vElement.tagName == 'INPUT') || (vElement.tagName == 'SELECT')) {
            vElement.style.backgroundColor = OtherColor;
        }
    }
    cTable2.rows[pRow].style.backgroundColor = OtherColor;
    vcells = cTable2.rows[pRow].cells;
    for (i = 0;i < vcells.length;i++) {
        var vElement = vcells[i].firstChild;
        if ((vElement.tagName == 'INPUT') || (vElement.tagName == 'SELECT')) {
            vElement.style.backgroundColor = OtherColor;
        }
    }
}

function resetRow(pRow) {
    if (typeof (pRow) == 'undefined')
        return false;
    cTable.rows[pRow].style.backgroundColor = OtherColor;
    var vcells = cTable.rows[pRow].cells;
    for (i = 0;i < vcells.length;i++) {
        var vElement = vcells[i].firstChild;
        if ((vElement != null) && (typeof (vElement.tagName) != 'undefined') && ((vElement.tagName == 'INPUT') || (vElement.tagName == 'SELECT'))) {
            vElement.style.backgroundColor = OtherColor;
        }
    }
}

function setCurRow2(pRow) {
    if (typeof (pRow) == 'undefined')
        return false;
    if (pRow < 0)
        return false;
    if (pRow > EndRow)
        return false;
    if ((pRow > EndRow) && (StartRow == EndRow))
        return false;
    cTable.rows[pRow].style.backgroundColor = CurrentColor;
    var vcells = cTable.rows[pRow].cells;
    for (i = 0;i < vcells.length;i++) {
        var vElement = vcells[i].firstChild;
        if ((vElement.tagName == 'INPUT') || (vElement.tagName == 'SELECT')) {
            vElement.style.backgroundColor = CurrentColor;
        }
    }
    cTable2.rows[pRow].style.backgroundColor = CurrentColor;
    var vcells = cTable2.rows[pRow].cells;
    for (i = 0;i < vcells.length;i++) {
        var vElement = vcells[i].firstChild;
        if ((vElement.tagName == 'INPUT') || (vElement.tagName == 'SELECT')) {
            vElement.style.backgroundColor = CurrentColor;
        }
    }
}

function setCurRow(pRow) {

    if (typeof (pRow) == 'undefined')
        return false;
    if (pRow < 0)
        return false;
    if (pRow > EndRow)
        return false;
    if ((pRow > EndRow) && (StartRow == EndRow))
        return false;
    cTable.rows[pRow].style.backgroundColor = CurrentColor;
    var vcells = cTable.rows[pRow].cells;
    for (i = 0;i < vcells.length;i++) {
        var vEle = vcells[i].firstChild;
        if (typeof (vEle) == 'undefined' || isNull(vEle))
            continue;
        if ((vEle.tagName == 'INPUT')) {
            vEle.style.backgroundColor = CurrentColor;
        }
    }
}

// Ham de an, hien cac menu layer
function findObj(n, d) {
    var p, i, x;
    if (!d)
        d = document;
    if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
        d = parent.frames[n.substring(p + 1)].document;
        n = n.substring(0, p);
    }
    if (!(x = d[n]) && d.all)
        x = d.all[n];
    for (i = 0;!x && i < d.forms.length;i++)
        x = d.forms[i][n];
    for (i = 0;!x && d.layers && i < d.layers.length;i++)
        x = findObj(n, d.layers[i].document);
    if (!x && d.getElementById)
        x = d.getElementById(n);
    return x;
}

// Ham bo cac ky tu trang dau va cuoi xau
// Tham so: s: Xau can cat cac ky tu
function trim(s) {
    var i;
    if (isNull(s))
        return "";
    i = s.length - 1;
    while (i >= 0 && s.charAt(i) == ' ')
        i--;
    s = s.substring(0, i + 1);
    i = 0;
    while (i < s.length && s.charAt(i) == ' ')
        i++;
    return s.substring(i);
}

// Cac ham su dung cho check date format
function isInteger(s) {
    var i;
    for (i = 0;i < s.length;i++) {
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
            return false;
    }
    return true;
}

function stripCharsInBag(s, bag) {
    var i;
    var returnString = "";
    for (i = 0;i < s.length;i++) {
        var c = s.charAt(i);
        if (bag.indexOf(c) ==  - 1)
            returnString += c;
    }
    return returnString;
}

function daysInFebruary(year) {
    return (((year % 4 == 0) && ((!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28);
}

function DaysArray(n) {
    for (var i = 1;i <= n;i++) {
        this[i] = 31;
        if (i == 4 || i == 6 || i == 9 || i == 11) {
            this[i] = 30;
        }
        if (i == 2) {
            this[i] = 29;
        }
    }
    return this;
}

function isDate(dtStr) {
    var dtCh = "*";
    if (dtStr == "")
        return true;
    var daysInMonth = DaysArray(12);

    for (var intElementNr = 0;intElementNr < strSeparatorArray.length;intElementNr++) {
        if (dtStr.indexOf(strSeparatorArray[intElementNr]) !=  - 1)
            dtCh = strSeparatorArray[intElementNr];
    }
    if (dtCh != "*")//neu co ky hieu phan cach
    {
        var pos1 = dtStr.indexOf(dtCh);
        var pos2 = dtStr.indexOf(dtCh, pos1 + 1);
        if (pos1 ==  - 1 || pos2 ==  - 1) {
            return false;
        }
        var strDay = dtStr.substring(0, pos1);
        var strMonth = dtStr.substring(pos1 + 1, pos2);
        var strYear = dtStr.substring(pos2 + 1);
    }
    else //khong co ky hieu phan cach
    {
        if (dtStr.length > 5) {
            strDay = dtStr.substr(0, 2);
            strMonth = dtStr.substr(2, 2);
            strYear = dtStr.substr(4);
        }
        else 
            return false;
    }

    if (!isInteger(strYear) || !isInteger(strMonth) || !isInteger(strDay))
        return false;

    strYr = strYear;

    if (strDay.charAt(0) == "0" && strDay.length > 1)
        strDay = strDay.substring(1);

    if (strMonth.charAt(0) == "0" && strMonth.length > 1)
        strMonth = strMonth.substring(1);

    for (var i = 1;i <= 3;i++) {
        if (strYr.charAt(0) == "0" && strYr.length > 1)
            strYr = strYr.substring(1);
    }

    month = parseInt(strMonth);
    day = parseInt(strDay);
    year = parseInt(strYr);

    if (strMonth.length < 1 || month < 1 || month > 12) {
        return false;
    }
    if (strDay.length < 1 || day < 1 || day > 31 || (month == 2 && day > daysInFebruary(year)) || day > daysInMonth[month]) {
        return false;
    }

    if (year < 50)
        year += 2000;
    if (year > 50 && year < 1000)
        year += 1900;
    if (strYear.length < 1 || year == 0 || year < minYear || year > maxYear) {
        return false;
    }
    if (day < 10)
        day = "0" + day;
    if (month < 10)
        month = "0" + month;
    return "" + day + "/" + month + "/" + year;
}

function isMonth(dtStr) {
    var dtCh = "*";
    if (dtStr == "")
        return "";
    var daysInMonth = DaysArray(12);

    for (var intElementNr = 0;intElementNr < strSeparatorArray.length;intElementNr++) {
        if (dtStr.indexOf(strSeparatorArray[intElementNr]) !=  - 1)
            dtCh = strSeparatorArray[intElementNr];
    }

    if (dtCh != "*")//neu co ky hieu phan cach
    {
        var pos1 = dtStr.indexOf(dtCh);

        var strMonth = dtStr.substring(0, pos1);
        var strYear = dtStr.substring(pos1 + 1);
    }
    else //khong co ky hieu phan cach
    {
        if (dtStr.length > 3) {
            strMonth = dtStr.substr(0, 2);
            strYear = dtStr.substr(2);
        }
        else 
            return false;
    }

    if (!isInteger(strYear) || !isInteger(strMonth))
        return false;
    strYr = strYear;

    if (strMonth.charAt(0) == "0" && strMonth.length > 1)
        strMonth = strMonth.substring(1);

    for (var i = 1;i <= 3;i++) {
        if (strYr.charAt(0) == "0" && strYr.length > 1)
            strYr = strYr.substring(1);
    }

    month = parseInt(strMonth);
    year = parseInt(strYr);

    if (strMonth.length < 1 || month < 1 || month > 12) {
        return false;
    }

    if (year < 50)
        year += 2000;
    if (year > 50 && year < 1000)
        year += 1900;
    if (strYear.length < 1 || year == 0 || year < minYear || year > maxYear) {
        return false;
    }
    if (month < 10)
        month = "0" + month;
    return "" + month + "/" + year;
}

function isYear(dtStr) {
    if (dtStr == "")
        return "";
    var daysInMonth = DaysArray(12);
    strYear = dtStr;
    strYr = strYear;
    if (!isInteger(strYear))
        return false;
    for (var i = 1;i <= 3;i++) {
        if (strYr.charAt(0) == "0" && strYr.length > 1)
            strYr = strYr.substring(1);
    }

    year = parseInt(strYr);
    if (year < 50)
        year += 2000;
    if (year > 50 && year < 1000)
        year += 1900;
    if (strYear.length < 1 || year == 0 || year < minYear || year > maxYear) {
        return false;
    }
    return "" + year;
}

// Doi mau cell khi mouse over, mouse move
function cOn(td) {
    if (document.getElementById || (document.all && !(document.getElementById))) {
        td.style.backgroundColor = "#609BFE";//"#99CC00";
    }
}

function cOut(td) {
    if (document.getElementById || (document.all && !(document.getElementById))) {
        td.style.backgroundColor = "#E5E5E5";
    }
}
// Hien thi ngay hien tai
function showDate() {
    var time = new Date();
    var dd = time.getDate();
    var mm = time.getMonth() + 1;
    var yy = time.getYear();
    var temp = '';
    if (dd < 10)
        dd = '0' + dd;
    if (mm < 10)
        mm = '0' + mm;
    temp = dd + "/" + mm + "/" + yy;
    document.write(temp);
}

// Phuc vu Valid tu so den so
function rowData(pType, pSeri, pFrom, pTo, pRow) {
    this.seri = pType + pSeri;
    this.fromNum = pFrom;
    this.toNum = pTo;
    this.rowNum = pRow;
}

// Phuc vu Valid tu quyen den quyen tu so den so
function rowDataQS(pType, pSeri, pTuquyen, pDenquyen, pTuso, pDenso, pRow) {
    this.seri = pType + pSeri;
    this.tuQuyen = pTuquyen;
    this.denQuyen = pDenquyen;
    this.tuSo = pTuso;
    this.denSo = pDenso;
    this.rowNum = pRow;
}

function sort_func(a, b) {
    if (a.seri == b.seri)
        return a.fromNum - b.fromNum;
    else if (a.seri < b.seri)
        return  - 1;
    else 
        return 1;
}

function sort_funcQS(a, b) {
    if (a.seri == b.seri)
        return a.tuSo - b.tuSo;
    else if (a.seri < b.seri)
        return  - 1;
    else 
        return 1;
}

function sort_Numbers(a, b) {
    return (a - b);
}

function dmKho(pMa, pTen) {
    this.Ma = pMa;
    this.Ten = pTen;
    this.Loai = "KHO";
}

function dmAnchi(pId, pMa, pTen) {
    this.ID = pId;
    this.Ma = pMa;
    this.Ten = pTen;
    this.Loai = "ANCHI";
}

function dmAnchi2(pId, pMa, pTen, pLoaiAnchi, pHinhThucIn, pNgayBatDau, pCqt_Id) {
    this.ID = pId;
    this.Ma = pMa;
    this.Ten = pTen;
    this.Loai = "ANCHI";
    if (pLoaiAnchi != null)
        this.LoaiAnchi = pLoaiAnchi;
    if (pNgayBatDau != null)
        this.NgayBatDau = pNgayBatDau;
    if (pHinhThucIn != null)
        this.HinhThucIn = pHinhThucIn;
    if (pCqt_Id != null)
        this.Cqt_Id = pCqt_Id;
}

function convertDMDVSDToString() {
    var strCvt = "new dmDVSD('"
    strCvt += this.Ma + "','";
    strCvt += SetUnicode(this.Ten) + "'";
    strCvt += ");";
    return strCvt;
}

function dmDVSD(pMa, pTen) {
    this.Ma = pMa;
    this.Ten = pTen;
    this.Loai = "DVSD";
    this.convertDMDVSDToString = convertDMDVSDToString
}

function dmCqt(pId, pMa, pTen, pCap) {
    this.ID = pId;
    this.Ma = pMa;
    this.Ten = pTen;
    this.Cap = pCap;
    this.Loai = "CQT";
}

//9
function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan) {

    return loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, null, null, null, null, null, null, null);
}
//10
function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pLoaiAnchi) {

    return loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, null, pLoaiAnchi, null, null, null, null, null);
}
//12
function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pHinhthucTheodoi) {

    return loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, null, null, pHinhthucTheodoi, null, null);
}
//13
function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC) {

    return loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC, null, null, null);
}
//14
function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC, pDonGiaIn) {

    return loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC, pDonGiaIn, null, null);
}
//13
function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC) {

    return loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC, null, null);
}
/*
* Edit: Longbh
* Sua lai ham de dung chung trong BC20	
*/

function loaiAnchi(pId, pMa, pTen, pTheodoi, pDVTLuuKho, pTyLeLuuKho, pDVTNhapXuat, pTyLeNhapXuat, pDonGiaBan, pSoKyHieu, pLoaiAnchi, pDgTtoanCC, pDgTtoanTC, pHinhthucTheodoi, pAnchiBan, pLac_XM, pNgayBatDau, pNgayKetThuc, pHinhThucIn, pDonGiaIn, pCqt_Id, pDKH_ID, pDKH_TIN) {
    this.ID = pId;
    this.Ma = pMa;
    this.Ten = pTen;
    this.Theodoi = pTheodoi;
    this.DVTLuuKho = pDVTLuuKho;
    this.TyLeLuuKho = pTyLeLuuKho;
    this.DVTNhapXuat = pDVTNhapXuat;
    this.TyLeNhapXuat = pTyLeNhapXuat;
    this.DonGiaBan = pDonGiaBan;
    if (pSoKyHieu != null)
        this.SoKyHieu = pSoKyHieu;
    if (pLoaiAnchi != null)
        this.LoaiAnchi = pLoaiAnchi;
    if (pDgTtoanCC != null) {
        this.DgTtoanCC = pDgTtoanCC;
        this.DgTtoanTC = pDgTtoanTC;
    }
    if (pHinhthucTheodoi != null)
        this.HinhthucTheodoi = pHinhthucTheodoi;
    if (pAnchiBan != null)
        this.AnchiBan = pAnchiBan;
    if (pLac_XM != null)
        this.Lac_XM = pLac_XM;
    if (pNgayBatDau != null)
        this.NgayBatDau = pNgayBatDau;
    if (pNgayKetThuc != null)
        this.NgayKetThuc = pNgayKetThuc;
    if (pDonGiaIn != null)
        this.DonGiaIn = pDonGiaIn;
    if (pHinhThucIn != null)
        this.HinhThucIn = pHinhThucIn;
    //linhlt5 thuc hien ngay 07-03-2011 them theo yeu cau moi ve dinh dang ky hieu
    if (pCqt_Id != null)
        this.Cqt_Id = pCqt_Id;
    if (pDKH_ID != null)
        this.Dkh_Id = pDKH_ID;
    if (pDKH_TIN != null)
        this.Dkh_Tin = pDKH_TIN;
    //Ham chuyen ve chuoi
    this.convertToString = convertToString
}

/**
 *	convertToString: chuyen ve dang chuoi
 */
function convertToString() {
    var strCvt = "new loaiAnchi('"
    strCvt += this.ID + "','";
    strCvt += SetUnicode(this.Ma) + "','";
    strCvt += SetUnicode(this.Ten) + "','";
    strCvt += this.Theodoi + "','";
    strCvt += SetUnicode(this.DVTLuuKho) + "','";
    strCvt += this.TyLeLuuKho + "','";
    strCvt += SetUnicode(this.DVTNhapXuat) + "','";
    strCvt += this.TyLeNhapXuat + "','";
    strCvt += this.DonGiaBan + "'";
    if (typeof (this.SoKyHieu) != 'undefined')
        strCvt += ",'" + this.SoKyHieu + "'";
    if (typeof (this.LoaiAnchi) != 'undefined')
        strCvt += ",'" + this.LoaiAnchi + "'";
    if (typeof (this.DgTtoanCC) != 'undefined') {
        strCvt += ",'" + this.DgTtoanCC;
        strCvt += "','" + this.DgTtoanTC + "'";
    }
    if (typeof (this.HinhthucTheodoi) != 'undefined')
        strCvt += ",'" + this.HinhthucTheodoi + "'";
    if (typeof (this.AnchiBan) != 'undefined')
        strCvt += ",'" + this.AnchiBan + "'";
    if (typeof (this.Lac_XM) != 'undefined')
        strCvt += ",'" + this.Lac_XM + "'";
    if (typeof (this.NgayBatDau) != 'undefined')
        strCvt += ",'" + this.NgayBatDau + "'";
    if (typeof (this.NgayKetThuc) != 'undefined')
        strCvt += ",'" + this.NgayKetThuc + "'";
    strCvt += ",'" + this.HinhThucIn + "'";
    if (typeof (this.DonGiaIn) != 'undefined')
        strCvt += ",'" + '' + "'";
    if (typeof (this.Cqt_Id) != 'undefined')
        strCvt += ",'" + this.Cqt_Id + "'";

    strCvt += ");";

    return strCvt;
}

function listDMKho(pMakho, pTenkho) {
    this.Makho = pMakho;
    this.Tenkho = pTenkho;
}

function genTable(tableID) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var newRow;
    var newCell;
    var order = 1;
    var vMamau = false;
    if (arrCont.length > 0) {
        for (i = 1;i <= arrCont.length;i++)
            if (arrCont[i - 1].Mau != "" && typeof (arrCont[i - 1].Mau) != 'undefined') {
                vMamau = true;
                break;
            }

        for (i = 1;i <= arrCont.length;i++) {
            newRow = vTable.insertRow(order);
            newCell = (newRow.insertCell());
            newCell.innerHTML = "" + i;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Ma;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Ten;
            newCell.height = 18;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Mau;
            order++;
        }
        if (!vMamau) {
            //Xoa cot Mau
            for (i = 0;i < vTable.rows.length;i++)
                vTable.rows[i].deleteCell();
        }
    }
    vTable.deleteRow(0);
}

function genTable2(tableID, table2, iLoai) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var vTable2 = document.getElementById(table2);
    var oldRow = vTable.rows[0];
    var newRow;

    if (iLoai != 1)
        oldRow.deleteCell();

    if (arrCont.length > 0) {
        for (i = 1;i <= arrCont.length;i++) {
            newRow = oldRow.cloneNode(true);
            newRow.childNodes[0].innerHTML = "" + i;
            newRow.childNodes[1].innerHTML = arrCont[i - 1].Ma;
            newRow.childNodes[2].innerHTML = arrCont[i - 1].Ten;
            if (iLoai == 1)
                newRow.childNodes[3].innerHTML = arrCont[i - 1].DonGiaIn;

            vTable.childNodes[0].appendChild(newRow);
        }

        if (iLoai == 1) {
            //co don gia
            vTable2.rows[0].cells[0].width = 36;
            vTable2.rows[0].cells[1].width = 116;
            vTable2.rows[0].cells[2].width = 260;
            vTable2.rows[0].cells[3].width = 70;
            vTable2.rows[0].cells[4].width = 18;
        }
        else {
            //Khong co don gia
            vTable2.rows[0].deleteCell();
            vTable2.rows[0].cells[0].width = 36;
            vTable2.rows[0].cells[1].width = 116;
            vTable2.rows[0].cells[2].width = 330;
            vTable2.rows[0].cells[3].width = 18;
            vTable2.rows[0].cells[3].innerText = '';
        }

    }
    vTable.deleteRow(0);
    if (vTable.rows.length == 0)
        return;
    for (i = 0;i < vTable.rows[0].cells.length;i++)
        vTable.rows[0].cells[i].width = vTable2.rows[0].cells[i].width;

}

function genDVSDTable(tableID, table2) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var vTable2 = document.getElementById(table2);
    var oldRow = vTable.rows[0];
    var newRow;
    if (arrCont.length > 0) {
        for (i = 1;i <= arrCont.length;i++) {
            newRow = oldRow.cloneNode(true);
            newRow.childNodes[0].innerHTML = "" + i;
            newRow.childNodes[1].innerHTML = arrCont[i - 1].Ma;
            newRow.childNodes[2].innerHTML = arrCont[i - 1].Ten;
            vTable.childNodes[0].appendChild(newRow);
        }
    }
    vTable.deleteRow(0);
    if (vTable.rows.length == 0)
        return;
    for (i = 0;i < vTable.rows[0].cells.length;i++)
        vTable.rows[0].cells[i].width = vTable2.rows[0].cells[i].width;
}

function genTableKho(tableID, table2) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var vTable2 = document.getElementById(table2);
    var newRow;
    var newCell;
    var order = 1;

    if (arrCont.length > 0) {
        for (i = 1;i <= arrCont.length;i++) {
            newRow = vTable.insertRow(order);
            newCell = (newRow.insertCell());
            newCell.align = 'center';
            newCell.innerHTML = "" + i;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Makho;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Tenkho;
            newCell = (newRow.insertCell());
            newCell.align = 'center'
            newCell.innerHTML = "<input type='checkbox' name='SelectedID' class='checkBox' value=''>";
            newCell.height = 18;
            order++;
        }
    }
    vTable.deleteRow(0);
    if (vTable.rows.length == 0)
        return;
    for (i = 0;i < vTable.rows[0].cells.length;i++)
        vTable.rows[0].cells[i].width = vTable2.rows[0].cells[i].width;

}

function genTableLoaiAch(tableID, table2) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var vTable2 = document.getElementById(table2);
    var newRow;
    var newCell;
    var order = 1;

    if (arrCont.length > 0) {
        for (i = 1;i <= arrCont.length;i++) {
            newRow = vTable.insertRow(order);
            newCell = (newRow.insertCell());
            newCell.align = 'center';
            newCell.innerHTML = "" + i;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1][0];
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1][1];
            newCell = (newRow.insertCell());
            newCell.align = 'center'
            newCell.innerHTML = "<input type='checkbox' name='SelectedID' class='checkBox' value=''>";
            newCell.height = 18;
            order++;
        }
    }
    vTable.deleteRow(0);
    if (vTable.rows.length == 0)
        return;
    for (i = 0;i < vTable.rows[0].cells.length;i++)
        vTable.rows[0].cells[i].width = vTable2.rows[0].cells[i].width;

}

function genTableDVSD(tableID, table2) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var vTable2 = document.getElementById(table2);
    var newRow;
    var newCell;
    var order = 1;
    if (arrCont.length > 0) {
        if (arrCont[arrCont.length - 1].Ma == arrCont[arrCont.length - 1].Ten && arrCont[arrCont.length - 1].Ten == "0")
            vTable2.caption.innerHTML += "<input type='hidden' id='lay_Ma_Ten' value='Y'>";

        for (i = 1;i <= arrCont.length;i++) {
            if (arrCont[i - 1].Ma == arrCont[i - 1].Ten && arrCont[i - 1].Ten == "0")
                continue;
            newRow = vTable.insertRow(order);
            newCell = (newRow.insertCell());
            newCell.align = 'center';
            newCell.innerHTML = "" + i;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Ma;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Ten;
            newCell = (newRow.insertCell());
            newCell.align = 'center';
            newCell.innerHTML = "<input type='checkbox' name='SelectedID' class='checkBox' value=''>\n";
            newCell.height = 18;
            order++;
        }
    }
    vTable.deleteRow(0);
    if (vTable.rows.length == 0)
        return;
    for (i = 0;i < vTable.rows[0].cells.length;i++)
        vTable.rows[0].cells[i].width = vTable2.rows[0].cells[i].width;

}

function genTableAnchi(tableID, table2) {
    arrCont = dialogArguments;
    var vTable = document.getElementById(tableID);
    var vTable2 = document.getElementById(table2);
    var newRow;
    var newCell;
    var order = 1;
    if (arrCont.length > 0) {
        if (typeof (arrCont[0].Loai) != 'undefined' && arrCont[0].Loai == "CQT") {
            vTable2.caption.innerHTML = "<h4 style='text-align: center'><font size='4'>Ch?n Cơ quan thuế</font></h4>";
            vTable2.rows[0].cells[2].innerText = "Tên Cơ quan thuế";
        }
        /*	else if (typeof(arrCont[0].Loai) != 'undefined' && arrCont[0].Loai == "KHO")
		{
			vTable2.caption.innerHTML = "<h4 style='text-align: center'><font size='4'>Ch?n Kho</font></h4>";
			vTable2.rows[0].cells[2].innerText = "Tên Kho";
		}
	*/
        else {
            vTable2.caption.innerHTML = "<h4 style='text-align: center'><font size='4'>Ch?n Ấn chỉ</font></h4>";
            vTable2.rows[0].cells[2].innerText = "Tên Ấn chỉ";
        }

        if (arrCont[arrCont.length - 1].Ma == arrCont[arrCont.length - 1].Ten && arrCont[arrCont.length - 1].Ten == "0")
            vTable2.caption.innerHTML += "<input type='hidden' id='lay_Ma_Ten' value='Y'>";

        for (i = 1;i <= arrCont.length;i++) {
            if (arrCont[i - 1].Ma == arrCont[i - 1].Ten && arrCont[i - 1].Ten == "0")
                continue;
            newRow = vTable.insertRow(order);
            newCell = (newRow.insertCell());
            newCell.align = 'center';
            newCell.innerHTML = "" + i;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Ma;
            newCell = (newRow.insertCell());
            newCell.innerHTML = arrCont[i - 1].Ten;
            newCell = (newRow.insertCell());
            newCell.align = 'center';
            newCell.innerHTML = "<input type='checkbox' name='SelectedID' class='checkBox' value=''>\n";
            if (typeof (arrCont[0].Loai) != 'undefined' && arrCont[0].Loai == "ANCHI")
                newCell.innerHTML += "<input type='hidden' name='IdAnchi' value='" + arrCont[i - 1].ID + "'>";
            newCell.height = 18;
            order++;
        }
    }
    vTable.deleteRow(0);
    if (vTable.rows.length == 0)
        return;
    for (i = 0;i < vTable.rows[0].cells.length;i++)
        vTable.rows[0].cells[i].width = vTable2.rows[0].cells[i].width;

}

function parse(thing) {
    var values = thing.split("; ");
    var out = new Object;
    for (i = 0;i < values.length;i++) {
        var keyv = values[i].split('=');
        out[keyv[0]] = keyv[1];
    }
    return out;
}

function parseLov(thing) {
    var values = thing.split("; ");
    var out = new Object;
    for (i = 0;i < values.length;i++) {
        var keyv = values[i].split('=');
        out[keyv[0]] = keyv[1];
    }
    return out;
}

function combine(things) {
    var out = "";
    for (i in things) {
        if (out != '')
            out += '; ';
        out += i + "=" + things[i];
    }
    return out;
}

function combineAnchi(things) {
    var out = "";
    for (i in things) {
        if (out != '')
            out += '; ';
        out += i + "==" + things[i];
    }
    return out;
}

// code dung cho trang html lam lov
function HighLightTR(backColor, textColor) {
    //
    if (typeof (preEl) != 'undefined') {
        preEl.bgColor = orgBColor;
        try {
            ChangeTextColor(preEl, orgTColor);
        }
        catch (e) {
            ;
        }
    }
    //detect dong bi click
    var el = event.srcElement;
    el = el.parentElement;
    orgBColor = el.bgColor;
    orgTColor = el.style.color;
    el.bgColor = backColor;

    try {
        ChangeTextColor(el, textColor);
    }
    catch (e) {
        ;
    }
    preEl = el;
}

function ChangeTextColor(a_obj, a_color) {
    for (i = 0;i < a_obj.cells.length;i++) {
        a_obj.cells(i).style.color = a_color;
    }
}

function applyFunctionKho() {
    var socot = t1.rows[0].cells.length;// So phan tu tren mot Row trong bang T1
    var sohang = t1.rows.length;// So hang du lieu trong bang T1
    var checked = 0;// So phan tu du lieu duoc Check
    var notchecked = 0;// So phan tu khong duoc Check
    var i, j;// Bien thong the su dung cho viec lap
    var selectedId = document.getElementsByName("SelectedID");
    // Khoi tao cac gia tri ban dau
    for (i = 0;i < socot - 1;i++) {
        out['check' + i] = "";
        out['uncheck' + i] = "";
    }

    for (i = 0;i < sohang;i++) {
        // Truong hop hang du lieu Check
        if (selectedId[i].checked) {
            for (j = 0;j < socot - 1;j++) {
                if (j == 2)
                    out['check' + j] += trim(t1.cells[i * socot + j - 1].innerText) + ' - ' + trim(t1.cells[i * socot + j].innerText) + "\n";
                else 
                    out['check' + j] += trim(t1.cells[i * socot + j].innerText) + ",";
            }
            checked++;
        }
        else {
            for (j = 0;j < socot - 1;j++) {
                if (j == 2)
                    out['uncheck' + j] += trim(t1.cells[i * socot + j - 1].innerText) + ' - ' + trim(t1.cells[i * socot + j].innerText) + "\n";
                else 
                    out['uncheck' + j] += trim(t1.cells[i * socot + j].innerText) + ",";
            }
            notchecked++;
        }
    }

    if (checked > notchecked) {
        out['result'] = "notchecked";
    }
    else {
        out['result'] = "checked";
    }

    // Cat bo dau ';' nam o cuoi xau	
    if (checked > 0) {
        for (i = 0;i < socot - 1;i++) {
            out['check' + i] = out['check' + i].substring(0, out['check' + i].length - 1);
        }
    }
    // Cat bo dau ';' nam o cuoi xau
    if (notchecked > 0) {
        for (i = 0;i < socot - 1;i++) {
            out['uncheck' + i] = out['uncheck' + i].substring(0, out['uncheck' + i].length - 1);
        }
    }
    out['ok'] = 'true';

    window.returnValue = combine(out);
    window.close();
}

function applyFunctionAnchi() {
    var socot = t1.rows[0].cells.length;// So phan tu tren mot Row trong bang T1
    var sohang = t1.rows.length;// So hang du lieu trong bang T1
    var checked = 0;// So phan tu du lieu duoc Check
    var notchecked = 0;// So phan tu khong duoc Check
    var total = 0;
    var i, j;// Bien thong the su dung cho viec lap
    var selectedId = document.getElementsByName("SelectedID");
    var achId = document.getElementsByName("IdAnchi");
    var isMaTen = document.getElementById("lay_Ma_Ten");

    // Khoi tao cac gia tri ban dau
    out['check_Id'] = "";
    out['check_Ma'] = "";
    out['check_Ten'] = "";

    out['uncheck_Id'] = "";
    out['uncheck_Ma'] = "";
    out['uncheck_Ten'] = "";

    for (i = 0;i < sohang;i++) {
        // Truong hop hang du lieu Check
        if (selectedId[i].checked) {

            if (achId.length > 0)
                out['check_Id'] += trim(achId[i].value) + ",";
            out['check_Ma'] += trim(t1.cells[i * socot + 1].innerText) + ",";
            if (!isNull(isMaTen) && typeof (isMaTen) != 'undefined' && isMaTen.value == "Y")
                out['check_Ten'] += trim(t1.cells[i * socot + 1].innerText) + " ~ " + trim(t1.cells[i * socot + 2].innerText) + "\n";
            else 
                out['check_Ten'] += trim(t1.cells[i * socot + 2].innerText) + "\n";++checked;++total;
        }
        else {
            if (achId.length > 0)
                out['uncheck_Id'] += trim(achId[i].value) + ",";
            out['uncheck_Ma'] += trim(t1.cells[i * socot + 1].innerText) + ",";

            if (!isNull(isMaTen) && typeof (isMaTen) != 'undefined' && isMaTen.value == "Y")
                out['uncheck_Ten'] += trim(t1.cells[i * socot + 1].innerText) + " ~ " + trim(t1.cells[i * socot + 2].innerText) + "\n";
            else 
                out['uncheck_Ten'] += trim(t1.cells[i * socot + 2].innerText) + "\n";++notchecked;++total;
        }
    }

    if (checked == total || notchecked == total) {
        out['result'] = "null";
    }
    else if (checked > notchecked) {
        out['result'] = "notchecked";
    }
    else {
        out['result'] = "checked";
    }

    // Cat bo dau ';' nam o cuoi xau	
    if (checked > 0) {
        if (achId.length > 0)
            out['check_Id'] = out['check_Id'].substring(0, out['check_Id'].length - 1);
        out['check_Ma'] = out['check_Ma'].substring(0, out['check_Ma'].length - 1);
        out['check_Ten'] = out['check_Ten'].substring(0, out['check_Ten'].length - 1);
    }
    // Cat bo dau ';' nam o cuoi xau
    if (notchecked > 0) {
        if (achId.length > 0)
            out['uncheck_Id'] = out['uncheck_Id'].substring(0, out['uncheck_Id'].length - 1);
        out['uncheck_Ma'] = out['uncheck_Ma'].substring(0, out['uncheck_Ma'].length - 1);
        out['uncheck_Ten'] = out['uncheck_Ten'].substring(0, out['uncheck_Ten'].length - 1);
    }
    out['ok'] = 'true';

    window.returnValue = combineAnchi(out);
    window.close();
}

function applyFunctionDVSD() {
    var socot = t1.rows[0].cells.length;// So phan tu tren mot Row trong bang T1
    var sohang = t1.rows.length;// So hang du lieu trong bang T1
    var checked = 0;// So phan tu du lieu duoc Check
    var notchecked = 0;// So phan tu khong duoc Check
    var total = 0;
    var i, j;// Bien thong the su dung cho viec lap
    var selectedId = document.getElementsByName("SelectedID");
    var isMaTen = document.getElementById("lay_Ma_Ten");

    // Khoi tao cac gia tri ban dau
    out['check_Ma'] = "";
    out['check_Ten'] = "";

    out['uncheck_Ma'] = "";
    out['uncheck_Ten'] = "";

    for (i = 0;i < sohang;i++) {
        // truong hop hang du lieu check
        if (selectedId[i].checked) {
            out['check_Ma'] += trim(t1.cells[i * socot + 1].innerText) + ",";
            if (!isNull(isMaTen) && typeof (isMaTen) != 'undefined' && isMaTen.value == "Y")
                out['check_Ten'] += trim(t1.cells[i * socot + 1].innerText) + " ~ " + trim(t1.cells[i * socot + 2].innerText) + "\n";
            else 
                out['check_Ten'] += trim(t1.cells[i * socot + 2].innerText) + "\n";++checked;++total;
        }
        else {
            out['uncheck_Ma'] += trim(t1.cells[i * socot + 1].innerText) + ",";
            if (!isNull(isMaTen) && typeof (isMaTen) != 'undefined' && isMaTen.value == "Y")
                out['uncheck_Ten'] += trim(t1.cells[i * socot + 1].innerText) + " ~ " + trim(t1.cells[i * socot + 2].innerText) + "\n";
            else 
                out['uncheck_Ten'] += trim(t1.cells[i * socot + 2].innerText) + "\n";++notchecked;++total;
        }
    }

    if (checked == total || notchecked == total) {
        out['result'] = "null";
    }
    else if (checked > notchecked) {
        out['result'] = "notchecked";
    }
    else {
        out['result'] = "checked";
    }

    // Cat bo dau ';' nam o cuoi xau	
    if (checked > 0) {
        out['check_Ma'] = out['check_Ma'].substring(0, out['check_Ma'].length - 1);
        out['check_Ten'] = out['check_Ten'].substring(0, out['check_Ten'].length - 1);
    }
    // Cat bo dau ';' nam o cuoi xau
    if (notchecked > 0) {
        out['uncheck_Ma'] = out['uncheck_Ma'].substring(0, out['uncheck_Ma'].length - 1);
        out['uncheck_Ten'] = out['uncheck_Ten'].substring(0, out['uncheck_Ten'].length - 1);
    }
    out['ok'] = 'true';
    window.returnValue = combineAnchi(out);
    window.close();
}

function applyFunctionDVSDST10() {
    var socot = t1.rows[0].cells.length;// So phan tu tren mot Row trong bang T1
    var sohang = t1.rows.length;// So hang du lieu trong bang T1
    var checked = 0;// So phan tu du lieu duoc Check
    var notchecked = 0;// So phan tu khong duoc Check
    var total = 0;
    var i, j;// Bien thong the su dung cho viec lap
    var selectedId = document.getElementsByName("SelectedID");
    var isMaTen = document.getElementById("lay_Ma_Ten");

    // Khoi tao cac gia tri ban dau
    out['check_Ma'] = "";
    out['check_Ten'] = "";

    out['uncheck_Ma'] = "";
    out['uncheck_Ten'] = "";

    for (i = 0;i < sohang;i++) {
        // truong hop hang du lieu check
        if (selectedId[i].checked) {
            out['check_Ma'] += trim(t1.cells[i * socot + 1].innerText) + ",";
            if (!isNull(isMaTen) && typeof (isMaTen) != 'undefined' && isMaTen.value == "Y")
                out['check_Ten'] += trim(t1.cells[i * socot + 1].innerText) + " ~ " + trim(t1.cells[i * socot + 2].innerText) + "\n";
            else 
                out['check_Ten'] += trim(t1.cells[i * socot + 2].innerText) + "\n";++checked;++total;
        }
        else {
            out['uncheck_Ma'] += trim(t1.cells[i * socot + 1].innerText) + ",";
            if (!isNull(isMaTen) && typeof (isMaTen) != 'undefined' && isMaTen.value == "Y")
                out['uncheck_Ten'] += trim(t1.cells[i * socot + 1].innerText) + " ~ " + trim(t1.cells[i * socot + 2].innerText) + "\n";
            else 
                out['uncheck_Ten'] += trim(t1.cells[i * socot + 2].innerText) + "\n";++notchecked;++total;
        }
    }

    if ((checked == total) || (notchecked == total)) {
        out['result'] = "null";
    }
    else {
        out['result'] = "checked";
    }

    // Cat bo dau ';' nam o cuoi xau	
    if (checked > 0) {
        out['check_Ma'] = out['check_Ma'].substring(0, out['check_Ma'].length - 1);
        out['check_Ten'] = out['check_Ten'].substring(0, out['check_Ten'].length - 1);
    }
    // Cat bo dau ';' nam o cuoi xau
    /*
	if (notchecked>0) {
		out['uncheck_Ma'] = out['uncheck_Ma'].substring(0,out['uncheck_Ma'].length-1);
		out['uncheck_Ten'] = out['uncheck_Ten'].substring(0,out['uncheck_Ten'].length-1);
 	}
 	*/
    out['ok'] = 'true';
    window.returnValue = combineAnchi(out);
    window.close();
}

function applyFunction() {
    out['ok'] = 'true';
    window.returnValue = combine(out);
    window.close();
}

function cencelFunction() {
    out['ok'] = 'false';
    window.returnValue = combine(out);
    window.close();
}

function rowLovClick() {
    HighLightTR('#94ABEF', 'white');
    var el = event.srcElement;
    el = el.parentElement;
    var len = el.cells.length;
    for (var i = 0;i < len;i++) {
        out[i] = el.cells[i].innerText;
    }

}

function rowLovDlbClick() {
    applyFunction();
}

function addevent() {
    for (var i = 0;i < t1.rows.length;i++) {
        t1.rows[i].attachEvent("onclick", rowLovClick);
        t1.rows[i].attachEvent("ondblclick", rowLovDlbClick);
    }
}

function addTKEvent(tableId) {
    var table = document.getElementById(tableId);
    var i;
    for (i = 1;i < table.rows.length;i++) {
        table.rows[i].attachEvent("onclick", rowLovClick);
        table.rows[i].attachEvent("ondblclick", rowLovDlbClick);
    }
    if (typeof (table.rows[1]) != 'undefined') {
        var len = table.rows[1].cells.length;
        for (i = 0;i < len;i++) {
            out[i] = table.rows[1].cells[i].innerText;
        }
    }
    if (typeof (table.rows[1]) != 'undefined')
        table.rows[1].cells[0].click();
}

function MM_preloadImages() {
    //v3.0
    var d = document;
    if (d.images) {
        if (!d.MM_p)
            d.MM_p = new Array();
        var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
        for (i = 0;i < a.length;i++)
            if (a[i].indexOf("#") != 0) {
                d.MM_p[j] = new Image;
                d.MM_p[j++].src = a[i];
            }
    }
}

function MM_swapImgRestore() {
    //v3.0
    var i, x, a = document.MM_sr;
    for (i = 0;a && i < a.length && (x = a[i]) && x.oSrc;i++)
        x.src = x.oSrc;
}

function MM_findObj(n, d) {
    //v4.01
    var p, i, x;
    if (!d)
        d = document;
    if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
        d = parent.frames[n.substring(p + 1)].document;
        n = n.substring(0, p);
    }
    if (!(x = d[n]) && d.all)
        x = d.all[n];
    for (i = 0;!x && i < d.forms.length;i++)
        x = d.forms[i][n];
    for (i = 0;!x && d.layers && i < d.layers.length;i++)
        x = MM_findObj(n, d.layers[i].document);
    if (!x && d.getElementById)
        x = d.getElementById(n);
    return x;
}

function MM_swapImage() {
    //v3.0
    var i, j = 0, x, a = MM_swapImage.arguments;
    document.MM_sr = new Array;
    for (i = 0;i < (a.length - 2);i += 3)
        if ((x = MM_findObj(a[i])) != null) {
            document.MM_sr[j++] = x;
            if (!x.oSrc)
                x.oSrc = x.src;
            x.src = a[i + 2];
        }
}

function keyPress() {
    var e = event.srcElement;
    var arg = keyPress.arguments;
    if (event.ctrlKey) {
        switch (event.keyCode) {
            case 39:
            //phim sang phai
                key = 9;
                shift = 1;
                if (arg.length == 1)
                    tabKey(arg[0]);
                else 
                    tabKey(arg[0], agr[1]);
                break;
            case 37:
            //phim sang trai
                key = 9;
                shift =  - 1;
                if (arg.length == 1)
                    tabKey(arg[0]);
                else 
                    tabKey(arg[0], agr[1]);
                break;
            case 38:
            //phim len
                key = 9;
                shift =  - 2;
                if (arg.length == 1)
                    tabKey(arg[0]);
                else 
                    tabKey(arg[0], agr[1]);
                break;
            case 40:
            //phim xuong
                key = 9;
                shift = 2;
                if (arg.length == 1)
                    tabKey(arg[0]);
                else 
                    tabKey(arg[0], agr[1]);
                break;
            default :
                break;
        }
    }
    shift = 0;//dat lai gia tri
}

/**
 *Hàm tab thực hiện nhảy tab khi có sự kiện tab kh?i một phẩn tử
 *Tham số vào: danh sách tên các bảng
 *Sử dụng:
 */
function tabKey() {
    if (err == 'error') {
        err = '';
        return;
    }
    //	alert(key);
    if (key != 9)
        return;

    var arg = tabKey.arguments;
    var obj = new Object;
    var e = event.srcElement;//(key==9)?event.srcElement:srcEvent;
    var eTable = e;
    var row = e.parentElement.parentElement.rowIndex;
    var col = 0;
    var nTable;
    var nIndex;
    while (eTable.tagName != 'TABLE')
        eTable = eTable.parentElement;
    while (eTable.rows[row].cells[col].firstChild != e)
        col++;
    for (var i = 1;i <= arg.length;i++) {
        obj[i] = document.getElementById(arg[i - 1]);
        nTable = arg.length;
        if (eTable.id == arg[i - 1])
            nIndex = i;
    }
    var result = new Object;
    var type;
    if (key == 9)
        type = shift != 0 ? shift : (event.shiftKey ?  - 1 : 1);
    else 
        type = 0;

    if (type ==  - 2)
        row = row - 1;
    else if (type == 2)
        row = row + 1;

    if (row < 0 || row > getNumberRow(obj[1]) - 1)
        return;

    result = tabTable(obj, nTable, nIndex, row, col, type);
    if (!result['ok']) {
        if (result['endRow']) {
            if (typeof (noAddRow) != 'undefined' && noAddRow)
                return;
            autoFillRowData(obj, nTable, result['row'], true);
        }
        else if (result['row'] == row + 1) {
            if (typeof (isAutoFillRow) != 'undefined' && isAutoFillRow) {
                bl = autoFillRowData(obj, nTable, result['row'], false);
            }
            else if (typeof (isAddData) != 'undefined' && isAddData) {
                copyValue(obj, nTable, result['row']);
            }
        }
        return;
    }
    var bl = false;
    if (usedAddData && result['rowChange']) {
        if (typeof (isAutoFillRow) != 'undefined' && isAutoFillRow) {
            bl = autoFillRowData(obj, nTable, result['row'], false);
        }
        else if (typeof (isAddData) != 'undefined' && isAddData) {
            copyValue(obj, nTable, result['row']);
        }
    }
    if (!bl) {
        var eCell = result['cell'];
        if (eCell.type == 'text') {
            eCell.click();
            eCell.select();
        }
        else if (eCell.type != 'button')
            eCell.focus();
    }
    if (result['tableChange']) {
        if (arg.length > 1) {
            mainFrame.scrollLeft = (type !=  - 1 || !result['rowChange']) ? 0 : mainFrame.scrollWidth;
            topFrame.scrollLeft = mainFrame.scrollLeft;
        }
    }
    key = 0;
}

/**
 *Hàm getChild: lấy v? một phần tử con trong container
 *Tham số vào:
 *	pElement: phần tử chứa
 *	nIndex: index của phẩn tử con cần lấy
 *Tham số ra:
 *	trả v? đối tượng con
 *	trả v? null nếu không có phần tử con thứ index
 *
 *Sử dụng obj=getChild(pElement,nIndex)
 */
function getChild(pElement, nIndex) {
    var obj = pElement.all;
    var result = null;
    if (nIndex < obj.length && nIndex >= 0)
        if (obj[nIndex].tagName == 'INPUT' && obj[nIndex].type != 'hidden' || obj[nIndex].tagName == 'SELECT')
            result = obj[nIndex];
    return result;
}

/**
 *Hàm getNumberRow: trả lại số row hien của bảng
 */
function getNumberRow(pTable) {
    var rows = pTable.rows.length;
    var num = rows;
    var obj;
    for (var i = 0;i < rows;i++) {
        if (pTable.rows[i].innerHTML.indexOf("</TH>") !=  - 1)
            continue;
        obj = pTable.rows[i].cells[0].all;
        if (obj.length == 0)
            num = num - 1;
        else if (obj[0].tagName == 'INPUT' && obj[0].type == 'hidden')
            num = num - 1;
    }
    return num;
}

/**
 *Hàm copyValue: copy giá trị dòng trên xuống dòng dưới khi dòng dưới rỗng
 *Tham số vào:
 *	obj: đối tượng chứa các bảng
 *	nTable: số bảng
 *	rowIndex:dòng mới cần copy
 *Sử dụng: khi tab xuống dòng mới hoặc khi thêm dòng mới
 */
function copyValue(obj, nTable, row) {
    var vSeri = document.getElementsByName("theodoiXeri");
    var vLacMa = document.getElementsByName("lacMa");
    var vHThucIn = document.getElementsByName("hThucIn");
    var vEle, vEle1;
    var i, j;
    if (typeof (vSeri[curRow - StartRow]) == 'undefined' || isNull(vSeri[curRow - StartRow]))
        return;
    if (vSeri[curRow - StartRow].value == 'Y') {
        var isCopy = false;
        for (i = 1;i <= nTable;i++) {
            vEle = obj[i].rows[row].all;
            for (j = 0;j < vEle.length;j++) {
                if (vEle[j].tagName == "INPUT" && (vEle[j].type == "text" || vEle[j].type == "hidden") && vEle[j].value != "") {
                    /* vi Input TINHCHAT thuong xuyen co du lieu nen khong kiem tra voi INPUT nay*/
                    if (vEle[j].name != "TINHCHAT") {
                        isCopy = true;
                        break;
                    }
                }
            }
            if (isCopy)
                break;
        }
        if (!isCopy) {
            for (i = 1;i <= nTable;i++) {
                for (j = 0;j < obj[i].rows[row].cells.length;j++) {
                    //					obj[i].rows[row].cells[j].innerHTML=obj[i].rows[row-1].cells[j].innerHTML;
                    vEle = obj[i].rows[row].cells[j].all;
                    vEle1 = obj[i].rows[row - 1].cells[j].all;
                    for (var k = 0;k < vEle.length;k++) {
                        if (vEle[k].tagName == "INPUT" && (vEle[k].type == "text" || vEle[k].type == "hidden")) {
                            if (vEle[k].name == "maAnchi" || vEle[k].name == "tenAnchi" || vEle[k].name == "tenDVT" || vEle[k].name == "donGia" || vEle[k].name == 'kyHieu' || vEle[k].name == "theodoiXeri" || vEle[k].name == "IdAnchi" || vEle[k].name == "tyLe" || vEle[k].name == "soKyHieu" || vEle[k].name == "DVT" || vEle[k].name == "slgTinhAC" || vEle[k].name == "slgChuanAC" || vEle[k].name == "maDvt" || vEle[k].name == "tmpTyLe" || vEle[k].name == "lacMa" || vEle[k].name == "hThucIn") {
                                /*Neu Input TINHCHAT da co gia tri thi lay gtri cua chinh no*/
                                if (vEle[k].name == "TINHCHAT" && vEle[k] != "") {
                                    vEle[k].value = vEle[k].value;
                                }
                                else {
                                    vEle[k].value = vEle1[k].value;
                                }
                            }
                        }
                        if (vEle[k].type == 'text')
                            vEle[k].readOnly = vEle1[k].readOnly;
                        else if (vEle[k].type == 'select-one') {
                            vEle[k].selectedIndex = vEle1[k].selectedIndex;
                            vEle[k].disabled = vEle1[k].disabled;
                        }
                    }
                }
            }
        }
    }
}

/**
 *Hàm tabTable: nhảy tab(hoặc shift+tab hoặc mouse) trên các bảng và có b? qua ph
 *Tham số vào:
 *  	obj: đối tượng chứa các bảng
 *  	n: số bảng
 *	nIndex: index của bảng chứa sự kiện
 *	row: hàng có sự kiện (mỗi ô chỉ có 1 phẩn tử)
 *	col: cột có sự kiện
 *	type: 1: tab; -1: shift+tab ; 0: mouse,
 *Tham số ra:
 *	result['ok']: trạng thái thực hiện tab
 *	result['cell']: phần tử sẽ có focus
 result['row']: dong se co focus
 *	result['rowChange']: tab nhảy xuông dòng dưới hay cùng dòng với phần
 *						tử gây ra sự kiên tab
 *Sử dụng trong hàm tab:  result = tabTable(obj,nTable,nIndex,row,col,type);
 */
function tabTable(obj, nTable, nIndex, row, col, type) {
    var result = new Object;
    var eTable;
    var ofs = type;
    var lim = (ofs ==  - 1) ? 0 : 1;
    result['rowChange'] = false;
    result['tableChange'] = false;
    result['cell'] = obj[nIndex].rows[row].cells[col].firstChild;
    result['ok'] = false;
    result['row'] = row;
    result['endRow'] = false;
    if (type == 2 || type ==  - 2) {
        ofs = 0;
        if (type == 2)
            result['rowChange'] = true;
    }
    //khởi tạo phần tử ban đầu
    if (ofs != 0) {
        if (col * ofs < lim * (obj[nIndex].rows[row].cells.length - 1)) {
            eTable = obj[nIndex];
            col += ofs;
        }
        else {
            if ((ofs == 1 && nIndex == nTable) || (ofs ==  - 1 && nIndex == 1)) {
                eTable = obj[(ofs == 1) ? 1 : nTable];
                row += (ofs == 1) ? 1 :  - 1;
                if (row < 0 || row > getNumberRow(eTable) - 1) {
                    result['endRow'] = row > 0;
                    result['row'] = row;
                    return result;
                }
                result['rowChange'] = true;
                result['row'] = row;
            }
            else {
                nIndex += ofs;
                eTable = obj[nIndex];
            }
            col = (1 - lim) * (eTable.rows[row].cells.length - 1);
            if (nTable > 1)
                result['tableChange'] = true;
        }
    }
    else 
        eTable = obj[nIndex];
    //lặp tới các phần tử tiếp theo có b? qua readonly
    var eCell;
    while (true) {
        eCell = getChild(eTable.rows[row].cells[col], 0);
        if (isNull(eCell))
            return result;

        if (eCell.type == 'text' || eCell.type == 'select-one') {
            if (eCell.readOnly || eCell.disabled)
                col += (ofs !=  - 1) ? 1 :  - 1;
            else 
                break;
            if (col == lim * eTable.rows[row].cells.length) {
                if (((ofs !=  - 1) && (nIndex == nTable)) || ((ofs ==  - 1) && (nIndex == 1))) {
                    eTable = obj[(ofs !=  - 1) ? 1 : nTable];
                    row += (ofs !=  - 1) ? 1 :  - 1;
                    if (row < 0 || row > getNumberRow(eTable) - 1) {
                        result['endRow'] = row > 0;
                        result['row'] = row;
                        return result;
                    }
                    result['rowChange'] = true;
                    result['row'] = row;
                }
                else {
                    nIndex += (ofs !=  - 1) ? 1 :  - 1;
                    eTable = obj[nIndex];
                }
                col = (1 - lim) * (eTable.rows[row].cells.length - 1);
                if (nTable > 1)
                    result['tableChange'] = true;
            }
        }
        else 
            break;
    }
    result['cell'] = eCell;
    result['ok'] = true;
    return result;
}

/**
 *Hàm validTextLength: kiểm tra độ dài của text
 *Tham số:
 *	pElement: textbox
 *	size: kích thước cho phép
 *Sử dụng: onblur=validTextLength(this,size)
 */
function validTextLength(pElement, size) {
    if ((pElement.value == null) || (pElement.value == ""))
        return true;
    pElement.value = trim(pElement.value);
    if (pElement.value.length > size) {
        alert(msgInvalidSize);
        pElement.select();
        return false;
    }
    return true;
}

/**
 * Ham: updateTable()
 * Xoa bo nhung dong ma khong co cac truong ky hieu, tu so, den so, so luong
 */
function updateTable() {
    var vMaAnchi = document.getElementsByName('maAnchi');
    var vTheodoiXeri = document.getElementsByName('theodoiXeri');
    var vKyHieu = document.getElementsByName('kyHieu');
    var vTuSo = document.getElementsByName('tuSo');
    var vDenSo = document.getElementsByName('denSo');
    var vTuQuyen = document.getElementsByName('tuQuyen');
    var vDenQuyen = document.getElementsByName('denQuyen');
    var vSoLuong = document.getElementsByName('soLuong');
    for (var i = 0;i < vMaAnchi.length;i++) {
        if (!isNull(vMaAnchi[i].value)) {

            if (typeof (hopdongin) == 'undefined') {
                if ((vTheodoiXeri[i].value == 'Y' && isNull(vTuSo[i].value) && isNull(vDenSo[i].value) && isNull(vSoLuong[i].value) && (typeof (vTuQuyen[i]) == 'undefined' || typeof (vTuQuyen[i]) != 'undefined' && isNull(vTuQuyen[i].value)) && (typeof (vDenQuyen[i]) == 'undefined' || typeof (vDenQuyen[i]) != 'undefined' && isNull(vDenQuyen[i].value))) || (vTheodoiXeri[i].value != 'Y' && isNull(vSoLuong[i].value))) {
                    var vcells = cTable.rows[StartRow + i].cells;
                    //				curRow = StartRow+i;
                    //				deleterow();
                    for (var j = 0;j < vcells.length;j++)
                        resetElementValue(vcells[j], true);
                }
            }
            else {
                if ((vTheodoiXeri[i].value == 'Y' && isNull(vTuSo[i].value) && isNull(vDenSo[i].value) && (typeof (vTuQuyen[i]) == 'undefined' || typeof (vTuQuyen[i]) != 'undefined' && isNull(vTuQuyen[i].value)) && (typeof (vDenQuyen[i]) == 'undefined' || typeof (vDenQuyen[i]) != 'undefined' && isNull(vDenQuyen[i].value))) || (vTheodoiXeri[i].value != 'Y' && isNull(vSoLuong[i].value))) {
                    var vcells = cTable.rows[StartRow + i].cells;
                    //				curRow = StartRow+i;
                    //				deleterow();
                    for (var j = 0;j < vcells.length;j++)
                        resetElementValue(vcells[j], true);
                }
            }
        }
    }
}

function updateTable2() {
    var vMaAnchi = document.getElementsByName('maAnchi');
    for (var i = 0;i < vMaAnchi.length;i++) {
        if (!isNull(vMaAnchi[i].value)) {

            var vcells2 = cTable2.rows[StartRow + i].cells;
            var isnull = true;
            var j;
            for (j = 0;j < vcells2.length;j++)
                if (!isNull(vcells2[j].firstChild.value)) {
                    isnull = false;
                    break;
                }
            if (isnull) {
                var vcells = cTable.rows[StartRow + i].cells;
                for (j = 0;j < vcells.length;j++)
                    resetElementValue(vcells[j], true);

                for (j = 0;j < vcells2.length;j++)
                    resetElementValue(vcells2[j], true);

            }
        }
    }
}

/**
 * Tinh cac tham so
 */
function validTheoquyen() {
    var e = event.srcElement;
    var row = e.parentElement.parentElement.rowIndex - 1;
    if (typeof (started) != 'undefined')
        row++;
    var vMaAnchi = document.getElementsByName('maAnchi');
    var vKyHieu = document.getElementsByName('kyHieu');
    var vTuQuyen = document.getElementsByName('tuQuyen');
    var vDenQuyen = document.getElementsByName('denQuyen');
    var vTyLe = document.getElementsByName('tyLe');
    //alert("length - "+vMaAnchi.length);
    //tinh tu so, den so, so luong
    if (!isNull(vTyLe[row].value)) {

        var chuan, tinh;
        var strTL = (vTyLe[row].value).split('/');
        // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
        // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
        if (strTL.length = 2) {
            chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
            tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
        }
        else 
            return true;
        var tuso = ((parseInt(toNumber(vTuQuyen[row].value)) - 1)) * chuan / tinh + 1;
        var denso = parseInt(toNumber(vDenQuyen[row].value)) * chuan / tinh;
        if (e == vTuQuyen[row]) {
            if (tuso > 9999999999) {
                alert("Trư?ng Từ Số vượt quá giới hạn do nhập sai trư?ng Từ Quyển.\nHãy nhập lại trư?ng Từ Quyển!");
                vTuQuyen[row].focus();
                err = 'error';
                return false;
            }
        }
        else if (e == vDenQuyen[row]) {
            if (denso > 9999999999) {
                alert("Trư?ng ?ến Số vượt quá giới hạn do nhập sai trư?ng ?ến Quyển.\nHãy nhập lại trư?ng ?ến Quyển!");
                vDenQuyen[row].focus();
                err = 'error';
                return false;
            }
        }
        else if (!isNull(vMaAnchi[row].value) && !isNull(vKyHieu[row].value) && !isNull(vTuQuyen[row].value) && !isNull(vDenQuyen[row].value)) {
            if (tuso > 9999999999) {
                alert("Trư?ng Từ Số vượt quá giới hạn do nhập sai trư?ng Từ Quyển.\nHãy nhập lại trư?ng Từ Quyển!");
                vTuQuyen[row].focus();
                err = 'error';
                return false;
            }
            if (denso > 9999999999) {
                alert("Trư?ng ?ến Số vượt quá giới hạn do nhập sai trư?ng ?ến Quyển.\nHãy nhập lại trư?ng ?ến Quyển!");
                vDenQuyen[row].focus();
                err = 'error';
                return false;
            }

        }
    }
    return true;
}

function validTheoquyen1() {
    var e = event.srcElement;
    var row = e.parentElement.parentElement.rowIndex - 1;
    if (typeof (started) != 'undefined')
        row++;
    var vMaAnchi = document.getElementsByName('maAnchi');
    var vKyHieu = document.getElementsByName('kyHieu');
    var vTuQuyen = document.getElementsByName('tuQuyen');
    var vDenQuyen = document.getElementsByName('denQuyen');
    var vTuSo = document.getElementsByName('tuSo');
    var vDenSo = document.getElementsByName('denSo');
    var vTyLe = document.getElementsByName('tyLe');

    //tinh tu so, den so, so luong
    if (!isNull(vMaAnchi[row].value) && !isNull(vKyHieu[row].value) && !isNull(vTuQuyen[row].value) && !isNull(vDenQuyen[row].value)) {

        var chuan, tinh;
        var strTL = (vTyLe[row].value).split('/');
        // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
        // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
        if (strTL.length = 2) {
            chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
            tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
        }
        var tuso = ((parseInt(toNumber(vTuQuyen[row].value)) - 1)) * chuan / tinh + 1;
        if (tuso > 9999999999) {
            alert("Trư?ng Từ Số vượt quá giới hạn do nhập sai trư?ng Từ Quyển.\nHãy nhập lại trư?ng Từ Quyển!");
            vTuQuyen[row].focus();
            err = 'error';
            return false;
        }
        var denso = parseInt(toNumber(vDenQuyen[row].value)) * chuan / tinh;
        if (denso > 9999999999) {
            alert("Trư?ng ?ến Số vượt quá giới hạn do nhập sai trư?ng ?ến Quyển.\nHãy nhập lại trư?ng ?ến Quyển!");
            vDenQuyen[row].focus();
            err = 'error';
            return false;
        }
    }
    return true;
}

function tinh_soluong_theoquyen() {
    var e = event.srcElement;
    var row = e.parentElement.parentElement.rowIndex - 1;
    if (typeof (started) != 'undefined')
        row++;
    var vMaAnchi = document.getElementsByName('maAnchi');
    var vKyHieu = document.getElementsByName('kyHieu');
    var vTuQuyen = document.getElementsByName('tuQuyen');
    var vDenQuyen = document.getElementsByName('denQuyen');
    var vTuSo = document.getElementsByName('tuSo');
    var vDenSo = document.getElementsByName('denSo');
    var vTyLe = document.getElementsByName('tyLe');
    var vSoLuong = document.getElementsByName('soLuong');
    var vLoaiKho = document.getElementsByName('loaiKho');

    //tinh tu so, den so, so luong
    if (!isNull(vMaAnchi[row].value) && !isNull(vKyHieu[row].value) && !isNull(vTuQuyen[row].value) && !isNull(vDenQuyen[row].value)) {
        var chuan, tinh;
        var strTL = (vTyLe[row].value).split('/');
        // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
        // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
        if (strTL.length == 2) {
            chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
            tinh = parseInt(strTL[1]);// Lay so luong don vi tinh
        }
        vTuSo[row].value = ((parseInt(toNumber(vTuQuyen[row].value)) - 1)) * chuan / tinh + 1;
        vDenSo[row].value = parseInt(toNumber(vDenQuyen[row].value)) * chuan / tinh;
        if (typeof (captren) != 'undefined' || (typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0 && vLoaiKho[0].value == 'H'))
            vSoLuong[row].value = parseInt(toNumber(vDenSo[row].value)) - parseInt(toNumber(vTuSo[row].value)) + 1;
        else 
            vSoLuong[row].value = parseInt(toNumber(vDenQuyen[row].value)) - parseInt(toNumber(vTuQuyen[row].value)) + 1;

        vTuSo[row].value = toFormatNumber(vTuSo[row].value);
        vDenSo[row].value = toFormatNumber(vDenSo[row].value);
        vSoLuong[row].value = toFormatNumber(vSoLuong[row].value);
    }
    return true;
}

function tinh_soluong_theoso() {
    var e = event.srcElement;
    var row = e.parentElement.parentElement.rowIndex - 1;
    if (typeof (started) != 'undefined')
        row++;
    var vMaAnchi = document.getElementsByName('maAnchi');
    var vKyHieu = document.getElementsByName('kyHieu');
    var vTuSo = document.getElementsByName('tuSo');
    var vDenSo = document.getElementsByName('denSo');
    var vTuQuyen = document.getElementsByName('tuQuyen');
    var vDenQuyen = document.getElementsByName('denQuyen');
    var vTyLe = document.getElementsByName('tyLe');
    var vTmpTyLe = document.getElementsByName("tmpTyLe");
    var vSoLuong = document.getElementsByName('soLuong');
    var vLoaiKho = document.getElementsByName('loaiKho');

    if (!isNull(vMaAnchi[row].value) && !isNull(vKyHieu[row].value) && !isNull(vTuSo[row].value) && !isNull(vDenSo[row].value)) {
        var chuan, tinh;
        var tyle = vTmpTyLe.length > 0 ? vTmpTyLe[row].value : vTyLe[row].value;
        var strTL = tyle.split('/');

        // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
        // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
        if (strTL.length == 2) {
            chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
            tinh = parseInt(strTL[1]);// Lay so luong don vi tinh			
        }

        if (chuan != tinh && vTuQuyen.length > 0) {
            if (((parseInt(toNumber(vTuSo[row].value)) - 1) % (chuan / tinh) == 0 && parseInt(toNumber(vDenSo[row].value)) % (chuan / tinh) == 0) || typeof (isDVSD) != 'undefined') {
                vTuQuyen[row].value = toFormatNumber((parseInt(toNumber(vTuSo[row].value)) - 1) / (chuan / tinh) + 1);
                if (parseInt(toNumber(vDenSo[row].value)) % (chuan / tinh) == 0)
                    vDenQuyen[row].value = toFormatNumber(parseInt(toNumber(vDenSo[row].value)) / (chuan / tinh));
                else 
                    vDenQuyen[row].value = toFormatNumber(parseInt(toNumber(vDenSo[row].value)) / (chuan / tinh) + 1);

            }
            else {
                vTuQuyen[row].value = "";
                vDenQuyen[row].value = "";

            }
        }

        if (chuan != tinh && vTuQuyen.length > 0 && (typeof (captren) != 'undefined' || (typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0 && (vLoaiKho[0].value == 'H' || vLoaiKho[0].value == '')))) {
            if (typeof (vLoaiKho) != 'undefined' && vLoaiKho.length > 0 && vLoaiKho[0].value == '')
                vSoLuong[row].value = parseInt(toNumber(vDenQuyen[row].value)) - parseInt(toNumber(vTuQuyen[row].value)) + 1;
            else 
                vSoLuong[row].value = parseInt(toNumber(vDenSo[row].value)) - parseInt(toNumber(vTuSo[row].value)) + 1;

        }
        else {
            vSoLuong[row].value = (parseInt(toNumber(vDenSo[row].value)) - parseInt(toNumber(vTuSo[row].value)) + 1) * tinh / chuan;
        }

        vSoLuong[row].value = toFormatNumber(vSoLuong[row].value);
        if (vSoLuong[row].value == '0')
            vSoLuong[row].value = '';
    }
    return true;
}

/**
 * Ham autoFillRowData: tu dong dien du lieu vao cac row
 */
function autoFillRowData(obj, nTable, row, isEnd) {
    var vSeri = document.getElementsByName("theodoiXeri");
    var vMaAC = document.getElementsByName("maAnchi");
    var vTuso = document.getElementsByName("tuSo");
    var vDenso = document.getElementsByName("denSo");
    var vEle, vEle1;
    var i, j, n;
    if (typeof (vSeri[row - StartRow - 2]) == 'undefined' || isNull(vSeri[row - StartRow - 2].value)) {
        if (typeof (vSeri[row - StartRow - 1]) != 'undefined' && !isNull(vSeri[row - StartRow - 1].value)) {
            if (isEnd) {
                if (nTable > 1)
                    addrow2();
                else 
                    addrow();
            }
            else 
                copyValue(obj, nTable, row);
        }
        return false;
    }
    if (typeof (vSeri[curRow - StartRow]) == 'undefined' || isNull(vSeri[curRow - StartRow].value))
        return false;
    if (isNull(vMaAC[row - StartRow - 1].value) || isNull(vMaAC[row - StartRow - 2].value) || (vMaAC[row - StartRow - 1].value != vMaAC[row - StartRow - 2].value) || !isNull(vTuso[row - StartRow - 2].value) || !isNull(vDenso[row - StartRow - 2].value)) {
        if (isEnd) {
            if (nTable > 1)
                addrow2();
            else 
                addrow();
        }
        else 
            copyValue(obj, nTable, row);
        return false;
    }
    if (vSeri[curRow - StartRow].value == 'Y') {
        var isCopy = false;
        for (i = 1;i <= nTable;i++) {
            /* Kiem tra xem co phai la dong cuoi ko */
            if (isEnd) {
                if (nTable > 1)
                    addrow2();
                else 
                    addrow();
            }
            /* ~*/
            vEle = obj[i].rows[row].all;
            for (j = 0;j < vEle.length;j++) {
                if (vEle[i].tagName == "INPUT" && (vEle[i].type == "text" || vEle[i].type == "hidden") && vEle[i].value != "") {
                    if (vEle[i].name != "TINHCHAT") {
                        isCopy = true;
                        break;
                    }
                }
            }
            if (isCopy)
                break;
        }
        if (!isCopy) {
            var rs = new Object;
            var vKyhieu = document.getElementsByName("kyHieu");
            var vLacMa = document.getElementsByName("lacMa");
            var vHThucIn = document.getElementsByName("hThucIn");
            var seri1 = vKyhieu[row - StartRow - 2].value;
            var seri2 = vKyhieu[row - StartRow - 1].value;
            if ((vLacMa[row - StartRow - 2] != null) && vLacMa[row - StartRow - 2].value == '01' && vHThucIn[row - StartRow - 2].value == '02') {
                rs = compareSeriNew(seri1, seri2, row - StartRow, vMaAC[row - StartRow - 2].value);
            }
            else {
                rs = compareSeri(seri1, seri2, row - StartRow, vMaAC[row - StartRow - 2].value);
            }
            if (rs['result'] < 0) {
                if (isEnd) {
                    if (nTable > 1)
                        addrow2();
                    else 
                        addrow();
                }
                else 
                    copyValue(obj, nTable, row);
                return false;
            }
            var nRow = rs['result'] - (getNumberRow(obj[1]) - row) - 2;//so row can them vao			
            if (nRow > 0)//them vao cho du so dong
                for (i = 0;i < nRow;i++)
                    if (nTable == 1)
                        addrow();
                    else 
                        addrow2();
            fillRowData(row - StartRow - 2);
            var rowData;
            var s = '';
            for (i = 1;i <= nTable;i++) {
                rowData = obj[i].rows[row - 2];
                for (var n = 0;n < rs['result'];n++) {
                    for (j = 0;j < obj[i].rows[row].cells.length;j++) {
                        vEle = obj[i].rows[row - 2 + n].cells[j].all;
                        vEle1 = rowData.cells[j].all;
                        for (var k = 0;k < vEle.length;k++) {
                            if (vEle[k].tagName == "INPUT" && (vEle[k].type == "text" || vEle[k].type == "hidden")) {
                                if (vEle[k].name == "kyHieu") {
                                    vEle[k].value = rs["\'" + n + "\'"];
                                    s += ':' + rs["\'" + n + "\'"];
                                }
                                else 
                                    vEle[k].value = vEle1[k].value;
                            }
                            if (vEle[k].type == 'text')
                                vEle[k].readOnly = vEle1[k].readOnly;
                            else if (vEle[k].type == 'select-one') {
                                vEle[k].selectedIndex = vEle1[k].selectedIndex;
                                vEle[k].disabled = vEle1[k].disabled;
                            }
                        }
                    }
                }
            }
            //dat focus			
            var focused = false;
            /* Tan dung ham tinh() tren trang khi onblur bang cach focus lan luot tung dong*/
            for (i = 0;i < (row - 3 + rs['result']);i++) {
                /*Co tac dung khi ham onBlur cua cell 7 goi ham tinh lai dong*/
                vEle = obj[1].rows[row - 3 - i + rs['result']].cells[7].firstChild;
                if (vEle.tagName == 'INPUT' && vEle.type != 'hidden') {
                    if (vEle.type == 'text')
                        vEle.select();
                    else 
                        vEle.focus();
                }
            }
            /* Dat focus ve phan tu tren dong cuoi*/
            for (i = 0;i < obj[1].rows[row - 3 + rs['result']].cells.length;i++) {
                vEle = obj[1].rows[row - 3 + rs['result']].cells[0].firstChild;
                if (!focused && vEle.tagName == 'INPUT' && vEle.type != 'hidden') {
                    if (vEle.type == 'text')
                        vEle.select();
                    else 
                        vEle.focus();

                    resetRow(curRow);
                    setCurRow(row - 3 + rs['result']);
                    curRow = row - 3 + rs['result'];
                    //vEle.click();
                    focused = true;
                }
            }
            return true;
        }
        return false;
    }
    return false
}

/** Ham getKyhieus: tra ve chuoi chua truong ky hieu (tru cac ky hieu dong row1 va row2)
 */
function getKyhieus(row1, row2, maAC) {
    var vKyHieu = document.getElementsByName("kyHieu");
    var vMaAC = document.getElementsByName("maAnchi");
    var sKyHieus = '';
    for (var i = 0;i < vKyHieu.length;i++) {
        if (!isNull(vKyHieu[i].value) && i != row1 && i != row2 && vMaAC[i].value == maAC)
            sKyHieus += vKyHieu[i].value + ";";
    }
    if (!isNull(sKyHieus))
        sKyHieus = sKyHieus.substr(0, sKyHieus.length - 1);
    return sKyHieus;
}

/**
 * Ham compareSeri: tra ve ket qua so sanh giua 2 xau ky hieu
 * Tham so:
 *	seri1, seri2: hai xau ky hieu can so sanh
 * Ket qua tra ve: la mot Object
 *	rs['result']=-1: seri1> seri2
 *	rs['result']=n: seri1<seri2 va khoang cach giua seri1 va seri2 la n
 *	khi do cac chuoi o giua se la rs['1'], rs['2'], ... , rs['n']
 * vi du: AA/2003 < AC/2003 la 2 se co 2 chuoi la rs['1']=AB/2003 va rs['2']=AC/2003
 hoac AB/2003 < AA/2003 ket qua -1
 * su dung n=compareSeri(seri1, seri2)
 */

function compareSeri(seri1, seri2, row, maAC) {
    var strSeri = "ABCDEGHKLMNPQRSTUVXY";
    var indx1 = strSeri.indexOf(seri1.substr(0, 1).toUpperCase());
    var indx2 = strSeri.indexOf(seri1.substr(1, 1).toUpperCase());
    var indy1 = strSeri.indexOf(seri2.substr(0, 1).toUpperCase());
    var indy2 = strSeri.indexOf(seri2.substr(1, 1).toUpperCase());
    var strEx = seri1.substr(2, seri1.length - 2).toUpperCase();
    var sKyhieus = getKyhieus(row - 1, row - 2, maAC);
    var kyhieu = '';
    var rs = new Object();
    var n = (indy1 - indx1) * strSeri.length + (indy2 - indx2);
    if (n <= 0) {
        rs['result'] =  - 1;
        return rs;
    }
    var i = indx1;
    var j = indx2;
    var k = 0;
    while ((indy1 - i) * strSeri.length + (indy2 - j) >= 0) {
        kyhieu = strSeri.charAt(i) + strSeri.charAt(j) + strEx;
        //alert(kyhieu);
        if (isNull(sKyhieus) || sKyhieus.indexOf(kyhieu) ==  - 1) {
            rs["\'" + k + "\'"] = kyhieu;
            k++;
            //			alert(kyhieu);
        }
        j++;
        if (j == strSeri.length) {
            j = 0;
            i++;
        }
    }
    rs['result'] = k;
    return rs;
}

function compareSeriNew(seri1, seri2, row, maAC) {
    var strSeri = "ABCDEGHKLMNPQRSTUVXY";
    var indx1 = strSeri.indexOf(seri1.substr(2, 1).toUpperCase());
    var indx2 = strSeri.indexOf(seri1.substr(3, 1).toUpperCase());
    var preStr = seri1.substr(0, 2);
    //alert(seri1.substr(2,1).toUpperCase());
    //alert(indx1);
    var indy1 = strSeri.indexOf(seri2.substr(2, 1).toUpperCase());
    var indy2 = strSeri.indexOf(seri2.substr(3, 1).toUpperCase());
    var strEx = seri1.substr(4, seri1.length - 4).toUpperCase();
    var sKyhieus = getKyhieus(row - 1, row - 2, maAC);
    var kyhieu = '';
    var rs = new Object();
    var n = (indy1 - indx1) * strSeri.length + (indy2 - indx2);
    if (n <= 0) {
        rs['result'] =  - 1;
        return rs;
    }
    var i = indx1;
    var j = indx2;
    var k = 0;
    while ((indy1 - i) * strSeri.length + (indy2 - j) >= 0) {
        kyhieu = preStr + strSeri.charAt(i) + strSeri.charAt(j) + strEx;
        if (isNull(sKyhieus) || sKyhieus.indexOf(kyhieu) ==  - 1) {
            rs["\'" + k + "\'"] = kyhieu;
            k++;
            //			alert(kyhieu);
        }
        j++;
        if (j == strSeri.length) {
            j = 0;
            i++;
        }
    }
    rs['result'] = k;
    return rs;
}

/** Ham fillRowData: dien du lieu vao cac truong tuso, denso, tuquyen, denquyen, dongia, soluong
 * Tham so:	row - dong dien du lieu
 */
function fillRowData(row) {
    var vTyLe = document.getElementsByName("tyLe");
    var vDonGia = document.getElementsByName("donGia");
    var vSlgTinh = document.getElementsByName("slgTinhAC");
    var vSlgChuan = document.getElementsByName("slgChuanAC");
    var vSoKyHieu = document.getElementsByName("soKyHieu");
    var vTuQuyen = document.getElementsByName("tuQuyen");
    var vDenQuyen = document.getElementsByName("denQuyen");
    var vTuSo = document.getElementsByName("tuSo");
    var vDenSo = document.getElementsByName("denSo");
    var vSoLuong = document.getElementsByName("soLuong");
    var vSoTien = document.getElementsByName("soTien");
    var chuan, tinh, strTL;

    if (typeof (vTyLe[row]) != 'undefined') {
        strTL = (vTyLe[row].value).split('/');
        // Gia tri cua item pTyle co dang: xxxx/xxxx.xxxxxx
        // Duoc hieu la: soluong dvi chuan/soluong dvi tinh.ID cua dvi tinh
        if (strTL.length = 2) {
            chuan = parseInt(strTL[0]);// Lay so luong don vi chuan
            tinh = parseInt(strTL[1]);// Lay so luong don vi tinh			
        }
    }
    else {
        chuan = parseInt(vSlgChuan[row].value);
        tinh = parseInt(vSlgTinh[row].value);
    }
    //	alert(chuan+' '+tinh+' '+vSoKyHieu[row].value);
    vTuSo[row].value = "1";
    vDenSo[row].value = toFormatNumber(toNumber(vSoKyHieu[row].value));//vSoKyHieu[row].value;		
    if (typeof (vTuQuyen[row]) != 'undefined' && chuan != tinh) {
        vTuQuyen[row].value = "1";
        vDenQuyen[row].value = toFormatNumber(parseInt(toNumber(vDenSo[row].value)) * tinh / chuan);
        if (parseInt(parseInt(toNumber(vDenSo[row].value)) % (chuan / tinh)) != 0) {
            vDenQuyen[row].value = toFormatNumber(parseInt(toNumber(vDenQuyen[row].value)) + 1);
        }
        vSoLuong[row].value = vDenQuyen[row].value;
    }
    else {

        //		hiepvx: dong doan nay vi loi dvt tren giao dien khi tu dong sinh dong
        /* ---- */
        /*		if (typeof(vTuQuyen[row])!='undefined')
		{
			vTuQuyen[row].value = 1;
			vDenQuyen[row].value = toFormatNumber(parseInt(toNumber(vDenSo[row].value))* tinh/chuan);
			if (parseInt(parseInt(toNumber(vDenSo[row].value))%(chuan/tinh)) != 0)
	    	{
				vDenQuyen[row].value = toFormatNumber(parseInt(toNumber(vDenQuyen[row].value)) + 1);			   	    	
			}
		}
*/
        /*-------*/
        vSoLuong[row].value = vDenSo[row].value;
    }

    if (typeof (vSoTien[row]) != 'undefined' && vSoTien[row].type == 'text') {
        vSoTien[row].value = parseFloat(toNumber(vSoLuong[row].value)) * parseFloat(toNumber(vDonGia[row].value));
        vSoTien[row].value = toFormatNumberDe(vSoTien[row].value, 3);
    }
}

function synchronize(text, combo, direction) {
    var selectedLabel = '';
    var kq = false;
    var i = 0;
    if (direction == 1)//text -> combox
    {
        if (isNull(text.value)) {
            combo.selectedIndex = 0;
            return;
        }
        while (i < combo.length) {
            selectedLabel = combo.options[i].text;
            selectedLabel = selectedLabel.substr(0, selectedLabel.indexOf(" - "));
            if (selectedLabel == text.value) {
                kq = true;
                break;
            }
            i++;
        }
        if (kq)
            combo.selectedIndex = i;
        else {
            text.select();
            alert("Mã đơn vị nhận ấn chỉ không hợp lệ");
        }
    }
    else if (direction == 2) {
        //combox -> text
        selectedLabel = combo.options[combo.selectedIndex].text;
        if (isNull(selectedLabel))
            text.value = '';
        else 
            text.value = selectedLabel.substr(0, selectedLabel.indexOf(" - "));
    }
}

function updateDataTable(start, tableId) {
    var table = document.getElementById(tableId);
    var vMaAnchi = document.getElementsByName("maAnchi");
    var nHasData = 0;//so dong co du lieu
    var nRowData = 10;//so dong hien thi khi khong co du lieu
    var nExRow = 4;//so dong hien thi them khi co du lieu
    var nCutRow = 0;//cat tu dong
    for (i = 0;i < vMaAnchi.length;i++)
        if (!isNull(vMaAnchi[i].value)) {
            nHasData++;
        }
    if (nHasData == 0)
        nCutRow = start + nRowData;
    else 
        nCutRow = start + nHasData + nExRow;
    var s = window.name;
    //alert(s);
    if (s != 'false')
        table.innerHTML = s;

    //	nCutRow = sodong;
    //	while (nCutRow<table.rows.length)			
    //		table.deleteRow(nCutRow);
    //	alert(window.name);
}

/**
 Ham shoLovDm: hien thi danh muc an chi, co quan thue, kho
 Thamso: iLoaiLov: 1 - Lov Co quan thue
 2 - Lov Anchi
 */
function showLovDm(iLoaiLov) {
    var result;

    if (iLoaiLov == 1)//Lov Co quan thue
        result = window.showModalDialog('lovDMAnchi.html', arrCqtContent, 'center:yes;resizable:no;status:no;dialogWidth:460px;dialogHeight:540px');
    else // Lov Anchi
        result = window.showModalDialog('lovDMAnchi.html', arrAchContent, 'center:yes;resizable:no;status:no;dialogWidth:460px;dialogHeight:540px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parseDLDm(result);

    if (vLovVal['ok'] == 'true') {
        if (iLoaiLov == 1)//Coquanthue
        {
            if (vLovVal['result'] == "null") {
                document.forms[0].P_MA_CQT1.value = "";
                document.forms[0].P_CQT_LIST.value = "";
                document.forms[0].P_NOT_CQT.value = "";
            }
            else if (vLovVal['result'] == "checked") {
                document.forms[0].P_MA_CQT1.value = vLovVal['check_Ma'];
                document.forms[0].P_CQT_LIST.value = vLovVal['check_Ten'];
                document.forms[0].P_NOT_CQT.value = "N";
            }
            else {
                document.forms[0].P_MA_CQT1.value = vLovVal['uncheck_Ma'];
                document.forms[0].P_CQT_LIST.value = vLovVal['uncheck_Ten'];
                document.forms[0].P_NOT_CQT.value = "Y";
            }
        }
        else //Anchi
        {
            if (vLovVal['result'] == "null") {
                document.forms[0].P_ACH_ID.value = "";
                document.forms[0].P_ACH_MA.value = "";
                document.forms[0].P_ACH_LIST.value = "";
                if (typeof (document.forms[0].P_HTHI_ACH) != 'undefined')
                    document.forms[0].P_HTHI_ACH.value = "";
                if (typeof (document.forms[0].P_NOT_ACH) != 'undefined')
                    document.forms[0].P_NOT_ACH.value = "";
            }
            else if (vLovVal['result'] == "checked") {
                document.forms[0].P_ACH_ID.value = vLovVal['check_Id'];
                document.forms[0].P_ACH_MA.value = vLovVal['check_Ma'];
                document.forms[0].P_ACH_LIST.value = vLovVal['check_Ten'];
                if (typeof (document.forms[0].P_HTHI_ACH) != 'undefined')
                    document.forms[0].P_HTHI_ACH.value = vLovVal['check_Ten'];
                if (typeof (document.forms[0].P_NOT_ACH) != 'undefined')
                    document.forms[0].P_NOT_ACH.value = "N";
            }
            else {
                document.forms[0].P_ACH_ID.value = vLovVal['uncheck_Id'];
                document.forms[0].P_ACH_MA.value = vLovVal['uncheck_Ma'];
                document.forms[0].P_ACH_LIST.value = vLovVal['check_Ten'];//vLovVal['uncheck_Ten'];
                if (typeof (document.forms[0].P_HTHI_ACH) != 'undefined')
                    document.forms[0].P_HTHI_ACH.value = vLovVal['check_Ten'];
                if (typeof (document.forms[0].P_NOT_ACH) != 'undefined')
                    document.forms[0].P_NOT_ACH.value = "Y";
            }

        }
    }
    return 0;

}

function showLovDmDVSD(iLoaiLov) {
    var result;
    if (iLoaiLov == 2)// Lov DVSD
        result = window.showModalDialog('lovDMDVSD.html', arrDVSDContent, 'center:yes;resizable:no;status:no;dialogWidth:460px;dialogHeight:540px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parseDLDm(result);

    if (vLovVal['ok'] == 'true') {
        if (iLoaiLov == 2)// DVSD 
        {
            if (vLovVal['result'] == "null") {
                document.forms[0].P_MA_DVSD.value = "";
                document.forms[0].P_DVSD_LIST.value = "";
                document.forms[0].P_NOT_DVSD.value = "";
            }
            else if (vLovVal['result'] == "checked") {
                document.forms[0].P_MA_DVSD.value = vLovVal['check_Ma'];
                document.forms[0].P_DVSD_LIST.value = vLovVal['check_Ten'];
                document.forms[0].P_NOT_DVSD.value = "N";
            }
            else {
                document.forms[0].P_MA_DVSD.value = vLovVal['uncheck_Ma'];
                document.forms[0].P_DVSD_LIST.value = vLovVal['uncheck_Ten'];
                document.forms[0].P_NOT_DVSD.value = "Y";
            }
        }
    }
    return 0;
}

function showLovDmDVSDST10(iLoaiLov) {
    var result;
    if (iLoaiLov == 2)// Lov DVSD
        result = window.showModalDialog('lovDMDVSDST10.html', arrDVSDContent, 'center:yes;resizable:no;status:no;dialogWidth:460px;dialogHeight:540px');
    if (typeof (result) == 'undefined')
        return  - 1;

    var vLovVal = parseDLDm(result);

    if (vLovVal['ok'] == 'true') {
        if (iLoaiLov == 2)// DVSD 
        {
            if (vLovVal['result'] == "null") {
                document.forms[0].P_MA_DVSD.value = "";
                document.forms[0].P_DVSD_LIST.value = "";
                document.forms[0].P_NOT_DVSD.value = "";
            }
            else if (vLovVal['result'] == "checked") {
                document.forms[0].P_MA_DVSD.value = vLovVal['check_Ma'];
                document.forms[0].P_DVSD_LIST.value = vLovVal['check_Ten'];
                document.forms[0].P_NOT_DVSD.value = "N";
            }
            else {
                document.forms[0].P_MA_DVSD.value = vLovVal['uncheck_Ma'];
                document.forms[0].P_DVSD_LIST.value = vLovVal['uncheck_Ten'];
                document.forms[0].P_NOT_DVSD.value = "Y";
            }
        }
    }
    return 0;
}

function parseDLDm(thing) {
    var values = thing.split("; ");
    var out = new Object;
    for (i = 0;i < values.length;i++) {
        var keyv = values[i].split('==');
        out[keyv[0]] = keyv[1];
    }

    return out;
}

/*
*	Ham displayError: hien thi loi nhap xuat
*	Param: iLoai: 1 - Loi Nhap; 2 - Loi Xuat	
*		   iStart: dong bat dau chua du lieu
*/
function displayError(iLoai, iStart) {
    var vRowsErr = document.getElementsByName("RowErrors");
    if (vRowsErr.length > 0 && !isNull(vRowsErr[0].value))//co loi
    {
        var sRowsErr = vRowsErr[0].value;
        var msg = "";
        var sErr = "";
        var arrayErr = sRowsErr.split(";");
        var rows = new Array();
        var i = 0;

        // Lay cac dong loi
        for (i = 0;i < arrayErr.length;i++) {
            rows[i] = parseInt(arrayErr[i]);
            sErr += (rows[i] + 1) + "; ";
        }

        if (iLoai == 1)//Loi Nhap
            msg += "Dữ liệu nhập đã có trong tồn hoặc đang trên phiếu nhập\n";
        else if (iLoai == 2)//Loi Xuat
            msg += "Dữ liệu xuất không có trong tồn, đang trên phiếu xuất\n";
        else if (iLoai == 3)//Nhap tu nha in
            msg += "Dữ liệu xuất không có trong hợp đồng hoặc đã có trong kho\n";
        else if (iLoai == 4)//Nhap Dieu chinh
            msg += "Dữ liệu nhập không có trong phiếu gốc\n";
        msg += "Các dòng lỗi: " + sErr;
        alert(msg);

        // Set mau loi
        for (i = 0;i < rows.length;i++) {
            setColor(rows[i] + iStart, '#FF0000');
        }
    }
}

function displayErrorS(iStart) {
    var iLoai = document.getElementsByName("KindError").item(0);
    //alert(iLoai.value);
    var vRowsErr = document.getElementsByName("RowErrors");
    if (vRowsErr.length > 0 && !isNull(vRowsErr[0].value)) {
        // Co loi
        var sRowsErr = vRowsErr[0].value;
        var msg = "";
        var sErr = "";
        var arrayErr = sRowsErr.split(";");
        var rows = new Array();
        var i = 0;

        // Lay cac dong loi
        for (i = 0;i < arrayErr.length;i++) {
            rows[i] = parseInt(arrayErr[i]);
            sErr += (rows[i] + 1) + "; ";
        }

        if (iLoai.value == 1)//Loi Nhap
            msg += "Dữ liệu nhập đã có trong tồn hoặc đang trên phiếu nhập\n";
        //"Dữ liệu nhập đã có trong tồn hoặc đang trên phiếu nhập\n";
        else if (iLoai.value == 2)//Loi Xuat
            msg += "Dữ liệu xuất không có trong tồn hoặc đang trên phiếu xuất\n";
        else if (iLoai.value == 3)//Nhap tu nha in
            msg += "Dữ liệu xuất không có trong hợp đồng hoặc đã có trong kho\n";
        else if (iLoai.value == 4)//Nhap Dieu chinh
            msg += "Dữ liệu nhập không có trong phiếu gốc\n";
        //"Dữ liệu nhập không có trong phiếu gốc\n";
        msg += "Các dòng lỗi: " + sErr;
        alert(msg);

        // Set mau loi
        for (i = 0;i < rows.length;i++) {
            setColor(rows[i] + iStart, '#FF0000');
        }
    }
}

function displayErrorTonDVSD(iStart) {
    var iLoai = document.getElementsByName("KindError").item(0);
    var vRowsErr = document.getElementsByName("RowErrors");
    if (vRowsErr.length > 0 && !isNull(vRowsErr[0].value)) {
        // Co loi
        var sRowsErr = vRowsErr[0].value;
        var msg = "";
        var sErr = "";
        var arrayErr = sRowsErr.split(";");
        var rows = new Array();
        var i = 0;
        // Lay cac dong loi
        for (i = 0;i < arrayErr.length;i++) {
            rows[i] = parseInt(arrayErr[i]);
            sErr += (rows[i] + 1) + "; ";
        }

        if (iLoai.value == 1)//Loi Ton
            msg += "Dữ liệu nhập phải nh? hơn trong tồn đơn vị sử dụng số lượng\n";
        msg += "Các dòng lỗi: " + sErr;
        alert(msg);

        // Set mau loi
        for (i = 0;i < rows.length;i++) {
            setColor(rows[i] + iStart, '#FF0000');
        }
    }
}

/**
 *
 */
function ktraSoPhieu(dataField) {
    var arrStandard = new Array();
    var arrData = new Array();

    arrStandard[0] = "ABCDEFGHIJKLMNOPQRSTUVXY";
    arrStandard[1] = "0123456789";
    arrStandard[2] = "0123456789";

    if ((dataField.value == null) || (dataField.value == ""))
        return true;
    dataField.value = trim(dataField.value);
    dataField.value = (dataField.value).toUpperCase();

    if (dataField.value.indexOf("-") ==  - 1 || dataField.value.indexOf("/") ==  - 1 || dataField.value.indexOf("-") != dataField.value.lastIndexOf("-") || dataField.value.indexOf("/") != dataField.value.lastIndexOf("/") || dataField.value.indexOf("/") > dataField.value.indexOf("-")) {
        alert("Sai định dạng số phiếu.(CC/YYYY-C)!");
        dataField.focus();
        return false;
    }

    arrData[0] = dataField.value.substring(0, dataField.value.indexOf("/"));
    arrData[1] = dataField.value.substring(dataField.value.indexOf("/") + 1, dataField.value.indexOf("-"));
    arrData[2] = dataField.value.substr(dataField.value.indexOf("-") + 1);

    for (var i = 0;i < 3;i++) {
        if (arrData[i].length == 0 || (i == 0 && arrData[i].length != 2) || (i == 1 && arrData[i].length != 4)) {
            alert("Sai định dạng số phiếu.(CC/YYYY-C)!");
            dataField.focus();
            return false;
        }
        for (var j = 0;j < arrData[i].length;j++) {
            if (arrStandard[i].indexOf(arrData[i].charAt(j)) ==  - 1) {
                alert("Sai định dạng số phiếu.(CC/YYYY-C)!");
                alert(arrData[i].charAt(j) + ":" + arrStandard[i]);
                dataField.focus();
                return false;
            }
        }
    }
    return true;
}

/**
 * Kiem tra khi submit form
 */
function submitForm(form_no, alert_type) {
    var vform_no = 0;
    if (!isNull(form_no))
        vform_no = form_no;

    if (typeof (submitFlag) != 'undefined' && submitFlag == 0) {
        if (showAlertSubmit(alert_type)) {
            submitFlag = 1;// first time a button has been clicked
            document.forms[vform_no].submit();
        }
        else {
            return;
        }
    }
    else {
        return;// submit has already been clicked
    }
}
//ham dung de hien thi thong bao khi submit form
//neu p_type=null thi khong hien thi thong bao; neu p_type =1 thi hien thong bao luc xoa; ngoai ra thi hien xau truyen vao
function showAlertSubmit(p_type) {
    if (isNull(p_type))
        return true;
    else if (p_type == 1)
        return confirm("Có chắc chắn muốn xóa dữ liệu đã ch�?n không?");
    else 
        return confirm(p_type);
}

/**
 * Thu tuc bat phim phim tat
 */
function keyCallLov() {
    var indx = curRow - StartRow;
    if (event.keyCode == 120) {
        //F9 -> Lov an chi
        var vAcLov = document.getElementsByName("acLov");
        if (vAcLov.length > 0)
            vAcLov[indx].click();
    }
    else if (event.keyCode == 119) {
        //F8 -> Lov ton kho
        var vImg = document.getElementsByName("tkLov");
        if (vImg.length > 0)
            vImg[indx].click();
    }
}

function formSubmit(form) {
    form.submit();
}

/**
 * validKyHieu: kiem tra ky hieu, dang XX/YYYY
 */
function validKyHieu(pSeri) {
    var i;
    var strKyhieu = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    var strExt = "ABCDEFGHIJKLMNOPQRSTUVXYZ";

    if (pSeri == "")
        return true;
    pSeri = trim(pSeri);

    pSeri = pSeri.toUpperCase();
    while (pSeri.indexOf("-") >= 0)
        pSeri = pSeri.replace("-", "");
    pSeri = pSeri.toUpperCase();

    if (pSeri.length > 8 || pSeri.length < 4)
        return false;

    var vKH;
    var vNam;

    if (pSeri.indexOf("/") ==  - 1) {
        vKH = pSeri.substring(0, 2);
        vNam = pSeri.substring(2, pSeri.length);
    }
    else {
        var vPartition = (pSeri).split('/');
        if (vPartition.length != 2 && vPartition.length != 0) {
            return false;
        }
        vKH = vPartition[0];
        vNam = vPartition[1];
    }
    if (vNam.length < 2) {
        return false;
    }
    if (vNam.length < 4) {
        var sNam = vNam.substr(0, 2);
        if (parseInt(sNam) > 80)
            vNam = "19" + vNam;
        else 
            vNam = "20" + vNam;
    }
    pSeri = vKH + "/" + vNam;

    if (vKH.length != 2) {
        return false;
    }

    for (i = 0;i < vKH.length;i++) {
        if (strKyhieu.indexOf(vKH.charAt(i)) ==  - 1) {
            return false;
        }
    }

    if (vNam.length == 4) {
        if (!isNumber2(vNam)) {
            return false;
        }
    }
    else if (vNam.length == 5) {
        if (!isNumber2(vNam.substring(0, 4))) {
            return false;
        }

        if (strExt.indexOf(vNam.charAt(vNam.length - 1)) ==  - 1) {
            return false;
        }

    }
    else {
        return false;
    }

    return true;
}

/**
 * validTenDM: kiem tra ky hieu dac biet
 * @param: String pSeri
 * Longvh
 */
function validTenDM(pSeri) {
    var i;
    var strExt = "\"'";
    if (pSeri == null || pSeri == "" || pSeri == '')
        return true;
    var txtSeri = trim(pSeri);
    for (i = 0;i < txtSeri.length;i++) {
        if (strExt.indexOf(txtSeri.charAt(i)) !=  - 1) {
            alert("Bạn không được nhập các ký tự đặc biệt " + "\" hoặc '");
            return false;
        }
    }

    return true;
}

/**
 * valid ma an chi bo cac ky tu dac biet
 * @param: String pSeri
 * Longvh
 */

function validMaAC(pSeri) {

    var i;
    var strExt = "abcdđefghijklmnopqrstuvwxyzABCD�?EFGHIJKLMNOPQRSTUVWXYZ0123456789//-.";
    var strMsgExt = "abcdđefghijklmnopqrstuvwxyzABCD�?EFGHIJKLMNOPQRSTUVWXYZ0123456789/-.";
    if (pSeri == null || pSeri.value == "" || pSeri == '')
        return true;
    var txtSeri = trim(pSeri);
    for (i = 0;i < txtSeri.length;i++) {
        if (strExt.indexOf(txtSeri.charAt(i)) ==  - 1) {
            alert("Không được nhập ngoài danh sách ký tự " + strMsgExt);
            return false;
        }
    }

    return true;
}

/**
 * validSoPhieu: kiem tra so phieu, dang XX/YYYY-ZZ
 */

function validKyhieu_Sophieu(kyhieu_sophieu) {
    if (kyhieu_sophieu == "")
        return true;
    var kyhieu = "";
    var sophieu = "";
    var pos = kyhieu_sophieu.indexOf("-");

    if (pos < 0)//khong co dang XX/YYYY-ZZ
        return false;

    kyhieu = kyhieu_sophieu.substring(0, pos);
    sophieu = kyhieu_sophieu.substr(pos + 1);

    return (validKyHieu(kyhieu) && isNumber(sophieu));
}

/**
 * Thu tuc luu danh muc vao cookie
 *	name: ten cookie
 *	arrContent: gia tri luu vao cookie
 */
function storeDanhMuc(name, value) {
    var date = new Date("December 31, 2005");
    var myCookie = "";
    var data = "";
    for (var i = 0;i < value.length;i++)
        data += escape(value[i].convertToString()) + "]";

    myCookie = escape(name) + "=" + data + "; expires=" + date.toGMTString() + ";";
    document.cookie = myCookie;
}

/**
 * Thu tuc lay danh muc tu cookie
 *	name: ten cookie
 */
function loadDanhMuc(name) {
    var aCookie = document.cookie.split("; ");//cac cookie phan cach boi dau ';'
    var username = null;//cookie co dang achCookie=ach1]ach2]..;
    var strSearch = name + "=";

    if (aCookie.length == 0)
        return;
    for (var i = 0;i < aCookie.length;i++) {
        if (aCookie[i].indexOf(strSearch) !=  - 1) {

            var myCookie = aCookie[i].split("=");
            var myData = myCookie[1].split("]");
            arrContent = new Array();

            for (var j = 0;j < myData.length;j++) {
                arrContent[j] = eval(unescape(myData[j]));
            }
            break;
        }
    }
}

function SaveListAchToFile(arrData) {
    //	alert("Ghi nhe");
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    //	alert(fso);
    var newFile = fso.CreateTextFile("C:\\ListAch.txt", true);

    for (var i = 0;i < arrData.length;i++)
        newFile.WriteLine(arrData[i].convertToString());

    newFile.Close();
}

function LoadListAchFromFile() {
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var newFile = fso.CreateTextFile("C:\\ListAch.txt", true);

    for (var i = 0;i < arrData.length;i++)
        newFile.WriteLine(arrData[i].convertToString());

    newFile.Close();

}

/**
 *	activeBlur: Kích hoạt sự kiện onblur khi dùng phím tắt
 */
function activeBlur() {
    if (event.keyCode == 18)//phim tat ALT + KEY
    {
        var obj = document.getElementsByTagName("INPUT");
        for (var i = 0;i < obj.length;i++) {
            if (obj[i].type == "text" && obj[i].outerHTML.indexOf("onblur") !=  - 1)
                obj[i].blur();
        }
    }

}

/**
 Goi ham init_DM_XM_Ach de xoa gia tri Lov Dm Ach
 **/
function init_LOV_DM_Ach(arrFull) {
    arrContent = arrFull;
    var vXM = document.forms[0].maAnchi_XM.value;
    var vM = document.forms[0].maAnchi;
    var vT = document.forms[0].tenAnchi;
    vM.value = "";
    vT.value = "";
    getArrayAnChi(vXM, arrContent);
    return;
}

function getArrayAnChi(pXM, arrACH) {
    //	var strPrint = "";
    var temp_Arr = new Array;
    var dem = 0;
    if ((pXM == null) || (pXM == "")) {
        temp_Arr = arrACH;
    }
    else {
        for (i = 0;i < arrACH.length;i++) {
            if (arrACH[i].Lac_XM == pXM) {
                temp_Arr[dem] = arrACH[i];
                //strPrint = strPrint + "arrContent["+ dem +"].Lac_XM =" + arrACH[i].Lac_XM + "\n"; 
                dem = dem + 1;
            }
        }
    }
    arrContent = temp_Arr;
    //	alert(strPrint);
    return;
}

/* Convert to Unicode */
function GetUnicode(iStr) {
    for (i = 0, oStr = '';i < iStr.length;) {
        if (iStr.charCodeAt(i) == 38) {
            if (iStr.charCodeAt(i + 1) == 35) {
                p = iStr.indexOf(';', i + 2);
                if (p !=  - 1) {
                    if (p - i <= 7) {
                        if (isFinite(iStr.substr(i + 2, p - i - 2))) {
                            oStr = oStr.concat(String.fromCharCode(iStr.substr(i + 2, p - i - 2)));
                            i = p + 1;
                            continue;
                        }
                    }
                }
            }
            else {
                p = iStr.indexOf(';', i + 1);
                if (p !=  - 1) {
                    switch (iStr.substr(i + 1, p - i - 1)) {
                        case 'amp':
                            oStr = oStr.concat('&');
                            i = p + 1;
                            break;
                        case 'quot':
                            oStr = oStr.concat('"');
                            i = p + 1;
                            break;
                        case 'lt':
                            oStr = oStr.concat('<');
                            i = p + 1;
                            break;
                        case 'gt':
                            oStr = oStr.concat('>');
                            i = p + 1;
                            break;
                    }
                }
            }
        }

        oStr = oStr.concat(iStr.charAt(i));
        i++;
    }

    return oStr;
}

function SetUnicode(iStr) {
    for (i = 0, oStr = '';i < iStr.length;i++) {
        switch ((j = iStr.charCodeAt(i))) {
            case 34:
                oStr = oStr.concat('&quot;');
                break;
            case 38:
                oStr = oStr.concat('&amp;');
                break;
            case 39:
                oStr = oStr.concat('&#39;');
                break;
            case 60:
                oStr = oStr.concat('&lt;');
                break;
            case 62:
                oStr = oStr.concat('&gt;');
                break;
            default :
                if (j < 32 || j > 127 || j == 34 || j == 39) {
                    oStr = oStr.concat('&#').concat(j).concat(';');
                }
                else {
                    oStr = oStr.concat(iStr.charAt(i));
                }
                break;
        }
    }

    return oStr;
}

function loaiACH(pId, pMa, pTen, pHinhThucIn, pLoaiAch) {
    return loaiACH(pId, pMa, pTen, pHinhThucIn, pLoaiAch, null, null);
}

function loaiACH(pId, pMa, pTen, pHinhThucIn, pLoaiAch, pTyLeNhapXuat) {
    return loaiACH(pId, pMa, pTen, pHinhThucIn, pLoaiAch, pTyLeNhapXuat, null);
}

function loaiACH(pId, pMa, pTen, pHinhThucIn, pLoaiAch, pTyLeNhapXuat, pTyLeLuuKho) {
    this.ID = pId;
    this.Ma = pMa;
    this.Ten = pTen;
    this.HinhThucIn = pHinhThucIn;
    this.LoaiAnchi = pLoaiAch;
    this.TyLeNhapXuat = pTyLeNhapXuat;
    this.TyLeLuuKho = pTyLeLuuKho;
}
//lydh2 them cho phan phieu xac minh
//linhlt5 ngay 15/02/2011 thuc hien lay them ba thong tin con lai de check ky hieu an chi theo mau moi
function loaiAnchi2(pId, pMa, pTen, pLac_XM, pHinhThucIn, pLoaiAch, pNgayBatDau, pCqt_Id, pDKH_TIN) {
    this.ID = pId;
    this.Ma = pMa;
    this.Ten = pTen;
    this.Lac_XM = pLac_XM;
    this.HinhThucIn = pHinhThucIn;
    this.LoaiAnchi = pLoaiAch;
    this.NgayBatDau = pNgayBatDau;
    this.Cqt_Id = pCqt_Id;
    this.Dkh_Tin = pDKH_TIN;

}
//lydh2 end
// Sonpm2 added: SHOW LOV CQT
function convertDMCQTToString() {
    var strCvt = "new dmCqtSS('"
    strCvt += this.Ma + "','";
    strCvt += SetUnicode(this.Ten) + "'";
    strCvt += ");";
    return strCvt;
}

function dmCqtSS(pMa, pTen, pID) {
    this.Ma = pMa;
    this.Ten = pTen;
    this.Loai = "CQT";
    this.ID = pID;
    this.convertDMCQTToString = convertDMCQTToString
}
//~Sonpm2
//toandd 
function fomatDate(sDate, sFormatMark) {
    var sDatetemp
    var values = sDate.split("/");
    var yr_num;
    var mo_num;
    var day_num;
    if ((sFormatMark.toUpperCase() == "DD/MM/YYYY") || (sFormatMark.toUpperCase() == "D")) {
        yr_num = values[2];
        mo_num = values[1] - 1;
        day_num = values[0];
    }
    else if ((sFormatMark.toUpperCase() == "MM/YYYY") || (sFormatMark.toUpperCase() == "M")) {
        yr_num = values[1];
        mo_num = values[0] - 1;
        day_num = 1;
    }
    else if ((sFormatMark.toUpperCase() == "Q/YYYY") || (sFormatMark.toUpperCase() == "Q")) {
        yr_num = values[1];
        values = values[0].split("Q");
        mo_num = (values[1] * 3 - 2) - 1;
        day_num = 1;
    }
    else if ((sFormatMark.toUpperCase() == "YYYY") || (sFormatMark.toUpperCase() == "Y")) {
        yr_num = values[0];
        mo_num = 0;
        day_num = 1;
    }
    return new Date(yr_num, mo_num, day_num);
}

/*sua loi khong click lov thi khong xoa ky hieu -- 22-02-2011*/
function xoa_Kyhieu1() {
    var vKyHieu = document.getElementsByName("kyHieu");
    var vI_ = document.getElementsByName("IdAnchi");
    var vMa = document.getElementsByName("maAnchi");
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }

    if (StartRow1 > 0)
        rowIndx = e1.rowIndex - StartRow1;
    else 
        rowIndx = e1.rowIndex - StartRow;
    for (var j = 0;j < arrContent.length;j++) {
        if (arrContent[j].ID == vI_[rowIndx].value) {
            vKyHieu[rowIndx].value = "";
        }
    }
}
//~toandd
/*linhlt5 thuc hien them ham chung cho phan bat ky hieu theo nghiep vu thay doi: ngay 08/03/2011*/
function Check_CqtID() {
    //lay dong thuc hien
    var e1 = event.srcElement;
    while (e1.tagName != 'TR') {
        e1 = e1.parentElement;
    }

    if (StartRow1 > 0)
        rowIndx = e1.rowIndex - StartRow1;
    else 
        rowIndx = e1.rowIndex - StartRow;
    var vI_ = document.getElementsByName("IdAnchi");
    //lay thong tin lan thuc hien
    for (var j = 0;j < arrContent.length;j++) {
        if (arrContent[j].ID == vI_[rowIndx].value) {
            if ((arrContent[j].Cqt_Id + "") == "") {
                return false;
            }
        }
    }
    return true;
}

function nhan_mvach_err(id, loai_bc, hdr_id, dtl_id, ten_bang, ma_loi, ext_err, col_name, ten_loi) {
    this.id = id;
    this.loai_bc = loai_bc;
    this.hdr_id = hdr_id;
    this.dtl_id = dtl_id;
    this.ten_bang = ten_bang;
    this.ma_loi = ma_loi;
    this.ext_err = ext_err;
    this.col_name = col_name;
    this.ten_loi = ten_loi;
}

function format_number(pTuso, pDenso, pSoluong) {
    var vTuso = document.getElementsByName("tuSo");
    var vDenso = document.getElementsByName("denSo");
    var vSoluong = document.getElementsByName("soLuong");
    for (var j = 0;j < vTuso.length;j++) {
        if (!isNull(vTuso[j])) {
            vTuso[j].value = toFormatNumber(toNumber(vTuso[j].value));
            vDenso[j].value = toFormatNumber(toNumber(vDenso[j].value));
            vSoluong[j].value = toFormatNumber(toNumber(vSoluong[j].value));
        }
    }
}

function displayMessage(msg) {
    alert(msg.replace(/@@/g, '\n'));
}

function validNumberNotNotice(obj, size) {
    var arg = validNumberNotNotice.arguments;
    if ((obj.value == null) || (obj.value == ""))
        return true;
    obj.value = trim(obj.value);
    obj.value = toNumber(obj.value);

    if (obj.value.indexOf('-') >= 0 && !obj.readOnly) {
        //showError(msgInvalidValue);
        err = 'error';
        obj.focus();
        return false;
    }

    if (!isNumber1(obj, arg.length) && !obj.readOnly) {
        //showError(msgInvalidFormat);
        err = 'error';
        obj.focus();
        return false;
    }

    if (obj.value.length > size && !obj.readOnly) {
        //	showError(msgInvalidSize);
        err = 'error';
        obj.focus();
        return false;
    }
    return true;
}

function validDateFormatNotNotice(dateField, dateFormat) {
    var dt = dateField;
    if ((dt.value == null) || (dt.value == ""))
        return true;
    var returndate;
    if (dateFormat.toUpperCase() == 'DD/MM/YYYY') {
        returndate = isDate(dt.value);
    }
    else if (dateFormat.toUpperCase() == 'MM/YYYY') {
        returndate = isMonth(dt.value);
    }
    else if (dateFormat.toUpperCase() == 'YYYY') {
        returndate = isYear(dt.value);
    }
    else {
        returndate = false;
    }

    if (returndate == false) {
        dt.focus();
        return false;
    }
    dt.value = returndate;
    return returndate;
}

/**
 * Check Date
 * Used: onblur="CheckDate(this)"
 * Format: dd/mm/yyyy
 */
function CheckDate(field, title) {
    var checkstr = "0123456789";
    var DateField = field;
    var Datevalue = "";
    var DateTemp = "";
    var seperator = "/";
    var day;
    var month;
    var year;
    var leap = 0;
    var err = 0;
    var i;
    var j = 0;
    err = 0;
    DateValue = DateField.value;
    if (DateValue == "")
        return true;
    /* Delete all chars except 0..9 */
    for (i = 0;i < DateValue.length;i++) {
        if (checkstr.indexOf(DateValue.substr(i, 1)) >= 0) {
            DateTemp = DateTemp + DateValue.substr(i, 1);
        }
    }
    DateValue = DateTemp;
    /* Always change date to 8 digits - string*/
    /* if year is entered as 2-digit / always assume 20xx */
    if (DateValue.length == 6) {
        DateValue = DateValue.substr(0, 4) + '20' + DateValue.substr(4, 2);
    }
    if (DateValue.length != 8) {
        err = 19;
    }
    /* year is wrong if year = 0000 */
    year = DateValue.substr(4, 4);
    if (year == 0) {
        err = 20;
    }
    /* Validation of month*/
    day = DateValue.substr(0, 2);
    month = DateValue.substr(2, 2);
    if ((month < 1) || (month > 12)) {
        err = 21;
    }
    /* Validation of day*/
    if (day < 1) {
        err = 22;
    }

    /* Validation leap-year / february / day */
    if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
        leap = 1;
    }
    if ((month == 2) && (leap == 1) && (day > 29)) {
        err = 23;
    }
    if ((month == 2) && (leap != 1) && (day > 28)) {
        err = 24;
    }
    /* Validation of other months */
    if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
        err = 25;
    }
    if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
        err = 26;
    }
    /* if 00 ist entered, no error, deleting the entry */
    if ((day == 0) && (month == 0) && (year == 00)) {
        err = 0;
        day = "";
        month = "";
        year = "";
        seperator = "";
    }
    /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */

    if (err == 0) {
        DateField.value = day + seperator + month + seperator + year;
        if (DateField.value == "") {
            alert(title + " kh&#244;ng &#273;&#250;ng &#273;&#7883;nh d&#7841;ng dd/mm/YYYY");
            DateField.focus();
            return false;
        }
    }
    /* Error-message if err != 0 */
    else {
        //alert("M&#227; s&#7889; NKT");
        alert(unescape('Nh%u1EADp%20t%EAn%20danh%20s%E1ch%20%21'));        
        DateField.focus();
        return false;
    }

    if (parseInt(year) < 1800) {
        alert("2");
        DateField.focus();
        return false;
    }
    return true;
}