#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${symbol_dollar}{fn:startsWith(fitxerForm.titleCode,'=')}">
       <c:out value="${symbol_dollar}{fn:substringAfter(fitxerForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${symbol_dollar}{not empty fitxerForm.titleCode}">
    <fmt:message key="${symbol_dollar}{fitxerForm.titleCode}" >
      <fmt:param value="${symbol_dollar}{fitxerForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${symbol_dollar}{empty fitxerForm.entityNameCode}">
      <fmt:message var="entityname" key="fitxer.fitxer"/>
    </c:if>
    <c:if test="${symbol_dollar}{not empty fitxerForm.entityNameCode}">
      <fmt:message var="entityname" key="${symbol_dollar}{fitxerForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${symbol_dollar}{fitxerForm.nou?'genapp.createtitle':(fitxerForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${symbol_dollar}{keytitle}">
      <fmt:param value="${symbol_dollar}{entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${symbol_dollar}{not empty fitxerForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${symbol_dollar}{fn:startsWith(fitxerForm.subTitleCode,'=')}" />
<c:if test="${symbol_dollar}{subtitleTranslated}">
   <c:out value="${symbol_dollar}{fn:substringAfter(fitxerForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${symbol_dollar}{not subtitleTranslated}">
  <fmt:message key="${symbol_dollar}{fitxerForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>