#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FitxerFields" className="${package}.model.fields.FitxerFields"/>
  
  <c:set var="__theForm" value="${symbol_dollar}{fitxerForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${symbol_dollar}{not __theForm.view}">    <c:forEach items="${symbol_dollar}{__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${symbol_dollar}{hiddenFieldF.javaName}" />
      <c:if test="${symbol_dollar}{gen:hasProperty(__theForm.fitxer,hiddenField)}">
        <form:errors path="fitxer.${symbol_dollar}{hiddenField}" cssClass="errorField alert alert-error" />
        <form:hidden path="fitxer.${symbol_dollar}{hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

