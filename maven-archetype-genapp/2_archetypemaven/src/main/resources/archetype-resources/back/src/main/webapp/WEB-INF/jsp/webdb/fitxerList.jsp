#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="fitxer" cssClass="form-search"  modelAttribute="fitxerFilterForm" 
        method="${symbol_dollar}{method}"  enctype="multipart/form-data">

  <%@include file="fitxerListCommon.jsp" %>
  <div id="${symbol_dollar}{formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="fitxerListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="fitxerListSubtitle.jsp" %>
  <%@include file="fitxerListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="fitxerListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="fitxerListCore.jsp" %>
  <c:if test="${symbol_dollar}{not empty fitxerItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${symbol_dollar}{__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/fitxerListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

