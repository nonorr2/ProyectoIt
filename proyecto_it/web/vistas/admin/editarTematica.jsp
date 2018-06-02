<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="../admin/cabeceraAdmin.jsp" %>

<div class="banner-gen">
    <h1 class="text-banner">EDITAR TEMATICA</h1>
</div>

<s:div cssClass="container-fluid text-center">
    <s:div cssClass="col-sm-2 col-sm-2-new"></s:div>
    <s:div cssClass="col-sm-8 text-left borde-tematica" >
        <s:form method="post" action="editTemaPersistencia"> 

            <s:div cssClass="imgcontainer">
                <img class="avatar imgTematica" src="<s:url value="%{tematica.imagen}"/>"/>
                <s:file name="imagen" id="fileImgTema" cssClass="fileImgUser"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:label><b>Titulo:</b></s:label>
                <s:textfield name="nombre" placeholder="Introduzaca el Titulo" cssClass="form-control" id="titulo" value="%{tematica.nombre}"/>
            </s:div>
            <s:div cssClass="botonAceptarPerfil">
                <s:hidden name="id" value="%{tematica.id}" /> 
                <s:submit name="editarTema" cssClass="btn btn-primary filtro" value="Editar"/>
            </s:div>
        </s:form>
    </s:div>
    <s:div cssClass="col-sm-2"></s:div>
</s:div>
<%@include file="../../footer.jsp" %> 