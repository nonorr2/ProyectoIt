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
        <p>No hay ninguna publicaci&oacute;n que empiece por <s:property value="filtroPubli"/></p>
    </s:div>
</s:if>
<s:else>
    <s:iterator var="publicacion" value="publicaciones">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <img src="<s:property value="foto"/>"/>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="titulo" /></h1>
                        <p><s:property value="idUsuario.nickname"/></p>
                        <p><s:property value="fechaHoraModificacion"/></p>
                        <p>Comentarios: <s:property value="numComentarios" /></p> 
                        <s:div cssClass="contenedor-iconos-pulgares">
                            <s:div>
                                <img src="images/iconos/pulgarPositivo.png" class="icono-pulgar">
                                <p class="divPulgarPos"><s:property value="numVotosPositivosPublicacion" /></p>
                            </s:div>
                            <s:div>
                                <img src="images/iconos/pulgarNegativo.png" class="icono-pulgar">
                                <p class="divPulgarNeg"><s:property value="numVotosNegativosPublicacion" /></p>
                            </s:div>
                        </s:div>
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
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
