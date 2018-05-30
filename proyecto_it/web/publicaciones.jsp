<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraNoLogado.jsp" %>
<body id="myPage">
    <div class="banner-gen">
        <h1 class="text-banner">PUBLICACIONES</h1>
    </div>
    <s:div cssClass="container-fluid text-center">
        <s:iterator var="publicacion" value="publicaciones">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado" onclick="document.getElementById('id01').style.display = 'block'">
                    <s:div cssClass="publi-no-imagen">
                        <s:div cssClass="letras-imagen">
                            <p class="iniciales-no-imagen">NR</p>
                        </s:div>
                    </s:div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="titulo" /></h1>
                        <!--                    LA FECHA NO SALE-->
                        <p><s:property value="fechaHoraModificacion" /></p>
                        <!--COMO HAGO LO DE LOS VOTOS Y LOS COMENTARIOS-->
                        <p>Numero de votos</p>
                        <p>Numero de comentarios</p>
                    </s:div>
                </s:div>
            </s:div>
        </s:iterator>
    </s:div>
    <%@include file="footer.jsp" %>