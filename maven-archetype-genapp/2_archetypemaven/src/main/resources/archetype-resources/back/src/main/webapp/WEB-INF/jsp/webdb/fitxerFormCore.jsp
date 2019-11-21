#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FitxerFields" className="${package}.model.fields.FitxerFields"/>
  
        <c:if test="${symbol_dollar}{!gen:contains(__theForm.hiddenFields,FitxerFields.NOM)}">
        <tr id="fitxer_nom_rowid">
          <td>
            <label>
              <fmt:message key="${symbol_dollar}{(empty __theForm.labels[FitxerFields.NOM])?'fitxer.nom':__theForm.labels[FitxerFields.NOM]}" /> &nbsp;(*)
              <c:if test="${symbol_dollar}{not empty __theForm.help[FitxerFields.NOM]}">
              <i class="icon-info-sign" title="${symbol_dollar}{__theForm.help[FitxerFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fitxer.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${symbol_dollar}{ gen:contains(__theForm.readOnlyFields ,FitxerFields.NOM)? 'true' : 'false'}" cssClass="${symbol_dollar}{gen:contains(__theForm.readOnlyFields ,FitxerFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="fitxer.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${symbol_dollar}{!gen:contains(__theForm.hiddenFields,FitxerFields.DESCRIPCIO)}">
        <tr id="fitxer_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${symbol_dollar}{(empty __theForm.labels[FitxerFields.DESCRIPCIO])?'fitxer.descripcio':__theForm.labels[FitxerFields.DESCRIPCIO]}" />
              <c:if test="${symbol_dollar}{not empty __theForm.help[FitxerFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${symbol_dollar}{__theForm.help[FitxerFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="fitxer.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${symbol_dollar}{ gen:contains(__theForm.readOnlyFields ,FitxerFields.DESCRIPCIO)? 'true' : 'false'}" path="fitxer.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="${symbol_pound}" onclick="javascript:var ta=document.getElementById('fitxer.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="${symbol_pound}" onclick="javascript:var ta=document.getElementById('fitxer.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="${symbol_pound}" onclick="javascript:var ta=document.getElementById('fitxer.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${symbol_dollar}{!gen:contains(__theForm.hiddenFields,FitxerFields.MIME)}">
        <tr id="fitxer_mime_rowid">
          <td>
            <label>
              <fmt:message key="${symbol_dollar}{(empty __theForm.labels[FitxerFields.MIME])?'fitxer.mime':__theForm.labels[FitxerFields.MIME]}" /> &nbsp;(*)
              <c:if test="${symbol_dollar}{not empty __theForm.help[FitxerFields.MIME]}">
              <i class="icon-info-sign" title="${symbol_dollar}{__theForm.help[FitxerFields.MIME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fitxer.mime" cssClass="errorField alert alert-error" />
            <form:input readonly="${symbol_dollar}{ gen:contains(__theForm.readOnlyFields ,FitxerFields.MIME)? 'true' : 'false'}" cssClass="${symbol_dollar}{gen:contains(__theForm.readOnlyFields ,FitxerFields.MIME)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="fitxer.mime"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${symbol_dollar}{!gen:contains(__theForm.hiddenFields,FitxerFields.TAMANY)}">
        <tr id="fitxer_tamany_rowid">
          <td>
            <label>
              <fmt:message key="${symbol_dollar}{(empty __theForm.labels[FitxerFields.TAMANY])?'fitxer.tamany':__theForm.labels[FitxerFields.TAMANY]}" /> &nbsp;(*)
              <c:if test="${symbol_dollar}{not empty __theForm.help[FitxerFields.TAMANY]}">
              <i class="icon-info-sign" title="${symbol_dollar}{__theForm.help[FitxerFields.TAMANY]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fitxer.tamany" cssClass="errorField alert alert-error" />
            <form:input readonly="${symbol_dollar}{ gen:contains(__theForm.readOnlyFields ,FitxerFields.TAMANY)? 'true' : 'false'}" cssClass="${symbol_dollar}{gen:contains(__theForm.readOnlyFields ,FitxerFields.TAMANY)? 'input-mini uneditable-input' : 'input-mini'}"   path="fitxer.tamany"   />

           </td>
        </tr>
        </c:if>
        
