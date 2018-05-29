<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraNoLogado.jsp" %>
<body id="myPage">

    <s:div cssClass="banner-gen">
        <h1 class="text-banner">PUBLICACIONES</h1>
    </s:div>
    <s:div cssClass="container-fluid text-center">
        <s:textfield  cssClass="textFileFiltrar"/>
        <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:div>
    <s:iterator>
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <s:div cssClass="publi-no-imagen">
                        <s:div cssClass="letras-imagen">
                            <p class="iniciales-no-imagen">NR</p>
                        </s:div>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1>Titulo de la publicacion</h1>
                        <p>Autor</p>
                        <p>Fecha y hora</p>
                        <p>Numero de votos</p>
                        <p>Numero de comentarios</p>
                    </s:div>
                    <s:div cssClass="contenedor-iconos">
                        <img src="images/iconos/papelera.png" class="icono"/>
                    </s:div>
                </s:div>
            </s:div>
        </s:div>
    </s:iterator>
    <%@include file="footer.jsp" %>
