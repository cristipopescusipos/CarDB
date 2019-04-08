<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Alerts List :</title>
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

    <h1 class="text-primary"><i class="fa fa-calendar"></i>&nbsp;Alerts</h1>


    <div class="container">
        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo"></span><i class="fa fa-gears"></i>&nbsp;Useful Links</button>
        <div id="demo" class="collapse in">
            <p><a href="https://www.emag.ro/asigurari-rca#step-1">Link to emag for RCA</a></p>
            <p><a href="https://www.emag.ro/search/extinctor?ref=effective_search">Link to emag fro the fire extinguisher</a></p>
            <p><a href="https://www.emag.ro/search/trusa%20prim%20ajutor?ref=effective_search">Link to emag fro the First Aid Kit</a></p>
            <p><a href="http://prog.rarom.ro/listepublice/itp.asp?judet=timis">Link for ITP in Timisoara</a></p>
            <p><a href="https://promotortm.ro/service-auto/statie-itp/">Link for ITP at PROMOTOR</a></p>

        </div>
    </div>

    <table class="table table-hover">

        <tr>
            <th>Owner Name:</th>
            <th>Car Plates:</th>
            <th>Rca Expiration Date:</th>
            <th>Itp Expiration Date:</th>
            <th>Rovinieta Expiration Date:</th>
            <th>Fire Extinguisher Expiration Date:</th>
            <th>First Aid Expiration Date:</th>
            <th>Edit:</th>
        </tr>

        <c:forEach items="${requestScope.alerts}" var="alert">
            <tr>
                <td> <c:out value="${alert.name}"/> </td>
                <td> <c:out value="${alert.plates}"/> </td>
                <td> <c:out value="${alert.rca}"/> </td>
                <td> <c:out value="${alert.itp}"/> </td>
                <td> <c:out value="${alert.rovinieta}"/> </td>
                <td> <c:out value="${alert.fireExt}"/> </td>
                <td> <c:out value="${alert.firstAid}"/> </td>
                <td><a class="btn btn-success" href="?type=edit&idCar=<c:out value="${alert.idCar}"/>" role="button">Edit</a></td>
            </tr>
        </c:forEach>
    </table>



    <%@include file="../includes/footer.jsp" %>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/js/bs-animation.js"></script>
</body>

</html>
