<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="./localHeader.jsp"%>

<openmrs:require privilege="Manage Roles,Manage Order Types,Add Users" otherwise="/login.htm"
	redirect="/module/radiologyrest/config.list" />

<h2><spring:message code="radiologyrest.config" /></h2>
<a href="config.list?command=typeRoles"><spring:message code="radiologyrest.createTypeAndRoles" /></a><br/>
<a href="config.list?command=dummyUsers"><spring:message code="radiologyrest.dummyUsers" /></a><br/>
<spring:message code="general.saving" arguments=": "/><br/>
<spring:message code="radiologyrest.mwl" />${mwl}<br/>
<spring:message code="radiologyrest.mpps" />${mpps}<br/>
<%--
<spring:message code="radiology.storage" />${storage}<br/>
<h2><spring:message code="radiology.xebraConfig" /></h2>
1. <a href="config.list?command=SCP"><spring:message code="radiology.createSCP" /></a><br/>
2. <a href="config.list?command=AE"><spring:message code="radiology.createAE" /></a><br/>
--%>
<%@ include file="/WEB-INF/template/footer.jsp"%>