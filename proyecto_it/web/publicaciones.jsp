<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="cabeceraLogado.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">PUBLICACIONES</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form method="post" action="">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
</s:div>
<s:if test="%{publicaciones.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No ha creado ninguna publicaci&oacute;n</p>
    </s:div>
</s:if>    
<s:else>       
    <s:iterator value="publicaciones" var="publicacion">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <s:if test="%{getFoto().length() > 0}"> 
                            <img src="<s:property value="getFoto()"/>" />
                        </s:if>
                        <s:else>
                            <img src="images/noFoto.png"/>
                        </s:else>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="getTitulo()" /></h1>
                        <p>Fecha de cración: <s:property value="getFechaHoraModificacion()" /></p>
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
</s:else>  
<%@include file="footer.jsp" %>