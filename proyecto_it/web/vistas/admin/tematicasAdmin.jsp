<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="../admin/cabeceraAdmin.jsp" %>
<%@include file="addTematica.jsp" %>

<s:div cssClass="banner-gen">
    <h1 class="text-banner">TEMATICAS</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:textfield  cssClass="textFileFiltrar"/>
    <s:submit name="btoLogin" cssClass="btn btn-primary filtro" value="Filtrar"/>
    <s:div cssClass="contenedor-icono-add">
        <s:div cssClass="contenedor-icono-add" onclick="document.getElementById('id03').style.display = 'block'">
            <img src="images/iconos/add.png" class="icono"/>
        </s:div>
    </s:div>
</s:div>
<s:iterator var="tematica" value="tematicas">
    <s:div cssClass="container-fluid text-center">
        <s:div cssClass="row">
            <s:div cssClass="contenedor-publi sombreado">
                <s:div cssClass="publi-no-imagen">
                    <img src="<s:property value="imagen"/>"/>
                </s:div>
                <s:div cssClass="datos-publicacion">
                    <h1><s:property value="nombre"/></h1>
                    <p>Numero de publicaciones asociada</p>
                </s:div>
                <s:div cssClass="contenedor-iconos">
                    <s:form action="removeTematica" method="post">
                        <s:hidden name="idTematicaRemove" value="%{id}" /> 
                        <s:submit type="image" src="images/iconos/papelera.png" name="removeTematica" cssClass="icono"/>
                    </s:form>
                    <s:form action="editTematica" method="post">
                        <s:hidden name="idTematicaEdit" value="%{id}" /> 
                        <s:submit type="image" src="images/iconos/editar.png" name="editTematica" cssClass="icono"/>
                    </s:form>
                </s:div>
            </s:div>
        </s:div>
    </s:div>
</s:iterator>
<%@include file="../../footer.jsp" %>
