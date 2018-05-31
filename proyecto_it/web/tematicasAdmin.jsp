<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraAdmin.jsp" %>
<body id="myPage">

    <s:div cssClass="banner-gen">
        <h1 class="text-banner">TEMATICAS</h1>
    </s:div>
    <s:div cssClass="container-fluid text-center">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
        <s:div cssClass="contenedor-icono-add">
            <img src="images/iconos/add.png" class="icono"/>
        </s:div>
    </s:div>
        <s:iterator var="tematica" value="tematicas">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <div class="publi-no-imagen" style=" background-image: url('<s:property value="imagen" />')">
                    </div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="nombre"/></h1>
                        <p>Numero de publicaciones asociada</p>
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
                        <img src="images/iconos/papelera.png" class="icono"/>
                        <img src="images/iconos/editar.png" class="icono"/>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
    <%@include file="footer.jsp" %>
