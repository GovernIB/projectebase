#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IdiomaFields" className="${package}.model.fields.IdiomaFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${symbol_dollar}{__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="well formbox" style="${symbol_dollar}{displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="pull-right">

           <a class="pull-right" style="margin-left:10px" href="${symbol_pound}"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="icon-remove"></i></a>
           <input style="margin-left: 3px" class="btn btn-warning pull-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-warning pull-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-primary pull-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}">
      <c:if test="${symbol_dollar}{ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${symbol_dollar}{__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${symbol_dollar}{__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${symbol_dollar}{gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${symbol_dollar}{__entry.value.searchBy.fullName}" name="${symbol_dollar}{__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${symbol_dollar}{__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${symbol_dollar}{__entry.value.searchBy.fullName}Fins" name="${symbol_dollar}{__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${symbol_dollar}{__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${symbol_dollar}{__entry.value.searchBy.fullName}" name="${symbol_dollar}{__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${symbol_dollar}{cercaperAF}" type="text" value="${symbol_dollar}{__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${symbol_dollar}{gen:contains(__theFilterForm.filterByFields ,IdiomaFields.IDIOMAID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="idioma.idiomaID" var="idiomaID" />
              <fmt:message key="genapp.form.searchby" var="cercaperidiomaID" >                
                 <fmt:param value="${symbol_dollar}{idiomaID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${symbol_dollar}{idiomaID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${symbol_dollar}{cercaperidiomaID}" path="idiomaID" />
            </div>


        </c:if>
        <c:if test="${symbol_dollar}{gen:contains(__theFilterForm.filterByFields ,IdiomaFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="idioma.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${symbol_dollar}{nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${symbol_dollar}{nom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${symbol_dollar}{cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${symbol_dollar}{gen:contains(__theFilterForm.filterByFields ,IdiomaFields.SUPORTAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="idioma.suportat" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="suportatDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="suportatFins" />

            </div>


        </c:if>
        <c:if test="${symbol_dollar}{gen:contains(__theFilterForm.filterByFields ,IdiomaFields.ORDRE)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="idioma.ordre" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="ordreDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="ordreFins" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${symbol_dollar}{__theFilterForm.additionalFields}">
      <c:if test="${symbol_dollar}{ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${symbol_dollar}{__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${symbol_dollar}{__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${symbol_dollar}{gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${symbol_dollar}{__entry.value.searchBy.fullName}" name="${symbol_dollar}{__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${symbol_dollar}{__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${symbol_dollar}{__entry.value.searchBy.fullName}Fins" name="${symbol_dollar}{__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${symbol_dollar}{__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${symbol_dollar}{__entry.value.searchBy.fullName}" name="${symbol_dollar}{__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${symbol_dollar}{cercaperAF}" type="text" value="${symbol_dollar}{__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
