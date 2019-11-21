#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="idioma" cssClass="form-search"  modelAttribute="idiomaFilterForm" 
        method="${symbol_dollar}{method}"  enctype="multipart/form-data">

  <%@include file="idiomaListCommon.jsp" %>
  <div id="${symbol_dollar}{formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="idiomaListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="idiomaListSubtitle.jsp" %>
  <%@include file="idiomaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="idiomaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="idiomaListCore.jsp" %>
  <c:if test="${symbol_dollar}{not empty idiomaItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${symbol_dollar}{__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/idiomaListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

