<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="../admin/cabeceraAdmin.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">PUBLICACIONES</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form action="filtroPubli">
        <s:textfield name="filtroPublicacion" cssClass="textFileFiltrar"/>
        <s:submit name="btoFiltroPubli" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
</s:div>
<s:if test="%{publicaciones.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No hay ninguna publicaci&oacute;n que empiece por <s:property value="filtroPublicacion"/></p>
    </s:div>
</s:if>
<s:else>
    <s:iterator var="publicacion" value="publicaciones">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:if test="%{getPublicacion().getFoto().length() > 0}"> 
                        <div class="publi-no-imagen" style="background-image: url(<s:property value="getFoto()"/>)">
                        </div>
                    </s:if>
                    <s:else>
                        <div class="publi-no-imagen" style="background-image: url(images/defaultPhoto.jpg)">
                        </div>
                    </s:else>
                    <s:div cssClass="datos-publicacion datos-publi-admin">
                        <s:div cssClass="datos-publicacion-left">
                            <h1><s:property value="titulo" /></h1>
                            <p>Nombre de usuario: <s:property value="idUsuario.nickname"/></p>
                            <p>Fecha de modificaci&oacute;n: <s:property value="fechaHoraModificacion"/></p>
                        </s:div>
                        <s:div cssClass="datos-publicacion-right">
                            <p>Comentarios: <s:property value="numComentarios" /></p>
                            <p>Votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                            <p>Votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                        </s:div>
                    </s:div>
                    <s:div cssClass="papelera-admin">
                        <s:form action="removePublicacion" method="post">
                            <s:hidden name="idPublicacionRemove" value="%{id}" /> 
                            <s:submit type="image" src="images/iconos/papelera.png" name="removePublicacion" cssClass="icono"/>
                        </s:form>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>
<%@include file="../../footer.jsp" %>
