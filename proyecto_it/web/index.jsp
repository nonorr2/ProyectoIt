<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraNoLogado.jsp" %>
<body id="myPage">
    <div class="banner-index">
        <p class="text-banner">Habla de lo que quieras</p>
        <br>
        <p class="text-banner">SIN CENSURA</p>
    </div>
    <div class="container-fluid text-center">
        <h2>TEMAS M&Aacute;S ACTIVOS</h2>
        <br>
        <s:div cssClass="row">
            <s:iterator var="tema" value="tematicas">
                <s:div cssClass="col-sm-3 sombreado">
                    <div class="div-icono" style=" background-image: url('<s:property value="imagen" />')"></div>
                    <h4><s:property value="nombre" /></h4>
                </s:div>
            </s:iterator>
        </s:div>
    </div>
    <%@include file="footer.jsp" %>