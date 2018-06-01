<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:div id="id02" cssClass="modal">
    <s:form theme="simple" cssClass="modal-content animate contenedor-nweTematica" action="addPublicacion" method="post" enctype="multipart/form-data">
        <s:div cssClass="imgcontainer">
            <span onclick="document.getElementById('id02').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <!--<img src="images/imgLogin.png" alt="Avatar" class="avatar">-->
        </s:div>
        <s:div cssClass="container-addPublicacion">
            <h2 style="color: #f4511e;">Nueva Publicación</h2>
            <s:label><b>Título: </b></s:label>
            <s:textfield name="tituloPubliacion" cssClass="form-control"/>
            <s:label><b>Foto: </b></s:label> <%-- COMO GUARDAR LA IMAGEN --%>
            <s:file name="fotoPubliacion" cssClass="form-control-file"/>
            <s:label><b>Contenido: </b></s:label>
            <s:textarea name="contenidoPubliacion" cssClass="form-control"/>
            <s:label><b>Ruta: </b></s:label> <%-- CAMBIAR --%>
            <s:textfield name="rutaPubliacion" cssClass="form-control"/>
            <s:label><b>Temática: </b></s:label>
            <s:select name="tematicaPubliacion" list="tematicas" listKey="id" listValue="nombre" cssClass="form-control"/><br>
            <s:submit name="btoAddPublicacion" cssClass="btn btn-primary" value="Crear"/>            
        </s:div>
    </s:form>
</s:div>
