#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:if test="${symbol_dollar}{not empty __theFilterForm.titleCode}">
      <fmt:message key="${symbol_dollar}{__theFilterForm.titleCode}">
        <fmt:param value="${symbol_dollar}{__theFilterForm.titleParam}" />
      </fmt:message>
  </c:if>
  <c:if test="${symbol_dollar}{empty __theFilterForm.titleCode}">
    <fmt:message key="genapp.listtitle">
      <fmt:param value="${symbol_dollar}{entitynameplural}"/>
    </fmt:message>
  </c:if>

      <%-- AGRUPAR PER BOTO - INICI  --%>
  <c:if test="${symbol_dollar}{fn:length(groupby_items) > 0}">
      <c:set var="displayGroupBut" value="${symbol_dollar}{__theFilterForm.visibleGroupBy?'display:none;':''}" />
      <a id="GroupButton" style="${symbol_dollar}{displayGroupBut}" title="<fmt:message key="genapp.form.groupby"/>" onclick="document.getElementById('GroupDiv').style.display = 'inherit'; document.getElementById('GroupButton').style.display = 'none';" class="btn" role="button" data-toggle="modal">
         <img src="<c:url value="/img/treeicon.png"/>"/>
      </a>
  </c:if>
      <%-- AGRUPAR PER BOTO - FINAL  --%>

      <%-- FILTRAR PER BOTO - INICI  --%>
      <c:if test="${symbol_dollar}{fn:length(__theFilterForm.filterByFields) > 0}">
      <c:set var="displayFilterBut" value="${symbol_dollar}{__theFilterForm.visibleFilterBy?'display:none;':''}" />
      <a id="FilterButton" style="${symbol_dollar}{displayFilterBut}" title="<fmt:message key="genapp.form.filterby"/>" onclick="document.getElementById('FilterDiv').style.display = 'inherit'; document.getElementById('FilterButton').style.display = 'none';" class="btn" role="button" data-toggle="modal">
         <i class="icon-search"></i>
      </a>
      </c:if>
      <%-- FILTRAR PER BOTO - FINAL  --%>
     
      <%-- BOTO DE NOU ELEMENT EN LLISTAT  --%>
    <c:if test="${symbol_dollar}{__theFilterForm.addButtonVisible}">
      <a class="btn btn-small pull-right" role="button" data-toggle="modal"
        href="<c:url value="${symbol_dollar}{contexte}/new"/>"> <i class="icon-plus-sign"></i>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${symbol_dollar}{entityname}"/>
       </fmt:message>
      </a>
    </c:if>
      <%-- BOTO DE ESBORRAT MULTIPLE  --%>
    <c:if test="${symbol_dollar}{__theFilterForm.deleteSelectedButtonVisible && __theFilterForm.visibleMultipleSelection && not empty traduccioItems}">
      <a class="btn btn-danger btn-small pull-right" style="color: white;" href="${symbol_pound}myModal"
        onclick="openModalSubmit('<c:url value="${symbol_dollar}{contexte}/deleteSelected"/>','show', 'traduccio');"
        title="<fmt:message key="genapp.delete"/>">
        <i class="icon-trash icon-white"></i>
        <fmt:message key="genapp.delete.selected" />
      </a>
    </c:if>
    <c:forEach var="button" items="${symbol_dollar}{__theFilterForm.additionalButtons}">
      <c:set var="thelink" value="${symbol_dollar}{button.link}" />
     <c:set var="thehref" value="${symbol_pound}"/>
      <c:if test="${symbol_dollar}{!fn:startsWith(thelink,'javascript:')}">
        <c:url var="thehref" value="${symbol_dollar}{thelink}"/>
        <c:url var="thelink" value=""/>
      </c:if>
<a class="btn btn-small ${symbol_dollar}{button.type} pull-right" style="${symbol_dollar}{(empty button.type)? '' : 'color: white;'}"  href="${symbol_dollar}{thehref}" onclick="${symbol_dollar}{thelink}" title="<fmt:message key="${symbol_dollar}{button.codeText}"/>">
         <i class="${symbol_dollar}{button.icon}"></i>
         <fmt:message key="${symbol_dollar}{button.codeText}"/>
      </a>
    </c:forEach>
  
