<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:div id="id03" cssClass="modal">
    <s:form theme="simple" cssClass=" modal-content animate contenedor-nweTematica" action="" method="post" enctype="multipart/form-data">
        <s:div cssClass="imgcontainer">
            <span onclick="document.getElementById('id03').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <!--<img src="images/imgLogin.png" alt="Avatar" class="avatar">-->
        </s:div>
        <s:div cssClass="container-addPublicacion">
            <h2 style="color: #f4511e;">Nueva Tematica</h2>
            <s:label><b>Titulo: </b></s:label>
            <s:textfield name="tituloTematica" cssClass="form-control"/>
            <s:label><b>Foto: </b></s:label> 
            <s:file name="fotoTematica"/>
            <s:submit name="btoAddTematica" cssClass="btn btn-primary" value="Crear"/>            
        </s:div>
    </s:form>
</s:div> 