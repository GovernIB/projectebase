#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
          <c:if test="${symbol_dollar}{__theFilterForm.editButtonVisible || __theFilterForm.deleteButtonVisible || not empty __theFilterForm.additionalButtonsForEachItem || not empty __theFilterForm.additionalButtonsByPK}">
          <td>
          <c:set var="pk" value="${symbol_dollar}{idioma.idiomaID}"/>
          <c:choose>
           <c:when test="${symbol_dollar}{__theFilterForm.actionsRenderer == 1}">
            <div class="btn-group" data-toggle="buttons-checkbox">
            <c:if test="${symbol_dollar}{__theFilterForm.editButtonVisible}">
            <a class="btn " href="<c:url value="${symbol_dollar}{contexte}/${symbol_dollar}{idioma.idiomaID}/edit"/>" onclick="" title="<fmt:message key="genapp.edit"/>">
               <i class="icon-pencil"></i>
            </a>
            </c:if>
            <c:if test="${symbol_dollar}{__theFilterForm.deleteButtonVisible}">
            <a class="btn btn-danger" href="${symbol_pound}myModal" onclick="openModal('<c:url value="${symbol_dollar}{contexte}/${symbol_dollar}{idioma.idiomaID}/delete"/>','show');" title="<fmt:message key="genapp.delete"/>">
               <i class="icon-trash icon-white"></i>
            </a>
            </c:if>
            <c:set var="bracket" value="{0}"/>
            <c:forEach var="button" items="${symbol_dollar}{__theFilterForm.additionalButtonsForEachItem}">
                  <c:set var="thehref" value="${symbol_pound}"/>
                  <c:set var="thelink" value="${symbol_dollar}{fn:replace(button.link,bracket, pk)}" />
                  <c:if test="${symbol_dollar}{!fn:startsWith(thelink,'javascript:')}">
                  <c:url var="thehref" value="${symbol_dollar}{thelink}"/>
                  <c:url var="thelink" value=""/>
                  </c:if>
                  <a class="btn ${symbol_dollar}{button.type}" href="${symbol_dollar}{thehref}" onclick="${symbol_dollar}{thelink}" title="<fmt:message key="${symbol_dollar}{button.codeText}"/>">
                     <c:if test="${symbol_dollar}{fn:startsWith(button.icon, '/')}">
                     <img src="<c:url value="${symbol_dollar}{button.icon}"/>"/>
                     </c:if>                     <c:if test="${symbol_dollar}{!fn:startsWith(button.icon, '/')}">
                     <i class="${symbol_dollar}{button.icon}"></i>
                     </c:if>
                  </a>
            </c:forEach>

            <c:if test="${symbol_dollar}{not empty __theFilterForm.additionalButtonsByPK}">
              <c:if test="${symbol_dollar}{not empty __theFilterForm.additionalButtonsByPK[pk]}">
                  <c:forEach var="button" items="${symbol_dollar}{__theFilterForm.additionalButtonsByPK[pk]}">
                  <c:set var="thehref" value="${symbol_pound}"/>
                  <c:set var="thelink" value="${symbol_dollar}{fn:replace(button.link,bracket, pk)}" />
                  <c:if test="${symbol_dollar}{!fn:startsWith(thelink,'javascript:')}">
                  <c:url var="thehref" value="${symbol_dollar}{thelink}"/>
                  <c:url var="thelink" value=""/>
                  </c:if>
                  <a class="btn ${symbol_dollar}{button.type}" href="${symbol_dollar}{thehref}" onclick="${symbol_dollar}{thelink}" title="<fmt:message key="${symbol_dollar}{button.codeText}"/>">
                     <c:if test="${symbol_dollar}{fn:startsWith(button.icon, '/')}">
                     <img src="<c:url value="${symbol_dollar}{button.icon}"/>"/>
                     </c:if>                     <c:if test="${symbol_dollar}{!fn:startsWith(button.icon, '/')}">
                     <i class="${symbol_dollar}{button.icon}"></i>
                     </c:if>
                  </a>
                  </c:forEach>

               </c:if>

            </c:if>

            </div>
            </c:when>
           <c:when test="${symbol_dollar}{__theFilterForm.actionsRenderer == 2}">
                <div class="btn-group">
      <a class="btn btn-small ${symbol_dollar}{__theFilterForm.additionalInfoForActionsRendererByPK[pk]}" href="${symbol_pound}" style="${symbol_dollar}{(empty __theFilterForm.additionalInfoForActionsRendererByPK[pk])? '' : 'color: white;'}"><i class="icon-list ${symbol_dollar}{(empty __theFilterForm.additionalInfoForActionsRendererByPK[pk])? '' : 'icon-white'}"></i> <fmt:message key="genapp.actions" /></a>
      <a class="btn btn-small ${symbol_dollar}{__theFilterForm.additionalInfoForActionsRendererByPK[pk]} dropdown-toggle" data-toggle="dropdown" href="${symbol_pound}">&nbsp;<span class="caret"> </span></a>
      <ul class="dropdown-menu pull-right" style="min-width:35px;padding:5px 5px 0px 5px;margin:0px;font-size: 12px" >
            <c:if test="${symbol_dollar}{__theFilterForm.editButtonVisible}">
            <li>
            <a class="btn  btn-small a_item" style="margin-bottom:5px;" href="<c:url value="${symbol_dollar}{contexte}/${symbol_dollar}{idioma.idiomaID}/edit"/>" onclick="">
            <i class="icon-pencil"></i>
             <fmt:message key="genapp.edit"/>
            </a>
            </li>
            </c:if>
            <c:if test="${symbol_dollar}{__theFilterForm.deleteButtonVisible}">
            <li>
            <a class="btn btn-danger btn-small a_item" style="margin-bottom:5px;color: white;" href="${symbol_pound}myModal" onclick="openModal('<c:url value="${symbol_dollar}{contexte}/${symbol_dollar}{idioma.idiomaID}/delete"/>','show');">
            <i class="icon-trash icon-white"></i>
             <fmt:message key="genapp.delete"/>
            </a>
            </li>
            </c:if>
            <c:set var="bracket" value="{0}"/>
            <c:forEach var="button" items="${symbol_dollar}{__theFilterForm.additionalButtonsForEachItem}">
                  <c:set var="thehref" value="${symbol_pound}"/>
                  <c:set var="thelink" value="${symbol_dollar}{fn:replace(button.link,bracket, pk)}" />
                  <c:if test="${symbol_dollar}{!fn:startsWith(thelink,'javascript:')}">
                  <c:url var="thehref" value="${symbol_dollar}{thelink}"/>
                  <c:url var="thelink" value=""/>
                  </c:if>
                  <li>
                  <a class="btn ${symbol_dollar}{button.type} btn-small a_item" style="margin-bottom:5px;${symbol_dollar}{(empty button.type)? '' : 'color: white;'};" href="${symbol_dollar}{thehref}" onclick="${symbol_dollar}{thelink}">
                  <c:if test="${symbol_dollar}{fn:startsWith(button.icon, '/')}">
                  <img src="<c:url value="${symbol_dollar}{button.icon}"/>"/>
                  </c:if>                  <c:if test="${symbol_dollar}{!fn:startsWith(button.icon, '/')}">
                  <i class="${symbol_dollar}{button.icon}"></i>
                  </c:if>
                   <fmt:message key="${symbol_dollar}{button.codeText}"/>
                  </a>
                  </li>
            </c:forEach>

            <c:if test="${symbol_dollar}{not empty __theFilterForm.additionalButtonsByPK}">
              <c:if test="${symbol_dollar}{not empty __theFilterForm.additionalButtonsByPK[pk]}">
                  <c:forEach var="button" items="${symbol_dollar}{__theFilterForm.additionalButtonsByPK[pk]}">
                  <c:set var="thehref" value="${symbol_pound}"/>
                  <c:set var="thelink" value="${symbol_dollar}{fn:replace(button.link,bracket, pk)}" />
                  <c:if test="${symbol_dollar}{!fn:startsWith(thelink,'javascript:')}">
                  <c:url var="thehref" value="${symbol_dollar}{thelink}"/>
                  <c:url var="thelink" value=""/>
                  </c:if>
                  <li>
                  <a class="btn ${symbol_dollar}{button.type} btn-small a_item" style="margin-bottom:5px;${symbol_dollar}{(empty button.type)? '' : 'color: white;'};" href="${symbol_dollar}{thehref}" onclick="${symbol_dollar}{thelink}">
                  <c:if test="${symbol_dollar}{fn:startsWith(button.icon, '/')}">
                  <img src="<c:url value="${symbol_dollar}{button.icon}"/>"/>
                  </c:if>                  <c:if test="${symbol_dollar}{!fn:startsWith(button.icon, '/')}">
                  <i class="${symbol_dollar}{button.icon}"></i>
                  </c:if>
                   <fmt:message key="${symbol_dollar}{button.codeText}"/>
                  </a>
                  </li>
                  </c:forEach>

               </c:if>

            </c:if>

                 </ul>
    </div>
            </c:when>
            <c:otherwise>
              &nbsp;<!-- Sense Render d'accions -->
            </c:otherwise>
          </c:choose>
           </td>
           </c:if>
