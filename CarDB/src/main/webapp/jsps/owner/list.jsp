<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car list :</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <link rel="stylesheet" href="../../assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="../../assets/css/styles.css">
    <link rel="stylesheet" href="../../assets/css/Footer-Basic.css">
    <link  href="../../assets/css/Features-Boxed.css" rel="stylesheet" >
    <link  href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" rel="stylesheet" >
    <link  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >
    <link  href="../../assets/css/Features-Clean.css" rel="stylesheet" >
    <link  href="../../assets/css/Footer-Clean.css" rel="stylesheet" >

</head>
<body>
<%@include file="../includes/menu.jsp" %>

<div class="container p-3 mb-2 bg-light text-dark">

    <h1 class="text-primary"><i class="fa fa-user"></i>&nbsp;List of Owners :</h1>

    <div class="container">
        <a class="btn btn-success" href="?action=add" role="button">Add Owner</a>
        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo"></span><i class="fa fa-gears"></i>&nbsp;Useful Info</button>
        <div id="demo" class="collapse in">
            <p>Owners have unique names, telephon numbers and emails;</p>
            <p>You cant delete a owner if he has a car on his name;</p>

        </div>

    </div>


        <table class="table table-hover">

            <tr>
                <th>Owner Name:</th>
                <th>Owner Telephone:</th>
                <th>Owner Email:</th>
                <th>Edit/Delete Owner:</th>
            </tr>

            <c:forEach items="${requestScope.owners}" var="owner">

                <tr>
                    <td><c:out value="${owner.name}"/></td>
                    <td><c:out value="${owner.telephone}"/></td>
                    <td><c:out value="${owner.email}"/></td>
                    <td>
                        <a class="btn btn-success" href="?action=edit&idOwner=<c:out value="${owner.idOwner}"/>" role="button">Edit</a>
                        <a Onclick="return ConfirmDelete();"class="btn btn-danger" href="?action=delete&idOwner=<c:out value="${owner.idOwner}"/>" role="button">Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>


    <%@include file="../includes/footer.jsp" %>

</div>

<script>
    function ConfirmDelete()
    {
        var x = confirm("Are you sure you want to delete?");
        if (x)
            return true;
        else
            return false;
    }
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/js/bs-animation.js"></script>

</body>
</html>
