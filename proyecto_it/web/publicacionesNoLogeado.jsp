<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraNoLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">PUBLICACIONES</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="filtrarPublicacionesNoLogeado">
        <s:textfield  cssClass="textFileFiltrar" name="filtroPublicacion"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
</s:div>
<s:if test="%{publicacionesDecoradas.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No hay ninguna publicaci&oacute;n</p>
    </s:div>
</s:if>    
<s:else>       
    <s:iterator value="publicaciones" var="publicacion">
        <s:div cssClass="container-fluid text-center" onclick="document.getElementById('id01').style.display = 'block'">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:if test="%{getPublicacion().getFoto().length() > 0}">
                        <div class="publi-no-imagen" style="background-image: url(<s:property value="getFoto()"/>)">
                        </div>
                    </s:if>
                    <s:else>
                        <div class="publi-no-imagen" style="background-image: url(images/noFoto.png)">
                        </div>
                    </s:else>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="titulo"/></h1>
                        <p>Número de votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                        <p>Número de votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                        <p>Numero de comentarios: <s:property value="numComentarios" /></p>
                        <p>Fecha de cración: <s:property value="getPublicacion().getFechaHoraModificacion()" /></p>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>  
<%@include file="footer.jsp" %>