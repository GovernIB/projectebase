#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="traduccio" cssClass="form-search"  modelAttribute="traduccioFilterForm" 
        method="${symbol_dollar}{method}"  enctype="multipart/form-data">

  <%@include file="traduccioListCommon.jsp" %>
  <div id="${symbol_dollar}{formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="traduccioListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="traduccioListSubtitle.jsp" %>
  <%@include file="traduccioListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="traduccioListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="traduccioListCore.jsp" %>
  <c:if test="${symbol_dollar}{not empty traduccioItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${symbol_dollar}{__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/traduccioListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

