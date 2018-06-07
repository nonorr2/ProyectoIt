<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:div id="id03" cssClass="modal">
    <s:form theme="simple" cssClass=" modal-content animate contenedor-nweTematica" action="addTemaPersistencia" method="post" enctype="multipart/form-data">
        <s:div cssClass="imgcontainer">
            <span onclick="document.getElementById('id03').style.display = 'none'" class="close" title="Close Modal">&times;</span>
        </s:div>
        <s:div cssClass="container-addPublicacion">
            <h2 style="color: #f4511e;">Nueva Tematica</h2>
            <s:fielderror fieldName="nombre" cssClass="alert alert-danger" />
            <s:label><b>Titulo: </b></s:label>
            <s:textfield name="nombre" cssClass="form-control"/>
            <s:label><b>Imagen: </b></s:label> 
            <s:file name="imagen" id="fileImgTematica"/>
            <s:submit name="btoAddTematica" cssClass="btn btn-primary" value="Crear"/>            
        </s:div>
    </s:form>
</s:div> 