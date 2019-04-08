<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car edit :</title>
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

    <h1 class="text-primary"><i class="fa fa-list-alt"></i>&nbsp;Edit Car : </h1>

    <form method="post" action="/car">

        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="idCar" value="<c:out value="${car.idCar}"/>">
        <%--<input type="hidden" name="idOwner" value="<c:out value="${car.idOwner}"/>">--%>

        <div class="form-group">
            <label class="col-sm-4 control-label">Car Plates :</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="plates" value="<c:out value="${car.plates}"/>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Car Rca Expiration Date :</label>
            <div class="col-sm-8">
                <input class="form-control" type="date" name="rca" value="<c:out value="${car.rca}"/>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Car RoVinieta Expiration Date :</label>
            <div class="col-sm-8">
                <input class="form-control" type="date" name="rovinieta" value="<c:out value="${car.rovinieta}"/>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Car Itp Expiration Date :</label>
            <div class="col-sm-8">
                <input class="form-control" type="date" name="itp" value="<c:out value="${car.itp}"/>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Car Fire Extinguisher Expiration Date :</label>
            <div class="col-sm-8">
                <input class="form-control" type="date" name="fireExt" value="<c:out value="${car.fireExt}"/>">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Car First Aid Expiration Date :</label>
            <div class="col-sm-8">
                <input class="form-control" type="date" name="firstAid" value="<c:out value="${car.firstAid}"/>">
            </div>
        </div>

        <button class="btn btn-primary" type="submit" value="Edit" >Edit</button>

    </form>

    <%@include file="../includes/footer.jsp" %>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/js/bs-animation.js"></script>

</body>
</html>
