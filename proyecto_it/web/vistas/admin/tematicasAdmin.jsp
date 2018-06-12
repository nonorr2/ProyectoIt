<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="../admin/cabeceraAdmin.jsp" %>
<%@include file="addTematica.jsp" %>

<s:if test="error">
    <script>
        document.getElementById('id03').style.display = 'block';
    </script>
</s:if>
<s:if test="tematicaIguales">
    <script>
        document.getElementById('id03').style.display = 'block';
    </script>
</s:if>


<s:div cssClass="banner-gen">
    <h1 class="text-banner">TEMATICAS</h1>
</s:div>
<s:div cssClass="container-fluid text-center">
    <s:form action="filtrarTema">
        <s:textfield name="filtrarTema"  cssClass="textFileFiltrar"/>
        <s:submit name="btoFiltrarTema" cssClass="btn btn-primary filtro" value="Filtrar"/>
    </s:form>
    <s:div cssClass="contenedor-icono-add">
        <s:div cssClass="contenedor-icono-add" onclick="document.getElementById('id03').style.display = 'block'">
            <img src="images/iconos/add.png" class="icono"/>
        </s:div>
    </s:div>
</s:div>
<s:if test="%{tematicas.isEmpty()}">
    <s:div cssClass="alert alert-warning noContenido">
        <p>No hay ninguna tematica que empiece por <s:property value="filtrarTema"/></p>
    </s:div>
</s:if>
<s:else>
    <s:iterator var="tematica" value="tematicas">
        <s:div cssClass="container-fluid text-center">
            <s:div cssClass="row">
                <s:div cssClass="contenedor-publi sombreado">
                    <div class="publi-no-imagen" style="background-image: url(<s:property value="imagen"/>)">
                    </div>
                    <s:div cssClass="datos-publicacion">
                        <h1><s:property value="nombre"/></h1>
                        <p>N&uacute;mero de publicaciones: <s:property value="numPublicacionesDeUnaTematica"/></p>       
                    </s:div>
                    <s:div>
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
</s:else>
<%@include file="../../footer.jsp" %>
