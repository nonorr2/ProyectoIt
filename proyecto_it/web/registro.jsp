<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="cabeceraNoLogado.jsp" %>

<div class="container-fluid text-center">    
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left"> 
            <h1>&Uacute;nete a nosotros</h1>

            <s:form method="post" action="">   
                <div class="form-group">
                    <s:label>Email:</s:label>
                    <s:textfield name="email" placeholder="Enter email" cssClass="form-control" id="email"/>
<!--                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">-->
                </div>
<!--                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="remember"> Remember me</label>
                </div>-->
                <!--<button type="submit" class="btn btn-default">Submit</button>-->
            </s:form>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
