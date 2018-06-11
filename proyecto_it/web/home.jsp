<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">HOME</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="filtroPublicacionesSuscrito">
        <s:textfield name="filtroPublicacion" cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
</s:div>
<s:if test="%{listaPublicaciones.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No tiene ninguna suscripci&oacute;n</p>
    </s:div>
</s:if>    
<s:else>  
    <s:iterator value="listaPublicaciones" var="publicacion">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <div class="publi-no-imagen" style="background-image: url(<s:property value="publicacion.getFoto()"/>)">
                    </div>
                    <s:div cssClass="datos-publicacion">
                        <s:form method="post" action="verPublicacion">
                            <s:hidden value="%{getPublicacion().getId()}" name="id_publi" />
                            <s:submit value="%{getPublicacion().getTitulo()}" cssClass="titulo-submit-publicacion"/>
                            <p><s:property value="usuario.getNickname()" /></p>
                            <p><s:property value="publicacion.getFechaHoraModificacion()" /></p>
                            <p>Número de votos positivos: <s:property value="numVotosPositivosPublicacion" /></p>
                            <p>Número de votos negativos: <s:property value="numVotosNegativosPublicacion" /></p>
                            <p>Último comentario: <s:property value="ultimoComentario.getFechaHoraModificacion()" /></p>                    
                        </s:form>
                    </s:div>
                    <s:div cssClass="contenedor-flechas">
                        <s:form method="post" action="votoPositivoPublicacion">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:submit type="image" src="images/iconos/flechaVerde.png" name="votoPositivoPublicacion" cssClass="flechas"/>
                        </s:form>
                        <s:form method="post" action="unFollowPublicacion">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:submit name="unFollowPublicacion" value="UnFollow" cssClass="btn btn-warning flechas follow" />
                        </s:form>
                        <s:form method="post" action="votoNegativoPublicacion">
                            <s:hidden name="idPublicacion" value="%{id}" />
                            <s:submit type="image" src="images/iconos/flechaRoja.png" name="votoNegativoPublicacion" cssClass="flechas"/>
                        </s:form>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>  
<%@include file="footer.jsp" %>
