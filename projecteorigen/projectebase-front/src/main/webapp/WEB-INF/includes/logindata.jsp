
    <c:choose>
        <c:when test="${loginib.logged}">
            <p>
            Dades autenticació:</p>
            <dl>
                <dt>Nom:</dt>
                <dd>${loginib.datos.nombre} ${loginib.datos.apellidos}</dd>
                <dt>NIF:</dt>
                <dd>${loginib.datos.nif}</dd>
                <dt>QAA:</dt>
                <dd>${loginib.datos.qaa}</dd>
                <dt>Mètode autenticació:</dt>
                <dd>${loginib.datos.metodoAutenticacion}</dd>
            </dl>    
            <p>
            <a href="${request.contextPath}/logout">Logout</a>
            </p>
        </c:when>
        <c:otherwise>
            <p>Usuari no autenticat</p>
        </c:otherwise>
    </c:choose>