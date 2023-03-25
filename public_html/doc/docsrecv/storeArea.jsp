<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

	<style type="text/css">
	#mainContainer{
		width:660px;
		margin:0 auto;
		text-align:left;
		height:100%;
		background-color:#FFF;
		border-left:3px double #000;
		border-right:3px double #000;
	}
	#formContent{
		padding:5px;
	}
	#doc_storeArea{
		position:absolute;	/* Never change this one */
		width:175px;	/* Width of box */
		height:120px;	/* Height of box */
		overflow:auto;	/* Scrolling features */
		border:1px solid #317082;	/* Dark green border */
		background-color:#FFF;	/* White background color */
		text-align:left;
		font-size:0.9em;
		z-index:100;
	}
	#doc_storeArea div{	/* General rule for both .optionDiv and .optionDivSelected */
		margin:1px;		
		padding:1px;
		cursor:pointer;
		font-size:0.9em;
	}
	#doc_storeArea .optionDiv{	/* Div for each item in list */
		
	}
	#doc_storeArea .optionDivSelected{ /* Selected item in the list */
		background-color:#317082;
		color:#FFF;
	}
	#doc_storeArea_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
	}
	form{
		display:inline;
	}
	</style>
        <input type="text" id="storeArea" name="storeArea" value="" style="width:147px" onkeyup="ajax_showOptions(this,'5','docsrecvMain','Anchor=_SEARCH_STORES',event,'doc_storeArea')">
