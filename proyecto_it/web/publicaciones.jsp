<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">PUBLICACIONES</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="filtrarPublicacion">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
</s:div>
<s:if test="%{publicacionesDecoradas.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No hay ninguna publicaci&oacute;n</p>
    </s:div>
</s:if>    
<s:else>       
    <s:iterator value="publicacionesDecoradas" var="publicacion">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <s:if test="%{getPublicacion().getFoto().length() > 0}"> 
                            <img src="<s:property value="getFoto()"/>" />
                        </s:if>
                        <s:else>
                            <img src="images/noFoto.png"/>
                        </s:else>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="getPublicacion().getTitulo()" /></h1>
                        <p>Número de votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                        <p>Número de votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                        <p>Numero de comentarios: <s:property value="numComentarios" /></p>
                        <p>Fecha de cración: <s:property value="getPublicacion().getFechaHoraModificacion()" /></p>
                    </s:div>
                    <s:div cssClass="contenedor-flechas">
                        <s:form method="post" action="votoPositivoPublicacionAjena">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:hidden name="idTema" value="%{getPublicacion().getIdTematica().getId()}" />
                            <s:submit type="image" src="images/iconos/flechaVerde.png" name="votoPositivoPublicacion" cssClass="flechas"/>
                        </s:form>
                        <s:if test="%{isSuscripcion()}">
                            <s:form method="post" action="unFollowPublicacionAjena">
                                <s:hidden name="idPublicacion" value="%{id}" />
                                <s:hidden name="idTema" value="%{getPublicacion().getIdTematica().getId()}" />
                                <s:submit name="unFollowPublicacion" value="UnFollow" cssClass="btn btn-warning flechas follow" />
                            </s:form>
                        </s:if>
                        <s:else>
                            <s:form method="post" action="followPublicacion">
                                <s:hidden name="idPublicacion" value="%{id}" />
                                <s:hidden name="idTema" value="%{getPublicacion().getIdTematica().getId()}" />
                                <s:submit name="followPublicacion" value="Follow" cssClass="btn btn-info flechas follow" />
                            </s:form>
                        </s:else>
                        <s:form method="post" action="votoNegativoPublicacionAjena">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:hidden name="idTema" value="%{getPublicacion().getIdTematica().getId()}" />
                            <s:submit type="image" src="images/iconos/flechaRoja.png" name="votoNegativoPublicacion" cssClass="flechas"/>
                        </s:form>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>  
<%@include file="footer.jsp" %>