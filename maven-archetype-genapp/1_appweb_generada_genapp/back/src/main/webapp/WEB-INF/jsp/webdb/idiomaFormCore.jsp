<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IdiomaFields" className="org.fundaciobit.projectebase.archetype.model.fields.IdiomaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.IDIOMAID)}">
        <tr id="idioma_idiomaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.IDIOMAID])?'idioma.idiomaID':__theForm.labels[IdiomaFields.IDIOMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IdiomaFields.IDIOMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[IdiomaFields.IDIOMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="idioma.idiomaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.IDIOMAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IdiomaFields.IDIOMAID)? 'input-mini uneditable-input' : 'input-mini'}"  maxlength="5" path="idioma.idiomaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.NOM)}">
        <tr id="idioma_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.NOM])?'idioma.nom':__theForm.labels[IdiomaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IdiomaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[IdiomaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="idioma.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IdiomaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="idioma.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.SUPORTAT)}">
        <tr id="idioma_suportat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.SUPORTAT])?'idioma.suportat':__theForm.labels[IdiomaFields.SUPORTAT]}" />
              <c:if test="${not empty __theForm.help[IdiomaFields.SUPORTAT]}">
              <i class="icon-info-sign" title="${__theForm.help[IdiomaFields.SUPORTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,IdiomaFields.SUPORTAT)}" >
              <form:errors path="idioma.suportat" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.SUPORTAT)? 'false' : 'true'}" path="idioma.suportat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,IdiomaFields.SUPORTAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.idioma.suportat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.ORDRE)}">
        <tr id="idioma_ordre_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.ORDRE])?'idioma.ordre':__theForm.labels[IdiomaFields.ORDRE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IdiomaFields.ORDRE]}">
              <i class="icon-info-sign" title="${__theForm.help[IdiomaFields.ORDRE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="idioma.ordre" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.ORDRE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IdiomaFields.ORDRE)? 'input-mini uneditable-input' : 'input-mini'}"   path="idioma.ordre"   />

           </td>
        </tr>
        </c:if>
        