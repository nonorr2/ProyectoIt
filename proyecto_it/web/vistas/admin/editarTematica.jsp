<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<%@include file="../admin/cabeceraAdmin.jsp" %>

<div class="banner-gen">
    <h1 class="text-banner">EDITAR TEMATICA</h1>
</div>

<s:div cssClass="container-fluid">
    <s:div cssClass="sombreado con-new-comment" >
        <s:form method="post" action="editTemaPersistencia" enctype="multipart/form-data"> 
            <s:div cssClass="imgcontainer">
                <s:fielderror fieldName="imgTematica" cssClass="alert alert-danger" />
                <img class="avatar imgTematica" src="<s:url value="%{tematica.imagen}"/>"/>
                <s:file name="imgTematica" id="fileImgTema" cssClass="fileImgUser"/>
            </s:div>
            <s:div cssClass="form-group col-sm-6">
                <s:fielderror fieldName="nombre" cssClass="alert alert-danger" />
                <s:label><b>Titulo:</b></s:label>
                <s:textfield name="nombre" placeholder="Introduzaca el Titulo" cssClass="form-control" id="titulo" value="%{tematica.nombre}"/>
            </s:div>
            <s:div cssClass="botonAceptarPerfil">
                <s:hidden name="id" value="%{tematica.id}" /> 
                <s:submit name="editarTema" cssClass="btn btn-primary filtro" value="Editar"/>
            </s:div>
        </s:form>
    </s:div>
</s:div>
<%@include file="../../footer.jsp" %> 