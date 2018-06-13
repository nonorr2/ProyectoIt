<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">PUBLICACIONES</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="filtrarPublicacionesTemas">
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
    <s:iterator value="publicacionesDecoradas" var="publicacion">
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

                    <s:div cssClass="datos-publicacion">
                        <s:form method="post" action="verPublicacion">
                            <s:hidden value="%{getPublicacion().getId()}" name="id_publi" />
                            <s:submit value="%{getPublicacion().getTitulo()}" cssClass="titulo-submit-publicacion"/>
                            <p>Número de votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                            <p>Número de votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                            <p>Numero de comentarios: <s:property value="numComentarios" /></p>
                            <p>Fecha de cración: <s:property value="getPublicacion().getFechaHoraModificacion()" /></p>
                        </s:form>
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